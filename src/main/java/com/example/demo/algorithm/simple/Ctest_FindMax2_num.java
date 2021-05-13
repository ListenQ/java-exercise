package com.example.demo.algorithm.simple;

/**
 * 找出不大于N的最大的2幂指数
 * Ctest_FindMax2_num
 * @author zhangqi 
 * @date 2021年3月24日-下午5:13:35
 * 
 */
public class Ctest_FindMax2_num {
	
	
	public static void main(String[] args) {
		int N = 20210324;
		System.out.println(findN(N));
	}
	
	
	/**
	 *  <a href=https://www.cnblogs.com/gjmhome/p/11351315.html  />来自地址</a> 
	 * @author zhangqi 
	 * @date 2021年5月13日-下午3:43:46
	 * @param n
	 * @return int
	 */
	private static int findN(int n) {
		n |= n >> 1;
		n |= n >> 2;
		n |= n >> 4;
		n |= n >> 8;
		n |= n >> 16;
		n |= n >> 32;
		return (n +1 ) >>1;
	}

}
