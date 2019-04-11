package com.simuladorwebapp.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.simuladorwebapp.models.entity.Prestamo;

public interface IPrestamoDao  extends CrudRepository<Prestamo, Long> {
	
	@Query(value="SELECT * FROM prestamo WHERE codigo=:codigo", nativeQuery = true)
	public Prestamo findByCodigo(@Param("codigo") String codigo);
	
}
