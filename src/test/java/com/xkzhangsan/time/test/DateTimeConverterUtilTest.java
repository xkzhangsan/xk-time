package com.xkzhangsan.time.test;

import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.util.Date;

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
}
