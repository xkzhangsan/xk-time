package com.xkzhangsan.time.formatter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Objects;

import com.xkzhangsan.time.converter.DateTimeConverterUtil;

/**
 * 日期格式化和解析<br>
 * 包含常用日期格式如：<br>
 * yyyy-MM-dd<br>
 * HH:mm:ss<br>
 * yyyy-MM-dd HH:mm:ss<br>
 * yyyy-MM-dd'T'HH:mm:ssZ等等<br>
 * 
 * 1.格式化方法， format*， 比如formatToDateStr(Date date) 格式化，返回日期部分，如：yyyy-MM-dd；<br> 
 * format(Date date, DateTimeFormatter formatter) formatter 可以选择已定义好的formatter比如YYYY_MM_DD_HH_MM_SS_FMT （yyyy-MM-dd HH:mm:ss）<br>
 * 
 * 2.解析方法， parse*， 比如parseDateStrToDate(String text) 解析日期yyyy-MM-dd，返回Date；<br>
 * parseToDate(String text, DateTimeFormatter formatter) 根据 formatter解析为 Date<br>
 * 
 * 注意：格式化和解析ZonedDateTime 时区时间时，只能使用ISO开头的Formatter，如ISO_DATE_FMT和YYYY_MM_DD_T_HH_MM_SS_Z_FMT<br>
 * 因为，其他Formatter都绑定的是系统默认时区：<br>
 * private static final ZoneId ZONE = ZoneId.systemDefault();<br>
 * 
 * 如果需要使用其他Formatter，可以使用withZone方法重新设置时区，比如：<br>
 * YYYY_MM_DD_HH_MM_SS_SSS_FMT.withZone(ZoneId.of("Europe/Paris")<br>
* @ClassName: DateTimeFormatterUtil 
* @Description: DateTime Formatter
* @author xkzhangsan
* @date 2019年12月1日
*
 */
public class DateTimeFormatterUtil {
	
	private DateTimeFormatterUtil(){
	}
	
	private static final String YYYY = "yyyy";
	
	private static final String YYYY_MM = "yyyy-MM";
  
	private static final String YYYYMM = "yyyyMM";
	
	private static final String YYYY_MM_CN = "yyyy年MM月";	
	
	private static final String YYYY_MM_EN = "yyyy/MM";
    
	private static final String YYYY_MM_DD = "yyyy-MM-dd";
      
	private static final String YYYYMMDD = "yyyyMMdd";
	
	private static final String YYYY_MM_DD_POINT = "yyyy.MM.dd";
    
	private static final String YYYY_MM_DD_CN = "yyyy年MM月dd日";
    
	private static final String YYYY_MM_DD_EN = "yyyy/MM/dd";
	
	private static final String YYYY_MM_DD_E = "yyyy-MM-dd E";
	
	private static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
  
	private static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    
	private static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
      
	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
	private static final String YYYY_MM_DD_HH_MM_SS_CN = "yyyy年MM月dd日 HH:mm:ss";
	
	private static final String YYYY_MM_DD_HH_MM_SS_A_CN = "yyyy年MM月dd日 HH:mm:ss a";	
    
	private static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    
	private static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	
	private static final String MM_DD_HH_MM = "MM-dd HH:mm";
    
	private static final String MM_DD_HH_MM_CN = "MM月dd日 HH:mm";

	private static String HHMMSS = "HHmmss";
    
	private static String HH_MM_SS = "HH:mm:ss";
	
	private static final String MM_DD = "MM-dd";
	
	private static final String MMDD = "MMdd";	
    
	private static final String MM_DD_CN = "MM月dd日";
	
	private static final String MM_DD_HH_MM_SS = "MM-dd HH:mm:ss";	
    
	private static final String MM_DD_HH_MM_SS_CN = "MM月dd日 HH:mm:ss";
	
	/**
	 * ZonedDateTime 时区时间格式
	 */
	private static final String YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ssZ";
    
	//  ============================formatters============================
    
    private static final ZoneId ZONE = ZoneId.systemDefault();
    
    /**
     * such as 2019
     */
	public static final DateTimeFormatter YYYY_FMT = DateTimeFormatter.ofPattern(YYYY).withZone(ZONE);
	
    /**
     * such as 2019-12
     */	
	public static final DateTimeFormatter YYYY_MM_FMT = DateTimeFormatter.ofPattern(YYYY_MM).withZone(ZONE);
  
    /**
     * such as 201912
     */	
	public static final DateTimeFormatter YYYYMM_FMT = DateTimeFormatter.ofPattern(YYYYMM).withZone(ZONE);
	
    /**
     * such as 12月03日
     */
	public static final DateTimeFormatter YYYY_MM_CN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_CN).withZone(ZONE);
	
    /**
     * such as 2019/12
     */	
    public static final DateTimeFormatter YYYY_MM_EN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_EN).withZone(ZONE);
    
    /**
     * such as 2019-12-03
     */	    
	public static final DateTimeFormatter YYYY_MM_DD_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD).withZone(ZONE);
      
    /**
     * such as 20191203
     */	
	public static final DateTimeFormatter YYYYMMDD_FMT = DateTimeFormatter.ofPattern(YYYYMMDD).withZone(ZONE);
	
    /**
     * such as 2019.12.03
     */	
    public static final DateTimeFormatter YYYY_MM_DD_POINT_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_POINT).withZone(ZONE);
    
    /**
     * such as 2019年12月03日
     */    
    public static final DateTimeFormatter YYYY_MM_DD_CN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_CN).withZone(ZONE);
    
    /**
     * such as 2019/12/03
     */    
    public static final DateTimeFormatter YYYY_MM_DD_EN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_EN).withZone(ZONE);
    
    /**
     * such as 2020-01-01 星期三
     */
    public static final DateTimeFormatter YYYY_MM_DD_E_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_E).withZone(ZONE);
	
    /**
     * such as 201912032147
     */     
    public static final DateTimeFormatter YYYYMMDDHHMM_FMT = DateTimeFormatter.ofPattern(YYYYMMDDHHMM).withZone(ZONE);
  
    /**
     * such as 2019-12-03 21:47
     */    
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM).withZone(ZONE);
    
    /**
     * such as 20191203214731
     */    
    public static final DateTimeFormatter YYYYMMDDHHMMSS_FMT = DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS).withZone(ZONE);
     
    /**
     * such as 2019-12-03 21:47:31
     */    
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS).withZone(ZONE);
    
    /**
     * such as 2019年12月03日 21:47:31
     */    
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_CN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_CN).withZone(ZONE);
    
    /**
     * such as 2020年01月01日 00:00:00 上午
     */
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_A_CN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_A_CN).withZone(ZONE);
    
    /**
     * such as 20191203214731714
     */    
    public static final DateTimeFormatter YYYYMMDDHHMMSSSSS_FMT = DateTimeFormatter.ofPattern(YYYYMMDDHHMMSSSSS).withZone(ZONE);
    
    /**
     * such as 2019-12-03 21:47:31.991
     */    
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_SSS_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_SSS).withZone(ZONE);
    
    /**
     * such as 12-03 21:47
     */    
    public static final DateTimeFormatter MM_DD_HH_MM_FMT = DateTimeFormatter.ofPattern(MM_DD_HH_MM).withZone(ZONE);
    
    /**
     * such as 12月03日 21:47
     */    
    public static final DateTimeFormatter MM_DD_HH_MM_CN_FMT = DateTimeFormatter.ofPattern(MM_DD_HH_MM_CN).withZone(ZONE);

    /**
     * such as 214731
     */    
    public static final DateTimeFormatter HHMMSS_FMT = DateTimeFormatter.ofPattern(HHMMSS).withZone(ZONE);
    
    /**
     * such as 21:47:31
     */    
    public static final DateTimeFormatter HH_MM_SS_FMT = DateTimeFormatter.ofPattern(HH_MM_SS).withZone(ZONE);
    
    /**
     * such as 12月03日
     */    
    public static final DateTimeFormatter MM_DD_CN_FMT = DateTimeFormatter.ofPattern(MM_DD_CN).withZone(ZONE);
    
    /**
     * such as 01-01
     */
    public static final DateTimeFormatter MM_DD_FMT = DateTimeFormatter.ofPattern(MM_DD).withZone(ZONE);

    /**
     * such as 0101
     */
    public static final DateTimeFormatter MMDD_FMT = DateTimeFormatter.ofPattern(MMDD).withZone(ZONE);
    
    /**
     * such as 12月03日 21:47:31
     */    
    public static final DateTimeFormatter MM_DD_HH_MM_SS_CN_FMT = DateTimeFormatter.ofPattern(MM_DD_HH_MM_SS_CN).withZone(ZONE);
    
    /**
     * such as 01-01 00:00:00
     */
    public static final DateTimeFormatter MM_DD_HH_MM_SS_FMT = DateTimeFormatter.ofPattern(MM_DD_HH_MM_SS).withZone(ZONE);
    
    /**
     * ZonedDateTime 时区时间格式Formatter
     * such as 2020-02-18T22:37:55+0800
     */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_Z_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_T_HH_MM_SS_Z);
    
    //ISO Formatters
    
    /**
     * such as '2011-12-03' or '2011-12-03+01:00'. 
     */
    public static final DateTimeFormatter ISO_DATE_FMT = DateTimeFormatter.ISO_DATE;
    
    /**
     * such as '2011-12-03T10:15:30','2011-12-03T10:15:30+01:00' or '2011-12-03T10:15:30+01:00[Europe/Paris]'. 
     */
	public static final DateTimeFormatter ISO_DATE_TIME_FMT = DateTimeFormatter.ISO_DATE_TIME;
	
	/**
	 * such as '2011-12-03T10:15:30Z'. 
	 */
	public static final DateTimeFormatter ISO_INSTANT_FMT = DateTimeFormatter.ISO_INSTANT;
	
	/**
	 * such as '2011-12-03'. 
	 */
	public static final DateTimeFormatter ISO_LOCAL_DATE_FMT = DateTimeFormatter.ISO_LOCAL_DATE;
	
	/**
	 * such as '2011-12-03T10:15:30'. 
	 */
	public static final DateTimeFormatter ISO_LOCAL_DATE_TIME_FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	
	/**
	 * such as '10:15' or '10:15:30'. 
	 */
	public static final DateTimeFormatter ISO_LOCAL_TIME_FMT = DateTimeFormatter.ISO_LOCAL_TIME;
	
	
	/**
	 * such as '10:15', '10:15:30' or '10:15:30+01:00'. 
	 */
	public static final DateTimeFormatter ISO_TIME_FMT = DateTimeFormatter.ISO_TIME;
	
	/**
	 * such as '2012-W48-6'. 
	 */
	public static final DateTimeFormatter ISO_WEEK_DATE_FMT = DateTimeFormatter.ISO_WEEK_DATE;
	
	/**
	 * such as '2011-12-03T10:15:30+01:00[Europe/Paris]'. 
	 */
	public static final DateTimeFormatter ISO_ZONED_DATE_TIME_FMT = DateTimeFormatter.ISO_ZONED_DATE_TIME;
	
	/**
	 * such as '20111203'. 
	 */
	public static final DateTimeFormatter BASIC_ISO_DATE_FMT = DateTimeFormatter.BASIC_ISO_DATE;
	
    //  =============================format===========================
    
    /**
     * 格式化，返回日期部分，如：yyyy-MM-dd
     * @param date
     * @return
     */
    public static String formatToDateStr(Date date){
    	return DateTimeConverterUtil.toLocalDateTime(date).format(YYYY_MM_DD_FMT);
    }
    
    /**
     * 格式化，返回日期部分，如：yyyy-MM-dd
     * @param localDateTime
     * @return
     */
    public static String formatToDateStr(LocalDateTime localDateTime){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	return localDateTime.format(YYYY_MM_DD_FMT);
    }
    
    /**
     * 格式化，返回完整日期时间，如：yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatToDateTimeStr(Date date){
    	return DateTimeConverterUtil.toLocalDateTime(date).format(YYYY_MM_DD_HH_MM_SS_FMT);
    }
    
    /**
     * 格式化，返回完整日期时间，如：yyyy-MM-dd HH:mm:ss
     * @param localDateTime
     * @return
     */
    public static String formatToDateTimeStr(LocalDateTime localDateTime){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	return localDateTime.format(YYYY_MM_DD_HH_MM_SS_FMT);
    }
    
    /**
     * 格式化，返回时间部分，如：HH:mm:ss
     * @param date
     * @return
     */
    public static String formatToTimeStr(Date date){
    	return DateTimeConverterUtil.toLocalDateTime(date).format(HH_MM_SS_FMT);
    }
    
    /**
     * 格式化，返回时间部分，如：HH:mm:ss
     * @param localDateTime
     * @return
     */
    public static String formatToTimeStr(LocalDateTime localDateTime){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	return localDateTime.format(HH_MM_SS_FMT);
    }
    
    /**
     * 根据 formatter格式化 date
     * @param date
     * @param formatter
     * @return
     */
    public static String format(Date date, DateTimeFormatter formatter){
    	Objects.requireNonNull(formatter, "formatter");
    	return DateTimeConverterUtil.toLocalDateTime(date).format(formatter);
    }
    
    /**
     * 根据 formatter格式化 localDateTime
     * @param localDateTime
     * @param formatter
     * @return
     */
    public static String format(LocalDateTime localDateTime, DateTimeFormatter formatter){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	Objects.requireNonNull(formatter, "formatter");
    	return localDateTime.format(formatter);
    }
    
    /**
     * 根据 formatter格式化 localDate
     * @param localDate
     * @param formatter
     * @return
     */
    public static String format(LocalDate localDate, DateTimeFormatter formatter){
    	Objects.requireNonNull(formatter, "formatter");
    	return DateTimeConverterUtil.toLocalDateTime(localDate).format(formatter);
    }
    
    /**
     * 根据 formatter格式化 localTime
     * @param localTime
     * @param formatter
     * @return
     */
    public static String format(LocalTime localTime, DateTimeFormatter formatter){
    	Objects.requireNonNull(localTime, "localTime");
    	Objects.requireNonNull(formatter, "formatter");
    	return localTime.format(formatter);
    }
    
    /**
     * 根据 formatter格式化 instant
     * @param instant
     * @param formatter
     * @return
     */
    public static String format(Instant instant, DateTimeFormatter formatter){
    	Objects.requireNonNull(instant, "instant");
    	Objects.requireNonNull(formatter, "formatter");
    	return formatter.format(instant);
    }
    
    public static String format(TemporalAccessor temporal, DateTimeFormatter formatter){
    	Objects.requireNonNull(temporal, "temporal");
    	Objects.requireNonNull(formatter, "formatter");
    	return formatter.format(temporal);
    }
    
    //  ============================parse============================

    /**
     * 解析日期yyyy-MM-dd，返回Date
     * @param text
     * @return
     */
    public static Date parseDateStrToDate(String text){
    	return DateTimeConverterUtil.toDate(LocalDate.parse(text, YYYY_MM_DD_FMT));
    }
    
    /**
     * 解析日期yyyy-MM-dd，返回LocalDateTime
     * @param text
     * @return
     */
    public static LocalDateTime parseDateStrToLocalDateTime(String text){
    	return DateTimeConverterUtil.toLocalDateTime(LocalDate.parse(text, YYYY_MM_DD_FMT));
    }
    
    /**
     * 解析日期yyyy-MM-dd，返回Instant
     * @param text
     * @return
     */
    public static Instant parseDateStrToInstant(String text){
    	return DateTimeConverterUtil.toInstant(LocalDate.parse(text, YYYY_MM_DD_FMT));
    }
    
    /**
     * 解析日期yyyy-MM-dd HH:mm:ss，返回Date
     * @param text
     * @return
     */
    public static Date parseDateTimeStrToDate(String text){
    	return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(YYYY_MM_DD_HH_MM_SS_FMT.parse(text)));
    }
    
    /**
     * 解析日期yyyy-MM-dd HH:mm:ss，返回LocalDateTime
     * @param text
     * @return
     */
    public static LocalDateTime parseDateTimeStrToLocalDateTime(String text){
    	return DateTimeConverterUtil.toLocalDateTime(YYYY_MM_DD_HH_MM_SS_FMT.parse(text));
    }
    
    /**
     * 解析日期yyyy-MM-dd HH:mm:ss，返回Instant
     * @param text
     * @return
     */
    public static Instant parseDateTimeStrToInstant(String text){
    	return DateTimeConverterUtil.toInstant(YYYY_MM_DD_HH_MM_SS_FMT.parse(text));
    }
    
    /**
     * 根据 formatter解析为 Date
     * @param text
     * @param formatter
     * @return
     */
    public static Date parseToDate(String text, DateTimeFormatter formatter){
    	Objects.requireNonNull(formatter, "formatter");
    	Date date = null;
    	try {
    		date = DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(formatter.parse(text)));
		} catch (Exception e) {
			date = DateTimeConverterUtil.toDate(LocalDate.parse(text, formatter));
		}
    	return date;
    }
    
    /**
     * 根据 formatter解析为 LocalDateTime
     * @param text
     * @param formatter
     * @return
     */
    public static LocalDateTime parseToLocalDateTime(String text, DateTimeFormatter formatter){
    	Objects.requireNonNull(formatter, "formatter");
    	LocalDateTime localDateTime = null;
    	try {
    		localDateTime = DateTimeConverterUtil.toLocalDateTime(formatter.parse(text));
		} catch (Exception e) {
			localDateTime = DateTimeConverterUtil.toLocalDateTime(LocalDate.parse(text, formatter));
		}
    	return localDateTime;
    }
    
    /**
     * 根据 formatter解析为 Instant
     * @param text
     * @param formatter
     * @return
     */
    public static Instant parseToInstant(String text, DateTimeFormatter formatter){
    	Objects.requireNonNull(formatter, "formatter");
    	Instant instant = null;
    	try {
    		instant = DateTimeConverterUtil.toInstant(formatter.parse(text));
		} catch (Exception e) {
			instant = DateTimeConverterUtil.toInstant(LocalDate.parse(text, formatter));
		}
    	return instant;
    }
    
    /**
     * 根据 formatter解析为 ZonedDateTime
     * @param text
     * @param formatter
     * @return
     */
    public static ZonedDateTime parseToZonedDateTime(String text, DateTimeFormatter formatter){
    	return ZonedDateTime.parse(text, formatter);
    }
    
}
