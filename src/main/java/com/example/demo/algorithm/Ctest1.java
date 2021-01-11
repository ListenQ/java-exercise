package com.example.demo.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 算法-两数之和
 * Ctest1<BR>
 * 创建人：zhangqi <BR>
 * 时间：2020年4月26日-下午11:46:08 <BR>
 * @version 1.0.0
 * 
 */
public class Ctest1 {
	
	
	public static void main(String[] args) {
		int [] nums = {2,7,11,15}; int target = 17;
		long start = System.nanoTime();
		int[] algorithm = twoSum2(nums, target);
		System.out.println((System.nanoTime()-start));
		System.out.println(Arrays.toString(algorithm));
	}
	
	
	/**
	 * 两数之和
	 * @author zhangqi 
	 * @date 2021年1月11日-下午5:22:25
	 * @param nums
	 * @param target
	 * @return int[]
	 */
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
	
	
	/**
	 * 第二种解法hashMap
	 * @author zhangqi 
	 * @date 2021年1月11日-下午6:41:22
	 * @param nums
	 * @param target
	 * @return int[]
	 */
	public static int[] twoSum2(int[] nums,int target) {
		int [] index = new int[2];
		Map<Integer, Integer> numMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int num = target - nums[i];
			if (numMap.containsKey(num)) {
				index[0] = numMap.get(num);
				index[1] = i;
				return index;
			}
			numMap.put(nums[i], i);
		}
		return index;
	}

}
