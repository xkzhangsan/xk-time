package com.xkzhangsan.time.test;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.calculator.TimePair;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.enums.ZoneIdEnum;
import com.xkzhangsan.time.formatter.DateFormatPattern;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;
import com.xkzhangsan.time.utils.CollectionUtil;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 日期计算测试类
 * @author xkzhangsan
 */
public class DateTimeCalculatorUtilTest {
	
	/**
	 * 获取时间年月日时分秒毫秒
	 */
	@Test
	public void dateCalculatorGetTest(){
		//2020-04-29T17:30:15
		Date date = DateTimeFormatterUtil.parseToDate("2020-04-29 17:30:15.111", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSS_FMT);
		Assert.assertEquals(2020,DateTimeCalculatorUtil.getYear(date));
		Assert.assertEquals(4,DateTimeCalculatorUtil.getMonth(date));
		Assert.assertEquals(29,DateTimeCalculatorUtil.getDayOfMonth(date));
		Assert.assertEquals(17,DateTimeCalculatorUtil.getHour(date));
		Assert.assertEquals(30,DateTimeCalculatorUtil.getMinute(date));
		Assert.assertEquals(15,DateTimeCalculatorUtil.getSecond(date));
		Assert.assertEquals(111,DateTimeCalculatorUtil.getMillisecond(date));
		Assert.assertEquals(120,DateTimeCalculatorUtil.getDayOfYear(date));
		Assert.assertEquals(DateTimeCalculatorUtil.getDayOfYear(new Date()), DateTimeCalculatorUtil.getDayOfYear());
		Assert.assertEquals(366,DateTimeCalculatorUtil.getLengthOfYear(2020));
	}
	
	/**
	 * date年月日时分秒毫秒增加
	 */
	@Test
	public void dateCalculatorPlusTest(){
		//2020-04-29T17:30:15.30
		Date date = DateTimeCalculatorUtil.getDate(2020,4,29,17,30,15, 30);

		Assert.assertEquals(2020+1,DateTimeCalculatorUtil.getYear(DateTimeCalculatorUtil.plusYears(date, 1)));
		Assert.assertEquals(4+1,DateTimeCalculatorUtil.getMonth(DateTimeCalculatorUtil.plusMonths(date, 1)));
		Assert.assertEquals(29+1,DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.plusDays(date, 1)));
		Assert.assertEquals(17+1,DateTimeCalculatorUtil.getHour(DateTimeCalculatorUtil.plusHours(date, 1)));
		Assert.assertEquals(30+1,DateTimeCalculatorUtil.getMinute(DateTimeCalculatorUtil.plusMinutes(date, 1)));
		Assert.assertEquals(15+1,DateTimeCalculatorUtil.getSecond(DateTimeCalculatorUtil.plusSeconds(date, 1)));
		Assert.assertEquals(30+1,DateTimeCalculatorUtil.getMillisecond(DateTimeCalculatorUtil.plusMillis(date, 1)));
	}

	/**
	 * date年月日时分秒毫秒增加 LocalDateTime
	 */
	@Test
	public void dateCalculatorPlusTest2(){
		//2020-04-29T17:30:15.30
		LocalDateTime localDateTime = LocalDateTime.of(2020,4,29,17,30,15, 30*1000_000);

		Assert.assertEquals(2020+1,DateTimeCalculatorUtil.getYear(DateTimeCalculatorUtil.plusYears(localDateTime, 1)));
		Assert.assertEquals(4+1,DateTimeCalculatorUtil.getMonth(DateTimeCalculatorUtil.plusMonths(localDateTime, 1)));
		Assert.assertEquals(29+1,DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.plusDays(localDateTime, 1)));
		Assert.assertEquals(17+1,DateTimeCalculatorUtil.getHour(DateTimeCalculatorUtil.plusHours(localDateTime, 1)));
		Assert.assertEquals(30+1,DateTimeCalculatorUtil.getMinute(DateTimeCalculatorUtil.plusMinutes(localDateTime, 1)));
		Assert.assertEquals(15+1,DateTimeCalculatorUtil.getSecond(DateTimeCalculatorUtil.plusSeconds(localDateTime, 1)));
		Assert.assertEquals(30+1,DateTimeCalculatorUtil.getMillisecond(DateTimeCalculatorUtil.plusMillis(localDateTime, 1)));
	}

	/**
	 * date年月日时分秒毫秒增加 LocalDate LocalTime
	 */
	@Test
	public void dateCalculatorPlusTest3(){
		//2020-04-29T17:30:15.30
		LocalDate localDate = LocalDate.of(2020,4,29);

		Assert.assertEquals(2020+1,DateTimeCalculatorUtil.getYear(DateTimeCalculatorUtil.plusYears(localDate, 1)));
		Assert.assertEquals(4+1,DateTimeCalculatorUtil.getMonth(DateTimeCalculatorUtil.plusMonths(localDate, 1)));
		Assert.assertEquals(29+1,DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.plusDays(localDate, 1)));

		LocalTime localTime = LocalTime.of(17,30,15, 30*1000_000);
		Assert.assertEquals(17+1,DateTimeCalculatorUtil.getHour(DateTimeCalculatorUtil.plusHours(localTime, 1)));
		Assert.assertEquals(30+1,DateTimeCalculatorUtil.getMinute(DateTimeCalculatorUtil.plusMinutes(localTime, 1)));
		Assert.assertEquals(15+1,DateTimeCalculatorUtil.getSecond(DateTimeCalculatorUtil.plusSeconds(localTime, 1)));
		Assert.assertEquals(30+1,DateTimeCalculatorUtil.getMillisecond(DateTimeCalculatorUtil.plusMillis(localTime, 1)));
	}
	
	/**
	 * date年月日时分秒毫秒减少
	 */
	@Test
	public void dateCalculatorMinusTest(){
		//2020-04-29T17:30:15.30
		Date date = DateTimeCalculatorUtil.getDate(2020,4,29,17,30,15, 30);

		Assert.assertEquals(2020-1,DateTimeCalculatorUtil.getYear(DateTimeCalculatorUtil.minusYears(date, 1)));
		Assert.assertEquals(4-1,DateTimeCalculatorUtil.getMonth(DateTimeCalculatorUtil.minusMonths(date, 1)));
		Assert.assertEquals(29-1,DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.minusDays(date, 1)));
		Assert.assertEquals(17-1,DateTimeCalculatorUtil.getHour(DateTimeCalculatorUtil.minusHours(date, 1)));
		Assert.assertEquals(30-1,DateTimeCalculatorUtil.getMinute(DateTimeCalculatorUtil.minusMinutes(date, 1)));
		Assert.assertEquals(15-1,DateTimeCalculatorUtil.getSecond(DateTimeCalculatorUtil.minusSeconds(date, 1)));
		Assert.assertEquals(30-1,DateTimeCalculatorUtil.getMillisecond(DateTimeCalculatorUtil.minusMillis(date, 1)));
	}

	/**
	 * date年月日时分秒毫秒减少 LocalDateTime
	 */
	@Test
	public void dateCalculatorMinusTest2(){
		//2020-04-29T17:30:15.30
		LocalDateTime localDateTime = LocalDateTime.of(2020,4,29,17,30,15, 30*1000_000);

		Assert.assertEquals(2020-1,DateTimeCalculatorUtil.getYear(DateTimeCalculatorUtil.minusYears(localDateTime, 1)));
		Assert.assertEquals(4-1,DateTimeCalculatorUtil.getMonth(DateTimeCalculatorUtil.minusMonths(localDateTime, 1)));
		Assert.assertEquals(29-1,DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.minusDays(localDateTime, 1)));
		Assert.assertEquals(17-1,DateTimeCalculatorUtil.getHour(DateTimeCalculatorUtil.minusHours(localDateTime, 1)));
		Assert.assertEquals(30-1,DateTimeCalculatorUtil.getMinute(DateTimeCalculatorUtil.minusMinutes(localDateTime, 1)));
		Assert.assertEquals(15-1,DateTimeCalculatorUtil.getSecond(DateTimeCalculatorUtil.minusSeconds(localDateTime, 1)));
		Assert.assertEquals(30-1,DateTimeCalculatorUtil.getMillisecond(DateTimeCalculatorUtil.minusMillis(localDateTime, 1)));
	}

	/**
	 * date年月日时分秒毫秒减少 LocalDate LocalTime
	 */
	@Test
	public void dateCalculatorMinusTest3(){
		//2020-04-29T17:30:15.30
		LocalDate localDate = LocalDate.of(2020,4,29);

		Assert.assertEquals(2020-1,DateTimeCalculatorUtil.minusYears(localDate, 1).getYear());
		Assert.assertEquals(4-1,DateTimeCalculatorUtil.minusMonths(localDate, 1).getMonth().getValue());
		Assert.assertEquals(29-1,DateTimeCalculatorUtil.minusDays(localDate, 1).getDayOfMonth());

		LocalTime localTime = LocalTime.of(17,30,15, 30*1000_000);
		Assert.assertEquals(17-1,DateTimeCalculatorUtil.minusHours(localTime, 1).getHour());
		Assert.assertEquals(30-1,DateTimeCalculatorUtil.minusMinutes(localTime, 1).getMinute());
		Assert.assertEquals(15-1,DateTimeCalculatorUtil.minusSeconds(localTime, 1).getSecond());
		Assert.assertEquals(30-1,DateTimeCalculatorUtil.getMillisecond(DateTimeCalculatorUtil.minusMillis(localTime, 1)));
	}
	
	/**
	 * Date年月日时分秒毫秒属性值修改
	 */
	@Test
	public void dateCalculatorWithTest(){
		//2020-04-29T17:30:15.30
		Date date = DateTimeCalculatorUtil.getDate(2020,4,29,17,30,15, 30);

		Assert.assertEquals(2019,DateTimeCalculatorUtil.getYear(DateTimeCalculatorUtil.withYear(date, 2019)));
		Assert.assertEquals(3,DateTimeCalculatorUtil.getMonth(DateTimeCalculatorUtil.withMonth(date, 3)));
		Assert.assertEquals(28,DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.withDayOfMonth(date, 28)));
		Assert.assertEquals(16,DateTimeCalculatorUtil.getHour(DateTimeCalculatorUtil.withHour(date, 16)));
		Assert.assertEquals(29,DateTimeCalculatorUtil.getMinute(DateTimeCalculatorUtil.withMinute(date, 29)));
		Assert.assertEquals(14,DateTimeCalculatorUtil.getSecond(DateTimeCalculatorUtil.withSecond(date, 14)));
		Assert.assertEquals(15,DateTimeCalculatorUtil.getMillisecond(DateTimeCalculatorUtil.withMilli(date, 15)));
		// 修改DayOfWeek，原DayOfWeek为3，修改为1 （对比 3-2=1）
		Assert.assertEquals(DateTimeCalculatorUtil.getDayOfWeek(date)-2, DateTimeCalculatorUtil.getDayOfWeek(DateTimeCalculatorUtil.withDayOfWeek(date, 1)));
	}

	/**
	 * Date年月日时分秒毫秒属性值修改 LocalDateTime
	 */
	@Test
	public void dateCalculatorWithTest2(){
		//2020-04-29T17:30:15.30
		LocalDateTime localDateTime = LocalDateTime.of(2020,4,29,17,30,15, 30*1000_000);

		Assert.assertEquals(2019,DateTimeCalculatorUtil.getYear(DateTimeCalculatorUtil.withYear(localDateTime, 2019)));
		Assert.assertEquals(3,DateTimeCalculatorUtil.getMonth(DateTimeCalculatorUtil.withMonth(localDateTime, 3)));
		Assert.assertEquals(28,DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.withDayOfMonth(localDateTime, 28)));
		Assert.assertEquals(16,DateTimeCalculatorUtil.getHour(DateTimeCalculatorUtil.withHour(localDateTime, 16)));
		Assert.assertEquals(29,DateTimeCalculatorUtil.getMinute(DateTimeCalculatorUtil.withMinute(localDateTime, 29)));
		Assert.assertEquals(14,DateTimeCalculatorUtil.getSecond(DateTimeCalculatorUtil.withSecond(localDateTime, 14)));
		Assert.assertEquals(15,DateTimeCalculatorUtil.getMillisecond(DateTimeCalculatorUtil.withMilli(localDateTime, 15)));
	}

	/**
	 * Date年月日时分秒毫秒属性值修改
	 */
	@Test
	public void dateCalculatorWithTest3(){
		//2020-04-29T17:30:15.30
		LocalDate localDate = LocalDate.of(2020,4,29);

		Assert.assertEquals(2019,DateTimeCalculatorUtil.withYear(localDate, 2019).getYear());
		Assert.assertEquals(3,DateTimeCalculatorUtil.withMonth(localDate, 3).getMonth().getValue());
		Assert.assertEquals(28,DateTimeCalculatorUtil.withDayOfMonth(localDate, 28).getDayOfMonth());

		LocalTime localTime = LocalTime.of(17,30,15, 30);
		Assert.assertEquals(16,DateTimeCalculatorUtil.withHour(localTime, 16).getHour());
		Assert.assertEquals(29,DateTimeCalculatorUtil.withMinute(localTime, 29).getMinute());
		Assert.assertEquals(14,DateTimeCalculatorUtil.withSecond(localTime, 14).getSecond());
		Assert.assertEquals(15,DateTimeCalculatorUtil.getMillisecond(DateTimeCalculatorUtil.withMilli(localTime, 15)));
	}
	

	/**
	 * 使用Period比较2个LocalDate
	 */
	@Test
	public void dateCalculatorPeriodBetweenTest(){
		LocalDate localDate = LocalDate.of(2020, 4, 29);
		LocalDate localDate2 = LocalDate.of(2021, 3, 7);

		Assert.assertEquals(0,DateTimeCalculatorUtil.betweenYears(localDate, localDate2));
		Assert.assertEquals(10,DateTimeCalculatorUtil.betweenMonths(localDate, localDate2));
		Assert.assertEquals(7,DateTimeCalculatorUtil.betweenDays(localDate, localDate2));
		Assert.assertEquals(0,DateTimeCalculatorUtil.betweenPeriodYears(localDate, localDate2));
		Assert.assertEquals(10,DateTimeCalculatorUtil.betweenPeriodMonths(localDate, localDate2));
		Assert.assertEquals(7,DateTimeCalculatorUtil.betweenPeriodDays(localDate, localDate2));
	}
	
	/**
	 * 使用Period比较2个Date
	 */
	@Test
	public void dateCalculatorPeriodBetweenTest2(){
		Date date = DateTimeCalculatorUtil.getDate(2020, 4, 29);
		Date date2 = DateTimeCalculatorUtil.getDate(2021, 3, 7);

		Assert.assertEquals(0,DateTimeCalculatorUtil.betweenYears(date, date2));
		Assert.assertEquals(10,DateTimeCalculatorUtil.betweenMonths(date, date2));
		Assert.assertEquals(7,DateTimeCalculatorUtil.betweenDays(date, date2));
		Assert.assertEquals(0,DateTimeCalculatorUtil.betweenPeriodYears(date, date2));
		Assert.assertEquals(10,DateTimeCalculatorUtil.betweenPeriodMonths(date, date2));
		Assert.assertEquals(7,DateTimeCalculatorUtil.betweenPeriodDays(date, date2));
	}
	

	/**
	 * 使用Duration比较2个Date
	 */
	@Test
	public void dateCalculatorDurationBetweenTest(){
		Date date = DateTimeCalculatorUtil.getDate(2020, 4, 29, 10, 10,10);
		Date date2 = DateTimeCalculatorUtil.getDate(2020, 5, 1, 15, 15,15);

		Assert.assertEquals(2,DateTimeCalculatorUtil.betweenTotalDays(date, date2));
		Assert.assertEquals(53,DateTimeCalculatorUtil.betweenTotalHours(date, date2));
		Assert.assertEquals(3185,DateTimeCalculatorUtil.betweenTotalMinutes(date, date2));
		Assert.assertEquals(191105,DateTimeCalculatorUtil.betweenTotalSeconds(date, date2));
		Assert.assertEquals(191105000,DateTimeCalculatorUtil.betweenTotalMillis(date, date2));
		Assert.assertEquals(191105000000000L,DateTimeCalculatorUtil.betweenTotalNanos(date, date2));
		
		LocalDateTime localDateTime = LocalDateTime.of(2020, 4, 29, 10, 10,10);
		LocalDateTime localDateTime2 = LocalDateTime.of(2020, 5, 1, 15, 15,15);
		Assert.assertEquals(2,DateTimeCalculatorUtil.betweenTotalDays(localDateTime, localDateTime2));
		Assert.assertEquals(53,DateTimeCalculatorUtil.betweenTotalHours(localDateTime, localDateTime2));
		Assert.assertEquals(3185,DateTimeCalculatorUtil.betweenTotalMinutes(localDateTime, localDateTime2));
		Assert.assertEquals(191105,DateTimeCalculatorUtil.betweenTotalSeconds(localDateTime, localDateTime2));
		Assert.assertEquals(191105000,DateTimeCalculatorUtil.betweenTotalMillis(localDateTime, localDateTime2));
		Assert.assertEquals(191105000000000L,DateTimeCalculatorUtil.betweenTotalNanos(localDateTime, localDateTime2));
		
		LocalDate localDate = LocalDate.of(2020, 4, 29);
		LocalDate localDate2 = LocalDate.of(2020, 5, 1);
		Assert.assertEquals(2,DateTimeCalculatorUtil.betweenTotalDays(localDate, localDate2));
		Assert.assertEquals(48,DateTimeCalculatorUtil.betweenTotalHours(localDate, localDate2));
		Assert.assertEquals(2880,DateTimeCalculatorUtil.betweenTotalMinutes(localDate, localDate2));
		Assert.assertEquals(172800,DateTimeCalculatorUtil.betweenTotalSeconds(localDate, localDate2));
		Assert.assertEquals(172800000,DateTimeCalculatorUtil.betweenTotalMillis(localDate, localDate2));
		Assert.assertEquals(172800000000000L,DateTimeCalculatorUtil.betweenTotalNanos(localDate, localDate2));
		
		LocalTime localTime = LocalTime.of(10, 10,10);
		LocalTime localTime2 = LocalTime.of(15, 15,15);
		Assert.assertEquals(5,DateTimeCalculatorUtil.betweenTotalHours(localTime, localTime2));
		Assert.assertEquals(305,DateTimeCalculatorUtil.betweenTotalMinutes(localTime, localTime2));
		Assert.assertEquals(18305,DateTimeCalculatorUtil.betweenTotalSeconds(localTime, localTime2));
		Assert.assertEquals(18305000,DateTimeCalculatorUtil.betweenTotalMillis(localTime, localTime2));
		Assert.assertEquals(18305000000000L,DateTimeCalculatorUtil.betweenTotalNanos(localTime, localTime2));
	}
	
	
	/**
	 * Date其他常用计算
	 */
	@Test
	public void dateCalculatorOtherTest(){
		Date date = DateTimeCalculatorUtil.getDate(2020, 4, 29);
		//获取星期值
		Assert.assertEquals(3,DateTimeCalculatorUtil.getDayOfWeek(date));
		//获取当前月的最后一天
		Assert.assertEquals(DateTimeCalculatorUtil.getDate(2020, 4, 30),DateTimeCalculatorUtil.lastDayOfMonth(date));
		//判断是否闰年
		Assert.assertEquals(true,DateTimeCalculatorUtil.isLeapYear(date));
		//获取月的天数
		Assert.assertEquals(30,DateTimeCalculatorUtil.lengthOfMonth(date));
		//获取年的天数
		Assert.assertEquals(366,DateTimeCalculatorUtil.lengthOfYear(date));
		//下一个星期一
		Assert.assertEquals(DateTimeCalculatorUtil.getDate(2020, 5, 4),DateTimeCalculatorUtil.next(date, DayOfWeek.MONDAY));
		//上一个星期一
		Assert.assertEquals(DateTimeCalculatorUtil.getDate(2020, 4, 27),DateTimeCalculatorUtil.previous(date, DayOfWeek.MONDAY));
		//获下一个工作日
		Assert.assertEquals(DateTimeCalculatorUtil.getDate(2020, 4, 30),DateTimeCalculatorUtil.nextWorkDay(date));
		//获下一个闰年
		Assert.assertEquals(DateTimeCalculatorUtil.getDate(2024, 4, 29),DateTimeCalculatorUtil.nextLeapYear(date));
	}
	

	/**
	 * 时区时间计算
	 */
	@Test
	public void zonedDateTimeTest(){
		//系统默认时区
		ZonedDateTime defaultZonedDateTime = DateTimeCalculatorUtil.getZonedDateTimeNowOfDefault();
		Assert.assertNotNull(defaultZonedDateTime);

		//系统上海时区
		ZonedDateTime shanghaiZonedDateTime = DateTimeCalculatorUtil.getZonedDateTimeNowOfShanghai();
		Assert.assertEquals(true, shanghaiZonedDateTime.toString().contains("Asia/Shanghai"));

		//系统巴黎时区
		ZonedDateTime parisZonedDateTime = DateTimeCalculatorUtil.getZonedDateTimeNowOfParis();
		Assert.assertEquals(true, parisZonedDateTime.toString().contains("Europe/Paris"));

        ZonedDateTime shanghaiZonedDateTimeSpecial = ZonedDateTime.parse("2020-04-30T13:23:02.879+08:00[Asia/Shanghai]");
		//上海时区，转换为巴黎时区
		ZonedDateTime transformZonedDateTime = DateTimeCalculatorUtil.transform(shanghaiZonedDateTimeSpecial,
				ZoneIdEnum.ECT.getZoneIdName());
		Assert.assertEquals("2020-04-30T07:23:02.879+02:00[Europe/Paris]",transformZonedDateTime.toString());
		//Date时区转换
		String parisTransform = DateTimeCalculatorUtil.transform(DateTimeConverterUtil.toDate(shanghaiZonedDateTimeSpecial), ZoneId.of("Europe/Paris"));
		Assert.assertEquals("2020-04-30 07:23:02", parisTransform);
	}	
	
	/**
	 * 获取可用时区ID
	 */
	@Test
	public void getAvailableZoneIds(){
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        Assert.assertEquals(true, CollectionUtil.isNotEmpty(zoneIds));
	}
	
	/**
	 * 2个时间比较
	 */
	@Test
	public void dateCompareTest(){
		LocalDateTime localDateTime1 = LocalDateTime.now();
		LocalDateTime localDateTime2 = localDateTime1.plusDays(1);
		Assert.assertEquals(-1, DateTimeCalculatorUtil.compare(localDateTime1, localDateTime2));

		Date date1 = DateTimeFormatterUtil.parseDateStrToDate("2020-04-29");
		Date date2 = DateTimeCalculatorUtil.getDate(2020, 4, 29);
		Assert.assertEquals(0,DateTimeCalculatorUtil.compare(date1, date2));
	}
	
	/**
	 * 获取月份信息，包括英文全称简称，中文等
	 */
	@Test
	public void dateGetMonthTest(){
		Date date = DateTimeCalculatorUtil.getDate(2020, 4, 29);

		Assert.assertEquals(4,DateTimeCalculatorUtil.getMonth(date));
		Assert.assertEquals("April",DateTimeCalculatorUtil.getMonthEnLong(date));
		Assert.assertEquals("Apr",DateTimeCalculatorUtil.getMonthEnShort(date));
		Assert.assertEquals("APR",DateTimeCalculatorUtil.getMonthEnShortUpper(date));
		Assert.assertEquals("四月",DateTimeCalculatorUtil.getMonthCnLong(date));
		Assert.assertEquals("四",DateTimeCalculatorUtil.getMonthCnShort(date));
	}
	
	/**
	 * 获取星期信息，包括英文全称简称，中文等
	 */
	@Test
	public void dateGetWeekTest(){
		Date date = DateTimeCalculatorUtil.getDate(2020, 4, 29);

		Assert.assertEquals(3,DateTimeCalculatorUtil.getDayOfWeek(date));
		Assert.assertEquals("Wednesday",DateTimeCalculatorUtil.getDayOfWeekEnLong(date));
		Assert.assertEquals("Wed",DateTimeCalculatorUtil.getDayOfWeekEnShort(date));
		Assert.assertEquals("WED",DateTimeCalculatorUtil.getDayOfWeekEnShortUpper(date));
		Assert.assertEquals("星期三",DateTimeCalculatorUtil.getDayOfWeekCn(date));
		Assert.assertEquals("三",DateTimeCalculatorUtil.getDayOfWeekCnShort(date));
	}	
	
	/**
	 * 获取准确的起始时间方法测试
	 * 比如startTimeOfMonth() 当月起始时间 当月第一天日期+00:00:00
	 * 	  endTimeOfMonth() 当月最后一天日期+23:59:59
	 */
	@Test
	public void dateStartTimeAndEndTimeTest(){
		//当天
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(new Date())+" 00:00:00",DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.startTimeOfToday()));
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(new Date())+" 23:59:59",DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.endTimeOfToday()));

		//指定年月
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(DateTimeCalculatorUtil.getDateStartOfMonth(2019, 10))+" 00:00:00",DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.startTimeOfSpecialMonth(2019, 10)));
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(DateTimeCalculatorUtil.getDateEndOfMonth(2019, 10))+" 23:59:59",DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.endTimeOfSpecialMonth(2019, 10)));
		
		//指定日期
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(DateTimeCalculatorUtil.getDate(2019, 10, 1))+" 00:00:00",DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.startTimeOfDate(2019, 10, 1)));
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(DateTimeCalculatorUtil.getDate(2019, 10, 1))+" 23:59:59",DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.endTimeOfDate(2019, 10, 1)));
		
		//精确结束时间
		Assert.assertEquals("2019-10-01 23:59:59.999000000",DateTimeFormatterUtil.format(
				DateTimeCalculatorUtil.endAccuracyTimeOfDate(DateTimeCalculatorUtil.getDate(2019, 10, 1)),
				DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSSSSSSSS_FMT));
		
		Assert.assertEquals("2019-10-01 00:00:00.000000000",DateTimeFormatterUtil.format(
				DateTimeCalculatorUtil.startTimeOfLocalDateTime(LocalDateTime.of(2019, 10, 1,1,1)),
				DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSSSSSSSS_FMT));
		
		Assert.assertEquals("2019-10-01 23:59:59.999999999",DateTimeFormatterUtil.format(
				DateTimeCalculatorUtil.endAccuracyTimeOfLocalDateTime(LocalDateTime.of(2019, 10, 1,1,1)),
				DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSSSSSSSS_FMT));
	}
	
	/**
	 * 相同月日对比
	 */
	@Test
	public void sameMonthDayTest(){
		Date date = DateTimeCalculatorUtil.getDate(2020, 2, 29);

		//date的月日部分是否和02-29相等
		Assert.assertEquals(true,DateTimeCalculatorUtil.isSameMonthDay(date, "02-29"));
		//date的月日部分是否和new Date()的月日部分相等
		Assert.assertEquals(false,DateTimeCalculatorUtil.isSameMonthDay(date, new Date()));
		//当前时间月日部分是否和02-29相等
		Assert.assertNotNull(DateTimeCalculatorUtil.isSameMonthDayOfNow("02-29"));
		
		//date的月日部分和下一个03-05相差天数
        Assert.assertEquals(5,DateTimeCalculatorUtil.betweenNextSameMonthDay(date, "03-05"));
		//当前时间月日部分和下一个03-05相差天数
        Assert.assertNotNull(DateTimeCalculatorUtil.betweenNextSameMonthDayOfNow("03-05"));
		
		//date为准，下一个02-14的日期
        Assert.assertEquals(DateTimeCalculatorUtil.getDate(2021, 2, 14),DateTimeCalculatorUtil.nextSameMonthDay(date, "02-14"));
		//date为准，下一个03-05的日期
        Assert.assertEquals(DateTimeCalculatorUtil.getDate(2020, 3, 5),DateTimeCalculatorUtil.nextSameMonthDay(date, "03-05"));
		//date为准，下一个02-29的日期 ，02-29 只有闰年有。
        Assert.assertEquals(DateTimeCalculatorUtil.getDate(2024, 2, 29),DateTimeCalculatorUtil.nextSameMonthDay(date, "02-29"));
		//当前时间为准，下一个02-29的日期  ，02-29 只有闰年有。
        Assert.assertNotNull(DateTimeCalculatorUtil.nextSameMonthDayOfNow("02-29"));
	}
	
	/**
	 * 星座计算测试
	 */
	@Test
	public void constellationTest(){
		//获取星座中文
		Assert.assertEquals("双鱼座",DateTimeCalculatorUtil.getConstellationNameCn("02-29"));
		//获取星座英文
		Assert.assertEquals("Pisces",DateTimeCalculatorUtil.getConstellationNameEn("02-29"));
		
		Assert.assertEquals("摩羯座",DateTimeCalculatorUtil.getConstellationNameCn("12-31"));
		
		Assert.assertEquals("摩羯座",DateTimeCalculatorUtil.getConstellationNameCn("01-01"));
		
		Assert.assertEquals("双鱼座",DateTimeCalculatorUtil.getConstellationNameCn("02-29"));
		//2020-05-01 的星座
		Assert.assertEquals("金牛座",DateTimeCalculatorUtil.getConstellationNameCn(DateTimeCalculatorUtil.getDate(2020, 5, 1)));
	}
	
	/**
	 * yearMonth测试
	 */
	@Test
	public void yearMonthTest(){
		//是否过期
        Assert.assertEquals(true,DateTimeCalculatorUtil.isExpiry("2020-03"));
		
		//获取指定年月的所有日期列表
		List<Date> dateList = DateTimeCalculatorUtil.getDateList(2020,3);
        Assert.assertEquals(31,dateList.size());

		//获取指定区间的时间列表，包含起始
		List<Date> dateList2 = DateTimeCalculatorUtil.getDateList(dateList.get(0), dateList.get(dateList.size()-1));
        Assert.assertEquals(31,dateList2.size());
        
		//获取指定区间的时间列表，包含起始，指定单位的相同时间
        Date start = DateTimeCalculatorUtil.getDate(2021, 1, 31);
        Date end = DateTimeCalculatorUtil.getDate(2021, 7, 31);
        
		List<Date> dateList3 = DateTimeCalculatorUtil.getDateList(start, end, ChronoUnit.MONTHS);
        Assert.assertEquals(7,dateList3.size());
        
        start = DateTimeCalculatorUtil.getDate(2021, 1, 1);
        end = DateTimeCalculatorUtil.getDate(2021, 1, 31);
        List<String> dateFormatList = DateTimeCalculatorUtil.getDateFormatList(start, end, DateFormatPattern.YYYY_MM_DD_CN);
        Assert.assertEquals(31,dateFormatList.size());
        Assert.assertEquals("2021年01月01日",dateFormatList.get(0));
	}
	
	/**
	 * Date 减少精度测试
	 */
	@Test
	public void reduceAccuracyDateTest(){
		Date date = DateTimeFormatterUtil.parseDateTimeStrToDate("2020-04-30 13:05:11");
        Assert.assertEquals("2020-04-30 13:05:11",DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.reduceAccuracyToSecond(date)));
        Assert.assertEquals("2020-04-30 13:05:00",DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.reduceAccuracyToMinute(date)));
        Assert.assertEquals("2020-04-30 13:00:00",DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.reduceAccuracyToHour(date)));
        Assert.assertEquals("2020-04-30 00:00:00",DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.reduceAccuracyToDay(date)));
	}
	
	/**
	 * 获取时间戳
	 */	
	@Test
	public void getTimestampTest(){
		//时间戳
        Assert.assertNotNull(String.valueOf(DateTimeCalculatorUtil.getEpochMilli()));
		//时间戳 到秒
        Assert.assertNotNull(String.valueOf(DateTimeCalculatorUtil.getEpochSecond()));
		//获取格式化时间戳字符串 yyyy-MM-dd HH:mm:ss
		Assert.assertNotNull(DateTimeCalculatorUtil.getEpochMilliFormat());
		//获取格式化时间戳字符串 yyyy-MM-dd HH:mm:ss.SSS
		Assert.assertNotNull(DateTimeCalculatorUtil.getEpochMilliFormatFull());
		
		//iso 2020-02-18T22:37:55+08:00
		Assert.assertNotNull(DateTimeCalculatorUtil.getEpochMilliIsoFormat());
		//iso 2020-02-18T22:37:55.991+08:00
		Assert.assertNotNull(DateTimeCalculatorUtil.getEpochMilliIsoFormatFull());
		
		//iso 2020-02-18T22:37:55+0800
		Assert.assertNotNull(DateTimeCalculatorUtil.getEpochMilliIsoFormatFullNoColon());
		//iso 2020-02-18T22:37:55.991+0800
		Assert.assertNotNull(DateTimeCalculatorUtil.getEpochMilliIsoFormatFullNoColon());
	}
	
	/**
	 * 年龄生日测试
	 */	
	@Test
	public void getAgeBirthDayTest(){
		Date date = DateTimeCalculatorUtil.getDate(2000, 6, 4);
		int age = DateTimeCalculatorUtil.getAge(date);
		Assert.assertTrue(age>=20);
		@SuppressWarnings("unused")
		boolean isBirthDay = DateTimeCalculatorUtil.isBirthDay(date);
	}
	
	/**
	 * 周数计算测试
	 */	
	@Test
	public void getWeekofTest(){
		Date date = DateTimeCalculatorUtil.getDate(2020, 8, 1);
		// 日期所在月中第几周
		Assert.assertEquals(1, DateTimeCalculatorUtil.weekOfMonth(date));
		DateTimeCalculatorUtil.weekOfMonth();
		// 日期所在年中第几周
		Assert.assertEquals(31, DateTimeCalculatorUtil.weekOfYear(date));
		DateTimeCalculatorUtil.weekOfYear();
		
		Date date2 = DateTimeCalculatorUtil.getDate(2020, 8, 3);
		Assert.assertTrue(DateTimeCalculatorUtil.isMonday(date2));
		
		Date date3 = DateTimeCalculatorUtil.getDate(2020, 8, 7);
		Assert.assertTrue(DateTimeCalculatorUtil.isFriday(date3));
	}
	
	/**
	 * 十二时辰测试
	 */	
	@Test
	public void getTwelveHoursTest(){
		LocalTime localTime = LocalTime.of(23,15,0);
		Assert.assertEquals("子时", DateTimeCalculatorUtil.getTwelveTwo(localTime));
		
		Date date = DateTimeCalculatorUtil.getDate(2020, 8, 1, 0,15,0);
		Assert.assertEquals("子时", DateTimeCalculatorUtil.getTwelveTwo(date));
		
		Date date2 = DateTimeCalculatorUtil.getDate(2020, 8, 1, 20,30,0);
		Assert.assertEquals("戌时", DateTimeCalculatorUtil.getTwelveTwo(date2));
	}
	
	/**
	 * 季度测试
	 */	
	@Test
	public void quarterTest(){
		//获取季度
		Date date = DateTimeCalculatorUtil.getDate(2000, 1, 1);
		Assert.assertEquals(1, DateTimeCalculatorUtil.getQuarter(date));
		
		LocalDate localDate = LocalDate.of(2000, 1, 1);
		Assert.assertEquals(1, DateTimeCalculatorUtil.getQuarter(localDate));
		
		LocalDateTime localDateTime = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
		Assert.assertEquals(1, DateTimeCalculatorUtil.getQuarter(localDateTime));
		
		//第一季度起始时间
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(
				DateTimeCalculatorUtil.getDateStartOfMonth(2019, 1))+" 00:00:00",
				DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.startTimeOfFirstQuarter(2019)));
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(
				DateTimeCalculatorUtil.getDateEndOfMonth(2019, 3))+" 23:59:59",
				DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.endTimeOfFirstQuarter(2019)));
		
		//第二季度起始时间
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(
				DateTimeCalculatorUtil.getDateStartOfMonth(2019, 4))+" 00:00:00",
				DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.startTimeOfSecondQuarter(2019)));
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(
				DateTimeCalculatorUtil.getDateEndOfMonth(2019, 6))+" 23:59:59",
				DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.endTimeOfSecondQuarter(2019)));
		
		//第三季度起始时间
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(
				DateTimeCalculatorUtil.getDateStartOfMonth(2019, 7))+" 00:00:00",
				DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.startTimeOfThirdQuarter(2019)));
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(
				DateTimeCalculatorUtil.getDateEndOfMonth(2019, 9))+" 23:59:59",
				DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.endTimeOfThirdQuarter(2019)));
		
		//第四季度起始时间
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(
				DateTimeCalculatorUtil.getDateStartOfMonth(2019, 10))+" 00:00:00",
				DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.startTimeOfFourthQuarter(2019)));
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(
				DateTimeCalculatorUtil.getDateEndOfMonth(2019, 12))+" 23:59:59",
				DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.endTimeOfFourthQuarter(2019)));
		System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.startTimeOfCurrentQuarter()));
		System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.endTimeOfCurrentQuarter()));
	}
	
	/**
	 * 年起始日期准确时间测试
	 */	
	@Test
	public void startTimeOfYearTest(){
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(
				DateTimeCalculatorUtil.getDateStartOfMonth(2019, 1))+" 00:00:00",
				DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.startTimeOfYear(2019)));
		Assert.assertEquals(DateTimeFormatterUtil.formatToDateStr(
				DateTimeCalculatorUtil.getDateEndOfMonth(2019, 12))+" 23:59:59",
				DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.endTimeOfYear(2019)));
		System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.startTimeOfCurrentYear()));
		System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(DateTimeCalculatorUtil.endTimeOfCurrentYear()));
	}

	/**
	 * 以当前时间为参考的，常用时间（明天，下周，下月，明年等）计算测试
	 */
	@Test
	public void todayTest(){
		Assert.assertEquals(LocalDateTime.now().getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.today()));

		Assert.assertEquals(LocalDateTime.now().plusDays(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.tomorrow()));

		Assert.assertEquals(LocalDateTime.now().plusWeeks(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.nextWeek()));

		Assert.assertEquals(LocalDateTime.now().plusMonths(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.nextMonth()));

		Assert.assertEquals(LocalDateTime.now().plusYears(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.nextYear()));

		Assert.assertEquals(LocalDateTime.now().minusDays(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.yesterday()));

		Assert.assertEquals(LocalDateTime.now().minusWeeks(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.lastWeek()));

		Assert.assertEquals(LocalDateTime.now().minusMonths(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.lastMonth()));

		Assert.assertEquals(LocalDateTime.now().minusYears(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeCalculatorUtil.lastYear()));
	}
	
	/**
	 * 中国法定节假日相关的工作日测试
	 */
	@Test
	public void chineseWorkDayTest(){
		//2021年放假信息
		String holidayData = "2021-01-01:0,2021-02-07:1,2021-02-11:0,2021-02-12:0,2021-02-15:0,2021-02-16:0,2021-02-17:0,2021-02-20:1,2021-04-05:0,2021-04-25:1,2021-05-03:0,2021-05-04:0,2021-05-05:0,2021-05-08:1,2021-06-14:0,2021-09-18:1,2021-09-20:0,2021-09-21:0,2021-09-26:1,2021-10-01:0,2021-10-04:0,2021-10-05:0,2021-10-06:0,2021-10-07:0,2021-10-09:1";
		//指定日期是否是工作日
		Assert.assertEquals(true,DateTimeCalculatorUtil.isChineseWorkDay(DateTimeCalculatorUtil.getDate(2021, 2, 20), holidayData));
		Assert.assertEquals(true,DateTimeCalculatorUtil.isChineseWorkDay(LocalDate.of(2021, 2, 20), holidayData));
		
		//下一个工作日
		Assert.assertEquals("2021-02-20",DateTimeCalculatorUtil.nextChineseWorkDay(LocalDate.of(2021, 2, 19), holidayData).toString());
		//工作日计数
		Assert.assertEquals(1,DateTimeCalculatorUtil.chineseWorkDay(LocalDate.of(2021, 9, 18), LocalDate.of(2021, 9, 18), holidayData).size());
	}
	
	/**
	 * 时间段重叠测试
	 */
	@Test
	public void isOverlapTest(){
		//2021-05-10到2021-05-20和2021-05-15到2021-06-01 重叠
		Date startDate1 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-10");
		Date endDate1 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-20");
		Date startDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-15");
		Date endDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-06-01");
		//不严格模式，首尾相连不重叠
		Assert.assertTrue(DateTimeCalculatorUtil.isOverlap(startDate1, endDate1, startDate2, endDate2));
		//严格模式，首尾相连重叠
		Assert.assertTrue(DateTimeCalculatorUtil.isOverlap(startDate1, endDate1, startDate2, endDate2, true));
		
		//2021-05-29到2021-05-31和2021-05-31到2021-06-01 不严格模式，不重叠 ，严格模式，重叠
		Date startDate3 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-29");
		Date endDate3 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-31");
		Date startDate4 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-31");
		Date endDate4 = DateTimeFormatterUtil.parseDateStrToDate("2021-06-01");
		//不严格模式，首尾相连不重叠
		Assert.assertFalse(DateTimeCalculatorUtil.isOverlap(startDate3, endDate3, startDate4, endDate4));
		//严格模式，首尾相连重叠
		Assert.assertTrue(DateTimeCalculatorUtil.isOverlap(startDate3, endDate3, startDate4, endDate4, true));
		
		List<TimePair> list=new ArrayList<>();
		Date startDate5 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-10");
		Date endDate5 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-12");
		TimePair timePair0 = new TimePair(startDate5.getTime(), endDate5.getTime());
		Date startDate6 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-03");
		Date endDate6 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-10");
		TimePair timePair1 = new TimePair(startDate6.getTime(), endDate6.getTime());
		Date startDate7 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-25");
		Date endDate7 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-26");
		TimePair timePair2 = new TimePair(startDate7.getTime(), endDate7.getTime());
		
		list.add(timePair0);
		list.add(timePair1);
		list.add(timePair2);
		Assert.assertFalse(DateTimeCalculatorUtil.isOverlap(list, false));
		Assert.assertTrue(DateTimeCalculatorUtil.isOverlap(list, true));
	}
	
	/**
	 * 时间段重叠测试
	 */
	@Test
	public void overlapTimeTest(){
		//不重叠
		Date startDate1 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-10");
		Date endDate1 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-20");
		Date startDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-20");
		Date endDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-06-01");
		Assert.assertEquals(DateTimeCalculatorUtil.overlapTime(startDate1, endDate1, startDate2, endDate2), 0);

		//1和2完全相等 2021-05-10~2021-05-20 / 2021-05-10~2021-05-20 
		Assert.assertEquals(DateTimeCalculatorUtil.overlapTime(startDate1, endDate1, startDate1, endDate1),
				DateTimeCalculatorUtil.betweenTotalMillis(startDate1, endDate1));
		
		//1包含2 2021-05-10~2021-05-20 / 2021-05-10~2021-05-15
		startDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-10");
		endDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-15");
		Assert.assertEquals(DateTimeCalculatorUtil.overlapTime(startDate1, endDate1, startDate2, endDate2), 
				DateTimeCalculatorUtil.betweenTotalMillis(startDate2, endDate2));
		
		//2包含1 2021-05-10~2021-05-20 / 2021-05-05~2021-05-20
		startDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-05");
		endDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-20");
		Assert.assertEquals(DateTimeCalculatorUtil.overlapTime(startDate1, endDate1, startDate2, endDate2), 
				DateTimeCalculatorUtil.betweenTotalMillis(startDate1, endDate1));
		
		//1和2的开始重叠 2021-05-10~2021-05-20 / 2021-05-15~2021-05-25
		startDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-15");
		endDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-25");
		Assert.assertEquals(DateTimeCalculatorUtil.overlapTime(startDate1, endDate1, startDate2, endDate2), 
				DateTimeCalculatorUtil.betweenTotalMillis(startDate2, endDate1));
		
		//1和2的结束重叠 2021-05-10~2021-05-20 / 2021-05-05~2021-05-15
		startDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-05");
		endDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-15");
		Assert.assertEquals(DateTimeCalculatorUtil.overlapTime(startDate1, endDate1, startDate2, endDate2), 
				DateTimeCalculatorUtil.betweenTotalMillis(startDate1, endDate2));
		
		//2和1的开始重叠 2021-05-10~2021-05-20 / 2021-05-05~2021-05-15
		startDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-05");
		endDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-15");
		Assert.assertEquals(DateTimeCalculatorUtil.overlapTime(startDate1, endDate1, startDate2, endDate2), 
				DateTimeCalculatorUtil.betweenTotalMillis(startDate1, endDate2));
		
		//2和1的结束重叠 2021-05-10~2021-05-20 / 2021-05-15~2021-05-25
		startDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-15");
		endDate2 = DateTimeFormatterUtil.parseDateStrToDate("2021-05-25");
		Assert.assertEquals(DateTimeCalculatorUtil.overlapTime(startDate1, endDate1, startDate2, endDate2), 
				DateTimeCalculatorUtil.betweenTotalMillis(startDate2, endDate1));
	}
	
	/**
	 * 平均时间计算测试
	 */
	@Test
	public void averageTimeTest(){
		Date ldt2 = DateTimeFormatterUtil.parseToDate("2019-12-01 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT);
		Date ldt3 = DateTimeFormatterUtil.parseToDate("2019-12-02 13:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT);
		List<Date> list = new ArrayList<>();
		list.add(ldt2);
		list.add(ldt3);
		LocalTime lt = DateTimeCalculatorUtil.averageTime(list);
		
		Assert.assertEquals("15:03:03", lt.toString());
	}
	
	/**
	 * 倒计时计算测试
	 */
	@Test
	public void countdownTest(){
		long millis = 24*60*60*1000 + 3*60*60*1000 + 10*60*1000 + 30*1000;
		
		Assert.assertEquals("27小时10分钟30秒", DateTimeCalculatorUtil.countdown(millis));
		//指定格式 时,分,秒
		Assert.assertEquals("27时10分30秒", DateTimeCalculatorUtil.countdown(millis, "时,分,秒"));
		//指定格式 时,分
		Assert.assertEquals("27时10分", DateTimeCalculatorUtil.countdown(millis, "时,分"));
		
		Date startDate1 = DateTimeFormatterUtil.parseDateTimeStrToDate("2021-05-19 12:00:00");
		Date endDate1 = DateTimeFormatterUtil.parseDateTimeStrToDate("2021-05-20 15:30:30");
		//指定格式 时,分,秒
		Assert.assertEquals("27时30分30秒", DateTimeCalculatorUtil.countdown(startDate1, endDate1, "时,分,秒"));
		//======================包含天===========================
		Assert.assertEquals("1天3小时10分钟30秒", DateTimeCalculatorUtil.countdownWithDay(millis));
		
		//指定格式 天,时,分,秒
		Assert.assertEquals("1天3时10分30秒", DateTimeCalculatorUtil.countdownWithDay(millis, "天,时,分,秒"));
		//指定格式 时,分
		Assert.assertEquals("1天3时10分", DateTimeCalculatorUtil.countdownWithDay(millis, "天,时,分"));
		
		//指定格式 天,时,分,秒
		Assert.assertEquals("1天3时30分30秒", DateTimeCalculatorUtil.countdownWithDay(startDate1, endDate1, "天,时,分,秒"));
		
	}	

	@Test
	public void durationBetween(){
		LocalDateTime ldt2 = DateTimeFormatterUtil.parseToLocalDateTime("2019-12-01 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT);
		LocalDateTime ldt3 = DateTimeFormatterUtil.parseToLocalDateTime("2019-12-02 13:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT);
		Duration d = DateTimeCalculatorUtil.durationBetween(ldt2, ldt3);
		//相差时间转换为总天数
        Assert.assertEquals(0,d.toDays());
	}
	
	@Test
	public void periodBetween(){
		LocalDate ld1 = DateTimeFormatterUtil.parseToLocalDateTime("2019-12-01 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT).toLocalDate();
		LocalDate ld2 = DateTimeFormatterUtil.parseToLocalDateTime("2019-12-02 13:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT).toLocalDate();
		Period p = DateTimeCalculatorUtil.periodBetween(ld1, ld2);
		//只考虑日期部分，不关心时间部分
        Assert.assertEquals(1,p.getDays());
	}
	
}
