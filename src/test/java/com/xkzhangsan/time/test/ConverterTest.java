package com.xkzhangsan.time.test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.xkzhangsan.time.converter.DateTimeConverterUtil;

public class ConverterTest {

	public static void main(String[] args) {
		System.out.println("==============Date Converter===============");
		Date date = new Date();
		System.out.println(DateTimeConverterUtil.toLocalDateTime(date));
		System.out.println(DateTimeConverterUtil.toLocalDate(date));
		System.out.println(DateTimeConverterUtil.toLocalTime(date));
		System.out.println(DateTimeConverterUtil.toInstant(date));
		
		System.out.println("==============LocalDateTime Converter===============");
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		System.out.println(DateTimeConverterUtil.toLocalDate(ldt));
		System.out.println(DateTimeConverterUtil.toLocalTime(ldt));
		System.out.println(DateTimeConverterUtil.toInstant(ldt));
		
		System.out.println("==============LocalDate Converter===============");
		LocalDate ld = LocalDate.now();
		System.out.println(ld);
		System.out.println(DateTimeConverterUtil.toDate(ld));
		System.out.println(DateTimeConverterUtil.toLocalDateTime(ld));
		System.out.println(DateTimeConverterUtil.toInstant(ld));
		
		System.out.println("==============Instant Converter===============");
		Instant instant = Instant.now();
		System.out.println(instant);
		System.out.println(DateTimeConverterUtil.toDate(instant));
		System.out.println(DateTimeConverterUtil.toLocalDateTime(instant));
		System.out.println(DateTimeConverterUtil.toLocalDate(instant));
		
	}
}
