package com.example.demo.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test5 {
	
	private static final String middle = "0630";
	private static final String middleStart = "0901";
	private static final String annual = "1231";
	private static final String annualStart = "0331";
	
	
	public static void main(String[] args) {
		System.out.println(generate(6));
	}
	
	/**
	 * 根据当前日期开始往后生成年报日期
	 * @author zhangqi 
	 * @date 2019年10月30日-下午7:03:41
	 * @param count 生成多少个
	 * @return List<String>
	*/
	public static List<String> generate(int count) {
		String d = "2020-04-03";//DateUtil.dataFormat(new Date(), "yyyy-MM-dd");
		List<String> dates = new ArrayList<String>(count);
		//9.1号后查询当年中报为起始
		if(String.format("%s%s", d.split("-")[1],d.split("-")[2]).compareTo(middleStart)>0) {
			dates.add(String.format("%s%s", d.split("-")[0],middle));
			getYearReportDate(Integer.valueOf(d.split("-")[0]), middle,--count,dates);
		}//没到年报日期生成时间则以去年中报为起始
		else if(String.format("%s%s", d.split("-")[1],d.split("-")[2]).compareTo(annualStart) <0) {
			dates.add(String.format("%s%s", Integer.valueOf(d.split("-")[0]) -1,middle));
			getYearReportDate(Integer.valueOf(d.split("-")[0]) -1, middle,--count,dates);
		}
		//3.31号后查询去年年报为起始或者没到中报日期生成时间
		else if(String.format("%s%s", d.split("-")[1],d.split("-")[2]).compareTo(annualStart)>0) {
			dates.add(String.format("%s%s", Integer.valueOf(d.split("-")[0]) -1,annual));
			getYearReportDate(Integer.valueOf(d.split("-")[0]) -1, annual,--count,dates);
		}
		return dates;
	}
	
	
	/**
	 * 递归获取年报日期
	 * @author zhangqi 
	 * @date 2019年10月30日-下午7:03:01
	 * @param year
	 * @param dateStr
	 * @param count void
	*/
	private static void getYearReportDate(int year,String dateStr,int count,List<String> dates) {
		if(count <=0) {
			return;
		}
		//9.1号后查询当年中报为起始
		if(dateStr.compareTo(middleStart)>0 || dateStr.compareTo(annualStart) <0) {
			dateStr = middle;
			dates.add(String.format("%s%s", year,dateStr));
			getYearReportDate(year, dateStr,--count,dates);
		}//3.31号后查询去年年报为起始
		else if(dateStr.compareTo(annualStart)>0 || dateStr.compareTo(middleStart) <0) {
			dateStr = annual; --year;
			dates.add(String.format("%s%s", year,dateStr));
			getYearReportDate(year, dateStr,--count,dates);
		}
	}
	

}
