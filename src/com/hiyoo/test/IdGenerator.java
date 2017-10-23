package com.hiyoo.test;

import com.hiyoo.service.impl.IdManager;

//���ļ�Ϊ���ݿ��ID��������ģ����̲߳������� ����̹߳���1��Id����������ȡMaxId
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
