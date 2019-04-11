package com.simuladorwebapp.service;

import java.util.List;
import com.simuladorwebapp.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> finAll();
	public Cliente findById(Long id);
	public Cliente save(Cliente cliente);
	public void deleteById(Long id);
	
}
