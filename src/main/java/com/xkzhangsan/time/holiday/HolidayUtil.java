package com.xkzhangsan.time.holiday;

import java.time.temporal.Temporal;
import java.util.Date;
import java.util.Map;

import com.xkzhangsan.time.LunarDate;

/**
 * 节日处理工具<br>
 * 包含<br>
 * 1.公历节假日计算， getLocalHoliday* 比如getLocalHoliday(Date date) 计算date的公历节日，{@code getLocalHoliday(Date date, Map<String, String> localHolidayMap)} 可以传入自定义公历节日数据<br>
 * 2.农历节假日计算， getChineseHoliday* 比如getChineseHoliday(Date date) 计算date的农历节日，{@code getChineseHoliday(Date date, Map<String, String> chineseHolidayMap)} 可以传入自定义农历节日数据<br>
 * 3.二十四节气计算， getSolarTerm* 比如getSolarTerm(Date date) 计算date的二十四节气<br>
 * 
 * 农历相关，仅支持公历1900-2100年的计算，使用{@link LunarDate}<br>
* @author xkzhangsan
 */
public class HolidayUtil {
	
	private HolidayUtil(){
	}
	
	/**
	 * 根据日期获取公历节日
	 * @param date 日期
	 * @return String
	 */
	public static String getLocalHoliday(Date date){
		return Holiday.getLocalHoliday(date);
	}
	
	/**
	 * 根据日期获取公历节日
	 * @param date 日期
	 * @param localHolidayMap 自定义节日数据，特殊节日如，"母亲节", "5-W-2-7" 5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天
	 * @return String
	 */
	public static String getLocalHoliday(Date date, Map<String, String> localHolidayMap){
		return Holiday.getLocalHoliday(date, localHolidayMap);
	}
	
	/**
	 * 根据日期获取公历节日
	 * @param temporal 支持 LocalDate、LocalDateTime、Instant和ZonedDateTime
	 * @return String
	 */
	public static String getLocalHoliday(Temporal temporal){
		return Holiday.getLocalHoliday(temporal);
	}
	
	/**
	 * 根据日期获取公历节日
	 * @param temporal 支持 LocalDate、LocalDateTime、Instant和ZonedDateTime
	 * @param localHolidayMap 自定义节日数据，特殊节日如，"母亲节", "5-W-2-7" 5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天
	 * @return String
	 */
	public static String getLocalHoliday(Temporal temporal, Map<String, String> localHolidayMap){
		return Holiday.getLocalHoliday(temporal, localHolidayMap);
	}
	
	/**
	 * 根据日期获取农历几日
	 * @param date 日期
	 * @return String
	 */
	public static String getChineseHoliday(Date date){
		return Holiday.getChineseHoliday(date);
	}
	
	/**
	 * 根据日期获取农历几日
	 * @param date 日期
	 * @param chineseHolidayMap 自定义节日数据，特殊节日如除夕 用CHUXI表示
	 * @return String
	 */
	public static String getChineseHoliday(Date date, Map<String, String> chineseHolidayMap){
		return Holiday.getChineseHoliday(date, chineseHolidayMap);
	}
	
	/**
	 * 根据日期获取农历几日
	 * @param temporal 支持 LocalDate、LocalDateTime、Instant和ZonedDateTime 支持 LocalDate、LocalDateTime、Instant和ZonedDateTime
	 * @return String
	 */
	public static String getChineseHoliday(Temporal temporal){
		return Holiday.getChineseHoliday(temporal);
	}
	
	/**
	 * 根据日期获取农历几日
	 * @param temporal 支持 LocalDate、LocalDateTime、Instant和ZonedDateTime
	 * @param chineseHolidayMap 自定义节日数据，特殊节日如除夕 用CHUXI表示
	 * @return String
	 */
	public static String getChineseHoliday(Temporal temporal, Map<String, String> chineseHolidayMap){
		return Holiday.getChineseHoliday(temporal, chineseHolidayMap);
	}
	
	/**
	 * 根据日期获取二十四节气
	 * @param date 日期
	 * @return String
	 */
	public static String getSolarTerm(Date date){
		return Holiday.getSolarTerm(date);
	}
	
	/**
	 * 根据日期获取二十四节气
	 * @param temporal 支持 LocalDate、LocalDateTime、Instant和ZonedDateTime
	 * @return String
	 */
	public static String getSolarTerm(Temporal temporal){
		return Holiday.getSolarTerm(temporal);
	}
}
