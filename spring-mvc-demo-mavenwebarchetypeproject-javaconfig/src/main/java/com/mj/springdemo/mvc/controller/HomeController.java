package com.mj.springdemo.mvc.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mj.springdemo.mvc.util.constants.MessageConstants;

@Controller
public class HomeController extends BaseController {
	private static Map<String, String> modelAttributeMapShowMain;
	
	static {
		modelAttributeMapShowMain =  new HashMap<>();
		modelAttributeMapShowMain.put("welcomeMessage", MessageConstants.MESSAGE_WELCOME);
		modelAttributeMapShowMain.put("requestMapping", MessageConstants.REQUEST_MAPPING);
		modelAttributeMapShowMain.put("helloWorldForm", MessageConstants.HELLO_WORLD_FORM);
		modelAttributeMapShowMain.put("springMvcFormTagsDataBinding", MessageConstants.SPRING_MVC_FORM_TAGS_DATA_BINDING);
		modelAttributeMapShowMain.put("textFieldsModelAttribute", MessageConstants.TEXT_FIELDS_MODEL_ATTRIBUTE);
		modelAttributeMapShowMain.put("studentForm", MessageConstants.STUDENT_FORM);
		modelAttributeMapShowMain.put("formValidation", MessageConstants.FORM_VALIDATION);
		modelAttributeMapShowMain.put("customerForm", MessageConstants.CUSTOMER_FORM);
		modelAttributeMapShowMain.put("crudSample", MessageConstants.CRUD_SAMPLE);
		modelAttributeMapShowMain.put("studentCrud", MessageConstants.STUDENT_CRUD);
		modelAttributeMapShowMain.put("utilities", MessageConstants.UTILITIES);
		modelAttributeMapShowMain.put("showAllRequestMappings", MessageConstants.SHOW_ALL_REQUEST_MAPPINGS);
		modelAttributeMapShowMain.put("showAllBeans", MessageConstants.SHOW_ALL_BEANS);
		modelAttributeMapShowMain.put("testDbConnection", MessageConstants.TEST_DB_CONNECTION);
	}
	
	@GetMapping("/")
	public String showMain(Locale locale, Model model) {
        System.out.println("current Locale="+LocaleContextHolder.getLocale());
		model.addAllAttributes(getMessageValue(locale, modelAttributeMapShowMain));
		return "main-menu";
	}
}
