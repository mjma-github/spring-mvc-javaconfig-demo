package com.mj.springdemo.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//class-level request mapping to avoid mapping ambiguity with other controllers
//note: comment this annotation to see the error
@RequestMapping("/helloWorld")
public class HelloWorldController {
	// method-level request mapping for displaying the form
	@GetMapping("/showForm")
	public String showForm() {
		return "show-form";
	}

	// method-level request mapping for displaying view with data from form
	@PostMapping("/processForm")
	public String processForm() {
		return "process-form";
	}

	// method-level request mapping for displaying view with data from form using model data
	@PostMapping("/processForm2")
	public String processFormUsingModel(HttpServletRequest request, Model model) {
		String message = "Hello from processForm2 " + request.getParameter("studentName").toUpperCase() + "!";
		model.addAttribute("message", message);
		return "process-form";
	}

	// method-level request mapping for displaying view with data from request param using model
	// data
	@PostMapping("/processForm3")
	public String processFormUsingRequestParamAndModel(@RequestParam("studentName") String studentName, Model model) {
		String message = "Hello from processForm3 " + studentName.toUpperCase() + "!";
		model.addAttribute("message", message);
		return "process-form";
	}
}
