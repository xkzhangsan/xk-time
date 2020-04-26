package com.xkzhangsan.time.test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.Test;

import com.xkzhangsan.time.converter.DateTimeConverterUtil;

public class ConverterTest {
	
	@Test
	public void dateConverterTest(){
		System.out.println("===================dateConverterTest=====================");
		Date date = new Date();
		System.out.println(DateTimeConverterUtil.toLocalDateTime(date));
		System.out.println(DateTimeConverterUtil.toLocalDate(date));
		System.out.println(DateTimeConverterUtil.toLocalTime(date));
		System.out.println(DateTimeConverterUtil.toInstant(date));
	}
	
	@Test
	public void localDateTimeConverterTest(){
		System.out.println("===================localDateTimeConverterTest=====================");
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		System.out.println(DateTimeConverterUtil.toDate(ldt));
		System.out.println(DateTimeConverterUtil.toLocalDate(ldt));
		System.out.println(DateTimeConverterUtil.toLocalTime(ldt));
		System.out.println(DateTimeConverterUtil.toInstant(ldt));
		System.out.println(DateTimeConverterUtil.toZonedDateTime(ldt));
	}
	
	@Test
	public void localDateConverterTest(){
		System.out.println("===================localDateConverterTest=====================");
		LocalDate ld = LocalDate.now();
		System.out.println(ld);
		System.out.println(DateTimeConverterUtil.toDate(ld));
		System.out.println(DateTimeConverterUtil.toLocalDateTime(ld));
		System.out.println(DateTimeConverterUtil.toInstant(ld));
	}
	
	@Test
	public void localTimeConverterTest(){
		System.out.println("===================localTimeConverterTest=====================");
		LocalTime lt = LocalTime.now();
		System.out.println(lt);
		System.out.println(DateTimeConverterUtil.toDate(lt));
		System.out.println(DateTimeConverterUtil.toLocalDateTime(lt));
		System.out.println(DateTimeConverterUtil.toLocalTime(lt));
		System.out.println(DateTimeConverterUtil.toInstant(lt));
	}	

	
	@Test
	public void instantConverterTest(){
		System.out.println("===================instantConverterTest=====================");
		Instant instant = Instant.now();
		System.out.println(instant);
		System.out.println(DateTimeConverterUtil.toDate(instant));
		System.out.println(DateTimeConverterUtil.toLocalDateTime(instant));
		System.out.println(DateTimeConverterUtil.toLocalDate(instant));
	}
	
	@Test
	public void zonedDateTimeConverterTest(){
		System.out.println("===================zonedDateTimeConverterTest=====================");
		System.out.println("===================ToOther=====================");
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		System.out.println(zonedDateTime);
		System.out.println(DateTimeConverterUtil.toDate(zonedDateTime));
		System.out.println(DateTimeConverterUtil.toLocalDateTime(zonedDateTime));
		System.out.println(DateTimeConverterUtil.toLocalDate(zonedDateTime));
		System.out.println(DateTimeConverterUtil.toLocalTime(zonedDateTime));
		System.out.println(DateTimeConverterUtil.toInstant(zonedDateTime));
		System.out.println("===================toZonedDateTime=====================");
		System.out.println(zonedDateTime);
		System.out.println(DateTimeConverterUtil.toZonedDateTime(new Date()));
		System.out.println(DateTimeConverterUtil.toZonedDateTime(LocalDateTime.now()));
		System.out.println(DateTimeConverterUtil.toZonedDateTime(LocalDate.now()));
		System.out.println(DateTimeConverterUtil.toZonedDateTime(LocalTime.now()));
		System.out.println(DateTimeConverterUtil.toZonedDateTime(Instant.now()));
		//Asia/Shanghai
		System.out.println(DateTimeConverterUtil.toZonedDateTime(LocalDateTime.now(), "Asia/Shanghai"));
	}
	
	@Test
	public void yearMonthConverterTest(){
		System.out.println("===================yearMonthConverterTest=====================");
		Date date = new Date();
		System.out.println(date);
		YearMonth yearMonth = DateTimeConverterUtil.toYearMonth(date);
		System.out.println(yearMonth);
		
		Date date1 = DateTimeConverterUtil.toDate(yearMonth, 15);
		//转换为当月第一天
		Date date2 = DateTimeConverterUtil.toDateStartOfMonth(yearMonth);
		//转换为当月最后一天
		Date date3 = DateTimeConverterUtil.toDateEndOfMonth(yearMonth);
		System.out.println(date1);
		System.out.println(date2);
		System.out.println(date3);
		
		LocalDate LocalDate1 = DateTimeConverterUtil.toLocalDate(yearMonth, 15);
		//转换为当月第一天
		LocalDate LocalDate2 = DateTimeConverterUtil.toLocalDateStartOfMonth(yearMonth);
		//转换为当月最后一天
		LocalDate LocalDate3 = DateTimeConverterUtil.toLocalDateEndOfMonth(yearMonth);
		System.out.println(LocalDate1);
		System.out.println(LocalDate2);
		System.out.println(LocalDate3);
	}
	
	/**
	 * 时间戳转换测试
	 */
	@Test
	public void epochMilliConverterTest(){
		System.out.println("===================epochMilliConverterTest=====================");
		Date date = new Date();
		long epochMilli = date.getTime();
		System.out.println("epochMilli:"+epochMilli);
		System.out.println("===================ToOther=====================");
		System.out.println(DateTimeConverterUtil.toDate(epochMilli));
		System.out.println(DateTimeConverterUtil.toLocalDateTime(epochMilli));
		System.out.println(DateTimeConverterUtil.toLocalDate(epochMilli));
		System.out.println(DateTimeConverterUtil.toInstant(epochMilli));
		System.out.println(DateTimeConverterUtil.toZonedDateTime(epochMilli));
		System.out.println("===================toEpochMilli=====================");
		System.out.println(DateTimeConverterUtil.toEpochMilli(new Date()));
		System.out.println(DateTimeConverterUtil.toEpochMilli(LocalDateTime.now()));
		// 另一种方式： +8 时区
		System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
		System.out.println(DateTimeConverterUtil.toEpochMilli(LocalDate.now()));
		System.out.println(DateTimeConverterUtil.toEpochMilli(Instant.now()));
		System.out.println(DateTimeConverterUtil.toEpochMilli(ZonedDateTime.now()));
	}
	
	@Test
	public void timestampConverterTest(){
		System.out.println("===================timestampConverterTest=====================");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		long epochMilli = timestamp.getTime();
		System.out.println("epochMilli:"+epochMilli);
		System.out.println("===================ToOther=====================");
		System.out.println(DateTimeConverterUtil.toLocalDateTime(timestamp));
		System.out.println(DateTimeConverterUtil.toInstant(timestamp));
		System.out.println(DateTimeConverterUtil.toEpochMilli(timestamp));
		System.out.println("===================toEpochMilli=====================");
		System.out.println(DateTimeConverterUtil.toTimestamp(new Date()));
		System.out.println(DateTimeConverterUtil.toTimestamp(LocalDateTime.now()));
		System.out.println(DateTimeConverterUtil.toTimestamp(Instant.now()));
		System.out.println(DateTimeConverterUtil.toTimestamp(epochMilli));
	}
}
