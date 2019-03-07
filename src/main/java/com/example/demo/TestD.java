package com.example.demo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;

public class TestD {

	public static void main(String[] args) throws UnsupportedEncodingException {
		int [][] num = {{625,270},{1250,540}};
		int [] w = {1250,540};
		List<Long> ids = Arrays.asList(12l,45l,6564l,7l);
		List<Long> ids2 = new ArrayList<>(0);
		Long id = 454l;
		//System.out.println(Arrays.equals(num[0],w));
		System.out.println(ids.contains(id));
		String s[] = "|Wechat|feixiaohao07|copy".split("\\|");
		System.out.println(s[2]);
		
//		for (int i = 0; i < 10; i++) {
//			int nextInt = RandomUtils.nextInt(6);
//			System.out.println(nextInt==0?++nextInt:nextInt);
//		}
		
//		ids2.stream().forEach(isd ->{
//			System.out.println(isd);
//		});
		
//		String ss= "  测试标题&casd& #40; asdf & #41;  ";
//		System.out.println(StringEscapeUtils.unescapeHtml4(ss.replaceAll("\\s*","")));
		
		System.out.println("bqi_web".contains("fxh"));
		
	}

	

	

}
