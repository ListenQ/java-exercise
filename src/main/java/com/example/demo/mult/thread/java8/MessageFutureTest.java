package com.example.demo.mult.thread.java8;

/**
 * 函数式编程设计模式 观察者
 * MessageFutureTest
 * @author zhangqi 
 * @date 2020年5月18日-下午4:34:40
 * 
 */
public class MessageFutureTest {
	
	public static void main(String[] args) {
		String messageId = "df-asdgas-12145-";
		MessageFuture.addListener(messageId, null, null, (ms,request,message) ->{
			System.out.println("--->"+ms);
			request.toString();
		});
	}

}
