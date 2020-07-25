package com.example.demo.design.patterns.strategy;

/**
 * 策略模式
 * Context<BR>
 * 创建人：zhangqi <BR>
 * 时间：2020年7月25日-下午10:53:08 <BR>
 * @version 1.0.0
 * 
 */
public class Context {
	
	private Strategy strategy;
	
	public Context(Strategy strategy) {
		this.strategy =strategy;
	}
	
	public int executeStratrgy(int num1,int num2) {
		return strategy.doOperation(num1, num2);
	}

}
