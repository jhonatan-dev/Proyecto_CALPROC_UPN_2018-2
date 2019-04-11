package com.simuladorwebapp.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.simuladorwebapp.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	@Query(value="SELECT * FROM usuario WHERE nombre_usuario=:nombre_usuario", nativeQuery = true)
	public Usuario findByUsername(@Param("nombre_usuario") String nombre_usuario);
	
}
