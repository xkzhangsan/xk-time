package com.xkzhangsan.time.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 日期转换工具类<br>
 * 包含Date、LocalDate、LocalDateTime、LocalTime、Instant、ZonedDateTime、YearMonth、Timestamp和long等互相转换<br>
 * 
 * 注意，ZonedDateTime相关的转换，尤其是其他时间转ZonedDateTime，要注意时间和对应时区一致。<br>
* @author xkzhangsan
*
 */
public class DateTimeConverterUtil {
	
	private DateTimeConverterUtil(){
	}

	/**
	 * LocalDateTime转Date
	 * @param localDateTime LocalDateTime
	 * @return Date
	 */
	public static Date toDate(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * LocalDate转Date
	 * @param localDate LocalDate
	 * @return Date
	 */
	public static Date toDate(LocalDate localDate) {
		Objects.requireNonNull(localDate, "localDate");
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * LocalTime转Date
	 * 以当天的日期+LocalTime组成新的LocalDateTime转换为Date
	 * @param localTime LocalTime
	 * @return Date
	 */
	public static Date toDate(LocalTime localTime) {
		Objects.requireNonNull(localTime, "localTime");
		return Date.from(LocalDate.now().atTime(localTime).atZone(ZoneId.systemDefault()).toInstant());
	}	

	/**
	 * Instant转Date
	 * @param instant Instant
	 * @return Date
	 */
	public static Date toDate(Instant instant) {
		return Date.from(instant);
	}
	
	/**
	 * 时间戳epochMilli毫秒转Date
	 * @param epochMilli 时间戳
	 * @return Date
	 */
	public static Date toDate(long epochMilli){
		Objects.requireNonNull(epochMilli, "epochMilli");
		return new Date(epochMilli);
	}
	
	/**
	 * ZonedDateTime转Date
	 * 注意时间对应的时区和默认时区差异
	 * @param zonedDateTime ZonedDateTime
	 * @return Date
	 */
	public static Date toDate(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return Date.from(zonedDateTime.toInstant());
	}
	
	/**
	 * YearMonth转Date
	 * 注意dayOfMonth范围：1到31之间，最大值根据月份确定特殊情况，如2月闰年29，非闰年28
	 * 如果要转换为当月最后一天，可以使用下面方法：toDateEndOfMonth(YearMonth)
	 * @param yearMonth YearMonth
	 * @param dayOfMonth 天
	 * @return Date
	 */
	public static Date toDate(YearMonth yearMonth, int dayOfMonth) {
		Objects.requireNonNull(yearMonth, "yearMonth");
		return toDate(yearMonth.atDay(dayOfMonth));
	}
	
	/**
	 * YearMonth转Date，转换为当月第一天
	 * @param yearMonth YearMonth
	 * @return Date
	 */
	public static Date toDateStartOfMonth(YearMonth yearMonth) {
		return toDate(yearMonth, 1);
	}
	
	/**
	 * YearMonth转Date，转换为当月最后一天
	 * @param yearMonth YearMonth
	 * @return Date
	 */
	public static Date toDateEndOfMonth(YearMonth yearMonth) {
		Objects.requireNonNull(yearMonth, "yearMonth");
		return toDate(yearMonth.atEndOfMonth());
	}

	/**
	 * Date转LocalDateTime
	 * @param date Date
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(Date date) {
		Objects.requireNonNull(date, "date");
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	/**
	 * Timestamp转LocalDateTime
	 * @param timestamp Timestamp
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
		Objects.requireNonNull(timestamp, "timestamp");
		return timestamp.toLocalDateTime();
	}

	/**
	 * LocalDate转LocalDateTime
	 * @param localDate LocalDate
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(LocalDate localDate) {
		Objects.requireNonNull(localDate, "localDate");
		return localDate.atStartOfDay();
	}
	
	/**
	 * LocalTime转LocalDateTime
	 * 以当天的日期+LocalTime组成新的LocalDateTime
	 * @param localTime LocalTime
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(LocalTime localTime) {
		Objects.requireNonNull(localTime, "localTime");
		return LocalDate.now().atTime(localTime);
	}

	/**
	 * Instant转LocalDateTime
	 * @param instant Instant
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(Instant instant) {
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}
	
	/**
	 * 时间戳epochMilli毫秒转LocalDateTime
	 * @param epochMilli 时间戳
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(long epochMilli) {
		Objects.requireNonNull(epochMilli, "epochMilli");
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
	}
	
	/**
	 * temporal转LocalDateTime
	 * @param temporal TemporalAccessor
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(TemporalAccessor temporal) {
		return LocalDateTime.from(temporal);
	}
	
	/**
	 * ZonedDateTime转LocalDateTime
	 * 注意时间对应的时区和默认时区差异
	 * @param zonedDateTime ZonedDateTime
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toLocalDateTime();
	}

	/**
	 * Date转LocalDate
	 * @param date Date
	 * @return LocalDate
	 */
	public static LocalDate toLocalDate(Date date) {
		return toLocalDateTime(date).toLocalDate();
	}

	/**
	 * LocalDateTime转LocalDate
	 * @param localDateTime LocalDateTime
	 * @return LocalDate
	 */
	public static LocalDate toLocalDate(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate();
	}

	/**
	 * Instant转LocalDate
	 * @param instant Instant
	 * @return LocalDate
	 */
	public static LocalDate toLocalDate(Instant instant) {
		return toLocalDateTime(instant).toLocalDate();
	}
	
	/**
	 * 时间戳epochMilli毫秒转LocalDate
	 * @param epochMilli 时间戳
	 * @return LocalDate
	 */
	public static LocalDate toLocalDate(long epochMilli) {
		Objects.requireNonNull(epochMilli, "epochMilli");
		return toLocalDateTime(epochMilli).toLocalDate();
	}	
	
	/**
	 * temporal转LocalDate
	 * @param temporal TemporalAccessor
	 * @return LocalDate
	 */
	public static LocalDate toLocalDate(TemporalAccessor temporal) {
		return LocalDate.from(temporal);
	}
	
	/**
	 * ZonedDateTime转LocalDate
	 * 注意时间对应的时区和默认时区差异
	 * @param zonedDateTime ZonedDateTime
	 * @return LocalDate
	 */
	public static LocalDate toLocalDate(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toLocalDate();
	}
	
	/**
	 * YearMonth转LocalDate
	 * 注意dayOfMonth范围：1到31之间，最大值根据月份确定特殊情况，如2月闰年29，非闰年28
	 * 如果要转换为当月最后一天，可以使用下面方法：toLocalDateEndOfMonth(YearMonth)
	 * @param yearMonth YearMonth
	 * @param dayOfMonth 天
	 * @return LocalDate
	 */
	public static LocalDate toLocalDate(YearMonth yearMonth, int dayOfMonth) {
		Objects.requireNonNull(yearMonth, "yearMonth");
		return yearMonth.atDay(dayOfMonth);
	}
	
	/**
	 * YearMonth转LocalDate，转换为当月第一天
	 * @param yearMonth YearMonth
	 * @return LocalDate
	 */
	public static LocalDate toLocalDateStartOfMonth(YearMonth yearMonth) {
		return toLocalDate(yearMonth, 1);
	}
	
	/**
	 * YearMonth转LocalDate，转换为当月最后一天
	 * @param yearMonth YearMonth
	 * @return LocalDate
	 */
	public static LocalDate toLocalDateEndOfMonth(YearMonth yearMonth) {
		Objects.requireNonNull(yearMonth, "yearMonth");
		return yearMonth.atEndOfMonth();
	}

	/**
	 * Date转LocalTime
	 * @param date Date
	 * @return LocalTime
	 */
	public static LocalTime toLocalTime(Date date) {
		return toLocalDateTime(date).toLocalTime();
	}

	/**
	 * LocalDateTime转LocalTime
	 * @param localDateTime LocalDateTime
	 * @return LocalTime
	 */
	public static LocalTime toLocalTime(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalTime();
	}

	/**
	 * Instant转LocalTime
	 * @param instant Instant
	 * @return LocalTime
	 */
	public static LocalTime toLocalTime(Instant instant) {
		return toLocalDateTime(instant).toLocalTime();
	}
	
	/**
	 * temporal转LocalTime
	 * @param temporal TemporalAccessor
	 * @return LocalTime
	 */
	public static LocalTime toLocalTime(TemporalAccessor temporal) {
		return LocalTime.from(temporal);
	}
	
	/**
	 * ZonedDateTime转LocalTime
	 * 注意时间对应的时区和默认时区差异
	 * @param zonedDateTime ZonedDateTime
	 * @return LocalTime
	 */
	public static LocalTime toLocalTime(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toLocalTime();
	}

	/**
	 * Date转Instant
	 * @param date Date
	 * @return Instant
	 */
	public static Instant toInstant(Date date) {
		Objects.requireNonNull(date, "date");
		return date.toInstant();
	}
	
	/**
	 * Timestamp转Instant
	 * @param timestamp Timestamp
	 * @return Instant
	 */
	public static Instant toInstant(Timestamp timestamp) {
		Objects.requireNonNull(timestamp, "timestamp");
		return timestamp.toInstant();
	}

	/**
	 * LocalDateTime转Instant
	 * @param localDateTime LocalDateTime
	 * @return Instant
	 */
	public static Instant toInstant(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
	}

	/**
	 * LocalDate转Instant
	 * @param localDate LocalDate
	 * @return Instant
	 */
	public static Instant toInstant(LocalDate localDate) {
		return toLocalDateTime(localDate).atZone(ZoneId.systemDefault()).toInstant();
	}
	
	/**
	 * LocalTime转Instant
	 * 以当天的日期+LocalTime组成新的LocalDateTime转换为Instant
	 * @param localTime LocalTime
	 * @return Instant
	 */
	public static Instant toInstant(LocalTime localTime) {
		return toLocalDateTime(localTime).atZone(ZoneId.systemDefault()).toInstant();
	}
	
	/**
	 * 时间戳epochMilli毫秒转Instant
	 * @param epochMilli 时间戳
	 * @return Instant
	 */
	public static Instant toInstant(long epochMilli) {
		Objects.requireNonNull(epochMilli, "epochMilli");
		return Instant.ofEpochMilli(epochMilli);
	}
	
	/**
	 * temporal转Instant
	 * @param temporal TemporalAccessor
	 * @return Instant
	 */
	public static Instant toInstant(TemporalAccessor temporal) {
		return Instant.from(temporal);
	}
	
	/**
	 * ZonedDateTime转Instant
	 * 注意，zonedDateTime时区必须和当前系统时区一致，不然会出现问题
	 * @param zonedDateTime ZonedDateTime
	 * @return Instant
	 */
	public static Instant toInstant(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toInstant();
	}
	
	/**
	 * Date转时间戳
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param date Date
	 * @return 时间戳
	 */
	public static long toEpochMilli(Date date){
		Objects.requireNonNull(date, "date");
		return date.getTime();
	}
	
	/**
	 * Timestamp转时间戳
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param timestamp Timestamp
	 * @return 时间戳
	 */
	public static long toEpochMilli(Timestamp timestamp){
		Objects.requireNonNull(timestamp, "timestamp");
		return timestamp.getTime();
	}
	
	/**
	 * LocalDateTime转时间戳
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param localDateTime LocalDateTime
	 * @return 时间戳
	 */
	public static long toEpochMilli(LocalDateTime localDateTime){
		return toInstant(localDateTime).toEpochMilli();
	}
	
	/**
	 * LocalDate转时间戳
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param localDate LocalDate
	 * @return 时间戳
	 */
	public static long toEpochMilli(LocalDate localDate){
		return toInstant(localDate).toEpochMilli();
	}
	
	/**
	 * Instant转时间戳
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param instant Instant
	 * @return 时间戳
	 */
	public static long toEpochMilli(Instant instant){
		Objects.requireNonNull(instant, "instant");
		return instant.toEpochMilli();
	}
	
	/**
	 * ZonedDateTime转时间戳，注意，zonedDateTime时区必须和当前系统时区一致，不然会出现问题
	 * 从1970-01-01T00:00:00Z开始的毫秒值
	 * @param zonedDateTime ZonedDateTime
	 * @return 时间戳
	 */
	public static long toEpochMilli(ZonedDateTime zonedDateTime) {
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.toInstant().toEpochMilli();
	}
	
	/**
	 * Date转ZonedDateTime，时区为系统默认时区
	 * @param date Date
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime toZonedDateTime(Date date) {
		Objects.requireNonNull(date, "date");
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault());
	}
	
	/**
	 * Date转ZonedDateTime
	 * @param date Date
	 * @param zoneId 目标时区
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime toZonedDateTime(Date date, String zoneId) {
		Objects.requireNonNull(zoneId, "zoneId");
		return toZonedDateTime(date, ZoneId.of(zoneId));
	}
	
	/**
	 * Date转ZonedDateTime
	 * @param date Date
	 * @param zone 目标时区
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime toZonedDateTime(Date date, ZoneId zone) {
		Objects.requireNonNull(date, "date");
		Objects.requireNonNull(zone, "zone");
		return Instant.ofEpochMilli(date.getTime()).atZone(zone);
	}
	
	/**
	 * LocalDateTime转ZonedDateTime，时区为系统默认时区
	 * @param localDateTime LocalDateTime
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime toZonedDateTime(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.atZone(ZoneId.systemDefault());
	}
	
	/**
	 * LocalDateTime转ZonedDateTime，时区为zoneId对应时区
	 * 注意，需要保证localDateTime和zoneId是对应的，不然会出现错误
	 * 
	 * @param localDateTime LocalDateTime
	 * @param zoneId LocalDateTime
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime toZonedDateTime(LocalDateTime localDateTime, String zoneId) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		Objects.requireNonNull(zoneId, "zoneId");
		return localDateTime.atZone(ZoneId.of(zoneId));
	}	

	/**
	 * LocalDate转ZonedDateTime，时区为系统默认时区
	 * @param localDate LocalDate
	 * @return ZonedDateTime such as 2020-02-19T00:00+08:00[Asia/Shanghai]
	 */
	public static ZonedDateTime toZonedDateTime(LocalDate localDate) {
		Objects.requireNonNull(localDate, "localDate");
		return localDate.atStartOfDay().atZone(ZoneId.systemDefault());
	}
	
	/**
	 * LocalTime转ZonedDateTime
	 * 以当天的日期+LocalTime组成新的ZonedDateTime，时区为系统默认时区
	 * @param localTime LocalTime
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime toZonedDateTime(LocalTime localTime) {
		Objects.requireNonNull(localTime, "localTime");
		return LocalDate.now().atTime(localTime).atZone(ZoneId.systemDefault());
	}

	/**
	 * Instant转ZonedDateTime，时区为系统默认时区
	 * @param instant Instant
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime toZonedDateTime(Instant instant) {
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).atZone(ZoneId.systemDefault());
	}
	
	/**
	 * 时间戳epochMilli毫秒转ZonedDateTime，时区为系统默认时区
	 * @param epochMilli 时间戳
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime toZonedDateTime(long epochMilli) {
		Objects.requireNonNull(epochMilli, "epochMilli");
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault())
				.atZone(ZoneId.systemDefault());
	}
	
	/**
	 * temporal转ZonedDateTime，时区为系统默认时区
	 * @param temporal TemporalAccessor
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime toZonedDateTime(TemporalAccessor temporal) {
		return LocalDateTime.from(temporal).atZone(ZoneId.systemDefault());
	}
	
	/**
	 * Date转YearMonth
	 * @param date Date
	 * @return YearMonth
	 */
	public static YearMonth toYearMonth(Date date){
		LocalDate localDate = toLocalDate(date);
		return YearMonth.of(localDate.getYear(), localDate.getMonthValue());
	}
	
	/**
	 * LocalDateTime转YearMonth
	 * @param localDateTime LocalDateTime
	 * @return YearMonth
	 */
	public static YearMonth toYearMonth(LocalDateTime localDateTime){
		LocalDate localDate = toLocalDate(localDateTime);
		return YearMonth.of(localDate.getYear(), localDate.getMonthValue());
	}
	
	/**
	 * LocalDate转YearMonth
	 * @param localDate LocalDate
	 * @return YearMonth
	 */
	public static YearMonth toYearMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return YearMonth.of(localDate.getYear(), localDate.getMonthValue());
	}
	
	/**
	 * Instant转YearMonth
	 * @param instant Instant
	 * @return YearMonth
	 */
	public static YearMonth toYearMonth(Instant instant){
		LocalDate localDate = toLocalDate(instant);
		return YearMonth.of(localDate.getYear(), localDate.getMonthValue());
	}
	
	/**
	 * ZonedDateTime转YearMonth
	 * @param zonedDateTime ZonedDateTime
	 * @return YearMonth
	 */
	public static YearMonth toYearMonth(ZonedDateTime zonedDateTime){
		LocalDate localDate = toLocalDate(zonedDateTime);
		return YearMonth.of(localDate.getYear(), localDate.getMonthValue());
	}
	
	/**
	 * Date转Timestamp
	 * @param date Date
	 * @return Timestamp
	 */
	public static Timestamp toTimestamp(Date date){
		Objects.requireNonNull(date, "date");
		return new Timestamp(date.getTime());
	}
	
	/**
	 * LocalDateTime转Timestamp
	 * @param localDateTime LocalDateTime
	 * @return Timestamp
	 */
	public static Timestamp toTimestamp(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return Timestamp.valueOf(localDateTime);
	}
	
	/**
	 * Instant转Timestamp
	 * @param instant Instant
	 * @return Timestamp
	 */
	public static Timestamp toTimestamp(Instant instant){
		Objects.requireNonNull(instant, "instant");
		return Timestamp.from(instant);
	}

	/**
	 * 时间戳epochMilli转Timestamp
	 * @param epochMilli 时间戳
	 * @return Timestamp
	 */
	public static Timestamp toTimestamp(long epochMilli){
		return new Timestamp(epochMilli);
	}
	
	/**
	 * 单位转换
	 * 
	 * @param sourceDuration
	 *            数量
	 * @param sourceUnit
	 *            原单位
	 * @param targetUnit
	 *            新单位
	 * @return 返回结果
	 */
	public static long unitConversion(long sourceDuration, TimeUnit sourceUnit, TimeUnit targetUnit) {
		return targetUnit.convert(sourceDuration, sourceUnit);
	}

	/**
	 * 单位转换，精确计算
	 * @param sourceDuration 数量
	 * @param sourceUnit 原单位
	 * @param targetUnit 新单位
	 * @param scale 小数位数
	 * @param roundingMode 舍入模式
	 * @return 返回结果
	 */
	public static BigDecimal unitConversionPrecise(long sourceDuration, TimeUnit sourceUnit, TimeUnit targetUnit, int scale, RoundingMode roundingMode) {
		return BigDecimal.valueOf(sourceDuration)
				.multiply(BigDecimal.valueOf(sourceUnit.toNanos(1))).divide(BigDecimal.valueOf(targetUnit.toNanos(1)), scale, roundingMode);
	}
	
	/**
	 * 单位转换，精确计算，保留1位小数，ROUND_DOWN 舍去多余小数
	 * @param sourceDuration 数量
	 * @param sourceUnit 原单位
	 * @param targetUnit 新单位
	 * @return 返回结果，保留1位小数，ROUND_DOWN 舍去多余小数
	 */
	public static BigDecimal unitConversionPrecise(long sourceDuration, TimeUnit sourceUnit, TimeUnit targetUnit) {
		return unitConversionPrecise(sourceDuration, sourceUnit, targetUnit, 1, RoundingMode.DOWN);
	}
	
	/**
	 * 单位转换，分钟转小时
	 * @param sourceDuration
	 * @return 小时数
	 */
	public static long minuteToHour(long num){
		return unitConversion(num, TimeUnit.MINUTES, TimeUnit.HOURS);
	}
	
	/**
	 * 单位转换，分钟转小时，保留1位小数，ROUND_DOWN 舍去多余小数
	 * @param sourceDuration
	 * @return 小时数，保留1位小数，ROUND_DOWN 舍去多余小数
	 */
	public static BigDecimal minuteToHourPrecise(long num){
		return unitConversionPrecise(num, TimeUnit.MINUTES, TimeUnit.HOURS);
	}
	
	/**
	 * 单位转换，分钟转小时
	 * @param num 天数
	 * @param scale 小数位数
	 * @param roundingMode 舍入模式 
	 * @return 小时数
	 */
	public static BigDecimal minuteToHourPrecise(long num, int scale, RoundingMode roundingMode){
		return unitConversionPrecise(num, TimeUnit.MINUTES, TimeUnit.HOURS, scale, roundingMode);
	}
}
