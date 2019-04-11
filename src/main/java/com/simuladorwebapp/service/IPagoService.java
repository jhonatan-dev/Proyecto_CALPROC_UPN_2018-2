package com.simuladorwebapp.service;

import java.util.List;
import com.simuladorwebapp.models.entity.Pago;

public interface IPagoService {

	public List<Pago> finAll();
	public Pago findById(Long id);
	public Pago save(Pago pago);
	public void deleteById(Long id);

}
