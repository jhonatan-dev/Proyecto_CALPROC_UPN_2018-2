package com.simuladorwebapp.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="rol", uniqueConstraints= {@UniqueConstraint(columnNames= {"usuario_id","nombre"}, name="UQ_USUARIO_ID_Y_NOMBRE_ROL")})
public class Rol implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message="El Rol no debe ser Nulo.")
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	EnumTipoTrabajador nombre;

	Usuario usuario;
	
	public Rol() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumTipoTrabajador getNombre() {
		return nombre;
	}

	public void setNombre(EnumTipoTrabajador nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
