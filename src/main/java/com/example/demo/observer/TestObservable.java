package com.example.demo.observer;

import java.util.Observable;

import org.springframework.stereotype.Component;

/**
 * Observable 被观察者
 * <br/> setChanged() 方法来 设置 change 为 true
 * <br/> notifyObservers() 方法向观察者发送信息
 * TestObservable
 * @author zhangqi 
 * @date 2020年6月23日-上午11:22:42
 * 
 */
@Component
public class TestObservable extends Observable{
	
	void test() {
		String [] str = {"a","b","c"};
		for (String string : str) {
			//设置标记
			setChanged();
			//通知观察者
			notifyObservers(string);
			try {  
                Thread.sleep(1000);  
            } catch (InterruptedException e) {  
                System.out.println("Error Occurred.");  
            }  
		}
	}

}
