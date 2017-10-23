package com.hiyoo.dao;


import java.sql.*;
import javax.sql.DataSource;
import com.alibaba.druid.pool.*;
import com.hiyoo.dao.DBConfig;

public final class DBConnect {
    private DataSource DS=null;
    private static DBConfig dbconfig=DBConfig.getInstance();
	
	public DBConnect(){
		initDS();
	}
	
	public void initDS(){
		DruidDataSource ds=new DruidDataSource();
		ds.setDriverClassName(dbconfig.getDriver());
		ds.setUsername(dbconfig.getUsername());
		ds.setPassword(dbconfig.getPassword());
		ds.setUrl(dbconfig.getUrl());
		ds.setInitialSize(dbconfig.getInitialsize());
		ds.setMaxActive(dbconfig.getMaxActive());
		ds.setMinIdle(dbconfig.getMinIdle());
       
        ds.setMaxWait(dbconfig.getMaxWait());
        
        ds.setPoolPreparedStatements(dbconfig.isPoolPreparedStatements());
        ds.setMaxOpenPreparedStatements(dbconfig.getMaxOpenPreparedStatements());
        ds.setTestOnBorrow(dbconfig.isTestOnBorrow());
        ds.setTestOnReturn(dbconfig.isTestOnReturn());
        ds.setTestWhileIdle(dbconfig.isTestWhileIdle());
        
        ds.setTimeBetweenEvictionRunsMillis(dbconfig.getTimeBetweenEvictionRunsMillis());
        ds.setMinEvictableIdleTimeMillis(dbconfig.getMinEvictableIdleTimeMillis());
        
        ds.setRemoveAbandoned(dbconfig.isRemoveAbandoned());
        ds.setRemoveAbandonedTimeout(dbconfig.getRemoveAbandonedTimeout());

        DS=ds;
	}
	
	
	public Connection getConnection(){
		Connection conn=null;
		try{
				conn=DS.getConnection();
			}catch(Exception e){
				e.printStackTrace();
		}
		return conn;
	}
	
	public String toString(){
		return DS.toString();
	}

}
