package com.simuladorwebapp.service;

import java.util.List;
import com.simuladorwebapp.models.entity.Cuota;

public interface ICuotaService {

	public List<Cuota> finAll();
	public Cuota findById(Long id);
	public Cuota save(Cuota cuota);
	public void deleteById(Long id);
	
}
