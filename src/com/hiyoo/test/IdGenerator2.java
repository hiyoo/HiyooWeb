package com.hiyoo.test;

import com.hiyoo.service.impl.IdManager;

//此文件为数据库表ID自增测试模拟多线程操作程序, 每次访问单独启动一个线程来获取maxid
public class IdGenerator2 extends Thread{
	private IdManager manager;
	private String tablename;
	private long step;
	
	public IdGenerator2(IdManager manager,String tablename,long step) {
		this.manager=manager;
		this.tablename=tablename;
		this.step=step;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				
			}
			manager=new IdManager();
			
			//IdManager manager=IdManager.getInstatnce();
			System.out.println(Thread.currentThread().getName()+":"+manager.getMaxId(tablename, step)+":"+manager.hashCode());
		}	
	}
}
