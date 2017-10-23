package com.hiyoo.test;

import com.hiyoo.service.impl.IdManager;

//此文件为数据库表ID自增测试模拟多线程操作程序, 使用限定的数量线程来获取maxid
public class IdGenerator3 extends Thread{
	private IdManager manager;
	private String tablename;
	private long step;
	
	public IdGenerator3(IdManager manager,String tablename,long step) {
		this.manager=manager;
		this.tablename=tablename;
		this.step=step;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(50);
			}catch(InterruptedException e) {
				
			}
			//manager=new IdManager();
			
			//IdManager manager=IdManager.getInstatnce();
			System.out.println(Thread.currentThread().getName()+":"+manager.getMaxId(tablename, step)+":"+manager.hashCode());
		}	
	}
}
