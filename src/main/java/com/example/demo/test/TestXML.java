package com.example.demo.test;

import java.util.regex.Pattern;

import com.example.demo.util.XmlUtils;
import com.example.demo.vo.ClientBalanceRequest;
import com.example.demo.vo.GTSRequestVo;

/**
 *
 * @author benky
 */
public class TestXML {

	private static final Pattern pattern = Pattern.compile("</>|<>|\r|\\s+/g");
	private static final Pattern pattern_2 = Pattern.compile("\\s*|\t|\r|\n"); 
	
	public static void main(String[] args) throws Exception {
		
		ClientBalanceRequest request = new ClientBalanceRequest();
		request.setUserCode("ZHANGSAN");
		request.setClientAccCode("ZQ01ZQ01");
		request.setPassword("123456");
		
		GTSRequestVo<ClientBalanceRequest> gts = new GTSRequestVo<>();
		gts.setBody(request);
		gts.setRootType("client_balance");
		gts.setRootMsgnum(""+System.currentTimeMillis());
		
		String xml = XmlUtils.toXml(gts);
		System.out.println(pattern.matcher(xml).replaceAll(""));
		
	}

}
