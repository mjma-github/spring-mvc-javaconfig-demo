package com.mj.springdemo.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mj.springdemo.mvc.model.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// InitBinder is a controller requestmapping method preprocessor
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		// All string input whitespace will be trimmed/converted to null
		// Used to disallow whitespace form input, in combination with Customer @NotNull
		// Alternatively, we could just add @NotBlank to fields that must not be
		// comprised of all whitespace
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@GetMapping("/showForm")
	public String showForm(Model model) {
		model.addAttribute("customer1", new Customer());
		return "customer-form";
	}

	@PostMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("customer1") Customer customer,
			// Note: BindingResult parameter must be immediately next to it's form object
			// model attribute
			// If multiple model attribute parameters are used, each must have its own
			// BindingResult right after
			BindingResult bindingResult) {
		
		System.out.println(customer);
		System.out.println("bindingResult=" + bindingResult);

		if (bindingResult.hasErrors()) {
			return "customer-form";
		}

		return "customer-confirmation";
	}
}
