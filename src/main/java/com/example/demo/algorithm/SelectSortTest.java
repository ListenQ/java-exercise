package com.example.demo.algorithm;

/**
 * 选择排序
 * SelectSortTest<BR>
 * 创建人：zhangqi <BR>
 * 时间：2020年6月19日-下午10:17:39 <BR>
 * @version 1.0.0
 * 
 */
public class SelectSortTest {
	
	public static void main(String[] args) {
		int[] arr = {5,3,1,4,6,8,7,2,9};
		selectSort(arr);
		for (int i : arr) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
	
	
	public static void selectSort(int[] arr) {
		if(null == arr || arr.length < 2) {
			return;
		}
		
		// 0 ~ N-1 指的是下标(需要比较交换的范围)最后第N位就一个所以不需要比较交换了 所以就是到(arr.length -1)
		// 1 ~ N-1
		for (int i = 0; i < arr.length -1; i++) {
			int minIndex = i;
			
			for (int j = i+1; j < arr.length; j++) {
				//查找最小值下标
				minIndex = arr[j] < arr[minIndex]? j : minIndex;
			}
			//交换当前位置与最小值位置
			change(arr, i, minIndex);
		}
	}
	
	/**
	 * 交换位置
	 */
	private static void change(int[] arr,int i,int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	

}
