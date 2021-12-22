package com.xkzhangsan.time.calculator;

import static com.xkzhangsan.time.constants.Constant.MONTHDAY_FORMAT_PRE;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.xkzhangsan.time.TemporalAdjusterExtension;
import com.xkzhangsan.time.constants.XkTimeConstant;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.enums.ConstellationNameEnum;
import com.xkzhangsan.time.enums.MonthNameEnum;
import com.xkzhangsan.time.enums.TwelveTwoEnum;
import com.xkzhangsan.time.enums.WeekNameEnum;
import com.xkzhangsan.time.enums.ZoneIdEnum;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;
import com.xkzhangsan.time.utils.ArrayUtil;
import com.xkzhangsan.time.utils.CollectionUtil;
import com.xkzhangsan.time.utils.StringUtil;

/**
 * 日期计算工具类<br>
 * 包括：<br>
 * 1.获取时间属性方法（支持年月日时分秒毫秒，星期，时间戳等），get* 比如getYear(Date date) 获取年部分，getMonthCnLong(Date date)获取月份中文，getDayOfWeekCn(Date date)，获取星期中文<br>
 * 2.获取时间加操作方法，plus* 比如plusYears(Date date, long amountToAdd) 当前时间年增加amountToAdd值<br>
 * 3.获取时间减操作方法，minus* 比如minusYears(Date date, long amountToSubtract) 当前时间年减少amountToSubtract值<br>
 * 4.获取时间修改属性方法，with* 比如withYear(Date date, long newValue) 修改当前时间年值为newValue<br>
 * 5.获取比较2个时间方法，between* 比如betweenTotalDays(Date startInclusive, Date endExclusive) 比较2个时间，获取相差总天数<br>
 * 6.其他常用方法，比如isLeapYear(Date date) 判断是否闰年，isWeekend(Date date) 判断是否周末，isExpiry(String yearMonthStr) 是否过期等<br>
 * 7.时区转换计算方法，transform*，比如transform(ZonedDateTime zonedDateTime, String zoneId)<br>
 * 8.比较2个时间大小和相等方法，compare*，比如compare(Date date1, Date date2)<br>
 * 9.获取准确的起始时间方法，start*,end*，比如startTimeOfMonth() 当月起始时间 当月第一天日期+00:00:00 endTimeOfMonth() 当月最后一天日期+23:59:59 精确到秒；endAccuracyTimeOf*，精确到毫秒（Date），精确到纳秒（LocalDateTime）<br>
 * 10.相同月日比较判断方法，isSameMonthDay*，betweenNextSameMonthDay*，nextSameMonthDay*， 比如用于生日，节日等周期性的日期比较判断<br>
 * 11.星座计算方法，getConstellation*，比如getConstellationNameCn(String monthDayStr)，根据日期计算星座<br>
 * 12.计算指定年月或起始时间区间的时间列表，get*List， 比如getDateList(int year, int month)，计算指定年月的时间列表<br>
 * 13.减少时间精度方法，reduceAccuracyTo*， 比如reduceAccuracyToDay(Date date)，减少时间精度到天，其他补0，返回如，2020-04-23 00:00:00<br>
 * 14.获取时间戳方法，getEpoch*， 比如getEpochMilli()获取时间戳，getEpochMilliFormat()获取时间戳格式化字符串（yyyy-MM-dd HH:mm:ss）<br>
 * 15.计算年龄方法，getAge*， 比如getAge(Date birthDay)，通过生日计算年龄<br>
 * 16.判断是否到生日方法，isBirthDay*， 比如isBirthDay(Date birthDay)，根据生日判断当前日期是否到生日<br>
 * 17.周数计算方法，weekof*， 比如weekOfMonth(Date date)，日期所在月中第几周<br>
 * 18.判断星期一，星期五方法，isMonday*,isZhouYi*， 比如isZhouYi(Date date)，是否为周一<br>
 * 19.十二时辰计算方法，getTwelveTwo*， 比如getTwelveTwo(Date date)，获取指定时间对应的十二时辰<br>
 * 20.季度计算方法，getQuarter*， 比如getQuarter(Date date)，获取指定时间对应的季度<br>
 * 21.获取季度准确的起始时间方法（四个季度），startTimeOf*Quarter， 比如startTimeOfFirstQuarter(int year)，获取指定年的第一季度开始时间<br>
 * 22.获取年准确的起始时间方法，startTimeOfYear， 比如startTimeOfYear(int year)，获取指定年的开始时间<br>
 * 23.常用时间（明天，下周，下月，明年等）计算方法，比如tomorrow()，计算明天，返回Date<br>
 * 24.修改星期值方法 withDayOfWeek*，比如withDayOfWeek(Date date, long newValue)，修改星期为指定值newValue，返回Date<br>
 * 25.中国工作日计算（将放假信息包含在内），包括判断当前日期是否为工作日和下一个工作日等方法， isChineseWorkDay*，nextChineseWorkDay*，比如isChineseWorkDay(Date, String holidayData)，nextChineseWorkDay(Date date, String holidayData)<br>
 * 节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断<br>
 * 26.判断2个或多个时间段是否有重叠（交集）方法， isOverlap*，比如isOverlap(Date startDate1, Date endDate1, Date startDate2, Date endDate2)，重叠返回true。<br>
 * 27.计算平均时间方法，averageTime*，比如{@code averageTime(List<Date> dateList)}，返回平均时间，比如"15:03:03"。<br>
 * 28.根据毫秒值计算倒计时方法，支持支持传入时间对象和指定格式，countdown*，比如countdown(long millis),返回倒计时，比如"27小时10分钟30秒"。<br>
 * 29.获取指定区间的格式化时间列表 方法，比如getDateFormatList(Date start, Date end, String dateFormatPattern) 支持传入格式化模板。  <br>
 * 30.计算2个时间段的重叠（交集）时间方法，比如overlapTime(Date startDate1, Date endDate1, Date startDate2, Date endDate2)，返回毫秒值。  <br>
 * 
* @author xkzhangsan
*
 */
public class DateTimeCalculatorUtil {
	
	private DateTimeCalculatorUtil(){
	}
	
	// get base time property
	
	/**
	 * 获取年，比如2020
	 * @param date Date
	 * @return int
	 */
	public static int getYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getYear();
	}
	
	/**
	 * 获取年，比如2020
	 * @param instant Instant
	 * @return int
	 */
	public static int getYear(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getYear();
	}
	
	/**
	 * 获取年，比如2020
	 * LocalDateTime LocalDate ZonedDateTime 可以直接getYear()
	 * @param localDateTime LocalDateTime
	 * @return int
	 */
	public static int getYear(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getYear();
	}

	/**
	 * 获取年，比如2020
	 * LocalDateTime LocalDate ZonedDateTime 可以直接getYear()
	 * @param localDate localDate
	 * @return int
	 */
	public static int getYear(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.getYear();
	}
	
	/**
	 * 获取月， 比如 1
	 * @param date Date
	 * @return int
	 */
	public static int getMonth(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getMonthValue();
	}
	
	/**
	 * 获取月， 比如 1
	 * @param instant Instant
	 * @return int
	 */
	public static int getMonth(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getMonthValue();
	}
	
	/**
	 * 获取月， 比如 1
	 * LocalDateTime LocalDate ZonedDateTime 可以直接getMonthValue()
	 * @param localDateTime LocalDateTime
	 * @return int
	 */
	public static int getMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getMonthValue();
	}
	
	/**
	 * 获取月， 比如 1
	 * LocalDateTime LocalDate ZonedDateTime 可以直接getMonthValue()
	 * @param localDate LocalDate
	 * @return int
	 */
	public static int getMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.getMonthValue();
	}	
	
	/**
	 * 获取月英文全称， 比如 January
	 * @param date Date
	 * @return String
	 */
	public static String getMonthEnLong(Date date){
		return MonthNameEnum.getFullNameEnByCode(getMonth(date));
	}
	
	/**
	 * 获取月英文全称， 比如 January
	 * @param instant Instant
	 * @return String
	 */
	public static String getMonthEnLong(Instant instant){
		return MonthNameEnum.getFullNameEnByCode(getMonth(instant));
	}
	
	/**
	 * 获取月英文全称， 比如 January
	 * @param localDateTime LocalDateTime
	 * @return String
	 */
	public static String getMonthEnLong(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return MonthNameEnum.getFullNameEnByCode(getMonth(localDateTime));
	}
	
	/**
	 * 获取月英文全称， 比如 January
	 * @param localDate LocalDate
	 * @return String
	 */
	public static String getMonthEnLong(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return MonthNameEnum.getFullNameEnByCode(getMonth(localDate));
	}
	
	/**
	 * 获取月英文简称， 比如 Jan
	 * @param date Date
	 * @return String
	 */
	public static String getMonthEnShort(Date date){
		return MonthNameEnum.getShortNameEnByCode(getMonth(date));
	}
	
	/**
	 * 获取月英文简称， 比如 Jan
	 * @param instant Instant
	 * @return String
	 */
	public static String getMonthEnShort(Instant instant){
		return MonthNameEnum.getShortNameEnByCode(getMonth(instant));
	}
	
	/**
	 * 获取月英文简称， 比如 Jan
	 * @param localDateTime LocalDateTime
	 * @return String
	 */
	public static String getMonthEnShort(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return MonthNameEnum.getShortNameEnByCode(getMonth(localDateTime));
	}
	
	/**
	 * 获取月英文简称， 比如 Jan
	 * @param localDate LocalDate
	 * @return String
	 */
	public static String getMonthEnShort(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return MonthNameEnum.getShortNameEnByCode(localDate.getMonthValue());
	}
	
	/**
	 * 获取月英文简称大写， 比如 JAN
	 * @param date Date
	 * @return String
	 */
	public static String getMonthEnShortUpper(Date date){
		return MonthNameEnum.getShortNameEnByCode(getMonth(date)).toUpperCase();
	}
	
	/**
	 * 获取月英文简称大写， 比如 JAN
	 * @param instant Instant
	 * @return String
	 */
	public static String getMonthEnShortUpper(Instant instant){
		return MonthNameEnum.getShortNameEnByCode(getMonth(instant)).toUpperCase();
	}
	
	/**
	 * 获取月英文简称大写， 比如 JAN
	 * @param localDateTime LocalDateTime
	 * @return String
	 */
	public static String getMonthEnShortUpper(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return MonthNameEnum.getShortNameEnByCode(getMonth(localDateTime)).toUpperCase();
	}
	
	/**
	 * 获取月英文简称大写， 比如 JAN
	 * @param localDate LocalDate
	 * @return String
	 */
	public static String getMonthEnShortUpper(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return MonthNameEnum.getShortNameEnByCode(localDate.getMonthValue()).toUpperCase();
	}	
	
	/**
	 * 获取月份中文全称， 比如一月
	 * @param date Date
	 * @return String
	 */
	public static String getMonthCnLong(Date date){
		return MonthNameEnum.getFullNameCnByCode(getMonth(date));
	}
	
	/**
	 * 获取月份中文全称， 比如一月
	 * @param instant Instant
	 * @return String
	 */
	public static String getMonthCnLong(Instant instant){
		return MonthNameEnum.getFullNameCnByCode(getMonth(instant));
	}
	
	/**
	 * 获取月份中文全称， 比如一月
	 * @param localDateTime LocalDateTime
	 * @return String
	 */
	public static String getMonthCnLong(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return MonthNameEnum.getFullNameCnByCode(getMonth(localDateTime));
	}
	
	/**
	 * 获取月份中文全称， 比如一月
	 * @param localDate LocalDate
	 * @return String
	 */
	public static String getMonthCnLong(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return MonthNameEnum.getFullNameCnByCode(localDate.getMonthValue());
	}
	
	/**
	 * 获取月份中文简称， 比如一
	 * @param date Date
	 * @return String
	 */
	public static String getMonthCnShort(Date date){
		return MonthNameEnum.getShortNameCnByCode(getMonth(date));
	}
	
	/**
	 * 获取月份中文简称， 比如一
	 * @param instant Instant
	 * @return String
	 */
	public static String getMonthCnShort(Instant instant){
		return MonthNameEnum.getShortNameCnByCode(getMonth(instant));
	}
	
	/**
	 * 获取月份中文简称， 比如一
	 * @param localDateTime LocalDateTime
	 * @return String
	 */
	public static String getMonthCnShort(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return MonthNameEnum.getShortNameCnByCode(getMonth(localDateTime));
	}
	
	/**
	 * 获取月份中文简称， 比如一
	 * @param localDate LocalDate
	 * @return String
	 */
	public static String getMonthCnShort(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return MonthNameEnum.getShortNameCnByCode(localDate.getMonthValue());
	}
	
	/**
	 * 获取天
	 * @param date Date
	 * @return int
	 */
	public static int getDayOfMonth(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getDayOfMonth();
	}
	
	/**
	 * 获取天
	 * @param instant Instant
	 * @return int
	 */
	public static int getDayOfMonth(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getDayOfMonth();
	}
	
	/**
	 * 获取天
	 * LocalDateTime LocalDate ZonedDateTime 可以直接.getDayOfMonth()
	 * @param localDateTime LocalDateTime
	 * @return int
	 */
	public static int getDayOfMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getDayOfMonth();
	}

	/**
	 * 获取天
	 * LocalDateTime LocalDate ZonedDateTime 可以直接.getDayOfMonth()
	 * @param localDate localDate
	 * @return int
	 */
	public static int getDayOfMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.getDayOfMonth();
	}
	
	/**
	 * 获取天（一年中）
	 * @param date Date
	 * @return int
	 */
	public static int getDayOfYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getDayOfYear();
	}
	
	/**
	 * 获取天（一年中）
	 * @param instant Instant
	 * @return int
	 */
	public static int getDayOfYear(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getDayOfYear();
	}
	
	/**
	 * 获取天（一年中）
	 * LocalDateTime LocalDate ZonedDateTime 可以直接.getDayOfYear()获取
	 * @param localDateTime LocalDateTime
	 * @return int
	 */
	public static int getDayOfYear(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getDayOfYear();
	}

	/**
	 * 获取天（一年中）
	 * LocalDateTime LocalDate ZonedDateTime 可以直接.getDayOfYear()获取
	 * @param localDate localDate
	 * @return int
	 */
	public static int getDayOfYear(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.getDayOfYear();
	}
	
	/**
	 * 获取某年的总天数
	 * @param year 年
	 * @return int
	 */
	public static int getLengthOfYear(int year){
		return Year.of(year).length();
	}
	
	/**
	 * 获取当前时间在一年中的第几天
	 * @return int
	 */
	public static int getDayOfYear(){
		return getDayOfYear(new Date());
	}
	
	/**
	 * 获取小时
	 * @param date Date
	 * @return int
	 */
	public static int getHour(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getHour();
	}
	
	/**
	 * 获取小时
	 * @param instant Instant
	 * @return int
	 */
	public static int getHour(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getHour();
	}
	
	/**
	 * 获取小时
	 * LocalDateTime LocalTime ZonedDateTime 可以直接.getHour()获取
	 * @param localDateTime LocalDateTime
	 * @return int
	 */
	public static int getHour(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getHour();
	}

	/**
	 * 获取小时
	 * LocalDateTime LocalTime ZonedDateTime 可以直接.getHour()获取
	 * @param localTime localTime
	 * @return int
	 */
	public static int getHour(LocalTime localTime){
		Objects.requireNonNull(localTime, "localTime");
		return localTime.getHour();
	}
	
	/**
	 * 获取分钟
	 * @param date Date
	 * @return int
	 */
	public static int getMinute(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getMinute();
	}
	
	/**
	 * 获取分钟
	 * @param instant Instant
	 * @return int
	 */
	public static int getMinute(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getMinute();
	}
	
	/**
	 * 获取分钟
	 * LocalDateTime LocalTime ZonedDateTime 可以直接.getMinute()获取
	 * @param localDateTime LocalDateTime
	 * @return int
	 */
	public static int getMinute(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getMinute();
	}

	/**
	 * 获取分钟
	 * LocalDateTime LocalTime ZonedDateTime 可以直接.getMinute()获取
	 * @param localTime localTime
	 * @return int
	 */
	public static int getMinute(LocalTime localTime){
		Objects.requireNonNull(localTime, "localTime");
		return localTime.getMinute();
	}
	
	/**
	 * 获取秒
	 * @param date Date
	 * @return int
	 */
	public static int getSecond(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getSecond();
	}
	
	/**
	 * 获取秒
	 * @param instant Instant
	 * @return int
	 */
	public static int getSecond(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getSecond();
	}
	
	/**
	 * 获取秒
	 * LocalDateTime LocalTime ZonedDateTime 可以直接.getSecond()获取
	 * @param localDateTime LocalDateTime
	 * @return int
	 */
	public static int getSecond(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getSecond();
	}

	/**
	 * 获取秒
	 * LocalDateTime LocalTime ZonedDateTime 可以直接.getSecond()获取
	 * @param localTime localTime
	 * @return int
	 */
	public static int getSecond(LocalTime localTime){
		Objects.requireNonNull(localTime, "localTime");
		return localTime.getSecond();
	}
	
	/**
	 * 获取毫秒
	 * @param date Date
	 * @return int
	 */
	public static int getMillisecond(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getNano()/1_000_000;
	}
	
	/**
	 * 获取毫秒
	 * @param instant Instant
	 * @return int
	 */
	public static int getMillisecond(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getNano()/1_000_000;
	}
	
	/**
	 * 获取毫秒
	 * @param localDateTime LocalDateTime
	 * @return int
	 */
	public static int getMillisecond(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getNano()/1_000_000;
	}

	/**
	 * 获取毫秒
	 * @param localTime localTime
	 * @return int
	 */
	public static int getMillisecond(LocalTime localTime){
		Objects.requireNonNull(localTime, "localTime");
		return localTime.getNano()/1_000_000;
	}

	/**
	 * 获取毫秒
	 * @param zonedDateTime zonedDateTime
	 * @return int
	 */
	public static int getMillisecond(ZonedDateTime zonedDateTime){
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return zonedDateTime.getNano()/1_000_000;
	}
	
	/**
	 * 获取时间戳
	 * @return long
	 */
	public static long getEpochMilli(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 获取时间戳（到秒）
	 * @return long
	 */
	public static long getEpochSecond(){
		return System.currentTimeMillis()/1000;
	}

	/**
	 * 获取格式化时间戳字符串
	 * @return String 格式： yyyy-MM-dd HH:mm:ss
	 */
	public static String getEpochMilliFormat(){
		return DateTimeFormatterUtil.formatToDateTimeStr(new Date());
	}
	
	/**
	 * 获取格式化时间戳字符串，带毫秒
	 * @return String 格式： yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static String getEpochMilliFormatFull(){
		return DateTimeFormatterUtil.format(new Date(), DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSS_FMT);
	}
	
	/**
	 * 获取格式化时间戳字符串 iso格式 带: 2020-02-18T22:37:55+08:00
	 * @return String 格式： yyyy-MM-ddTHH:mm:ssxxx
	 */
	public static String getEpochMilliIsoFormat(){
		return DateTimeFormatterUtil.format(new Date(), DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_XXX_FMT);
	}
	
	/**
	 * 获取格式化时间戳字符串 iso格式 带毫秒  带: 2020-02-18T22:37:55.991+08:00
	 * @return String 格式： yyyy-MM-dd'T'HH:mm:ss.SSSxxx
	 */
	public static String getEpochMilliIsoFormatFull(){
		return DateTimeFormatterUtil.format(new Date(), DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_SSS_XXX_FMT);
	}
	
	/**
	 * 获取格式化时间戳字符串 iso格式 2020-02-18T22:37:55+0800
	 * @return String 格式： yyyy-MM-ddTHH:mm:ssZ
	 */
	public static String getEpochMilliIsoNotFormatNoColon(){
		return DateTimeFormatterUtil.format(new Date(), DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_Z_FMT);
	}
	
	/**
	 * 获取格式化时间戳字符串 iso格式 带毫秒 2020-02-18T22:37:55.991+0800
	 * @return String 格式： yyyy-MM-dd'T'HH:mm:ss.SSSZ
	 */
	public static String getEpochMilliIsoFormatFullNoColon(){
		return DateTimeFormatterUtil.format(new Date(), DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_SSS_Z_FMT);
	}
	
	/**
	 * 根据年月日创建Date，时间部分为：00:00:00
	 * @param year 年
	 * @param month 月
	 * @param dayOfMonth 日
	 * @return Date
	 */
	public static Date getDate(int year, int month, int dayOfMonth){
		return DateTimeConverterUtil.toDate(LocalDate.of(year, month, dayOfMonth));
	}

	/**
	 * 根据年月日时分秒创建Date
	 * @param year 年
	 * @param month 月
	 * @param dayOfMonth 日
	 * @param hour 时
	 * @param minute 分
	 * @param second 秒
	 * @return Date
	 */
	public static Date getDate(int year, int month, int dayOfMonth, int hour, int minute, int second){
		return DateTimeConverterUtil.toDate(LocalDateTime.of(year, month, dayOfMonth, hour, minute, second));
	}

	/**
	 * 根据年月日时分秒毫秒创建Date
	 * @param year 年
	 * @param month 月
	 * @param dayOfMonth 日
	 * @param hour 时
	 * @param minute 分
	 * @param second 秒
	 * @param milliOfSecond 毫秒
	 * @return Date
	 */
	public static Date getDate(int year, int month, int dayOfMonth, int hour, int minute, int second, int milliOfSecond){
		return DateTimeConverterUtil.toDate(LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, milliOfSecond*1000_000));
	}

	/**
	 * 获取指定月第一天
	 * @param year 年
	 * @param month 月
	 * @return Date
	 */
	public static Date getDateStartOfMonth(int year, int month){
		return DateTimeConverterUtil.toDateStartOfMonth(YearMonth.of(year, month));
	}

	/**
	 * 获取指定月最后一天
	 * @param year 年
	 * @param month 月
	 * @return Date
	 */
	public static Date getDateEndOfMonth(int year, int month){
		return DateTimeConverterUtil.toDateEndOfMonth(YearMonth.of(year, month));
	}
	
	/**
	 * 计算年龄
	 * @param birthDay 生日
	 * @return int 年龄
	 */
	public static int getAge(LocalDate birthDay){
		Objects.requireNonNull(birthDay, "birthDay");
		Period period = periodBetween(birthDay, LocalDate.now());
		if (period.getYears() < 0) {
			throw new DateTimeException("birthDay is after now!");
		} else {
			return period.getYears();
		}
	}
	
	/**
	 * 计算年龄
	 * @param birthDay 生日
	 * @return int 年龄
	 */
	public static int getAge(Date birthDay){
		return getAge(DateTimeConverterUtil.toLocalDate(birthDay));
	}
	
	/**
	 * 计算年龄
	 * @param birthDay 生日
	 * @return int 年龄
	 */
	public static int getAge(LocalDateTime birthDay){
		return getAge(DateTimeConverterUtil.toLocalDate(birthDay));
	}
	
 	/**
	 * 获得季度值
	 * @param localDateTime LocalDateTime
	 * @return int 季度 1,2,3,4
	 */
	public static int getQuarter(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return (localDateTime.getMonthValue()+2)/3;
	}
	
	/**
	 * 获得季度值
	 * @param localDate LocalDate
	 * @return int 季度 1,2,3,4
	 */
	public static int getQuarter(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return (localDate.getMonthValue()+2)/3;
	}
	
	/**
	 * 获得季度值
	 * @param date Date
	 * @return int 季度 1,2,3,4
	 */
	public static int getQuarter(Date date){
		Objects.requireNonNull(date, "date");
		return (getMonth(date)+2)/3;
	}
	
	/**
	 * 获得当前季度值
	 * @return int 季度 1,2,3,4
	 */
	public static int getQuarterOfNow(){
		return (LocalDate.now().getMonthValue()+2)/3;
	}
	
	// plus two times
	
	/**
	 * 加年
	 * @param date Date
	 * @param amountToAdd 增加的值
	 * @return Date
	 */
	public static Date plusYears(Date date, long amountToAdd){
		return plus(date, ChronoUnit.YEARS, amountToAdd);
	}

	/**
	 * 加年
	 * @param localDateTime LocalDateTime
	 * @param amountToAdd 增加的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime plusYears(LocalDateTime localDateTime, long amountToAdd){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.plusYears(amountToAdd);
	}

	/**
	 * 加年
	 * @param localDate LocalDate
	 * @param amountToAdd 增加的值
	 * @return LocalDate
	 */
	public static LocalDate plusYears(LocalDate localDate, long amountToAdd){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.plusYears(amountToAdd);
	}

	/**
	 * 加月
	 * @param date Date
	 * @param amountToAdd 增加的值
	 * @return Date
	 */
	public static Date plusMonths(Date date, long amountToAdd){
		return plus(date, ChronoUnit.MONTHS, amountToAdd);
	}

	/**
	 * 加月
	 * @param localDateTime LocalDateTime
	 * @param amountToAdd 增加的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime plusMonths(LocalDateTime localDateTime, long amountToAdd){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.plusMonths(amountToAdd);
	}

	/**
	 * 加月
	 * @param localDate LocalDate
	 * @param amountToAdd 增加的值
	 * @return LocalDate
	 */
	public static LocalDate plusMonths(LocalDate localDate, long amountToAdd){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.plusMonths(amountToAdd);
	}

	/**
	 * 加星期
	 * @param date Date
	 * @param amountToAdd 增加的值
	 * @return Date
	 */
	public static Date plusWeeks(Date date, long amountToAdd){
		return plus(date, ChronoUnit.WEEKS, amountToAdd);
	}

	/**
	 * 加星期
	 * @param localDateTime LocalDateTime
	 * @param amountToAdd 增加的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime plusWeeks(LocalDateTime localDateTime, long amountToAdd){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.plusWeeks(amountToAdd);
	}

	/**
	 * 加星期
	 * @param localDate LocalDate
	 * @param amountToAdd 增加的值
	 * @return LocalDate
	 */
	public static LocalDate plusWeeks(LocalDate localDate, long amountToAdd){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.plusWeeks(amountToAdd);
	}

	/**
	 * 加天
	 * @param date Date
	 * @param amountToAdd 增加的值
	 * @return Date
	 */
	public static Date plusDays(Date date, long amountToAdd){
		return plus(date, ChronoUnit.DAYS, amountToAdd);
	}

	/**
	 * 加天
	 * @param localDateTime LocalDateTime
	 * @param amountToAdd 增加的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime plusDays(LocalDateTime localDateTime, long amountToAdd){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.plusDays(amountToAdd);
	}

	/**
	 * 加天
	 * @param localDate LocalDate
	 * @param amountToAdd 增加的值
	 * @return LocalDate
	 */
	public static LocalDate plusDays(LocalDate localDate, long amountToAdd){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.plusDays(amountToAdd);
	}

	/**
	 * 加小时
	 * @param date Date
	 * @param amountToAdd 增加的值
	 * @return Date
	 */
	public static Date plusHours(Date date, long amountToAdd){
		return plus(date, ChronoUnit.HOURS, amountToAdd);
	}

	/**
	 * 加小时
	 * @param localDateTime LocalDateTime
	 * @param amountToAdd 增加的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime plusHours(LocalDateTime localDateTime, long amountToAdd){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.plusHours(amountToAdd);
	}

	/**
	 * 加小时
	 * @param localTime LocalTime
	 * @param amountToAdd 增加的值
	 * @return LocalTime
	 */
	public static LocalTime plusHours(LocalTime localTime, long amountToAdd){
		Objects.requireNonNull(localTime, "localTime");
		return localTime.plusHours(amountToAdd);
	}

	/**
	 * 加分钟
	 * @param date Date
	 * @param amountToAdd 增加的值
	 * @return Date
	 */
	public static Date plusMinutes(Date date, long amountToAdd){
		return plus(date, ChronoUnit.MINUTES, amountToAdd);
	}

	/**
	 * 加分钟
	 * @param localDateTime LocalDateTime
	 * @param amountToAdd 增加的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime plusMinutes(LocalDateTime localDateTime, long amountToAdd){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.plusMinutes(amountToAdd);
	}

	/**
	 * 加分钟
	 * @param localTime LocalTime
	 * @param amountToAdd 增加的值
	 * @return LocalTime
	 */
	public static LocalTime plusMinutes(LocalTime localTime, long amountToAdd){
		Objects.requireNonNull(localTime, "localTime");
		return localTime.plusMinutes(amountToAdd);
	}

	/**
	 * 加秒
	 * @param date Date
	 * @param amountToAdd 增加的值
	 * @return Date
	 */
	public static Date plusSeconds(Date date, long amountToAdd){
		return plus(date, ChronoUnit.SECONDS, amountToAdd);
	}

	/**
	 * 加秒
	 * @param localDateTime LocalDateTime
	 * @param amountToAdd 增加的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime plusSeconds(LocalDateTime localDateTime, long amountToAdd){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.plusSeconds(amountToAdd);
	}

	/**
	 * 加秒
	 * @param localTime LocalTime
	 * @param amountToAdd 增加的值
	 * @return LocalTime
	 */
	public static LocalTime plusSeconds(LocalTime localTime, long amountToAdd){
		Objects.requireNonNull(localTime, "localTime");
		return localTime.plusSeconds(amountToAdd);
	}
	
	/**
	 * 增加毫秒
	 * @param date Date
	 * @param amountToAdd 增加的值
	 * @return Date
	 */
	public static Date plusMillis(Date date, long amountToAdd){
		return plus(date, ChronoUnit.MILLIS, amountToAdd);
	}

	/**
	 * 增加毫秒
	 * @param localDateTime LocalDateTime
	 * @param amountToAdd 增加的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime plusMillis(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.MILLIS, amountToAdd);
	}

	/**
	 * 增加毫秒
	 * @param localTime LocalTime
	 * @param amountToAdd 增加的值
	 * @return LocalTime
	 */
	public static LocalTime plusMillis(LocalTime localTime, long amountToAdd){
		return (LocalTime) plus(localTime, ChronoUnit.MILLIS, amountToAdd);
	}
	
	// minus two times
	
	/**
	 * 减年
	 * @param date Date
	 * @param amountToSubtract 减少的值
	 * @return Date
	 */
	public static Date minusYears(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.YEARS, amountToSubtract);
	}

	/**
	 * 减年
	 * @param localDateTime LocalDateTime
	 * @param amountToSubtract 减少的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime minusYears(LocalDateTime localDateTime, long amountToSubtract){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.minusYears(amountToSubtract);
	}

	/**
	 * 减年
	 * @param localDate LocalDate
	 * @param amountToSubtract 减少的值
	 * @return LocalDate
	 */
	public static LocalDate minusYears(LocalDate localDate, long amountToSubtract){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.minusYears(amountToSubtract);
	}

	/**
	 * 减月
	 * @param date Date
	 * @param amountToSubtract 减少的值
	 * @return Date
	 */
	public static Date minusMonths(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.MONTHS, amountToSubtract);
	}

	/**
	 * 减月
	 * @param localDateTime LocalDateTime
	 * @param amountToSubtract 减少的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime minusMonths(LocalDateTime localDateTime, long amountToSubtract){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.minusMonths(amountToSubtract);
	}

	/**
	 * 减月
	 * @param localDate LocalDate
	 * @param amountToSubtract 减少的值
	 * @return LocalDate
	 */
	public static LocalDate minusMonths(LocalDate localDate, long amountToSubtract){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.minusMonths(amountToSubtract);
	}

	/**
	 * 减星期
	 * @param date Date
	 * @param amountToSubtract 减少的值
	 * @return Date
	 */
	public static Date minusWeeks(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.WEEKS, amountToSubtract);
	}

	/**
	 * 减星期
	 * @param localDateTime LocalDateTime
	 * @param amountToSubtract 减少的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime minusWeeks(LocalDateTime localDateTime, long amountToSubtract){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.minusWeeks(amountToSubtract);
	}

	/**
	 * 减星期
	 * @param localDate LocalDate
	 * @param amountToSubtract 减少的值
	 * @return LocalDate
	 */
	public static LocalDate minusWeeks(LocalDate localDate, long amountToSubtract){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.minusWeeks(amountToSubtract);
	}

	/**
	 * 减天
	 * @param date Date
	 * @param amountToSubtract 减少的值
	 * @return Date
	 */
	public static Date minusDays(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.DAYS, amountToSubtract);
	}

	/**
	 * 减天
	 * @param localDateTime LocalDateTime
	 * @param amountToSubtract 减少的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime minusDays(LocalDateTime localDateTime, long amountToSubtract){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.minusDays(amountToSubtract);
	}

	/**
	 * 减天
	 * @param localDate LocalDate
	 * @param amountToSubtract 减少的值
	 * @return LocalDate
	 */
	public static LocalDate minusDays(LocalDate localDate, long amountToSubtract){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.minusDays(amountToSubtract);
	}

	/**
	 * 减小时
	 * @param date Date
	 * @param amountToSubtract 减少的值
	 * @return Date
	 */
	public static Date minusHours(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.HOURS, amountToSubtract);
	}

	/**
	 * 减小时
	 * @param localDateTime LocalDateTime
	 * @param amountToSubtract 减少的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime minusHours(LocalDateTime localDateTime, long amountToSubtract){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.minusHours(amountToSubtract);
	}

	/**
	 * 减小时
	 * @param localTime LocalTime
	 * @param amountToSubtract 减少的值
	 * @return LocalTime
	 */
	public static LocalTime minusHours(LocalTime localTime, long amountToSubtract){
		Objects.requireNonNull(localTime, "localTime");
		return localTime.minusHours(amountToSubtract);
	}

	/**
	 * 减分钟
	 * @param date Date
	 * @param amountToSubtract 减少的值
	 * @return Date
	 */
	public static Date minusMinutes(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.MINUTES, amountToSubtract);
	}

	/**
	 * 减分钟
	 * @param localDateTime LocalDateTime
	 * @param amountToSubtract 减少的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime minusMinutes(LocalDateTime localDateTime, long amountToSubtract){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.minusMinutes(amountToSubtract);
	}

	/**
	 * 减分钟
	 * @param localTime LocalTime
	 * @param amountToSubtract 减少的值
	 * @return LocalTime
	 */
	public static LocalTime minusMinutes(LocalTime localTime, long amountToSubtract){
		Objects.requireNonNull(localTime, "localTime");
		return localTime.minusMinutes(amountToSubtract);
	}

	/**
	 * 减秒
	 * @param date Date
	 * @param amountToSubtract 减少的值
	 * @return Date
	 */
	public static Date minusSeconds(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.SECONDS, amountToSubtract);
	}

	/**
	 * 减秒
	 * @param localDateTime LocalDateTime
	 * @param amountToSubtract 减少的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime minusSeconds(LocalDateTime localDateTime, long amountToSubtract){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.minusSeconds(amountToSubtract);
	}

	/**
	 * 减秒
	 * @param localTime LocalTime
	 * @param amountToSubtract 减少的值
	 * @return LocalTime
	 */
	public static LocalTime minusSeconds(LocalTime localTime, long amountToSubtract){
		Objects.requireNonNull(localTime, "localTime");
		return localTime.minusSeconds(amountToSubtract);
	}

	/**
	 * 减毫秒
	 * @param date Date
	 * @param amountToSubtract 减少的值
	 * @return Date
	 */
	public static Date minusMillis(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.MILLIS, amountToSubtract);
	}

	/**
	 * 减毫秒
	 * @param localDateTime LocalDateTime
	 * @param amountToSubtract 减少的值
	 * @return LocalDateTime
	 */
	public static LocalDateTime minusMillis(LocalDateTime localDateTime, long amountToSubtract){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.MILLIS, amountToSubtract);
	}

	/**
	 * 减毫秒
	 * @param localTime LocalTime
	 * @param amountToSubtract 减少的值
	 * @return LocalTime
	 */
	public static LocalTime minusMillis(LocalTime localTime, long amountToSubtract){
		return (LocalTime) minus(localTime, ChronoUnit.MILLIS, amountToSubtract);
	}	
	
	// modify property
	
	/**
	 * 修改年
	 * @param date Date
	 * @param newValue 新值
	 * @return Date
	 */
	public static Date withYear(Date date, long newValue){
		return with(date, ChronoField.YEAR, newValue);
	}

	/**
	 * 修改年
	 * @param localDateTime LocalDateTime
	 * @param newValue 新值
	 * @return LocalDateTime
	 */
	public static LocalDateTime withYear(LocalDateTime localDateTime, long newValue){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.withYear((int) newValue);
	}

	/**
	 * 修改年
	 * @param localDate LocalDate
	 * @param newValue 新值
	 * @return LocalDate
	 */
	public static LocalDate withYear(LocalDate localDate, long newValue){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.withYear((int) newValue);
	}

	/**
	 * 修改月
	 * @param date Date
	 * @param newValue 新值
	 * @return Date
	 */
	public static Date withMonth(Date date, long newValue){
		return with(date, ChronoField.MONTH_OF_YEAR, newValue);
	}

	/**
	 * 修改月
	 * @param localDateTime LocalDateTime
	 * @param newValue 新值
	 * @return LocalDateTime
	 */
	public static LocalDateTime withMonth(LocalDateTime localDateTime, long newValue){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.withMonth((int) newValue);
	}

	/**
	 * 修改月
	 * @param localDate LocalDate
	 * @param newValue 新值
	 * @return LocalDate
	 */
	public static LocalDate withMonth(LocalDate localDate, long newValue){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.withMonth((int) newValue);
	}

	/**
	 * 修改天
	 * @param date Date
	 * @param newValue 新值
	 * @return Date
	 */
	public static Date withDayOfMonth(Date date, long newValue){
		return with(date, ChronoField.DAY_OF_MONTH, newValue);
	}

	/**
	 * 修改天
	 * @param localDateTime LocalDateTime
	 * @param newValue 新值
	 * @return LocalDateTime
	 */
	public static LocalDateTime withDayOfMonth(LocalDateTime localDateTime, long newValue){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.withDayOfMonth((int) newValue);
	}

	/**
	 * 修改天
	 * @param localDate LocalDate
	 * @param newValue 新值
	 * @return LocalDate
	 */
	public static LocalDate withDayOfMonth(LocalDate localDate, long newValue){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.withDayOfMonth((int) newValue);
	}

	/**
	 * 修改一年中的天
	 * @param date Date
	 * @param newValue 新值
	 * @return Date
	 */
	public static Date withDayOfYear(Date date, long newValue){
		return with(date, ChronoField.DAY_OF_YEAR, newValue);
	}

	/**
	 * 修改一年中的天
	 * @param localDateTime LocalDateTime
	 * @param newValue 新值
	 * @return LocalDateTime
	 */
	public static LocalDateTime withDayOfYear(LocalDateTime localDateTime, long newValue){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.withDayOfYear((int) newValue);
	}

	/**
	 * 修改一年中的天
	 * @param localDate LocalDate
	 * @param newValue 新值
	 * @return LocalDate
	 */
	public static LocalDate withDayOfYear(LocalDate localDate, long newValue){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.withDayOfYear((int) newValue);
	}

	/**
	 * 修改小时
	 * @param date Date
	 * @param newValue 新值
	 * @return Date
	 */
	public static Date withHour(Date date, long newValue){
		return with(date, ChronoField.HOUR_OF_DAY, newValue);
	}

	/**
	 * 修改小时
	 * @param localDateTime LocalDateTime
	 * @param newValue 新值
	 * @return LocalDateTime
	 */
	public static LocalDateTime withHour(LocalDateTime localDateTime, long newValue){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.withHour((int) newValue);
	}

	/**
	 * 修改小时
	 * @param localTime LocalTime
	 * @param newValue 新值
	 * @return LocalTime
	 */
	public static LocalTime withHour(LocalTime localTime, long newValue){
		Objects.requireNonNull(localTime, "localTime");
		return localTime.withHour((int) newValue);
	}

	/**
	 * 修改分钟
	 * @param date Date
	 * @param newValue 新值
	 * @return Date
	 */
	public static Date withMinute(Date date, long newValue){
		return with(date, ChronoField.MINUTE_OF_HOUR, newValue);
	}

	/**
	 * 修改分钟
	 * @param localDateTime LocalDateTime
	 * @param newValue 新值
	 * @return LocalDateTime
	 */
	public static LocalDateTime withMinute(LocalDateTime localDateTime, long newValue){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.withMinute((int) newValue);
	}

	/**
	 * 修改分钟
	 * @param localTime LocalTime
	 * @param newValue 新值
	 * @return LocalTime
	 */
	public static LocalTime withMinute(LocalTime localTime, long newValue){
		Objects.requireNonNull(localTime, "localTime");
		return localTime.withMinute((int) newValue);
	}

	/**
	 * 修改秒
	 * @param date Date
	 * @param newValue 新值
	 * @return Date
	 */
	public static Date withSecond(Date date, long newValue){
		return with(date, ChronoField.SECOND_OF_MINUTE, newValue);
	}

	/**
	 * 修改秒
	 * @param localDateTime LocalDateTime
	 * @param newValue 新值
	 * @return LocalDateTime
	 */
	public static LocalDateTime withSecond(LocalDateTime localDateTime, long newValue){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.withSecond((int) newValue);
	}

	/**
	 * 修改秒
	 * @param localTime LocalTime
	 * @param newValue 新值
	 * @return LocalTime
	 */
	public static LocalTime withSecond(LocalTime localTime, long newValue){
		Objects.requireNonNull(localTime, "localTime");
		return localTime.withSecond((int) newValue);
	}

	/**
	 * 修改毫秒
	 * @param date Date
	 * @param newValue 新值
	 * @return Date
	 */
	public static Date withMilli(Date date, long newValue){
		return with(date, ChronoField.MILLI_OF_SECOND, newValue);
	}

	/**
	 * 修改毫秒
	 * @param localDateTime LocalDateTime
	 * @param newValue 新值
	 * @return LocalDateTime
	 */
	public static LocalDateTime withMilli(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.MILLI_OF_SECOND, newValue);
	}

	/**
	 * 修改毫秒
	 * @param localTime LocalTime
	 * @param newValue 新值
	 * @return LocalTime
	 */
	public static LocalTime withMilli(LocalTime localTime, long newValue){
		return (LocalTime) with(localTime, ChronoField.MILLI_OF_SECOND, newValue);
	}
	
	/**
	 * 修改星期
	 * @param date Date
	 * @param newValue 新值 1-7
	 * @return Date
	 */
	public static Date withDayOfWeek(Date date, long newValue){
		return with(date, ChronoField.DAY_OF_WEEK, newValue);
	}

	/**
	 * 修改星期
	 * @param localDateTime LocalDateTime
	 * @param newValue 新值 1-7
	 * @return LocalDateTime
	 */
	public static LocalDateTime withDayOfWeek(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.DAY_OF_WEEK, newValue);
	}
	
	/**
	 * 修改星期
	 * @param localDate LocalDate
	 * @param newValue 新值 1-7
	 * @return LocalDateTime
	 */
	public static LocalDate withDayOfWeek(LocalDate localDate, long newValue){
		return (LocalDate) with(localDate, ChronoField.DAY_OF_WEEK, newValue);
	}	
	
	// get the difference between two times
	
	/**
	 * 获取2个日期的相差年月天的年数部分，不是相差总年数，
	 * 比如2020-02-29 2021-03-07，返回1
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	@Deprecated
	public static long betweenYears(LocalDateTime startInclusive, LocalDateTime endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getYears();
	}
	
	/**
	 * 获取2个日期的相差年月天的年数部分，不是相差总年数，
	 * 比如2020-02-29 2021-03-07，返回1
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	@Deprecated
	public static long betweenYears(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getYears();
	}
	
	/**
	 * 获取2个日期的相差年月天的年数部分，不是相差总年数，
	 * 比如2020-02-29 2021-03-07，返回1
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	@Deprecated
	public static long betweenYears(LocalDate startInclusive, LocalDate endExclusive){
		return Period.between(startInclusive, endExclusive).getYears();
	}
	
	/**
	 * 获取2个日期的相差年月天的年数部分，不是相差总年数，
	 * 比如2020-02-29 2021-03-07，返回1
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenPeriodYears(LocalDateTime startInclusive, LocalDateTime endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getYears();
	}
	
	/**
	 * 获取2个日期的相差年月天的年数部分，不是相差总年数，
	 * 比如2020-02-29 2021-03-07，返回1
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenPeriodYears(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getYears();
	}
	
	/**
	 * 获取2个日期的相差年月天的年数部分，不是相差总年数，
	 * 比如2020-02-29 2021-03-07，返回1
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenPeriodYears(LocalDate startInclusive, LocalDate endExclusive){
		return Period.between(startInclusive, endExclusive).getYears();
	}	
	
	/**
	 * 获取2个日期的相差年月天的月数部分，不是相差总月数，
	 * 比如2020-02-29 2021-03-07，返回0
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	@Deprecated
	public static long betweenMonths(LocalDateTime startInclusive, LocalDateTime endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getMonths();
	}
	
	/**
	 * 获取2个日期的相差年月天的月数部分，不是相差总月数，
	 * 比如2020-02-29 2021-03-07，返回0
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	@Deprecated
	public static long betweenMonths(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getMonths();
	}
	
	/**
	 * 获取2个日期的相差年月天的月数部分，不是相差总月数，
	 * 比如2020-02-29 2021-03-07，返回0
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	@Deprecated
	public static long betweenMonths(LocalDate startInclusive, LocalDate endExclusive){
		return Period.between(startInclusive, endExclusive).getMonths();
	}
	
	/**
	 * 获取2个日期的相差年月天的月数部分，不是相差总月数，
	 * 比如2020-02-29 2021-03-07，返回0
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenPeriodMonths(LocalDateTime startInclusive, LocalDateTime endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getMonths();
	}
	
	/**
	 * 获取2个日期的相差年月天的月数部分，不是相差总月数，
	 * 比如2020-02-29 2021-03-07，返回0
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenPeriodMonths(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getMonths();
	}
	
	/**
	 * 获取2个日期的相差年月天的月数部分，不是相差总月数，
	 * 比如2020-02-29 2021-03-07，返回0
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenPeriodMonths(LocalDate startInclusive, LocalDate endExclusive){
		return Period.between(startInclusive, endExclusive).getMonths();
	}	
	
	/**
	 * 获取2个日期的相差年月天的天数部分，不是相差总天数，
	 * 比如2020-02-29 2021-03-07，返回7
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	@Deprecated
	public static long betweenDays(LocalDateTime startInclusive, LocalDateTime endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getDays();
	}
	
	/**
	 * 获取2个日期的相差年月天的天数部分，不是相差总天数，
	 * 比如2020-02-29 2021-03-07，返回7
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	@Deprecated
	public static long betweenDays(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getDays();
	}
	
	/**
	 * 获取2个日期的相差年月天的天数部分，不是相差总天数，
	 * 比如2020-02-29 2021-03-07，返回7
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	@Deprecated
	public static long betweenDays(LocalDate startInclusive, LocalDate endExclusive){
		return Period.between(startInclusive, endExclusive).getDays();
	}
	
	/**
	 * 获取2个日期的相差年月天的天数部分，不是相差总天数，
	 * 比如2020-02-29 2021-03-07，返回7
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenPeriodDays(LocalDateTime startInclusive, LocalDateTime endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getDays();
	}
	
	/**
	 * 获取2个日期的相差年月天的天数部分，不是相差总天数，
	 * 比如2020-02-29 2021-03-07，返回7
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenPeriodDays(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getDays();
	}
	
	/**
	 * 获取2个日期的相差年月天的天数部分，不是相差总天数，
	 * 比如2020-02-29 2021-03-07，返回7
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenPeriodDays(LocalDate startInclusive, LocalDate endExclusive){
		return Period.between(startInclusive, endExclusive).getDays();
	}
	
	/**
	 * 获取2个日期的相差总天数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalDays(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toDays();
	}
	
	/**
	 * 获取2个日期的相差总天数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalDays(LocalDate startInclusive, LocalDate endExclusive){
		return Duration.between(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toDays();
	}	
	
	/**
	 * 获取2个日期的相差总天数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalDays(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toDays();
	}
	
	/**
	 * 获取2个日期的相差总小时数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalHours(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toHours();
	}
	
	/**
	 * 获取2个日期的相差总小时数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalHours(LocalDate startInclusive, LocalDate endExclusive){
		return Duration.between(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toHours();
	}
	
	/**
	 * 获取2个日期的相差总小时数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalHours(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toHours();
	}
	
	/**
	 * 获取2个日期的相差总小时数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalHours(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toHours();
	}
	
	/**
	 * 获取2个日期的相差总分钟数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalMinutes(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toMinutes();
	}
	
	/**
	 * 获取2个日期的相差总分钟数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalMinutes(LocalDate startInclusive, LocalDate endExclusive){
		return Duration.between(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toMinutes();
	}
	
	/**
	 * 获取2个日期的相差总分钟数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalMinutes(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toMinutes();
	}
	
	/**
	 * 获取2个日期的相差总分钟数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalMinutes(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toMinutes();
	}
	
	/**
	 * 获取2个日期的相差总秒数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalSeconds(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).getSeconds();
	}
	
	/**
	 * 获取2个日期的相差总秒数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalSeconds(LocalDate startInclusive, LocalDate endExclusive){
		return Duration.between(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).getSeconds();
	}
	
	/**
	 * 获取2个日期的相差总秒数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalSeconds(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).getSeconds();
	}
	
	/**
	 * 获取2个日期的相差总秒数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalSeconds(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).getSeconds();
	}
	
	/**
	 * 获取2个日期的相差总毫秒数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalMillis(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toMillis();
	}
	
	/**
	 * 获取2个日期的相差总毫秒数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalMillis(LocalDate startInclusive, LocalDate endExclusive){
		return Duration.between(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toMillis();
	}
	
	/**
	 * 获取2个日期的相差总毫秒数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalMillis(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toMillis();
	}
	
	/**
	 * 获取2个日期的相差总毫秒数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalMillis(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toMillis();
	}	
	
	/**
	 * 获取2个日期的相差总纳秒数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalNanos(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toNanos();
	}
	
	/**
	 * 获取2个日期的相差总纳秒数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalNanos(LocalDate startInclusive, LocalDate endExclusive){
		return Duration.between(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toNanos();
	}
	
	/**
	 * 获取2个日期的相差总纳秒数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalNanos(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toNanos();
	}
	
	/**
	 * 获取2个日期的相差总纳秒数
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return long
	 */
	public static long betweenTotalNanos(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toNanos();
	}
	
	
	// get other properties
	
	/**
	 * 获取星期值 1-7，星期一到星期日
	 * @param date Date
	 * @return int
	 */
	public static int getDayOfWeek(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getDayOfWeek().getValue();
	}
	
	/**
	 * 获取星期值 1-7，星期一到星期日
	 * @param localDateTime LocalDateTime
	 * @return int
	 */
	public static int getDayOfWeek(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getDayOfWeek().getValue();
	}
	
	/**
	 * 获取星期值 1-7，星期一到星期日
	 * @param localDate LocalDate
	 * @return int
	 */
	public static int getDayOfWeek(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.getDayOfWeek().getValue();
	}	
	
	/**
	 * 获取星期值 1-7，星期一到星期日
	 * @param instant Instant
	 * @return int
	 */
	public static int getDayOfWeek(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getDayOfWeek().getValue();
	}
	
	/**
	 * 获取星期英文全称，比如Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday
	 * @param date Date
	 * @return String
	 */
	public static String getDayOfWeekEnLong(Date date){
		return WeekNameEnum.getFullNameEnByCode(getDayOfWeek(date));
	}
	
	/**
	 * 获取星期英文全称，比如Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday
	 * @param localDateTime LocalDateTime
	 * @return String
	 */
	public static String getDayOfWeekEnLong(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return WeekNameEnum.getFullNameEnByCode(getDayOfWeek(localDateTime));
	}
	
	/**
	 * 获取星期英文全称，比如Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday
	 * @param localDate LocalDate
	 * @return String
	 */
	public static String getDayOfWeekEnLong(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return WeekNameEnum.getFullNameEnByCode(getDayOfWeek(localDate));
	}	
	
	/**
	 * 获取星期英文全称，比如Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday
	 * @param instant Instant
	 * @return String
	 */
	public static String getDayOfWeekEnLong(Instant instant){
		return WeekNameEnum.getFullNameEnByCode(getDayOfWeek(instant));
	}
	
	/**
	 * 获取星期英文简称，比如Mon
	 * @param date Date
	 * @return String
	 */
	public static String getDayOfWeekEnShort(Date date){
		return WeekNameEnum.getShortNameEnByCode(getDayOfWeek(date));
	}
	
	/**
	 * 获取星期英文简称，比如Mon
	 * @param localDateTime LocalDateTime
	 * @return String
	 */
	public static String getDayOfWeekEnShort(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return WeekNameEnum.getShortNameEnByCode(getDayOfWeek(localDateTime));
	}
	
	/**
	 * 获取星期英文简称，比如Mon
	 * @param localDate LocalDate
	 * @return String
	 */
	public static String getDayOfWeekEnShort(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return WeekNameEnum.getShortNameEnByCode(getDayOfWeek(localDate));
	}	
	
	/**
	 * 获取星期英文简称，比如Mon
	 * @param instant Instant
	 * @return String
	 */
	public static String getDayOfWeekEnShort(Instant instant){
		return WeekNameEnum.getShortNameEnByCode(getDayOfWeek(instant));
	}
	
	/**
	 * 获取星期英文简称大写，比如MON
	 * @param date Date
	 * @return String
	 */
	public static String getDayOfWeekEnShortUpper(Date date){
		return WeekNameEnum.getShortNameEnByCode(getDayOfWeek(date)).toUpperCase();
	}
	
	/**
	 * 获取星期英文简称大写，比如MON
	 * @param localDateTime LocalDateTime
	 * @return String
	 */
	public static String getDayOfWeekEnShortUpper(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return WeekNameEnum.getShortNameEnByCode(getDayOfWeek(localDateTime)).toUpperCase();
	}
	
	/**
	 * 获取星期英文简称大写，比如MON
	 * @param localDate LocalDate
	 * @return String
	 */
	public static String getDayOfWeekEnShortUpper(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return WeekNameEnum.getShortNameEnByCode(getDayOfWeek(localDate)).toUpperCase();
	}
	
	/**
	 * 获取星期英文简称大写，比如MON
	 * @param instant Instant
	 * @return String
	 */
	public static String getDayOfWeekEnShortUpper(Instant instant){
		return WeekNameEnum.getShortNameEnByCode(getDayOfWeek(instant)).toUpperCase();
	}	
	
	
	/**
	 * 获取星期中文，比如星期一
	 * @param date Date
	 * @return String
	 */
	public static String getDayOfWeekCn(Date date){
		return WeekNameEnum.getNameCnByCode(getDayOfWeek(date));
	}
	
	/**
	 * 获取星期中文，比如星期一
	 * @param localDateTime LocalDateTime
	 * @return String
	 */
	public static String getDayOfWeekCn(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return WeekNameEnum.getNameCnByCode(getDayOfWeek(localDateTime));
	}
	
	/**
	 * 获取星期中文，比如星期一
	 * @param localDate LocalDate
	 * @return String
	 */
	public static String getDayOfWeekCn(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return WeekNameEnum.getNameCnByCode(getDayOfWeek(localDate));
	}	
	
	/**
	 * 获取星期中文，比如星期一
	 * @param instant Instant
	 * @return String
	 */
	public static String getDayOfWeekCn(Instant instant){
		return WeekNameEnum.getNameCnByCode(getDayOfWeek(instant));
	}
	
	/**
	 * 获取星期中文简称，比如星期一为一
	 * @param date Date
	 * @return String
	 */
	public static String getDayOfWeekCnShort(Date date){
		return WeekNameEnum.getNameCnByCode(getDayOfWeek(date)).substring(2);
	}
	
	/**
	 * 获取星期中文简称，比如星期一为一
	 * @param localDateTime LocalDateTime
	 * @return String
	 */
	public static String getDayOfWeekCnShort(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return WeekNameEnum.getNameCnByCode(getDayOfWeek(localDateTime)).substring(2);
	}
	
	/**
	 * 获取星期中文简称，比如星期一为一
	 * @param localDate LocalDate
	 * @return String
	 */
	public static String getDayOfWeekCnShort(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return WeekNameEnum.getNameCnByCode(getDayOfWeek(localDate)).substring(2);
	}	
	
	/**
	 * 获取星期中文简称，比如星期一为一
	 * @param instant Instant
	 * @return String
	 */
	public static String getDayOfWeekCnShort(Instant instant){
		return WeekNameEnum.getNameCnByCode(getDayOfWeek(instant)).substring(2);
	}	
	
	/**
	 * 获取当前月的第一天
	 * @param localDate LocalDate
	 * @return LocalDate
	 */
	public static LocalDate firstDayOfMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusters.firstDayOfMonth());
	}
	
	/**
	 * 获取当前月的第一天
	 * @param localDateTime LocalDateTime
	 * @return LocalDateTime
	 */
	public static LocalDateTime firstDayOfMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.with(TemporalAdjusters.firstDayOfMonth());
	}
	
	/**
	 * 获取当前月的第一天
	 * @param date Date
	 * @return Date
	 */
	public static Date firstDayOfMonth(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).with(TemporalAdjusters.firstDayOfMonth()));
	}	
	
	/**
	 * 获取当前月的最后一天
	 * @param localDate LocalDate
	 * @return LocalDate
	 */
	public static LocalDate lastDayOfMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusters.lastDayOfMonth());
	}
	
	/**
	 * 获取当前月的最后一天
	 * @param localDateTime LocalDateTime
	 * @return LocalDateTime
	 */
	public static LocalDateTime lastDayOfMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.with(TemporalAdjusters.lastDayOfMonth());
	}
	
	/**
	 * 获取当前月的最后一天
	 * @param date Date
	 * @return Date
	 */
	public static Date lastDayOfMonth(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).with(TemporalAdjusters.lastDayOfMonth()));
	}	
	
	/**
	 * 判断是否闰年
	 * @param localDate LocalDate
	 * @return boolean
	 */
	public static boolean isLeapYear(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.isLeapYear();
	}
	
	/**
	 * 判断是否闰年
	 * @param localDateTime LocalDateTime
	 * @return boolean
	 */
	public static boolean isLeapYear(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().isLeapYear();
	}
	
	/**
	 * 判断是否闰年
	 * @param date Date
	 * @return boolean
	 */
	public static boolean isLeapYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().isLeapYear();
	}
	
	/**
	 * 判断是否闰年
	 * @param year 年
	 * @return boolean
	 */
	public static boolean isLeapYear(int year){
		return ((year % 4) == 0) && ((year % 100) != 0 || (year % 400) == 0);
	}
	
	/**
	 * 下一个闰年
	 * @param year 年
	 * @return int
	 */
	public static int nextLeapYear(int year){
		for (int i = 0; i < 8; i++) {
			year++;
			if(isLeapYear(year)){
				return year;
			}
		}
		return -1;
	}
	
	/**
	 * 下一个闰年
	 * @param localDateTime LocalDateTime
	 * @return LocalDateTime
	 */
	public static LocalDateTime nextLeapYear(LocalDateTime localDateTime){
		return localDateTime.withYear(nextLeapYear(localDateTime.getYear()));
	}
	
	/**
	 * 下一个闰年
	 * @param localDate LocalDate
	 * @return LocalDate
	 */
	public static LocalDate nextLeapYear(LocalDate localDate){
		return localDate.withYear(nextLeapYear(localDate.getYear()));
	}
	
	/**
	 * 下一个闰年
	 * @param date Date
	 * @return Date
	 */
	public static Date nextLeapYear(Date date){
		return DateTimeConverterUtil.toDate(nextLeapYear(DateTimeConverterUtil.toLocalDateTime(date)));
	}
	
	/**
	 * 判断是否工作日 （周一到周五）
	 * @param date Date
	 * @return boolean
	 */
	public static boolean isWorkDay(Date date){
		int dayOfWeek = getDayOfWeek(date);
		if(dayOfWeek == 6 || dayOfWeek == 7){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 判断是否工作日 （周一到周五）
	 * @param localDateTime LocalDateTime
	 * @return boolean
	 */
	public static boolean isWorkDay(LocalDateTime localDateTime){
		int dayOfWeek = getDayOfWeek(localDateTime);
		if(dayOfWeek == 6 || dayOfWeek == 7){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 判断是否工作日 （周一到周五）
	 * @param localDate LocalDate
	 * @return boolean
	 */
	public static boolean isWorkDay(LocalDate localDate){
		int dayOfWeek = getDayOfWeek(localDate);
		if(dayOfWeek == 6 || dayOfWeek == 7){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 判断是否周末（周六周日）
	 * @param date Date
	 * @return boolean
	 */
	public static boolean isWeekend(Date date){
		return ! isWorkDay(date);
	}
	
	/**
	 * 判断是否周末（周六周日）
	 * @param localDateTime LocalDateTime
	 * @return boolean
	 */
	public static boolean isWeekend(LocalDateTime localDateTime){
		return ! isWorkDay(localDateTime);
	}
	
	/**
	 * 判断是否周末（周六周日）
	 * @param localDate LocalDate
	 * @return boolean
	 */
	public static boolean isWeekend(LocalDate localDate){
		return ! isWorkDay(localDate);
	}
	
	/**
	 * 判断是否中国工作日，包含法定节假日调整日期，节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断。
	 * @param date Date
	 * @param holidayData 放假信息0表示放假，1表示工作日，如：2021-01-01:0,2021-02-07:1
	 * @return boolean
	 */
	public static boolean isChineseWorkDay(Date date, String holidayData){
		return isChineseWorkDay(DateTimeConverterUtil.toLocalDateTime(date), holidayData);
	}
	
	/**
	 * 判断是否中国工作日，包含法定节假日调整日期，节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断。
	 * @param localDateTime LocalDateTime
	 * @param holidayData 放假信息0表示放假，1表示工作日，如：2021-01-01:0,2021-02-07:1
	 * @return boolean
	 */
	public static boolean isChineseWorkDay(LocalDateTime localDateTime, String holidayData){
		Objects.requireNonNull(holidayData, "holidayData");
		Map<String, Integer> dateTypeMap = StringUtil.convertHolidayDataToMapUseCache(holidayData);
		Integer dateType = dateTypeMap.get(DateTimeFormatterUtil.formatToDateStr(localDateTime));
		if(dateType != null){
			return dateType == 1 ? true : false;
		}
		return isWorkDay(localDateTime);
	}
	
	/**
	 * 判断时间段是否包含工作日，节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断。
	 * @param start 开始 时间戳
	 * @param end 结束 时间戳
	 * @param holidayData 放假信息0表示放假，1表示工作日，如：2021-01-01:0,2021-02-07:1
	 * @return boolean true 包含工作日 false 不包含 
	 */
	public static boolean hasChineseWorkDay(long start, long end, String holidayData){
		Objects.requireNonNull(holidayData, "holidayData");
		LocalDateTime startDate = DateTimeConverterUtil.toLocalDateTime(start);
		LocalDateTime endDate = DateTimeConverterUtil.toLocalDateTime(end);
		List<LocalDateTime> dateList = getLocalDateTimeList(startDate, endDate, ChronoUnit.DAYS);
		Map<String, Integer> dateTypeMap = StringUtil.convertHolidayDataToMapUseCache(holidayData);
		for(LocalDateTime date : dateList){
			String dateStr = DateTimeFormatterUtil.formatToDateStr(date);
			Integer dateType = dateTypeMap.get(dateStr);
			if(dateType != null){
				if(dateType == 1){
					return true;
				}
			}else{
				if(isWorkDay(date)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断时间段是否包含工作日，节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断。
	 * @param start 开始 时间戳
	 * @param end 结束 时间戳
	 * @param holidayData 放假信息0表示放假，1表示工作日，如：2021-01-01:0,2021-02-07:1
	 * @return boolean true 包含工作日 false 不包含 
	 */
	public static boolean hasChineseWorkDay(String start, String end, String holidayData){
		Objects.requireNonNull(start, "start");
		Objects.requireNonNull(end, "end");
		Objects.requireNonNull(holidayData, "holidayData");
		return hasChineseWorkDay(Long.parseLong(start), Long.parseLong(end), holidayData);
	}
	
	/**
	 * 判断时间段是否包含工作日，节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断。
	 * @param start 开始 Date
	 * @param end 结束 Date
	 * @param holidayData 放假信息0表示放假，1表示工作日，如：2021-01-01:0,2021-02-07:1
	 * @return boolean true 包含工作日 false 不包含 
	 */
	public static boolean hasChineseWorkDay(Date start, Date end, String holidayData){
		Objects.requireNonNull(start, "start");
		Objects.requireNonNull(end, "end");
		Objects.requireNonNull(holidayData, "holidayData");
		return hasChineseWorkDay(DateTimeConverterUtil.toEpochMilli(start), DateTimeConverterUtil.toEpochMilli(end), holidayData);
	}
	
	/**
	 * 判断时间段是否包含工作日，节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断。
	 * @param start 开始 LocalDate
	 * @param end 结束 LocalDate
	 * @param holidayData 放假信息0表示放假，1表示工作日，如：2021-01-01:0,2021-02-07:1
	 * @return boolean true 包含工作日 false 不包含 
	 */
	public static boolean hasChineseWorkDay(LocalDate start, LocalDate end, String holidayData){
		Objects.requireNonNull(start, "start");
		Objects.requireNonNull(end, "end");
		Objects.requireNonNull(holidayData, "holidayData");
		return hasChineseWorkDay(DateTimeConverterUtil.toEpochMilli(start), DateTimeConverterUtil.toEpochMilli(end), holidayData);
	}	
	
	/**
	 * 时间段内中国工作日，节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断。
	 * @param start 开始 时间戳
	 * @param end 结束 时间戳
	 * @param holidayData 放假信息0表示放假，1表示工作日，如：2021-01-01:0,2021-02-07:1
	 * @return 返回 工作日， yyyy-MM-dd 英文逗号分隔
	 */
	public static List<String> chineseWorkDay(long start, long end, String holidayData){
		Objects.requireNonNull(holidayData, "holidayData");
		List<String> result = new ArrayList<>();
		LocalDate startDate = DateTimeConverterUtil.toLocalDate(start);
		LocalDate endDate = DateTimeConverterUtil.toLocalDate(end);
		List<LocalDate> dateList = getLocalDateList(startDate, endDate, ChronoUnit.DAYS);
		Map<String, Integer> dateTypeMap = StringUtil.convertHolidayDataToMapUseCache(holidayData);
		dateList.stream().forEach(date->{
			String dateStr = date.toString();
			Integer dateType = dateTypeMap.get(dateStr);
			if(dateType != null){
				if(dateType == 1){
					result.add(dateStr);
				}
			}else{
				if(isWorkDay(date)){
					result.add(dateStr);
				}
			}
		});
		return result;
	}

	/**
	 * 时间段内中国工作日，节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断。
	 * @param start 开始 时间戳
	 * @param end 结束 时间戳
	 * @param holidayData 放假信息0表示放假，1表示工作日，如：2021-01-01:0,2021-02-07:1
	 * @return 返回 工作日， yyyy-MM-dd 英文逗号分隔
	 */
	public static List<String> chineseWorkDay(LocalDate start, LocalDate end, String holidayData){
		return chineseWorkDay(DateTimeConverterUtil.toEpochMilli(start), DateTimeConverterUtil.toEpochMilli(end), holidayData);
	}
	/**
	 * 时间段内中国工作日天数，节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断。
	 * @param start 开始 时间戳
	 * @param end 结束 时间戳
	 * @param holidayData 放假信息0表示放假，1表示工作日，如：2021-01-01:0,2021-02-07:1
	 * @return 返回 工作日， yyyy-MM-dd 英文逗号分隔
	 */
	public static int chineseWorkDayCount(long start, long end, String holidayData){
		return chineseWorkDay(start, end, holidayData).size();
	}
	
	/**
	 * 判断是否中国工作日，包含法定节假日调整日期，节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断。
	 * @param localDate LocalDate
	 * @param holidayData 放假信息0表示放假，1表示工作日，如：2021-01-01:0,2021-02-07:1
	 * @return boolean
	 */
	public static boolean isChineseWorkDay(LocalDate localDate, String holidayData){
		return isChineseWorkDay(DateTimeConverterUtil.toLocalDateTime(localDate), holidayData);
	}
	
	/**
	 * 下一个中国工作日，包含法定节假日调整日期，节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断。
	 * @param date Date
	 * @param holidayData 放假信息0表示放假，1表示工作日，如：2021-01-01:0,2021-02-07:1
	 * @return Date
	 */
	public static Date nextChineseWorkDay(Date date, String holidayData){
		while(date != null){
			date = plusDays(date, 1);
			if(isChineseWorkDay(DateTimeConverterUtil.toLocalDate(date), holidayData)){
				return date;
			}
		}
		return null;
	}
	
	/**
	 * 下一个中国工作日，包含法定节假日调整日期，节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断。
	 * @param localDateTime LocalDateTime
	 * @param holidayData 放假信息0表示放假，1表示工作日，如：2021-01-01:0,2021-02-07:1
	 * @return LocalDateTime
	 */
	public static LocalDateTime nextChineseWorkDay(LocalDateTime localDateTime, String holidayData){
		while(localDateTime != null){
			localDateTime = plusDays(localDateTime, 1);
			if(isChineseWorkDay(localDateTime, holidayData)){
				return localDateTime;
			}
		}
		return null;
	}
	
	/**
	 * 下一个中国工作日，包含法定节假日调整日期，节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断。
	 * @param localDate LocalDate
	 * @param holidayData 放假信息0表示放假，1表示工作日，如：2021-01-01:0,2021-02-07:1
	 * @return LocalDate
	 */
	public static LocalDate nextChineseWorkDay(LocalDate localDate, String holidayData){
		while(localDate != null){
			localDate = plusDays(localDate, 1);
			if(isChineseWorkDay(localDate, holidayData)){
				return localDate;
			}
		}
		return null;
	}
	
	/**
	 * 获取月的天数
	 * @param localDate LocalDate
	 * @return int
	 */
	public static int lengthOfMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.lengthOfMonth();
	}
	
	/**
	 * 获取月的天数
	 * @param localDateTime LocalDateTime
	 * @return int
	 */
	public static int lengthOfMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().lengthOfMonth();
	}
	
	/**
	 * 获取月的天数
	 * @param date Date
	 * @return int
	 */
	public static int lengthOfMonth(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().lengthOfMonth();
	}
	
	/**
	 *  获取年的天数
	 * @param localDate LocalDate
	 * @return int
	 */
	public static int lengthOfYear(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.lengthOfYear();
	}
	
	/**
	 * 获取年的天数
	 * @param localDateTime LocalDateTime
	 * @return int
	 */
	public static int lengthOfYear(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().lengthOfYear();
	}
	
	/**
	 * 获取年的天数
	 * @param date Date
	 * @return int
	 */
	public static int lengthOfYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().lengthOfYear();
	}	
	
	/**
	 * 下一个星期几
	 * @param localDate 日期
	 * @param dayOfWeek 星期
	 * @return LocalDate
	 */
	public static LocalDate next(LocalDate localDate, DayOfWeek dayOfWeek){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusters.next(dayOfWeek));
	}
	
	/**
	 * 下一个星期几
	 * @param localDateTime 日期
	 * @param dayOfWeek 星期
	 * @return LocalDateTime
	 */
	public static LocalDateTime next(LocalDateTime localDateTime, DayOfWeek dayOfWeek){
		return localDateTime.with(TemporalAdjusters.next(dayOfWeek));
	}

	/**
	 * 下一个星期几
	 * @param date 日期
	 * @param dayOfWeek 星期
	 * @return Date
	 */
	public static Date next(Date date, DayOfWeek dayOfWeek){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).with(TemporalAdjusters.next(dayOfWeek)));
	}
	
	
	/**
	 * 上一个星期几
	 * @param localDate 日期
	 * @param dayOfWeek 星期
	 * @return LocalDate
	 */
	public static LocalDate previous(LocalDate localDate, DayOfWeek dayOfWeek){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusters.previous(dayOfWeek));
	}
	
	/**
	 * 上一个星期几
	 * @param localDateTime 日期
	 * @param dayOfWeek 星期
	 * @return LocalDateTime
	 */
	public static LocalDateTime previous(LocalDateTime localDateTime, DayOfWeek dayOfWeek){
		return localDateTime.with(TemporalAdjusters.previous(dayOfWeek));
	}

	/**
	 * 上一个星期几
	 * @param date 日期
	 * @param dayOfWeek 星期
	 * @return Date
	 */
	public static Date previous(Date date, DayOfWeek dayOfWeek){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).with(TemporalAdjusters.previous(dayOfWeek)));
	}
	
	/**
	 * 获下一个工作日
	 * @param localDate LocalDate
	 * @return LocalDate
	 */
	public static LocalDate nextWorkDay(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusterExtension.nextWorkDay());
	}
	
	/**
	 * 获下一个工作日
	 * @param localDateTime LocalDateTime
	 * @return LocalDateTime
	 */
	public static LocalDateTime nextWorkDay(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.with(TemporalAdjusterExtension.nextWorkDay());
	}
	
	/**
	 * 获下一个工作日
	 * @param date Date
	 * @return Date
	 */
	public static Date nextWorkDay(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).with(TemporalAdjusterExtension.nextWorkDay()));
	}
	
	/**
	 * 获取当前系统当前时区时间
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime getZonedDateTimeNowOfDefault(){
		return ZonedDateTime.now(ZoneId.systemDefault());
	}
	
	/**
	 * 获取当前上海时区时间（北京时间）
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime getZonedDateTimeNowOfShanghai(){
		return ZonedDateTime.now(ZoneId.of(ZoneIdEnum.CTT.getZoneIdName()));
	}
	
	/**
	 * 获取当前巴黎时区时间
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime getZonedDateTimeNowOfParis(){
		return ZonedDateTime.now(ZoneId.of(ZoneIdEnum.ECT.getZoneIdName()));
	}
	
	/**
	 * 获取当前美国东部标准时区
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime getZonedDateTimeNowOfEST(){
		return ZonedDateTime.now(ZoneId.of(ZoneIdEnum.EST.getZoneIdName()));
	}
	
	/**
	 * 获取当前东京时区时间
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime getZonedDateTimeNowOfTokyo(){
		return ZonedDateTime.now(ZoneId.of(ZoneIdEnum.JST.getZoneIdName()));
	}	
	
	// base operation
	
	/**
	 * 日期加操作
	 * @param temporal Temporal
	 * @param unit 单位
	 * @param amountToAdd 增加的值 增加的值
	 * @return Temporal
	 */
	public static Temporal plus(Temporal temporal, TemporalUnit unit, long amountToAdd) {
		Objects.requireNonNull(temporal, "temporal");
		return temporal.plus(amountToAdd, unit);
	}
	
	/**
	 * date日期加操作
	 * @param date Date
	 * @param unit 单位
	 * @param amountToAdd 增加的值 增加的值
	 * @return Date
	 */
	public static Date plus(Date date, TemporalUnit unit, long amountToAdd) {
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).plus(amountToAdd, unit));
	}
	
	/**
	 * 日期减操作
	 * @param temporal Temporal
	 * @param unit 单位
	 * @param amountToSubtract 减少的值
	 * @return Temporal
	 */
	public static Temporal minus(Temporal temporal, TemporalUnit unit, long amountToSubtract) {
		Objects.requireNonNull(temporal, "temporal");
		return temporal.minus(amountToSubtract, unit);
	}
	
	/**
	 * date日期减操作
	 * @param date 日期
	 * @param unit 单位
	 * @param amountToSubtract 减少的值
	 * @return Date
	 */
	public static Date minus(Date date, TemporalUnit unit, long amountToSubtract) {
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).minus(amountToSubtract, unit));
	}
	
	/**
	 * 根据field修改属性
	 * @param temporal Temporal
	 * @param field 属性
	 * @param newValue 新值
	 * @return Temporal
	 */
	public static Temporal with(Temporal temporal, TemporalField field, long newValue) {
		Objects.requireNonNull(temporal, "temporal");
		return temporal.with(field, newValue);
	}
	
	/**
	 * 根据field修改属性
	 * @param date 日期
	 * @param field 属性
	 * @param newValue 新值
	 * @return Date
	 */
	public static Date with(Date date, TemporalField field, long newValue) {
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).with(field, newValue));
	}
	
	/**
	 * 获取2个日期的总的天时分秒毫秒纳秒
	 * @param startInclusive 开始时间
	 * @param endExclusive 结束时间
	 * @return Duration
	 */
	public static Duration durationBetween(Temporal startInclusive, Temporal endExclusive){
		return Duration.between(startInclusive, endExclusive);
	}
	
	/**
	 * 获取2个日期的相差年月日部分属性
	 * @param startDateInclusive 开始时间
	 * @param endDateExclusive 结束时间
	 * @return Period
	 */
	public static Period periodBetween(LocalDate startDateInclusive, LocalDate endDateExclusive){
		return Period.between(startDateInclusive, endDateExclusive);
	}
	
	/**
	 * 获取时区当前时间
	 * @param zoneId 时区
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime getZonedDateTimeNow(String zoneId){
		Objects.requireNonNull(zoneId, "zoneId");
		return ZonedDateTime.now(ZoneId.of(zoneId));
	}
	
	/**
	 * 时区转换计算
	 * @param zonedDateTime ZonedDateTime
	 * @param zoneId 例如 Asia/Shanghai
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime transform(ZonedDateTime zonedDateTime, String zoneId){
		Objects.requireNonNull(zoneId, "zoneId");
		return transform(zonedDateTime, ZoneId.of(zoneId));
	}
	
	/**
	 * 时区转换计算
	 * @param zonedDateTime ZonedDateTime
	 * @param zone 时区
	 * @return ZonedDateTime
	 */
	public static ZonedDateTime transform(ZonedDateTime zonedDateTime, ZoneId zone){
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		Objects.requireNonNull(zone, "zone");
		return zonedDateTime.withZoneSameInstant(zone);
	}
	
	/**
	 * 时区转换计算
	 * @param date Date
	 * @param zoneId 目标时区
	 * @return 日期 yyyy-MM-dd HH:mm:ss
	 */
	public static String transform(Date date, String zoneId){
		Objects.requireNonNull(zoneId, "zoneId");
		return transform(date, ZoneId.of(zoneId));
	}
	
	/**
	 * 时区转换计算
	 * @param date Date
	 * @param zone 目标时区
	 * @return 日期 yyyy-MM-dd HH:mm:ss
	 */
	public static String transform(Date date, ZoneId zone){
		Objects.requireNonNull(zone, "zone");
		return DateTimeFormatterUtil.formatToDateTimeStr(date, zone.toString());
	}
	
	/**
	 * 比较2个时间Date
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return int date1 大于 date2 返回1， date1 小于 date2 返回-1，date1 等于date2 返回0
	 */
	public static int compare(Date date1, Date date2){
		return compare(DateTimeConverterUtil.toLocalDateTime(date1), DateTimeConverterUtil.toLocalDateTime(date2));
	}
	
	/**
	 * 比较2个时间,可用于LocalDateTime,LocalDate,LocalTime,Instant
	 * @param temporal1 时间1
	 * @param temporal2 时间2
	 * @return int temporal1 大于 temporal2 返回1， temporal1 小于 temporal2 返回-1，temporal1 等于temporal2 返回0
	 */
	public static int compare(Temporal temporal1, Temporal temporal2){
		Objects.requireNonNull(temporal1, "temporal1");
		Objects.requireNonNull(temporal2, "temporal2");
		if(temporal1 instanceof LocalDateTime && temporal2 instanceof LocalDateTime){
			LocalDateTime localDateTimeA = (LocalDateTime)temporal1;
			LocalDateTime localDateTimeB = (LocalDateTime)temporal2;
			return localDateTimeA.compareTo(localDateTimeB);
		}else if(temporal1 instanceof LocalDate && temporal2 instanceof LocalDate){
			LocalDate localDateA = (LocalDate)temporal1;
			LocalDate localDateB = (LocalDate)temporal2;
			return localDateA.compareTo(localDateB);
		}else if(temporal1 instanceof LocalTime && temporal2 instanceof LocalTime){
			LocalTime localTimeA = (LocalTime)temporal1;
			LocalTime localTimeB = (LocalTime)temporal2;
			return localTimeA.compareTo(localTimeB);
		}else if(temporal1 instanceof Instant && temporal2 instanceof Instant){
			Instant instantA = (Instant)temporal1;
			Instant instantB = (Instant)temporal2;
			return instantA.compareTo(instantB);
		}
		
		throw new DateTimeException("Unsupported Temporal, must be LocalDateTime,LocalDate,LocalTime,Instant");
	}
	
	//获取精确起始时间 00:00:00 - 23:59:59
	
	/**
	 * 一天开始时间 00:00:00
	 * @return LocalTime
	 */
	public static LocalTime startTimeOfDay(){
		return LocalTime.MIN;
	}
	
	/**
	 * 一天开始时间 23:59:59
	 * @return LocalTime
	 */
	public static LocalTime endTimeOfDay(){
		return LocalTime.of(23, 59, 59);
	}	
	
	/**
	 * 一天结束时间 精确时间到纳秒 23:59:59.999999999
	 * @return LocalTime
	 */
	public static LocalTime endAccuracyTimeOfDay(){
		return LocalTime.MAX;
	}
	
	/**
	 * 昨天起始时间 即：昨天日期+00:00:00
	 * @return Date
	 */
	public static Date startTimeOfYesterday(){
		return DateTimeConverterUtil.toDate(LocalDate.now().minusDays(1).atTime(startTimeOfDay()));
	}
	
	/**
	 * 昨天结束时间即：昨天日期+23:59:59
	 * @return Date
	 */
	public static Date endTimeOfYesterday(){
		return DateTimeConverterUtil.toDate(LocalDate.now().minusDays(1).atTime(endTimeOfDay()));
	}
	
	/**
	 * 明天起始时间 即：明天日期+00:00:00
	 * @return Date
	 */
	public static Date startTimeOfTomorrow(){
		return DateTimeConverterUtil.toDate(LocalDate.now().plusDays(1).atTime(startTimeOfDay()));
	}
	
	/**
	 * 明天结束时间即：明天日期+23:59:59
	 * @return Date
	 */
	public static Date endTimeOfTomorrow(){
		return DateTimeConverterUtil.toDate(LocalDate.now().plusDays(1).atTime(endTimeOfDay()));
	}
	
	/**
	 * 今天起始时间 即：今天日期+00:00:00
	 * @return Date
	 */
	public static Date startTimeOfToday(){
		return DateTimeConverterUtil.toDate(LocalDate.now().atTime(startTimeOfDay()));
	}
	
	/**
	 * 今天结束时间即：今天日期+23:59:59
	 * @return Date
	 */
	public static Date endTimeOfToday(){
		return DateTimeConverterUtil.toDate(LocalDate.now().atTime(endTimeOfDay()));
	}
	
	/**
	 * 上个月第一天起始时间 即：上个月第一天日期+00:00:00
	 * @return Date
	 */
	public static Date startTimeOfLastMonth(){
		return DateTimeConverterUtil.toDate(firstDayOfMonth(LocalDate.now().minusMonths(1)).atTime(startTimeOfDay()));
	}
	
	/**
	 * 上个月最后一天结束时间 即：上个月最后一天日期+23:59:59
	 * @return Date
	 */
	public static Date endTimeOfLastMonth(){
		return DateTimeConverterUtil.toDate(lastDayOfMonth(LocalDate.now().minusMonths(1)).atTime(endTimeOfDay()));
	}
	
	/**
	 * 当月第一天起始时间 即：当月第一天日期+00:00:00
	 * @return Date
	 */
	public static Date startTimeOfMonth(){
		return DateTimeConverterUtil.toDate(firstDayOfMonth(LocalDate.now()).atTime(startTimeOfDay()));
	}
	
	/**
	 * 当月最后一天结束时间即：当月最后一天日期+23:59:59
	 * @return Date
	 */
	public static Date endTimeOfMonth(){
		return DateTimeConverterUtil.toDate(lastDayOfMonth(LocalDate.now()).atTime(endTimeOfDay()));
	}
	
	/**
	 * 获date起始时间
	 * @param date Date
	 * @return Date
	 */
	public static Date startTimeOfDate(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDate(date).atTime(startTimeOfDay()));
	}
	
	/**
	 * 获取date结束时间 精确到秒 23:59:59
	 * @param date Date
	 * @return Date
	 */
	public static Date endTimeOfDate(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDate(date).atTime(endTimeOfDay()));
	}
	
	
	/**
	 * 获localDateTime结束时间，精确时间到纳秒 23:59:59.999000000 （转换为Date会丢失毫秒以后数据）
	 * @param date Date
	 * @return Date
	 */
	public static Date endAccuracyTimeOfDate(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDate(date).atTime(endAccuracyTimeOfDay()));
	}
	
	/**
	 * 获localDateTime起始时间
	 * @param localDateTime LocalDateTime
	 * @return LocalDateTime
	 */
	public static LocalDateTime startTimeOfLocalDateTime(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.with(startTimeOfDay());
	}	
	
	/**
	 * 获取localDateTime结束时间，精确时间到纳秒 23:59:59.999999999
	 * @param localDateTime LocalDateTime
	 * @return LocalDateTime
	 */
	public static LocalDateTime endAccuracyTimeOfLocalDateTime(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.with(endAccuracyTimeOfDay());
	}


	/**
	 * 获取指定年月的第一天起始时间
	 * @param year 年
	 * @param month 月
	 * @return Date
	 */
	public static Date startTimeOfSpecialMonth(int year, int month){
		return DateTimeConverterUtil.toDate(LocalDate.of(year, month, 1).atTime(startTimeOfDay()));
	}
	
	/**
	 * 获取指定年月的最后一天结束时间
	 * @param year 年
	 * @param month 月
	 * @return Date
	 */
	public static Date endTimeOfSpecialMonth(int year, int month){
		return DateTimeConverterUtil.toDate(lastDayOfMonth(LocalDate.of(year, month, 1)).atTime(endTimeOfDay()));
	}
	
	/**
	 * 获取指定日期的起始时间
	 * @param year 年
	 * @param month 月
	 * @param dayOfMonth 日
	 * @return Date
	 */
	public static Date startTimeOfDate(int year, int month, int dayOfMonth){
		return DateTimeConverterUtil.toDate(LocalDate.of(year, month, dayOfMonth).atTime(startTimeOfDay()));
	}
	
	/**
	 * 获取指定日期的结束时间
	 * @param year 年
	 * @param month 月
	 * @param dayOfMonth 日
	 * @return Date
	 */
	public static Date endTimeOfDate(int year, int month, int dayOfMonth){
		return DateTimeConverterUtil.toDate(LocalDate.of(year, month, dayOfMonth).atTime(endTimeOfDay()));
	}
	
	/**
	 * 获取第一季度起始日期的开始时间
	 * @param year 年
	 * @return Date
	 */
	public static Date startTimeOfFirstQuarter(int year){
		return startTimeOfSpecialMonth(year, 1);
	}
	
	/**
	 * 获取第二季度起始日期的开始时间
	 * @param year 年
	 * @return Date
	 */
	public static Date startTimeOfSecondQuarter(int year){
		return startTimeOfSpecialMonth(year, 4);
	}
	
	/**
	 * 获取第三季度起始日期的开始时间
	 * @param year 年
	 * @return Date
	 */
	public static Date startTimeOfThirdQuarter(int year){
		return startTimeOfSpecialMonth(year, 7);
	}
	
	/**
	 * 获取第四季度起始日期的开始时间
	 * @param year 年
	 * @return Date
	 */
	public static Date startTimeOfFourthQuarter(int year){
		return startTimeOfSpecialMonth(year, 10);
	}
	
	/**
	 * 获取第一季度结束日期的开始时间
	 * @param year 年
	 * @return Date
	 */
	public static Date endTimeOfFirstQuarter(int year){
		return endTimeOfSpecialMonth(year, 3);
	}
	
	/**
	 * 获取第二季度结束日期的开始时间
	 * @param year 年
	 * @return Date
	 */
	public static Date endTimeOfSecondQuarter(int year){
		return endTimeOfSpecialMonth(year, 6);
	}
	
	/**
	 * 获取第三季度结束日期的开始时间
	 * @param year 年
	 * @return Date
	 */
	public static Date endTimeOfThirdQuarter(int year){
		return endTimeOfSpecialMonth(year, 9);
	}
	
	/**
	 * 获取第四季度结束日期的开始时间
	 * @param year 年
	 * @return Date
	 */
	public static Date endTimeOfFourthQuarter(int year){
		return endTimeOfSpecialMonth(year, 12);
	}

	/**
	 * 获取当前季度起始日期的开始时间
	 * @return Date
	 */
	public static Date startTimeOfCurrentQuarter(){
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int firstMonthOfQuarter = now.getMonth().firstMonthOfQuarter().getValue();
		return startTimeOfSpecialMonth(year, firstMonthOfQuarter);
	}
	
	/**
	 * 获取当前季度结束日期的时间
	 * @return Date
	 */
	public static Date endTimeOfCurrentQuarter(){
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int endMonthOfQuarter = now.getMonth().firstMonthOfQuarter().getValue()+2;
		return endTimeOfSpecialMonth(year, endMonthOfQuarter);
	}
	
	/**
	 * 获取指定年起始日期的开始时间
	 * @param year 年
	 * @return Date
	 */
	public static Date startTimeOfYear(int year){
		return startTimeOfSpecialMonth(year, 1);
	}
	
	/**
	 * 获取指定年结束日期的开始时间
	 * @param year 年
	 * @return Date
	 */
	public static Date endTimeOfYear(int year){
		return endTimeOfSpecialMonth(year, 12);
	}

	/**
	 * 获取当前年起始日期的开始时间
	 * @return Date
	 */
	public static Date startTimeOfCurrentYear(){
		return startTimeOfYear(LocalDate.now().getYear());
	}
	
	/**
	 * 获取当前年结束日期的时间
	 * @return Date
	 */
	public static Date endTimeOfCurrentYear(){
		return endTimeOfYear(LocalDate.now().getYear());
	}	
	
	// 使用MonthDay对比时间的月日，用于生日，节日等周期性的日期比较判断。
	
	/**
	 * 相同月日比较判断，用于生日，节日等周期性的日期比较判断。
	 * @param localDate1 日期1
	 * @param monthDay 月日
	 * @return boolean
	 */
	public static boolean isSameMonthDay(LocalDate localDate1, MonthDay monthDay){
		Objects.requireNonNull(localDate1, "localDate1");
		Objects.requireNonNull(monthDay, "monthDay");
		return MonthDay.of(localDate1.getMonthValue(), localDate1.getDayOfMonth()).equals(monthDay);
	}
	
	/**
	 * 相同月日比较判断，用于生日，节日等周期性的日期比较判断。
	 * @param localDate1 日期1
	 * @param monthDayStr  月日字符串 MM-dd格式
	 * @return boolean
	 */
	public static boolean isSameMonthDay(LocalDate localDate1, String monthDayStr){
		Objects.requireNonNull(monthDayStr, "monthDayStr");
		return isSameMonthDay(localDate1, MonthDay.parse(MONTHDAY_FORMAT_PRE + monthDayStr));
	}

	/**
	 * 相同月日比较判断，用于生日，节日等周期性的日期比较判断。
	 * @param localDate1 日期1
	 * @param localDate2 日期2
	 * @return boolean
	 */
	public static boolean isSameMonthDay(LocalDate localDate1, LocalDate localDate2){
		Objects.requireNonNull(localDate2, "localDate2");
		return isSameMonthDay(localDate1, MonthDay.of(localDate2.getMonthValue(), localDate2.getDayOfMonth()));
	}

	/**
	 * 相同月日比较判断，用于生日，节日等周期性的日期比较判断。
	 * @param date 日期
	 * @param monthDayStr 月日字符串，MM-dd格式
	 * @return boolean
	 */
	public static boolean isSameMonthDay(Date date, String monthDayStr){
		return isSameMonthDay(DateTimeConverterUtil.toLocalDate(date), monthDayStr);
	}

	/**
	 * 相同月日比较判断，用于生日，节日等周期性的日期比较判断。
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return boolean
	 */
	public static boolean isSameMonthDay(Date date1, Date date2){
		Objects.requireNonNull(date1, "date1");
		Objects.requireNonNull(date2, "date2");
		return isSameMonthDay(DateTimeConverterUtil.toLocalDate(date1), DateTimeConverterUtil.toLocalDate(date2));
	}

	/**
	 * 相同月日比较判断，与当前日期对比，用于生日，节日等周期性的日期比较判断
	 * @param monthDayStr 月日字符串，MM-dd格式
	 * @return boolean
	 */
	public static boolean isSameMonthDayOfNow(String monthDayStr){
		return isSameMonthDay(LocalDate.now(), monthDayStr);
	}

	/**
	 * 下个固定月日相差天数，用于生日，节日等周期性的日期推算
	 * @param localDate1 日期1
	 * @param month 月
	 * @param dayOfMonth 日
	 * @return long
	 */
	public static long betweenNextSameMonthDay(LocalDate localDate1, int month, int dayOfMonth) {
		Objects.requireNonNull(localDate1, "localDate1");
		MonthDay monthDay1 = MonthDay.of(localDate1.getMonthValue(), localDate1.getDayOfMonth());
		MonthDay monthDay2 = MonthDay.of(month, dayOfMonth);
		// 闰年2月29
		MonthDay leapMonthDay = MonthDay.of(2, 29);
		if (leapMonthDay.equals(monthDay2)) {
			LocalDate nextLeapYear = nextLeapYear(localDate1);
			return betweenTotalDays(localDate1.atStartOfDay(),
					nextLeapYear.withMonth(month).withDayOfMonth(dayOfMonth).atStartOfDay());
		}
		// localDate1 月日 小于 month dayOfMonth
		if (monthDay1.compareTo(monthDay2) == -1) {
			return betweenTotalDays(localDate1.atStartOfDay(),
					localDate1.withMonth(month).withDayOfMonth(dayOfMonth).atStartOfDay());
		} else {
			LocalDate next = localDate1.plusYears(1);
			return betweenTotalDays(localDate1.atStartOfDay(),
					next.withMonth(month).withDayOfMonth(dayOfMonth).atStartOfDay());
		}
	}

	/**
	 * 下个固定月日相差天数，用于生日，节日等周期性的日期推算
	 * @param localDate 日期
	 * @param monthDayStr 月日字符串，MM-dd格式
	 * @return long
	 */
	public static long betweenNextSameMonthDay(LocalDate localDate, String monthDayStr) {
		Objects.requireNonNull(monthDayStr, "monthDayStr");
		MonthDay monthDay2 = MonthDay.parse(MONTHDAY_FORMAT_PRE + monthDayStr);
		return betweenNextSameMonthDay(localDate, monthDay2.getMonthValue(), monthDay2.getDayOfMonth());
	}

	/**
	 * 下个固定月日相差天数，用于生日，节日等周期性的日期推算
	 * @param date 日期
	 * @param monthDayStr 月日字符串，MM-dd格式
	 * @return long
	 */
	public static long betweenNextSameMonthDay(Date date, String monthDayStr) {
		Objects.requireNonNull(monthDayStr, "monthDayStr");
		MonthDay monthDay2 = MonthDay.parse(MONTHDAY_FORMAT_PRE + monthDayStr);
		return betweenNextSameMonthDay(DateTimeConverterUtil.toLocalDate(date), monthDay2.getMonthValue(),
				monthDay2.getDayOfMonth());
	}

	/**
	 * 下个固定月日相差天数，与当前日期对比，用于生日，节日等周期性的日期推算
	 * @param monthDayStr 月日字符串，MM-dd格式
	 * @return long
	 */
	public static long betweenNextSameMonthDayOfNow(String monthDayStr) {
		Objects.requireNonNull(monthDayStr, "monthDayStr");
		MonthDay monthDay2 = MonthDay.parse(MONTHDAY_FORMAT_PRE + monthDayStr);
		return betweenNextSameMonthDay(LocalDate.now(), monthDay2.getMonthValue(),
				monthDay2.getDayOfMonth());
	}

	/**
	 * 下个固定月日相差日期，用于生日，节日等周期性的日期推算
	 * @param localDate 日期
	 * @param monthDayStr 月日字符串，MM-dd格式
	 * @return LocalDate
	 */
	public static LocalDate nextSameMonthDay(LocalDate localDate, String monthDayStr){
		return localDate.plusDays(betweenNextSameMonthDay(localDate, monthDayStr));
	}

	/**
	 * 下个固定月日相差日期，用于生日，节日等周期性的日期推算
	 * @param date 日期
	 * @param monthDayStr 月日字符串，MM-dd格式
	 * @return Date
	 */
	public static Date nextSameMonthDay(Date date, String monthDayStr){
		return DateTimeConverterUtil.toDate(nextSameMonthDay(DateTimeConverterUtil.toLocalDate(date), monthDayStr));
	}

	/**
	 * 下个固定月日相差日期，与当前日期对比，用于生日，节日等周期性的日期推算
	 * @param monthDayStr 月日字符串，MM-dd格式
	 * @return Date
	 */
	public static Date nextSameMonthDayOfNow(String monthDayStr){
		return nextSameMonthDay(new Date(), monthDayStr);
	}

	/**
	 * 根据日期查询星座中文名称
	 * @param monthDayStr 月日字符串，MM-dd格式
	 * @return String
	 */
	public static String getConstellationNameCn(String monthDayStr){
		return ConstellationNameEnum.getNameCnByMonthDay(monthDayStr);
	}

	/**
	 * 根据日期查询星座中文名称
	 * @param date Date
	 * @return String
	 */
	public static String getConstellationNameCn(Date date){
		String monthDayStr = DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_FMT);
		return ConstellationNameEnum.getNameCnByMonthDay(monthDayStr);
	}

	/**
	 * 根据日期查询星座英文名称
	 * @param monthDayStr 月日字符串，MM-dd格式
	 * @return String
	 */
	public static String getConstellationNameEn(String monthDayStr){
		return ConstellationNameEnum.getNameEnByMonthDay(monthDayStr);
	}
	
	/**
	 * 获取指定区间的时间列表，包含起始
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 时间列表
	 */
	public static List<LocalDateTime> getLocalDateTimeList(LocalDateTime start, LocalDateTime end){
		Objects.requireNonNull(start, "start");
		Objects.requireNonNull(end, "end");
		if(start.isAfter(end)){
			throw new DateTimeException("start must before or equal end!");
		}
		List<LocalDateTime> localDateTimeList = new ArrayList<LocalDateTime>();
		long days = betweenTotalDays(start, end)+1;
		for(long i=0; i<days; i++){
			localDateTimeList.add(start.plusDays(i));
		}
		return localDateTimeList;
	}
	
	/**
	 * 获取指定区间的时间列表，包含起始，间隔指定单位的相同时间
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param unit 单位
	 * @return 时间列表
	 */
	public static List<LocalDateTime> getLocalDateTimeList(LocalDateTime start, LocalDateTime end, ChronoUnit unit){
		Objects.requireNonNull(start, "start");
		Objects.requireNonNull(end, "end");
		Objects.requireNonNull(unit, "unit");
		if(start.isAfter(end)){
			throw new DateTimeException("start must before or equal end!");
		}
		List<LocalDateTime> localDateTimeList = new ArrayList<LocalDateTime>();
		if(start.equals(end)){
			localDateTimeList.add(start);
			return localDateTimeList;
		}
		int i = 1;
		LocalDateTime localDateTime = start;
		localDateTimeList.add(localDateTime);
		while(localDateTime.isBefore(end)){
			localDateTime = (LocalDateTime) plus(start, unit, i);
			if(localDateTime.isAfter(end) || localDateTime.equals(end)){
				break;
			}
			localDateTimeList.add(localDateTime);
			i++;
		}
		localDateTimeList.add(end);
		return localDateTimeList;
	}
	
	/**
	 * 获取指定区间的格式化时间列表，包含起始
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param dateFormatPattern 格式化模板 @see com.xkzhangsan.time.formatter.DateFormatPattern
	 * @return 格式化时间列表
	 */
	public static List<String> getLocalDateTimeFormatList(LocalDateTime start, LocalDateTime end, String dateFormatPattern){
		DateTimeFormatter formatter = DateTimeFormatterUtil.getDateTimeFormatter(dateFormatPattern);
		return getLocalDateTimeList(start, end).stream()
				.map(localDateTime -> DateTimeFormatterUtil.format(localDateTime, formatter)).collect(Collectors.toList());
	}
	
	/**
	 * 获取指定区间的时间列表，包含起始
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 时间列表
	 */
	public static List<LocalDate> getLocalDateList(LocalDate start, LocalDate end){
		return getLocalDateTimeList(DateTimeConverterUtil.toLocalDateTime(start),
				DateTimeConverterUtil.toLocalDateTime(end)).stream()
						.map(localDateTime -> localDateTime.toLocalDate()).collect(Collectors.toList());
	}
	
	/**
	 * 获取指定区间的时间列表，包含起始，间隔指定单位的相同时间
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param unit 单位
	 * @return 时间列表
	 */
	public static List<LocalDate> getLocalDateList(LocalDate start, LocalDate end, ChronoUnit unit){
		Objects.requireNonNull(start, "start");
		Objects.requireNonNull(end, "end");
		Objects.requireNonNull(unit, "unit");
		if(start.isAfter(end)){
			throw new DateTimeException("start must before or equal end!");
		}
		List<LocalDate> localDateList = new ArrayList<LocalDate>();
		if(start.equals(end)){
			localDateList.add(start);
			return localDateList;
		}
		int i = 1;
		LocalDate localDate = start;
		localDateList.add(localDate);
		while(localDate.isBefore(end)){
			localDate = (LocalDate) plus(start, unit, i);
			if(localDate.isAfter(end) || localDate.equals(end)){
				break;
			}
			localDateList.add(localDate);
			i++;
		}
		localDateList.add(end);
		return localDateList;
	}
	
	/**
	 * 获取指定区间的时间列表，包含起始
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 时间列表
	 */
	public static List<Date> getDateList(Date start, Date end){
		return getLocalDateTimeList(DateTimeConverterUtil.toLocalDateTime(start),
				DateTimeConverterUtil.toLocalDateTime(end)).stream()
						.map(localDateTime -> DateTimeConverterUtil.toDate(localDateTime)).collect(Collectors.toList());
	}
	
	/**
	 * 获取指定区间的格式化时间列表，包含起始
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param dateFormatPattern 格式化模板 @see com.xkzhangsan.time.formatter.DateFormatPattern
	 * @return  格式化时间列表
	 */
	public static List<String> getDateFormatList(Date start, Date end, String dateFormatPattern){
		DateTimeFormatter formatter = DateTimeFormatterUtil.getDateTimeFormatter(dateFormatPattern);
		return getDateList(start, end).stream()
				.map(date -> DateTimeFormatterUtil.format(date, formatter)).collect(Collectors.toList());
	}
	
	/**
	 * 获取指定区间的时间列表，包含起始，间隔指定单位的相同时间
	 * @param startInclusive 开始时间
	 * @param endInclusive 结束时间
	 * @param unit 单位
	 * @return 时间列表
	 */
	public static List<Date> getDateList(Date startInclusive, Date endInclusive, ChronoUnit unit){
		return getLocalDateTimeList(DateTimeConverterUtil.toLocalDateTime(startInclusive),
				DateTimeConverterUtil.toLocalDateTime(endInclusive), unit).stream()
						.map(localDateTime -> DateTimeConverterUtil.toDate(localDateTime)).collect(Collectors.toList());
	}
	
	/**
	 *  获取指定年月的所有日期列表
	 * @param yearMonth 年月
	 * @return 时间列表
	 */
	public static List<LocalDate> getLocalDateList(YearMonth yearMonth){
		Objects.requireNonNull(yearMonth, "yearMonth");
		List<LocalDate> localDateList = new ArrayList<LocalDate>();
		long days = yearMonth.lengthOfMonth();
		LocalDate localDate = DateTimeConverterUtil.toLocalDateStartOfMonth(yearMonth);
		for(long i=0; i<days; i++){
			localDateList.add(plusDays(localDate, i));
		}
		return localDateList;
	}
	
	/**
	 *  获取指定年月的所有日期列表
	 * @param yearMonthStr 年月字符串，格式： yyyy-MM
	 * @return 时间列表
	 */
	public static List<LocalDate> getLocalDateList(String yearMonthStr){
		Objects.requireNonNull(yearMonthStr, "yearMonthStr");
		YearMonth yearMonth = YearMonth.parse(yearMonthStr);
		return getLocalDateList(yearMonth);
	}
	
	/**
	 *  获取指定年月的所有日期列表
	 * @param year 年
	 * @param month 月
	 * @return 时间列表
	 */
	public static List<LocalDate> getLocalDateList(int year, int month){
		YearMonth yearMonth = YearMonth.of(year, month);
		return getLocalDateList(yearMonth);
	}
	
	/**
	 *  获取指定年月的所有日期列表
	 * @param yearMonth 年月
	 * @return 时间列表
	 */
	public static List<LocalDateTime> getLocalDateTimeList(YearMonth yearMonth){
		return getLocalDateList(yearMonth).stream()
				.map(localDate -> DateTimeConverterUtil.toLocalDateTime(localDate)).collect(Collectors.toList());
	}	
	
	/**
	 *  获取指定年月的所有日期列表
	 * @param yearMonthStr 年月字符串，格式： yyyy-MM
	 * @return 时间列表
	 */
	public static List<LocalDateTime> getLocalDateTimeList(String yearMonthStr){
		return getLocalDateList(yearMonthStr).stream()
				.map(localDate -> DateTimeConverterUtil.toLocalDateTime(localDate)).collect(Collectors.toList());
	}
	
	/**
	 *  获取指定年月的所有日期列表
	 * @param year 年
	 * @param month 月
	 * @return 时间列表
	 */
	public static List<LocalDateTime> getLocalDateTimeList(int year, int month){
		return getLocalDateList(YearMonth.of(year, month)).stream()
				.map(localDate -> DateTimeConverterUtil.toLocalDateTime(localDate)).collect(Collectors.toList());
	}
	
	/**
	 * 获取指定年月的所有日期列表
	 * @param yearMonthStr 年月字符串，格式： yyyy-MM
	 * @return 时间列表
	 */
	public static List<Date> getDateList(String yearMonthStr){
		return getLocalDateList(yearMonthStr).stream().map(localDate -> DateTimeConverterUtil.toDate(localDate))
				.collect(Collectors.toList());
	}
	
	/**
	 * 获取指定年月的所有日期列表
	 * @param year 年
	 * @param month 月
	 * @return 时间列表
	 */
	public static List<Date> getDateList(int year, int month){
		return getLocalDateList(YearMonth.of(year, month)).stream().map(localDate -> DateTimeConverterUtil.toDate(localDate))
				.collect(Collectors.toList());
	}	
	
	/**
	 * 判断是否过期，（输入年月小于当前年月）
	 * @param yearMonth 年月
	 * @return boolean
	 */
	public static boolean isExpiry(YearMonth yearMonth){
		Objects.requireNonNull(yearMonth, "yearMonth");
		if(yearMonth.isBefore(YearMonth.now())){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否过期，（输入年月小于当前年月）
	 * @param yearMonthStr 年月字符串，格式： yyyy-MM
	 * @return boolean
	 */
	public static boolean isExpiry(String yearMonthStr){
		Objects.requireNonNull(yearMonthStr, "yearMonthStr");
		YearMonth yearMonth = YearMonth.parse(yearMonthStr);
		return isExpiry(yearMonth);
	}
	
	/**
	 * 是否为生日
	 * @param birthDay 生日
	 * @return boolean
	 */
	public static boolean isBirthDay(LocalDate birthDay){
		Objects.requireNonNull(birthDay, "birthDay");
		return isSameMonthDay(birthDay, LocalDate.now());
	}
	
	/**
	 * 是否为生日
	 * @param birthDay 生日
	 * @return boolean
	 */
	public static boolean isBirthDay(Date birthDay){
		Objects.requireNonNull(birthDay, "birthDay");
		return isBirthDay(DateTimeConverterUtil.toLocalDate(birthDay));
	}
	
	/**
	 * 是否为生日
	 * @param birthDay 生日
	 * @return boolean
	 */
	public static boolean isBirthDay(LocalDateTime birthDay){
		Objects.requireNonNull(birthDay, "birthDay");
		return isBirthDay(DateTimeConverterUtil.toLocalDate(birthDay));
	}
	
	/**
	 * 减少时间精度到秒，其他补0，返回如，2020-04-23 15:18:13
	 * @param localDateTime LocalDateTime
	 * @return LocalDateTime
	 */
	public static LocalDateTime reduceAccuracyToSecond(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonthValue(),
				localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute(),
				localDateTime.getSecond());
	}
	
	/**
	 * 减少时间精度到秒，其他补0，返回如，2020-04-23 15:18:13
	 * @param date Date
	 * @return Date
	 */
	public static Date reduceAccuracyToSecond(Date date) {
		Objects.requireNonNull(date, "date");
		return DateTimeConverterUtil.toDate(reduceAccuracyToSecond(DateTimeConverterUtil.toLocalDateTime(date)));
	}
	
	/**
	 * 减少时间精度到分，其他补0，返回如，2020-04-23 15:18:00
	 * @param localDateTime LocalDateTime
	 * @return LocalDateTime
	 */
	public static LocalDateTime reduceAccuracyToMinute(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonthValue(),
				localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute(),
				0);
	}
	
	/**
	 * 减少时间精度到分，其他补0，返回如，2020-04-23 15:18:00
	 * @param date Date
	 * @return Date
	 */
	public static Date reduceAccuracyToMinute(Date date) {
		Objects.requireNonNull(date, "date");
		return DateTimeConverterUtil.toDate(reduceAccuracyToMinute(DateTimeConverterUtil.toLocalDateTime(date)));
	}
	
	/**
	 * 减少时间精度到小时，其他补0，返回如，2020-04-23 15:00:00
	 * @param localDateTime LocalDateTime
	 * @return LocalDateTime
	 */
	public static LocalDateTime reduceAccuracyToHour(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonthValue(),
				localDateTime.getDayOfMonth(), localDateTime.getHour(), 0, 0);
	}
	
	/**
	 * 减少时间精度到小时，其他补0，返回如，2020-04-23 15:00:00
	 * @param date Date
	 * @return Date
	 */
	public static Date reduceAccuracyToHour(Date date) {
		Objects.requireNonNull(date, "date");
		return DateTimeConverterUtil.toDate(reduceAccuracyToHour(DateTimeConverterUtil.toLocalDateTime(date)));
	}
	
	/**
	 * 减少时间精度到天，其他补0，返回如，2020-04-23 00:00:00
	 * @param localDateTime LocalDateTime
	 * @return LocalDateTime
	 */
	public static LocalDateTime reduceAccuracyToDay(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime, "localDateTime");
		return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonthValue(),
				localDateTime.getDayOfMonth(), 0, 0, 0);
	}
	
	/**
	 * 减少时间精度到天，其他补0，返回如，2020-04-23 00:00:00
	 * @param date Date
	 * @return Date
	 */
	public static Date reduceAccuracyToDay(Date date) {
		Objects.requireNonNull(date, "date");
		return DateTimeConverterUtil.toDate(reduceAccuracyToDay(DateTimeConverterUtil.toLocalDateTime(date)));
	}
	
	/**
	 * 日期所在月中第几周
	 * @param localDate LocalDate
	 * @param locale 地区 为null 是取系统默认地区
	 * @return 周数
	 */
	public static int weekOfMonth(LocalDate localDate, Locale locale){
		Objects.requireNonNull(localDate, "localDate");
		WeekFields weekFields = locale == null ? WeekFields.of(Locale.getDefault()) : WeekFields.of(locale);
		return (int) weekFields.weekOfMonth().getFrom(localDate);
	}
	
	/**
	 * 日期所在月中第几周
	 * @param localDate LocalDate
	 * @return 周数
	 */
	public static int weekOfMonth(LocalDate localDate){
		return weekOfMonth(localDate, null);
	}
	
	/**
	 * 日期所在月中第几周
	 * @param localDateTime LocalDateTime
	 * @return 周数
	 */
	public static int weekOfMonth(LocalDateTime localDateTime){
		return weekOfMonth(DateTimeConverterUtil.toLocalDate(localDateTime), null);
	}
	
	/**
	 * 日期所在月中第几周
	 * @param date Date
	 * @return 周数
	 */
	public static int weekOfMonth(Date date){
		return weekOfMonth(DateTimeConverterUtil.toLocalDate(date), null);
	}
	
	/**
	 * 当前日期所在月中第几周
	 * @return 周数
	 */
	public static int weekOfMonth(){
		return weekOfMonth(LocalDate.now());
	}
	
	/**
	 * 日期所在年中第几周
	 * @param localDate LocalDate
	 * @param locale 地区 为null 是取系统默认地区
	 * @return 周数
	 */
	public static int weekOfYear(LocalDate localDate, Locale locale){
		Objects.requireNonNull(localDate, "localDate");
		WeekFields weekFields = locale == null ? WeekFields.of(Locale.getDefault()) : WeekFields.of(locale);
		return (int) weekFields.weekOfYear().getFrom(localDate);
	}
	
	/**
	 * 日期所在年中第几周
	 * @param localDate LocalDate
	 * @return 周数
	 */
	public static int weekOfYear(LocalDate localDate){
		return weekOfYear(localDate, null);
	}
	
	/**
	 * 日期所在年中第几周
	 * @param localDateTime LocalDateTime
	 * @return 周数
	 */
	public static int weekOfYear(LocalDateTime localDateTime){
		return weekOfYear(DateTimeConverterUtil.toLocalDate(localDateTime), null);
	}
	
	/**
	 * 日期所在年中第几周
	 * @param date Date
	 * @return 周数
	 */
	public static int weekOfYear(Date date){
		return weekOfYear(DateTimeConverterUtil.toLocalDate(date), null);
	}
	
	/**
	 * 当前日期所在年中第几周
	 * @return 周数
	 */
	public static int weekOfYear(){
		return weekOfYear(LocalDate.now());
	}
	
	/**
	 * 是否为周一
	 * @param localDate LocalDate
	 * @return 是 true 否 false
	 */
	public static boolean isMonday(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return WeekNameEnum.Mon.getCode() == localDate.getDayOfWeek().getValue();
	}
	
	/**
	 * 是否为周一
	 * @param date Date
	 * @return 是 true 否 false
	 */
	public static boolean isMonday(Date date){
		return isMonday(DateTimeConverterUtil.toLocalDate(date));
	}
	
	/**
	 * 是否为周一
	 * @param localDate LocalDate
	 * @return 是 true 否 false
	 */
	public static boolean isZhouYi(LocalDate localDate){
		return isMonday(localDate);
	}
	
	/**
	 * 是否为周一
	 * @param date Date
	 * @return 是 true 否 false
	 */
	public static boolean isZhouYi(Date date){
		return isMonday(DateTimeConverterUtil.toLocalDate(date));
	}
	
	/**
	 * 是否为周五
	 * @param localDate LocalDate
	 * @return 是 true 否 false
	 */
	public static boolean isFriday(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return WeekNameEnum.Fri.getCode() == localDate.getDayOfWeek().getValue();
	}
	
	/**
	 * 是否为周五
	 * @param date Date
	 * @return 是 true 否 false
	 */
	public static boolean isFriday(Date date){
		return isFriday(DateTimeConverterUtil.toLocalDate(date));
	}
	
	/**
	 * 是否为周五
	 * @param localDate LocalDate
	 * @return 是 true 否 false
	 */
	public static boolean isZhouWu(LocalDate localDate){
		return isFriday(localDate);
	}
	
	/**
	 * 是否为周五
	 * @param date Date
	 * @return 是 true 否 false
	 */
	public static boolean isZhouWu(Date date){
		return isFriday(DateTimeConverterUtil.toLocalDate(date));
	}
	
	/**
	 * 获取指定时间对应的十二时辰
	 * @param localTime LocalTime
	 * @return 十二时辰名称
	 */
	public static String getTwelveTwo(LocalTime localTime){
		return TwelveTwoEnum.getNameCn(localTime);
	}
	
	/**
	 * 获取指定时间对应的十二时辰
	 * @param localDateTime LocalDateTime
	 * @return 十二时辰名称
	 */
	public static String getTwelveTwo(LocalDateTime localDateTime){
		return TwelveTwoEnum.getNameCn(DateTimeConverterUtil.toLocalTime(localDateTime));
	}
	
	/**
	 * 获取指定时间对应的十二时辰
	 * @param date Date
	 * @return 十二时辰名称
	 */
	public static String getTwelveTwo(Date date){
		return TwelveTwoEnum.getNameCn(date);
	}
	
	/**
	 * 获取当前时间对应的十二时辰
	 * @return 十二时辰名称
	 */
	public static String getTwelveTwo(){
		return TwelveTwoEnum.getNameCn(LocalTime.now());
	}

	// 以当前时间为参考的，常用时间（明天，下周，下月，明年等）计算

	/**
	 * 今天
	 * @return Date
	 */
	public static Date today(){
		return new Date();
	}

	/**
	 * 明天
	 * @return Date
	 */
	public static Date tomorrow(){
		return plusDays(today(), 1);
	}

	/**
	 * 下周
	 * @return Date
	 */
	public static Date nextWeek(){
		return plusWeeks(today(), 1);
	}

	/**
	 * 下月
	 * @return Date
	 */
	public static Date nextMonth(){
		return plusMonths(today(), 1);
	}

	/**
	 * 明年
	 * @return Date
	 */
	public static Date nextYear(){
		return plusYears(today(), 1);
	}

	/**
	 * 昨天
	 * @return Date
	 */
	public static Date yesterday(){
		return minusDays(today(), 1);
	}

	/**
	 * 上周
	 * @return Date
	 */
	public static Date lastWeek(){
		return minusWeeks(today(), 1);
	}

	/**
	 * 上月
	 * @return Date
	 */
	public static Date lastMonth(){
		return minusMonths(today(), 1);
	}

	/**
	 * 去年
	 * @return Date
	 */
	public static Date lastYear(){
		return minusYears(today(), 1);
	}
	
	/**
	 * 判断2个时间段是否有重叠（交集）
	 * @param startDate1 时间段1开始时间戳
	 * @param endDate1 时间段1结束时间戳
	 * @param startDate2 时间段2开始时间戳
	 * @param endDate2 时间段2结束时间戳
	 * @param isStrict 是否严格重叠，true 严格，没有任何相交或相等；false 不严格，可以首尾相等，比如2021/5/29-2021/5/31和2021/5/31-2021/6/1，不重叠。
	 * @return 返回是否重叠
	 */
	public static boolean isOverlap(long startDate1, long endDate1, long startDate2, long endDate2, boolean isStrict){
		if(endDate1<startDate1){
			throw new DateTimeException("endDate1不能小于startDate1");
		}
		if(endDate2<startDate2){
			throw new DateTimeException("endDate2不能小于startDate2");
		}
		if(isStrict){
			if(! (endDate1<startDate2 || startDate1>endDate2)){
				return true;
			}
		}else{
			if(! (endDate1<=startDate2 || startDate1>=endDate2)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断2个时间段是否有重叠（交集）
	 * @param startDate1 时间段1开始时间
	 * @param endDate1 时间段1结束时间
	 * @param startDate2 时间段2开始时间
	 * @param endDate2 时间段2结束时间
	 * @param isStrict 是否严格重叠，true 严格，没有任何相交或相等；false 不严格，可以首尾相等，比如2021-05-29到2021-05-31和2021-05-31到2021-06-01，不重叠。
	 * @return 返回是否重叠
	 */
	public static boolean isOverlap(Date startDate1, Date endDate1, Date startDate2, Date endDate2, boolean isStrict){
		Objects.requireNonNull(startDate1, "startDate1");
		Objects.requireNonNull(endDate1, "endDate1");
		Objects.requireNonNull(startDate2, "startDate2");
		Objects.requireNonNull(endDate2, "endDate2");
		return isOverlap(startDate1.getTime(), endDate1.getTime(), startDate2.getTime(), endDate2.getTime(), isStrict);
	}
	
	/**
	 * 判断2个时间段是否有重叠（交集），不严格模式，首尾相等不等于重叠
	 * @param startDate1 时间段1开始时间
	 * @param endDate1 时间段1结束时间
	 * @param startDate2 时间段2开始时间
	 * @param endDate2 时间段2结束时间
	 * @return 返回是否重叠
	 */
	public static boolean isOverlap(Date startDate1, Date endDate1, Date startDate2, Date endDate2){
		return isOverlap(startDate1, endDate1, startDate2, endDate2, false);
	}
	
	/**
	 * 判断2个时间段是否有重叠（交集）
	 * @param startDate1 时间段1开始时间
	 * @param endDate1 时间段1结束时间
	 * @param startDate2 时间段2开始时间
	 * @param endDate2 时间段2结束时间
	 * @param isStrict 是否严格重叠，true 严格，没有任何相交或相等；false 不严格，可以首尾相等，比如2021-05-29到2021-05-31和2021-05-31到2021-06-01，不重叠。
	 * @return 返回是否重叠
	 */
	public static boolean isOverlap(LocalDateTime startDate1, LocalDateTime endDate1, LocalDateTime startDate2, LocalDateTime endDate2, boolean isStrict){
		Objects.requireNonNull(startDate1, "startDate1");
		Objects.requireNonNull(endDate1, "endDate1");
		Objects.requireNonNull(startDate2, "startDate2");
		Objects.requireNonNull(endDate2, "endDate2");
		return isOverlap(DateTimeConverterUtil.toEpochMilli(startDate1),
				DateTimeConverterUtil.toEpochMilli(endDate1), 
				DateTimeConverterUtil.toEpochMilli(startDate2), 
				DateTimeConverterUtil.toEpochMilli(endDate2), isStrict);
	}
	
	/**
	 * 判断2个时间段是否有重叠（交集）
	 * @param startDate1 时间段1开始时间
	 * @param endDate1 时间段1结束时间
	 * @param startDate2 时间段2开始时间
	 * @param endDate2 时间段2结束时间
	 * @param isStrict 是否严格重叠，true 严格，没有任何相交或相等；false 不严格，可以首尾相等，比如2021-05-29到2021-05-31和2021-05-31到2021-06-01，不重叠。
	 * @return 返回是否重叠
	 */	
	public static boolean isOverlap(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2, boolean isStrict){
		Objects.requireNonNull(startDate1, "startDate1");
		Objects.requireNonNull(endDate1, "endDate1");
		Objects.requireNonNull(startDate2, "startDate2");
		Objects.requireNonNull(endDate2, "endDate2");
		return isOverlap(DateTimeConverterUtil.toEpochMilli(startDate1),
				DateTimeConverterUtil.toEpochMilli(endDate1), 
				DateTimeConverterUtil.toEpochMilli(startDate2), 
				DateTimeConverterUtil.toEpochMilli(endDate2), isStrict);
	}
	
	/**
	 * 判断多个时间段是否有重叠（交集）
	 * @param timePairs 时间段数组
	 * @param isStrict 是否严格重叠，true 严格，没有任何相交或相等；false 不严格，可以首尾相等，比如2021-05-29到2021-05-31和2021-05-31到2021-06-01，不重叠。
	 * @return 返回是否重叠
	 */
	public static boolean isOverlap(TimePair[] timePairs, boolean isStrict){
		if(ArrayUtil.isEmpty(timePairs)){
			throw new DateTimeException("timePairs不能为空");
		}
		
		Arrays.sort(timePairs, Comparator.comparingLong(TimePair::getStart));
		
        for(int i=1;i<timePairs.length;i++){
        	if(isStrict){
            	if(! (timePairs[i-1].getEnd()<timePairs[i].getStart())){
            		return true;
            	}
        	}else{
        		if(! (timePairs[i-1].getEnd()<=timePairs[i].getStart())){
            		return true;
            	} 
        	}
        }
		return false;
	}
	
	/**
	 * 判断多个时间段是否有重叠（交集）
	 * @param timePairList 时间段列表
	 * @param isStrict 是否严格重叠，true 严格，没有任何相交或相等；false 不严格，可以首尾相等，比如2021-05-29到2021-05-31和2021-05-31到2021-06-01，不重叠。
	 * @return 返回是否重叠
	 */
	public static boolean isOverlap(List<TimePair> timePairList, boolean isStrict){
		if(CollectionUtil.isEmpty(timePairList)){
			throw new DateTimeException("timePairList不能为空");
		}
		TimePair[] timePairs = new TimePair[timePairList.size()];
		timePairList.toArray(timePairs);
		return isOverlap(timePairs, isStrict);
	}
	
	/**
	 * 计算2个时间段的重叠（交集）时间
	 * @param startDate1 时间段1开始时间戳
	 * @param endDate1 时间段1结束时间戳
	 * @param startDate2 时间段2开始时间戳
	 * @param endDate2 时间段2结束时间戳
	 * @return 返回是重叠时间，单位毫秒
	 */
	public static long overlapTime(long startDate1, long endDate1, long startDate2, long endDate2){
		//不重叠，返回0
		if(! isOverlap(startDate1, endDate1, startDate2, endDate2, false)){
			return 0;
		}
		long overlapTime = 0;
		if(startDate1 <= startDate2 && endDate2 <= endDate1){//1包含2，或相等
			overlapTime = endDate2 - startDate2;
		}else if(startDate2 <= startDate1 && endDate1 <= endDate2){//2包含1，或相等
			overlapTime = endDate1 - startDate1;
		}else if(startDate1 < startDate2){//1和2的开始重叠
			overlapTime = endDate1 - startDate2;
		}else if(endDate1 > endDate2){//1和2的结束重叠
			overlapTime = endDate2 - startDate1;
		}
		return overlapTime;
	}
	
	/**
	 * 计算2个时间段的重叠（交集）时间
	 * @param startDate1 时间段1开始时间戳
	 * @param endDate1 时间段1结束时间戳
	 * @param startDate2 时间段2开始时间戳
	 * @param endDate2 时间段2结束时间戳
	 * @return 返回是重叠时间，单位毫秒
	 */
	public static long overlapTime(Date startDate1, Date endDate1, Date startDate2, Date endDate2){
		return overlapTime(startDate1.getTime(), endDate1.getTime(), startDate2.getTime(), endDate2.getTime());
	}
	
	/**
	 * 计算平均时间
	 * @param dateList 待计算列表
	 * @return 返回平均时间，LocalTime.toString()可以返回格式化字符串，比如：15:03:03 
	 */
	public static LocalTime averageTime(List<Date> dateList) {
		if (CollectionUtil.isEmpty(dateList)) {
			throw new DateTimeException("dateList不能为空");
		}
		double average = dateList.stream().mapToDouble(date -> DateTimeConverterUtil.toLocalTime(date).toNanoOfDay())
				.average().getAsDouble();
		return LocalTime.ofNanoOfDay(new Double(average).longValue());
	}

	/**
	 * 根据毫秒值计算倒计时
	 * @param millis 相差毫秒值
	 * @return 返回倒计时，millis 小于等于0 返回：0小时0分钟0秒
	 */
	public static String countdown(long millis){
		if (millis <= 0) {
            return "0小时0分钟0秒";
        }
		Duration duration = Duration.ofMillis(millis);
		long hours =  duration.getSeconds() / XkTimeConstant.SECONDS_PER_HOUR;
        int minutes = (int) ((duration.getSeconds() % XkTimeConstant.SECONDS_PER_HOUR) / XkTimeConstant.SECONDS_PER_MINUTE);
        int seconds = (int) (duration.getSeconds() % XkTimeConstant.SECONDS_PER_MINUTE);
        StringBuilder buf = new StringBuilder(24);
        buf.append(hours).append("小时");
        buf.append(minutes).append("分钟");
        buf.append(seconds).append("秒");
        return buf.toString();
	}
	
	/**
	 * 根据传入时间和当前时间计算倒计时
	 * @param start 开始时间
	 * @param unitNames 单位，英文逗号分隔，比如"小时,分钟,秒"， "时,分,秒"，"时,分"
	 * @return 返回倒计时，millis 小于等于0 返回：unitNames指定的格式，默认 0小时0分钟0秒
	 */
	public static String countdown(Date start, String unitNames){
		Objects.requireNonNull(start, "start");
		Date end = new Date();
		return countdown(end.getTime()-start.getTime(), unitNames);
	}
	
	/**
	 * 根据起始时间计算倒计时
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param unitNames 单位，英文逗号分隔，比如"小时,分钟,秒"， "时,分,秒"，"时,分"
	 * @return 返回倒计时，millis 小于等于0 返回：unitNames指定的格式，默认 0小时0分钟0秒
	 */
	public static String countdown(Date start, Date end, String unitNames){
		Objects.requireNonNull(start, "start");
		Objects.requireNonNull(end, "end");
		return countdown(end.getTime()-start.getTime(), unitNames);
	}
	
	/**
	 * 根据毫秒值计算倒计时
	 * @param millis 相差毫秒值
	 * @param unitNames 单位，英文逗号分隔，比如"小时,分钟,秒"， "时,分,秒"，"时,分"
	 * @return 返回倒计时，millis 小于等于0 返回：unitNames指定的格式，默认 0小时0分钟0秒
	 */
	public static String countdown(long millis, String unitNames){
		StringBuilder buf = new StringBuilder(24);
		long[] valueArr = new long[]{0, 0, 0};
		
		if(StringUtil.isEmpty(unitNames)){
			unitNames = "小时,分钟,秒";
		}
		
		if (millis <= 0) {
			return getCountDownResult(unitNames, buf, valueArr);
        }
		
		Duration duration = Duration.ofMillis(millis);
		long hours =  duration.getSeconds() / XkTimeConstant.SECONDS_PER_HOUR;
		valueArr[0] = hours;
		int minutes = (int) ((duration.getSeconds() % XkTimeConstant.SECONDS_PER_HOUR) / XkTimeConstant.SECONDS_PER_MINUTE);
		valueArr[1] = minutes;
		int seconds = (int) (duration.getSeconds() % XkTimeConstant.SECONDS_PER_MINUTE);
		valueArr[2] = seconds;
        return getCountDownResult(unitNames, buf, valueArr);
	}
	
	/**
	 * 根据毫秒值计算倒计时，包含天数
	 * @param millis 相差毫秒值
	 * @return 返回倒计时，millis 小于等于0 返回：0天0小时0分钟0秒
	 */
	public static String countdownWithDay(long millis){
		if (millis <= 0) {
			return "0天0小时0分钟0秒";
        }
		Duration duration = Duration.ofMillis(millis);
		long days =  duration.getSeconds() / XkTimeConstant.SECONDS_PER_DAY;
		int hours =  (int) ((duration.getSeconds() % XkTimeConstant.SECONDS_PER_DAY) / XkTimeConstant.SECONDS_PER_HOUR);
        int minutes = (int) ((duration.getSeconds() % XkTimeConstant.SECONDS_PER_HOUR) / XkTimeConstant.SECONDS_PER_MINUTE);
        int seconds = (int) (duration.getSeconds() % XkTimeConstant.SECONDS_PER_MINUTE);
        StringBuilder buf = new StringBuilder(24);
        buf.append(days).append("天");
        buf.append(hours).append("小时");
        buf.append(minutes).append("分钟");
        buf.append(seconds).append("秒");
        return buf.toString();
	}
	
	/**
	 * 根据毫秒值计算倒计时，包含天数
	 * @param millis 相差毫秒值
	 * @param unitNames 单位，英文逗号分隔，比如"天,小时,分钟,秒"， "天,时,分,秒"，"天,时,分"
	 * @return 返回倒计时，millis 小于等于0 返回：unitNames指定的格式，默认0天0小时0分钟0秒 
	 */
	public static String countdownWithDay(long millis, String unitNames){
		StringBuilder buf = new StringBuilder(24);
		long[] valueArr = new long[]{0, 0, 0, 0};
		
		if(StringUtil.isEmpty(unitNames)){
			unitNames = "天,小时,分钟,秒";
		}
		
		if (millis <= 0) {
			return getCountDownResult(unitNames, buf, valueArr);
        }
		Duration duration = Duration.ofMillis(millis);
		long days =  duration.getSeconds() / XkTimeConstant.SECONDS_PER_DAY;
		valueArr[0] = days;
		int hours =  (int) ((duration.getSeconds() % XkTimeConstant.SECONDS_PER_DAY) / XkTimeConstant.SECONDS_PER_HOUR);
		valueArr[1] = hours;
		int minutes = (int) ((duration.getSeconds() % XkTimeConstant.SECONDS_PER_HOUR) / XkTimeConstant.SECONDS_PER_MINUTE);
		valueArr[2] = minutes;
		int seconds = (int) (duration.getSeconds() % XkTimeConstant.SECONDS_PER_MINUTE);
		valueArr[3] = seconds;
		return getCountDownResult(unitNames, buf, valueArr);
	}
	
	/**
	 * 根据传入时间和当前时间计算倒计时
	 * @param start 时间参数
	 * @param unitNames 单位，英文逗号分隔，比如"天,小时,分钟,秒"， "天,时,分,秒"，"天,时,分"
	 * @return 返回倒计时，millis 小于等于0 返回：unitNames指定的格式，默认0天0小时0分钟0秒 
	 */
	public static String countdownWithDay(Date start, String unitNames){
		Objects.requireNonNull(start, "start");
		Date end = new Date();
		return countdownWithDay(end.getTime()-start.getTime(), unitNames);
	}
	
	/**
	 * 根据起始时间计算倒计时
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param unitNames 单位，英文逗号分隔，比如"天,小时,分钟,秒"， "天,时,分,秒"，"天,时,分"
	 * @return 返回倒计时，millis 小于等于0 返回：unitNames指定的格式，默认0天0小时0分钟0秒 
	 */
	public static String countdownWithDay(Date start, Date end, String unitNames){
		Objects.requireNonNull(start, "start");
		Objects.requireNonNull(end, "end");
		return countdownWithDay(end.getTime()-start.getTime(), unitNames);
	}
	
	//====================private======================
	
	/**
	 * 获取倒计时格式化结果
	 * @param unitNames unitNames 单位，英文逗号分隔，比如"天,小时,分钟,秒"， "天,时,分,秒"，"天,时,分","时,分,秒"
	 * @param buf StringBuilder
	 * @param valueArr 时间值数组
	 * @return 返回倒计时格式化结果
	 */
	private static String getCountDownResult(String unitNames, StringBuilder buf, long[] valueArr) {
		String[] unitNameArr = unitNames.split(",");
		for(int i=0; i<unitNameArr.length; i++){
			buf.append(valueArr[i]+unitNameArr[i]);
		}
		return buf.toString();
	}
}
