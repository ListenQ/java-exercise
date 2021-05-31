package com.example.demo.algorithm.simple;

/**
 * 最长公共前缀 LeetCode 简单第14题
 * Ctest_LongestCommonPrefix
 * @author zhangqi 
 * @date 2021年1月16日-上午10:48:38
 *
 */
public class Ctest_LongestCommonPrefix {
	
	public static void main(String[] args) {
		long start = System.nanoTime();
		String[] strs = {"",""};
		String result = longestCommonPrefix(strs );
		System.out.println((System.nanoTime() - start)+"*result = "+result);
	}
	
	
	/**
	 * 编写一个函数来查找字符串数组中的最长公共前缀，如果不存在公共前缀，返回空字符串 ""
	 */
	public static String longestCommonPrefix(String[] strs) {
		String longPrefix = "";
		if (null == strs || strs.length == 0) {
			return longPrefix;
		}
		for (int i = 0; i < strs.length; i++) {
			
		}
		return longPrefix;
    }

}
