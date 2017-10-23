package com.hiyoo.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hiyoo.dao.DBConnect;

public final class DBUtil {	

	private static final DBConnect dbconn=new DBConnect();
	
	public Connection getConnection(){
		return dbconn.getConnection();
	}
	
	public static final boolean executeUpdate(String sql,String[] parameters){
		Connection ct=dbconn.getConnection();
		PreparedStatement ps=null;
		boolean isSuccess=true;
		
		try{
			    ps=ct.prepareStatement(sql);
			    
			    if(parameters!=null){
			    	for(int i=0;i<parameters.length;i++){
			    		ps.setString(i+1, parameters[i]);
			    	}
			    }
			    
			    ps.executeUpdate();
		}catch(Exception e){
			isSuccess=false;
			System.out.println("数据库更新错误，SQL语句："+sql+","+parameters);
			throw new RuntimeException(e.getMessage());
		}finally{
            close(ps,ct);
		}
		return isSuccess;
	}
		
	public static final boolean executeUpdate(String[] sql,String[][] parameters){
		
		Connection ct=dbconn.getConnection();
		PreparedStatement ps=null;
		boolean isSuccess=true;
		String errorSql="";
		
		try{
			    ct.setAutoCommit(false);	    
			
			    for(int i=0,len=sql.length;i<len;i++){
			    	
			    	if(sql[i]!=null){
			    		errorSql=sql[i];
			    		ps=ct.prepareStatement(sql[i]);	
			    		//if(parameters!=null) {
			    			//if(parameters[i]!=null) {
			    			for(int j=0;j<parameters[i].length;j++){
			    				//System.out.println("p["+i+"]["+j+"]:"+parameters[i][j]);
			    				ps.setString(j+1, parameters[i][j]);  
			    				
			    			}//}
			    		//}
			    		
			    		ps.executeUpdate();
			
			       }
			    }
			    
			    ct.commit();
			    ct.setAutoCommit(true);	
			    close(ps,ct);
		}catch(Exception e){
			try {
					ct.rollback();
					ct.setAutoCommit(true);
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
			isSuccess=false;
			System.out.println("数据库更新错误，SQL语句："+errorSql);
			throw new RuntimeException(e.getMessage());
		}finally{
            close(ps,ct);
		}
		return isSuccess;
    }
	
	public static final boolean executeProcedure(String sql,CallableParameter parameters[]) {
		Connection ct=dbconn.getConnection();
		CallableStatement cs=null;
		boolean isSuccess=true;
		try {
			cs=ct.prepareCall(sql);
			
			if(parameters!=null){
		    	for(int i=0;i<parameters.length;i++){
		    		if(parameters[i].OutPut) {
		    			String paramName=parameters[i].Name;
		    			if(paramName==null||"".equals(paramName)) {
		    				cs.registerOutParameter(1, parameters[i].Type);
		    			}else {
		    				cs.registerOutParameter(parameters[i].Name,parameters[i].Type);
		    			}
		    		}else {
		    			cs.setObject(parameters[i].Name,parameters[i].Value);
		    		}

		    	}
		    }
			
			cs.executeUpdate();
			
			cs.close();
			ct.close();
		}catch(Exception e) {
			isSuccess=false;
			System.out.println("执行存储过程错误："+sql);
			throw new RuntimeException(e.getMessage());
		}finally {
			close(cs,ct);
		}
		return isSuccess;
	}
	public static final long executeQuery(String sql,String[] parameters){
		Connection ct=dbconn.getConnection();
		PreparedStatement ps=null;
        ResultSet rs = null;
        long result=0;
        
        try {     	
        	ps = ct.prepareStatement(sql);	
        	if(parameters!=null){
		    	for(int i=0;i<parameters.length;i++){
		    		ps.setString(i+1, parameters[i]);
		    	}
		    }
            rs = ps.executeQuery();
            if(rs.next()) {
            	result=rs.getInt(1);
            }else {
            	result=0;
            	throw new RuntimeException("没有查询到数据");
            }
        } catch (Exception e) {
        	System.out.println("数据查询错误："+sql);
        	throw new RuntimeException(e.getMessage());
        } finally {
            close(rs, ps, ct);
        }
		return result;
	}
	
	public static final <T> List<T> executeQuery(String sql,String[] parameters,Class<T> cls){
		Connection ct=dbconn.getConnection();
		PreparedStatement ps=null;
        ResultSet rs = null;
        //List<T> list = null;
        List<T> list = new ArrayList<T>();
        T obj = null;
        try {
        	
        	ps = ct.prepareStatement(sql);
        	
        	if(parameters!=null){
		    	for(int i=0;i<parameters.length;i++){
		    		ps.setString(i+1, parameters[i]);
		    	}
		    }
            rs = ps.executeQuery();
            while (rs.next()) {
   
                obj = executeResultSet(cls, rs);
                list.add(obj);
            }
        } catch (Exception e) {
        	System.out.println("数据查询错误："+sql);
        	throw new RuntimeException(e.getMessage());
        } finally {
            close(rs, ps, ct);
        }
        return list;
		
	}
	
	
	 /**
     * 将一条记录转成一个对象
     * 
     * @param cls
     *            泛型类型
     * @param rs
     *            ResultSet对象
     * @return 泛型类型对象
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    private static final <T> T executeResultSet(Class<T> cls, ResultSet rs)
            /*throws InstantiationException, IllegalAccessException, SQLException*/ {
    	T obj = null;
    	ResultSetMetaData rsm = null;
    	Field[] fields = null;
    	Field field = null;
        String fieldName = null;
        String columnName = null;
        Object value = null;
        
    	try {
    		obj = cls.newInstance();
    		rsm = rs.getMetaData();
    		fields = cls.getDeclaredFields();    
        	
        	for (int i =0,len=fields.length ; i < len; i++) {
        		field = fields[i];
        		fieldName = field.getName();
        		for (int j = 1,columnCount=rsm.getColumnCount(); j <= columnCount; j++) {
        			columnName = rsm.getColumnName(j);
        			if (fieldName.equalsIgnoreCase(columnName)) {
        				//System.out.println("Field:"+columnName+",Type:"+rsm.getColumnType(j));
        				value=getValueByType(rs,rsm.getColumnType(j),columnName);
        				field.setAccessible(true);
        				field.set(obj, value);
        				break;
        			}
        		}
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException("数据库记录生成对象到List时报错:"+e.getMessage());
        }
        return obj;
    }
	
    /*** 
     *  
     * @param rs 查询出来的结果集 
     * @param type SQL type from java.sql.Types 
     * @param name 数据库记录所对应的字段名称 
     * @return 返回一条记录的一个列值(按照字段类型值返回） 
     * @throws SQLException 
     */  
    private static Object getValueByType(ResultSet rs, int type, String name) throws SQLException{  
        switch(type){  
            case Types.NUMERIC:  
                    return rs.getLong(name); 
            case Types.CHAR:
            		return rs.getString(name);
            case Types.VARCHAR:  
                return rs.getString(name);  
            case Types.DATE:  
                return rs.getDate(name);  
            case Types.TIMESTAMP:  
            	Timestamp timestamp=rs.getTimestamp(name);
            	return timestamp;
            case Types.INTEGER:  
                return rs.getInt(name);  
            case Types.DOUBLE:  
                return rs.getDouble(name);  
            case Types.FLOAT:  
                return rs.getFloat(name);  
            case Types.BIGINT:  
                return rs.getLong(name); 
            default:  
                return rs.getObject(name);  
        }  
    }  
    
    private static void getProcReturnParam(CallableStatement cs,
            CallableParameter... params) throws SQLException {
        for (CallableParameter param : params) {
            if (param.OutPut) {
                String paramName = param.Name;
                if (paramName == null || paramName.equals("")) {
                   param.Value = cs.getObject(1);// 返回类型参数值
               } else {
                    param.Value = cs.getObject(paramName);// 输出类型参数值
                }
            }
        }
    }
    
	public static void close(ResultSet rs,Statement ps,Connection ct){
		
		if(rs!=null){
			try{
				rs.close();
			}catch(Exception e){
				
			}
			
		}
		
		if(ps!=null){
			try{
				ps.close();
			}catch(SQLException e){
				e.printStackTrace();
				
			}
			ps=null;
		}
		
		if(ct!=null){
			try{
				ct.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			ct=null;
		}
		
	}
	
	public static void close(Statement ps,Connection ct){		
		if(ps!=null){
			try{
				ps.close();
			}catch(SQLException e){
				e.printStackTrace();
				
			}
			ps=null;
		}
		
		if(ct!=null){
			try{
				ct.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			ct=null;
		}
		
	}

	public static void close(CallableStatement cs,Connection ct){		
		if(cs!=null){
			try{
				cs.close();
			}catch(SQLException e){
				e.printStackTrace();
				
			}
			cs=null;
		}
		
		if(ct!=null){
			try{
				ct.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			ct=null;
		}
		
	}
}
