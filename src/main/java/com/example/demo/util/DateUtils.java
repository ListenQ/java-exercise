package com.example.demo.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
	
	public static Date convertTimezone(Date sourceDate, String targetZoneId){
        return convertTimezone(sourceDate, TimeZone.getTimeZone(targetZoneId));
    }
	
	 public static Date convertTimezone(Date sourceDate, String sourceZoneId, String targetZoneId){
	        TimeZone sourceTimeZone=TimeZone.getTimeZone(sourceZoneId);
	        TimeZone targetTimeZone=TimeZone.getTimeZone(targetZoneId);

	        return convertTimezone(sourceDate, sourceTimeZone, targetTimeZone);
	 }
	 
	 public static Date convertTimezone(Date localDate, TimeZone targetTimezone){
	        return convertTimezone(localDate, TimeZone.getDefault(), targetTimezone);
	    }
	 public static Date convertTimezone(Date sourceDate, TimeZone sourceTimezone, TimeZone targetTimezone){


	        // targetDate - sourceDate=targetTimezone-sourceTimezone
	        // --->
	        // targetDate=sourceDate + (targetTimezone-sourceTimezone)


	        Calendar calendar=Calendar.getInstance();
	       // date.getTime() 为时间戳, 为格林尼治到系统现在的时间差,世界各个地方获取的时间戳是一样的,
	       // 格式化输出时,因为设置了不同的时区,所以输出不一样
	        long sourceTime=sourceDate.getTime(); 


	        calendar.setTimeZone(sourceTimezone);
	        calendar.setTimeInMillis(sourceTime);// 设置之后,calendar会计算各种filed对应的值,并保存

	        //获取源时区的到UTC的时区差
	        int sourceZoneOffset=calendar.get(Calendar.ZONE_OFFSET);


	        calendar.setTimeZone(targetTimezone);
	        calendar.setTimeInMillis(sourceTime);

	        int targetZoneOffset=calendar.get(Calendar.ZONE_OFFSET);
	        int targetDaylightOffset=calendar.get(Calendar.DST_OFFSET); // 夏令时


	        long targetTime=sourceTime+ (targetZoneOffset+targetDaylightOffset) -sourceZoneOffset;

	        return new Date(targetTime);  

	    }

}
