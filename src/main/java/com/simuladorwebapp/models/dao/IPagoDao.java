package com.simuladorwebapp.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.simuladorwebapp.models.entity.Pago;

public interface IPagoDao  extends CrudRepository<Pago, Long> {

}