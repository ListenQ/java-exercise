package com.example.demo.design.patterns.observer;

/**
 * 观察者模式例子
 * ObserverPatternDemo<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年8月12日-下午10:28:36 <BR>
 * @version 1.0.0
 * 
 */
public class ObserverPatternDemo {
	
	
	public static void main(String[] args) {
		Subject subject = new Subject();
		
		new HexaObserver(subject);
		new OctalObserver(subject);
		new BinaryObserver(subject);

		System.out.println("first state change: 15");
		subject.setState(15);
		
		System.out.println("Second state change : 10");
		
		subject.setState(10);
	}
	
	

}
