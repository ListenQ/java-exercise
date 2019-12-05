package com.example.demo.test;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTimeTest {
	

	private static final DateTimeFormatter DEFAULT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	private static final String s = new String("123");
	private static final String ss;
	static {
		ss = new String("465");
	}

	public static void main(String[] args) throws ParseException {
		// System.out.println(parseDate("2019-08-07 12:23:54"));
//		 System.out.println(DateUtil.parseDate("2019-08-07 12:23:54"));
		Calendar openTimeAM = Calendar.getInstance();
		openTimeAM.set(Calendar.HOUR_OF_DAY, 9);
		openTimeAM.set(Calendar.MINUTE, 30);
		openTimeAM.set(Calendar.SECOND, 0);
		openTimeAM.set(Calendar.MILLISECOND, 0);
//	        System.out.println(openTimeAM.getTime());
//	        System.out.println(getNowTime(9, 30, 0, 0));
//		System.out.println(new Date());
//		System.out.println(parseDate(LocalDateTime.now()));
//		System.out.println(getlastTransMinute(DateUtil.dataFormat(DateUtil.parseDate("2019-08-12 13:00:00"))));
		
		String date = "2019-10-25";
		String time = "10:14";
//		System.out.println(DateUtil.parseDate(String.format("%s %s", date,time),"yyyy-MM-dd HH:mm"));
		
		System.out.println(DateTimeTest.s.hashCode());
		System.out.println(DateTimeTest.s.hashCode());
	}

	public static Date parseDate(String formatDate) throws ParseException {
		LocalDateTime localDateTime = LocalDateTime.parse(formatDate, DEFAULT_DATETIME_FORMAT);
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
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

	public static Date getNowTime(int hours, int minute, int seconds, int milliSecond) {
		LocalTime localTime = LocalTime.of(hours, minute, seconds, milliSecond);
		LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), localTime);
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

	public static Date parseDate(LocalDateTime localDateTime) {
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

	private static String getlastTransMinute(String minute) throws ParseException {
		Date lastMinuteDate = DateUtil.parseDate(minute);
		LocalDateTime localDateTime = DateUtil.parseLocalDateTime(lastMinuteDate);

		// 如果是下午开盘就获取上午收盘的时间的数据
		if (minute.substring(11, 16).equals("13:00")) {
			localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 30, 0, 0));
		} else {
			localDateTime = localDateTime.minusMinutes(1);
		}
		return DateUtil.dateFormat(localDateTime);
	}
}
