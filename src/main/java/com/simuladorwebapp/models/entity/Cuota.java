package com.simuladorwebapp.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="cuota")
public class Cuota implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private int numero_cuota;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date fecha_vencimiento;
	
	@Column(nullable=false)
	private double saldo;
	
	@Column(nullable=false)
	private double amortizacion;
	
	@Column(nullable=false)
	private double interes_mensual;

	@Column(nullable=false)
	private double seguro_desgravamen_mensual;
	
	@Column(nullable=false)
	private double cuota_fija;
	
	@Column(nullable=false)
	private double seguro_del_bien;
	
	@Column(nullable=false)
	private double pip;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prestamo_id", nullable=false, foreignKey = @ForeignKey(name="FK_C_PRESTAMO_ID"))
	private Prestamo prestamo;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pago_id", nullable = true, foreignKey = @ForeignKey(name="FK_C_PAGO_ID"))
	private Pago pago;
	
	public Cuota() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero_cuota() {
		return numero_cuota;
	}

	public void setNumero_cuota(int numero_cuota) {
		this.numero_cuota = numero_cuota;
	}

	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getAmortizacion() {
		return amortizacion;
	}

	public void setAmortizacion(double amortizacion) {
		this.amortizacion = amortizacion;
	}

	public double getInteres_mensual() {
		return interes_mensual;
	}

	public void setInteres_mensual(double interes_mensual) {
		this.interes_mensual = interes_mensual;
	}

	public double getSeguro_desgravamen_mensual() {
		return seguro_desgravamen_mensual;
	}

	public void setSeguro_desgravamen_mensual(double seguro_desgravamen_mensual) {
		this.seguro_desgravamen_mensual = seguro_desgravamen_mensual;
	}

	public double getCuota_fija() {
		return cuota_fija;
	}

	public void setCuota_fija(double cuota_fija) {
		this.cuota_fija = cuota_fija;
	}

	public double getSeguro_del_bien() {
		return seguro_del_bien;
	}

	public void setSeguro_del_bien(double seguro_del_bien) {
		this.seguro_del_bien = seguro_del_bien;
	}

	public double getPip() {
		return pip;
	}

	public void setPip(double pip) {
		this.pip = pip;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}
	
}
