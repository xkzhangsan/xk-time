package com.xkzhangsan.time.test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.xkzhangsan.time.LunarDate;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

/**
 * 日期转换测试
 * @author xkzhangsan
 */
public class DateTimeConverterUtilTest {
	
	@Test
	public void dateConverterTest(){
		Date date = new Date();

		LocalDateTime localDateTime = DateTimeConverterUtil.toLocalDateTime(date);
		Assert.assertNotNull(localDateTime);

		LocalDate localDate = DateTimeConverterUtil.toLocalDate(date);
		Assert.assertNotNull(localDate);

		LocalTime localTime = DateTimeConverterUtil.toLocalTime(date);
		Assert.assertNotNull(localTime);

		Instant instant = DateTimeConverterUtil.toInstant(date);
		Assert.assertNotNull(instant);
	}
	
	@Test
	public void localDateTimeConverterTest(){
		LocalDateTime ldt = LocalDateTime.now();

		Date date = DateTimeConverterUtil.toDate(ldt);
		Assert.assertNotNull(date);

		LocalDate localDate = DateTimeConverterUtil.toLocalDate(ldt);
		Assert.assertNotNull(localDate);

		LocalTime localTime = DateTimeConverterUtil.toLocalTime(ldt);
		Assert.assertNotNull(localTime);

		Instant instant = DateTimeConverterUtil.toInstant(ldt);
		Assert.assertNotNull(instant);

		ZonedDateTime zonedDateTime = DateTimeConverterUtil.toZonedDateTime(ldt);
		Assert.assertNotNull(zonedDateTime);
	}
	
	@Test
	public void localDateConverterTest(){
		LocalDate ld = LocalDate.now();
		Date date = DateTimeConverterUtil.toDate(ld);
		Assert.assertNotNull(date);

		LocalDateTime localDateTime = DateTimeConverterUtil.toLocalDateTime(ld);
		Assert.assertNotNull(localDateTime);

		Instant instant = DateTimeConverterUtil.toInstant(ld);
		Assert.assertNotNull(instant);
	}
	
	@Test
	public void localTimeConverterTest(){
		LocalTime lt = LocalTime.now();

		Date date = DateTimeConverterUtil.toDate(lt);
		Assert.assertNotNull(date);

		LocalDateTime localDateTime = DateTimeConverterUtil.toLocalDateTime(lt);
		Assert.assertNotNull(localDateTime);

		Instant instant = DateTimeConverterUtil.toInstant(lt);
		Assert.assertNotNull(instant);
	}	

	
	@Test
	public void instantConverterTest(){
		Instant instant = Instant.now();
		Date date = DateTimeConverterUtil.toDate(instant);
		Assert.assertNotNull(date);

		LocalDateTime localDateTime = DateTimeConverterUtil.toLocalDateTime(instant);
		Assert.assertNotNull(localDateTime);

		LocalDate localDate = DateTimeConverterUtil.toLocalDate(instant);
		Assert.assertNotNull(localDate);
	}
	
	@Test
	public void zonedDateTimeConverterTest(){
		//ToOther
		ZonedDateTime zonedDateTime = ZonedDateTime.now();

		Date date = DateTimeConverterUtil.toDate(zonedDateTime);
		Assert.assertNotNull(date);

		LocalDateTime localDateTime = DateTimeConverterUtil.toLocalDateTime(zonedDateTime);
		Assert.assertNotNull(localDateTime);

		LocalDate localDate = DateTimeConverterUtil.toLocalDate(zonedDateTime);
		Assert.assertNotNull(localDate);

		LocalTime localTime = DateTimeConverterUtil.toLocalTime(zonedDateTime);
		Assert.assertNotNull(localTime);

		Instant instant = DateTimeConverterUtil.toInstant(zonedDateTime);
		Assert.assertNotNull(instant);

		//toZonedDateTime
		ZonedDateTime zonedDateTime1 = DateTimeConverterUtil.toZonedDateTime(new Date());
		Assert.assertNotNull(zonedDateTime1);

		ZonedDateTime zonedDateTime2 = DateTimeConverterUtil.toZonedDateTime(LocalDateTime.now());
		Assert.assertNotNull(zonedDateTime2);

		ZonedDateTime zonedDateTime3 = DateTimeConverterUtil.toZonedDateTime(LocalDate.now());
		Assert.assertNotNull(zonedDateTime3);

		ZonedDateTime zonedDateTime4 = DateTimeConverterUtil.toZonedDateTime(LocalTime.now());
		Assert.assertNotNull(zonedDateTime4);

		ZonedDateTime zonedDateTime5 = DateTimeConverterUtil.toZonedDateTime(Instant.now());
		Assert.assertNotNull(zonedDateTime5);

		//Asia/Shanghai
		ZonedDateTime zonedDateTime6 = DateTimeConverterUtil.toZonedDateTime(LocalDateTime.now(), "Asia/Shanghai");
		Assert.assertNotNull(zonedDateTime6);
		
		//Asia/Shanghai转Europe/Paris
		ZonedDateTime zonedDateTime7 = DateTimeConverterUtil.toZonedDateTimeAndTransformZone(LocalDateTime.now(), "Europe/Paris");
		ZonedDateTime zonedDateTime8 = DateTimeConverterUtil.toZonedDateTime(new Date(), "Europe/Paris");
		Assert.assertEquals(zonedDateTime7.getZone(), zonedDateTime8.getZone());
	}
	
	@Test
	public void yearMonthConverterTest(){
		Date date = DateTimeFormatterUtil.parseDateStrToDate("2020-04-29");

		//转换为yearMonth
		YearMonth yearMonth = DateTimeConverterUtil.toYearMonth(date);
		Assert.assertEquals("2020-04", yearMonth.toString());

		//转换为当月15号
		Date date1 = DateTimeConverterUtil.toDate(yearMonth, 15);
		Assert.assertEquals(DateTimeFormatterUtil.parseDateStrToDate("2020-04-15"), date1);

		//转换为当月第一天
		Date date2 = DateTimeConverterUtil.toDateStartOfMonth(yearMonth);
		Assert.assertEquals(DateTimeFormatterUtil.parseDateStrToDate("2020-04-01"), date2);

		//转换为当月最后一天
		Date date3 = DateTimeConverterUtil.toDateEndOfMonth(yearMonth);
		Assert.assertEquals(DateTimeFormatterUtil.parseDateStrToDate("2020-04-30"), date3);

		//转换为当月15号
		LocalDate LocalDate1 = DateTimeConverterUtil.toLocalDate(yearMonth, 15);
		Assert.assertEquals(LocalDate.of(2020,4,15), LocalDate1);

		//转换为当月第一天
		LocalDate LocalDate2 = DateTimeConverterUtil.toLocalDateStartOfMonth(yearMonth);
		Assert.assertEquals(LocalDate.of(2020,4,1), LocalDate2);

		//转换为当月最后一天
		LocalDate LocalDate3 = DateTimeConverterUtil.toLocalDateEndOfMonth(yearMonth);
		Assert.assertEquals(LocalDate.of(2020,4,30), LocalDate3);
	}
	
	/**
	 * 时间戳转换测试
	 */
	@Test
	public void epochMilliConverterTest(){
		long epochMilli = 1588140134930L;
		//ToOther
		Date date = DateTimeConverterUtil.toDate(epochMilli);
		Assert.assertNotNull(date);

		LocalDateTime localDateTime = DateTimeConverterUtil.toLocalDateTime(epochMilli);
		Assert.assertNotNull(localDateTime);

		LocalDate localDate = DateTimeConverterUtil.toLocalDate(epochMilli);
		Assert.assertNotNull(localDate);

		Instant instant = DateTimeConverterUtil.toInstant(epochMilli);
		Assert.assertNotNull(instant);

		ZonedDateTime zonedDateTime = DateTimeConverterUtil.toZonedDateTime(epochMilli);
		Assert.assertNotNull(zonedDateTime);

		Timestamp timestamp = DateTimeConverterUtil.toTimestamp(epochMilli);
		Assert.assertNotNull(timestamp);

		//toEpochMilli
		Assert.assertEquals(epochMilli, DateTimeConverterUtil.toEpochMilli(date));

		Assert.assertEquals(epochMilli, DateTimeConverterUtil.toEpochMilli(localDateTime));

		Assert.assertEquals(epochMilli, DateTimeConverterUtil.toEpochMilli(instant));

		Assert.assertEquals(epochMilli, DateTimeConverterUtil.toEpochMilli(zonedDateTime));

		Assert.assertEquals(epochMilli, DateTimeConverterUtil.toEpochMilli(timestamp));
	}
	
	/**
	 * Timestamp转换测试
	 */
	@Test
	public void timestampConverterTest(){
		long epochMilli = 1588140134930L;
		Timestamp timestamp = new Timestamp(epochMilli);

		//ToOther
		LocalDateTime localDateTime = DateTimeConverterUtil.toLocalDateTime(timestamp);
		Assert.assertNotNull(localDateTime);

		Instant instant = DateTimeConverterUtil.toInstant(timestamp);
		Assert.assertNotNull(instant);

		Assert.assertEquals(epochMilli, DateTimeConverterUtil.toEpochMilli(timestamp));

		//toTimestamp
		Timestamp timestamp1 = DateTimeConverterUtil.toTimestamp(new Date());
		Assert.assertNotNull(timestamp1);

		Timestamp timestamp2 = DateTimeConverterUtil.toTimestamp(LocalDateTime.now());
		Assert.assertNotNull(timestamp2);

		Timestamp timestamp3 = DateTimeConverterUtil.toTimestamp(Instant.now());
		Assert.assertNotNull(timestamp3);

		Timestamp timestamp4 = DateTimeConverterUtil.toTimestamp(epochMilli);
		Assert.assertEquals(timestamp, timestamp4);
	}
	
	/**
	 * 天转换为其他单位测试
	 */
	@Test
	public void dayUnitConversionTest(){
		Assert.assertEquals(TimeUnit.DAYS.toHours(1), DateTimeConverterUtil.dayToHour(1));
		Assert.assertEquals(TimeUnit.DAYS.toMinutes(1), DateTimeConverterUtil.dayToMinute(1));
		Assert.assertEquals(TimeUnit.DAYS.toSeconds(1), DateTimeConverterUtil.dayToSecond(1));
		Assert.assertEquals(TimeUnit.DAYS.toMillis(1), DateTimeConverterUtil.dayToMillisecond(1));
	}
	
	/**
	 * 小时转换为其他单位测试
	 */
	@Test
	public void hourUnitConversionTest(){
		Assert.assertEquals(TimeUnit.HOURS.toMinutes(1), DateTimeConverterUtil.hourToMinute(1));
		Assert.assertEquals(TimeUnit.HOURS.toSeconds(1), DateTimeConverterUtil.hourToSecond(1));
		Assert.assertEquals(TimeUnit.HOURS.toMillis(1), DateTimeConverterUtil.hourToMillisecond(1));
		
		Assert.assertEquals(TimeUnit.HOURS.toDays(12), DateTimeConverterUtil.hourToDay(12));
		//精确计算，带1位小数
		Assert.assertEquals(0.5, DateTimeConverterUtil.hourToDayPrecise(12).doubleValue(), 0);
		Assert.assertEquals("0.5", DateTimeConverterUtil.hourToDayPreciseString(12));
		Assert.assertEquals("0", DateTimeConverterUtil.hourToDayPreciseString(1));
	}
	
	/**
	 * 分钟转换为其他单位测试
	 */
	@Test
	public void minuteUnitConversionTest(){
		Assert.assertEquals(TimeUnit.MINUTES.toSeconds(1), DateTimeConverterUtil.minuteToSecond(1));
		Assert.assertEquals(TimeUnit.MINUTES.toMillis(1), DateTimeConverterUtil.minuteToMillisecond(1));
		
		Assert.assertEquals(TimeUnit.MINUTES.toHours(30), DateTimeConverterUtil.minuteToHour(30));
		//精确计算，带1位小数
		Assert.assertEquals(0.5, DateTimeConverterUtil.minuteToHourPrecise(30).doubleValue(), 0);
		Assert.assertEquals("0.5", DateTimeConverterUtil.minuteToHourPreciseString(30));
		
		Assert.assertEquals(TimeUnit.MINUTES.toDays(12*60), DateTimeConverterUtil.minuteToDay(12*60));
		//精确计算，带1位小数
		Assert.assertEquals(0.5, DateTimeConverterUtil.minuteToDayPrecise(12*60).doubleValue(), 0);
		Assert.assertEquals("0.5", DateTimeConverterUtil.minuteToDayPreciseString(12*60));
	}
	
	/**
	 * 秒转换为其他单位测试
	 */
	@Test
	public void secondUnitConversionTest(){
		Assert.assertEquals(TimeUnit.SECONDS.toMillis(1), DateTimeConverterUtil.secondToMillisecond(1));
		
		Assert.assertEquals(TimeUnit.SECONDS.toMinutes(1), DateTimeConverterUtil.secondToMinute(30));
		//精确计算，带1位小数
		Assert.assertEquals(0.5, DateTimeConverterUtil.secondToMinutePrecise(30).doubleValue(), 0);
		Assert.assertEquals("0.5", DateTimeConverterUtil.secondToMinutePreciseString(30));
		
		Assert.assertEquals(TimeUnit.SECONDS.toHours(1), DateTimeConverterUtil.secondToHour(30*60));
		//精确计算，带1位小数
		Assert.assertEquals(0.5, DateTimeConverterUtil.secondToHourPrecise(30*60).doubleValue(), 0);
		Assert.assertEquals("0.5", DateTimeConverterUtil.secondToHourPreciseString(30*60));
		
		Assert.assertEquals(TimeUnit.SECONDS.toDays(1), DateTimeConverterUtil.secondToDay(12*60*60));
		//精确计算，带1位小数
		Assert.assertEquals(0.5, DateTimeConverterUtil.secondToDayPrecise(12*60*60).doubleValue(), 0);
		Assert.assertEquals("0.5", DateTimeConverterUtil.secondToDayPreciseString(12*60*60));
		Assert.assertEquals("0", DateTimeConverterUtil.secondToDayPreciseString(10));
	}
	
	/**
	 * 毫秒转换为其他单位测试
	 */
	@Test
	public void millisecondUnitConversionTest(){
		Assert.assertEquals(TimeUnit.MILLISECONDS.toSeconds(500), DateTimeConverterUtil.millisecondToSecond(500));
		//精确计算，带1位小数
		Assert.assertEquals(0.5, DateTimeConverterUtil.millisecondToSecondPrecise(500).doubleValue(), 0);
		Assert.assertEquals("0.5", DateTimeConverterUtil.millisecondToSecondPreciseString(500));
		//精确计算，带3位小数
		Assert.assertEquals(0.123, DateTimeConverterUtil.millisecondToSecondPreciseThree(123).doubleValue(), 0);
		Assert.assertEquals("0.11", DateTimeConverterUtil.millisecondToSecondPreciseThreeString(110));
		Assert.assertEquals("0.110", DateTimeConverterUtil.millisecondToSecondPreciseThreePlainString(110));
		
		Assert.assertEquals(TimeUnit.MILLISECONDS.toMinutes(30*1000), DateTimeConverterUtil.millisecondToMinute(30*1000));
		//精确计算，带1位小数
		Assert.assertEquals(0.5, DateTimeConverterUtil.millisecondToMinutePrecise(30*1000).doubleValue(), 0);
		Assert.assertEquals("0.5", DateTimeConverterUtil.millisecondToMinutePreciseString(30*1000));
		
		Assert.assertEquals(TimeUnit.MILLISECONDS.toHours(30*60*1000), DateTimeConverterUtil.millisecondToHour(30*60*1000));
		//精确计算，带1位小数
		Assert.assertEquals(0.5, DateTimeConverterUtil.millisecondToHourPrecise(30*60*1000).doubleValue(), 0);
		Assert.assertEquals("0.5", DateTimeConverterUtil.millisecondToHourPreciseString(30*60*1000));
		
		Assert.assertEquals(TimeUnit.MILLISECONDS.toDays(12*60*60*1000), DateTimeConverterUtil.millisecondToDay(12*60*60*1000));
		//精确计算，带1位小数
		Assert.assertEquals(0.5, DateTimeConverterUtil.millisecondToDayPrecise(12*60*60*1000).doubleValue(), 0);
		Assert.assertEquals("0.5", DateTimeConverterUtil.millisecondToDayPreciseString(12*60*60*1000));
	}
	
	@Test
	public void temporalAccessorToZonedDateTimeTest(){
		TemporalAccessor temporalInstant = Instant.now();
		DateTimeConverterUtil.toZonedDateTime(temporalInstant);
		
		TemporalAccessor temporalLocalDate = LocalDate.now();
		DateTimeConverterUtil.toZonedDateTime(temporalLocalDate);
		
		TemporalAccessor temporalLocalDateTime = LocalDateTime.now();
		DateTimeConverterUtil.toZonedDateTime(temporalLocalDateTime);
		
		TemporalAccessor temporalLocalTime = LocalTime.now();
		DateTimeConverterUtil.toZonedDateTime(temporalLocalTime);
		
		TemporalAccessor temporalZonedDateTime = ZonedDateTime.now();
		DateTimeConverterUtil.toZonedDateTime(temporalZonedDateTime);
		
		TemporalAccessor temporalOffsetDateTime = OffsetDateTime.now();
		DateTimeConverterUtil.toZonedDateTime(temporalOffsetDateTime);
		
		TemporalAccessor temporalOffsetTime = OffsetTime.now();
		DateTimeConverterUtil.toZonedDateTime(temporalOffsetTime);
		
		TemporalAccessor temporalLunarDate = LunarDate.now();
		DateTimeConverterUtil.toZonedDateTime(temporalLunarDate);
		
	}
	
	@Test
	public void temporalAccessorConverterTest(){
		TemporalAccessor temporal = ZonedDateTime.now();

		Date date = DateTimeConverterUtil.toDate(temporal);
		Assert.assertNotNull(date);
		
		LocalDateTime localDateTime = DateTimeConverterUtil.toLocalDateTime(temporal);
		Assert.assertNotNull(localDateTime);

		LocalDate localDate = DateTimeConverterUtil.toLocalDate(temporal);
		Assert.assertNotNull(localDate);

		LocalTime localTime = DateTimeConverterUtil.toLocalTime(temporal);
		Assert.assertNotNull(localTime);

		Instant instant = DateTimeConverterUtil.toInstant(temporal);
		Assert.assertNotNull(instant);

		ZonedDateTime zonedDateTime = DateTimeConverterUtil.toZonedDateTime(temporal);
		Assert.assertNotNull(zonedDateTime);
		
		Timestamp timestamp = DateTimeConverterUtil.toTimestamp(temporal);
		Assert.assertNotNull(timestamp);
		
		YearMonth yearMonth = DateTimeConverterUtil.toYearMonth(temporal);
		Assert.assertNotNull(yearMonth);
		
		long tpochMilli = DateTimeConverterUtil.toEpochMilli(temporal);
		Assert.assertTrue(tpochMilli >0);
	}
	
	/**
	 * LocalTime转时间毫秒测试
	 */
	@Test
	public void timeMilliTest(){
		//时间部分毫秒值转换
		Assert.assertEquals(79800000, DateTimeConverterUtil.toTimeMilli(LocalTime.of(22, 10, 0)));
		Assert.assertEquals(LocalTime.of(22, 10, 0), DateTimeConverterUtil.toLocalTime(79800000));
		
		//时间戳毫秒值转换
		Assert.assertEquals(LocalTime.of(22, 10, 0), DateTimeConverterUtil.toLocalTime(1632147000000L));
	}
}
