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
	
	private static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
  
	private static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    
	private static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
      
	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
	private static final String YYYY_MM_DD_HH_MM_SS_CN = "yyyy年MM月dd日 HH:mm:ss";
    
	private static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    
	private static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    
	private static final String MM_DD_HH_MM = "MM-dd HH:mm";
    
	private static final String MM_DD_HH_MM_CN = "MM月dd日 HH:mm";

	private static String HHMMSS = "HHmmss";
    
	private static String HH_MM_SS = "HH:mm:ss";
    
	private static final String MM_DD_CN = "MM月dd日";
    
	private static final String MM_DD_HH_MM_SS_CN = "MM月dd日 HH:mm:ss";
    
	//  ============================formatters============================
    
    private static final ZoneId ZONE = ZoneId.systemDefault();
    
	public static final DateTimeFormatter YYYY_FMT = DateTimeFormatter.ofPattern(YYYY).withZone(ZONE);
	
	public static final DateTimeFormatter YYYY_MM_FMT = DateTimeFormatter.ofPattern(YYYY_MM).withZone(ZONE);
  
	public static final DateTimeFormatter YYYYMM_FMT = DateTimeFormatter.ofPattern(YYYYMM).withZone(ZONE);
	
	public static final DateTimeFormatter YYYY_MM_CN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_CN).withZone(ZONE);
	
    public static final DateTimeFormatter YYYY_MM_EN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_EN).withZone(ZONE);
    
	public static final DateTimeFormatter YYYY_MM_DD_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD).withZone(ZONE);
      
	public static final DateTimeFormatter YYYYMMDD_FMT = DateTimeFormatter.ofPattern(YYYYMMDD).withZone(ZONE);
	
    public static final DateTimeFormatter YYYY_MM_DD_POINT_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_POINT).withZone(ZONE);
    
    public static final DateTimeFormatter YYYY_MM_DD_CN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_CN).withZone(ZONE);
    
    public static final DateTimeFormatter YYYY_MM_DD_EN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_EN).withZone(ZONE);
	
    public static final DateTimeFormatter YYYYMMDDHHMM_FMT = DateTimeFormatter.ofPattern(YYYYMMDDHHMM).withZone(ZONE);
  
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM).withZone(ZONE);
    
    public static final DateTimeFormatter YYYYMMDDHHMMSS_FMT = DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS).withZone(ZONE);
      
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS).withZone(ZONE);
    
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_CN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_CN).withZone(ZONE);
    
    public static final DateTimeFormatter YYYYMMDDHHMMSSSSS_FMT = DateTimeFormatter.ofPattern(YYYYMMDDHHMMSSSSS).withZone(ZONE);
    
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_SSS_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_SSS).withZone(ZONE);
    
    public static final DateTimeFormatter MM_DD_HH_MM_FMT = DateTimeFormatter.ofPattern(MM_DD_HH_MM).withZone(ZONE);
    
    public static final DateTimeFormatter MM_DD_HH_MM_CN_FMT = DateTimeFormatter.ofPattern(MM_DD_HH_MM_CN).withZone(ZONE);

    public static final DateTimeFormatter HHMMSS_FMT = DateTimeFormatter.ofPattern(HHMMSS).withZone(ZONE);
    
    public static final DateTimeFormatter HH_MM_SS_FMT = DateTimeFormatter.ofPattern(HH_MM_SS).withZone(ZONE);
    
    public static final DateTimeFormatter MM_DD_CN_FMT = DateTimeFormatter.ofPattern(MM_DD_CN).withZone(ZONE);
    
    public static final DateTimeFormatter MM_DD_HH_MM_SS_CN_FMT = DateTimeFormatter.ofPattern(MM_DD_HH_MM_SS_CN).withZone(ZONE);
    
    //  =============================format===========================
    
    public static String format(Date date, DateTimeFormatter formatter){
    	Objects.requireNonNull(formatter, "formatter");
    	return DateTimeConverterUtil.toLocalDateTime(date).format(formatter);
    }
    
    
    public static String format(LocalDateTime localDateTime, DateTimeFormatter formatter){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	Objects.requireNonNull(formatter, "formatter");
    	return localDateTime.format(formatter);
    }
    
    public static String format(LocalDate localDate, DateTimeFormatter formatter){
    	Objects.requireNonNull(formatter, "formatter");
    	return DateTimeConverterUtil.toLocalDateTime(localDate).format(formatter);
    }
    
    public static String format(LocalTime localTime, DateTimeFormatter formatter){
    	Objects.requireNonNull(localTime, "localTime");
    	Objects.requireNonNull(formatter, "formatter");
    	return localTime.format(formatter);
    }
    
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
    
    public static Date parseToDate(String str, DateTimeFormatter formatter){
    	return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(formatter.parse(str)));
    }
    
    public static LocalDateTime parseToLocalDateTime(String str, DateTimeFormatter formatter){
    	return DateTimeConverterUtil.toLocalDateTime(formatter.parse(str));
    }
    
    public static Instant parseToInstant(String str, DateTimeFormatter formatter){
    	return DateTimeConverterUtil.toInstant(formatter.parse(str));
    }
    
}
