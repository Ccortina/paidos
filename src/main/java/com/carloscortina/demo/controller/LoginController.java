package com.carloscortina.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@RequestMapping(value="form",method=RequestMethod.GET)
	public String loadLoginForm()
	{
		return "login/loginForm";
	}
	
	public String processLogin(){
		return "";
	}
}
