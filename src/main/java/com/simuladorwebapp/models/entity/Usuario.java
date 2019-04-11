package com.simuladorwebapp.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message="El Nombre de Usuario no debe ser Nulo.")
	@NotEmpty(message="El Nombre de Usuario no debe estar Vacío.")
	@Size(min = 5, message="El Nombre de Usuario por lo menos debe de ser de 5 caracteres.")
	@Column(unique=true, length=30, nullable=false)
	private String nombre_usuario;
	
	@NotNull(message="La Contraseña no debe ser Nula.")
	@NotEmpty(message="La Contraseña no debe estar Vacía.")
	@Size(min = 5, message="La Contraseña por lo menos debe de ser de 5 caracteres.")
	@Column(length=60, nullable=false)
	private String clave;
	
	@Column(nullable=false)
	private boolean habilitado;
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private Persona persona;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name="FK_R_USUARIO_ID"), nullable=false)
	private List<Rol> roles = new ArrayList<>();
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Prestamo> prestamos = new ArrayList<>();
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pago> pagos = new ArrayList<>();
	
	public Usuario() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	
	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		if (persona == null) {
            if (this.persona != null) {
                this.persona.setUsuario(null);
            }
        }
        else {
            persona.setUsuario(this);
        }
		this.persona = persona;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}
	
	public void addRol(Rol rol) {
		roles.add(rol);
		rol.setUsuario(this);
	}
	
	public void removeRol(Rol rol) {
		roles.remove(rol);
		rol.setUsuario(null);
	}

	public void addPrestamo(Prestamo prestamo) {
		prestamos.add(prestamo);
		prestamo.setUsuario(this);
	}
	
	public void removePrestamo(Prestamo prestamo) {
		prestamos.remove(prestamo);
		prestamo.setUsuario(null);
	}
	
	public void addPago(Pago pago) {
		pagos.add(pago);
		pago.setUsuario(this);
	}
	
	public void removePago(Pago pago) {
		pagos.remove(pago);
		pago.setUsuario(null);
	}
	
}