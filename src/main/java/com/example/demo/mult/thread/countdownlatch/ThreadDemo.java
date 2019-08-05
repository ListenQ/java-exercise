package com.example.demo.mult.thread.countdownlatch;

import java.util.List;

public class ThreadDemo implements Runnable{
	
	private TAbract t;
	
	public ThreadDemo(TAbract t) {
		this.t = t;
	}


	@Override
	public void run() {
//		service.batch();
		
		t.batch();
		
		System.out.println(Thread.currentThread().getName()+"执行");
	}

}
