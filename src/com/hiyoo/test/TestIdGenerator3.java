package com.hiyoo.test;

import com.hiyoo.service.impl.IdManager;

//此文件为数据库表ID自增测试主程序， 采取固定的现场数量来获取MaxId
public class TestIdGenerator3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IdManager idMgr1=new IdManager();
		IdGenerator3 svr1=new IdGenerator3(idMgr1,"test",1);
		svr1.start();
		svr1.setName("test-idMgr1-svr1");
		IdGenerator3 svr2=new IdGenerator3(idMgr1,"test",1);
		svr2.start();
		svr2.setName("test-idMgr1-svr2");
		
		IdManager idMgr2=new IdManager();
		IdGenerator3 svr3=new IdGenerator3(idMgr2,"test2",1);
		svr3.start();
		svr3.setName("test2-idMgr2-svr3");
		
		IdGenerator3 svr4=new IdGenerator3(idMgr2,"test2",1);
		svr4.start();
		svr4.setName("test2-idMgr2-svr4");
		
		IdGenerator3 svr5=new IdGenerator3(idMgr2,"test",1);
		svr5.start();
		svr5.setName("test-idMgr2-svr5");
		
		
		//System.out.println(IdManager.getInstatnce().getMaxId("test2", 1));
		
	}

}
