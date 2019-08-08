package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat DEFAULT_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String dataFormat(Date date) {
        String dateString = DEFAULT_DATETIME_FORMAT.format(date);
        return dateString;
    }

    public static Date parseDate(String formatDate) throws ParseException {
        Date date = DEFAULT_DATETIME_FORMAT.parse(formatDate);
        return date;
    }


    public static Date getZeroTime() {
    	LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    	ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }
    
    public static Date getTomorrowZeroTime() {
    	LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().minusDays(-1), LocalTime.MIN);
    	ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

}
