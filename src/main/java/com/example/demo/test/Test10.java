package com.example.demo.test;

import java.util.Arrays;

import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class Test10 {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		 Integer[] array = { 2 ,5,null,0,6,7,9,10,-2,-4};
	        Arrays.parallelSort(array, ComparatorUtils.nullLowComparator((k1, k2) -> {
	        	if(null == k1){
	        		System.out.println(k1);
	        		return 0;
	        	}
	        	if(null == k2) {
	        		System.out.println(k2);
	        		return 0;
	        	}
	        	if(k1.equals(k2)) {
	        		return 0;
	        	}
	            return NumberUtils.compare(Integer.valueOf(k1+""), Integer.valueOf(k2+""));
	        }));
	        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(array));
	}

}
