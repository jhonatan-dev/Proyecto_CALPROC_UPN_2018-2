package com.simuladorwebapp.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.simuladorwebapp.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{

}
