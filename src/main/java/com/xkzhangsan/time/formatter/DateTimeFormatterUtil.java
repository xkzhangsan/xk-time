package com.xkzhangsan.time.formatter;

import com.xkzhangsan.time.constants.Constant;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.utils.StringUtil;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * 日期格式化和解析<br>
 * 包含常用日期格式如：<br>
 * yyyy-MM-dd<br>
 * HH:mm:ss<br>
 * yyyy-MM-dd HH:mm:ss<br>
 * yyyy-MM-dd HH:mm:ss.SSS<br>
 * yyyy-MM-dd'T'HH:mm:ssZ等等<br>
 * yyyy-MM-dd'T'HH:mm:ss.SSSZ等等<br>
 * 
 * 1.格式化方法， format*， 比如formatToDateStr(Date date) 格式化，返回日期部分，如：yyyy-MM-dd；<br> 
 * format(Date date, DateTimeFormatter formatter) formatter 可以选择已定义好的formatter比如YYYY_MM_DD_HH_MM_SS_FMT （yyyy-MM-dd HH:mm:ss）<br>
 * 
 * 2.解析方法， parse*， 比如parseDateStrToDate(String text) 解析日期yyyy-MM-dd，返回Date；<br>
 * parseToDate(String text, DateTimeFormatter formatter) 根据 formatter解析为 Date<br>
 * 
 * 3.自动解析方法，根据字符串特点自动识别解析，smartParse*，比如smartParseToDate(String text) 自动解析Date<br>
 * 
 * 注意：格式化和解析ZonedDateTime 时区时间时，只能使用ISO开头的Formatter，如ISO_DATE_FMT和YYYY_MM_DD_T_HH_MM_SS_Z_FMT<br>
 * 因为，其他Formatter都绑定的是系统默认时区：<br>
 * private static final ZoneId ZONE = ZoneId.systemDefault();<br>
 * 
 * 如果需要使用其他Formatter，可以使用withZone方法重新设置时区，比如：<br>
 * YYYY_MM_DD_HH_MM_SS_SSS_FMT.withZone(ZoneId.of("Europe/Paris")<br>
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
	
	/**
	 * 12小时制
	 */
	private static final String YYYY_MM_DD_HH_MM_SS_A_CN = "yyyy年MM月dd日 hh:mm:ss a";
	
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
	
	/**
	 * ZonedDateTime 时区时间格式 带 :  0时区时，+00:00
	 */
	private static final String YYYY_MM_DD_T_HH_MM_SS_XXX = "yyyy-MM-dd'T'HH:mm:ssxxx";
	
	/**
	 * ZonedDateTime 时区时间格式 带毫秒
	 */
	private static final String YYYY_MM_DD_T_HH_MM_SS_SSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	
	/**
	 * ZonedDateTime 时区时间格式 带毫秒  带 : 0时区时，+00:00
	 */
	private static final String YYYY_MM_DD_T_HH_MM_SS_SSS_XXX = "yyyy-MM-dd'T'HH:mm:ss.SSSxxx";
	
	/**
	 * ZonedDateTime 时区时间格式 带 :  0时区时，Z
	 */
	private static final String YYYY_MM_DD_T_HH_MM_SS_XXX_Z = "yyyy-MM-dd'T'HH:mm:ssXXX";
	
	/**
	 * ZonedDateTime 时区时间格式 带毫秒 带 :  0时区时，Z
	 */
	private static final String YYYY_MM_DD_T_HH_MM_SS_SSS_XXX_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	
	//add for excel date
	private static final String YY_MM_DD_EN = "yy/MM/dd";
	
	private static final String MM_DD_YY_EN = "MM/dd/yy";
	
	private static final String MM_DD_EN = "MM/dd";
	
	/**
	 * 2012-3-14
	 */
	private static final String YYYY_M_D = "yyyy-M-d";
	/**
	 * 2012/3/14
	 */
	private static final String YYYY_M_D_EN = "yyyy/M/d";
	/**
	 * 5月1日
	 */
	private static final String M_D_CN = "M月d日";
	/**
	 * 5/1
	 */
	private static final String M_D_EN = "M/d";
	
	//add for excel time
	private static String HH_MM_SS_CN = "HH时mm分ss秒";
	
	private static String HH_MM_CN = "HH时mm分";
	
	private static String HH_MM = "HH:mm";
	
	private static String HH_MM_A = "hh:mm a";
	
	private static String H_M_S = "H:m:s";
	
	private static String H_M = "H:m";
	
	//add for excel date time
	private static final String YYYY_MM_DD_HH_MM_SS_CN_ALL = "yyyy年MM月dd日 HH时mm分ss秒";
	
	private static final String YYYY_MM_DD_HH_MM_SS_A_CN_ALL = "yyyy年MM月dd日 HH时mm分ss秒 a";
	
	private static final String YYYY_MM_DD_HH_MM_SS_EN = "yyyy/MM/dd HH:mm:ss";
	
	private static final String YYYY_MM_DD_HH_MM_EN = "yyyy/MM/dd HH:mm";
	
	private static final String YYYY_M_D_H_M_S = "yyyy-M-d H:m:s";
	
	private static final String YYYY_M_D_H_M_S_EN = "yyyy/M/d H:m:s";
	
	private static final String YYYY_M_D_H_M_EN = "yyyy/M/d H:m";
	
	/**
	 * 带毫秒
	 */
	private static final String YYYY_M_D_H_M_S_SSS = "yyyy-M-d H:m:s.SSS";
	
	/**
	 * 带毫秒
	 */
	private static final String YYYY_M_D_H_M_S_SSS_EN = "yyyy/M/d H:m:s.SSS";
	
	/**
	 * 带毫秒 带时区
	 */
	private static final String YYYY_M_D_T_H_M_S_SSS_Z = "yyyy-M-d'T'H:m:s.SSSZ";
	
	/**
	 * 带毫秒 带时区 +08:00
	 */
	private static final String YYYY_M_D_T_H_M_S_SSS_XXX = "yyyy-M-d'T'H:m:s.SSSxxx";
	
	/**
	 * 带毫秒 带时区 +08:00
	 */
	private static final String YYYY_M_D_T_H_M_S_SSS_XXX_EN = "yyyy/M/d'T'H:m:s.SSSxxx";
	
	/**
	 * 带毫秒 带时区
	 */
	private static final String YYYY_M_D_T_H_M_S_SSS_Z_EN = "yyyy/M/d'T'H:m:s.SSSZ";
	
	/**
	 * Date 默认格式  Thu May 21 22:58:05 CST 2020
	 */
	private static final String EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY = "EEE MMM dd HH:mm:ss zzz yyyy";
    
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
     * such as 2020年01月01日 00:00:00 上午 12小时制
     */
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_A_CN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_A_CN).withZone(ZONE);
    
    /**
     * such as 2020年04月29日 02:46:29 PM 12小时制 AM PM
     */
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_A_AM_PM_CN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_A_CN, Locale.ENGLISH).withZone(ZONE);
    
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

    /**
     * ZonedDateTime 时区时间格式Formatter 带: 0时区时，+00:00
     * such as 2020-02-18T22:37:55+08:00
     */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_XXX_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_T_HH_MM_SS_XXX);
    
    /**
     * ZonedDateTime 时区时间格式Formatter 带毫秒
     * such as 2020-02-18T22:37:55.991+0800
     */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_SSS_Z_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_T_HH_MM_SS_SSS_Z);

    /**
     * ZonedDateTime 时区时间格式Formatter 带毫秒  带: 0时区时，+00:00
     * such as 2020-02-18T22:37:55.991+08:00
     */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_SSS_XXX_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_T_HH_MM_SS_SSS_XXX);
    
    
    /**
     * ZonedDateTime 时区时间格式Formatter 带: 0时区时，Z
     * such as 2020-02-18T22:37:55+08:00
     */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_XXX_Z_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_T_HH_MM_SS_XXX_Z);
    
    /**
     * ZonedDateTime 时区时间格式Formatter 带毫秒  带: 0时区时，Z
     * such as 2020-02-18T22:37:55.991+08:00
     */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_SSS_XXX_Z_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_T_HH_MM_SS_SSS_XXX_Z);
    
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
	
	//add for excel date
	/**
	 * such as 20/04/29
	 */
	public static final DateTimeFormatter YY_MM_DD_EN_FMT = DateTimeFormatter.ofPattern(YY_MM_DD_EN);
	
	/**
	 * such as 04/29/20
	 */
	public static final DateTimeFormatter MM_DD_YY_EN_FMT = DateTimeFormatter.ofPattern(MM_DD_YY_EN);
	
	/**
	 * such as 04/29
	 */
	public static final DateTimeFormatter MM_DD_EN_FMT = DateTimeFormatter.ofPattern(MM_DD_EN);
	
	/**
	 * 2012-3-14 不补0
	 */
	public static final DateTimeFormatter YYYY_M_D_FMT = DateTimeFormatter.ofPattern(YYYY_M_D);
	/**
	 * 2012/3/14
	 */
	public static final DateTimeFormatter YYYY_M_D_EN_FMT = DateTimeFormatter.ofPattern(YYYY_M_D_EN);
	/**
	 * 5月1日
	 */
	public static final DateTimeFormatter M_D_CN_FMT = DateTimeFormatter.ofPattern(M_D_CN);
	/**
	 * 5/1
	 */
	public static final DateTimeFormatter M_D_EN_FMT = DateTimeFormatter.ofPattern(M_D_EN);
	
	//add for excel time
	/**
	 * such as 14时46分29秒
	 */
	public static DateTimeFormatter HH_MM_SS_CN_FMT = DateTimeFormatter.ofPattern(HH_MM_SS_CN);
	
	/**
	 * such as 14时46分
	 */
	public static DateTimeFormatter HH_MM_CN_FMT = DateTimeFormatter.ofPattern(HH_MM_CN);
	
	/**
	 * such as 14:46
	 */
	public static DateTimeFormatter HH_MM_FMT = DateTimeFormatter.ofPattern(HH_MM);
	
	/**
	 * such as 02:46 下午
	 */
	public static DateTimeFormatter HH_MM_A_FMT = DateTimeFormatter.ofPattern(HH_MM_A);
	
	/**
	 * such as 02:46 PM 12小时制 AM PM
	 */
	public static DateTimeFormatter HH_MM_A_AM_PM_FMT = DateTimeFormatter.ofPattern(HH_MM_A, Locale.ENGLISH);
	
	public static DateTimeFormatter H_M_S_FMT = DateTimeFormatter.ofPattern(H_M_S);
	
	public static DateTimeFormatter H_M_FMT = DateTimeFormatter.ofPattern(H_M);
	//add for excel date time
	/**
	 * such as 2020年04月29日 14时46分29秒
	 */
	public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_CN_ALL_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_CN_ALL);
	
	/**
	 * such as 2020年04月29日 14时46分29秒 下午
	 */
	public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_A_CN_ALL_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_A_CN_ALL);
	
	/**
	 * such as 2020年04月29日 14时46分29秒  PM 12小时制 AM PM
	 */
	public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_A_AM_PM_CN_ALL_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_A_CN_ALL, Locale.ENGLISH);
	
	/**
	 * such as 2020/04/29 14:46:29
	 */
	public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_EN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_EN);
	
	/**
	 * such as 2020/04/29 14:46
	 */
	public static final DateTimeFormatter YYYY_MM_DD_HH_MM_EN_FMT = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_EN);

	/**
	 * such as 2020-4-9 1:1:1
	 */
	public static final DateTimeFormatter YYYY_M_D_H_M_S_FMT = DateTimeFormatter.ofPattern(YYYY_M_D_H_M_S);
	
	/**
	 * such as 2020/4/9 1:1:1
	 */
	public static final DateTimeFormatter YYYY_M_D_H_M_S_EN_FMT = DateTimeFormatter.ofPattern(YYYY_M_D_H_M_S_EN);
	
	/**
	 * such as 2020/4/9 1:1
	 */
	public static final DateTimeFormatter YYYY_M_D_H_M_EN_FMT = DateTimeFormatter.ofPattern(YYYY_M_D_H_M_EN);
	
	/**
	 * such as 2020-4-9 1:1:1.111
	 */
	public static final DateTimeFormatter YYYY_M_D_H_M_S_SSS_FMT = DateTimeFormatter.ofPattern(YYYY_M_D_H_M_S_SSS);
	
	/**
	 * such as 2020/4/9 1:1:1.111
	 */
	public static final DateTimeFormatter YYYY_M_D_H_M_S_SSS_EN_FMT = DateTimeFormatter.ofPattern(YYYY_M_D_H_M_S_SSS_EN);
	
	/**
	 * such as 2020-4-9 1:1:1.111+0800
	 */
	public static final DateTimeFormatter YYYY_M_D_T_H_M_S_SSS_Z_FMT = DateTimeFormatter.ofPattern(YYYY_M_D_T_H_M_S_SSS_Z);
	
	/**
	 * such as 2020-4-9T1:1:1.111+08:00
	 */
	public static final DateTimeFormatter YYYY_M_D_T_H_M_S_SSS_XXX_FMT = DateTimeFormatter.ofPattern(YYYY_M_D_T_H_M_S_SSS_XXX);
	
	/**
	 * such as 2020-4-9T1:1:1.111+08:00
	 */
	public static final DateTimeFormatter YYYY_M_D_T_H_M_S_SSS_XXX_EN_FMT = DateTimeFormatter.ofPattern(YYYY_M_D_T_H_M_S_SSS_XXX_EN);
	/**
	 * such as 2020/4/9T1:1:1.111+0800
	 */
	public static final DateTimeFormatter YYYY_M_D_T_H_M_S_SSS_Z_EN_FMT = DateTimeFormatter.ofPattern(YYYY_M_D_T_H_M_S_SSS_Z_EN);
	
	/**
	 * Date 默认格式  Thu May 21 22:58:05 CST 2020
	 */
	public static final DateTimeFormatter EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY_FMT = DateTimeFormatter.ofPattern(EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY, Locale.ENGLISH);
	
    //  =============================format===========================
    
    /**
     * 格式化，返回日期部分，如：yyyy-MM-dd
     * @param date
     * @return String
     */
    public static String formatToDateStr(Date date){
    	return DateTimeConverterUtil.toLocalDateTime(date).format(YYYY_MM_DD_FMT);
    }
    
    /**
     * 格式化，返回日期部分，如：yyyy-MM-dd 指定时区
     * @param date
     * @param zoneId
     * @return String
     */
    public static String formatToDateStr(Date date, String zoneId){
		return StringUtil.isNotEmpty(zoneId)
				? DateTimeConverterUtil.toZonedDateTime(date).format(YYYY_MM_DD_FMT.withZone(ZoneId.of(zoneId)))
				: DateTimeConverterUtil.toZonedDateTime(date).format(YYYY_MM_DD_FMT.withZone(null));
    }
    
    /**
     * 格式化，返回日期部分，如：yyyy-MM-dd
     * @param localDateTime
     * @return String
     */
    public static String formatToDateStr(LocalDateTime localDateTime){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	return localDateTime.format(YYYY_MM_DD_FMT);
    }
    
    /**
     * 格式化，返回日期部分，如：yyyy-MM-dd 指定时区
     * @param localDateTime
     * @param zoneId
     * @return String
     */
    public static String formatToDateStr(LocalDateTime localDateTime, String zoneId){
    	Objects.requireNonNull(localDateTime, "localDateTime");
		return StringUtil.isNotEmpty(zoneId) ? localDateTime.format(YYYY_MM_DD_FMT.withZone(ZoneId.of(zoneId)))
				: localDateTime.format(YYYY_MM_DD_FMT.withZone(null));
    }
    
    /**
     * 格式化，返回日期部分，如：yyyy-MM-dd
     * @param zonedDateTime
     * @return String
     */
    public static String formatToDateStr(ZonedDateTime zonedDateTime){
    	Objects.requireNonNull(zonedDateTime, "zonedDateTime");
    	return zonedDateTime.format(YYYY_MM_DD_FMT);
    }
    
    /**
     * 格式化，返回日期部分，如：yyyy-MM-dd 指定时区
     * @param zonedDateTime
     * @param zoneId
     * @return String
     */
    public static String formatToDateStr(ZonedDateTime zonedDateTime, String zoneId){
    	Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return StringUtil.isNotEmpty(zoneId) ? zonedDateTime.format(YYYY_MM_DD_FMT.withZone(ZoneId.of(zoneId)))
				: zonedDateTime.format(YYYY_MM_DD_FMT.withZone(null));
    }
    
    /**
     * 格式化，返回完整日期时间，如：yyyy-MM-dd HH:mm:ss
     * @param date
     * @return String
     */
    public static String formatToDateTimeStr(Date date){
    	return DateTimeConverterUtil.toZonedDateTime(date).format(YYYY_MM_DD_HH_MM_SS_FMT);
    }
    
    /**
     * 格式化，返回完整日期时间，如：yyyy-MM-dd HH:mm:ss 指定时区
     * @param date
     * @param zoneId 
     * @return String
     */
    public static String formatToDateTimeStr(Date date, String zoneId){
		return StringUtil.isNotEmpty(zoneId)
				? DateTimeConverterUtil.toZonedDateTime(date)
						.format(YYYY_MM_DD_HH_MM_SS_FMT.withZone(ZoneId.of(zoneId)))
				: DateTimeConverterUtil.toZonedDateTime(date).format(YYYY_MM_DD_HH_MM_SS_FMT.withZone(null));
    }
    
    /**
     * 格式化，返回完整日期时间，如：yyyy-MM-dd HH:mm:ss
     * @param localDateTime
     * @return String
     */
    public static String formatToDateTimeStr(LocalDateTime localDateTime){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	return localDateTime.format(YYYY_MM_DD_HH_MM_SS_FMT);
    }
    
    /**
     * 格式化，返回完整日期时间，如：yyyy-MM-dd HH:mm:ss 指定时区
     * @param localDateTime
     * @param zoneId
     * @return String
     */
    public static String formatToDateTimeStr(LocalDateTime localDateTime, String zoneId){
    	Objects.requireNonNull(localDateTime, "localDateTime");
		return StringUtil.isNotEmpty(zoneId) ? localDateTime.format(YYYY_MM_DD_HH_MM_SS_FMT.withZone(ZoneId.of(zoneId)))
				: localDateTime.format(YYYY_MM_DD_HH_MM_SS_FMT.withZone(null));
    }
    
    /**
     * 格式化，返回完整日期时间，如：yyyy-MM-dd HH:mm:ss
     * @param zonedDateTime
     * @return String
     */
    public static String formatToDateTimeStr(ZonedDateTime zonedDateTime){
    	Objects.requireNonNull(zonedDateTime, "zonedDateTime");
    	return zonedDateTime.format(YYYY_MM_DD_HH_MM_SS_FMT);
    }
    
    /**
     * 格式化，返回完整日期时间，如：yyyy-MM-dd HH:mm:ss 指定时区
     * @param zonedDateTime
     * @param zoneId
     * @return String
     */
    public static String formatToDateTimeStr(ZonedDateTime zonedDateTime, String zoneId){
    	Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return StringUtil.isNotEmpty(zoneId) ? zonedDateTime.format(YYYY_MM_DD_HH_MM_SS_FMT.withZone(ZoneId.of(zoneId)))
				: zonedDateTime.format(YYYY_MM_DD_HH_MM_SS_FMT.withZone(null));
    }
    
    /**
     * 格式化，返回时间部分，如：HH:mm:ss
     * @param date
     * @return String
     */
    public static String formatToTimeStr(Date date){
    	return DateTimeConverterUtil.toLocalDateTime(date).format(HH_MM_SS_FMT);
    }
    
    /**
     * 格式化，返回时间部分，如：HH:mm:ss
     * @param localDateTime
     * @return String
     */
    public static String formatToTimeStr(LocalDateTime localDateTime){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	return localDateTime.format(HH_MM_SS_FMT);
    }
    
    /**
     * 根据 formatter格式化 date
     * @param date
     * @param formatter
     * @return String
     */
    public static String format(Date date, DateTimeFormatter formatter){
    	Objects.requireNonNull(formatter, "formatter");
    	return DateTimeConverterUtil.toZonedDateTime(date).format(formatter);
    }
    
    /**
     * 根据 formatter格式化 date 支持自定义时区
     * @param date
     * @param formatter
     * @param zoneId
     * @return String
     */
    public static String format(Date date, DateTimeFormatter formatter, String zoneId){
    	Objects.requireNonNull(formatter, "formatter");
    	if(StringUtil.isNotEmpty(zoneId)){
    		formatter.withZone(ZoneId.of(zoneId));
    	}else{
    		formatter.withZone(null);
    	}
		return StringUtil.isNotEmpty(zoneId)
				? DateTimeConverterUtil.toZonedDateTime(date).format(formatter.withZone(ZoneId.of(zoneId)))
				: DateTimeConverterUtil.toZonedDateTime(date).format(formatter.withZone(null));
    }
    
    /**
     * 根据 formatter格式化 localDateTime
     * @param localDateTime
     * @param formatter
     * @return String
     */
    public static String format(LocalDateTime localDateTime, DateTimeFormatter formatter){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	Objects.requireNonNull(formatter, "formatter");
    	return localDateTime.format(formatter);
    }
    
    /**
     * 根据 formatter格式化 localDateTime 指定时区
     * @param localDateTime
     * @param formatter
     * @param zoneId
     * @return String
     */
    public static String format(LocalDateTime localDateTime, DateTimeFormatter formatter, String zoneId){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	Objects.requireNonNull(formatter, "formatter");
    	
		return StringUtil.isNotEmpty(zoneId) ? localDateTime.format(formatter.withZone(ZoneId.of(zoneId)))
				: localDateTime.format(formatter.withZone(ZoneId.of(zoneId)));
    }
    
    /**
     * 根据 formatter格式化 localDate
     * @param localDate
     * @param formatter
     * @return String
     */
    public static String format(LocalDate localDate, DateTimeFormatter formatter){
    	Objects.requireNonNull(formatter, "formatter");
    	return DateTimeConverterUtil.toLocalDateTime(localDate).format(formatter);
    }
    
    /**
     * 根据 formatter格式化 localTime
     * @param localTime
     * @param formatter
     * @return String
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
     * @return String
     */
    public static String format(Instant instant, DateTimeFormatter formatter){
    	Objects.requireNonNull(instant, "instant");
    	Objects.requireNonNull(formatter, "formatter");
    	return formatter.format(instant);
    }
    
    /**
     * 根据 formatter格式化 zonedDateTime
     * @param zonedDateTime
     * @param formatter
     * @return String
     */
    public static String format(ZonedDateTime zonedDateTime, DateTimeFormatter formatter){
    	Objects.requireNonNull(zonedDateTime, "zonedDateTime");
    	Objects.requireNonNull(formatter, "formatter");
    	return zonedDateTime.format(formatter);
    }
    
    /**
     * 根据 formatter格式化 zonedDateTime 指定时区
     * @param zonedDateTime
     * @param formatter
     * @param zoneId
     * @return String
     */
    public static String format(ZonedDateTime zonedDateTime, DateTimeFormatter formatter, String zoneId){
    	Objects.requireNonNull(zonedDateTime, "zonedDateTime");
    	Objects.requireNonNull(formatter, "formatter");
		return StringUtil.isNotEmpty(zoneId) ? zonedDateTime.format(formatter.withZone(ZoneId.of(zoneId)))
				: zonedDateTime.format(formatter.withZone(null));
    }
    
    public static String format(TemporalAccessor temporal, DateTimeFormatter formatter){
    	Objects.requireNonNull(temporal, "temporal");
    	Objects.requireNonNull(formatter, "formatter");
    	return formatter.format(temporal);
    }
    
    public static String format(TemporalAccessor temporal, DateTimeFormatter formatter, String zoneId){
    	Objects.requireNonNull(temporal, "temporal");
    	Objects.requireNonNull(formatter, "formatter");
    	if(StringUtil.isNotEmpty(zoneId)){
    		formatter.withZone(ZoneId.of(zoneId));
    	}else{
    		formatter.withZone(null);
    	}
		return StringUtil.isNotEmpty(zoneId) ? formatter.withZone(ZoneId.of(zoneId)).format(temporal)
				: formatter.withZone(null).format(temporal);
    }
    
    //  ============================parse============================

    /**
     * 解析日期yyyy-MM-dd，返回Date
     * @param text
     * @return Date
     */
    public static Date parseDateStrToDate(String text){
    	return DateTimeConverterUtil.toDate(LocalDate.parse(text, YYYY_MM_DD_FMT));
    }
    
    /**
     * 解析日期yyyy-MM-dd，返回LocalDateTime
     * @param text
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateStrToLocalDateTime(String text){
    	return DateTimeConverterUtil.toLocalDateTime(LocalDate.parse(text, YYYY_MM_DD_FMT));
    }
    
    /**
     * 解析日期yyyy-MM-dd，返回Instant
     * @param text
     * @return Instant
     */
    public static Instant parseDateStrToInstant(String text){
    	return DateTimeConverterUtil.toInstant(LocalDate.parse(text, YYYY_MM_DD_FMT));
    }
    
    /**
     * 解析日期yyyy-MM-dd HH:mm:ss，返回Date
     * @param text
     * @return Date
     */
    public static Date parseDateTimeStrToDate(String text){
    	return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(YYYY_MM_DD_HH_MM_SS_FMT.parse(text)));
    }
    
    /**
     * 解析日期yyyy-MM-dd HH:mm:ss，返回LocalDateTime
     * @param text
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTimeStrToLocalDateTime(String text){
    	return DateTimeConverterUtil.toLocalDateTime(YYYY_MM_DD_HH_MM_SS_FMT.parse(text));
    }
    
    /**
     * 解析日期yyyy-MM-dd HH:mm:ss，返回Instant
     * @param text
     * @return Instant
     */
    public static Instant parseDateTimeStrToInstant(String text){
    	return DateTimeConverterUtil.toInstant(YYYY_MM_DD_HH_MM_SS_FMT.parse(text));
    }
    
    /**
     * 根据 formatter解析为 Date
     * @param text
     * @param formatter
     * @return Date
     */
    public static Date parseToDate(String text, DateTimeFormatter formatter){
    	Objects.requireNonNull(formatter, "formatter");
    	Date date = null;
    	try {
    		date = DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(formatter.parse(text)));
		} catch (DateTimeException e) {
			if(e.getMessage().startsWith(Constant.PARSE_LOCAL_DATE_EXCEPTION)){
				date = DateTimeConverterUtil.toDate(LocalDate.parse(text, formatter));
			}else{
				throw e;
			}
		}
    	return date;
    }
    
    /**
     * 根据 formatter解析为 LocalDateTime
     * @param text
     * @param formatter
     * @return LocalDateTime
     */
    public static LocalDateTime parseToLocalDateTime(String text, DateTimeFormatter formatter){
    	Objects.requireNonNull(formatter, "formatter");
    	LocalDateTime localDateTime = null;
    	try {
    		localDateTime = DateTimeConverterUtil.toLocalDateTime(formatter.parse(text));
		} catch (DateTimeException e) {
			if(e.getMessage().startsWith(Constant.PARSE_LOCAL_DATE_EXCEPTION)){
				localDateTime = DateTimeConverterUtil.toLocalDateTime(LocalDate.parse(text, formatter));
			}else{
				throw e;
			}
		}
    	return localDateTime;
    }
    
    /**
     * 根据 formatter解析为 Instant
     * @param text
     * @param formatter
     * @return Instant
     */
    public static Instant parseToInstant(String text, DateTimeFormatter formatter){
    	Objects.requireNonNull(formatter, "formatter");
    	Instant instant = null;
    	try {
    		instant = DateTimeConverterUtil.toInstant(formatter.parse(text));
		} catch (DateTimeException e) {
			if(e.getMessage().startsWith(Constant.PARSE_LOCAL_DATE_EXCEPTION)){
				instant = DateTimeConverterUtil.toInstant(LocalDate.parse(text, formatter));
			}else{
				throw e;
			}
		}
    	return instant;
    }
    
	/**
	 * 根据 formatter解析为 ZonedDateTime
	 * @param text
	 * @param formatter
	 * @return ZonedDateTime
	 */
    public static ZonedDateTime parseToZonedDateTime(String text, DateTimeFormatter formatter){
    	return ZonedDateTime.parse(text, formatter);
    }
    
    
	/**
	 * 自动解析为 Date
	 * 支持格式 yyyy-M-d yyyy/M/d yyyy-M-d H:m:s yyyy/M/d H:m:s yyyy-M-d H:m:s.S yyyy/M/d H:m:s.S yyyy-M-d'T'H:m:s.SSSxxx yyyy/M/d'T'H:m:s.SSSxxx
	 * @param text 
	 * @return Date
	 */
    public static Date smartParseToDate(String text){
		return DateTimeConverterUtil.toDate(smartParseToLocalDateTime(text));
    }
    
	/**
	 * 自动解析为 LocalDateTime
	 * 支持格式 yyyy-M-d yyyy/M/d yyyy-M-d H:m:s yyyy/M/d H:m:s yyyy-M-d H:m:s.S yyyy/M/d H:m:s.S yyyy-M-d'T'H:m:s.SSSxxx yyyy/M/d'T'H:m:s.SSSxxx
	 * @param text
	 * @return LocalDateTime
	 */
    public static LocalDateTime smartParseToLocalDateTime(String text){
    	if(StringUtil.isEmpty(text)){
    		throw new DateTimeException("text is null");
    	}
    	if((!text.contains("-")) && (!text.contains("/"))){
    		throw new DateTimeException("text is not supported!");
    	}
    	
    	if(text.length() <= 10){
    		if(text.contains("-")){
    			return parseToLocalDateTime(text, YYYY_M_D_FMT);
    		} else if(text.contains("/")){
    			return parseToLocalDateTime(text, YYYY_M_D_EN_FMT);
    		}
		} else if (text.contains(":") && text.contains(".")
				&& (text.contains("+") || text.contains("-"))) {
    		if(text.contains("-")){
    			return parseToLocalDateTime(text, YYYY_M_D_T_H_M_S_SSS_XXX_FMT);
    		} else if(text.contains("/")){
    			return parseToLocalDateTime(text, YYYY_M_D_T_H_M_S_SSS_XXX_EN_FMT);
    		}
    	} else if (text.contains(":") && text.contains(".")){
    		if(text.contains("-")){
    			return parseToLocalDateTime(text, YYYY_M_D_H_M_S_SSS_FMT);
    		} else if(text.contains("/")){
    			return parseToLocalDateTime(text, YYYY_M_D_H_M_S_SSS_EN_FMT);
    		}
		} else if (text.contains(":")){
    		if(text.contains("-")){
    			return parseToLocalDateTime(text, YYYY_M_D_H_M_S_FMT);
    		} else if(text.contains("/")){
    			return parseToLocalDateTime(text, YYYY_M_D_H_M_S_EN_FMT);
    		}
    	}
    	throw new DateTimeException("text is not supported!");
    }
    
}
