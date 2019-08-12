package com.example.demo.design.patterns.strategy;

/**
 * 策略模式
 * StrategyPatternDemo<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年8月12日-下午10:13:13 <BR>
 * @version 1.0.0
 * 
 */
public class StrategyPatternDemo {
	
	public static void main(String[] args) {
		Context context = new Context(new OperationAdd());
		System.out.println("10 + 5 =" + context.executeStratrgy(10, 5));
		
		context = new Context(new OperationSubstract());
		System.out.println("10 - 5 =" + context.executeStratrgy(10, 5));
		
		context = new Context(new OperationMultiply());
		System.out.println("10 * 5 =" + context.executeStratrgy(10, 5));
		
	}

}
