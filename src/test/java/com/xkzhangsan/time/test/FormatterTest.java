package com.xkzhangsan.time.test;

import java.time.LocalDateTime;
import java.util.Date;

import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

public class FormatterTest {

	public static void main(String[] args) {
		System.out.println("==============Date Formatter===============");
		Date date = new Date();
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_SS_FMT));//1
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HHMMSS_FMT));//2
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_CN_FMT));//3
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_CN_FMT));//4
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_FMT));//5

		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_SS_CN_FMT));//6
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_FMT));//7
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_CN_FMT));//8
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_CN_FMT));//9
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_EN_FMT));//10
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_FMT));//11
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_FMT));//12
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_FMT));//13
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT));//14
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSS_FMT));//15
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_POINT_FMT));//16
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_EN_FMT));//17
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_FMT));//18
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMM_FMT));//19
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDD_FMT));//20
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMM_FMT));//21
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMMSS_FMT));//22
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMMSSSSS_FMT));//23		
		
		System.out.println("==============LocalDateTime Formatter===============");		
		LocalDateTime lss = LocalDateTime.now();
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.HH_MM_SS_FMT));//1
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.HHMMSS_FMT));//2
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.MM_DD_CN_FMT));//3
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.MM_DD_HH_MM_CN_FMT));//4
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.MM_DD_HH_MM_FMT));//5
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.MM_DD_HH_MM_SS_CN_FMT));//6
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_FMT));//7
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_CN_FMT));//8
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_CN_FMT));//9
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_EN_FMT));//10
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_FMT));//11
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_FMT));//12
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_FMT));//13
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT));//14
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSS_FMT));//15
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_POINT_FMT));//16
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_EN_FMT));//17
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_FMT));//18
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYYMM_FMT));//19
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYYMMDD_FMT));//20
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYYMMDDHHMM_FMT));//21
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYYMMDDHHMMSS_FMT));//22
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYYMMDDHHMMSSSSS_FMT));//23
		
		System.out.println("=============parseToDate================");
		System.out.println(DateTimeFormatterUtil.parseToDate("2019年12月01日 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_FMT));
		System.out.println(DateTimeFormatterUtil.parseToDate("2019-12-01 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT));
		System.out.println("=============parseToLocalDateTime================");
		System.out.println(DateTimeFormatterUtil.parseToLocalDateTime("2019年12月01日 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_FMT));
		System.out.println(DateTimeFormatterUtil.parseToLocalDateTime("2019-12-01 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT));
		System.out.println("=============parseToInstant================");
		System.out.println(DateTimeFormatterUtil.parseToInstant("2019年12月01日 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_FMT));
	}
}
