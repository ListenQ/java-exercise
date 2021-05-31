package com.example.demo.algorithm.simple;

/**
 * 回文数LeetCode 简单第9题
 * Ctest_Palindrome
 * @author zhangqi 
 * @date 2021年1月13日-下午3:58:09
 *
 */
public class Ctest_Palindrome {

	
	public static void main(String[] args) {
		long start = System.nanoTime();
		boolean b = isPalindrome2(1231);
		System.out.println((System.nanoTime() - start)+"**"+b);
	}
	
	
	/**
	 * 判断回文数
	 */
	public static boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}
		if (x < 10) {
			return true;
		}
		
		String s = x+"";
		char[] array = s.toCharArray();
		for (int i = 0; i < array.length/2; i++) {
			if (array[i] != array[s.length()-i-1]) {
				return false;
			}
		}
		return true;
    }
	
	
	
	/**
	 * 进阶
	 */
	public static boolean isPalindrome2(int x) {
		if (x < 0) {
			return false;
		}
		if (x < 10) {
			return true;
		}
		long tmp = x, rec = 0;
		// 一个数 取模10 就是得到个位数
		for(; x !=0 ;) {
			//有溢出int范围的可能
			rec = rec*10 + x%10;
			x /= 10;
		}
		if (tmp != rec) {
			return false;
		}
		return true;
	}
}
