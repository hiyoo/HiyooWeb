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
            return oid+"任务返回的内容"; 
    } 
}

public class TestCallable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub

		 ExecutorService pool = Executors.newFixedThreadPool(2); 
         //创建两个有返回值的任务 
         Callable<Object> c1 = new MyCallable("A"); 
         Callable<Object> c2 = new MyCallable("B"); 
         //执行任务并获取Future对象 
         Future<Object> f1 = pool.submit(c1); 
         Future<Object> f2 = pool.submit(c2); 
         //从Future对象上获取任务的返回值，并输出到控制台 
         System.out.println(">>>"+f1.get().toString()); 
         System.out.println(">>>"+f2.get().toString()); 
         //关闭线程池 
         pool.shutdown(); 
		
	}

}
