package com.example.demo.test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

	private static final DateTimeFormatter DEFAULT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String dataFormat(Date date) {
    	Instant instant = date.toInstant();
    	LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DEFAULT_DATETIME_FORMAT);
    }
    
    public static String dataFormat(Date date, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return localDateTime.format(dateTimeFormatter);
    }

    public static Date parseDate(String formatDate){
    	LocalDateTime localDateTime = LocalDateTime.parse(formatDate,DEFAULT_DATETIME_FORMAT);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
    
    public static Date parseDate(LocalDateTime localDateTime) {
    	Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    	return Date.from(instant);
    }
    
    public static String dateFormat(LocalDateTime localDateTime) {
    	Date date = parseDate(localDateTime);
    	return dataFormat(date);
    }
    
    public static Date parseDate(String formatDate, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.parse(formatDate, dateTimeFormatter);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
    
    public static LocalDateTime parseLocalDateTime(Date date) {
    	return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date getZeroTime() {
    	LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
    
    public static Date getTomorrowZeroTime() {
    	LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().minusDays(-1), LocalTime.MIN);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
    
    public static Date getNowTime(int hours,int minute,int seconds,int milliSecond) {
		LocalTime localTime = LocalTime.of(hours,minute,seconds,milliSecond);
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(),localTime);
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
    
}
