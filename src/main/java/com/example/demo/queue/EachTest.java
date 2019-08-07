package com.example.demo.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EachTest {
	
	public static void main(String[] args) {
		List<String> itList = new ArrayList<String>();
		List<String> it2List = new ArrayList<String>();
		List<String> it3List = new ArrayList<String>();
		List<String> it4List = new ArrayList<String>();
		
		
		List<String> list=new ArrayList<String>();
		for (int i = 0; i < 1000; i++) {
			list.add("sdf"+i);
		}
		
		//方法1
		Iterator<String> it = list.iterator();
		long start = System.nanoTime();
		while(it.hasNext()){
			itList.add(it.next());
		}
		long end1 = System.nanoTime();
		//方法2
		for(Iterator<String> it2 = list.iterator();it2.hasNext();){  
			it2List.add(it2.next());  
        }  
		long end2 = System.nanoTime();
		
		//方法3
		for (String l :list){
			it3List.add(l);
	    } 
		long end3 = System.nanoTime();
		
		//方法4
		for(int i=0;i<list.size();i++){  
			it4List.add(list.get(i));  
        }  
		long end4 = System.nanoTime();
		
		
		System.out.println("第一种方法耗时：" + (end1 - start)+ "微秒");  
        System.out.println("第二种方法耗时：" + (end2-end1) + "微秒");  
        System.out.println("第三种方法耗时：" + (end3-end2) + "微秒");  
        System.out.println("第四种方法耗时：" + (end4-end3) + "微秒");  
	}

}
