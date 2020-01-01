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
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

public class CalculatorTest {
	
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
