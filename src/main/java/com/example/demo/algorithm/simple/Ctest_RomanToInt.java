package com.example.demo.algorithm.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转整数 LeetCode 简单第13题
 * Ctest_RomanToInt
 * @author zhangqi 
 * @date 2021年1月13日-下午5:48:26
 *
 */
public class Ctest_RomanToInt {
	
	public static void main(String[] args) {
		long start = System.nanoTime();
		int result = remanToInt2("IX");
		System.out.println((System.nanoTime() - start)+"***"+result);
	}
	
	
	/**
	 * 转换*给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
	 */
	@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
	public static int romanToInt(String s) {
		if (null == s || s.trim().isEmpty()) {
			return 0;
		}
		Map<Character,Integer> romanMap = new HashMap(){{
			put('I', 1);
			put('V', 5);
			put('X', 10);
			put('L', 50);
			put('C', 100);
			put('D', 500);
			put('M', 1000);
		}};
		int result = 0,preNum = romanMap.get(s.charAt(0)) == null? 0:romanMap.get(s.charAt(0));
		for (int i = 1; i < s.length(); i++) {
			int num = romanMap.get(s.charAt(i));
			if (preNum < num) {
				result -= preNum;
			} else {
				result += preNum;
			}
			preNum = num;
		}
		result += preNum;
		return result;
    }
	
	
	/**
	 * 进阶版
	 */
	public static int remanToInt2(String s) {
		if (null == s || s.trim().isEmpty()) {
			return 0;
		}
		int result = 0, lastNum = 0;
		for (int i = s.length()-1; i >= 0; i--) {
			int value = getValue(s.charAt(i));
			if (value < lastNum) {
				result -= value;
			} else {
				result += value;
			}
			lastNum = value;
		}
		return result;
	}
	
	private static int getValue(char c) {
		switch (c) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
		}
	}

}
