package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@RequestMapping("admin/empty")
	public String empty() {
		return "admin/empty";
	}
	
	@RequestMapping("user/test")
	public String t1() {
		return "user/test";
	}
	
	@RequestMapping("admin/test")
	public String t2() {
		return "admin/test";
	}
	
	@RequestMapping("user/main")
	public String t3() {
		return "user/main";
	}
	
	@RequestMapping("admin/main")
	public String t4() {
		return "admin/main";
	}
}
