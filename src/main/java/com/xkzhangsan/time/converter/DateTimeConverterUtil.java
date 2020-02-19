package com.xkzhangsan.time.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Objects;

/**
 * 日期转换
 * 包含Date、LocalDate、LocalDateTime、LocalTime和Instant的互相转换
 * 
* @ClassName: DateTimeConverterUtil 
* @Description: DateTime Converter
* @author xkzhangsan
* @date 2019年12月1日
*
 */
public class DateTimeConverterUtil {
	
	private DateTimeConverterUtil(){
	}

	public static Date toDate(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date toDate(LocalDate localDate) {
		Objects.requireNonNull(localDate, "localDate");
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * 以当天的日期+LocalTime组成新的LocalDateTime转换为Date
	 * @param localTime
	 * @return
	 */
	public static Date toDate(LocalTime localTime) {
		Objects.requireNonNull(localTime, "localTime");
		return Date.from(LocalDate.now().atTime(localTime).atZone(ZoneId.systemDefault()).toInstant());
	}	

	public static Date toDate(Instant instant) {
		return Date.from(instant);
	}
	
	public static Date toDate(long epochMilli){
		Objects.requireNonNull(epochMilli, "epochMilli");
		return new Date(epochMilli);
	}
	
	/**
	 * 注意，zonedDateTime时区必须和当前系统时区一致，不然会出现问题
	 * @param zonedDateTime
	 * @return
	 */
	public static Date toDate(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return Date.from(zonedDateTime.toInstant());
	}

	public static LocalDateTime toLocalDateTime(Date date) {
		Objects.requireNonNull(date, "date");
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static LocalDateTime toLocalDateTime(LocalDate localDate) {
		Objects.requireNonNull(localDate, "localDate");
		return localDate.atStartOfDay();
	}
	
	/**
	 * 以当天的日期+LocalTime组成新的LocalDateTime
	 * @param localTime
	 * @return
	 */
	public static LocalDateTime toLocalDateTime(LocalTime localTime) {
		Objects.requireNonNull(localTime, "localTime");
		return LocalDate.now().atTime(localTime);
	}

	public static LocalDateTime toLocalDateTime(Instant instant) {
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}
	
	public static LocalDateTime toLocalDateTime(long epochMilli) {
		Objects.requireNonNull(epochMilli, "epochMilli");
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
	}
	
	public static LocalDateTime toLocalDateTime(TemporalAccessor temporal) {
		return LocalDateTime.from(temporal);
	}
	
	/**
	 * 注意，zonedDateTime时区必须和当前系统时区一致，不然会出现问题
	 * @param zonedDateTime
	 * @return
	 */
	public static LocalDateTime toLocalDateTime(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toLocalDateTime();
	}

	public static LocalDate toLocalDate(Date date) {
		return toLocalDateTime(date).toLocalDate();
	}

	public static LocalDate toLocalDate(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate();
	}

	public static LocalDate toLocalDate(Instant instant) {
		return toLocalDateTime(instant).toLocalDate();
	}
	
	public static LocalDate toLocalDate(TemporalAccessor temporal) {
		return LocalDate.from(temporal);
	}
	
	/**
	 * 注意，zonedDateTime时区必须和当前系统时区一致，不然会出现问题
	 * @param zonedDateTime
	 * @return
	 */
	public static LocalDate toLocalDate(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toLocalDate();
	}

	public static LocalTime toLocalTime(Date date) {
		return toLocalDateTime(date).toLocalTime();
	}

	public static LocalTime toLocalTime(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalTime();
	}

	public static LocalTime toLocalTime(Instant instant) {
		return toLocalDateTime(instant).toLocalTime();
	}
	
	public static LocalTime toLocalTime(TemporalAccessor temporal) {
		return LocalTime.from(temporal);
	}
	
	/**
	 * 注意，zonedDateTime时区必须和当前系统时区一致，不然会出现问题
	 * @param zonedDateTime
	 * @return
	 */
	public static LocalTime toLocalTime(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toLocalTime();
	}

	public static Instant toInstant(Date date) {
		Objects.requireNonNull(date, "date");
		return date.toInstant();
	}

	public static Instant toInstant(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
	}

	public static Instant toInstant(LocalDate localDate) {
		return toLocalDateTime(localDate).atZone(ZoneId.systemDefault()).toInstant();
	}
	
	/**
	 * 以当天的日期+LocalTime组成新的LocalDateTime转换为Instant
	 * @param localTime
	 * @return
	 */
	public static Instant toInstant(LocalTime localTime) {
		return toLocalDateTime(localTime).atZone(ZoneId.systemDefault()).toInstant();
	}
	
	public static Instant toInstant(long epochMilli) {
		Objects.requireNonNull(epochMilli, "epochMilli");
		return Instant.ofEpochMilli(epochMilli);
	}
	
	public static Instant toInstant(TemporalAccessor temporal) {
		return Instant.from(temporal);
	}
	
	/**
	 * 注意，zonedDateTime时区必须和当前系统时区一致，不然会出现问题
	 * @param zonedDateTime
	 * @return
	 */
	public static Instant toInstant(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toInstant();
	}
	
	/**
	 * 转换为毫秒值
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param date
	 * @return
	 */
	public static long toEpochMilli(Date date){
		Objects.requireNonNull(date, "date");
		return date.getTime();
	}
	
	/**
	 * 转换为毫秒值
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param localDateTime
	 * @return
	 */
	public static long toEpochMilli(LocalDateTime localDateTime){
		return toInstant(localDateTime).toEpochMilli();
	}
	
	/**
	 * 转换为毫秒值
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param localDate
	 * @return
	 */
	public static long toEpochMilli(LocalDate localDate){
		return toInstant(localDate).toEpochMilli();
	}
	
	/**
	 * 转换为毫秒值
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param instant
	 * @return
	 */
	public static long toEpochMilli(Instant instant){
		Objects.requireNonNull(instant, "instant");
		return instant.toEpochMilli();
	}
	
	/**
	 * 转换为毫秒值，注意，zonedDateTime时区必须和当前系统时区一致，不然会出现问题
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param zonedDateTime
	 * @return
	 */
	public static long toEpochMilli(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toInstant().toEpochMilli();
	}
	
	/**
	 * 转换为ZonedDateTime，时区为系统默认时区
	 * @param date
	 * @return
	 */
	public static ZonedDateTime toZonedDateTime(Date date) {
		Objects.requireNonNull(date, "date");
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime()
				.atZone(ZoneId.systemDefault());
	}
	
	/**
	 * 转换为ZonedDateTime，时区为系统默认时区
	 * @param localDateTime
	 * @return
	 */
	public static ZonedDateTime toZonedDateTime(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.atZone(ZoneId.systemDefault());
	}

	/**
	 * 转换为ZonedDateTime，时区为系统默认时区
	 * @param localDate
	 * @return such as 2020-02-19T00:00+08:00[Asia/Shanghai]
	 */
	public static ZonedDateTime toZonedDateTime(LocalDate localDate) {
		Objects.requireNonNull(localDate, "localDate");
		return localDate.atStartOfDay().atZone(ZoneId.systemDefault());
	}
	
	/**
	 * 以当天的日期+LocalTime组成新的ZonedDateTime，时区为系统默认时区
	 * @param localTime
	 * @return
	 */
	public static ZonedDateTime toZonedDateTime(LocalTime localTime) {
		Objects.requireNonNull(localTime, "localTime");
		return LocalDate.now().atTime(localTime).atZone(ZoneId.systemDefault());
	}

	/**
	 * 转换为ZonedDateTime，时区为系统默认时区
	 * @param instant
	 * @return
	 */
	public static ZonedDateTime toZonedDateTime(Instant instant) {
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).atZone(ZoneId.systemDefault());
	}
	
	/**
	 * 转换为ZonedDateTime，时区为系统默认时区
	 * @param epochMilli
	 * @return
	 */
	public static ZonedDateTime toZonedDateTime(long epochMilli) {
		Objects.requireNonNull(epochMilli, "epochMilli");
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault())
				.atZone(ZoneId.systemDefault());
	}
	
	/**
	 * 转换为ZonedDateTime，时区为系统默认时区
	 * @param temporal
	 * @return
	 */
	public static ZonedDateTime toZonedDateTime(TemporalAccessor temporal) {
		return LocalDateTime.from(temporal).atZone(ZoneId.systemDefault());
	}	

}
