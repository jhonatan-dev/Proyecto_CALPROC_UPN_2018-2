package com.simuladorwebapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.simuladorwebapp.models.dao.ICuotaDao;
import com.simuladorwebapp.models.entity.Cuota;

@Service
public class CuotaServiceImpl implements ICuotaService {

	@Autowired
	private ICuotaDao cuotaDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Cuota> finAll() {
		return (List<Cuota>) cuotaDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Cuota findById(Long id) {
		return cuotaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cuota save(Cuota cuota) {
		return cuotaDao.save(cuota);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		cuotaDao.deleteById(id);
	}

}
