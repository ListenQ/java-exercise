package com.example.demo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

	public static void main(String[] args) throws Exception {
		
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 15, TimeUnit.SECONDS, new 
				ArrayBlockingQueue<Runnable>(5),new ThreadPoolExecutor.CallerRunsPolicy()
				);
		
		
		int [][] num = {{625,270},{1250,540}};
		int [] w = {1250,540};
		List<Long> ids = Arrays.asList(12l,45l,6564l,7l);
		List<Long> ids2 = new ArrayList<>(0);
		Long id = 454l;
		//System.out.println(Arrays.equals(num[0],w));
		//System.out.println(ids.contains(id));
		//String s[] = "|Wechat|feixiaohao07|copy".split("\\|");
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

//		String units = "4#204.17";
//		String[] strings = units.split(",");
//		System.out.println(strings[0].indexOf("4#"));
//		if(strings.length >1) {
//			double d = Double.valueOf(strings[0].substring(strings[0].indexOf("4#")+2, strings[0].length()));
//			double dd = Double.valueOf(strings[1].substring(strings[1].indexOf("5#")+2, strings[1].length()));
//			System.out.println(d-dd);
//		}else {
//			System.out.println(strings[0].substring(0,1));
//			System.out.println(units.substring(2));
//		}
		
		String pwd = "zq19940726";
		 MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算md5函数 59b60bdb39cd2852efe51b21b27a69c4
        md.update(pwd.getBytes());
//        System.out.println(new BigInteger(1, md.digest()).toString(16));
        //s5W9YTY0heh4f25i4nSyKOqtkMn7Zco2pzBZS1T7uyA
        String head = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
        String content = "eyJleHAiOjE1NjI4OTM5NTUsInVzZXJfbmFtZSI6IjEyMDMyMjAiLCJqdGkiOiI3ODgzZTFmOS1hN2E4LTQ4ZTctYmQ0Ny1kOTYzYmNiODdkZWEiLCJjbGllbnRfaWQiOiJ4aF93ZWIiLCJzY29wZSI6WyJzZXJ2ZXIiXX0";
        byte[] decode = Base64.getDecoder().decode(head);
        byte[] decode2 = Base64.getDecoder().decode(content);
        System.out.println(new String(decode,"UTF-8")+","+new String(decode2,"UTF-8"));
//        String secret = "XHM@9e7eKW1xyj@!r&fy";
//        System.out.println(Tools.HMACSHA256(head+"."+"eyJpc3MiOiJuaW5naGFvLm5ldCIsImV4cCI6IjE0Mzg5NTU0NDUiLCJuYW1lIjoid2FuZ2hhbyIsImFkbWluIjp0cnVlfQ", "secret"));
        short a = 128;
        byte b = (byte)a;
        System.out.println(a+"**"+b);
	}
	
	
	

}
