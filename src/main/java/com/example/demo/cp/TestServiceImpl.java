package com.example.demo.cp;

public class TestServiceImpl implements TestService{

	public TestServiceImpl(TestDto t) {
		System.out.println("这个是第一"+t);
	}
	
	public TestServiceImpl() {
	}
	
	@Override
	public void doSomething() {
		System.out.println("做的第一件事情");
	}

}
