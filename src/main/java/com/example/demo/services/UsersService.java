package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UsersDAO;
import com.example.demo.entities.Users;

@Service
public class UsersService {

	@Autowired
	UsersDAO repo;
	
	@Autowired
	SystemUserDetailsService SUDS;
	
	public void save(String newPass) {
		String newPassword = SUDS.encode(newPass);
		Users tmp = new Users(SUDS.getUsername(),newPassword);
		repo.save(tmp);
	}
}
