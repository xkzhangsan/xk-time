package com.xkzhangsan.time.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

public class CalculatorTest {

	public static void main(String[] args) {
		System.out.println("==============Date Calculator===============");
		Date date = new Date();
		System.out.println(date);
		System.out.println(DateTimeCalculatorUtil.plus(date, ChronoUnit.DAYS, 1));
		System.out.println(DateTimeCalculatorUtil.minus(date, ChronoUnit.DAYS, 3));
		System.out.println(DateTimeCalculatorUtil.setField(date, ChronoField.DAY_OF_MONTH, 15));
		
		System.out.println("==============LocalDateTime Calculator===============");
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		System.out.println(DateTimeCalculatorUtil.plus(ldt, ChronoUnit.DAYS, 1));
		System.out.println(DateTimeCalculatorUtil.minus(ldt, ChronoUnit.DAYS, 3));
		System.out.println(DateTimeCalculatorUtil.setField(ldt, ChronoField.DAY_OF_MONTH, 15));
		
		System.out.println("=============================");
		// 从1开始
		System.out.println(DateTimeCalculatorUtil.getMonth(date));
		// 从1开始，对应从周一开始
		System.out.println(DateTimeCalculatorUtil.getDayOfWeek(date));
		
		LocalDateTime ldt2 = DateTimeFormatterUtil.parseToLocalDateTime("2019-12-01 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT);
		LocalDateTime ldt3 = DateTimeFormatterUtil.parseToLocalDateTime("2019-12-02 13:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT);
		System.out.println(DateTimeCalculatorUtil.betweenDays(ldt2, ldt3));// 精确计算天数，不满24小时，输出0
		
		LocalDate ld1 = DateTimeFormatterUtil.parseToLocalDateTime("2019-12-01 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT).toLocalDate();
		LocalDate ld2 = DateTimeFormatterUtil.parseToLocalDateTime("2019-12-02 13:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT).toLocalDate();
		System.out.println(DateTimeCalculatorUtil.betweenDays(ld1, ld2));// 粗略计算天数，只计算日期部分，不满24小时也算1天，输出1
	}
}
