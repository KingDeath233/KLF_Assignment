package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.example.demo.entities.SecureUsers;


@Service
public class SystemUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDetailsManager jdbcUserDetailsManager;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public String encode(String original) {
		return encoder.encode(original);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = jdbcUserDetailsManager.loadUserByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("No user found with username: "+username);
		}
		return user;
	}
	
	public boolean registerUser(SecureUsers newUser, String type) {
		if(jdbcUserDetailsManager.userExists(newUser.getUsername())) {
			return false;
		}
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority("ROLE_"+type.toUpperCase()));
		newUser.setAuthorities(auth);
		String oldpass = newUser.getPassword();
		newUser.setPassword(encoder.encode(oldpass));
		jdbcUserDetailsManager.createUser(newUser);
		return true;
	}
	
	public String getUsername() {
		String username="";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		return username;
	}

}

