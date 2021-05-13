package com.example.demo.algorithm.simple;

/**
 * 字节位运算
 * Ctest_Byte<BR>
 * 创建人：zhangqi <BR>
 * 时间：2021年5月9日-上午10:23:22 <BR>
 * @version 1.0.0
 * 
 */
public class Ctest_Byte {
	
	
	
	public static void main(String[] args) {
		
		/**
		 * & 两个为1才为1，其他为0
		 * | 两个位只要有一个为1就为1，否则就为0
		 * ^ 相同为0，不同为1
		 * ~ 0变1，1变0
		 */
		
		// 利用位运算判断一个数是奇偶数
		//      110     111
		// << 左移 对于int类型大于等于32得要做 % 32 之后，long 类型要 %64位 
		// 正数  原码补码和反码是一样得
		//  原码 转 补码 => 符号位不变，其他位取反+1
		//  补码 转 原码 => -1 后 符号位不变在 取反
		// >> 右移 对于负数  要先将原码 ==>(符号位保持不变，按位取反) 获得反码 进行移动后  ==>(反码+1)    获取补码   
		int n = 6 , m = 8;
		System.out.println(isOdd(m));
		System.out.println("---------------");
		// 利用位运算交换两数、  【一个数对自己异或一次 =0，在异或另外一个数字，结果就是另外一个数】 ==> 0 & * = *
		// ^ 异或是  相同为0 不同为1
		System.out.println("自己异或自己："+(n ^ n));
		System.out.println("一个数与0异或："+(0 ^ n));
		System.out.println("两不同数异或："+(n ^ m));
		System.out.println(n ^ n ^ m);
		System.out.println(m ^ m ^ n);
		changeNum(n, m);
	}
	
	/**
	 * 数字交换
	 */
	private static void changeNum(int n,int m) {
		n = n ^ m;
		m = n ^ m;
		n = n ^ m;
		System.out.println("n:"+n+"--m:"+m);
	}
	
	
	/**
	 * (判断奇偶数,一个数 & 1 如果等于 0 位偶数 为1 则是奇数)<BR>
	 * 方法名：isOdd<BR>
	 * 创建人：zhangqi <BR>
	 * 时间：2021年5月9日-上午10:25:57 <BR>
	 * @param n
	 * @return boolean<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	private static boolean isOdd(int n) {
		return (n & 1) == 1;
	}

}
