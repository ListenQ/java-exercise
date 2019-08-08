package com.example.demo.mult.thread.countdownlatch;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.example.demo.DateTimeTest;

public class ThreadDemo implements Runnable{
	
	private TAbract t;
	
	public ThreadDemo(TAbract t) {
		this.t = t;
	}


	@Override
	public void run() {
//		service.batch();
		
		t.batch();
		try {
			System.out.println(Thread.currentThread().getName()+DateTimeTest.parseDate("2019-08-07 12:23:54"));
			System.out.println(Thread.currentThread().getName()+DateTimeTest.dataFormat(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+DateTimeTest.getZeroTime());
	}

}
