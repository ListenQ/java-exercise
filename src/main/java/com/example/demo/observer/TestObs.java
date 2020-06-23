package com.example.demo.observer;

public class TestObs {
	
	public static void main(String[] args) {
		TestObservable observable = new TestObservable();
		
		observable.addObserver(new TestObserver());
		
		observable.test();
	}

}
