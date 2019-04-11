package com.simuladorwebapp.service;

import java.util.List;
import com.simuladorwebapp.models.entity.Persona;

public interface IPersonaService {

	public List<Persona> finAll();
	public Persona findById(Long id);
	public Persona findByDni(String dni);
	public Persona save(Persona persona);
	public void deleteById(Long id);
	public boolean existsById(Long id);
}
