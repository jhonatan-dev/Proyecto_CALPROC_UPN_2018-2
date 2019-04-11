package com.simuladorwebapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.simuladorwebapp.models.dao.IPrestamoDao;
import com.simuladorwebapp.models.entity.Prestamo;

@Service
public class PrestamoServiceImpl implements IPrestamoService {

	@Autowired
	private IPrestamoDao prestamoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Prestamo> finAll() {
		return (List<Prestamo>) prestamoDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Prestamo findById(Long id) {
		return prestamoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Prestamo findByCodigo(String codigo) {
		return prestamoDao.findByCodigo(codigo);
	}
	
	@Override
	@Transactional
	public Prestamo save(Prestamo prestamo) {
		return prestamoDao.save(prestamo);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		prestamoDao.deleteById(id);
	}
	
}
