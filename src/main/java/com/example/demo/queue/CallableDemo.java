package com.example.demo.queue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * 也还是属于java5
 * @author Administrator
 *
 */
public class CallableDemo {

	private static ExecutorService excute = Executors.newFixedThreadPool(5);
	

	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		ExecutorService executor = Executors.newFixedThreadPool(2);
//		Future<String> futrue= executor.submit(new TT());
//		while (true) {
//			//表示等待完成
//			if(futrue.isDone()){
//				executor.shutdown();
//				break;
//			}
//		}
//		// futrue.get() 也还是阻塞当前线程
//		System.out.println(futrue.get());
		Future<String> str = excute.submit(new TT());
		System.out.println(str.get());
	}
	
	 private static class TT implements Callable<String>{

		@Override
		public String call() throws Exception {
			String ss=String.format("当前线程%s-时间是%s\n", Thread.currentThread().getName(),System.currentTimeMillis());
			return ss;
		}
	}
	 
	   
	 
	  
	 
}
