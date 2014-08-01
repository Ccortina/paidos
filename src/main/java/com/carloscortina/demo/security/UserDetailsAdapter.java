package com.carloscortina.demo.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.carloscortina.demo.model.Staffmember;
import com.carloscortina.demo.model.User;


@SuppressWarnings("serial")
public class UserDetailsAdapter implements UserDetails{

	private User user;
	
	public UserDetailsAdapter(User user)
	{
		this.user = user;
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
		System.out.println("--------------------------------------------------"+role);
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

}
