package com.simuladorwebapp.service;

import java.util.List;
import com.simuladorwebapp.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> finAll();
	public Usuario findById(Long id);
	public Usuario findByUserName(String nombre_usuario);
	public Usuario save(Usuario usuario);
	public void deleteById(Long id);
	
}
