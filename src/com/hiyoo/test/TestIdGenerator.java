package com.hiyoo.test;

import com.hiyoo.service.impl.IdManager;

//此文件为数据库表ID自增测试主程序，多个线程共用1个IdManager实例来获取MaxId
public class TestIdGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IdGenerator svr1=new IdGenerator("test",1);
		svr1.start();
		svr1.setName("test-svr1");
		IdGenerator svr2=new IdGenerator("test",1);
		svr2.start();
		svr2.setName("test-svr2");
		
		IdGenerator svr3=new IdGenerator("test2",1);
		svr3.start();
		svr3.setName("test2-svr3");
		
		IdGenerator svr4=new IdGenerator("test2",1);
		svr4.start();
		svr4.setName("test2-svr4");
		
		
		System.out.println(IdManager.getInstatnce().getMaxId("test2", 1));
		
	}

}
