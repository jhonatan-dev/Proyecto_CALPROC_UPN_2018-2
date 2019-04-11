package com.simuladorwebapp.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.simuladorwebapp.models.entity.Persona;

public interface IPersonaDao  extends CrudRepository<Persona, Long> {

	@Query(value="SELECT * FROM persona WHERE dni=:dni", nativeQuery = true)
	public Persona findByDni(@Param("dni") String dni);
}
