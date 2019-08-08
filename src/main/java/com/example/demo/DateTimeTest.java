package com.example.demo;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeTest {

	private static final DateTimeFormatter DEFAULT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	
	public static void main(String[] args) throws ParseException {
		 //System.out.println(parseDate("2019-08-07 12:23:54"));
		 System.out.println(DateUtil.parseDate("2019-08-07 12:23:54"));
		 
	}
	
	public static Date parseDate(String formatDate) throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.parse(formatDate,DEFAULT_DATETIME_FORMAT);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return  Date.from(instant);
    }
	
	public static String dataFormat(Date date) {
    	Instant instant = date.toInstant();
    	LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DEFAULT_DATETIME_FORMAT);
    }
	
	public static Date getZeroTime() {
    	LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().minusDays(-1), LocalTime.MIN);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
}
