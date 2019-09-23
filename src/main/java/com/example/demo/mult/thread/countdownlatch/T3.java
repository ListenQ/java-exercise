package com.example.demo.mult.thread.countdownlatch;

public class T3 implements TAbract{

	@Override
	public String batch() {
		System.out.println("t3....");
		return "success3";
	}

}
