package com.example.demo.cp;

public class TestServiceImpl3 implements TestService{

	public TestServiceImpl3(TestDto t) {
		System.out.println("这个是第三"+t);
	}
	
	public TestServiceImpl3() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void doSomething() {
		System.out.println("做的第三件事情");
	}

}
