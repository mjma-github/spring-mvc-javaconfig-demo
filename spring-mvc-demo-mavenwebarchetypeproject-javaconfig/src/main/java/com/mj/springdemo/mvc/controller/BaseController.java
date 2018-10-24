package com.mj.springdemo.mvc.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;

import com.mj.springdemo.mvc.util.constants.ApplicationOptionsConstants;

public abstract class BaseController {
	//Validation and application messages
	@Autowired
    MessageSource messageSource;
	
	@Autowired
	@Qualifier("propertiesFactoryBeanConstantsImpl")
	ApplicationOptionsConstants appOptionsConstants;
	
	Map<String, String> getMessageValue(Locale locale, Map<String, String> modelAttributeConstantsMap) {
		Map<String,String> mapping = new HashMap<>(modelAttributeConstantsMap);
		for(String modelAttribute : mapping.keySet()) {
			mapping.put(modelAttribute, messageSource.getMessage(mapping.get(modelAttribute), null, locale));
		}
		
		return mapping;
	}	
}
