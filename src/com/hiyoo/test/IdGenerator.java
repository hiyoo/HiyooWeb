package com.hiyoo.test;

import com.hiyoo.service.impl.IdManager;

//此文件为数据库表ID自增测试模拟多线程操作程序， 多个线程共用1个Id生成器来获取MaxId
public class IdGenerator extends Thread{
	private String tablename;
	private long step;
	
	public IdGenerator(String tablename,long step) {
		this.tablename=tablename;
		this.step=step;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				
			}
			IdManager manager=IdManager.getInstatnce();
			System.out.println(Thread.currentThread().getName()+":"+manager.getMaxId(tablename, step)+":"+manager.hashCode());
		}	
	}
}
