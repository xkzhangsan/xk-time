package com.xkzhangsan.time.formatter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Objects;

import com.xkzhangsan.time.converter.DateTimeConverterUtil;

/**
 * 
* @ClassName: DateTimeFormatterUtil 
* @Description: DateTime Formatter
* @author xkzhangsan
* @date 2019年12月1日
*
 */
public class DateTimeFormatterUtil {
	
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
     * @param str
     * @return
     */
    public static Date parseDateStrToDate(String str){
    	return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(YYYY_MM_DD_FMT.parse(str)));
    }
    
    /**
     * 解析日期yyyy-MM-dd，返回LocalDateTime
     * @param str
     * @return
     */
    public static LocalDateTime parseDateStrToLocalDateTime(String str){
    	return DateTimeConverterUtil.toLocalDateTime(YYYY_MM_DD_FMT.parse(str));
    }
    
    /**
     * 解析日期yyyy-MM-dd，返回Instant
     * @param str
     * @return
     */
    public static Instant parseDateStrToInstant(String str){
    	return DateTimeConverterUtil.toInstant(YYYY_MM_DD_FMT.parse(str));
    }
    
    /**
     * 解析日期yyyy-MM-dd HH:mm:ss，返回Date
     * @param str
     * @return
     */
    public static Date parseDateTimeStrToDate(String str){
    	return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(YYYY_MM_DD_HH_MM_SS_FMT.parse(str)));
    }
    
    /**
     * 解析日期yyyy-MM-dd HH:mm:ss，返回LocalDateTime
     * @param str
     * @return
     */
    public static LocalDateTime parseDateTimeStrToLocalDateTime(String str){
    	return DateTimeConverterUtil.toLocalDateTime(YYYY_MM_DD_HH_MM_SS_FMT.parse(str));
    }
    
    /**
     * 解析日期yyyy-MM-dd HH:mm:ss，返回Instant
     * @param str
     * @return
     */
    public static Instant parseDateTimeStrToInstant(String str){
    	return DateTimeConverterUtil.toInstant(YYYY_MM_DD_HH_MM_SS_FMT.parse(str));
    }
    
    /**
     * 根据 formatter解析为 Date
     * @param str
     * @param formatter
     * @return
     */
    public static Date parseToDate(String str, DateTimeFormatter formatter){
    	return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(formatter.parse(str)));
    }
    
    /**
     * 根据 formatter解析为 LocalDateTime
     * @param str
     * @param formatter
     * @return
     */
    public static LocalDateTime parseToLocalDateTime(String str, DateTimeFormatter formatter){
    	return DateTimeConverterUtil.toLocalDateTime(formatter.parse(str));
    }
    
    /**
     * 根据 formatter解析为 Instant
     * @param str
     * @param formatter
     * @return
     */
    public static Instant parseToInstant(String str, DateTimeFormatter formatter){
    	return DateTimeConverterUtil.toInstant(formatter.parse(str));
    }
    
}
