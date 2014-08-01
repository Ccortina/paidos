package com.carloscortina.demo;

import com.carloscortina.demo.service.AppointmentStatusService;
import com.carloscortina.demo.service.UserService;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
        @Autowired
        private UserService userService;
        @Autowired
        private AppointmentStatusService appointmentStatusService;
        
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
        * @param locale
        * @param model
        * @return 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("doctors",userService.getUserByRole(2));
                model.addAttribute("appointmentStatus",appointmentStatusService.getAll("AppointmentStatus"));
		return "home";
	}
	
}
