package com.example.demo;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeTest {

	private static DateTimeFormatter DEFAULT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	
	public static void main(String[] args) throws ParseException {
		 System.out.println(parseDate("2019-08-07 12:23:54"));
		 System.out.println(dataFormat(new Date()));
		 
	}
	
	public static Date parseDate(String formatDate) throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.parse(formatDate,DEFAULT_DATETIME_FORMAT);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return  Date.from(instant);
    }
	
	public static String dataFormat(Date date) {
    	Instant instant = date.toInstant();
    	ZoneId zone = ZoneId.systemDefault();
    	LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.format(DEFAULT_DATETIME_FORMAT);
    }
	
	public static Date getZeroTime() {
    	LocalDateTime localDateTime = LocalDateTime.now();
    	localDateTime.minusHours(1);
    	localDateTime.minusMinutes(0);
    	localDateTime.minusSeconds(0);
    	localDateTime.minusNanos(0);
    	ZoneId zone = ZoneId.systemDefault();
    	Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }
}
