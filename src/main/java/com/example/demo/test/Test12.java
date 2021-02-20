package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

public class Test12 {
	
	private static int k = 1;
	
	public static void main(String[] args) {
		String s = "zsaf%s:nume= %s";
//		test(s);
//		test2(s);
		
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			String num =RandomUtil.randomNumbers(6);
			list.add(num);
//			System.out.println(num);
		}
		Long a = 20L;
		Long b= 5L;
		
		ExecutorService executor = ExecutorBuilder.create()
			    .setCorePoolSize(50)
			    .setMaxPoolSize(50)
			    .setWorkQueue(new LinkedBlockingQueue<>(100))
			    .build();
		
//		list.parallelStream().forEach(l ->{
			
//			executor.execute(new Runnable() {
//				
//				@Override
//				public void run() {
//					int i = 1,j=2;
//					System.out.println(++i);
//					System.out.println(a-b);
//				}
//			});
//		});
//		executor.shutdownNow();
		
		
		
		
	}
	
	private static void test(String s) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			s = String.format(s, "张三",123);
		}
		System.out.println((System.currentTimeMillis()-start));
		System.out.println(s);
	}
	
	private static void test2(String s) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			s = StrUtil.format(s, "张三",132);
		}
		System.out.println((System.currentTimeMillis()-start));
		System.out.println(s);
	}
	
	
	
}
