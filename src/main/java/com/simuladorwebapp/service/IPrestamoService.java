package com.simuladorwebapp.service;

import java.util.List;
import com.simuladorwebapp.models.entity.Prestamo;

public interface IPrestamoService {

	public List<Prestamo> finAll();
	public Prestamo findById(Long id);
	public Prestamo findByCodigo(String codigo);
	public Prestamo save(Prestamo prestamo);
	public void deleteById(Long id);
	
}
