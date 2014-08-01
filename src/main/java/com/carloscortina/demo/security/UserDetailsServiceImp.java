/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.security;

import com.carloscortina.demo.dao.UserDao;
import com.carloscortina.demo.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos Cortina
 */
public class UserDetailsServiceImp implements UserDetailsService{

    @Autowired
    UserDao dao;
    
    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = dao.getUserByUsername(username);
        if(user != null){
            String password = user.getPassword();
            //Additional information on the security object
            boolean enabled = user.getActive() == 1  ;

            //Populate UserRole
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(user.getIdRole().getRole()));

            //Create Spring security User object
            org.springframework.security.core.userdetails.User securityUser = new
                    org.springframework.security.core.userdetails.User(username, password, authorities);

            return securityUser;
        }else{
            throw  new UsernameNotFoundException("User Not Found!!!");
        }
        
    }
}
