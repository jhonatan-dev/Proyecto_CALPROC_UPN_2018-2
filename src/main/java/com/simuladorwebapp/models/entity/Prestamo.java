package com.simuladorwebapp.models.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="prestamo")
public class Prestamo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="El Código del Préstamo no debe ser Nulo.")
	@NotEmpty(message="El Código del Préstamo no debe estar vacío.")
	@Size(min=19, max=19, message="El Código del Préstamo tiene 19 caracteres.")
	@Pattern(regexp="([0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4})", message="Ingrese un Código de Préstamo Válido.")
	@Column(nullable=false)
	private String codigo;
	
	@NotNull(message="Tipo de Préstamo no debe ser Nulo.")
	@Enumerated(EnumType.STRING)
	private EnumTipoPrestamo tipo_prestamo;
	
	@NotNull(message="Tipo de Cuota debe ser Nulo.")
	@Enumerated(EnumType.STRING)
	private EnumTipoCuota tipo_cuota;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoSeguroBien tipo_seguro_del_bien;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=true)
	private EnumTipoSeguroDesgravamen tipo_de_seguro_desgravamen;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(nullable=false)
	private Date fecha_desembolso;
	
	@Column(nullable=false)
	private int dia_de_pago;
	
	@Column(nullable=false)
	private int cuotaInicial;
	
	@Column(nullable=false)
	private double tea;
	
	@Column(nullable=false)
	private double tdes;
	
	@NotNull(message="Monto a Financiar no debe ser Nulo.")
	@Positive(message="Monto a Financiar debe ser mayor a cero.")
	private double monto_a_financiar;
	
	@NotNull(message="Número de Cuotas no debe ser Nulo.")
	@Positive(message="Número de Cuotas debe ser mayor a cero.")
	private int numero_cuotas;
	
	@Column(nullable=false)
	private double interes_moratorio;

	@Column(nullable=false)
	private double costo_envio_a_casa;
	
	@PositiveOrZero(message="Valor del Bien debe ser cero o mayor a este.")
	@Column(nullable = true)
	private double importe_asegurado;
	
	@Column(nullable = true)
	private double tasa_seguro_del_bien;
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private EnumTipoBien tipo_del_bien;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable=false, foreignKey = @ForeignKey(name="FK_P_CLIENTE_ID"))
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable=false, foreignKey = @ForeignKey(name="FK_P_USUARIO_ID"))
	private Usuario usuario;
	
	@OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Cuota> cuotas = new ArrayList<>();
	
	public Prestamo() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getCuotaInicial() {
		return cuotaInicial;
	}

	public void setCuotaInicial(int cuotaInicial) {
		this.cuotaInicial = cuotaInicial;
	}

	public EnumTipoBien getTipo_del_bien() {
		return tipo_del_bien;
	}

	public void setTipo_del_bien(EnumTipoBien tipo_del_bien) {
		this.tipo_del_bien = tipo_del_bien;
	}

	public EnumTipoPrestamo getTipo_prestamo() {
		return tipo_prestamo;
	}

	public void setTipo_prestamo(EnumTipoPrestamo tipo_prestamo) {
		this.tipo_prestamo = tipo_prestamo;
	}

	public EnumTipoSeguroBien getTipo_seguro_del_bien() {
		return tipo_seguro_del_bien;
	}

	public void setTipo_seguro_del_bien(EnumTipoSeguroBien tipo_seguro_del_bien) {
		this.tipo_seguro_del_bien = tipo_seguro_del_bien;
	}

	public EnumTipoSeguroDesgravamen getTipo_de_seguro_desgravamen() {
		return tipo_de_seguro_desgravamen;
	}

	public void setTipo_de_seguro_desgravamen(EnumTipoSeguroDesgravamen tipo_de_seguro_desgravamen) {
		this.tipo_de_seguro_desgravamen = tipo_de_seguro_desgravamen;
	}
	
	public EnumTipoCuota getTipo_cuota() {
		return tipo_cuota;
	}

	public void setTipo_cuota(EnumTipoCuota tipo_cuota) {
		this.tipo_cuota = tipo_cuota;
	}

	public Date getFecha_desembolso() {
		return fecha_desembolso;
	}

	public void setFecha_desembolso(Date fecha_desembolso) {
		this.fecha_desembolso = fecha_desembolso;
	}

	public int getDia_de_pago() {
		return dia_de_pago;
	}

	public void setDia_de_pago(int dia_de_pago) {
		this.dia_de_pago = dia_de_pago;
	}

	public double getTea() {
		return tea;
	}

	public void setTea(double tea) {
		this.tea = tea;
	}

	public double getTdes() {
		return tdes;
	}

	public void setTdes(double tdes) {
		this.tdes = tdes;
	}

	public double getMonto_a_financiar() {
		return monto_a_financiar;
	}

	public void setMonto_a_financiar(double monto_a_financiar) {
		this.monto_a_financiar = monto_a_financiar;
	}

	public int getNumero_cuotas() {
		return numero_cuotas;
	}

	public void setNumero_cuotas(int numero_cuotas) {
		this.numero_cuotas = numero_cuotas;
	}

	public double getInteres_moratorio() {
		return interes_moratorio;
	}

	public void setInteres_moratorio(double interes_moratorio) {
		this.interes_moratorio = interes_moratorio;
	}

	public double getCosto_envio_a_casa() {
		return costo_envio_a_casa;
	}

	public void setCosto_envio_a_casa(double costo_envio_a_casa) {
		this.costo_envio_a_casa = costo_envio_a_casa;
	}

	public double getTasa_seguro_del_bien() {
		return tasa_seguro_del_bien;
	}

	public void setTasa_seguro_del_bien(double tasa_seguro_del_bien) {
		this.tasa_seguro_del_bien = tasa_seguro_del_bien;
	}

	public double getImporte_asegurado() {
		return importe_asegurado;
	}

	public void setImporte_asegurado(double importe_asegurado) {
		this.importe_asegurado = importe_asegurado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Cuota> getCuotas() {
		return cuotas;
	}

	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}

	private void addCuota(Cuota cuota) {
		cuotas.add(cuota);
		cuota.setPrestamo(this);
	}
	
	/* No aplica para este software.
	private void removeCuota(Cuota cuota) {
		cuotas.remove(cuota);
		cuota.setPrestamo(null);
	}
	*/
	
	private String generarCodigoPrestamo() {
        String s = "";
		double d;
		for (int i = 1; i <= 16; i++) {
            d = Math.random() * 10;
            s = s + ((int)d);
            if (i % 4 == 0 && i != 16) {
                s = s + "-";
            }
        }
		return s;
	}
	
	private double calcularTED() {
		return ((Math.pow((1 + tea), (1/360d))) - 1);
	}
	
	private double calcularTEM(int dias_del_mes, double ted) {
		return ((Math.pow((1+ted), dias_del_mes)) - 1);
	}
	
	private double calcularInteresMensual(double saldo, double tem) {
		return (saldo * tem);
	}
	
	private double calcularSeguroDesgravamenMensual(double saldo, int dias_del_mes) {
		return (saldo * (tdes * ((dias_del_mes)/30d)));
	}
	
	private double calcularSeguroDelBienMensual() {
		return (importe_asegurado * (tasa_seguro_del_bien / 12d));
	}
	
	private double calcularCuotaAjustada(double saldo, int numero_cuotas, double tem) {
		return (saldo * (tem/(1-Math.pow(1+tem,-numero_cuotas))));
	}
    
	private double calcularAmortizacion(double cuotaAjustada, double interesMensual, double seguroDesgravamenMensual) {
		return (cuotaAjustada - interesMensual - seguroDesgravamenMensual);
	}
	
	private double calcularCuotaTotal(double cuotaAjustada, double seguroDelBienMensual) {
		return (cuotaAjustada + costo_envio_a_casa + seguroDelBienMensual);
	}
	
	private double calcularPIP(double cuotaFija) {
		return interes_moratorio * cuotaFija;
	}
	
	public String establecerConstantes() {
		if(tipo_prestamo==EnumTipoPrestamo.Efectivo) {
			tipo_de_seguro_desgravamen = null;
			tipo_del_bien = null;
			tipo_seguro_del_bien = null;
			tasa_seguro_del_bien = 0;
			cuotaInicial = 0;
			importe_asegurado = 0;
			tea = 0.24;
			tdes = 0.00075;
			costo_envio_a_casa = 10;
			interes_moratorio = 0.055;
		}else if(tipo_prestamo==EnumTipoPrestamo.Hipotecario) {
			costo_envio_a_casa = 0;
			interes_moratorio = 0.10;
			tea = 0.1352;
			numero_cuotas *=12; //12 meses del año
			tipo_del_bien = EnumTipoBien.Inmueble;
			monto_a_financiar = importe_asegurado - (importe_asegurado * (cuotaInicial/100d));
			if(tipo_de_seguro_desgravamen==EnumTipoSeguroDesgravamen.Individual) {
				tdes = 0.00028;
			}else if(tipo_de_seguro_desgravamen==EnumTipoSeguroDesgravamen.Mancomunado){
				tdes = 0.00052;
			}else {
				return "No ha seleccionado el Tipo de Seguro Desgravamen válido.";
			}
			if(tipo_seguro_del_bien==EnumTipoSeguroBien.Interno) {
				tasa_seguro_del_bien = 0.003;
			}else if(tipo_seguro_del_bien==EnumTipoSeguroBien.Endosado) {
				tasa_seguro_del_bien = 0.000;
			}else {
				return "No ha seleccionado el Tipo de Tasa de Seguro de Bien válido.";
			}
		}else if(tipo_prestamo==EnumTipoPrestamo.Vehicular) {
			tipo_seguro_del_bien = null;
			tasa_seguro_del_bien = 0.0467;
			interes_moratorio = 0.08;
			tea = 0.1299;
			costo_envio_a_casa = 10;
			tipo_del_bien = EnumTipoBien.Vehiculo;
			monto_a_financiar = importe_asegurado - (importe_asegurado * (cuotaInicial/100d));
			if(tipo_de_seguro_desgravamen==EnumTipoSeguroDesgravamen.Individual) {
				tdes = 0.000375;
			}else if(tipo_de_seguro_desgravamen==EnumTipoSeguroDesgravamen.Mancomunado){
				tdes = 0.00075;
			}else {
				return "No ha seleccionado el Tipo de Seguro Desgravamen válido.";
			}
		}else {
			return "No ha seleccionado el Tipo de Préstamo Válido.";
		}
		return null;
	}
	
	public List<Integer> obtenerPlazosPrestamos() {
		if(tipo_prestamo==EnumTipoPrestamo.Efectivo) {
			return Arrays.asList(6,12,24,36,48,60); //meses
		}else if(tipo_prestamo==EnumTipoPrestamo.Hipotecario) {
			return Arrays.asList(10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30); //meses
		}else if(tipo_prestamo==EnumTipoPrestamo.Vehicular) {
			return Arrays.asList(12,24,36,48,60); //años
		}else {
			return Arrays.asList(); //Vacío.
		}
	}
	
	public List<Integer> obtenerCuotasInicial(){
		if(tipo_prestamo==EnumTipoPrestamo.Vehicular) {
			return Arrays.asList(20,25,30,35,40); //porcentajes %
		}else {
			return Arrays.asList(); //Vacío.
		}
	}
	
	public void generarCronograma() {
		LocalDate fechaLocal = LocalDate.now();
		LocalDate fechaLocalSiguiente = fechaLocal.plusMonths(1);
		dia_de_pago = fechaLocal.getDayOfMonth();
		fecha_desembolso = java.sql.Date.valueOf(fechaLocal);
		codigo = generarCodigoPrestamo();
		double saldo = monto_a_financiar; 
		double totalAmortizaciones=0;
		int dias_del_mes = 30; //Días del período 't'
		double ted = calcularTED();
		double tem = calcularTEM(dias_del_mes, ted);
		double tp = tem + tdes;
		double seguroDelBienMensual = calcularSeguroDelBienMensual();
		for (int i = 0, j=numero_cuotas; i < numero_cuotas; i++) {
			double interesMensual, seguroDesgravamenMensual, cuotaAjustada, amortizacion, cuotaFija, pip;
			Cuota cuota = new Cuota();
			cuota.setSaldo(saldo);
			cuota.setNumero_cuota(i+1);
			cuota.setFecha_vencimiento(java.sql.Date.valueOf(fechaLocalSiguiente));
			interesMensual = calcularInteresMensual(saldo, tem);
			seguroDesgravamenMensual = calcularSeguroDesgravamenMensual(saldo, dias_del_mes);
			cuotaAjustada = calcularCuotaAjustada(saldo, j, tp);
			amortizacion = calcularAmortizacion(cuotaAjustada, interesMensual, seguroDesgravamenMensual);
			cuotaFija = calcularCuotaTotal(cuotaAjustada,seguroDelBienMensual);
			pip = calcularPIP(cuotaFija);
			cuota.setAmortizacion(amortizacion);
			cuota.setInteres_mensual(interesMensual);
			cuota.setSeguro_desgravamen_mensual(seguroDesgravamenMensual);
			cuota.setCuota_fija(cuotaFija);
			cuota.setSeguro_del_bien(seguroDelBienMensual);
			cuota.setPip(pip<50d ? 50d : pip); //Si el PIP resultante es menor a 50 soles, se utilizará este monto en su lugar.
			addCuota(cuota);
			saldo= saldo - amortizacion;
			fechaLocal = fechaLocalSiguiente;
			fechaLocalSiguiente = fechaLocal.plusMonths(1);
			j--; 
			totalAmortizaciones+=amortizacion;
		}
		System.out.println("Total Amortización:" + totalAmortizaciones);
		System.out.println(monto_a_financiar==totalAmortizaciones ? "Suma de Amortizaciones y Monto a Financiar, Son Iguales" : "Suma de Amortizaciones y Monto a Financiar, No son iguales :'v ");
	}

	public boolean comprobarPrestamoCompletamentePagado() {
		int cantidadCuotasPagadas = 0;
		for (Cuota cuota : cuotas) {
			if(cuota.getPago()!=null) {
				cantidadCuotasPagadas++;
			}
		}
		return numero_cuotas==cantidadCuotasPagadas;
	}

	private int calcularEdadCliente(Date fechaNacimiento) {
        return (int) java.time.temporal.ChronoUnit.YEARS.between(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(fechaNacimiento)), LocalDate.now());
	}
	
	public String verificarCliente() {
		int edadCliente = calcularEdadCliente(cliente.getPersona().getFecha_nacimiento());
		int edad_minima, edad_maxima;
		int ingreso_minimo;
		if(tipo_prestamo==EnumTipoPrestamo.Efectivo && cliente.getTipo_cliente()==EnumTipoCliente.Independiente) {
			edad_minima = 25;
			edad_maxima = 72;
			ingreso_minimo = 1500;
		} else if(tipo_prestamo==EnumTipoPrestamo.Efectivo && cliente.getTipo_cliente()==EnumTipoCliente.Dependiente) {
			edad_minima = 21;
			edad_maxima = 72;
			ingreso_minimo = 1000;
		}
		else if(tipo_prestamo==EnumTipoPrestamo.Hipotecario) {
			edad_minima = 25;
			edad_maxima = 71;
			ingreso_minimo = 2000;
		}
		else if(tipo_prestamo==EnumTipoPrestamo.Vehicular) {
			edad_minima = 24;
			edad_maxima = 72;
			ingreso_minimo = 1500;
		}
		else {
			return "No se ha podido completar la operación de validación del Cliente.";
		}
		if(edadCliente<edad_minima || edadCliente>edad_maxima) {
			return "Cliente: "+ cliente.getPersona().getNombres() + " " + cliente.getPersona().getApellidos() + ", no tiene la edad necesaria para obtener el préstamo.";
		}
		if(cliente.getIngresos_netos_mensuales()<ingreso_minimo) {
			return "Los Ingresos Netos Mensuales del Cliente: " + cliente.getPersona().getNombres() + " " + cliente.getPersona().getApellidos() + ", no cumplen con los requisitos necesarios para obtener el préstamo.";
		}
		return null;
	}
	
	public String sonMontosCorrectos() { //En Soles (S/.)
		double valor_minimo;
		double valor_maximo;
		if(tipo_prestamo==EnumTipoPrestamo.Efectivo) {
			valor_minimo = 3000;
			valor_maximo = 100000;
			if(!(monto_a_financiar>=valor_minimo && monto_a_financiar<=valor_maximo)) {
				return "Los montos a financiar son desde " + valor_minimo + " soles - hasta " + valor_maximo + " soles.";
			}
		}else if(tipo_prestamo==EnumTipoPrestamo.Hipotecario) {
			valor_minimo = 50000;
			valor_maximo = 5000000;
			if(cuotaInicial<10 || cuotaInicial>50) {
				return "La Cuota Inicial solo es desde 10% - hasta 50%";
			}
			if(!(importe_asegurado>=valor_minimo &&importe_asegurado<=valor_maximo)) {
				return "El valor del Inmueble es desde " + valor_minimo + " soles - hasta " + valor_maximo + " soles.";
			}
		}
		else if(tipo_prestamo==EnumTipoPrestamo.Vehicular) {
			valor_minimo = 15000;
			valor_maximo = 150000;
			if(!(importe_asegurado>=valor_minimo &&importe_asegurado<=valor_maximo)) {
				return "El valor del Vehiculo es desde " + valor_minimo + " soles - hasta " + valor_maximo + " soles.";
			}
		}else {
			return "No se ha podido completar la operación de validación de los Montos de Dinero Ingresados.";
		}
		return null;
	}
	
}