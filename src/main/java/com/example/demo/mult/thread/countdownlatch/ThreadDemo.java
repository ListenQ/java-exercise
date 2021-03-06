package com.example.demo.mult.thread.countdownlatch;

import java.util.concurrent.Callable;

import com.example.demo.test.DateTimeTest;

public class ThreadDemo implements Callable<String>{
	
	private TAbract t;
	
	public ThreadDemo(TAbract t) {
		this.t = t;
	}


	@Override
	public String call() {
//		service.batch();
		String result = null;
		try {
			 result = t.batch();
//			System.out.println(Thread.currentThread().getName()+DateTimeTest.parseDate("2019-08-07 12:23:54"));
//			System.out.println(Thread.currentThread().getName()+DateTimeTest.dataFormat(new Date()));
		} catch (Throwable e) {
			e.printStackTrace();
		} 
		System.out.println(Thread.currentThread().getName()+DateTimeTest.getZeroTime());
		return result;
	}

}
