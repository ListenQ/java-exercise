package com.example.demo.fiber;

import java.util.concurrent.ExecutionException;

import cn.hutool.core.thread.ThreadUtil;
import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;

public class TestFiber {
	
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		System.setProperty("co.paralleluniverse.fibers.detectRunawayFibers", "false");
		long start = System.currentTimeMillis();
		
		SuspendableRunnable runnable = new SuspendableRunnable() {
            public void run() throws SuspendExecution, InterruptedException {
                calc();
            }
        };
		
		int size = 10000;
		Fiber<Void>[] fibers = new Fiber[size];
		
        for (int i = 0; i < fibers.length; i++) {
            fibers[i] = new Fiber<Void>(runnable);
        }
        for (int i = 0; i < fibers.length; i++) {
            fibers[i].start();
        }
        for (int i = 0; i < fibers.length; i++) {
            fibers[i].join();
        }
        
        System.out.println((System.currentTimeMillis() - start));
	}
	
	
	static void calc() {
//        int result = 0;
//        for (int m = 0; m < 10000; m++) {
//            for (int i = 0; i < 2000; i++) {
//            	result += i;
//            }
//        }
        ThreadUtil.safeSleep(50);
    }
	

}
