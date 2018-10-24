package com.mj.springdemo.mvc.util.constants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * Properties are retrieved via @PropertySources({ @PropertySource("classpath:countries2.properties") })
 */
@Component
public class PropertySourceConstantsImpl implements ApplicationOptionsConstants {
	// @Value("#{'${student.countrylist}'.split(',')}")
	@Value("${student.countrylist}")
	private final List<String> studentCountryOptionsList = new ArrayList<>();

	public Map<String, String> getCountryOptions() {
		Map<String, String> studentCountryOptionsMap = new LinkedHashMap<>();

		for (String countryOption : studentCountryOptionsList) {
			String[] countryOptionKeyValue = countryOption.split(":");
			studentCountryOptionsMap.put(countryOptionKeyValue[0], countryOptionKeyValue[1]);
		}

		return studentCountryOptionsMap;
	}

	@Override
	public Map<String, String> getLanguageOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getOsOptions() {
		// TODO Auto-generated method stub
		return null;
	}
}
