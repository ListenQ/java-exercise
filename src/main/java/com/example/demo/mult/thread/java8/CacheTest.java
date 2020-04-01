package com.example.demo.mult.thread.java8;

import java.lang.reflect.Method;

public class CacheTest {
	
	
	public static void main(String[] args) throws Exception {
		TypeDemo obj = new TypeDemo();
		obj.setName("00700");
		obj.setType(0);
		CacheDemo cache = new CacheDemo();
		cache.cache(obj);
		change(obj);
		System.out.println(obj);
		System.out.println(CacheDemo.map);
	}
	
	private static void change(TypeDemo obj) throws Exception {
//		obj.setType(2);
		Method method = obj.getClass().getDeclaredMethod("setType", new Class[] {Integer.class});
		method.invoke(obj, 2);
	}
	
	static class TypeDemo{
		private String name;
		private Integer type;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
		@Override
		public String toString() {
			return "Type [name=" + name + ", type=" + type + "]";
		}
	}
	

}
