package com.example.demo.design.patterns.responsibility;

public class ErrorLogger extends AbstractLogger{
	
	public ErrorLogger(int level) {
		this.level = level;
	}

	@Override
	protected void write(String message) {
		System.out.println("ERROR::logger..."+message);
	}

}
