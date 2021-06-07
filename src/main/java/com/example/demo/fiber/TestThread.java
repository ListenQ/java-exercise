package com.example.demo.fiber;

import cn.hutool.core.thread.ThreadUtil;

public class TestThread {


	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				calc();
			}
		};



		int size = 10000;
		Thread[] threads = new Thread[size];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(r);
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}

		System.out.println(System.currentTimeMillis() - start);
	}


	static void calc() {
//		int result = 0;
//		for (int m = 0; m < 10000; m++) {
//			for (int i = 0; i < 200; i++) result += i;
//		}
		ThreadUtil.safeSleep(10);
	}

}
