package com.example.demo.mult.thread.java8;

import java.util.HashMap;
import java.util.Map;

public class CacheDemo {
	
	public static Map<String, Object> map = new HashMap<>(); 
	
	public void cache(final CacheTest.TypeDemo obj) {
		map.put(obj.getName(), obj);
	}

}
