package com.example.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.sf.json.util.JSONUtils;

public class Test7 {
	public static void main(String[] args) {
		String code = "10850";
		System.out.println(code.compareTo("00001") >=0 && code.compareTo("03999")<=0);
		System.out.println(code.startsWith("08", 0));
		
		BigDecimal b = new BigDecimal("101");
		boolean flag = (BigDecimal.ONE.compareTo(b) <=0 && BigDecimal.TEN.compareTo(b)>0);
		boolean flag2 = (BigDecimal.TEN.compareTo(b) <=0 && new BigDecimal("100").compareTo(b)>=0);
		boolean flag3 = new BigDecimal("100").compareTo(b) < 0;
		System.out.println(flag+"/"+flag2+"/"+flag3);
		
		List<String> list = new ArrayList<>();
		list.add("12");
		list.add("54");
		list.add(null);
		list.add("12");
		list.stream().filter(str -> str!=null && (filter(str))).collect(Collectors.toList());
		
		String msg = "[{\"diffRate\":0.06,\"industryCode\":\"0301\",\"industryDiffRate\":0.01948,\"industryName\":\"煤炭\",\"last\":1.280,\"name\":\"南方能源\"},{\"diffRate\":0.27,\"industryCode\":\"0902\",\"industryDiffRate\":0.01767,\"industryName\":\"地产\",\"last\":3.610,\"name\":\"卡森国际\"},{\"diffRate\":0.13,\"industryCode\":\"0901\",\"industryDiffRate\":0.01571,\"industryName\":\"建筑\",\"last\":2.130,\"name\":\"WAC HOLDINGS\"}]";
		String msg2 = "{\"334407776627601408\":{\"materialsGuid\":\"334407776627601408\",\"materialsName\":\"手套\",\"materialsNum\":\"0\",\"materialsTime\":\"0\"},\r\n" + 
				"				\"335383233539878912\":{\"materialsGuid\":\"335383233539878912\",\"materialsName\":\"手套2\",\"materialsNum\":\"0\",\"materialsTime\":\"0\"}}";
		JSONArray parseObject = JSON.parseArray(msg);
		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(msg);
		System.out.println(parseObject);
		System.out.println(json);
		
		list.stream().sorted(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		
	}
	
    private static boolean filter(String str) {
    	System.out.println("来了"+str.concat("pp"));
    	return true;
    }
    
    // 使用 java 7 排序
    private void sortUsingJava7(List<String> names){   
       Collections.sort(names, new Comparator<String>() {
          @Override
          public int compare(String s1, String s2) {
             return s1.compareTo(s2);
          }
       });
    }

}
