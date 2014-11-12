package com.carloscortina.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.PermissionsService;
import com.carloscortina.demo.service.UserService;

@Service("userDetailsService")
public class UserDetailsServiceAdapter implements UserDetailsService{

	@Autowired 
        private UserService userService;
        @Autowired
        private PermissionsService permissionsService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		User user = userService.getUserByUsername(username);
		
		if( user == null){
			throw new UsernameNotFoundException("No such user: " + username);
		}else if (user.getIdRole() == null){
			throw new UsernameNotFoundException("User " + username + "has no authorities");
		}
		
                UserDetailsAdapter newUser;
                if(user.getIdRole().getRole().equals("Asistente")){
                   newUser = new UserDetailsAdapter(user,permissionsService.getAll(""));
                }else{
                   newUser = new UserDetailsAdapter(user);
                }
                
		return newUser;
	}

}
