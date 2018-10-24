package com.mj.springdemo.mvc.util.constants;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * Properties are retrieved via PropertiesFactoryBean bean
 */
@Component
public class PropertiesFactoryBeanConstantsImpl implements ApplicationOptionsConstants {

	// order in property file not retained
	@Value("#{countryOptions}")
	private Map<String, String> countryOptions;

	@Value("#{languageOptions}")
	private Map<String, String> languageOptions;

	@Value("#{osOptions}")
	private Map<String, String> osOptions;
	
	public Map<String, String> getCountryOptions() {
		return countryOptions;
	}

	public Map<String, String> getLanguageOptions() {
		return languageOptions;
	}

	@Override
	public Map<String, String> getOsOptions() {
		return osOptions;
	}
}
