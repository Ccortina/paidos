package com.carloscortina.demo.security;

import com.carloscortina.demo.model.Permissions;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.carloscortina.demo.model.Staffmember;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.PermissionsService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


@SuppressWarnings("serial")
public class UserDetailsAdapter implements UserDetails{

	private User user;
        private List<Permissions> permissionsList;
	
	public UserDetailsAdapter(User user)
	{
		this.user = user;
	}
        
        public UserDetailsAdapter(User user,List<Permissions> permissionsList)
	{
		this.user = user;
                this.permissionsList = permissionsList;
	}
	
	public User getUser() { return (user); }
	public int getId() { return (user.getIdUser()); }
	public String getPaidosUsername() { return user.getUsername(); }
	public String getEmail() { return (user.getEmail()); }
	public Staffmember getStaffMember() { return ( user.getIdStaffMember() ); }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		String role = user.getIdRole().getRole();
		if(role.equals("Asistente")){
                    
                    for(Permissions p:permissionsList){
                        authorities.addAll(getNamedPermissions(p));
                    }
                    
                }
                
		authorities.add(new SimpleGrantedAuthority(role));
                
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
        
        private List<GrantedAuthority> getNamedPermissions(Permissions permission){
            List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
            
            switch( permission.getValue() ){
                case 1:
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_1"));
                    break;
                case 2:         
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_2"));
                    break;
                case 3:
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_1"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_2"));
                    break;
                case 4:
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_4"));
                    break;
                case 5:
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_1"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_4"));
                    break;
                case 6:
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_2"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_4"));
                    break;
                case 7:
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_1"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_2"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_4"));
                    break;    
                case 8:
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_8"));
                    break;
                case 9:
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_1"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_8"));
                    break;
                case 10:
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_2"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_8"));
                    break;
                case 11:
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_1"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_2"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_8"));
                    break;
                case 12:
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_4"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_8"));
                    break;
                case 13:
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"-"+permission.getPermission()+"_1"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_4"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_8"));
                    break;
                case 14:
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_2"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_4"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_8"));
                    break;
                case 15:
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_1"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_2"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_4"));
                    result.add(new SimpleGrantedAuthority(permission.getModule()+"_"+permission.getPermission()+"_8"));
                    break;       
            }
            
            return result; 
        }

}
