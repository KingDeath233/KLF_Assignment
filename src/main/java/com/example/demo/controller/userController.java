package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Contact;
import com.example.demo.entities.SecureUsers;
import com.example.demo.services.MailService;
import com.example.demo.services.UsersService;

@Controller
public class userController {
	
	@Autowired
	UsersService userService;
	
	@Autowired
	MailService mailService;
	
	@RequestMapping("/user/change_password")
	public String changePassword(Model theModel) {
		SecureUsers user = new SecureUsers();
		user.setUsername("tmp");
		theModel.addAttribute("user", user);
		return "user/change_password";
	}
	
	@PostMapping("/user/process-password")
	public String registerUser(@Valid @ModelAttribute("user") SecureUsers user, BindingResult result, Model model) {
		if(result.hasErrors()){
			return "user/change_password";
		}
		else {
			userService.save(user.getPassword());
			return "redirect:/success";
		}
	}
	
	@RequestMapping("/user/contact_me")
	public String contactMe(Model theModel) {
		theModel.addAttribute("contact", new Contact());
		return "user/contact_me";
	}
	
	@PostMapping("/user/process-contact")
	public String processContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult result, Model model) {
		if(result.hasErrors()){
			return "user/contact_me";
		}
		else {
			mailService.send(contact.getFromAddress(),"yechao98@gmail.com",contact.getSubject()+" from: "+contact.getFromAddress(),contact.getContent());
			return "redirect:/success";
		}
	}
	
	@RequestMapping("/user/generate_report")
	public String generateReport() {
		return "user/generate_report";
	}
}
