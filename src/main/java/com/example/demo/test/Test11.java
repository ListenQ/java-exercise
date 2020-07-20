package com.example.demo.test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.enums.Constants;

public class Test11 {
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		
		System.out.println(CMSingln.get());
	}
	
	
	static class CMSingln {
		private static Map<String,String> map = new HashMap<>();
		
		private CMSingln() {}
		
		static {
			Class<Constants.CM> cm = Constants.CM.class;
			Field[] fields = cm.getDeclaredFields();
			for (Field field : fields) {
				try {
					String path = field.get(field.getName()).toString();
					if(path.indexOf("@") > 0) {
						map.put(path.substring(0, path.indexOf("@")),path);
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static Map<String,String> get() {
			return map;
		}
	}
	

}
