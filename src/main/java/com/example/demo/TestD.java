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
		//System.out.println(ids.contains(id));
		String s[] = "|Wechat|feixiaohao07|copy".split("\\|");
		//System.out.println(s[2]);
		
//		for (int i = 0; i < 10; i++) {
//			int nextInt = RandomUtils.nextInt(6);
//			System.out.println(nextInt==0?++nextInt:nextInt);
//		}
		
//		ids2.stream().forEach(isd ->{
//			System.out.println(isd);
//		});
		
//		String ss= "&#22899;&#31070;&#33410;&#36731;&#26494;get&radic;&#33459;&#24515;&#30340;&#31192;&#35776;&#65281;TF&#21475;&#32418;&#12289;&#31062;&#39532;&#40857;&#39321;&#27700;&#12289;LA MER&#30524;&#38684;&hellip;&hellip;&#22899;&#31070;&#30340;&#24515;&#24605;&#24110;&#20320;&#25720;&#36879;&#20102;&#65292;&#24555;&#36190;&#25105;&#65281;"; 
//		System.out.println(StringEscapeUtils.unescapeHtml4(ss));
		
//		System.out.println("bqi_web".contains("fxh"));
		
		System.out.println(Arrays.asList("normal","spam","ad","flood","meaningless").contains("ad"));
		
		
		
	}

	

	

}
