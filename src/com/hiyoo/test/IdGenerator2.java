package com.hiyoo.test;

import com.hiyoo.service.impl.IdManager;

//���ļ�Ϊ���ݿ��ID��������ģ����̲߳�������, ÿ�η��ʵ�������һ���߳�����ȡmaxid
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
