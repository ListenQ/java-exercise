package com.example.demo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.text.StringEscapeUtils;

public class TestD {
	
	class Stu {
		private String name;
		private String number;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		@Override
		public String toString() {
			return "Stu [name=" + name + ", number=" + number + "]";
		}
	}

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
		
		String sss ="B123a##6g#鍒堕€犲晢c#浼樼瓑#71,妫夌嚎 A21-71234##7g#鍒堕€犲晢d#浼樼瓑#72";
		System.out.println(sss.split(",")[1].contains("#71"));
		String str = "452.00片";
		System.out.println(str.substring(0,str.indexOf("片")));
		
		
		
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put("batch_id", "124578412");
		map.put("sqrialno", "1");
		list.add(map);
		
		Stu stu = new TestD().new Stu();
		stu.setName(list.stream().map(m ->m.get("batch_id")).findFirst().get());
		stu.setNumber(list.stream().map(m ->m.get("sqrialno")).findFirst().get());
		System.out.println(stu);
		
	}
	
	

}
