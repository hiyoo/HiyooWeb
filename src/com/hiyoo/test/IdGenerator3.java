package com.hiyoo.test;

import com.hiyoo.service.impl.IdManager;

//���ļ�Ϊ���ݿ��ID��������ģ����̲߳�������, ʹ���޶��������߳�����ȡmaxid
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
