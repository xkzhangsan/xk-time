package com.xkzhangsan.time.test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		System.out.println(zonedDateTime);
		System.out.println(DateTimeConverterUtil.toDate(zonedDateTime));
		System.out.println(DateTimeConverterUtil.toLocalDateTime(zonedDateTime));
		System.out.println(DateTimeConverterUtil.toLocalDate(zonedDateTime));
		System.out.println(DateTimeConverterUtil.toLocalTime(zonedDateTime));
		System.out.println(DateTimeConverterUtil.toInstant(zonedDateTime));
	}	
}
