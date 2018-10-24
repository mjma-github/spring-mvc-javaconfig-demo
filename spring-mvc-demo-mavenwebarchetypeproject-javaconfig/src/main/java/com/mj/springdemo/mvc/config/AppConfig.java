package com.mj.springdemo.mvc.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

//Configuration for common service layer beans

@Configuration
@PropertySource(value = { "classpath:application.properties" })
//@EnableScheduling
//@EnableAspectJAutoProxy
//@EnableCaching
public class AppConfig {
	/************* start: List Constants *************/
	// Country options properties
	@Bean("countryOptions")
	public PropertiesFactoryBean countryOptions() {
		PropertiesFactoryBean properties = new PropertiesFactoryBean();
		properties.setLocation(new ClassPathResource("countries.properties"));
		return properties;
	}

	// Language properties
	@Bean("languageOptions")
	public PropertiesFactoryBean languageOptions() {
		PropertiesFactoryBean properties = new PropertiesFactoryBean();
		properties.setLocation(new ClassPathResource("languages.properties"));
		return properties;
	}

	// Language properties
	@Bean("osOptions")
	public PropertiesFactoryBean osOptions() {
		PropertiesFactoryBean properties = new PropertiesFactoryBean();
		properties.setLocation(new ClassPathResource("os.properties"));
		return properties;
	}
	/************* end: List Constants *************/
	
	// bean to show all request mappings used in UtilityController.endpoints()
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		return new RequestMappingHandlerMapping();
	}

	// // to convert property with list value to a List<>
	// @Bean
	// public ConversionService conversionService() {
	// return new DefaultConversionService();
	// }	
}
