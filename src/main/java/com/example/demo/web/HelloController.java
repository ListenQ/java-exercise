package com.example.demo.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.async.Task;
import com.example.demo.cp.TestDto;

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
		Map<String, Object> map =new HashMap<>();
		map.put("isLiked", true);
		map.put("is_liked", true);
		Future<Object> one2 = task.doTaskOne2();
		map.put("one",one2.get());
		System.out.println(one2.get());
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
	
	@RequestMapping("/test2")
	public Object test2() {
		TestDto test= new TestDto();
		test.setName("xixi");
		test.setNumber(new BigDecimal(1.00));
		test.setDate(new Date());
		return test;
	}
	

}
