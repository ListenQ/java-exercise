package com.example.demo.observer;

import java.util.Observable;
import java.util.Observer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Observer观察者例子
 * <br/> 观察者。实现该接口后，能够接收到来自被观察者因改动而发出的通知
 * <br/> update() 方法，会被 Observable 调用
 * TestObserver
 * @author zhangqi 
 * @date 2020年6月23日-上午11:12:37
 * 
 */
@Component
public class TestObserver implements Observer{

	@Autowired
	private TestObservable testObservable;
	
	
	@PostConstruct
	void init() {
		//给观察者设置被观察者
		testObservable.addObserver(this);
	}
	
	
	@Override
	public void update(Observable o, Object event) {
		System.out.println("监听有改变:"+event);
	}

}
