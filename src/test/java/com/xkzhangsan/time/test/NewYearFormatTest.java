package com.xkzhangsan.time.test;

import java.time.LocalDateTime;
import java.util.Date;

import com.xkzhangsan.time.LunarDate;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

public class NewYearFormatTest {

	public static void main(String[] args) {
		LocalDateTime localDateTime = LocalDateTime.of(2020, 1, 1, 0, 0);
		Date date = DateTimeConverterUtil.toDate(localDateTime);
		LunarDate lunarDate = LunarDate.from(localDateTime);
		System.out.println("=============================");
		System.out.println("Hello " + lunarDate.format());
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_CN_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_EN_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMM_FMT));
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_CN_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_EN_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_POINT_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_E_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDD_FMT));
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMM_FMT));
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_A_CN_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMMSS_FMT));//22
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSS_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMMSSSSS_FMT));		
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_SS_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_SS_CN_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_CN_FMT));
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_CN_FMT));
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_SS_FMT));
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HHMMSS_FMT));
		System.out.println("=============================");
	}
}
