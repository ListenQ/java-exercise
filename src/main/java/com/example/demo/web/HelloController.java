package com.example.demo.web;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.async.Task;
import com.example.demo.cp.TestDto;
import com.example.demo.test.DateTimeTest;

@RestController
public class HelloController {
	@Autowired
	private Task task;
	
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
	
	
	@GetMapping("/time")
	public Object testTime() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Date date = new Date();
		System.out.println("转换前时间是:"+DateTimeTest.dataFormat(date));
		System.out.println("美国纽约时间:"+DateTimeTest.dataFormat(date,ZoneId.of("America/New_York")));
		map.put("date", date);
		
        return map;
	}
	

}
