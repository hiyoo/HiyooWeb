package com.hiyoo.tools;

import java.io.*;
import java.util.Properties;

public class FileProcessor {
	
	public static void writeProperties(String fileName,String key,String value){
		
		FileInputStream fis=null;
		
		try{
			
			Properties pp=new Properties();
			fis=new FileInputStream(fileName);
			pp.load(fis);
			
			pp.setProperty(key, value);			
			
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
		
	}
	
    public static String readProperties(String fileName,String key){
		
		FileInputStream fis=null;
		String value="";
		
		try{
			
			Properties pp=new Properties();
			fis=new FileInputStream(fileName);
			pp.load(fis);
			
			value=pp.getProperty(key);			
			
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
		
		return value;
				
	}
}
