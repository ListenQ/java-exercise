package com.example.demo.async;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * 异步调用  @Async所修饰的函数不要定义为static类型，这样异步调用不会生效
 * Task<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年1月31日-下午3:09:32 <BR>
 * @version 1.0.0
 * 
 */
@Component
public class Task {

	public static Random random = new Random();

	/**
	 * 异步调用+java8并行流执行
	 */
	@Async
	public Future<Object> doTaskOne() throws Exception {
		System.out.println("开始做任务");
		long start = System.currentTimeMillis();
		Integer [] count = {0};
		List<String> urlList = Arrays.asList("http://www.baidu.com","http://www.listenq.cn","http://www.listenq.cn");
		List<String> resultList = urlList.parallelStream().parallel().map(url ->{
			try {
				count[0]++;
				//相当于请求第三方接口耗时
				Thread.sleep(random.nextInt(10000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "result:"+url;
		}).collect(Collectors.toList());
		System.out.println("完成调用，耗时：" + (System.currentTimeMillis() - start) + "毫秒");
		return new AsyncResult<Object>(resultList);
	}
	
	/**
	 * 异步调用+自定义并行流线程执行获取结果
	 */
	@Async
	public Future<Object> doTaskOne11() throws Exception {
		System.out.println("开始做任务11");
		long start = System.currentTimeMillis();
		List<String> urlList = Arrays.asList("http://www.baidu.com","http://www.listenq.cn","http://www.listenq.cn");
		ForkJoinPool forkJoinPool = new ForkJoinPool(15);
		Object res = forkJoinPool.submit(()->
			urlList.parallelStream().parallel().map(url ->{
				try {
					//相当于请求第三方接口耗时
					Thread.sleep(random.nextInt(10000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "result:"+url;
			}).collect(Collectors.toList())
		).get();
		System.out.println("完成调用，耗时：" + (System.currentTimeMillis() - start) + "毫秒");
		return new AsyncResult<Object>(res);
	}
	
	
	@Async
	public Future<Object> doTaskOne1() throws Exception {
		System.out.println("开始做任务1");
		long start = System.currentTimeMillis();
		for(int i = 0;i<3;i++) {
			Thread.sleep(random.nextInt(10000));
		}
		System.out.println("完成调用1，耗时：" + (System.currentTimeMillis() - start) + "毫秒");
		return new AsyncResult<Object>("任务1的结果");
	}
	
	@Async
	public Future<Object> doTaskOne2() throws Exception {
		System.out.println("开始做任务2");
		haoshi();
		System.out.println("111");
		haoshi();
		System.out.println("222");
		haoshi();
		return new AsyncResult<Object>("任务2的结果");
	}
	
	public Object doTaskOne3() throws Exception {
		System.out.println("开始做任务3");
		Future<Object> haoshi1 = haoshi2();
		Future<Object> haoshi2 = haoshi2();
		Future<Object> haoshi23 = haoshi2();
		return haoshi1.get().toString()+haoshi2.get()+haoshi23.get();
	}
	
	@Async
	public String test() throws Exception {
		System.out.println("开始做任务test");
		long start = System.currentTimeMillis();
		Object haoshi = haoshi();
		System.out.println("111");
		Object haoshi2 = haoshi();
		System.out.println("222");
		Object haoshi3 = haoshi();
		System.out.println("结果是:"+haoshi+haoshi2+haoshi3);
		System.out.println("完成调用test，耗时：" + (System.currentTimeMillis() - start) + "毫秒");
		return "任务3的结果";
	}
	
	
	@Async
	public Future<String> doTaskTwo() throws Exception {
		System.out.println("开始做任务二");
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		System.out.println("完成任务二，耗时：" + (System.currentTimeMillis() - start) + "毫秒");
		return new AsyncResult<>("任务二完成");
	}
	
	@Async
    public Future<String> doTaskThree() throws Exception {
        System.out.println("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成");
    }
	
	@Async
	public Object haoshi() throws InterruptedException {
		Thread.sleep(2000);
		return "返回结果";
	}
	
	
	@Async
	public Future<Object> haoshi2() throws InterruptedException {
		Thread.sleep(2000);
		return new AsyncResult<>("返回结果");
	}
	

}
