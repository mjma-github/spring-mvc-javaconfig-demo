package com.mj.springdemo.mvc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity(debug = true)	//enable spring security debug
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	public enum Roles { USER, ADMIN };
	
	@Autowired
	@Qualifier("securityDataSource")
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//configureInMemoryAuthentication(auth);
		
		//Use JDBC authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authConfigurer = http
				.authorizeRequests();

		setLoginConfig(authConfigurer);
		setRoleBasedRestrictions(authConfigurer);
		setExceptionHandling(authConfigurer);
	}
	
	//User security management service provided by Spring
	@Bean
	public UserDetailsManager userDetailsManager() {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(securityDataSource);
		
		return jdbcUserDetailsManager; 
	}	
	
	// add users for in-memory authentication
	private void configureInMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		UserBuilder userBuilder = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication()
			.withUser(userBuilder.username("mj").password("test123").roles(Roles.USER.toString()))
			.withUser(userBuilder.username("hanabi").password("test123").roles(Roles.ADMIN.toString()));
	}	

	//log-in config
	private void setLoginConfig(
			ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authConfigurer) throws Exception {
		
		authConfigurer
			.antMatchers("/resources/**").permitAll()		//permit all to access static resources
			//.anyRequest().authenticated()					//every request requires the user to be authenticated, disable this when using role-based restrictions
			.and()
			.formLogin()
				.loginPage("/login")						//custom login request mapping
				.loginProcessingUrl("/authenticateUser") 	//login authentication handled by spring
				.permitAll()								//login page accessible to all
			.and()
			.logout()
				.permitAll(); 								// enable logout, uses /logout request mapping
	}

	//role-based restrictions
	private void setRoleBasedRestrictions(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authConfigurer) throws Exception {
		String[] adminPages = {
				"/endpoints",
				"/beans",
				"/testDbConnection"
		};
		
		String[] userPages = {
				"/", 
				"/showForm",
				"/customer/**",
				"/helloWorld/**",
				"/student/**",
				};
		
		//admin pages
		for(String adminPage : adminPages) {
			authConfigurer.antMatchers(adminPage).hasRole(Roles.ADMIN.toString());
		}
		
		//user pages
		for(String userPage : userPages) {
			//authConfigurer.antMatchers(userPage).hasAnyRole(Roles.USER.toString(), Roles.ADMIN.toString());
			authConfigurer.antMatchers(userPage).hasAnyRole(Roles.USER.toString(), Roles.ADMIN.toString());
		}
	}
	
	private void setExceptionHandling(
			ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authConfigurer) throws Exception {
		authConfigurer
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	}
}
