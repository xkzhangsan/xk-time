package com.xkzhangsan.time.formatter;

import static com.xkzhangsan.time.enums.ZoneIdEnum.CTT;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.constants.Constant;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.enums.ChineseDateDigitEnum;
import com.xkzhangsan.time.enums.CommonTimeEnum;
import com.xkzhangsan.time.enums.RegexEnum;
import com.xkzhangsan.time.utils.ArrayUtil;
import com.xkzhangsan.time.utils.CollectionUtil;
import com.xkzhangsan.time.utils.StringUtil;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期格式化和解析<br>
 * 包含常用日期格式如：<br>
 * yyyy-MM-dd<br>
 * HH:mm:ss<br>
 * yyyy-MM-dd HH:mm:ss<br>
 * yyyy-MM-dd HH:mm:ss.SSS<br>
 * yyyy-MM-dd HH:mm:ss.SSSSSS<br>
 * yyyy-MM-dd HH:mm:ss.SSSSSSSSS<br>
 * yyyy-MM-dd'T'HH:mm:ss.SSSZ等等，支持毫秒、微秒和纳秒等精确时间。<br>
 * 
 * 1.格式化方法， format*， 比如formatToDateStr(Date date) 格式化，返回日期部分，如：yyyy-MM-dd；<br> 
 * format(Date date, DateTimeFormatter formatter) formatter 可以选择已定义好的formatter比如YYYY_MM_DD_HH_MM_SS_FMT （yyyy-MM-dd HH:mm:ss）<br>
 * 
 * 2.解析方法， parse*， 比如parseDateStrToDate(String text) 解析日期yyyy-MM-dd，返回Date；<br>
 * parseToDate(String text, DateTimeFormatter formatter) 根据 formatter解析为 Date<br>
 * 
 * 3.自动解析方法，根据字符串特点自动识别解析，smartParse*，比如 smartParseToDate(String text) 自动解析Date<br>
 * 
 * 4.ISO格式（包含T）自动解析方法，根据字符串特点自动识别解析，parseIso*，比如 parseIsoToDate(String text) 自动解析Date<br>
 *
 * 5.解析时间戳方法, parseEpochMilli*， 比如parseEpochMilliToDate(String text)，解析时间戳为Date，如 1590224790000<br>
 *
 * 6.解析Date默认格式，parseDateDefaultStr*，比如parseDateDefaultStrToDate(String text) <br>
 *     解析 EEE MMM dd HH:mm:ss zzz yyyy 比如：  Sat May 23 17:06:30 CST 2020 为Date <br>
 *     
 * 7.自定义时区格式化方法，比如 format(Date date, DateTimeFormatter formatter, String zoneId)，根据zoneId格式化Date <br>
 *
 * 8.自定义模板格式化方法，比如 format(Date date, String dateFormatPattern)，根据dateFormatPattern格式化Date <br>
 * 
 * 9.自定义模板解析方法，比如 parseToDate(String text, String dateFormatPattern)，根据dateFormatPattern格式化Date <br>
 * 
 * 10.Timestamp默认格式（ yyyy-mm-dd hh:mm:ss.fffffffff 其中 fffffffff 纳秒，省略后面的0）格式化方法，比如 formatTimestampStyle(Date date) <br>
 * 
 * 11.Timestamp默认格式（ yyyy-mm-dd hh:mm:ss.fffffffff 其中 fffffffff 纳秒，省略后面的0）解析方法，比如 parseTimestampStyleToDate(String text) <br>
 *
 * 12.验证日期格式是否正确方法，isValidDate*， 比如isValidDate(String text)，验证yyyy-MM-dd 格式字符串是否正确 <br>
 * 
 * 13.根据自定义模板数组解析方法， 比如parseToDate(String text, String[] dateFormatPatterns)，dateFormatPatterns 支持多种模板，只要其中一个解析成功就返回对应Date <br>
 *
 * 14.解析自然语言时间，今天，明天，下周，下月，明年，昨天，上周，上月，去年等， 比如parseNaturalLanguageToDate(String text), <br>
 * {@code parseNaturalLanguageToDate(String text, Map<String, String> naturalLanguageMap)} 支持自定义解析自然语言时间map <br>
 *
 * 15.中文日期格式化方法，比如formatToChineseDateStr(Date date, boolean isUpperCase)，isUpperCase false：2021年09月11日 true： 二〇二一年九月十一日<br>
 * 
 * 16.中文日期解析方法，比如parseChineseDateStrToDate(String text)，支持：2021年09月11日 和 二〇二一年九月十一日格式日期解析<br>
 * 
 * 注意：格式化和解析与系统时区不同的时间时，使用自定义时区格式化方法，或可以使用withZone方法重新设置时区，比如：<br>
 * YYYY_MM_DD_HH_MM_SS_SSS_FMT.withZone(ZoneId.of("Europe/Paris")<br>
 *
* @author xkzhangsan
*
 */
public class DateTimeFormatterUtil {
	
	private DateTimeFormatterUtil(){
	}
    
	/**
	 * 系统默认时区
	 */
    private static final ZoneId ZONE = ZoneId.systemDefault();
    
	/**
	 * 上海时区ID Asia/Shanghai
	 */
    public static final String SHANGHAI_ZONE_ID = CTT.getZoneIdName();
    
	/**
	 * 上海时区  Asia/Shanghai
	 */
    public static final ZoneId SHANGHAI_ZONE = ZoneId.of(SHANGHAI_ZONE_ID);
    
    // ==================================yyyy-MM-dd相关formatters==================================
	/**
	 * yyyy-MM-dd 比如：  2020-05-23
	 */
    public static final DateTimeFormatter YYYY_MM_DD_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD).withZone(ZONE);
    /**
     * yyyy-M-d 不补0 比如：  2020-5-23
     */
    public static final DateTimeFormatter YYYY_M_D_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_M_D).withZone(ZONE);
    
	/**
	 * yyyyMMdd  比如：  20200523
	 */
	public static final DateTimeFormatter YYYYMMDD_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYYMMDD).withZone(ZONE);
	
	/**
	 * yyyy/MM/dd  比如：  2020/05/23
	 */   
    public static final DateTimeFormatter YYYY_MM_DD_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_EN).withZone(ZONE);
	
	/**
	 * yyyy/M/d 不补0  比如：  2020/5/23
	 */
	public static final DateTimeFormatter YYYY_M_D_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_M_D_EN).withZone(ZONE);
	
	/**
	 * yyyy年MM月dd日  比如： 2020年05月23日
	 */    
    public static final DateTimeFormatter YYYY_MM_DD_CN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_CN).withZone(ZONE);
	
	/**
	 * yyyy年M月d日  比如： 2020年5月23日
	 */
	public static final DateTimeFormatter YYYY_M_D_CN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_M_D_CN).withZone(ZONE);
	
	/**
	 * yyyy.MM.dd  比如：2020.05.23
	 */
    public static final DateTimeFormatter YYYY_MM_DD_POINT_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_POINT).withZone(ZONE);
	
	/**
	 * yyyy.M.d 不补0  比如：2020.5.23
	 */
	public static final DateTimeFormatter YYYY_M_D_POINT_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_M_D_POINT).withZone(ZONE);
	
	/**
	 * yy/MM/dd 不补0  比如：20/05/23
	 */
	public static final DateTimeFormatter YY_MM_DD_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YY_MM_DD_EN).withZone(ZONE);
	
	/**
	 * yy/M/d  比如：20/5/23
	 */
	public static final DateTimeFormatter YY_M_D_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YY_M_D_EN).withZone(ZONE);
	
	/**
	 * MM/dd/yy 不补0  比如：05/23/20
	 */
	public static final DateTimeFormatter MM_DD_YY_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.MM_DD_YY_EN).withZone(ZONE);
	
	/**
	 * M/d/yy  比如：5/23/20
	 */
	public static final DateTimeFormatter M_D_YY_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.M_D_YY_EN).withZone(ZONE);
	
	/**
	 * yyyy-MM-dd E 不补0  比如：2020-05-23 星期六
	 */
    public static final DateTimeFormatter YYYY_MM_DD_E_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_E).withZone(ZONE);
    
	/**
	 * yy 年的后2位  比如： 20
	 */
    public static final DateTimeFormatter YY_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YY).withZone(ZONE);
    
	/**
	 * yyyy  比如：2020
	 */
	public static final DateTimeFormatter YYYY_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY).withZone(ZONE);
	
	/**
	 * yyyy-MM  比如：2020-05
	 */
	public static final DateTimeFormatter YYYY_MM_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM).withZone(ZONE);
  
	/**
	 * yyyyMM  比如：202005
	 */
	public static final DateTimeFormatter YYYYMM_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYYMM).withZone(ZONE);
	
	/**
	 * yyyy/MM  比如：2020/05
	 */
    public static final DateTimeFormatter YYYY_MM_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_EN).withZone(ZONE);
    
	/**
	 * yyyy年MM月  比如：2020年05月
	 */
	public static final DateTimeFormatter YYYY_MM_CN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_CN).withZone(ZONE);
	
	/**
	 * yyyy年M月  比如：2020年5月
	 */
	public static final DateTimeFormatter YYYY_M_CN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_M_CN).withZone(ZONE);
	
	/**
	 * MM-dd  比如：05-23
	 */
    public static final DateTimeFormatter MM_DD_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.MM_DD).withZone(ZONE);
    
	/**
	 * MMdd  比如：0523
	 */
    public static final DateTimeFormatter MMDD_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.MMDD).withZone(ZONE);
    
	/**
	 * MM/dd  比如：05/23
	 */
	public static final DateTimeFormatter MM_DD_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.MM_DD_EN).withZone(ZONE);
    
	/**
	 * M/d  比如：5/23
	 */
	public static final DateTimeFormatter M_D_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.M_D_EN).withZone(ZONE);
    
	/**
	 * MM月dd日  比如：05月23日
	 */ 
    public static final DateTimeFormatter MM_DD_CN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.MM_DD_CN).withZone(ZONE);
    
	/**
	 * M月d日 不补0  比如：5月23日
	 */
	public static final DateTimeFormatter M_D_CN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.M_D_CN).withZone(ZONE);
    
    
	
	
	// ==================================HH:mm:ss 相关formatters==================================
	
	/**
	 * HH:mm:ss  比如：17:26:30
	 */
    public static final DateTimeFormatter HH_MM_SS_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.HH_MM_SS).withZone(ZONE);
    
	/**
	 * H:m:s  比如：17:6:30
	 */
	public static DateTimeFormatter H_M_S_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.H_M_S).withZone(ZONE);
	
	/**
	 * HHmmss  比如：170630
	 */
    public static final DateTimeFormatter HHMMSS_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.HHMMSS).withZone(ZONE);
    
	/**
	 * HH时mm分ss秒  比如：17时06分30秒
	 */
	public static DateTimeFormatter HH_MM_SS_CN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.HH_MM_SS_CN).withZone(ZONE);
    
	/**
	 * HH:mm  比如：17:06
	 */
	public static DateTimeFormatter HH_MM_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.HH_MM).withZone(ZONE);
    
	/**
	 * H:m  比如：17:6
	 */
	public static DateTimeFormatter H_M_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.H_M).withZone(ZONE);
    
	/**
	 * HH时mm分 比如：17时06分
	 */
	public static DateTimeFormatter HH_MM_CN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.HH_MM_CN).withZone(ZONE);
    
	/**
	 * hh:mm a 比如：05:06 下午
	 */
	public static DateTimeFormatter HH_MM_A_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.HH_MM_A).withZone(ZONE);
	
	/**
	 * hh:mm a 比如：05:06 PM  AM PM
	 */
	public static DateTimeFormatter HH_MM_A_AM_PM_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.HH_MM_A, Locale.ENGLISH).withZone(ZONE);
    
    
	// ==================================HH:mm:ss.SSS 相关formatters==================================
	
	/**
	 * HH:mm:ss.SSS  比如：17:26:30.272
	 */
	public static DateTimeFormatter HH_MM_SS_SSS_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.HH_MM_SS_SSS).withZone(ZONE);
	
	// ==================================HH:mm:ss.SSSSSS 相关formatters==================================
	
	/**
	 * HH:mm:ss.SSSSSS  比如：17:26:30.272150
	 */
	public static DateTimeFormatter HH_MM_SS_SSSSSS_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.HH_MM_SS_SSSSSS).withZone(ZONE);
	
	// ==================================HH:mm:ss.SSSSSSSSS 相关formatters==================================
	
	/**
	 * HH:mm:ss.SSSSSSSSS  比如：17:26:30.272150620
	 */
	public static DateTimeFormatter HH_MM_SS_SSSSSSSSS_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.HH_MM_SS_SSSSSSSSS).withZone(ZONE);	
	
	
	// ==================================yyyy-MM-dd HH:mm:ss 相关formatters==================================
    
	/**
	 * yyyy-MM-dd HH:mm:ss 比如：2020-05-23 17:06:30
	 */
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_HH_MM_SS).withZone(ZONE);

	/**
	 * yyyy-M-d H:m:s 比如：2020-5-23 17:6:30
	 */
	public static final DateTimeFormatter YYYY_M_D_H_M_S_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_M_D_H_M_S).withZone(ZONE);
    
	/**
	 * yyyyMMddHHmmss 比如：20200523170630
	 */
    public static final DateTimeFormatter YYYYMMDDHHMMSS_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYYMMDDHHMMSS).withZone(ZONE);
    
	/**
	 * yyyy/MM/dd HH:mm:ss 比如：2020/05/23 17:06:30
	 */
	public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_HH_MM_SS_EN).withZone(ZONE);
	
	/**
	 * yyyy/M/d H:m:s 比如：2020/5/23 17:6:30
	 */
	public static final DateTimeFormatter YYYY_M_D_H_M_S_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_M_D_H_M_S_EN).withZone(ZONE);

	/**
	 * yyyy年MM月dd日 HH:mm:ss 比如：2020年05月23日 17:06:30
	 */
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_CN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_HH_MM_SS_CN).withZone(ZONE);

	/**
	 * yyyy年MM月dd日 HH时mm分ss秒 比如：2020年05月23日 17时06分30秒
	 */
	public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_CN_ALL_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_HH_MM_SS_CN_ALL).withZone(ZONE);
	
	/**
	 * yyyy-MM-dd HH:mm 比如：2020-05-23 17:06
	 */
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_HH_MM).withZone(ZONE);
	
	/**
	 * yyyy-M-d H:m 比如：2020-5-23 17:6
	 */
	public static final DateTimeFormatter YYYY_M_D_H_M_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_M_D_H_M).withZone(ZONE);
	
	/**
	 * yyyyMMddHHmm 比如：202005231706
	 */
    public static final DateTimeFormatter YYYYMMDDHHMM_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYYMMDDHHMM).withZone(ZONE);
	
	/**
	 * yyyy/MM/dd HH:mm 比如：2020/05/23 17:06
	 */
	public static final DateTimeFormatter YYYY_MM_DD_HH_MM_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_HH_MM_EN).withZone(ZONE);
	
	/**
	 * yyyy/M/d H:m 比如：2020/5/23 17:6
	 */
	public static final DateTimeFormatter YYYY_M_D_H_M_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_M_D_H_M_EN).withZone(ZONE);
	
	/**
	 * yyyy/M/d h:m a 比如：2020/5/23 5:6 下午 跟随系统
	 */
	public static final DateTimeFormatter YYYY_M_D_H_M_A_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_M_D_H_M_A_EN).withZone(ZONE);
	
	/**
	 * yyyy/M/d h:m a 比如：2020/5/23 5:6 PM  AM，PM
	 */
	public static final DateTimeFormatter YYYY_M_D_H_M_A_AM_PM_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_M_D_H_M_A_EN, Locale.ENGLISH).withZone(ZONE);
	/**
	 * MM-dd HH:mm 比如：05-23 17:06
	 */
    public static final DateTimeFormatter MM_DD_HH_MM_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.MM_DD_HH_MM).withZone(ZONE);

	/**
	 * MM月dd日 HH:mm 比如：05月23日 17:06
	 */
    public static final DateTimeFormatter MM_DD_HH_MM_CN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.MM_DD_HH_MM_CN).withZone(ZONE);
  
	/**
	 * MM-dd HH:mm:ss 比如：05-23 17:06:30
	 */
    public static final DateTimeFormatter MM_DD_HH_MM_SS_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.MM_DD_HH_MM_SS).withZone(ZONE);
    
	/**
	 * MM月dd日 HH:mm:ss 比如：05月23日 17:06:30
	 */
    public static final DateTimeFormatter MM_DD_HH_MM_SS_CN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.MM_DD_HH_MM_SS_CN).withZone(ZONE);
    
	/**
	 * yyyy年MM月dd日 hh:mm:ss a 比如：2020年05月23日 05:06:30 下午
	 */
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_A_CN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_HH_MM_SS_A_CN).withZone(ZONE);
    
    /**
     * yyyy年MM月dd日 hh:mm:ss a 比如：2020年05月23日 05:06:30 PM
     */
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_A_AM_PM_CN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_HH_MM_SS_A_CN, Locale.ENGLISH).withZone(ZONE);
    
	/**
	 * yyyy年MM月dd日 hh时mm分ss秒 a 比如：2020年05月23日 17时06分30秒 下午
	 */
	public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_A_CN_ALL_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_HH_MM_SS_A_CN_ALL).withZone(ZONE);
	
	/**
	 * yyyy年MM月dd日 hh时mm分ss秒 a 比如：2020年05月23日 17时06分30秒 PM
	 */
	public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_A_AM_PM_CN_ALL_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_HH_MM_SS_A_CN_ALL, Locale.ENGLISH).withZone(ZONE);
    
	
	
	
	// ==================================yyyy-MM-dd HH:mm:ss.SSS 相关formatters==================================
	
	/**
	 * yyyy-MM-dd HH:mm:ss.SSS 比如：2020-05-23 17:06:30.272
	 */
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_SSS_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_HH_MM_SS_SSS).withZone(ZONE);
    
	/**
	 * yyyy-MM-dd HH:mm:ss,SSS 比如：2020-05-23 17:06:30,272
	 */
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_SSS_COMMA_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_HH_MM_SS_SSS_COMMA).withZone(ZONE);
	
	/**
	 * yyyyMMddHHmmssSSS 比如：20200523170630272 <br>
	 *  Jdk8 解析 yyyyMMddHHmmssSSS bug，推荐解决用法 :https://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8031085
	 */
    public static final DateTimeFormatter YYYYMMDDHHMMSSSSS_FMT = new DateTimeFormatterBuilder().appendPattern(DateFormatPattern.YYYYMMDDHHMMSS).appendValue(ChronoField.MILLI_OF_SECOND, 3).toFormatter().withZone(ZONE);
    
	/**
	 * yyyy-M-d H:m:s.SSS 比如：2020-5-23 17:6:30.272
	 */
	public static final DateTimeFormatter YYYY_M_D_H_M_S_SSS_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_M_D_H_M_S_SSS).withZone(ZONE);
	
	/**
	 * yyyy/M/d H:m:s.SSS 比如：2020/5/23 17:6:30.272
	 */
	public static final DateTimeFormatter YYYY_M_D_H_M_S_SSS_EN_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_M_D_H_M_S_SSS_EN).withZone(ZONE);
	
	/**
	 * yyyy-M-d H:m:s,SSS 比如：2020-5-23 17:6:30,272
	 */
	public static final DateTimeFormatter YYYY_M_D_H_M_S_SSS_COMMA_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_M_D_H_M_S_SSS_COMMA).withZone(ZONE);	
	
	
	
	// ==================================yyyy-MM-dd HH:mm:ss.SSSSSS 相关formatters==================================
	/**
	 * yyyy-MM-dd HH:mm:ss.SSSSSS 比如：2020-05-23 17:06:30.272150
	 */
	public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_SSSSSS_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_HH_MM_SS_SSSSSS).withZone(ZONE);
	
	
	// ==================================yyyy-MM-dd HH:mm:ss.SSSSSSSSS 相关formatters==================================
	/**
	 * yyyy-MM-dd HH:mm:ss.SSSSSSSSS 比如：2020-05-23 17:06:30.272150620
	 */
	public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_SSSSSSSSS_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_HH_MM_SS_SSSSSSSSS).withZone(ZONE);
	
	
	
	// ==================================Iso相关formatters 包含 T （自定义）==================================

	/**
	 * yyyy-MM-dd'T'HH:mm:ssZ 比如：2020-05-23T17:06:30+0800
	 */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_Z_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_T_HH_MM_SS_Z);

	/**
	 * yyyy-MM-dd'T'HH:mm:ssxxx 比如：2020-05-23T17:06:30+08:00 0时区时末尾 为+00:00
	 */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_XXX_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_T_HH_MM_SS_XXX);
    
	/**
	 * yyyy-MM-dd'T'HH:mm:ssXXX 比如：2020-05-23T17:06:30+08:00 0时区时末尾 为Z
	 */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_XXX_Z_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_T_HH_MM_SS_XXX_Z);
    
	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSZ 比如：2020-05-23T17:06:30.272+0800
	 */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_SSS_Z_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_T_HH_MM_SS_SSS_Z);
    
	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSxxx 比如：2020-05-23T17:06:30.272+08:00
	 */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_SSS_XXX_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_T_HH_MM_SS_SSS_XXX);

	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSXXX 比如：2020-05-23T17:06:30.272+08:00 0时区时末尾 为Z
	 */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_SSS_XXX_Z_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_T_HH_MM_SS_SSS_XXX_Z);
    
	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ 比如：2020-05-23T17:06:30.272150+0800 2020-05-23T09:06:30.272150+0000
	 */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_SSSSSS_Z_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_T_HH_MM_SS_SSSSSS_Z);
    
	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSSSSxxx 比如：2020-05-23T17:06:30.272150+08:00 2020-05-23T09:06:30.272150+00:00
	 */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_SSSSSS_XXX_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_T_HH_MM_SS_SSSSSS_XXX);
    
	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX 比如：2020-05-23T17:06:30.272150+08:00 2020-05-23T09:06:30.272150Z 0时区时末尾 为Z
	 */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_SSSSSS_XXX_Z_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_T_HH_MM_SS_SSSSSS_XXX_Z);
    
	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSZ 比如：2020-05-23T17:06:30.272150620+0800 2020-05-23T09:06:30.272150620+0000
	 */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_SSSSSSSSS_Z_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_T_HH_MM_SS_SSSSSSSSS_Z);
    
	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSxxx 比如：2020-05-23T17:06:30.272150620+08:00 2020-05-23T09:06:30.272150620+00:00
	 */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_SSSSSSSSS_XXX_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_T_HH_MM_SS_SSSSSSSSS_XXX);    
    
	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSXXX 比如：2020-05-23T17:06:30.272150620+08:00 2020-05-23T09:06:30.272150620Z 0时区时末尾 为Z
	 */
    public static final DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_SSSSSSSSS_XXX_Z_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.YYYY_MM_DD_T_HH_MM_SS_SSSSSSSSS_XXX_Z);
    // ==================================Iso相关formatters 包含 T （Jdk）==================================
    
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
	
	
	
	// ==================================其他格式 formatters==================================
	
	/**
	 * Date 默认格式 EEE MMM dd HH:mm:ss zzz yyyy 比如：  Sat May 23 17:06:30 CST 2020
	 */
	public static final DateTimeFormatter EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY_FMT = DateTimeFormatter.ofPattern(DateFormatPattern.EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY, Locale.ENGLISH);
	
    /**
     * 根据格式化模板返回DateTimeFormatter，默认系统时区
     * @param dateFormatPattern 格式化模板
     * @return DateTimeFormatter
     */
    public static DateTimeFormatter getDateTimeFormatter(String dateFormatPattern){
    	return DateTimeFormatter.ofPattern(dateFormatPattern).withZone(ZONE);
    }
	
	// ==================================format==================================
	
    /**
     * 格式化，返回日期部分，如：yyyy-MM-dd
     * @param date Date
     * @return String
     */
    public static String formatToDateStr(Date date){
    	return DateTimeConverterUtil.toLocalDateTime(date).format(YYYY_MM_DD_FMT);
    }
    
    /**
     * 中文日期格式化，isUpperCase false：2021年09月11日 true： 二〇二一年九月十一日
     * @param date Date
     * @param isUpperCase 是否大写，false：2021年09月11日 true： 二〇二一年九月十一日
     * @return String
     */
    public static String formatToChineseDateStr(Date date, boolean isUpperCase){
    	return formatToChineseDateStr(DateTimeConverterUtil.toLocalDateTime(date), isUpperCase);
    }
    
    /**
     * 中文日期格式化，isUpperCase false：2021年09月11日 true： 二〇二一年九月十一日
     * @param localDateTime LocalDateTime
     * @param isUpperCase 是否大写，false：2021年09月11日 true： 二〇二一年九月十一日
     * @return String
     */
    public static String formatToChineseDateStr(LocalDateTime localDateTime, boolean isUpperCase){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	if(isUpperCase){
    		StringBuilder buf = new StringBuilder();
        	//年
        	String year = String.valueOf(localDateTime.getYear());
        	int yearLength = year.length();
        	for(int i=0; i<yearLength; i++){
        		buf.append(ChineseDateDigitEnum.ENUMS[year.charAt(i)-48].getChineseDigit());
        	}
        	buf.append("年");
        	//月
        	buf.append(ChineseDateDigitEnum.ENUMS[localDateTime.getMonthValue()].getChineseDigit());
        	buf.append("月");
        	//日
        	buf.append(ChineseDateDigitEnum.ENUMS[localDateTime.getDayOfMonth()].getChineseDigit());
        	buf.append("日");
        	return buf.toString();
    	}else{
    		return format(localDateTime, YYYY_MM_DD_CN_FMT);
    	}
    }    
    
    /**
     * 格式化，返回日期部分，如：yyyy-MM-dd 指定时区
     * @param date Date
     * @param zoneId 时区
     * @return String
     */
    public static String formatToDateStr(Date date, String zoneId){
		return StringUtil.isNotEmpty(zoneId)
				? DateTimeConverterUtil.toZonedDateTime(date).format(YYYY_MM_DD_FMT.withZone(ZoneId.of(zoneId)))
				: DateTimeConverterUtil.toZonedDateTime(date).format(YYYY_MM_DD_FMT.withZone(null));
    }
    
    /**
     * 格式化，返回日期部分，如：yyyy-MM-dd
     * @param localDateTime LocalDateTime
     * @return String
     */
    public static String formatToDateStr(LocalDateTime localDateTime){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	return localDateTime.format(YYYY_MM_DD_FMT);
    }
    
    /**
     * 格式化，返回日期部分，如：yyyy-MM-dd 指定时区
     * @param localDateTime LocalDateTime
     * @param zoneId 时区
     * @return String
     */
    public static String formatToDateStr(LocalDateTime localDateTime, String zoneId){
    	Objects.requireNonNull(localDateTime, "localDateTime");
		return StringUtil.isNotEmpty(zoneId) ? localDateTime.format(YYYY_MM_DD_FMT.withZone(ZoneId.of(zoneId)))
				: localDateTime.format(YYYY_MM_DD_FMT.withZone(null));
    }
    
    /**
     * 格式化，返回日期部分，如：yyyy-MM-dd
     * @param zonedDateTime ZonedDateTime
     * @return String
     */
    public static String formatToDateStr(ZonedDateTime zonedDateTime){
    	Objects.requireNonNull(zonedDateTime, "zonedDateTime");
    	return zonedDateTime.format(YYYY_MM_DD_FMT);
    }
    
    /**
     * 格式化，返回日期部分，如：yyyy-MM-dd 指定时区
     * @param zonedDateTime ZonedDateTime
     * @param zoneId 时区
     * @return String
     */
    public static String formatToDateStr(ZonedDateTime zonedDateTime, String zoneId){
    	Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return StringUtil.isNotEmpty(zoneId) ? zonedDateTime.format(YYYY_MM_DD_FMT.withZone(ZoneId.of(zoneId)))
				: zonedDateTime.format(YYYY_MM_DD_FMT.withZone(null));
    }
    
    /**
     * 格式化，返回完整日期时间，如：yyyy-MM-dd HH:mm:ss
     * @param date Date
     * @return String
     */
    public static String formatToDateTimeStr(Date date){
    	return DateTimeConverterUtil.toZonedDateTime(date).format(YYYY_MM_DD_HH_MM_SS_FMT);
    }
    
    /**
     * 格式化，返回完整日期时间，如：yyyy-MM-dd HH:mm:ss 指定时区
     * @param date Date
     * @param zoneId 时区
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
     * @param localDateTime LocalDateTime
     * @return String
     */
    public static String formatToDateTimeStr(LocalDateTime localDateTime){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	return localDateTime.format(YYYY_MM_DD_HH_MM_SS_FMT);
    }
    
    /**
     * 格式化，返回完整日期时间，如：yyyy-MM-dd HH:mm:ss 指定时区
     * @param localDateTime LocalDateTime
     * @param zoneId 时区
     * @return String
     */
    public static String formatToDateTimeStr(LocalDateTime localDateTime, String zoneId){
    	Objects.requireNonNull(localDateTime, "localDateTime");
		return StringUtil.isNotEmpty(zoneId) ? localDateTime.format(YYYY_MM_DD_HH_MM_SS_FMT.withZone(ZoneId.of(zoneId)))
				: localDateTime.format(YYYY_MM_DD_HH_MM_SS_FMT.withZone(null));
    }
    
    /**
     * 格式化，返回完整日期时间，如：yyyy-MM-dd HH:mm:ss
     * @param zonedDateTime ZonedDateTime
     * @return String
     */
    public static String formatToDateTimeStr(ZonedDateTime zonedDateTime){
    	Objects.requireNonNull(zonedDateTime, "zonedDateTime");
    	return zonedDateTime.format(YYYY_MM_DD_HH_MM_SS_FMT);
    }
    
    /**
     * 格式化，返回完整日期时间，如：yyyy-MM-dd HH:mm:ss 指定时区
     * @param zonedDateTime ZonedDateTime
     * @param zoneId 时区
     * @return String
     */
    public static String formatToDateTimeStr(ZonedDateTime zonedDateTime, String zoneId){
    	Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		return StringUtil.isNotEmpty(zoneId) ? zonedDateTime.format(YYYY_MM_DD_HH_MM_SS_FMT.withZone(ZoneId.of(zoneId)))
				: zonedDateTime.format(YYYY_MM_DD_HH_MM_SS_FMT.withZone(null));
    }
    
    /**
     * 格式化，返回时间部分，如：HH:mm:ss
     * @param date Date
     * @return String
     */
    public static String formatToTimeStr(Date date){
    	return DateTimeConverterUtil.toLocalDateTime(date).format(HH_MM_SS_FMT);
    }
    
    /**
     * 格式化，返回时间部分，如：HH:mm:ss
     * @param localDateTime LocalDateTime
     * @return String
     */
    public static String formatToTimeStr(LocalDateTime localDateTime){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	return localDateTime.format(HH_MM_SS_FMT);
    }
    
    /**
     * 根据 formatter格式化 date
     * @param date Date
     * @param formatter DateTimeFormatter
     * @return String
     */
    public static String format(Date date, DateTimeFormatter formatter){
    	Objects.requireNonNull(formatter, "formatter");
    	return DateTimeConverterUtil.toZonedDateTime(date).format(formatter);
    }
    
    /**
     * 根据 dateFormatPattern格式化 date
	 * @param date Date
	 * @param dateFormatPattern 自定义格式化模板
     * @return String
     */
    public static String format(Date date, String dateFormatPattern){
    	Objects.requireNonNull(dateFormatPattern, "dateFormatPattern");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern).withZone(ZONE);
    	return format(date, formatter);
    }    
    
    /**
     * 根据 formatter格式化 date 支持自定义时区
	 * @param date Date
	 * @param formatter DateTimeFormatter
     * @param zoneId 时区
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
     * @param localDateTime LocalDateTime
     * @param formatter DateTimeFormatter
     * @return String
     */
    public static String format(LocalDateTime localDateTime, DateTimeFormatter formatter){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	Objects.requireNonNull(formatter, "formatter");
    	return DateTimeConverterUtil.toZonedDateTime(localDateTime).format(formatter);
    }
    
    /**
     * 根据 dateFormatPattern格式化 localDateTime
     * @param localDateTime LocalDateTime
     * @param dateFormatPattern 自定义格式模板
     * @return String
     */
    public static String format(LocalDateTime localDateTime, String dateFormatPattern){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	Objects.requireNonNull(dateFormatPattern, "dateFormatPattern");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern).withZone(ZONE);
    	return format(localDateTime, formatter);
    }    
    
    /**
     * 根据 formatter格式化 localDateTime 指定时区
     * @param localDateTime LocalDateTime
     * @param formatter DateTimeFormatter
     * @param zoneId 时区
     * @return String
     */
    public static String format(LocalDateTime localDateTime, DateTimeFormatter formatter, String zoneId){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	Objects.requireNonNull(formatter, "formatter");
    	
		return StringUtil.isNotEmpty(zoneId) ? DateTimeConverterUtil.toZonedDateTime(localDateTime).format(formatter.withZone(ZoneId.of(zoneId)))
				: DateTimeConverterUtil.toZonedDateTime(localDateTime).format(formatter.withZone(ZoneId.of(null)));
    }
    
    /**
     * 根据 formatter格式化 localDate
     * @param localDate LocalDate
     * @param formatter DateTimeFormatter
     * @return String
     */
    public static String format(LocalDate localDate, DateTimeFormatter formatter){
    	Objects.requireNonNull(formatter, "formatter");
    	return DateTimeConverterUtil.toZonedDateTime(localDate).format(formatter);
    }
    
    /**
     * 根据 formatter格式化 localTime
     * @param localTime LocalTime
     * @param formatter DateTimeFormatter
     * @return String
     */
    public static String format(LocalTime localTime, DateTimeFormatter formatter){
    	Objects.requireNonNull(localTime, "localTime");
    	Objects.requireNonNull(formatter, "formatter");
    	return DateTimeConverterUtil.toZonedDateTime(localTime).format(formatter);
    }
    
    /**
     * 根据 formatter格式化 instant
     * @param instant Instant
     * @param formatter DateTimeFormatter
     * @return String
     */
    public static String format(Instant instant, DateTimeFormatter formatter){
    	Objects.requireNonNull(instant, "instant");
    	Objects.requireNonNull(formatter, "formatter");
    	return formatter.format(instant);
    }
    
    /**
     * 根据 formatter格式化 zonedDateTime
     * @param zonedDateTime ZonedDateTime
     * @param formatter DateTimeFormatter
     * @return String
     */
    public static String format(ZonedDateTime zonedDateTime, DateTimeFormatter formatter){
    	Objects.requireNonNull(zonedDateTime, "zonedDateTime");
    	Objects.requireNonNull(formatter, "formatter");
    	return zonedDateTime.format(formatter);
    }
    
    /**
     * 根据 formatter格式化 zonedDateTime 指定时区
     * @param zonedDateTime ZonedDateTime
     * @param formatter DateTimeFormatter
     * @param zoneId 时区
     * @return String
     */
    public static String format(ZonedDateTime zonedDateTime, DateTimeFormatter formatter, String zoneId){
    	Objects.requireNonNull(zonedDateTime, "zonedDateTime");
    	Objects.requireNonNull(formatter, "formatter");
		return StringUtil.isNotEmpty(zoneId) ? zonedDateTime.format(formatter.withZone(ZoneId.of(zoneId)))
				: zonedDateTime.format(formatter.withZone(null));
    }
    
    /**
     * 格式化date成Timestamp 默认格式 yyyy-mm-dd hh:mm:ss.fffffffff 其中 fffffffff 纳秒，省略后面的0 比如：
	 * <pre>
	 * 2020-05-23 17:06:30.0
	 * 2020-05-23 17:06:30.272
	 * 2020-05-23 17:06:30.27215
	 * 2020-05-23 17:06:30.27215062
	 *</pre>
     * @param date Date
     * @return String
     */
    public static String formatTimestampStyle(Date date){
    	Objects.requireNonNull(date, "date");
    	return DateTimeConverterUtil.toTimestamp(date).toString();
    }
    
    /**
     * 格式化localDateTime成Timestamp 默认格式 yyyy-mm-dd hh:mm:ss.fffffffff 其中 fffffffff 纳秒，省略后面的0 比如：
	 * <pre>
	 * 2020-05-23 17:06:30.0
	 * 2020-05-23 17:06:30.272
	 * 2020-05-23 17:06:30.27215
	 * 2020-05-23 17:06:30.27215062
	 * </pre>
     * @param localDateTime LocalDateTime
     * @return String
     */
    public static String formatTimestampStyle(LocalDateTime localDateTime){
    	Objects.requireNonNull(localDateTime, "localDateTime");
    	return DateTimeConverterUtil.toTimestamp(localDateTime).toString();
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
    
    // ==================================parse==================================

    /**
     * 解析日期yyyy-MM-dd，返回Date
     * @param text yyyy-MM-dd
     * @return Date
     */
    public static Date parseDateStrToDate(String text){
    	return DateTimeConverterUtil.toDate(LocalDate.parse(text, YYYY_MM_DD_FMT));
    }
    
    /**
     * 中文日期解析  2021年09月11日 或 二〇二一年九月十一日，返回Date
     * @param text 2021年09月11日 或 二〇二一年九月十一日
     * @return Date
     */
    public static Date parseChineseDateStrToDate(String text){
    	return DateTimeConverterUtil.toDate(parseChineseDateStrToLocalDateTime(text));
    }
    
    /**
     * 中文日期解析  2021年09月11日 或 二〇二一年九月十一日，返回LocalDateTime
     * @param text 2021年09月11日 或 二〇二一年九月十一日
     * @return LocalDateTime
     */
    public static LocalDateTime parseChineseDateStrToLocalDateTime(String text){
    	if(StringUtil.isEmpty(text)){
    		throw new NullPointerException("text");
    	}
    	text = text.trim();
    	Pattern pattern = RegexEnum.NormYearFour.getPattern();
    	Matcher match = pattern.matcher(text);
        if (match.find()){
        	return parseToLocalDateTime(text, YYYY_MM_DD_CN_FMT);
        } else {
			StringBuilder buf = new StringBuilder();
	    	//年
			String[] yearStrArr = text.split("年");
	    	String yearStr = yearStrArr[0];
	    	int yearStrLength = yearStr.length();
	    	for(int i=0; i<yearStrLength; i++){
	    		buf.append(ChineseDateDigitEnum.getIndexUseCache(String.valueOf(yearStr.charAt(i))));
	    	}
	    	int year = Integer.parseInt(buf.toString());
	    	//月
	    	String[] monthStrArr = yearStrArr[1].split("月");
	    	int month = ChineseDateDigitEnum.getIndexUseCache(monthStrArr[0]);
	    	//日
	    	String[] dayStrArr = monthStrArr[1].split("日");
	    	int day = ChineseDateDigitEnum.getIndexUseCache(dayStrArr[0]);
	    	return LocalDateTime.of(year, month, day, 0, 0);
    	}
    }
    
    /**
     * 解析日期yyyy-MM-dd，返回LocalDateTime
     * @param text yyyy-MM-dd
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateStrToLocalDateTime(String text){
    	return DateTimeConverterUtil.toLocalDateTime(LocalDate.parse(text, YYYY_MM_DD_FMT));
    }
    
    /**
     * 解析日期yyyy-MM-dd，返回Instant
     * @param text yyyy-MM-dd
     * @return Instant
     */
    public static Instant parseDateStrToInstant(String text){
    	return DateTimeConverterUtil.toInstant(LocalDate.parse(text, YYYY_MM_DD_FMT));
    }
    
    /**
     * 解析日期yyyy-MM-dd HH:mm:ss，返回Date
     * @param text yyyy-MM-dd HH:mm:ss
     * @return Date
     */
    public static Date parseDateTimeStrToDate(String text){
    	return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(YYYY_MM_DD_HH_MM_SS_FMT.parse(text)));
    }
    
    /**
     * 解析日期yyyy-MM-dd HH:mm:ss，返回LocalDateTime
     * @param text yyyy-MM-dd HH:mm:ss
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTimeStrToLocalDateTime(String text){
    	return DateTimeConverterUtil.toLocalDateTime(YYYY_MM_DD_HH_MM_SS_FMT.parse(text));
    }
    
    /**
     * 解析日期yyyy-MM-dd HH:mm:ss，返回Instant
     * @param text yyyy-MM-dd HH:mm:ss
     * @return Instant
     */
    public static Instant parseDateTimeStrToInstant(String text){
    	return DateTimeConverterUtil.toInstant(YYYY_MM_DD_HH_MM_SS_FMT.parse(text));
    }
    
    /**
     * 根据 formatter解析为 Date
     * @param text 待解析字符串
     * @param formatter DateTimeFormatter
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
     * 根据模板解析为 Date
	 * @param text 待解析字符串
	 * @param dateFormatPattern 自定义格式化模板
     * @return Date
     */
	public static Date parseToDate(String text, String dateFormatPattern) {
		Objects.requireNonNull(dateFormatPattern, "dateFormatPattern");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern).withZone(ZONE);
		return parseToDate(text, formatter);
	}
	
    /**
     * 根据自定义模板数组解析为 Date
	 * @param text 待解析字符串
	 * @param dateFormatPatterns 自定义格式化模板数组
     * @return Date
     */
	public static Date parseToDate(String text, String[] dateFormatPatterns) {
		Date result = null;
		if(ArrayUtil.isEmpty(dateFormatPatterns)){
			return result;
		}
		for(String dateFormatPattern : dateFormatPatterns){
			if(StringUtil.isNotEmpty(dateFormatPattern)){
				try {
					result = parseToDate(text, dateFormatPattern);
					if(result != null){
						break;
					}
				} catch (Exception e) {
				}
			}
		}
		return result;
	}
    
    /**
     * 根据 formatter解析为 LocalDateTime
     * @param text 待解析字符串
     * @param formatter DateTimeFormatter
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
     * 根据模板解析为 LocalDateTime
     * @param text 待解析字符串
     * @param dateFormatPattern 自定义格式化模板
     * @return LocalDateTime
     */
    public static LocalDateTime parseToLocalDateTime(String text, String dateFormatPattern){
    	Objects.requireNonNull(dateFormatPattern, "dateFormatPattern");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern).withZone(ZONE);
    	return parseToLocalDateTime(text, formatter);
    }
    
    /**
     * 根据自定义模板数组解析为 LocalDateTime
	 * @param text 待解析字符串
	 * @param dateFormatPatterns 自定义格式化模板数组
     * @return LocalDateTime
     */
	public static LocalDateTime parseToLocalDateTime(String text, String[] dateFormatPatterns) {
		LocalDateTime result = null;
		if(ArrayUtil.isEmpty(dateFormatPatterns)){
			return result;
		}
		for(String dateFormatPattern : dateFormatPatterns){
			if(StringUtil.isNotEmpty(dateFormatPattern)){
				try {
					result = parseToLocalDateTime(text, dateFormatPattern);
					if(result != null){
						break;
					}
				} catch (Exception e) {
				}
			}
		}
		return result;
	}
    
    /**
     * 根据 formatter解析为 Instant
     * @param text 待解析字符串
     * @param formatter DateTimeFormatter
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
	 * @param text 待解析字符串
	 * @param formatter DateTimeFormatter
	 * @return ZonedDateTime
	 */
    public static ZonedDateTime parseToZonedDateTime(String text, DateTimeFormatter formatter){
    	return ZonedDateTime.parse(text, formatter);
    }
    
    
	/**
	 * 自动解析为 Date
	 * <pre>
	 * =====================yyyy-MM-dd 相关=====================
	 * yyyy-MM-dd	2020-05-23 或 2020-5-23
	 * yyyyMMdd		20200523
	 * yyyy/MM/dd	2020/05/23 或 2020/5/23
	 * yyyy年MM月dd日	2020年05月23日 或 2020年5月23日
	 * yyyy.MM.dd	2020.05.23 或 2020.5.23
	 * 
	 * 
	 * =====================yyyy-MM-dd HH:mm:ss 相关=====================
	 * yyyy-MM-dd HH:mm:ss		2020-05-23 17:06:30
	 * yyyyMMddHHmmss			20200523170630
	 * yyyy年MM月dd日 HH:mm:ss		2020年05月23日 17:06:30
	 * yyyy年MM月dd日 HH时mm分ss秒	2020年05月23日 17时06分30秒
	 * yyyy-MM-dd HH:mm		2020-05-23 17:06
	 * yyyy/MM/dd HH:mm		2020/05/23 17:06
	 * 
	 * 
	 * =====================yyyy-MM-dd HH:mm:ss.SSS 相关=====================
	 * yyyy-MM-dd HH:mm:ss.SSS		2020-05-23 17:06:30.272
	 * yyyy-MM-dd HH:mm:ss,SSS		2020-05-23 17:06:30,272
	 * yyyyMMddHHmmssSSS			20200523170630272
	 * 
	 * =====================Iso相关格式=====================
	 * yyyy-MM-dd'T'HH:mm:ssZ			2020-05-23T17:06:30+0800
	 * yyyy-MM-dd'T'HH:mm:ss'Z'		2020-05-23T17:06:30Z
	 * yyyy-MM-dd'T'HH:mm:ssxxx		2020-05-23T17:06:30+08:00
	 * yyyy-MM-dd'T'HH:mm:ss.SSSZ		2020-05-23T17:06:30.272+0800
	 * yyyy-MM-dd'T'HH:mm:ss.SSSxxx		2020-05-23T17:06:30.272+08:00
	 * yyyy-MM-dd'T'HH:mm:ss.SSSXXX		2020-05-23T09:06:30.272Z
	 * 
	 * =====================其他格式=====================
	 * EEE MMM dd HH:mm:ss zzz yyyy	 	Sat May 23 17:06:30 CST 2020
	 * </pre>
	 * @param text 待解析字符串
	 * @return Date
	 */
    public static Date smartParseToDate(String text){
		return DateTimeConverterUtil.toDate(smartParseToLocalDateTime(text));
    }
    
	/**
	 * 自动解析为 LocalDateTime
	 * <pre>
	 * =====================yyyy-MM-dd 相关=====================
	 * yyyy-MM-dd	2020-05-23 或 2020-5-23
	 * yyyyMMdd		20200523
	 * yyyy/MM/dd	2020/05/23 或 2020/5/23
	 * yyyy年MM月dd日	2020年05月23日 或 2020年5月23日
	 * yyyy.MM.dd	2020.05.23 或 2020.5.23
	 * 
	 * 
	 * =====================yyyy-MM-dd HH:mm:ss 相关=====================
	 * yyyy-MM-dd HH:mm:ss		2020-05-23 17:06:30
	 * yyyyMMddHHmmss			20200523170630
	 * yyyy年MM月dd日 HH:mm:ss		2020年05月23日 17:06:30
	 * yyyy年MM月dd日 HH时mm分ss秒	2020年05月23日 17时06分30秒
	 * yyyy-MM-dd HH:mm		2020-05-23 17:06
	 * yyyy/MM/dd HH:mm		2020/05/23 17:06
	 * 
	 * 
	 * =====================yyyy-MM-dd HH:mm:ss.SSS 相关=====================
	 * yyyy-MM-dd HH:mm:ss.SSS		2020-05-23 17:06:30.272
	 * yyyy-MM-dd HH:mm:ss,SSS		2020-05-23 17:06:30,272
	 * yyyyMMddHHmmssSSS			20200523170630272
	 * 
	 * =====================Iso相关格式=====================
	 * yyyy-MM-dd'T'HH:mm:ssZ			2020-05-23T17:06:30+0800
	 * yyyy-MM-dd'T'HH:mm:ss'Z'		2020-05-23T17:06:30Z
	 * yyyy-MM-dd'T'HH:mm:ssxxx		2020-05-23T17:06:30+08:00
	 * yyyy-MM-dd'T'HH:mm:ss.SSSZ		2020-05-23T17:06:30.272+0800
	 * yyyy-MM-dd'T'HH:mm:ss.SSSxxx		2020-05-23T17:06:30.272+08:00
	 * yyyy-MM-dd'T'HH:mm:ss.SSSXXX		2020-05-23T09:06:30.272Z
	 * 
	 * =====================其他格式=====================
	 *  EEE MMM dd HH:mm:ss zzz yyyy	 	Sat May 23 17:06:30 CST 2020
	 * </pre>
	 * @param text 待解析字符串
	 * @return LocalDateTime
	 */
	public static LocalDateTime smartParseToLocalDateTime(String text) {
		// 1.字符串预检查处理
		if (StringUtil.isEmpty(text)) {
			throw new DateTimeException("text is null");
		}
		
		text = text.trim();
		int len = text.length();
		if(len < 8){
			throw new DateTimeException("text is not supported! " + text);
		}
		
		//预处理待解析字符串
		text = preprocessText(text);

		// 2.解析字符串
		// 2.1 Date 默认格式 EEE MMM dd HH:mm:ss zzz yyyy 如：Thu May 21 22:58:05 CST
		if (StringUtil.isStartWithWord(text)) {
			return parseToLocalDateTime(text, EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY_FMT);
		} else if (StringUtil.isNumeric(text)) {
			// 2.2 纯数字格式解析
			if (len == 14) {
				return parseToLocalDateTime(text, YYYYMMDDHHMMSS_FMT);
			} else if (len == 17) {
				return parseToLocalDateTime(text, YYYYMMDDHHMMSSSSS_FMT);
			} else if (len == 8) {
				return parseToLocalDateTime(text, YYYYMMDD_FMT);
			} else if (len == 12) {
				return parseToLocalDateTime(text, YYYYMMDDHHMM_FMT);
			}
		} else {
			// : 出现次数
			int colonCount = StringUtil.countWord(text, ":");
			// 2.3 yyyy-MM-dd 格式解析
			if (colonCount == 0) {
				return parseToLocalDateTime(text, YYYY_M_D_FMT);
			} else if (text.contains("T")) {
				// 2.4 ISO格式解析
				return parseIsoToLocalDateTime(text);
			} else if (colonCount > 0 && text.contains(".")) {
				if(text.split("\\.")[1].length()==3){
					// 2.5 yyyy-MM-dd HH:mm:ss.SSS
					return parseToLocalDateTime(text, YYYY_M_D_H_M_S_SSS_FMT);
				}else{
					return parseTimestampStyleToLocalDateTime(text);
				}
			} else if (colonCount > 0 && text.contains(",")) {
				// 2.6 yyyy-MM-dd HH:mm:ss,SSS
				return parseToLocalDateTime(text, YYYY_M_D_H_M_S_SSS_COMMA_FMT);
			} else if (colonCount > 0) {
				if (colonCount == 2) {
					// 2.7 yyyy-MM-dd HH:mm:ss
					return parseToLocalDateTime(text, YYYY_M_D_H_M_S_FMT);
				} if (colonCount == 1) {
					// 2.8 yyyy-MM-dd HH:mm
					return parseToLocalDateTime(text, YYYY_M_D_H_M_FMT);
				}
			}
		}
		throw new DateTimeException("text is not supported! " + text);
	}

	/**
	 * 解析Iso格式 包含 T 格式
	 * <pre>
	 * =====================Iso相关格式=====================
	 * yyyy-MM-dd'T'HH:mm:ssZ			2020-05-23T17:06:30+0800
	 * yyyy-MM-dd'T'HH:mm:ss'Z'		2020-05-23T17:06:30Z
	 * yyyy-MM-dd'T'HH:mm:ssxxx		2020-05-23T17:06:30+08:00
	 * yyyy-MM-dd'T'HH:mm:ss.SSSZ		2020-05-23T17:06:30.272+0800
	 * yyyy-MM-dd'T'HH:mm:ss.SSSxxx		2020-05-23T17:06:30.272+08:00
	 * yyyy-MM-dd'T'HH:mm:ss.SSSXXX		2020-05-23T09:06:30.272Z
	 * </pre>
	 * @param text 待解析字符串
	 * @return LocalDateTime
	 */
	public static LocalDateTime parseIsoToLocalDateTime(String text) {
		if (StringUtil.isEmpty(text)) {
			throw new DateTimeException("text is null");
		}

		if(!text.contains("T")){
			throw new DateTimeException("text is not supported! " + text);
		}
		
		text = text.trim();
		int len = text.length();
		if (!text.contains("[")) {
			if(text.contains("Z")){
				return parseToLocalDateTime(text, ISO_DATE_TIME_FMT);
			}else if (len == 24) {
				// yyyy-MM-dd'T'HH:mm:ssZ
				return parseToLocalDateTime(text, YYYY_MM_DD_T_HH_MM_SS_Z_FMT);
			} else if (len == 28) {
				// yyyy-MM-dd'T'HH:mm:ss.SSSZ
				return parseToLocalDateTime(text, YYYY_MM_DD_T_HH_MM_SS_SSS_Z_FMT);
			} else {
				return parseToLocalDateTime(text, ISO_DATE_TIME_FMT);
			}
		} else {
			// 包含时区Id时 先转换为ZonedDateTime 再转换为LocalDateTime
			if (len == 24) {
				// yyyy-MM-dd'T'HH:mm:ssZ
				return DateTimeConverterUtil
						.toLocalDateTime(parseToZonedDateTime(text, YYYY_MM_DD_T_HH_MM_SS_Z_FMT));
			} else if (len == 28) {
				// yyyy-MM-dd'T'HH:mm:ss.SSSZ
				return DateTimeConverterUtil
						.toLocalDateTime(parseToZonedDateTime(text, YYYY_MM_DD_T_HH_MM_SS_SSS_Z_FMT));
			} else {
				return DateTimeConverterUtil.toLocalDateTime(parseToZonedDateTime(text, ISO_DATE_TIME_FMT));
			}
		}
	}
	
	/**
	 * 解析Iso格式 包含 T 格式
	 * <pre>
	 * =====================Iso相关格式=====================
	 * yyyy-MM-dd'T'HH:mm:ssZ			2020-05-23T17:06:30+0800
	 * yyyy-MM-dd'T'HH:mm:ss'Z'		2020-05-23T17:06:30Z
	 * yyyy-MM-dd'T'HH:mm:ssxxx		2020-05-23T17:06:30+08:00
	 * yyyy-MM-dd'T'HH:mm:ss.SSSZ		2020-05-23T17:06:30.272+0800
	 * yyyy-MM-dd'T'HH:mm:ss.SSSxxx		2020-05-23T17:06:30.272+08:00
	 * yyyy-MM-dd'T'HH:mm:ss.SSSXXX		2020-05-23T09:06:30.272Z
	 * </pre>
	 * @param text 待解析字符串
	 * @return Date
	 */
	public static Date parseIsoToDate(String text) {
		return DateTimeConverterUtil.toDate(parseIsoToLocalDateTime(text));
	}
    
	/**
	 * 解析时间戳为Date
	 * @param text 比如 1590224790000
	 * @return LocalDateTime
	 */
	public static Date parseEpochMilliToDate(String text) {
		if (StringUtil.isEmpty(text)) {
			throw new DateTimeException("text is null");
		}
		
		text = text.trim();
		return DateTimeConverterUtil.toDate(Long.parseLong(text));
	}
	
	/**
	 * 解析时间戳为LocalDateTime
	 * @param text 比如 1590224790000
	 * @return LocalDateTime
	 */
	public static LocalDateTime parseEpochMilliToLocalDateTime(String text) {
		if (StringUtil.isEmpty(text)) {
			throw new DateTimeException("text is null");
		}
		
		text = text.trim();
		return DateTimeConverterUtil.toLocalDateTime(Long.parseLong(text));
	}
	
	/**
	 * 解析Date默认格式为Date
	 * @param text EEE MMM dd HH:mm:ss zzz yyyy  比如：  Sat May 23 17:06:30 CST 2020
	 * @return LocalDateTime
	 */
	public static Date parseDateDefaultStrToDate(String text) {
		return parseToDate(text, EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY_FMT);
	}
	
	/**
	 * 解析Date默认格式为LocalDateTime
	 * @param text EEE MMM dd HH:mm:ss zzz yyyy  比如：  Sat May 23 17:06:30 CST 2020
	 * @return LocalDateTime
	 */
	public static LocalDateTime parseDateDefaultStrToLocalDateTime(String text) {
		return parseToLocalDateTime(text, EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY_FMT);
	}
	
	/**
     * 解析Timestamp格式字符串为Date  默认格式 yyyy-mm-dd hh:mm:ss.fffffffff 其中 fffffffff 纳秒，省略后面的0 比如：
	 * <pre>
	 * 2020-05-23 17:06:30.0
	 * 2020-05-23 17:06:30.272
	 * 2020-05-23 17:06:30.27215
	 * 2020-05-23 17:06:30.27215062
	 *</pre>
	 * @param text 待解析字符串
	 * @return Date
	 */
	public static Date parseTimestampStyleToDate(String text){
		return DateTimeConverterUtil.toDate(parseTimestampStyleToLocalDateTime(text));
	}
	
	/**
     * 解析Timestamp格式字符串为LocalDateTime  默认格式 yyyy-mm-dd hh:mm:ss.fffffffff 其中 fffffffff 纳秒，省略后面的0 比如：
	 * <pre>
	 * 2020-05-23 17:06:30.0
	 * 2020-05-23 17:06:30.272
	 * 2020-05-23 17:06:30.27215
	 * 2020-05-23 17:06:30.27215062
	 *</pre>
	 * @param text 待解析字符串
	 * @return LocalDateTime
	 */
	public static LocalDateTime parseTimestampStyleToLocalDateTime(String text){
		//预处理
		Objects.requireNonNull(text, "text");
		text=text.trim();
		if(!text.contains(".")){
			throw new DateTimeException("text is not supported! " + text);
		}
		
		//.分割成2部分，分别分析
		String[] textArr = text.split("\\.");
		String main = textArr[0];
		String nanoOfSecond = textArr[1];
		int mainLen = main.length();
		int len = nanoOfSecond.length();
		if(mainLen != DateFormatPattern.YYYY_MM_DD_HH_MM_SS.length()){
			throw new DateTimeException("text is not supported! " + text);
		}
		if(len>9){
			throw new DateTimeException("text is not supported! " + text);
		}
		
		//纳秒部分补0
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<9-len;i++){
			sb.append("0");
		}
		nanoOfSecond = nanoOfSecond+sb.toString();
		text = main+"."+nanoOfSecond;
		
		//使用yyyy-MM-dd HH:mm:ss.SSSSSSSSS 标准格式解析
		return parseToLocalDateTime(text, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSSSSSSSS_FMT);
	}
	
	/**
	 * 验证日期格式是否正确
	 * @param text yyyy-MM-dd 格式
	 * @return 正确返回true，不正确返回false
	 */
	public static boolean isValidDate(String text){
		try {
			parseDateStrToDate(text);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 验证日期时间格式是否正确
	 * @param text yyyy-MM-dd HH:mm:ss 格式
	 * @return 正确返回true，不正确返回false
	 */
	public static boolean isValidDateTime(String text){
		try {
			parseDateTimeStrToDate(text);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 解析自然语言时间，今天，明天，下周，下月，明年，昨天，上周，上月，去年等。
	 * @param text 自然语言时间，待解析字符串
	 * @param  naturalLanguageMap 自定义自然语言时间map，其中key自定义，value需为 com.xkzhangsan.time.enums.CommonTimeEnum中的code；
	 *                            可以为空，默认使用com.xkzhangsan.time.enums.CommonTimeEnum解析。
	 * @return Date
	 */
	public static Date parseNaturalLanguageToDate(String text, Map<String, String> naturalLanguageMap){
		if(StringUtil.isEmpty(text)){
			return null;
		}
		text = text.trim();

		boolean isCommonTimeMap = false;
		if(CollectionUtil.isEmpty(naturalLanguageMap)){
			naturalLanguageMap = CommonTimeEnum.convertToMap();
			isCommonTimeMap = true;
		}
		if(! naturalLanguageMap.containsKey(text) || StringUtil.isEmpty(naturalLanguageMap.get(text))){
			return null;
		}

		String targetMethod = null;
		if(isCommonTimeMap){
			targetMethod = naturalLanguageMap.get(text);
		}else{
			String code = naturalLanguageMap.get(text);
			Map<String, String> commonTimeMap = CommonTimeEnum.convertToMap();
			if(commonTimeMap.containsKey(code)){
				targetMethod = commonTimeMap.get(code);
			}
		}
		if(targetMethod == null){
			return null;
		}

		//执行结果
		CommonTimeEnum targetCommonTime = CommonTimeEnum.getCommonTimeEnumByCode(targetMethod);
		if(targetCommonTime == null){
			return null;
		}
		
		switch (targetCommonTime){
			case TODAY :
				return DateTimeCalculatorUtil.today();
			case TOMORROW:
				return DateTimeCalculatorUtil.tomorrow();
			case NEXTWEEK:
				return DateTimeCalculatorUtil.nextWeek();
			case NEXTMONTH:
				return DateTimeCalculatorUtil.nextMonth();
			case NEXTYEAR:
				return DateTimeCalculatorUtil.nextYear();
			case YESTERDAY:
				return DateTimeCalculatorUtil.yesterday();
			case LASTWEEK:
				return DateTimeCalculatorUtil.lastWeek();
			case LASTMONTH:
				return DateTimeCalculatorUtil.lastMonth();
			case LASTYEAR:
				return DateTimeCalculatorUtil.lastYear();
			default:
				return null;
		}
	}

	/**
	 * 解析自然语言时间，今天，明天，下周，下月，明年，昨天，上周，上月，去年等。
	 * @param text 自然语言时间，待解析字符串
	 * @return Date
	 */
	public static Date parseNaturalLanguageToDate(String text){
		return parseNaturalLanguageToDate(text, null);
	}
	
	
	// ==================================private method==================================

    /**
     * 预处理待解析字符串
	 * @param text 待解析字符串
     * @return
     */
    private static String preprocessText(String text) {
    	text = convertSlashToNormal(text);
    	text = convertPointToNormal(text);
    	text = convertCnToNormal(text);
    	return text;
    }
    
    /**
     * "/" 转换为 -
     * @param str 字符串
     * @return
     */
    private static String convertSlashToNormal(String str){
    	if(!str.contains("[")){
    		return str.replace("/", "-");
    	}
    	return str;
    }
    
    /**
     * . 转换为 -
     * @param str 字符串
     * @return
     */
    private static String convertPointToNormal(String str){
    	if(StringUtil.countWord(str, ".") == 2){
    		return str.replace(".", "-");
    	}
    	return str;
    } 
    
    /**
     * 中文转换为 - 或 :
     * @param str 字符串
     * @return
     */
    private static String convertCnToNormal(String str){
    	if(StringUtil.hasChinese(str)){
			return str.replace("年", "-").replace("月", "-").replace("日", "").replace("时", ":").replace("分", ":")
					.replace("秒", "");
    	}
    	return str;
    }
    
}
