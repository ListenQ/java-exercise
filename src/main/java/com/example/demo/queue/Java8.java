package com.example.demo.queue;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
	
	static ExecutorService executor = Executors.newCachedThreadPool();
	
	public static void main(String[] args) {
		//先回顾下jdk5 的异步编程
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future future =  executorService.submit(new Callable<Double>() {
		    @Override
		    public Double call() throws Exception {
		    	System.out.println("做其他的事情");
		        return 12.0;
		    }
		});
		
		
		
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
		System.out.println("parallelStream总消耗时间:" + end+"毫秒");
		
		//CompletableFuture的并行例子
		long start2 = System.currentTimeMillis();
		System.out.println(findPrice2("java8实战"));
		long duration = System.currentTimeMillis() - start2;
		System.out.println("CompletableFuture总消耗时间：" + duration + "毫秒");
		
		//***** 以上比较 parallelStream优于 CompletableFuture*****
		
	}
	
	public static void doSomethingElse() {
		System.out.println("做其他的事情...");
	}
	
	//parallelStream的并行操作
	public static List<String> findPrice(String product){
		List<Shop> shops = Arrays.asList(new Shop("listenq"),
				new Shop("张琦"),
				new Shop("可爱多"),
				new Shop("维达"));
		return shops.parallelStream().map(shop ->
			String.format("%s 的价格是 %.2f",shop.getName(),shop.getPrice(product))
		).collect(Collectors.toList());
	}
	
	
//	CompletableFuture的并行操作
	public static List<String> findPrice2(String product){
		List<Shop> shops = Arrays.asList(new Shop("listenq"),new Shop("张琦"),new Shop("可爱多"),new Shop("维达"));
	    List<CompletableFuture<String>> priceFuture = shops.stream()
	            .map(shop -> CompletableFuture.supplyAsync( // 使用异步的方式计算每种商品的价格
	                    () -> shop.getName() + " 的价格是 " + shop.getPrice(product)))
	            .collect(Collectors.toList());

	    return priceFuture.stream()
	            .map(CompletableFuture::join) //join 操作等待所有异步操作的结果
	            .collect(Collectors.toList());
	}
	
	//使用CompletableFutures异步操作
	public static List<String> findPrice4(String product){
		List<Shop> shops = Arrays.asList(new Shop("listenq"),new Shop("张琦"),new Shop("可爱多"),new Shop("维达"));
		List<CompletableFuture<String>> priceFuture = shops.stream()
	            .map(shop -> CompletableFuture.supplyAsync( // 异步获取价格
	                    () -> shop.getPrice2(product), executor ))
	            .map(future -> future.thenApply(Quote::parse)) // 获取到价格后对价格解析
	            .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync( // 另一个异步任务构造异步应用报价
	                                                        () -> Discount.applyDiscount(quote), executor)))
	            .collect(Collectors.toList());

	    return priceFuture.stream()
	            .map(CompletableFuture::join) //join 操作和get操作有相同的含义，等待所有异步操作的结果。
	            .collect(Collectors.toList());
	}
	
	//如果两个异步操作是相互独立的
	/*List<CompletableFuture<Double>> priceFuture = shops.stream()
	        .map(shop -> CompletableFuture.supplyAsync( // 异步获取价格
	                () -> shop.getPrice(product), executor))
	        .map(future -> future.thenCombine(CompletableFuture.supplyAsync( // 异步获取折扣率
	                () -> Discount.getRate(), executor)
	                , (price, rate) -> price * rate)) // 将两个异步任务的结果合并
	        .collect(Collectors.toList());
	        */
	//将两个独立的CompletableFutures组合，因为操作是并行的，任务也是并行的
	
	
	
	
	
	static class Shop{
		private String name;
		private Random random = new Random();
		
		public Shop(String name) {
			this.name = name;
		}

		public double getPrice(String product) {
			return calculatePrice(product);
		}
		
		public String getPrice2(String product){
		    double price = calculatePrice(product);
		    Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
		    return String.format("s%:%.2f:%s", name, price, code);
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
