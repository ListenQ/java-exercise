package com.example.demo.reptile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 测试爬虫
 * TestReptile<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年9月19日-下午8:13:41 <BR>
 * @version 1.0.0
 * 
 */
public class TestReptile{
	
	public static String strUrl = "https://www.ywn88.com/?controller=game&action=bonuscode&lotteryid=41&issuecount=30";
	
	public static void main(String[] args) throws Exception {
		URL url = new URL(strUrl);
		URLConnection conn=url.openConnection();
		InputStream is=conn.getInputStream();
		System.out.println(conn.getContentEncoding());
		
		BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
		String line=null;
		while((line=br.readLine())!=null){
			System.out.println(line);
		}
		br.close();
	}
	
}
