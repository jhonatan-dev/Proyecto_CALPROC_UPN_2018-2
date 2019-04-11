package com.simuladorwebapp.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Los Ingresos Netos Mensuales no deben ser Nulos.")
	@Positive(message="Los Ingresos Netos Mensuales deben ser mayor a cero.")
	private double ingresos_netos_mensuales;
	
	@NotNull(message="Los Gastos Fijos Mensuales no deben ser Nulos.")
	@Positive(message="Los Gastos Fijos Mensuales deben ser mayor a cero.")
	private double gastos_fijos_mensuales;
	
	@NotNull(message="Tipo de Cliente no debe ser Nulo.")
	@Enumerated(EnumType.STRING)
	private EnumTipoCliente tipo_cliente;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id", nullable = false, foreignKey = @ForeignKey(name="FK_PERSONA_ID"))
	private Persona persona;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Prestamo> prestamos = new ArrayList<>();
	
	public Cliente() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getIngresos_netos_mensuales() {
		return ingresos_netos_mensuales;
	}

	public void setIngresos_netos_mensuales(double ingresos_netos_mensuales) {
		this.ingresos_netos_mensuales = ingresos_netos_mensuales;
	}

	public double getGastos_fijos_mensuales() {
		return gastos_fijos_mensuales;
	}

	public void setGastos_fijos_mensuales(double gastos_fijos_mensuales) {
		this.gastos_fijos_mensuales = gastos_fijos_mensuales;
	}

	public EnumTipoCliente getTipo_cliente() {
		return tipo_cliente;
	}

	public void setTipo_cliente(EnumTipoCliente tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public void addPrestamo(Prestamo prestamo) {
		prestamos.add(prestamo);
		prestamo.setCliente(this);
	}
	
	public void removePrestamo(Prestamo prestamo) {
		prestamos.remove(prestamo);
		prestamo.setCliente(null);
	}
	
}
