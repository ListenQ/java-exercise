package com.example.demo.queue;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Java5 {

	
	public static void main(String[] args) {
		/**
		 * Executor 执行器，ThreadPoolExecutor是它的一种实现
		 */
		Executor executor = Executors.newFixedThreadPool(1);
		executor.execute(new Runnable() {
			@Override
			public void run() {
				System.out.printf("当前线程%s-时间是%s\n", Thread.currentThread().getName(),System.currentTimeMillis());
			}
		});
		
		if(executor instanceof ExecutorService){
			ExecutorService executorService = ExecutorService.class.cast(executor);
			executorService.shutdown();
		}
	}
}
