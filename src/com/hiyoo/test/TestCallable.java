package com.hiyoo.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable implements Callable<Object>{ 
    private String oid; 

    MyCallable(String oid) { 
            this.oid = oid; 
    } 

    @Override 
    public Object call() throws Exception { 
            return oid+"���񷵻ص�����"; 
    } 
}

public class TestCallable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub

		 ExecutorService pool = Executors.newFixedThreadPool(2); 
         //���������з���ֵ������ 
         Callable<Object> c1 = new MyCallable("A"); 
         Callable<Object> c2 = new MyCallable("B"); 
         //ִ�����񲢻�ȡFuture���� 
         Future<Object> f1 = pool.submit(c1); 
         Future<Object> f2 = pool.submit(c2); 
         //��Future�����ϻ�ȡ����ķ���ֵ�������������̨ 
         System.out.println(">>>"+f1.get().toString()); 
         System.out.println(">>>"+f2.get().toString()); 
         //�ر��̳߳� 
         pool.shutdown(); 
		
	}

}
