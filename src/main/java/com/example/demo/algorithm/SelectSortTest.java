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
	
	
	/**
	 * 选择排序
	 * 时间复杂度: O(n^2)
	 * 空间复杂度: O(1)
	 */
	public static void selectSort(int[] arr) {
		if(null == arr || arr.length < 2) {
			return;
		}
		
		// 0 ~ N-2  指的是下标(需要比较交换的范围)  最后第N位就一个所以不需要比较交换了 所以就是  < (arr.length -1)
		// 1 ~ N-2
		for (int i = 0; i < arr.length -1; i++) {
			int minIndex = i;
			
			//因为前面已经比较交换位置了,所以是在i+1范围内查找最小值下标
			//1 ~ N-1 下标范围
			//2 ~ N-1 下标范围
			for (int j = i+1; j < arr.length; j++) {
				minIndex = arr[j] < arr[minIndex]? j : minIndex;
			}
			//如果最小值下标与当前位置一样就不需要交换
			if (minIndex != i) {
				//交换当前位置与最小值位置
//				change(arr, i, minIndex);
				swap(arr, i, minIndex);
			}
		}
	}
	
	
	/**
	 * 交换位置
	 */
	private static void swap(int[] arr,int currentIndex,int minIndex) {
		arr[currentIndex] = arr[currentIndex] ^ arr[minIndex];
		arr[minIndex] = arr[currentIndex] ^ arr[minIndex];
		arr[currentIndex] = arr[currentIndex] ^ arr[minIndex];
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
