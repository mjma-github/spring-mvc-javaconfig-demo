package com.mj.springdemo.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	//Custom access denied page for unauthorized access
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}	
}
