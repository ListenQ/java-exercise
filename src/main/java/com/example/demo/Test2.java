package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.demo.mult.thread.countdownlatch.T1;
import com.example.demo.mult.thread.countdownlatch.T2;
import com.example.demo.mult.thread.countdownlatch.T3;
import com.example.demo.mult.thread.countdownlatch.TAbract;
import com.example.demo.mult.thread.countdownlatch.ThreadDemo;

public class Test2 {
	
	private static List<TAbract> t;
	
	public void setT(List<TAbract> t) {
		this.t = t;
	}



	public static void main(String[] args) throws InterruptedException {
//		long number = t();
//		System.out.println("change最大的数是："+number);
		
//		String str= "{messageId=adfasdfasdf,maqTag=TC-HD-OERE,topic=YFDGP-DFS-DG}".replaceAll("\\{|\\}", "");
//		String[] strarr = str.split(",");
//		Map<String, Object> map = new HashMap<>();
//		for (String string : strarr) {
//			String [] keyarr = string.split("=");
//			map.put(keyarr[0], keyarr[1]);
//		}
//		System.out.println(map.get("messageId"));
		
		Test2 test = new Test2();
		test.setT(Arrays.asList(new T1(),new T2(),new T3()));
		
		ExecutorService executors= Executors.newFixedThreadPool(3);
		for (final TAbract tt : t) {
			executors.execute(new ThreadDemo(tt));
		}
		executors.shutdown();
		
		/*try {
			CountDownLatch c = new CountDownLatch(2); // join
			for (int i=0;i< 2;i++) {
				executors.execute(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(5000l);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName());
						c.countDown();
					}
				});
			}
			c.await(1 , TimeUnit.SECONDS);
			System.out.println(3);
		}catch(Exception e) {}
		finally {
			executors.shutdown();
		}
		System.out.println(4);*/
		
		
	}
	
	
	
	
	private static long t() {
		long num = 0;
		try {
			long [] array = {59,3,4,7};
			 for(int i = 0;i<array.length;i++) {
				 if(i <array.length-1 && array[i] > array[i+1]) {
					 if(array[i] > num) {
						 num = array[i];
					 }
				 }
			 }
			 System.out.println("最大的数："+num);
			 return num;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			num = 726;
		}
		return num;
	}

}
