package com.xkzhangsan.time.test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.Test;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

public class CalculatorTest {
	
	@Test
	public void dateCalculatorGetTest(){
		Date date = new Date();
		System.out.println(date);
		System.out.println(DateTimeConverterUtil.toLocalDateTime(date));
		System.out.println(DateTimeCalculatorUtil.getYear(date));
		System.out.println(DateTimeCalculatorUtil.getMonth(date));
		System.out.println(DateTimeCalculatorUtil.getDayOfMonth(date));
		System.out.println(DateTimeCalculatorUtil.getHour(date));
		System.out.println(DateTimeCalculatorUtil.getMinute(date));
		System.out.println(DateTimeCalculatorUtil.getSecond(date));
	}
	
	@Test
	public void dateCalculatorPlusTest(){
		Date date = new Date();
		System.out.println(date);
		System.out.println(DateTimeConverterUtil.toLocalDateTime(date));
		
		System.out.println(DateTimeCalculatorUtil.plusYears(date, 1));
		System.out.println(DateTimeCalculatorUtil.plusMonths(date, 1));
		System.out.println(DateTimeCalculatorUtil.plusWeeks(date, 1));
		System.out.println(DateTimeCalculatorUtil.plusDays(date, 1));
		System.out.println(DateTimeCalculatorUtil.plusHours(date, 1));
		System.out.println(DateTimeCalculatorUtil.plusMinutes(date, 1));
		System.out.println(DateTimeCalculatorUtil.plusSeconds(date, 1));
	}
	
	@Test
	public void dateCalculatorMinusTest(){
		Date date = new Date();
		System.out.println(date);
		System.out.println(DateTimeConverterUtil.toLocalDateTime(date));
		
		System.out.println(DateTimeCalculatorUtil.minusYears(date, 1));
		System.out.println(DateTimeCalculatorUtil.minusMonths(date, 1));
		System.out.println(DateTimeCalculatorUtil.minusWeeks(date, 1));
		System.out.println(DateTimeCalculatorUtil.minusDays(date, 1));
		System.out.println(DateTimeCalculatorUtil.minusHours(date, 1));
		System.out.println(DateTimeCalculatorUtil.minusMinutes(date, 1));
		System.out.println(DateTimeCalculatorUtil.minusSeconds(date, 1));
	}
	
	@Test
	public void dateCalculatorPlusTest2(){
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		System.out.println(DateTimeCalculatorUtil.plusYears(ldt, 1));
		System.out.println(DateTimeCalculatorUtil.plusMonths(ldt, 1));
		System.out.println(DateTimeCalculatorUtil.plusWeeks(ldt, 1));
		System.out.println(DateTimeCalculatorUtil.plusDays(ldt, 1));
		System.out.println(DateTimeCalculatorUtil.plusHours(ldt, 1));
		System.out.println(DateTimeCalculatorUtil.plusMinutes(ldt, 1));
		System.out.println(DateTimeCalculatorUtil.plusSeconds(ldt, 1));
	}
	
	@Test
	public void dateCalculatorMinusTest2(){
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		System.out.println(DateTimeCalculatorUtil.minusYears(ldt, 1));
		System.out.println(DateTimeCalculatorUtil.minusMonths(ldt, 1));
		System.out.println(DateTimeCalculatorUtil.minusWeeks(ldt, 1));
		System.out.println(DateTimeCalculatorUtil.minusDays(ldt, 1));
		System.out.println(DateTimeCalculatorUtil.minusHours(ldt, 1));
		System.out.println(DateTimeCalculatorUtil.minusMinutes(ldt, 1));
		System.out.println(DateTimeCalculatorUtil.minusSeconds(ldt, 1));
	}
	
	@Test
	public void dateCalculatorWithTest(){
		Date date = new Date();
		System.out.println(date);
		System.out.println(DateTimeConverterUtil.toLocalDateTime(date));
		System.out.println(DateTimeCalculatorUtil.getDayOfYear(date));
		
		System.out.println(DateTimeCalculatorUtil.withYear(date, 2021));
		System.out.println(DateTimeCalculatorUtil.withMonth(date, 3));
		System.out.println(DateTimeCalculatorUtil.withDayOfMonth(date, 6));
		System.out.println(DateTimeCalculatorUtil.withDayOfYear(date, 37));
		System.out.println(DateTimeCalculatorUtil.withHour(date, 17));
		System.out.println(DateTimeCalculatorUtil.withMinute(date, 30));
		System.out.println(DateTimeCalculatorUtil.withSecond(date, 30));
	}
	
	@Test
	public void dateCalculatorWithTest2(){
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		System.out.println(ldt.getDayOfYear());
		
		System.out.println(DateTimeCalculatorUtil.withYear(ldt, 2021));
		System.out.println(DateTimeCalculatorUtil.withMonth(ldt, 3));
		System.out.println(DateTimeCalculatorUtil.withDayOfMonth(ldt, 6));
		System.out.println(DateTimeCalculatorUtil.withDayOfYear(ldt, 37));
		System.out.println(DateTimeCalculatorUtil.withHour(ldt, 17));
		System.out.println(DateTimeCalculatorUtil.withMinute(ldt, 30));
		System.out.println(DateTimeCalculatorUtil.withSecond(ldt, 30));
	}	
	
	@Test
	public void dateCalculatorTest(){
		Date date = new Date();
		System.out.println(date);
		System.out.println(DateTimeCalculatorUtil.plusDays(date, 1));
		System.out.println(DateTimeCalculatorUtil.plus(date, ChronoUnit.DAYS, 1));
		System.out.println(DateTimeCalculatorUtil.minus(date, ChronoUnit.DAYS, 3));
		System.out.println(DateTimeCalculatorUtil.with(date, ChronoField.DAY_OF_MONTH, 15));
		//获取月份，从1开始
		System.out.println(DateTimeCalculatorUtil.getMonth(date));
		// 获取星期的值，从1开始，对应从周一开始
		System.out.println(DateTimeCalculatorUtil.getDayOfWeek(date));
		// 下一个工作日
		System.out.println(DateTimeCalculatorUtil.nextWorkDay(date));
	}
	
	@Test
	public void localDateTimeCalculatorTest(){
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		System.out.println(DateTimeCalculatorUtil.plusDays(ldt, 1));
		System.out.println(DateTimeCalculatorUtil.plus(ldt, ChronoUnit.DAYS, 1));
		System.out.println(DateTimeCalculatorUtil.minus(ldt, ChronoUnit.DAYS, 3));
		System.out.println(DateTimeCalculatorUtil.with(ldt, ChronoField.DAY_OF_MONTH, 15));
		//获取月份，从1开始
		System.out.println(ldt.getMonthValue());
		// 获取星期的值，从1开始，对应从周一开始
		System.out.println(ldt.getDayOfWeek());
		// 下一个工作日
		System.out.println(DateTimeCalculatorUtil.nextWorkDay(ldt));
	}
	
	@Test
	public void durationBetween(){
		LocalDateTime ldt2 = DateTimeFormatterUtil.parseToLocalDateTime("2019-12-01 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT);
		LocalDateTime ldt3 = DateTimeFormatterUtil.parseToLocalDateTime("2019-12-02 13:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT);
		Duration d = DateTimeCalculatorUtil.durationBetween(ldt2, ldt3);
		System.out.println("days:"+d.toDays());
	}
	
	@Test
	public void periodBetween(){
		LocalDate ld1 = DateTimeFormatterUtil.parseToLocalDateTime("2019-12-01 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT).toLocalDate();
		LocalDate ld2 = DateTimeFormatterUtil.parseToLocalDateTime("2019-12-02 13:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT).toLocalDate();
		Period p = DateTimeCalculatorUtil.periodBetween(ld1, ld2);
		System.out.println("days:"+p.getDays());
	}
}
