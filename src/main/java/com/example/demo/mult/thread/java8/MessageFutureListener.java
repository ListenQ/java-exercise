package com.example.demo.mult.thread.java8;

@FunctionalInterface
public interface MessageFutureListener {
	
	 public void callBack(String respId, Object request, Object message);

}
