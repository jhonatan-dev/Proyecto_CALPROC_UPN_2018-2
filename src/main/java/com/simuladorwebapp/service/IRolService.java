package com.simuladorwebapp.service;

import java.util.List;
import com.simuladorwebapp.models.entity.Rol;

public interface IRolService {

	public List<Rol> finAll();
	public Rol findById(Long id);
	public Rol save(Rol rol);
	public void deleteById(Long id);
	
}
