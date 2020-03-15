package com.xkzhangsan.time.test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.enums.ZoneIdEnum;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

public class CalculatorTest {
	
	/**
	 * 获取时间年月日时分秒
	 */
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
		System.out.println(DateTimeCalculatorUtil.getDate(2020, 2, 27));
	}
	
	/**
	 * date年月日时分秒增加
	 */
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
	
	/**
	 * date年月日时分秒减少
	 */
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
	
	/**
	 * LocalDateTime年月日时分秒增加
	 */
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
	
	/**
	 * LocalDateTime年月日时分秒减少
	 */
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
	
	/**
	 * Date年月日时分秒属性值修改
	 */
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
	
	/**
	 * LocalDateTime年月日时分秒属性值修改
	 */
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
	
	/**
	 * 使用Period比较2个LocalDate
	 */
	@Test
	public void dateCalculatorPeriodBetweenTest(){
		LocalDate localDate = LocalDate.now();
		LocalDate localDate2 = LocalDate.of(2021, 3, 7);
		System.out.println(localDate);
		System.out.println(localDate2);
		
		System.out.println(DateTimeCalculatorUtil.betweenYears(localDate, localDate2));
		System.out.println(DateTimeCalculatorUtil.betweenMonths(localDate, localDate2));
		System.out.println(DateTimeCalculatorUtil.betweenDays(localDate, localDate2));
	}
	
	/**
	 * 使用Period比较2个Date
	 */
	@Test
	public void dateCalculatorPeriodBetweenTest2(){
		Date date = new Date();
		LocalDate localDate2 = LocalDate.of(2021, 3, 7);
		Date date2 = DateTimeConverterUtil.toDate(localDate2);
		System.out.println(date);
		System.out.println(date2);
		
		System.out.println(DateTimeCalculatorUtil.betweenYears(date, date2));
		System.out.println(DateTimeCalculatorUtil.betweenMonths(date, date2));
		System.out.println(DateTimeCalculatorUtil.betweenDays(date, date2));
	}
	
	/**
	 * 使用Duration比较2个LocalDateTime
	 */
	@Test
	public void dateCalculatorDurationBetweenTest(){
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime localDateTime2 = LocalDateTime.of(2021, 3, 7, 22, 10, 10);
		System.out.println(localDateTime);
		System.out.println(localDateTime2);
		
		System.out.println(DateTimeCalculatorUtil.betweenTotalDays(localDateTime, localDateTime2));
		System.out.println(DateTimeCalculatorUtil.betweenTotalHours(localDateTime, localDateTime2));
		System.out.println(DateTimeCalculatorUtil.betweenTotalMinutes(localDateTime, localDateTime2));
		System.out.println(DateTimeCalculatorUtil.betweenTotalSeconds(localDateTime, localDateTime2));
		System.out.println(DateTimeCalculatorUtil.betweenTotalMillis(localDateTime, localDateTime2));
		System.out.println(DateTimeCalculatorUtil.betweenTotalNanos(localDateTime, localDateTime2));
	}
	
	/**
	 * 使用Duration比较2个Date
	 */
	@Test
	public void dateCalculatorDurationBetweenTest2(){
		Date date = new Date();
		LocalDate localDate2 = LocalDate.of(2021, 3, 7);
		Date date2 = DateTimeConverterUtil.toDate(localDate2);
		System.out.println(date);
		System.out.println(date2);
		
		System.out.println(DateTimeCalculatorUtil.betweenTotalDays(date, date2));
		System.out.println(DateTimeCalculatorUtil.betweenTotalHours(date, date2));
		System.out.println(DateTimeCalculatorUtil.betweenTotalMinutes(date, date2));
		System.out.println(DateTimeCalculatorUtil.betweenTotalSeconds(date, date2));
		System.out.println(DateTimeCalculatorUtil.betweenTotalMillis(date, date2));
		System.out.println(DateTimeCalculatorUtil.betweenTotalNanos(date, date2));
	}	
	
	
	/**
	 * Date其他常用计算
	 */
	@Test
	public void dateCalculatorOtherTest(){
		Date date = new Date();
		System.out.println(date);
		System.out.println(DateTimeConverterUtil.toLocalDateTime(date));
		//获取星期值
		System.out.println(DateTimeCalculatorUtil.getDayOfWeek(date));
		//获取星期值当前月的最后一天
		System.out.println(DateTimeCalculatorUtil.lastDayOfMonth(date));
		//判断是否闰年
		System.out.println(DateTimeCalculatorUtil.isLeapYear(date));
		//获取月的天数
		System.out.println(DateTimeCalculatorUtil.lengthOfMonth(date));
		//获取月的天数
		System.out.println(DateTimeCalculatorUtil.lengthOfYear(date));
		//下一个星期一
		System.out.println(DateTimeCalculatorUtil.next(date, DayOfWeek.MONDAY));
		//上一个星期一
		System.out.println(DateTimeCalculatorUtil.previous(date, DayOfWeek.MONDAY));
		//获下一个工作日
		System.out.println(DateTimeCalculatorUtil.nextWorkDay(date));
		//获下一个闰年
		System.out.println(DateTimeCalculatorUtil.nextLeapYear(date));
	}
	
	/**
	 * LocalDateTime其他常用计算
	 */
	@Test
	public void dateCalculatorOtherTest2(){
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		//获取星期值
		System.out.println(DateTimeCalculatorUtil.getDayOfWeek(ldt));
		//获取星期值当前月的最后一天
		System.out.println(DateTimeCalculatorUtil.lastDayOfMonth(ldt));
		//判断是否闰年
		System.out.println(DateTimeCalculatorUtil.isLeapYear(ldt));
		//获取月的天数
		System.out.println(DateTimeCalculatorUtil.lengthOfMonth(ldt));
		//获取月的天数
		System.out.println(DateTimeCalculatorUtil.lengthOfYear(ldt));
		//下一个星期一
		System.out.println(DateTimeCalculatorUtil.next(ldt, DayOfWeek.MONDAY));
		//上一个星期一
		System.out.println(DateTimeCalculatorUtil.previous(ldt, DayOfWeek.MONDAY));
		//获下一个工作日
		System.out.println(DateTimeCalculatorUtil.nextWorkDay(ldt));
		//获下一个闰年
		System.out.println(DateTimeCalculatorUtil.nextLeapYear(ldt));
	}
	
	/**
	 * 时区时间计算
	 */
	@Test
	public void zonedDateTimeTest(){
		//系统默认时区
		System.out.println(DateTimeCalculatorUtil.getZonedDateTimeNowOfDefault());
		//系统上海时区
		ZonedDateTime shanghaiZonedDateTime = DateTimeCalculatorUtil.getZonedDateTimeNowOfShanghai();
		System.out.println(shanghaiZonedDateTime);
		//系统巴黎时区
		ZonedDateTime parisZonedDateTime = DateTimeCalculatorUtil.getZonedDateTimeNowOfParis();
		System.out.println(parisZonedDateTime);
		//系统美国东部时区纽约时间
		System.out.println(DateTimeCalculatorUtil.getZonedDateTimeNowOfEST());
		//系统东京时区
		System.out.println(DateTimeCalculatorUtil.getZonedDateTimeNowOfTokyo());
		
		//上海时区，转换为巴黎时区
		System.out.println("============transform 时区转换=============");
		System.out.println("shanghaiZonedDateTime: "+shanghaiZonedDateTime);
		ZonedDateTime transformZonedDateTime = DateTimeCalculatorUtil.transform(shanghaiZonedDateTime,
				ZoneIdEnum.ECT.getZoneIdName());
		System.out.println("transformZonedDateTime: "+transformZonedDateTime);
	}	
	
	/**
	 * 获取可用时区ID
	 */
	@Test
	public void getAvailableZoneIds(){
		for(String zoneId : ZoneId.getAvailableZoneIds()){
			System.out.println(zoneId);
		}
	}
	
	/**
	 * 2个时间比较
	 */
	@Test
	public void dateCompareTest(){
		LocalDateTime localDateTime1 = LocalDateTime.now();
		LocalDateTime localDateTime2 = localDateTime1.plusDays(1);
		System.out.println(DateTimeCalculatorUtil.compare(localDateTime1, localDateTime2));
		
		Date date1 = new Date();
		Date date2 = date1;
		System.out.println(DateTimeCalculatorUtil.compare(date1, date2));
	}
	
	/**
	 * 获取月份信息，包括英文全称简称，中文等
	 */
	@Test
	public void dateGetMonthTest(){
		Date date = new Date();
		System.out.println(date);
		
		System.out.println(DateTimeCalculatorUtil.getMonth(date));
		System.out.println(DateTimeCalculatorUtil.getMonthEnLong(date));
		System.out.println(DateTimeCalculatorUtil.getMonthEnShort(date));
		System.out.println(DateTimeCalculatorUtil.getMonthCnLong(date));
		System.out.println(DateTimeCalculatorUtil.getMonthCnShort(date));
	}
	
	/**
	 * 获取星期信息，包括英文全称简称，中文等
	 */
	@Test
	public void dateGetWeekTest(){
		Date date = new Date();
		System.out.println(date);
		
		System.out.println(DateTimeCalculatorUtil.getDayOfWeek(date));
		System.out.println(DateTimeCalculatorUtil.getDayOfWeekEnLong(date));
		System.out.println(DateTimeCalculatorUtil.getDayOfWeekEnShort(date));
		System.out.println(DateTimeCalculatorUtil.getDayOfWeekCn(date));
	}	
	
	/**
	 * 获取准确的起始时间方法测试
	 * 比如startTimeOfMonth() 当月起始时间 当月第一天日期+00:00:00
	 * 	  endTimeOfMonth() 当月最后一天日期+23:59:59
	 */
	@Test
	public void dateStartTimeAndEndTimeTest(){
		//当天
		System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.startTimeOfToday()));
		System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.endTimeOfToday()));
		
		//当月
		System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.startTimeOfMonth()));
		System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.endTimeOfMonth()));
		
		//指定年月
		System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.startTimeOfSpecialMonth(2019, 10)));
		System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.endTimeOfSpecialMonth(2019, 10)));
		
		//指定日期
		System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.startTimeOfDate(2019, 10, 1)));
		System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.endTimeOfDate(2019, 10, 1)));
	}
	
	/**
	 * 相同月日对比
	 */
	@Test
	public void sameMonthDayTest(){
		Date date = DateTimeCalculatorUtil.getDate(2020, 2, 29);
		System.out.println(date);
		
		//date的月日部分是否和02-29相等
		System.out.println(DateTimeCalculatorUtil.isSameMonthDay(date, "02-29"));
		//date的月日部分是否和new Date()的月日部分相等
		System.out.println(DateTimeCalculatorUtil.isSameMonthDay(date, new Date()));
		//当前时间月日部分是否和02-29相等
		System.out.println(DateTimeCalculatorUtil.isSameMonthDayOfNow("02-29"));
		
		//date的月日部分和下一个03-05相差天数
		System.out.println(DateTimeCalculatorUtil.betweenNextSameMonthDay(date, "03-05"));
		//当前时间月日部分和下一个03-05相差天数
		System.out.println(DateTimeCalculatorUtil.betweenNextSameMonthDayOfNow("03-05"));
		
		//date为准，下一个02-14的日期
		System.out.println(DateTimeCalculatorUtil.nextSameMonthDay(date, "02-14"));
		//date为准，下一个03-05的日期
		System.out.println(DateTimeCalculatorUtil.nextSameMonthDay(date, "03-05"));
		//date为准，下一个02-29的日期 ，02-29 只有闰年有。
		System.out.println(DateTimeCalculatorUtil.nextSameMonthDay(date, "02-29"));
		//当前时间为准，下一个02-29的日期  ，02-29 只有闰年有。
		System.out.println(DateTimeCalculatorUtil.nextSameMonthDayOfNow("02-29"));
	}
	
	/**
	 * 星座计算测试
	 */
	@Test
	public void constellationTest(){
		//获取星座中文
		System.out.println(DateTimeCalculatorUtil.getConstellationNameCn("02-29"));
		//获取星座英文
		System.out.println(DateTimeCalculatorUtil.getConstellationNameEn("02-29"));
		
		//2020-05-01 的星座
		System.out.println(DateTimeCalculatorUtil.getConstellationNameCn(DateTimeCalculatorUtil.getDate(2020, 5, 1)));
	}
	
	/**
	 * yearMonth测试
	 */
	@Test
	public void yearMonthTest(){
		//是否过期
		System.out.println(DateTimeCalculatorUtil.isExpiry("2020-03"));
		
		//获取指定年月的所有日期列表
		List<Date> dateList = DateTimeCalculatorUtil.getDateList("2020-03");
		dateList.stream().forEach(date->{
			System.out.println(DateTimeFormatterUtil.formatToDateStr(date));
		});
		
		System.out.println("========================");

		//获取指定区间的时间列表，包含起始
		List<Date> dateList2 = DateTimeCalculatorUtil.getDateList(dateList.get(0), dateList.get(dateList.size()-1));
		dateList2.stream().forEach(date->{
			System.out.println(DateTimeFormatterUtil.formatToDateStr(date));
		});
		
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
