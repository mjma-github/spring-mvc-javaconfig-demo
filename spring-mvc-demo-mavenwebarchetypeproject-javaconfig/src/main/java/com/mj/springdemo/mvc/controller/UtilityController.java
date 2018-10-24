/*
 * Gets all requests mappings for this mvc application
 * https://stackoverflow.com/questions/11082818/spring-mvc-get-all-request-mappings
 */

package com.mj.springdemo.mvc.controller;

import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.JDBC_DRIVER;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.JDBC_PASSWORD;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.JDBC_URL;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.JDBC_USERNAME;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.mj.springdemo.mvc.util.constants.ApplicationOptionsConstants;

@Controller
public class UtilityController {
	private final RequestMappingHandlerMapping handlerMapping;
	private final ApplicationContext appContext;
	private final Map<String, String> beansMap = new HashMap<>();
	private final Environment env;
	private ApplicationOptionsConstants constants;

	@Autowired
	public UtilityController(RequestMappingHandlerMapping handlerMapping, 
			ApplicationContext appContext,
			@Qualifier("propertiesFactoryBeanConstantsImpl") ApplicationOptionsConstants constants,
			Environment env) {
		this.handlerMapping = handlerMapping;
		this.appContext = appContext;
		this.constants = constants;
		this.env = env;
	}
	
	@GetMapping(value = "/endpoints")
	public String endpoints(Model model) {
		model.addAttribute("handlerMethods", this.handlerMapping.getHandlerMethods());
		return "handler-methods";
	}

	@GetMapping(value = "/beans")
	public String beans(Model model) {

		for (String beanName : appContext.getBeanDefinitionNames()) {
			beansMap.put(beanName, appContext.getBean(beanName).getClass().toString());
		}

		model.addAttribute("beansMap", beansMap);
		return "beans";
	}

	@GetMapping(value = "/testDbConnection")
	@ResponseBody
	//@Transactional		//required when using session, else this is encountered: org.hibernate.HibernateException: Could not obtain transaction-synchronized Session for current thread
	public void testDbConnection(HttpServletResponse response) throws IOException, ClassNotFoundException {
		String jdbcUrl = env.getProperty(JDBC_URL);
		String user = env.getProperty(JDBC_USERNAME);
		String pass = env.getProperty(JDBC_PASSWORD);
		String driver = env.getProperty(JDBC_DRIVER);

		PrintWriter out = response.getWriter();
		out.print("Connecting to db... ");
		Class.forName(driver); // old way of registering driver to driver manager, for testing only

		try (Connection conn = DriverManager.getConnection(jdbcUrl, user, pass)) {
			out.println("success!<br>");
			
			//out.print("Getting current session ... ");
			//sessionFactory.getCurrentSession();
			//out.println("success!");
			
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("failed: " + e.getMessage());
		} finally {
			out.close();
		}
	}
}