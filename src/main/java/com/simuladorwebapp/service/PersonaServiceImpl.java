package com.simuladorwebapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.simuladorwebapp.models.dao.IPersonaDao;
import com.simuladorwebapp.models.entity.Persona;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaDao personaDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Persona> finAll() {
		return (List<Persona>) personaDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Persona findById(Long id) {
		return personaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Persona findByDni(String dni) {
		return personaDao.findByDni(dni);
	}
	
	@Override
	@Transactional
	public Persona save(Persona persona) {
		return personaDao.save(persona);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		personaDao.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public boolean existsById(Long id) {
		return personaDao.existsById(id);
	}
}
