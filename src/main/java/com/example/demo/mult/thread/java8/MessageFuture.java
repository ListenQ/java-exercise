package com.example.demo.mult.thread.java8;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MessageFuture {
	
	private static Map<String, MessageFutureListener> map = new ConcurrentHashMap<>();
	
	
	public static void addListener(String messageId, Long timeout, Object request,MessageFutureListener futureListener) {
		map.put(messageId, futureListener);
	}
	
	public static void gett(String messageId){
		System.out.printf("%s进来了~~~", map.get(messageId));
	}

}
