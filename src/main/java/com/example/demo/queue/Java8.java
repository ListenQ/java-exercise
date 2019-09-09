package com.example.demo.queue;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * java8的异步多线程实现demo
 * Java8<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年9月9日-下午9:18:18 <BR>
 * @version 1.0.0
 * 
 */
public class Java8 {
	
	public static void main(String[] args) {
		/*Shop shop = new Java8.Shop("zhangqi");
		long start = System.currentTimeMillis();
		Future<Double> priceAsync = shop.getPriceAsync("my name");
		long end = System.currentTimeMillis() - start;
		System.out.println("调用接口花的时间:"+(end)+"毫秒");
		
		doSomethingElse();
		try {
			priceAsync.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		long end2 = System.currentTimeMillis() - start;
		System.out.println("返回价格消耗时间:"+end2+"毫秒");*/
		
		
		//并行操作 Streams 和CompletableFutures 比较 
		//	1. 如果有大量计算的操作而没有I/O 操作（包括连接互联网），那么使用异步的 Streams 可以得到最好的性能。
		//	2. 相反如果有很多io操作， 使用CompletableFutures可以得到更好的编弹性。
		long start = System.currentTimeMillis();
		System.out.println(findPrice("java8实战"));
		long end = System.currentTimeMillis() - start;
		System.out.println("总消耗时间:" + end+"毫秒");
	}
	
	public static void doSomethingElse() {
		System.out.println("做其他的事情...");
	}
	
	
	public static List<String> findPrice(String product){
		List<Shop> shops = Arrays.asList(new Shop("listenq"),
				new Shop("张琦"),
				new Shop("可爱多"),
				new Shop("维达"));
		return shops.parallelStream().map(shop ->
			String.format("%s 的价格是 %.2f",shop.getName(),shop.getPrice(product))
		).collect(Collectors.toList());
	}
	
	
	
	
	static class Shop{
		private String name;
		private Random random = new Random();
		
		public Shop(String name) {
			this.name = name;
		}

		public double getPrice(String product) {
			return calculatePrice(product);
		}
		
		//模拟调用方法执行延时
		public static void delay() {
	        try {
	            Thread.sleep(1000L);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
		
		//模拟调用获取价格的服务
		private double calculatePrice(String product) {
			delay();
			return random.nextDouble() * product.charAt(0)+ product.charAt(1);
		}
		
		
		public Future<Double> getPriceAsync(String product){
			CompletableFuture<Double> future= new CompletableFuture<>();
			new Thread(()->{
				double calculatePrice = calculatePrice(product);
				future.complete(calculatePrice);
			}).start() ;
			
			return future;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Random getRandom() {
			return random;
		}

		public void setRandom(Random random) {
			this.random = random;
		}
		
	}
	
	

	
}
