package com.simuladorwebapp.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="persona")
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="El DNI no debe ser Nulo.")
	@NotEmpty(message="El DNI no debe estar Vacío.")
	@Size(min = 8, max = 8, message="El DNI es de 8 números.")
	@Pattern(regexp="([0-9]{8})", message="Ingrese un DNI Válido.")
	@Column(unique = true)
	private String dni;
	
	@NotNull(message="Los Nombres no deben ser Nulos.")
	@NotEmpty(message="Los Nombres no deben estar Vacíos.")
	private String nombres;

	@NotNull(message="Los Apellidos no deben ser Nulos.")
	@NotEmpty(message="Los Apellidos no deben estar Vacíos.")
	private String apellidos;
	
	@NotNull(message="La Fecha de Nacimiento no debe ser Nulo.")
	@Past(message="La Fecha de Nacimiento debe ser Pasado a la Fecha de Hoy.")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date fecha_nacimiento;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = true, foreignKey = @ForeignKey(name="FK_USUARIO_ID"))
	private Usuario usuario;
	
	@OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private Cliente cliente;
	
	public Persona() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		if (cliente == null) {
            if (this.cliente != null) {
                this.cliente.setPersona(null);
            }
        }
        else {
            cliente.setPersona(this);
        }
		this.cliente = cliente;
	}
	
}
