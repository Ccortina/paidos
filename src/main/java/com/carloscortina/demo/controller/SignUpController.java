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

import com.carloscortina.demo.model.Role;
import com.carloscortina.demo.model.Staffmember;
import com.carloscortina.demo.model.StaffRegistrationForm;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.RoleService;
import com.carloscortina.demo.service.StaffMemberService;
import com.carloscortina.demo.service.UserService;

@Controller
@RequestMapping(value="/signup")
public class SignUpController 
{
	@Autowired
	private RoleService roleService;
	
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
		convertPasswordError(result);	//Verify if the passwords match
		if (!result.hasErrors())	// The validation was correct?
		{
			Staffmember staff = toStaffMember(form);
			staffMemberService.createStaffmember(staff);	
			
			Role role = roleService.getRole(Integer.parseInt(form.getRole()));
			
			User user = toUser(form,role,staff);
			
			userService.registerUser(user, result);
			
			return ( "redirect:registrationOk" );
		}
		
		return ("signup/staffRegistrationForm");
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
	
	private User toUser(StaffRegistrationForm form,Role role,Staffmember staff)
	{
		User newUser = new User();
		newUser.setUsername(form.getUsername());
		newUser.setPassword(form.getPassword());
		newUser.setEmail(form.getEmail());
                newUser.setIdRole(role);
                newUser.setIdStaffMember(staff);
		
		return newUser;
		
	}
	
	private Staffmember toStaffMember(StaffRegistrationForm form)
	{
		Staffmember staff = new Staffmember();
                staff.setName(form.getFirstName());
		staff.setLastName(form.getLastName());
		staff.setPhone(form.getPhone());
		staff.setCellPhone(form.getCellPhone());
		staff.setProfessionalNumber(form.getProfessionalNumber());
		return staff;
		
	}
	
	@ModelAttribute("roles")
	private Map<String,String> roles(){
		List<Role> lista = roleService.getRoles();
		Map<String,String> roles = new HashMap<String, String>();
		for (Role role : lista) {
			roles.put(Integer.toString(role.getId()), role.getRole());
		}
		return roles;
	}
	
}
