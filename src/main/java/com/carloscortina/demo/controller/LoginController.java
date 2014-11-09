package com.carloscortina.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
        
        @RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model,
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
 
		if (error != null) {
			model.addAttribute("error", "Nombre de usuario o contraseña invalido!");
		}
 
		if (logout != null) {
			model.addAttribute("msg", "La sesion se ha cerrado correctamente");
		}
 
		return "Login/loginForm";
	}
}
