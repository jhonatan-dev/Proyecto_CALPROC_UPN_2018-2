package com.simuladorwebapp.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.simuladorwebapp.models.entity.Cuota;
import com.simuladorwebapp.models.entity.Pago;
import com.simuladorwebapp.models.entity.Persona;
import com.simuladorwebapp.models.entity.Prestamo;
import com.simuladorwebapp.service.ICuotaService;
import com.simuladorwebapp.service.IPagoService;
import com.simuladorwebapp.service.IPersonaService;
import com.simuladorwebapp.service.IPrestamoService;
import com.simuladorwebapp.service.IUsuarioService;

@Secured("ROLE_CAJERO")
@RequestMapping("/pago")
@SessionAttributes("prestamopago")
@Controller
public class PagoController {

	@Autowired
	private IPrestamoService prestamoService;

	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private ICuotaService cuotaService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPagoService pagoService;
	
	@GetMapping(value = {"", "/"})
	public String mostrarInicio() {
		return "redirect:/pago/formPrincipal";
	}
	
	@GetMapping(value = "/formPrincipal")
	public String formPrincipal(Model model) {
		model.addAttribute("prestamopago", new Prestamo());
		model.addAttribute("titulo", "Realizar Pago de Préstamo");
		return "pago/formularioPrincipal";
	}
	
	@PostMapping(value = "/formPrincipal")
	public String formPrincipal(@Valid Prestamo prestamopago, BindingResult result, Model model, RedirectAttributes flash, HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Realizar Pago de Préstamo");
			return "pago/formularioPrincipal";
		}
		Persona persona = null;
		String dni = prestamopago.getCliente().getPersona().getDni();
		if(dni.length()==8 && !dni.isEmpty() && dni!=null) {
			persona = personaService.findByDni(dni); //Busca al cliente dentro de la BD identificado con su DNI.
			if (persona == null) {
				flash.addFlashAttribute("error", "El DNI ingresado no existe en la BD!");
				return "redirect:/pago/formPrincipal";
			}else {
				if(persona.getCliente()==null) {
					flash.addFlashAttribute("error", "El DNI ingresado no está registrado como Cliente en la BD!");
					return "redirect:/pago/formPrincipal";
				}
			}
		}else {
			flash.addFlashAttribute("error", "El DNI ingresado no tiene el formato válido. Recuerde que son 8 números que lo conforman!");
			return "redirect:/pago/formPrincipal";
		}
		String codigo = prestamopago.getCodigo();
		if(codigo.length()==19 && !codigo.isEmpty() && codigo!=null) {
			 prestamopago = prestamoService.findByCodigo(codigo);
			if(prestamopago == null) {
				flash.addFlashAttribute("error", "El código de Préstamo ingresado no existe en la BD!");
				return "redirect:/pago/formPrincipal";
			}else {
				//Verifica que haya relación entre el Préstamo y el Cliente
				if(!prestamopago.getCliente().getPersona().getDni().toString().equals(dni)) {
					flash.addFlashAttribute("error", "El código de Préstamo no corresponde al DNI ingresado!");
					return "redirect:/pago/formPrincipal";	
				}else {
					//Verifica si todas las Cuotas del Préstamo han sido pagadas.
					if(prestamopago.comprobarPrestamoCompletamentePagado()) {
						flash.addFlashAttribute("error", "El Préstamo ya ha sido pagado en su totalidad!");
						return "redirect:/pago/formPrincipal";
					}
				}
			}
		}else{
			flash.addFlashAttribute("error", "El código del Préstamo no tiene el Formato Válido. Recuerde que son solo números que lo conforman!");
			return "redirect:/pago/formPrincipal";
		}
		model.addAttribute("prestamopago", prestamopago);
		return "redirect:/pago/listarCuotas";
	}
	
	@GetMapping(value = "/listarCuotas")
	public String listarCuotas(Model model) {
		model.addAttribute("titulo", "Lista de Cuotas");
		return "pago/listaCuotas";
	}
	
	@GetMapping(value = "/pagarCuota")
	public String pagarCuota(@RequestParam Long idCuota, RedirectAttributes flash, HttpSession session) {
		Prestamo prestamopago = (Prestamo) session.getAttribute("prestamopago");
		Cuota cuotaF = prestamopago.getCuotas().stream().filter(cuota -> idCuota==cuota.getId()).findAny().orElse(null);
		Pago pago = new Pago();
		pago.setUsuario(usuarioService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()));
		pago.setCuota(null);
		pago.PagarCuota(cuotaF);
		cuotaF.setPago(pago);
		if(pagoService.save(cuotaF.getPago())!=null) {
			if(cuotaService.save(cuotaF)!=null) {
				flash.addFlashAttribute("success", "Pago de Cuota registrada con éxito!");
			}else {
				flash.addFlashAttribute("error", "No se pudo registrar el Pago de Cuota!");
			}
		}else {
				flash.addFlashAttribute("error", "No se pudo registrar el Pago de Cuota!");
		}
		session.removeAttribute("prestamopago");
		return "redirect:/pago/formPrincipal";
	}
}