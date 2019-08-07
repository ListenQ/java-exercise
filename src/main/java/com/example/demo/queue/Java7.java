package com.example.demo.queue;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Java7 {
	
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		System.out.println(ForkJoinPool.commonPool().getParallelism());
		System.out.println("当前cpu执行数量:"+Runtime.getRuntime().availableProcessors());
		ForkJoinPool forkPool = new ForkJoinPool();
		
		
		
		forkPool.invoke(new RecursiveAction() {
			@Override
			protected void compute() {
				System.out.printf("当前线程%s-时间是%s\n", Thread.currentThread().getName(),System.currentTimeMillis());
			}
		});
		
		forkPool.shutdown();
	}
	
	
	
	
	

}
