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
		Future<Object> one2 = task.doTaskOne2();
		map.put("one",one2.get());
		System.out.println(one2.get());
		Future<Object> one11 = task.doTaskOne11();
		System.out.println(one11.get());
		return map;
	}
	
	@RequestMapping("/test")
	public Object test() {
		TestDto test= new TestDto();
		test.setName("xixi");
		test.setNumber(new BigDecimal(1.00));
		test.setDate(new Date());
		return test;
	}
	

}
