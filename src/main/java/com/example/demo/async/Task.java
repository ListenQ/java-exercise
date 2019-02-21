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
 * 异步调用
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
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		Thread.sleep(random.nextInt(10000));
		Thread.sleep(random.nextInt(10000));
		System.out.println("完成调用2，耗时：" + (System.currentTimeMillis() - start) + "毫秒");
		return new AsyncResult<Object>("任务2的结果");
	}
	
	public Future<Object> doTaskOne3() throws Exception {
		System.out.println("开始做任务3");
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		Thread.sleep(random.nextInt(10000));
		Thread.sleep(random.nextInt(10000));
		System.out.println("完成调用3，耗时：" + (System.currentTimeMillis() - start) + "毫秒");
		return new AsyncResult<Object>("任务3的结果");
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
	

}
