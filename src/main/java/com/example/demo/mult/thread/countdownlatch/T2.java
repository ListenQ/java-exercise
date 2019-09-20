package com.example.demo.mult.thread.countdownlatch;

public class T2 implements TAbract{

	@Override
	public String batch() throws InterruptedException {
		System.out.println("t2...."+System.nanoTime());
		if(true) {
			Thread.sleep(5*1000);
			throw new IllegalAccessError();
		}
		return "success2";
	}

}
