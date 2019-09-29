package com.example.demo.reptile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 幸运飞艇数据生成
 * PK10Lucky<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年9月29日-下午10:11:57 <BR>
 * @version 1.0.0
 * 
 */
public class PK10Lucky {
	
	public static void main(String[] args) {
		for(int i =1;i<=180;i++) {
			int[] num = generateLucky();
			System.out.println("幸运飞艇的"+i+"期出奖号码为:"+Arrays.toString(num));
		}
	}
	
	private static int[] generateLucky() {
		int [] num = new int[10];
		List<Integer> temp = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		
		for(int i=1;i<=10;i++) {
			temp.add(i);
		}
		int index = 0;
		while(true) {
			if(list.size()==10) {
				break;
			}
			int next = temp.get((int)(Math.random()*10));
			if(list.contains(next)) {
				continue;
			}else {
				num[index] = next;
				list.add(next);
				index ++;
			}
		}
		return num;
	}

}
