package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.SecureUsers;
import com.example.demo.services.SystemUserDetailsService;
import com.example.demo.services.UserActivityService;

@Controller
public class securityController {
	
	@Autowired 
	SystemUserDetailsService SUDS;
	
	@Autowired
	UserActivityService UAS;
	
	@RequestMapping("/success")
	public String success() {
		return "success";
	}
	
	@RequestMapping("")
	public String index() {
		UAS.save(SUDS.getUsername(), 2);
		return "system/main";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("newUser", new SecureUsers());
		return "register";
	}
	
	@PostMapping("/process-register")
	public String registerUser(@Valid @ModelAttribute("newUser") SecureUsers newUser, BindingResult result, Model model) {
		if(result.hasErrors()){
			return "register";
		}
		if(!SUDS.registerUser(newUser,"user")) {
			return "redirect:/register?userExists";
		}
		return "redirect:/login?signedup";
	}
	
	@RequestMapping("/access-denied")
	public String accessDenied() {
		return "access_denied";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){
	    	UAS.save(SUDS.getUsername(), 3);
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	
	@GetMapping("error")
	public String error() {
		return "error";
	}
	
}
