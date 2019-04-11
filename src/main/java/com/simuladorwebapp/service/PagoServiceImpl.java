package com.simuladorwebapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.simuladorwebapp.models.dao.IPagoDao;
import com.simuladorwebapp.models.entity.Pago;

@Service
public class PagoServiceImpl implements IPagoService {

	@Autowired
	private IPagoDao pagoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Pago> finAll() {
		return (List<Pago>) pagoDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Pago findById(Long id) {
		return pagoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Pago save(Pago pago) {
		return pagoDao.save(pago);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		pagoDao.deleteById(id);
	}

}
