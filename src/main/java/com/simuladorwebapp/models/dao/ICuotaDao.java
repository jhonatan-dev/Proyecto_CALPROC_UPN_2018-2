package com.simuladorwebapp.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.simuladorwebapp.models.entity.Cuota;

public interface ICuotaDao  extends CrudRepository<Cuota, Long> {

}
