package com.xkzhangsan.time.calculator;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Objects;

import com.xkzhangsan.time.TemporalAdjusterExtension;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;

/**
 * 日期计算工具类
 * 包括：
 * 1.获取时间属性方法，get* 比如getYear(Date date) 获取年部分
 * 2.获取时间加操作方法，plus* 比如plusYears(Date date, long amountToAdd) 当前时间年增加amountToAdd值
 * 3.获取时间减操作方法，minus* 比如minusYears(Date date, long amountToAdd) 当前时间年减少amountToAdd值
 * 4.获取时间修改属性方法，with* 比如withYear(Date date, long newValue) 修改当前时间年值为newValue
 * 5.获取比较2个时间方法，between* 比如betweenYears(Date startInclusive, Date endExclusive) 比较2个时间，获取年部分
 * 6.其他常用方法，比如isLeapYear(Date date) 判断是否闰年
 * 
* @ClassName: DateTimeCalculatorUtil 
* @Description:  DateTime Calculator
* @author xkzhangsan
* @date 2019年12月1日
*
 */
public class DateTimeCalculatorUtil {
	
	// get base time property
	
	public static int getYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getYear();
	}
	
	public static int getYear(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getYear();
	}
	
	public static int getMonth(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getMonthValue();
	}
	
	public static int getMonth(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getMonthValue();
	}
	
	public static int getDayOfMonth(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getDayOfMonth();
	}
	
	public static int getDayOfMonth(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getDayOfMonth();
	}
	
	public static int getDayOfYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getDayOfYear();
	}
	
	public static int getDayOfYear(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getDayOfYear();
	}
	
	public static int getHour(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getHour();
	}
	
	public static int getHour(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getHour();
	}	
	
	public static int getMinute(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getMinute();
	}
	
	public static int getMinute(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getMinute();
	}	
	
	public static int getSecond(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getSecond();
	}
	
	public static int getSecond(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getSecond();
	}	
	
	public static long getEpochMilli(){
		return Instant.now().toEpochMilli();
	}
	
	// plus two times
	
	public static Date plusYears(Date date, long amountToAdd){
		return plus(date, ChronoUnit.YEARS, amountToAdd);
	}
	
	public static LocalDateTime plusYears(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.YEARS, amountToAdd);
	}
	
	public static LocalDate plusYears(LocalDate localDate, long amountToAdd){
		return (LocalDate) plus(localDate, ChronoUnit.YEARS, amountToAdd);
	}
	
	public static Date plusMonths(Date date, long amountToAdd){
		return plus(date, ChronoUnit.MONTHS, amountToAdd);
	}
	
	public static LocalDateTime plusMonths(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.MONTHS, amountToAdd);
	}
	
	public static LocalDate plusMonths(LocalDate localDate, long amountToAdd){
		return (LocalDate) plus(localDate, ChronoUnit.MONTHS, amountToAdd);
	}
	
	public static Date plusWeeks(Date date, long amountToAdd){
		return plus(date, ChronoUnit.WEEKS, amountToAdd);
	}
	
	public static LocalDateTime plusWeeks(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.WEEKS, amountToAdd);
	}
	
	public static LocalDate plusWeeks(LocalDate localDate, long amountToAdd){
		return (LocalDate) plus(localDate, ChronoUnit.WEEKS, amountToAdd);
	}	
	
	public static Date plusDays(Date date, long amountToAdd){
		return plus(date, ChronoUnit.DAYS, amountToAdd);
	}
	
	public static LocalDateTime plusDays(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.DAYS, amountToAdd);
	}
	
	public static LocalDate plusDays(LocalDate localDate, long amountToAdd){
		return (LocalDate) plus(localDate, ChronoUnit.DAYS, amountToAdd);
	}
	
	public static Date plusHours(Date date, long amountToAdd){
		return plus(date, ChronoUnit.HOURS, amountToAdd);
	}
	
	public static LocalDateTime plusHours(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.HOURS, amountToAdd);
	}
	
	public static LocalTime plusHours(LocalTime localTime, long amountToAdd){
		return (LocalTime) plus(localTime, ChronoUnit.HOURS, amountToAdd);
	}
	
	public static Date plusMinutes(Date date, long amountToAdd){
		return plus(date, ChronoUnit.MINUTES, amountToAdd);
	}
	
	public static LocalDateTime plusMinutes(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.MINUTES, amountToAdd);
	}
	
	public static LocalTime plusMinutes(LocalTime localTime, long amountToAdd){
		return (LocalTime) plus(localTime, ChronoUnit.MINUTES, amountToAdd);
	}
	
	public static Date plusSeconds(Date date, long amountToAdd){
		return plus(date, ChronoUnit.SECONDS, amountToAdd);
	}
	
	public static LocalDateTime plusSeconds(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.SECONDS, amountToAdd);
	}
	
	public static LocalTime plusSeconds(LocalTime localTime, long amountToAdd){
		return (LocalTime) plus(localTime, ChronoUnit.SECONDS, amountToAdd);
	}	
	
	// minus two times
	
	public static Date minusYears(Date date, long amountToAdd){
		return minus(date, ChronoUnit.YEARS, amountToAdd);
	}
	
	public static LocalDateTime minusYears(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.YEARS, amountToAdd);
	}
	
	public static LocalDate minusYears(LocalDate localDate, long amountToAdd){
		return (LocalDate) minus(localDate, ChronoUnit.YEARS, amountToAdd);
	}
	
	public static Date minusMonths(Date date, long amountToAdd){
		return minus(date, ChronoUnit.MONTHS, amountToAdd);
	}
	
	public static LocalDateTime minusMonths(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.MONTHS, amountToAdd);
	}
	
	public static LocalDate minusMonths(LocalDate localDate, long amountToAdd){
		return (LocalDate) minus(localDate, ChronoUnit.MONTHS, amountToAdd);
	}
	
	public static Date minusWeeks(Date date, long amountToAdd){
		return minus(date, ChronoUnit.WEEKS, amountToAdd);
	}
	
	public static LocalDateTime minusWeeks(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.WEEKS, amountToAdd);
	}
	
	public static LocalDate minusWeeks(LocalDate localDate, long amountToAdd){
		return (LocalDate) minus(localDate, ChronoUnit.WEEKS, amountToAdd);
	}	
	
	public static Date minusDays(Date date, long amountToAdd){
		return minus(date, ChronoUnit.DAYS, amountToAdd);
	}
	
	public static LocalDateTime minusDays(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.DAYS, amountToAdd);
	}
	
	public static LocalDate minusDays(LocalDate localDate, long amountToAdd){
		return (LocalDate) minus(localDate, ChronoUnit.DAYS, amountToAdd);
	}
	
	public static Date minusHours(Date date, long amountToAdd){
		return minus(date, ChronoUnit.HOURS, amountToAdd);
	}
	
	public static LocalDateTime minusHours(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.HOURS, amountToAdd);
	}
	
	public static LocalTime minusHours(LocalTime localTime, long amountToAdd){
		return (LocalTime) minus(localTime, ChronoUnit.HOURS, amountToAdd);
	}
	
	public static Date minusMinutes(Date date, long amountToAdd){
		return minus(date, ChronoUnit.MINUTES, amountToAdd);
	}
	
	public static LocalDateTime minusMinutes(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.MINUTES, amountToAdd);
	}
	
	public static LocalTime minusMinutes(LocalTime localTime, long amountToAdd){
		return (LocalTime) minus(localTime, ChronoUnit.MINUTES, amountToAdd);
	}
	
	public static Date minusSeconds(Date date, long amountToAdd){
		return minus(date, ChronoUnit.SECONDS, amountToAdd);
	}
	
	public static LocalDateTime minusSeconds(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.SECONDS, amountToAdd);
	}
	
	public static LocalTime minusSeconds(LocalTime localTime, long amountToAdd){
		return (LocalTime) minus(localTime, ChronoUnit.SECONDS, amountToAdd);
	}
	
	// modify property
	
	public static Date withYear(Date date, long newValue){
		return with(date, ChronoField.YEAR, newValue);
	}
	
	public static LocalDateTime withYear(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.YEAR, newValue);
	}
	
	public static LocalDate withYear(LocalDate localDate, long newValue){
		return (LocalDate) with(localDate, ChronoField.YEAR, newValue);
	}
	
	public static Date withMonth(Date date, long newValue){
		return with(date, ChronoField.MONTH_OF_YEAR, newValue);
	}
	
	public static LocalDateTime withMonth(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.MONTH_OF_YEAR, newValue);
	}
	
	public static LocalDate withMonth(LocalDate localDate, long newValue){
		return (LocalDate) with(localDate, ChronoField.MONTH_OF_YEAR, newValue);
	}
	
	public static Date withDayOfMonth(Date date, long newValue){
		return with(date, ChronoField.DAY_OF_MONTH, newValue);
	}
	
	public static LocalDateTime withDayOfMonth(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.DAY_OF_MONTH, newValue);
	}
	
	public static LocalDate withDayOfMonth(LocalDate localDate, long newValue){
		return (LocalDate) with(localDate, ChronoField.DAY_OF_MONTH, newValue);
	}	
	
	public static Date withDayOfYear(Date date, long newValue){
		return with(date, ChronoField.DAY_OF_YEAR, newValue);
	}
	
	public static LocalDateTime withDayOfYear(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.DAY_OF_YEAR, newValue);
	}
	
	public static LocalDate withDayOfYear(LocalDate localDate, long newValue){
		return (LocalDate) with(localDate, ChronoField.DAY_OF_YEAR, newValue);
	}
	
	public static Date withHour(Date date, long newValue){
		return with(date, ChronoField.HOUR_OF_DAY, newValue);
	}
	
	public static LocalDateTime withHour(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.HOUR_OF_DAY, newValue);
	}
	
	public static LocalTime withHour(LocalTime localTime, long newValue){
		return (LocalTime) with(localTime, ChronoField.HOUR_OF_DAY, newValue);
	}
	
	public static Date withMinute(Date date, long newValue){
		return with(date, ChronoField.MINUTE_OF_HOUR, newValue);
	}
	
	public static LocalDateTime withMinute(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.MINUTE_OF_HOUR, newValue);
	}
	
	public static LocalTime withMinute(LocalTime localTime, long newValue){
		return (LocalTime) with(localTime, ChronoField.MINUTE_OF_HOUR, newValue);
	}
	
	public static Date withSecond(Date date, long newValue){
		return with(date, ChronoField.SECOND_OF_MINUTE, newValue);
	}
	
	public static LocalDateTime withSecond(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.SECOND_OF_MINUTE, newValue);
	}
	
	public static LocalTime withSecond(LocalTime localTime, long newValue){
		return (LocalTime) with(localTime, ChronoField.SECOND_OF_MINUTE, newValue);
	}
	
	// get the difference between two times
	
	/**
	 * 获取2个日期的相差年月天的年数部分
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenYears(LocalDateTime startInclusive, LocalDateTime endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getYears();
	}
	
	/**
	 * 获取2个日期的相差年月天的年数部分
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenYears(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getYears();
	}
	
	/**
	 * 获取2个日期的相差年月天的年数部分
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenYears(LocalDate startInclusive, LocalDate endExclusive){
		return Period.between(startInclusive, endExclusive).getYears();
	}
	
	/**
	 * 获取2个日期的相差年月天的月数部分
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenMonths(LocalDateTime startInclusive, LocalDateTime endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getMonths();
	}
	
	/**
	 * 获取2个日期的相差年月天的月数部分
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenMonths(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getMonths();
	}
	
	/**
	 * 获取2个日期的相差年月天的月数部分
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenMonths(LocalDate startInclusive, LocalDate endExclusive){
		return Period.between(startInclusive, endExclusive).getMonths();
	}
	
	/**
	 * 获取2个日期的相差年月天的天数部分
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenDays(LocalDateTime startInclusive, LocalDateTime endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getDays();
	}
	
	/**
	 * 获取2个日期的相差年月天的天数部分
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenDays(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getDays();
	}
	
	/**
	 * 获取2个日期的相差年月天的天数部分
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenDays(LocalDate startInclusive, LocalDate endExclusive){
		return Period.between(startInclusive, endExclusive).getDays();
	}
	
	/**
	 * 获取2个日期的相差总天数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalDays(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toDays();
	}
	
	/**
	 * 获取2个日期的相差总天数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalDays(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toDays();
	}
	
	/**
	 * 获取2个日期的相差总小时数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalHours(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toHours();
	}
	
	/**
	 * 获取2个日期的相差总小时数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalHours(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toHours();
	}
	
	/**
	 * 获取2个日期的相差总小时数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalHours(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toHours();
	}
	
	/**
	 * 获取2个日期的相差总分钟数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalMinutes(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toMinutes();
	}
	
	/**
	 * 获取2个日期的相差总分钟数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalMinutes(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toMinutes();
	}
	
	/**
	 * 获取2个日期的相差总分钟数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalMinutes(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toMinutes();
	}
	
	/**
	 * 获取2个日期的相差总秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalSeconds(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).getSeconds();
	}
	
	/**
	 * 获取2个日期的相差总秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalSeconds(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).getSeconds();
	}
	
	/**
	 * 获取2个日期的相差总秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalSeconds(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).getSeconds();
	}
	
	/**
	 * 获取2个日期的相差总毫秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalMillis(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toMillis();
	}
	
	/**
	 * 获取2个日期的相差总毫秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalMillis(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toMillis();
	}
	
	/**
	 * 获取2个日期的相差总毫秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalMillis(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toMillis();
	}	
	
	/**
	 * 获取2个日期的相差总纳秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalNanos(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toNanos();
	}
	
	/**
	 * 获取2个日期的相差总纳秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalNanos(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toNanos();
	}
	
	/**
	 * 获取2个日期的相差总纳秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalNanos(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toNanos();
	}
	
	
	// get other properties
	
	/**
	 * 获取星期值 1-7，星期一到星期日
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getDayOfWeek().getValue();
	}
	
	/**
	 * 获取星期值 1-7，星期一到星期日
	 * @param localDateTime
	 * @return
	 */
	public static int getDayOfWeek(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getDayOfWeek().getValue();
	}
	
	/**
	 * 获取星期值 1-7，星期一到星期日
	 * @param instant
	 * @return
	 */
	public static int getDayOfWeek(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getDayOfWeek().getValue();
	}
	
	/**
	 * 获取星期值当前月的最后一天
	 * @param localDate
	 * @return
	 */
	public static LocalDate lastDayOfMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusters.lastDayOfMonth());
	}
	
	/**
	 * 获取星期值当前月的最后一天
	 * @param localDateTime
	 * @return
	 */
	public static LocalDateTime lastDayOfMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.with(TemporalAdjusters.lastDayOfMonth());
	}	
	
	/**
	 * 获取星期值当前月的最后一天
	 * @param date
	 * @return
	 */
	public static Date lastDayOfMonth(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDate(date).with(TemporalAdjusters.lastDayOfMonth()));
	}	
	
	/**
	 * 判断是否闰年
	 * @param localDate
	 * @return
	 */
	public static boolean isLeapYear(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.isLeapYear();
	}
	
	/**
	 * 判断是否闰年
	 * @param localDateTime
	 * @return
	 */
	public static boolean isLeapYear(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().isLeapYear();
	}
	
	/**
	 * 判断是否闰年
	 * @param date
	 * @return
	 */
	public static boolean isLeapYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().isLeapYear();
	}
	
	/**
	 * 获取月的天数
	 * @param localDate
	 * @return
	 */
	public static int lengthOfMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.lengthOfMonth();
	}
	
	/**
	 * 获取月的天数
	 * @param localDateTime
	 * @return
	 */
	public static int lengthOfMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().lengthOfMonth();
	}
	
	/**
	 * 获取月的天数
	 * @param date
	 * @return
	 */
	public static int lengthOfMonth(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().lengthOfMonth();
	}
	
	/**
	 *  获取年的天数
	 * @param localDate
	 * @return
	 */
	public static int lengthOfYear(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.lengthOfYear();
	}
	
	/**
	 * 获取年的天数
	 * @param localDateTime
	 * @return
	 */
	public static int lengthOfYear(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().lengthOfYear();
	}
	
	/**
	 * 获取年的天数
	 * @param date
	 * @return
	 */
	public static int lengthOfYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().lengthOfYear();
	}	
	
	/**
	 * 下一个星期几
	 * @param localDate
	 * @param dayOfWeek
	 * @return
	 */
	public static LocalDate next(LocalDate localDate, DayOfWeek dayOfWeek){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusters.next(dayOfWeek));
	}
	
	/**
	 * 下一个星期几
	 * @param localDateTime
	 * @param dayOfWeek
	 * @return
	 */
	public static LocalDateTime next(LocalDateTime localDateTime, DayOfWeek dayOfWeek){
		return localDateTime.with(TemporalAdjusters.next(dayOfWeek));
	}

	/**
	 * 下一个星期几
	 * @param date
	 * @param dayOfWeek
	 * @return
	 */
	public static Date next(Date date, DayOfWeek dayOfWeek){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDate(date).with(TemporalAdjusters.next(dayOfWeek)));
	}
	
	
	/**
	 * 上一个星期几
	 * @param localDate
	 * @param dayOfWeek
	 * @return
	 */
	public static LocalDate previous(LocalDate localDate, DayOfWeek dayOfWeek){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusters.previous(dayOfWeek));
	}
	
	/**
	 * 上一个星期几
	 * @param localDateTime
	 * @param dayOfWeek
	 * @return
	 */
	public static LocalDateTime previous(LocalDateTime localDateTime, DayOfWeek dayOfWeek){
		return localDateTime.with(TemporalAdjusters.previous(dayOfWeek));
	}

	/**
	 * 上一个星期几
	 * @param date
	 * @param dayOfWeek
	 * @return
	 */
	public static Date previous(Date date, DayOfWeek dayOfWeek){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDate(date).with(TemporalAdjusters.previous(dayOfWeek)));
	}
	
	/**
	 * 获下一个工作日
	 * @param localDate
	 * @return
	 */
	public static LocalDate nextWorkDay(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusterExtension.nextWorkDay());
	}
	
	/**
	 * 获下一个工作日
	 * @param localDateTime
	 * @return
	 */
	public static LocalDateTime nextWorkDay(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.with(TemporalAdjusterExtension.nextWorkDay());
	}
	
	/**
	 * 获下一个工作日
	 * @param date
	 * @return
	 */
	public static Date nextWorkDay(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDate(date).with(TemporalAdjusterExtension.nextWorkDay()));
	}	
	
	// base operation
	
	/**
	 * 日期加操作
	 * @param temporal
	 * @param unit
	 * @param amountToAdd
	 * @return
	 */
	public static Temporal plus(Temporal temporal, TemporalUnit unit, long amountToAdd) {
		Objects.requireNonNull(temporal, "temporal");
		return temporal.plus(amountToAdd, unit);
	}
	
	/**
	 * date日期加操作
	 * @param date
	 * @param unit
	 * @param amountToAdd
	 * @return
	 */
	public static Date plus(Date date, TemporalUnit unit, long amountToAdd) {
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).plus(amountToAdd, unit));
	}
	
	/**
	 * 日期减操作
	 * @param temporal
	 * @param unit
	 * @param amountToSubtract
	 * @return
	 */
	public static Temporal minus(Temporal temporal, TemporalUnit unit, long amountToSubtract) {
		Objects.requireNonNull(temporal, "temporal");
		return temporal.minus(amountToSubtract, unit);
	}
	
	/**
	 * date日期减操作
	 * @param date
	 * @param unit
	 * @param amountToSubtract
	 * @return
	 */
	public static Date minus(Date date, TemporalUnit unit, long amountToSubtract) {
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).minus(amountToSubtract, unit));
	}
	
	/**
	 * 根据field修改属性
	 * @param temporal
	 * @param field
	 * @param newValue
	 * @return
	 */
	public static Temporal with(Temporal temporal, TemporalField field, long newValue) {
		Objects.requireNonNull(temporal, "temporal");
		return temporal.with(field, newValue);
	}
	
	/**
	 * 根据field修改属性
	 * @param date
	 * @param field
	 * @param newValue
	 * @return
	 */
	public static Date with(Date date, TemporalField field, long newValue) {
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).with(field, newValue));
	}
	
	/**
	 * 获取2个日期的总的天时分秒毫秒纳秒
	 * @param startInclusive
	 * @param endExclusive
	 * @return Duration
	 */
	public static Duration durationBetween(Temporal startInclusive, Temporal endExclusive){
		return Duration.between(startInclusive, endExclusive);
	}
	
	/**
	 * 获取2个日期的相差年月日部分属性
	 * @param startDateInclusive
	 * @param endDateExclusive
	 * @return Period
	 */
	public static Period periodBetween(LocalDate startDateInclusive, LocalDate endDateExclusive){
		return Period.between(startDateInclusive, endDateExclusive);
	}	
	
}
