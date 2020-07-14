package com.example.demo.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.services.UserActivityService;

@Component
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent>{

	@Autowired
	UserActivityService UAS;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {	
		UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
		UAS.save(userDetails.getUsername(),1);
	}

}
