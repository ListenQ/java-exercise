package com.example.demo.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.async.Task;
import com.example.demo.cp.TestDto;
import com.example.demo.listener.Demo2Event;
import com.example.demo.listener.Demo3Event;
import com.example.demo.listener.DemoEvent;
import com.example.demo.test.DateTimeTest;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;
import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;

@RestController
public class HelloController {
	@Autowired
	private Task task;
	
    @Autowired
    private ApplicationContext context;
    
//    @Resource(name="testServiceImpl2")
//    private TestService testService;
    
    @Resource(name="t2")
    private TestDto testDto;
	
    
	@RequestMapping("/hello")
	public Object index() throws Exception{
		Map<String, Object> map =new HashMap<>();
		map.put("isLiked", true);
		map.put("is_liked", true);
		Object doTaskOne3 = task.doTaskOne3();
		map.put("one",doTaskOne3);
		System.out.println(doTaskOne3);
//		Future<Object> one11 = task.doTaskOne11();
//		System.out.println(one11.get());
//		testService.doSomething();
		
		
		System.out.println(testDto.toString());
		return map;
	}
	
	@RequestMapping("/hello2")
	public Object index2() throws Exception{
		long start = System.currentTimeMillis();
		Map<String, Object> map =new HashMap<>();
		Future<Object> one2 = task.doTaskOne2();
		map.put("one",one2.get());
		System.out.println((System.currentTimeMillis()-start)+"s"+one2.get());
		return map;
	}
	
	@RequestMapping("/test")
	public Object test() throws Exception{
		Map<String, Object> map =new HashMap<>();
		String test = task.test();
		map.put("one",test);
		System.out.println(test);
		return map;
	}
	
	
	@RequestMapping("/test3")
	public Object test3() throws Exception {
		Map<String, Object> map =new HashMap<>();
		long start = System.currentTimeMillis();
		Object one2 = task.doTaskOne3();
		map.put("one",one2);
		System.out.println((System.currentTimeMillis()-start)+"s"+one2);
		return map;
	}
	
	@RequestMapping("/publish")
	public Object listen(String message) {
		context.publishEvent(new DemoEvent(this, message));
		return "success";
	}
	
	@RequestMapping("/publish2")
	public Object listen2(String message) {
		context.publishEvent(new Demo2Event(this, message));
		return "success";
	}
	
	@RequestMapping("/publish3")
	public Object listen3(String message) {
		context.publishEvent(new Demo3Event(this, message));
		return "success";
	}
	
	
	@GetMapping("/time")
	public Object testTime(String dateStr) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Date date = new Date();
		System.out.println("转换前时间是:"+DateTimeTest.dataFormat(date));
		System.out.println("美国纽约时间:"+DateTimeTest.dataFormat(date,ZoneId.of("America/New_York")));
		map.put("date", date);
		
		//美东时间转北京时间字符串
		String dataFormat = DateTimeTest.dataFormat(date, ZoneId.of("Asia/Shanghai"));
		System.err.println(dataFormat);
		
		//在全局美东时区获得一个北京时区时间
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Shanghai")));
		String str= format.format(new Date(date.getTime()));
		
		
		System.err.println(str);
		
		
		
        return map;
	}
	
	
	
	@GetMapping("/fiber")
	public Object testFiber() throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		
		SuspendableRunnable runnable = new SuspendableRunnable() {
            public void run() throws SuspendExecution, InterruptedException {
                calc();
            }
        };
		
		int size = 10000;
		Fiber<Void>[] fibers = new Fiber[size];
		
        for (int i = 0; i < fibers.length; i++) {
            fibers[i] = new Fiber<Void>(runnable);
        }
        for (int i = 0; i < fibers.length; i++) {
            fibers[i].start();
        }
        for (int i = 0; i < fibers.length; i++) {
            fibers[i].join();
        }

		System.out.println("fiber耗时:"+(System.currentTimeMillis() - start));
		return "success";
	}
	
	
	@GetMapping("/thread")
	public Object testThread() throws InterruptedException {
		long start = System.currentTimeMillis();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				calc();
			}
		};

		int size = 500;
		Thread[] threads = new Thread[size];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(r);
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}

		System.out.println("线程耗时:"+(System.currentTimeMillis() - start));
		return "success";
	}
	
	
	
	
	
	
	
	static void calc() {
//		int result = 0;
//		for (int m = 0; m < 10000; m++) {
//			for (int i = 0; i < 200; i++) result += i;
//		}
		HttpUtil.createGet("https://zhuorui-public-pre.oss-cn-shenzhen.aliyuncs.com/chip/HK/00700.txt");
		System.out.println(System.currentTimeMillis());
//		ThreadUtil.safeSleep(10);
	}
	
	

}
