package com.mj.springdemo.mvc.config;

import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.C3PO_INIT_MAX_IDLE_TIME;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.C3PO_INIT_POOLSIZE;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.C3PO_MAX_POOLSIZE;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.C3PO_MIN_POOLSIZE;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.HIBERNATE_DIALECT;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.HIBERNATE_HBM2DLL_AUTO;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.HIBERNATE_SHOW_SQL;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.JDBC_DRIVER;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.JDBC_PASSWORD;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.JDBC_URL;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.JDBC_USERNAME;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.SECURITY_C3PO_INIT_MAX_IDLE_TIME;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.SECURITY_C3PO_INIT_POOLSIZE;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.SECURITY_C3PO_MAX_POOLSIZE;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.SECURITY_C3PO_MIN_POOLSIZE;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.SECURITY_JDBC_DRIVER;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.SECURITY_JDBC_PASSWORD;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.SECURITY_JDBC_URL;
import static com.mj.springdemo.mvc.util.constants.ApplicationConstants.SECURITY_JDBC_USERNAME;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*------------------Spring 5/Hibernate integration------------------*/

@Configuration
@EnableTransactionManagement
// @EnableJpaRepositories
@PropertySource("classpath:security-persistence.properties")
public class PersistenceConfig {
	@Autowired
	private Environment env;

	private Logger logger = Logger.getLogger(getClass().getName());

	// Database DataSource/Connection pool
	@Bean("dataSource")
	public DataSource dataSource() throws PropertyVetoException {	
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getProperty(JDBC_DRIVER));
      dataSource.setUrl(env.getProperty(JDBC_URL));
      dataSource.setUsername(env.getProperty(JDBC_USERNAME));
      dataSource.setPassword(env.getProperty(JDBC_PASSWORD));

		// C3PO connection pool properties
		Properties props = new Properties();
		props.setProperty(C3PO_INIT_POOLSIZE, env.getProperty(C3PO_INIT_POOLSIZE));
		props.setProperty(C3PO_MIN_POOLSIZE, env.getProperty(C3PO_MIN_POOLSIZE));
		props.setProperty(C3PO_MAX_POOLSIZE, env.getProperty(C3PO_MAX_POOLSIZE));
		props.setProperty(C3PO_INIT_MAX_IDLE_TIME, env.getProperty(C3PO_INIT_MAX_IDLE_TIME));
		dataSource.setConnectionProperties(props);

		return dataSource;
	}

	// Security DataSource
	@Bean("securityDataSource")
	public DataSource securityDataSource() throws PropertyVetoException {
		logger.info(">>> security.jdbc.url=" + env.getProperty(SECURITY_JDBC_URL));
		logger.info(">>> security.jdbc.user=" + env.getProperty(SECURITY_JDBC_USERNAME));
		
		DriverManagerDataSource securityDataSource = new DriverManagerDataSource();
		securityDataSource.setDriverClassName(env.getProperty(SECURITY_JDBC_DRIVER));
		securityDataSource.setUrl(env.getProperty(SECURITY_JDBC_URL));
		securityDataSource.setUsername(env.getProperty(SECURITY_JDBC_USERNAME));
		securityDataSource.setPassword(env.getProperty(SECURITY_JDBC_PASSWORD));

		// C3PO connection pool properties
		Properties props = new Properties();
		props.setProperty(C3PO_INIT_POOLSIZE, env.getProperty(SECURITY_C3PO_INIT_POOLSIZE));
		props.setProperty(C3PO_MIN_POOLSIZE, env.getProperty(SECURITY_C3PO_MIN_POOLSIZE));
		props.setProperty(C3PO_MAX_POOLSIZE, env.getProperty(SECURITY_C3PO_MAX_POOLSIZE));
		props.setProperty(C3PO_INIT_MAX_IDLE_TIME, env.getProperty(SECURITY_C3PO_INIT_MAX_IDLE_TIME));
		securityDataSource.setConnectionProperties(props);

		return securityDataSource;
	}

	// JPA Entity manager
	// Note: prefer entity manager over hibernate session factory
	@Bean
	public LocalContainerEntityManagerFactoryBean geEntityManagerFactoryBean() throws PropertyVetoException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.mj.springdemo.mvc.model" });
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public JpaTransactionManager geJpaTransactionManager() throws PropertyVetoException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(geEntityManagerFactoryBean().getObject());
		return transactionManager;
	}



	private Properties additionalProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty(HIBERNATE_DIALECT, env.getProperty(HIBERNATE_DIALECT));
		hibernateProperties.setProperty(HIBERNATE_SHOW_SQL, env.getProperty(HIBERNATE_SHOW_SQL));
		hibernateProperties.setProperty(HIBERNATE_HBM2DLL_AUTO, env.getProperty(HIBERNATE_HBM2DLL_AUTO));

		return hibernateProperties;
	}

	private int getIntProperty(String propName) {
		return Integer.parseInt(env.getProperty(propName));
	}

	// user and password not properly set using ComboPooledDataSource.
	// Workaround is to set user and password in url.
	/*
	private String setJdbcUrl(String url, String user, String password) {
		if (url == null || url.length() == 0) {
			return null;
		}

		String jdbcUrl = url + (url.contains("?") ? "&" : "?");
		jdbcUrl += "user=" + user + "&password=" + password;

		return jdbcUrl;
	}
	
	// Hibernate session factory
	 @Bean("sessionFactory")
	 public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException
	 {
	 LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	 sessionFactory.setDataSource(dataSource());
	 sessionFactory.setPackagesToScan("com.mj.springdemo.mvc.model");
	
	 // Hibernate properties
	 sessionFactory.setHibernateProperties(additionalProperties());
	
	 return sessionFactory;
	 }

	// Hibernate transaction manager
	 @Bean("transactionManager")
	 public PlatformTransactionManager transactionManager() throws
	 PropertyVetoException {
	 return new HibernateTransactionManager(sessionFactory().getObject());
	 }
	 */
}
