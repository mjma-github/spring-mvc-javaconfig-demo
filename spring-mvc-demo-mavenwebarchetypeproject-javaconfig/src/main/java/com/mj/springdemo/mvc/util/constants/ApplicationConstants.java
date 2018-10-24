package com.mj.springdemo.mvc.util.constants;

public class ApplicationConstants {
	//Application Data Source
	public static final String JDBC_URL = "jdbc.url";
	public static final String JDBC_USERNAME = "jdbc.user";
	public static final String JDBC_PASSWORD = "jdbc.password";
	public static final String JDBC_DRIVER = "jdbc.driverClassName";
	
	public static final String C3PO_INIT_POOLSIZE = "initialPoolSize";
	public static final String C3PO_MIN_POOLSIZE = "minPoolSize";
	public static final String C3PO_MAX_POOLSIZE = "maxPoolSize";
	public static final String C3PO_INIT_MAX_IDLE_TIME = "maxIdleTime";
	
	public static final String HIBERNATE_DIALECT = "hibernate.dialect";
	public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	public static final String HIBERNATE_HBM2DLL_AUTO = "hibernate.hbm2ddl.auto";
	
	//Security Data Source
	public static final String SECURITY_JDBC_URL = "security.jdbc.url";
	public static final String SECURITY_JDBC_DRIVER = "security.jdbc.driver";
	public static final String SECURITY_JDBC_USERNAME = "security.jdbc.user";
	public static final String SECURITY_JDBC_PASSWORD = "security.jdbc.password";
	
	public static final String SECURITY_C3PO_INIT_POOLSIZE = "security.connection.pool.initialPoolSize";
	public static final String SECURITY_C3PO_MIN_POOLSIZE = "security.connection.pool.minPoolSize";
	public static final String SECURITY_C3PO_MAX_POOLSIZE = "security.connection.pool.maxPoolSize";
	public static final String SECURITY_C3PO_INIT_MAX_IDLE_TIME = "security.connection.pool.maxIdleTime";
}