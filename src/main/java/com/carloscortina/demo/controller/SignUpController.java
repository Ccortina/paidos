package com.carloscortina.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carloscortina.demo.model.Staffmember;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.StaffMemberService;
import com.carloscortina.demo.service.UserService;

@Controller
@RequestMapping(value="/signup")
public class SignUpController 
{
	//@Autowired
	//private RoleService roleService;
	
	@Autowired
	private StaffMemberService staffMemberService;
	
	@Autowired
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.setAllowedFields(
				new String[]
				{
					"username","password","confirmPassword","email",
					"firstName","lastName","phone","cellPhone",
					"professionalNumber","role"
				});
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

	
}
