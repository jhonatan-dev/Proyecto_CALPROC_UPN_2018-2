package com.simuladorwebapp.controllers;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@GetMapping(value = {"", "/"})
	public String irHome(HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) {
			logger.info("Utilizando forma estática SecurityContextHolder.getContext().getAuthentication(); Usuario autenticado, tu username es: ".concat(auth.getName()));
		}
		session.setAttribute("imagen_usuario", "usuario.png");
		return "index";
	}
	
}