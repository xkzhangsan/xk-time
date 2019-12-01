package com.xkzhangsan.time.calculator;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Objects;

import com.xkzhangsan.time.calculator.adjuster.NextWorkDay;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;

public class DateTimeCalculatorUtil {
	
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
	
	public static long getMilliseconds(){
		return Instant.now().getEpochSecond();
	}
	
	public static int getDayOfWeek(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getDayOfWeek().getValue();
	}
	
	public static int getDayOfWeek(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getDayOfWeek().getValue();
	}	

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
	
	public static Temporal setField(Temporal temporal, TemporalField field, long newValue) {
		Objects.requireNonNull(temporal, "temporal");
		return temporal.with(field, newValue);
	}
	
	public static Date setField(Date date, TemporalField field, long newValue) {
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).with(field, newValue));
	}	
	
	public static long betweenMilliseconds(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toMillis();
	}
	
	public static long betweenMilliseconds(Date date1, Date date2){
		Objects.requireNonNull(date1, "date1");
		Objects.requireNonNull(date2, "date2");
		return Duration.between(DateTimeConverterUtil.toLocalDateTime(date1), DateTimeConverterUtil.toLocalDateTime(date2)).toMillis();
	}
	
	/**
	 * 
	 * @Title: betweenDuration
	 * @Description: day hour minute milliseconds nanoseconds
	 * @param startInclusive
	 * @param endExclusive
	 * @return Duration
	 * @throws
	 */
	public static Duration betweenDuration(Temporal startInclusive, Temporal endExclusive){
		return Duration.between(startInclusive, endExclusive);
	}
	
	/**
	 * @Title: betweenDays
	 * @Description: 更准确的天数计算，包括时间部分
	 * @param startInclusive
	 * @param endExclusive
	 * @return long
	 * @throws
	 */
	public static long betweenDays(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toDays();
	}
	
	public static long betweenDays(Date date1, Date date2){
		Objects.requireNonNull(date1, "date1");
		Objects.requireNonNull(date2, "date2");
		return Duration.between(DateTimeConverterUtil.toLocalDateTime(date1), DateTimeConverterUtil.toLocalDateTime(date2)).toDays();
	}	
	
	/**
	 * 
	 * @Title: betweenDays
	 * @Description: 粗略计算天数，不关心时间部分
	 * @param startDateInclusive
	 * @param endDateExclusive
	 * @return int
	 * @throws
	 */
	public static int betweenDays(LocalDate startDateInclusive, LocalDate endDateExclusive){
		return Period.between(startDateInclusive, endDateExclusive).getDays();
	}
	
	/**
	 * 
	 * @Title: betweenPeriod
	 * @Description: year month day,such as '2 years, 3 months and 4 days' 粗略计算天数，不关心时间部分
	 * @param startDateInclusive
	 * @param endDateExclusive
	 * @return Period
	 * @throws
	 */
	public static Period betweenPeriod(LocalDate startDateInclusive, LocalDate endDateExclusive){
		return Period.between(startDateInclusive, endDateExclusive);
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
		return localDate.with(new NextWorkDay());
	}
	
	public static LocalDateTime nextWorkDay(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.with(new NextWorkDay());
	}
	
	public static Date nextWorkDay(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDate(date).with(new NextWorkDay()));
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
	
	public boolean isLeapYear(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.isLeapYear();
	}
	
	public boolean isLeapYear(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().isLeapYear();
	}
	
	public boolean isLeapYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().isLeapYear();
	}
	
	public int lengthOfMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.lengthOfMonth();
	}
	
	public int lengthOfMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().lengthOfMonth();
	}
	
	public int lengthOfMonth(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().lengthOfMonth();
	}
	
	public int lengthOfYear(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.lengthOfYear();
	}
	
	public int lengthOfYear(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().lengthOfYear();
	}
	
	public int lengthOfYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().lengthOfYear();
	}
	
}
