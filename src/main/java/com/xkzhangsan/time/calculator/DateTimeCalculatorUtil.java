package com.xkzhangsan.time.calculator;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
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
	
	// get other properties
	
	public static int getDayOfWeek(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getDayOfWeek().getValue();
	}
	
	public static int getDayOfWeek(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getDayOfWeek().getValue();
	}
	
	public static LocalDate lastDayOfMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusters.lastDayOfMonth());
	}
	
	public static LocalDateTime lastDayOfMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.with(TemporalAdjusters.lastDayOfMonth());
	}	
	
	public static Date lastDayOfMonth(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDate(date).with(TemporalAdjusters.lastDayOfMonth()));
	}	
	
	/**
	 * 判断是否闰年
	 * @param localDate
	 * @return
	 */
	public boolean isLeapYear(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.isLeapYear();
	}
	
	/**
	 * 判断是否闰年
	 * @param localDateTime
	 * @return
	 */
	public boolean isLeapYear(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().isLeapYear();
	}
	
	/**
	 * 判断是否闰年
	 * @param date
	 * @return
	 */
	public boolean isLeapYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().isLeapYear();
	}
	
	/**
	 * 获取月的天数
	 * @param localDate
	 * @return
	 */
	public int lengthOfMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.lengthOfMonth();
	}
	
	/**
	 * 获取月的天数
	 * @param localDateTime
	 * @return
	 */
	public int lengthOfMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().lengthOfMonth();
	}
	
	/**
	 * 获取月的天数
	 * @param date
	 * @return
	 */
	public int lengthOfMonth(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().lengthOfMonth();
	}
	
	/**
	 *  获取年的天数
	 * @param localDate
	 * @return
	 */
	public int lengthOfYear(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.lengthOfYear();
	}
	
	/**
	 * 获取年的天数
	 * @param localDateTime
	 * @return
	 */
	public int lengthOfYear(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().lengthOfYear();
	}
	
	/**
	 * 获取年的天数
	 * @param date
	 * @return
	 */
	public int lengthOfYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().lengthOfYear();
	}	
	
	public static LocalDate next(LocalDate localDate, DayOfWeek dayOfWeek){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusters.next(dayOfWeek));
	}
	
	public static LocalDateTime next(LocalDateTime localDateTime, DayOfWeek dayOfWeek){
		return localDateTime.with(TemporalAdjusters.next(dayOfWeek));
	}

	public static Date next(Date date, DayOfWeek dayOfWeek){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDate(date).with(TemporalAdjusters.next(dayOfWeek)));
	}
	
	public static LocalDate previous(LocalDate localDate, DayOfWeek dayOfWeek){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusters.previous(dayOfWeek));
	}
	
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
	
	// plus two times
	
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
	
	// get the difference between two times
	
	/**
	 * 获取2个日期的相差毫秒值
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenMilliseconds(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return durationBetween(startInclusive, endExclusive).toMillis();
	}
	
	/**
	 * 获取2个日期的相差毫秒值
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long betweenMilliseconds(Date date1, Date date2){
		Objects.requireNonNull(date1, "date1");
		Objects.requireNonNull(date2, "date2");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(date1), DateTimeConverterUtil.toLocalDateTime(date2)).toMillis();
	}
	
	/**
	 * 获取2个日期的相差天数，更准确的天数计算，包括时间部分
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenDays(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toDays();
	}
	
	/**
	 * 获取2个日期的相差天数，更准确的天数计算，包括时间部分
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long betweenDays(Date date1, Date date2){
		Objects.requireNonNull(date1, "date1");
		Objects.requireNonNull(date2, "date2");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(date1), DateTimeConverterUtil.toLocalDateTime(date2)).toDays();
	}	
	
	/**
	 * 获取2个日期的相差年月天的天数部分，粗略计算天数，不关心时间部分
	 * @param startDateInclusive
	 * @param endDateExclusive
	 * @return
	 */
	public static int betweenDays(LocalDate startDateInclusive, LocalDate endDateExclusive){
		return periodBetween(startDateInclusive, endDateExclusive).getDays();
	}
	
	// base operation
	
	public static Temporal plus(Temporal temporal, TemporalUnit unit, long amountToAdd) {
		Objects.requireNonNull(temporal, "temporal");
		return temporal.plus(amountToAdd, unit);
	}
	
	public static Date plus(Date date, TemporalUnit unit, long amountToAdd) {
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).plus(amountToAdd, unit));
	}
	
	public static Temporal minus(Temporal temporal, TemporalUnit unit, long amountToSubtract) {
		Objects.requireNonNull(temporal, "temporal");
		return temporal.minus(amountToSubtract, unit);
	}
	
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
	 * 获取2个日期的相差，时间部分
	 * @param startInclusive
	 * @param endExclusive
	 * @return Duration
	 */
	public static Duration durationBetween(Temporal startInclusive, Temporal endExclusive){
		return Duration.between(startInclusive, endExclusive);
	}
	
	/**
	 * 获取2个日期的相差，日期部分
	 * @param startDateInclusive
	 * @param endDateExclusive
	 * @return Period
	 */
	public static Period periodBetween(LocalDate startDateInclusive, LocalDate endDateExclusive){
		return Period.between(startDateInclusive, endDateExclusive);
	}	
	
}
