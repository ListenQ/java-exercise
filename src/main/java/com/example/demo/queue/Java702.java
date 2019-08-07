package com.example.demo.queue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class Java702 {
	
	public static void main(String[] args) {
		System.out.println(ForkJoinPool.commonPool().getParallelism());
		System.out.println("当前cpu执行数量:"+Runtime.getRuntime().availableProcessors());
		//与ThreadPoolExecutor类似
		ForkJoinPool forkPool = new ForkJoinPool();
		
		List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9);
		AddTask addTask = new AddTask(nums);
		forkPool.invoke(addTask);
		forkPool.shutdown();
	}
	
	private static class AddTask extends RecursiveAction{
		private final List<Integer> nums;
		
		private AddTask(List<Integer> nums) {
			this.nums = nums;
		}
		@Override
		protected void compute() {
			
		}
	}
	
	private static class AddTesk2 extends RecursiveTask<Integer>{

		@Override
		protected Integer compute() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	
	
	
	

}
