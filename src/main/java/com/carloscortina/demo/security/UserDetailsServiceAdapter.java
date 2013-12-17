package com.carloscortina.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.UserService;

@Service("userDetailsService")
public class UserDetailsServiceAdapter implements UserDetailsService{

	@Autowired UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		User user = userService.getUserByUsername(username);
		
		if( user == null){
			throw new UsernameNotFoundException("No such user: " + username);
		}else if (user.getRole() == null){
			throw new UsernameNotFoundException("User " + username + "has no authorities");
		}
		
		UserDetailsAdapter newUser = new UserDetailsAdapter(user); 
		
		return newUser;
	}

}
