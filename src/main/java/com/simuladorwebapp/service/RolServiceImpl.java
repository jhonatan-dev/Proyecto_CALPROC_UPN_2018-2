package com.simuladorwebapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.simuladorwebapp.models.dao.IRolDao;
import com.simuladorwebapp.models.entity.Rol;

@Service
public class RolServiceImpl implements IRolService {

	@Autowired
	private IRolDao rolDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Rol> finAll() {
		return (List<Rol>) rolDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Rol findById(Long id) {
		return rolDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Rol save(Rol rol) {
		return rolDao.save(rol);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		rolDao.deleteById(id);
	}

}
