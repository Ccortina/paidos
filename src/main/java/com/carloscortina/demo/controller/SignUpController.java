package com.carloscortina.demo.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.carloscortina.demo.model.StaffRegistrationForm;
import com.carloscortina.demo.model.User;

@Controller
@RequestMapping(value="/signup")
public class SignUpController 
{
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.setAllowedFields(
				new String[]
				{
					"username","password","confirmPassword","email",
					"firstName","lastName","phone","cellPhone",
					"professionalNumber"
				});
	}
	
	 @RequestMapping(value="form", method=RequestMethod.GET)
	 public String getRegistrationForm(Model model) 
	 {
		 	System.out.println("creando forma");
		 	model.addAttribute("form",new StaffRegistrationForm());
	        return "signup/staffRegistrationForm";
	 }
	 
	
	@RequestMapping(value="new", method=RequestMethod.POST)
	public String signup(@ModelAttribute("form") @Valid StaffRegistrationForm form, BindingResult result)
	{
		
		
		
		
		
		convertPasswordError(result);
		return (result.hasErrors() ? "signup/staffRegistrationForm" : "signup/RegistrationOk");
	}
	
	private static void convertPasswordError(BindingResult result)
	{
		for (ObjectError error : result.getGlobalErrors())
		{
			String msg = error.getDefaultMessage();
			if ("account.password.mismatch.message".equals(msg))
			{
				if (!result.hasFieldErrors("password"))
				{
					result.rejectValue("password", "error.mismatch");
				}
			}
		}
		
	}
	
	private User toUser(StaffRegistrationForm form)
	{
		User newUser = new User();
		newUser.setUsername(form.getUsername());
		newUser.setPassword(form.getPassword());
		newUser.setEmail(form.getEmail());
		return null;
		
	}
	
}
