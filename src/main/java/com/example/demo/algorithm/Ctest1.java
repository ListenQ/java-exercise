package com.example.demo.algorithm;

import java.util.Arrays;

/**
 * 算法
 * Ctest1<BR>
 * 创建人：zhangqi <BR>
 * 时间：2020年4月26日-下午11:46:08 <BR>
 * @version 1.0.0
 * 
 */
public class Ctest1 {
	
	
	public static void main(String[] args) {
		int [] nums = {2,7,11,15}; int target = 9;
		long start = System.currentTimeMillis();
		int[] algorithm = twoSum(nums, target);
		System.out.println((System.currentTimeMillis()-start));
		System.out.println(Arrays.toString(algorithm));
	}
	
	public static int[] twoSum(int[] nums, int target) {
		boolean flag = true;
		int [] index = new int[2];
		
		for (int i = 0; i < nums.length && flag; i++) {
			index[0] = i;
			int num = target - nums[i];
			for(int j =i+1;j< nums.length;j++) {
				if(num == nums[j]) {
					index[1] = j;
					flag = false;
					break;
				}
			}
		}
		
		return index;
	}

}
