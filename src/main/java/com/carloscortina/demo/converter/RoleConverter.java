package com.carloscortina.demo.converter;

import java.beans.PropertyEditorSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carloscortina.demo.model.Role;
import com.carloscortina.demo.service.RoleService;

public class RoleConverter extends PropertyEditorSupport {

		private final Logger logger = LoggerFactory.getLogger(RoleConverter.class);
		private RoleService roleService;
		
		public RoleConverter (){
		}
		
		public RoleConverter (RoleService roleService){
			this.roleService = roleService;
		}
		
		@Override
		public void setAsText(String role) {
			
			logger.info(role);
			Role auxiliar = new Role();
			auxiliar= roleService.getRole(Integer.parseInt(role));
			setValue(auxiliar);
		}

}
