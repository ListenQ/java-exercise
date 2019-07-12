package com.example.demo.design.patterns.proxy;

public class ProxyDemo {
	
	public static void main(String[] args) {
		Image image = new ProxyImage("test.png");
		image.display();
		System.out.println("----");
		image.display();
	}

}
