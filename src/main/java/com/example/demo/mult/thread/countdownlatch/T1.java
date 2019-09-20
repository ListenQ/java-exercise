package com.example.demo.mult.thread.countdownlatch;

public class T1 implements TAbract{

	@Override
	public String batch() {
		System.out.println("t1...."+System.nanoTime());
		return "success1";
	}

}
