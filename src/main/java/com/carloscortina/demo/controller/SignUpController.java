package com.carloscortina.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carloscortina.demo.model.StaffRegistrationForm;
import com.carloscortina.demo.model.User;

@Controller
@RequestMapping(value="/signup")
public class SignUpController 
{
	 @RequestMapping(value="form", method=RequestMethod.GET)
	 public String getRegistrationForm(Model model) 
	 {
		 	System.out.println("creando forma");
		 	model.addAttribute("form",new StaffRegistrationForm());
	        return "signup/staffRegistrationForm";
	 }
	 
	/* 
	@RequestMapping(value="new", method=RequestMethod.POST)
	public String signup(@Valid StaffRegistrationForm signupForm, BindingResult result, RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors()) 
		{
            return "signup/form";
        }
		
		User newUser = new User();
		newUser.setUsername(signupForm.getUsername());
		newUser.setPassword(signupForm.getPassword());
		newUser.setEmail(signupForm.getEmail());
		return "";
	}*/
	
}
