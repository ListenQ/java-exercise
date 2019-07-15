package com.example.demo.design.patterns.responsibility;

public class Demo {

	private static AbstractLogger getLogger() {
		AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
		AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
		AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
		
		errorLogger.setNextLogger(fileLogger);
		fileLogger.setNextLogger(consoleLogger);
		
		return errorLogger;
	}
	
	
	public static void main(String[] args) {
		AbstractLogger logger = getLogger();
		logger.logMessage(AbstractLogger.INFO, "这是一个 information");
		logger.logMessage(AbstractLogger.DEBUG, "这是一个debug information");
		logger.logMessage(AbstractLogger.ERROR, "这是一个error information");
	}
}
