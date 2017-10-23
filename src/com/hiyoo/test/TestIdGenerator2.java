package com.hiyoo.test;

//此文件为数据库表ID自增测试主程序，每次访问都创建1个线程来访问。
public class TestIdGenerator2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IdGenerator2 svr1=new IdGenerator2(null,"test",1);
		svr1.start();
		svr1.setName("test-svr1");
		IdGenerator2 svr2=new IdGenerator2(null,"test",1);
		svr2.start();
		svr2.setName("test-svr2");
		
		IdGenerator2 svr3=new IdGenerator2(null,"test2",1);
		svr3.start();
		svr3.setName("test2-svr3");
		
		IdGenerator2 svr4=new IdGenerator2(null,"test2",1);
		svr4.start();
		svr4.setName("test2-svr4");
		
		
		//System.out.println(IdManager.getInstatnce().getMaxId("test2", 1));
		
	}

}
