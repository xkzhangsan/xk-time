package com.xkzhangsan.time.test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;

import com.xkzhangsan.time.converter.DateTimeConverterUtil;

public class ConverterTest {
	
	@Test
	public void dateConverterTest(){
		Date date = new Date();
		System.out.println(DateTimeConverterUtil.toLocalDateTime(date));
		System.out.println(DateTimeConverterUtil.toLocalDate(date));
		System.out.println(DateTimeConverterUtil.toLocalTime(date));
		System.out.println(DateTimeConverterUtil.toInstant(date));
	}
	
	@Test
	public void localDateTimeConverterTest(){
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		System.out.println(DateTimeConverterUtil.toDate(ldt));
		System.out.println(DateTimeConverterUtil.toLocalDate(ldt));
		System.out.println(DateTimeConverterUtil.toLocalTime(ldt));
		System.out.println(DateTimeConverterUtil.toInstant(ldt));
	}
	
	@Test
	public void localDateConverterTest(){
		LocalDate ld = LocalDate.now();
		System.out.println(ld);
		System.out.println(DateTimeConverterUtil.toDate(ld));
		System.out.println(DateTimeConverterUtil.toLocalDateTime(ld));
		System.out.println(DateTimeConverterUtil.toInstant(ld));
	}

	
	@Test
	public void instantConverterTest(){
		Instant instant = Instant.now();
		System.out.println(instant);
		System.out.println(DateTimeConverterUtil.toDate(instant));
		System.out.println(DateTimeConverterUtil.toLocalDateTime(instant));
		System.out.println(DateTimeConverterUtil.toLocalDate(instant));
	}
}
