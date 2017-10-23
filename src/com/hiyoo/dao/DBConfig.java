package com.hiyoo.dao;

import java.io.InputStream;
import java.util.Properties;

import com.hiyoo.tools.FileProcessor;
import com.hiyoo.tools.KeyProcessor;

public final class DBConfig {
	
	/*
	 * dbtype 数据库类型
	 * ORACLE         Oracle数据库 
	 * SQLSERVER      SqlServer数据库
	 * MYSQL          Mysql数据库
	 */
	private String dbType;   
	private String server;
	private String port;
	private String database;
	private String username;
	private String password;	
	
	private String url;
	private String driver;
	
	private Integer initialsize;
	private Integer maxActive;
	private Integer minIdle;
	private Integer maxWait;
	
	private boolean poolPreparedStatements;
	private Integer maxOpenPreparedStatements;
	
	private boolean testOnBorrow;
	private boolean testOnReturn;
	private boolean testWhileIdle;
	private Integer timeBetweenEvictionRunsMillis;
	private Integer minEvictableIdleTimeMillis;
	
	private boolean removeAbandoned;
	private Integer removeAbandonedTimeout;
	private boolean logAbandoned;

	private static final String PropertiesName="/dbInfo.properties";
	private static final DBConfig INSTANCE=new DBConfig();
	private Properties pp;
	//private FileInputStream fis;
	
	
	public static final DBConfig getInstance(){
		
		return  INSTANCE;
		
	}
	
	public DBConfig(){
		
		initDbConfig();   //初始化相关参数
		
		
	}
	
	
	public void initDbConfig(){
		InputStream fis=null;
		try{
		
			pp=new Properties();
			fis=this.getClass().getResourceAsStream(PropertiesName);
			pp.load(fis);
			dbType=pp.getProperty("dbType");
			server=pp.getProperty("server");
			port=pp.getProperty("port");
			database=pp.getProperty("database");
			username=pp.getProperty("username");
			password=KeyProcessor.deciphering(pp.getProperty("password"));	
			initialsize=Integer.valueOf(pp.getProperty("initialSize"));
			maxActive=Integer.valueOf(pp.getProperty("maxActive"));
			minIdle=Integer.valueOf(pp.getProperty("minIdle"));
			maxWait=Integer.valueOf(pp.getProperty("maxWait"));
			
			poolPreparedStatements=Boolean.getBoolean(pp.getProperty("poolPreparedStatements"));
			maxOpenPreparedStatements=Integer.valueOf(pp.getProperty("maxOpenPreparedStatements"));
			testOnBorrow=Boolean.getBoolean(pp.getProperty("testOnBorrow"));
			testOnReturn=Boolean.getBoolean(pp.getProperty("testOnReturn"));
			testWhileIdle=Boolean.getBoolean(pp.getProperty("testWhileIdle"));
			timeBetweenEvictionRunsMillis=Integer.valueOf(pp.getProperty("timeBetweenEvictionRunsMillis"));
			minEvictableIdleTimeMillis=Integer.valueOf(pp.getProperty("minEvictableIdleTimeMillis"));
			removeAbandoned=Boolean.getBoolean(pp.getProperty("removeAbandoned"));
			removeAbandonedTimeout=Integer.valueOf(pp.getProperty("removeAbandonedTimeout"));
			logAbandoned=Boolean.getBoolean(pp.getProperty("logAbandoned"));
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				fis.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			fis=null;
		}	
		
		switch(dbType)
		{   
		     case  "ORACLE"   :  
		    	 {
		    		 driver="oracle.jdbc.driver.OracleDriver";
		    		 url="jdbc:oracle:thin:@"+server+":"+port+":"+database; 
		    		 break;
		    	 }
		     case  "SQLSERVER":  
		    	 {
		    		 driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		    		 url="jdbc:sqlserver://"+server+":"+port+";DatabaseName="+database;
                     break;
		    	 }
		     case  "MYSQL"    :  
		    	 {
		    		 driver="com.mysql.jdbc.Driver"; 
		    		 url="jdbc:mysql://"+server+":"+port+"/"+database;
		    		 break;
		    	 }
		
		}	
		
	}
		
	public Properties getPp() {
		return pp;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		FileProcessor.writeProperties(PropertiesName, "dbType", dbType);
		this.dbType = dbType;
	}

	public String getUrl() {
		return url;

	}

	public String getDriver() {
		return driver;				
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		FileProcessor.writeProperties(PropertiesName, "username", username);
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		
		String pwd=KeyProcessor.encryption(password);
		FileProcessor.writeProperties(PropertiesName, "password", pwd);
		this.password = pwd;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		FileProcessor.writeProperties(PropertiesName, "server", server);
		this.server = server;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		FileProcessor.writeProperties(PropertiesName, "port", port);
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		FileProcessor.writeProperties(PropertiesName, "database", database);
		this.database = database;
	}

	public Integer getInitialsize() {
		return initialsize;
	}

	public Integer getMaxActive() {
		return maxActive;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public Integer getMaxWait() {
		return maxWait;
	}

	public boolean isPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	public Integer getMaxOpenPreparedStatements() {
		return maxOpenPreparedStatements;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	public Integer getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public Integer getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public boolean isRemoveAbandoned() {
		return removeAbandoned;
	}

	public Integer getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}

	public boolean isLogAbandoned() {
		return logAbandoned;
	}
	
    
}
