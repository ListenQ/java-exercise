package com.example.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.cp.TestDto;

public class Test7 {
	public static void main(String[] args) {
//		String code = "10850";
//		System.out.println(code.compareTo("00001") >=0 && code.compareTo("03999")<=0);
//		System.out.println(code.startsWith("08", 0));
//		
//		BigDecimal b = new BigDecimal("101");
//		boolean flag = (BigDecimal.ONE.compareTo(b) <=0 && BigDecimal.TEN.compareTo(b)>0);
//		boolean flag2 = (BigDecimal.TEN.compareTo(b) <=0 && new BigDecimal("100").compareTo(b)>=0);
//		boolean flag3 = new BigDecimal("100").compareTo(b) < 0;
//		System.out.println(flag+"/"+flag2+"/"+flag3);
		
		List<TestDto> list = new ArrayList<TestDto>();
		list.add(new TestDto("嘻嘻", new Date(),new BigDecimal("20")));
		list.add(new TestDto("哈哈", new Date(),new BigDecimal("25")));
		list.add(new TestDto("哦哦", new Date(),null));
		list.add(new TestDto("逗逼", new Date(),new BigDecimal("18")));
		list.add(new TestDto("手动阀", new Date(),null));
		list.add(new TestDto("阿斯蒂芬", new Date(),new BigDecimal("-102.0")));
		list.add(new TestDto("挨过多少", new Date(),null));
		list.add(new TestDto("阀", new Date(),BigDecimal.ZERO));
		//list.stream().filter(str -> str!=null && (filter(str))).collect(Collectors.toList());
		
//		String msg = "[{\"diffRate\":0.06,\"industryCode\":\"0301\",\"industryDiffRate\":0.01948,\"industryName\":\"煤炭\",\"last\":1.280,\"name\":\"南方能源\"},{\"diffRate\":0.27,\"industryCode\":\"0902\",\"industryDiffRate\":0.01767,\"industryName\":\"地产\",\"last\":3.610,\"name\":\"卡森国际\"},{\"diffRate\":0.13,\"industryCode\":\"0901\",\"industryDiffRate\":0.01571,\"industryName\":\"建筑\",\"last\":2.130,\"name\":\"WAC HOLDINGS\"}]";
//		String msg2 = "{\"334407776627601408\":{\"materialsGuid\":\"334407776627601408\",\"materialsName\":\"手套\",\"materialsNum\":\"0\",\"materialsTime\":\"0\"},\r\n" + 
//				"				\"335383233539878912\":{\"materialsGuid\":\"335383233539878912\",\"materialsName\":\"手套2\",\"materialsNum\":\"0\",\"materialsTime\":\"0\"}}";
//		JSONArray parseObject = JSON.parseArray(msg);
//		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(msg);
//		System.out.println(parseObject);
//		System.out.println(json);
		
		//TODO nullsFirst + reversed => 降序   而且 把为null置为最后
		//List<TestDto> collect = list.stream().sorted(Comparator.comparing(TestDto::getNumber,Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
		//TODO nullsLast             => 升序    而且把为null置为最后
		//List<TestDto> collect2 = list.stream().sorted(Comparator.comparing(TestDto::getNumber,Comparator.nullsLast(BigDecimal::compareTo))).collect(Collectors.toList());
		
		//TODO nullsLast             => 降序    而且把为null当为0
		List<TestDto> collect3 = list.stream().sorted(compare()).collect(Collectors.toList());

		collect3.forEach(System.err::println);
	
		
//		list.sort(new Comparator<TestDto>() {
//	           @Override
//	           public int compare(TestDto s1, TestDto s2) {
//	        	   
//	        	   BigDecimal temp1 = s1.getNumber();
//	        	   BigDecimal temp2 = s2.getNumber();
//	        	   if(temp1 == null) {
//	        		   s1.setNumber(BigDecimal.ZERO);
//	        	   }
//	        	   if(temp2 == null) {
//	        		   s2.setNumber(BigDecimal.ZERO);
//	        	   }
//	        	   
//	        	   Integer result = s1.getNumber().compareTo(s2.getNumber());
//	        	   s1.setNumber(temp1);
//	        	   s2.setNumber(temp2);
//	        	   
//	        	   return result;
//	             // return s1.getNumber().compareTo(s2.getNumber());
//	           }
//	        });
		
		
		
//		System.err.println(list);
		
	}
	
	
	private static Comparator<TestDto> compare(){
		return new Comparator<TestDto>() {
	           @Override
	           public int compare(TestDto s1, TestDto s2) {
	        	   
	        	   BigDecimal temp1 = s1.getNumber();
	        	   BigDecimal temp2 = s2.getNumber();
	        	   if(temp1 == null) {
	        		   s1.setNumber(BigDecimal.ZERO);
	        	   }
	        	   if(temp2 == null) {
	        		   s2.setNumber(BigDecimal.ZERO);
	        	   }
	        	   
	        	   int result = s1.getNumber().compareTo(s2.getNumber());
	        	   s1.setNumber(temp1);
	        	   s2.setNumber(temp2);
	        	   return result;
	           }
	        };		
	}
	
	
	
    private static boolean filter(String str) {
    	System.out.println("来了"+str.concat("pp"));
    	return true;
    }
    
    // 使用 java 7 排序
    private static void sortUsingJava7(List<String> names){   
       Collections.sort(names, new Comparator<String>() {
          @Override
          public int compare(String s1, String s2) {
             return s1.compareTo(s2);
          }
       });
    }
    
    private static void sortUsing(List<TestDto> names){   
        Collections.sort(names, new Comparator<TestDto>() {
           @Override
           public int compare(TestDto s1, TestDto s2) {
        	   if( s1.getNumber() == null) {
        		   return 0;
        	   }
        	   if(s2.getNumber() == null) {
        		   return 0;
        	   }
              return s1.getNumber().compareTo(s2.getNumber());
           }
        });
     }
    
    

}
