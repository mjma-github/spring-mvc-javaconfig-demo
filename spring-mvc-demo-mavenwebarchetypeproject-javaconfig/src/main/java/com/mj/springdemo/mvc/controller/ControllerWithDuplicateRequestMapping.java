package com.mj.springdemo.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerWithDuplicateRequestMapping {

	// Note: has dupplicate request mapping in HelloWorldController to show the use
	// of class-level request mapping
	@GetMapping("/showForm")
	public String showForm() {
		return "show-form";
	}
}
