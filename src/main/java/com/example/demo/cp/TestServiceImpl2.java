package com.example.demo.cp;

public class TestServiceImpl2 implements TestService{
	
	public TestServiceImpl2(TestDto t) {
		System.out.println("這個是第二"+t);
	}
	
	public TestServiceImpl2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSomething() {
		System.out.println("做的第二件事情");
	}

}
