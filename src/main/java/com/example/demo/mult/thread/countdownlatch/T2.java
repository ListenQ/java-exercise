package com.example.demo.mult.thread.countdownlatch;

public class T2 implements TAbract{

	@Override
	public void batch() throws InterruptedException {
		System.out.println("t2....");
		if(true) {
			Thread.sleep(6*1000);
			throw new RuntimeException();
		}
	}

}