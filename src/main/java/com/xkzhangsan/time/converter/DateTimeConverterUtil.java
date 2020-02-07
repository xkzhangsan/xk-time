package com.xkzhangsan.time.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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
	
	public static Date toDate(Long epochMilli){
		Objects.requireNonNull(epochMilli, "epochMilli");
		return new Date(epochMilli);
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
	
	public static LocalDateTime toLocalDateTime(Long epochMilli) {
		Objects.requireNonNull(epochMilli, "epochMilli");
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
	}
	
	public static LocalDateTime toLocalDateTime(TemporalAccessor temporal) {
		return LocalDateTime.from(temporal);
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
	
	public static Instant toInstant(Long epochMilli) {
		Objects.requireNonNull(epochMilli, "epochMilli");
		return Instant.ofEpochMilli(epochMilli);
	}
	
	public static Instant toInstant(TemporalAccessor temporal) {
		return Instant.from(temporal);
	}
	
	/**
	 * 转换为毫秒值
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param date
	 * @return
	 */
	public static Long toEpochMilli(Date date){
		Objects.requireNonNull(date, "date");
		return date.getTime();
	}
	
	/**
	 * 转换为毫秒值
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param localDateTime
	 * @return
	 */
	public static Long toEpochMilli(LocalDateTime localDateTime){
		return toInstant(localDateTime).toEpochMilli();
	}
	
	/**
	 * 转换为毫秒值
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param localDate
	 * @return
	 */
	public static Long toEpochMilli(LocalDate localDate){
		return toInstant(localDate).toEpochMilli();
	}
	
	/**
	 * 转换为毫秒值
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param instant
	 * @return
	 */
	public static Long toEpochMilli(Instant instant){
		Objects.requireNonNull(instant, "instant");
		return instant.toEpochMilli();
	}

}
