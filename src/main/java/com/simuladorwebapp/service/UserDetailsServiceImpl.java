package com.simuladorwebapp.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.simuladorwebapp.models.dao.IUsuarioDao;
import com.simuladorwebapp.models.entity.Rol;
import com.simuladorwebapp.models.entity.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		if(usuario==null) {
			logger.error("Error login: No existe el usuario '" + username + "'");
			throw new UsernameNotFoundException("Usuario " + username + " no existe en el sistema!");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Rol rol: usuario.getRoles()) {
			logger.info("Rol: ".concat(rol.getNombre().name()));
			authorities.add(new SimpleGrantedAuthority(rol.getNombre().name()));
		}
        if(authorities.isEmpty()) {
        	logger.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
        	throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }
		return new User(usuario.getNombre_usuario(), usuario.getClave(), usuario.isHabilitado(), true, true, true, authorities);
	}
	
}
