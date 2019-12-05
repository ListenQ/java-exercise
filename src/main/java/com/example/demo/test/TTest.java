package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class TTest {

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		List<String> urls=new ArrayList<>();
		for(int i = 0;i<1000;i++) {
			urls.add(i+"http://www.baidu.com");
		}
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(6);
		ForkJoinTask<List<String>> result = forkJoinPool.submit(new UrlsTask(urls));//异步开始执行任务同时返回这个任务
		forkJoinPool.execute(new UrlsTask(urls));//会异步执行这个任务但是没有任何返回
		forkJoinPool.invoke(new UrlsTask(urls));//会异步开始执行任务，直接返回一个结果
		List<String> rsp = result.get();
		System.out.println("结果："+rsp);
		forkJoinPool.shutdown();
	}
}
