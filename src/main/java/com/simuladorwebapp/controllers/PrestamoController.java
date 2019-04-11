package com.simuladorwebapp.controllers;

import java.util.Arrays;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simuladorwebapp.models.entity.EnumTipoCuota;
import com.simuladorwebapp.models.entity.EnumTipoPrestamo;
import com.simuladorwebapp.models.entity.EnumTipoSeguroBien;
import com.simuladorwebapp.models.entity.EnumTipoSeguroDesgravamen;
import com.simuladorwebapp.models.entity.Persona;
import com.simuladorwebapp.models.entity.Prestamo;
import com.simuladorwebapp.service.IPersonaService;
import com.simuladorwebapp.service.IPrestamoService;
import com.simuladorwebapp.service.IUsuarioService;

@Secured("ROLE_ANALISTA")
@RequestMapping("/prestamo")
@SessionAttributes("prestamo")
@Controller
public class PrestamoController {
	
	@Autowired
	private IPrestamoService prestamoService;

	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping(value = {"", "/"})
	public String mostrarInicio() {
		return "redirect:/prestamo/formPrincipal";
	}
	
	@GetMapping(value = "/formPrincipal")
	public String formPrincipal(Model model) {		
		model.addAttribute("prestamo", new Prestamo());
		model.addAttribute("tiposPrestamo", Arrays.stream(EnumTipoPrestamo.values()).map(Enum::name).collect(Collectors.toList()).toArray());
		model.addAttribute("titulo", "Realizar Préstamo");
		return "prestamo/formularioPrincipal";
	}
	
	@PostMapping(value = "/formPrincipal")
	public String formPrincipal(@Valid Prestamo prestamo, BindingResult result, Model model, RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Realizar Préstamo");
			return "prestamo/formularioPrincipal";
		}
		Persona persona = null;
		String dni = prestamo.getCliente().getPersona().getDni();
		if(dni.length()==8 && !dni.isEmpty() && dni!=null) {
			persona = personaService.findByDni(dni); //Busca al cliente dentro de la BD identificado con su DNI.
			if (persona == null) {
				flash.addFlashAttribute("error", "El DNI ingresado no existe en la BD!");
				return "redirect:/prestamo/formPrincipal";
			}else {
				if(persona.getCliente()==null) {
					flash.addFlashAttribute("error", "El DNI ingresado no está registrado como Cliente en la BD!");
					return "redirect:/prestamo/formPrincipal";
				}else {
					prestamo.setCliente(persona.getCliente());
					String errorMensaje = prestamo.verificarCliente();
					if(errorMensaje!=null) {
						flash.addFlashAttribute("error", errorMensaje);
						return "redirect:/prestamo/formPrincipal";
					}
				}
			}
		}else {
			flash.addFlashAttribute("error", "El DNI ingresado no tiene el formato válido. Recuerde que son 8 números que lo conforman!");
			return "redirect:/prestamo/formPrincipal";
		}
		if(prestamo.getTipo_prestamo()==EnumTipoPrestamo.Efectivo) {
			return "redirect:/prestamo/formEfectivo";
		}else if(prestamo.getTipo_prestamo()==EnumTipoPrestamo.Hipotecario) {
			return "redirect:/prestamo/formHipotecario";
		} else if(prestamo.getTipo_prestamo()==EnumTipoPrestamo.Vehicular) {
			return "redirect:/prestamo/formVehicular";
		}else {
			flash.addFlashAttribute("error", "No ha seleccionado el Tipo de Préstamo válido.");
			return "redirect:/prestamo/formPrincipal";
		}
	}
	
	@GetMapping(value = "/formEfectivo")
	public String formularioEfectivo(RedirectAttributes flash, Model model, HttpSession session) {
		Prestamo prestamo = (Prestamo) session.getAttribute("prestamo");
		if(prestamo.getCliente()==null) {
			flash.addFlashAttribute("error", "El Cliente ingresado no existe en la BD!");
			return "redirect:/prestamo/formPrincipal";
		}
		model.addAttribute("plazos", prestamo.obtenerPlazosPrestamos());
		model.addAttribute("tipocuotas", Arrays.stream(EnumTipoCuota.values()).map(Enum::name).collect(Collectors.toList()).toArray());
		model.addAttribute("titulo", "Solicitud de Préstamo Efectivo");
		return "prestamo/formularioEfectivo";
	}
	
	@GetMapping(value = "/formHipotecario")
	public String formularioHipotecario(RedirectAttributes flash, Model model, HttpSession session) {
		Prestamo prestamo = (Prestamo) session.getAttribute("prestamo");
		if(prestamo.getCliente()==null) {
			flash.addFlashAttribute("error", "El Cliente ingresado no existe en la BD!");
			return "redirect:/prestamo/formPrincipal";
		}
		model.addAttribute("plazos", prestamo.obtenerPlazosPrestamos());
		model.addAttribute("tipocuotas", Arrays.stream(EnumTipoCuota.values()).map(Enum::name).collect(Collectors.toList()).toArray());
		model.addAttribute("tiposdesegurobien", Arrays.stream(EnumTipoSeguroBien.values()).map(Enum::name).collect(Collectors.toList()).toArray());
		model.addAttribute("tiposdesegurodesgravamen", Arrays.stream(EnumTipoSeguroDesgravamen.values()).map(Enum::name).collect(Collectors.toList()).toArray());
		model.addAttribute("titulo", "Solicitud de Préstamo Hipotecario");
		return "prestamo/formularioHipotecario";
	}
	
	@GetMapping(value = "/formVehicular")
	public String formularioVehicular(RedirectAttributes flash, Model model, HttpSession session) {
		Prestamo prestamo = (Prestamo) session.getAttribute("prestamo");
		if(prestamo.getCliente()==null) {
			flash.addFlashAttribute("error", "El Cliente ingresado no existe en la BD!");
			return "redirect:/prestamo/formPrincipal";
		}
		model.addAttribute("plazos", prestamo.obtenerPlazosPrestamos());
		model.addAttribute("cuotasIniciales", prestamo.obtenerCuotasInicial());
		model.addAttribute("tipocuotas", Arrays.stream(EnumTipoCuota.values()).map(Enum::name).collect(Collectors.toList()).toArray());
		model.addAttribute("tiposdesegurodesgravamen", Arrays.stream(EnumTipoSeguroDesgravamen.values()).map(Enum::name).collect(Collectors.toList()).toArray());
		model.addAttribute("titulo", "Solicitud de Préstamo Vehicular");
		return "prestamo/formularioVehicular";
	}
	
	@PostMapping(value = "/listarCuotas")
	public String listarCuotas(@Valid Prestamo prestamo,  BindingResult result, Model model, RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Solicitud de Préstamo" + prestamo.getTipo_prestamo().name());
			return "redirect:/prestamo/form" + prestamo.getTipo_prestamo().name();
		}
		String errorMensaje1 = prestamo.establecerConstantes();
		if(errorMensaje1!=null) {
			flash.addFlashAttribute("error", errorMensaje1);
			return "redirect:/prestamo/formPrincipal";
		}else {
			String errorMensaje2 = prestamo.sonMontosCorrectos();
			if(errorMensaje2!=null) {
				flash.addFlashAttribute("error", errorMensaje2);
				return "redirect:/prestamo/formPrincipal";
			}
		}
		prestamo.generarCronograma();
		model.addAttribute("titulo", "Lista de Cuotas");
		return "prestamo/listaCuotas";
	}
	
	@PostMapping(value = "/guardarPrestamo")
	public String guardarPrestamo(RedirectAttributes flash, HttpSession session) {
		Prestamo prestamo = (Prestamo) session.getAttribute("prestamo");
		prestamo.setUsuario(usuarioService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()));
		if(prestamoService.save(prestamo) != null) {
			flash.addFlashAttribute("success", "Préstamo registrado con éxito!");
		}else {
			flash.addFlashAttribute("error", "No se pudo registrar el Préstamo!");
		}
		session.removeAttribute("prestamo");
		return "redirect:/prestamo/formPrincipal";
	}
	
}
