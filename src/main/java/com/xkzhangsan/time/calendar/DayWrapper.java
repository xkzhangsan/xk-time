package com.xkzhangsan.time.calendar;

import com.xkzhangsan.time.LunarDate;
import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;
import com.xkzhangsan.time.holiday.Holiday;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * 日
 * 
 * @author xkzhangsan
 */
public class DayWrapper implements Serializable {

	private static final long serialVersionUID = 5710793952115910594L;

	/**
	 * date
	 */
	private Date date;

	/**
	 * java8 localDateTime 丰富方法可以使用
	 */
	private LocalDateTime localDateTime;

	/**
	 * 日期 yyyy-MM-dd
	 */
	private String dateStr;

	/**
	 * 天，当月第几天
	 */
	private int day;
	
	/**
	 * 星期，数字，1-7
	 */
	private int week;

	/**
	 * 星期，中文简写，比如星期一为一
	 */
	private String weekCnShort;
	
	/**
	 * 星期，中文全称，比如星期一
	 */
	private String weekCnLong;
	
	/**
	 * 星期，英文简写，比如星期一为Mon
	 */
	private String weekEnShort;
	
	/**
	 * 星期，英文简写大写，比如星期一为MON
	 */
	private String weekEnShortUpper;
	
	
	/**
	 * 星期，英文全称，比如星期一为Monday
	 */
	private String weekEnLong;

	/**
	 * 公历节日
	 */
	private String localHoliday;

	/**
	 * 农历
	 */
	private LunarDate lunarDate;

	/**
	 * 农历节日
	 */
	private String chineseHoliday;

	/**
	 * 农历日期
	 */
	private String lunarDateStr;
	
	/**
	 * 农历天，比如初一
	 */
	private String lunarDay;

	/**
	 * 二十四节气
	 */
	private String solarTerm;
	
	/**
	 * 日期类型，0休息日，1其他为工作日
	 */
	private int dateType;

	/**
	 * 扩展信息
	 */
	private Object obj;

	/**
	 * 创建DayWrapper
	 * 
	 * @param localDateTime LocalDateTime
	 */
	public DayWrapper(LocalDateTime localDateTime) {
		this(localDateTime, false);
	}

	/**
	 * 创建DayWrapper
	 * 
	 * @param localDateTime LocalDateTime
	 * @param includeLunarDate
	 *            是否包含农历
	 */
	public DayWrapper(LocalDateTime localDateTime, boolean includeLunarDate) {
		this(localDateTime, includeLunarDate, false, null, null);
	}

	/**
	 * 创建DayWrapper
	 * 
	 * @param localDateTime LocalDateTime
	 * @param includeLunarDate
	 *            是否包含农历
	 * @param includeHoliday
	 *            是否包含节日
	 * @param localHolidayMap
	 *            自定义公历节日信息localHolidayMap 自定义公历节日数据，特殊节日如，"母亲节", "5-W-2-7"
	 *            5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天，为null时，使用默认数据 LocalHolidayEnum
	 * @param chineseHolidayMap
	 *            自定义农历节日信息，特殊节日如除夕 用CHUXI表示，为null时，使用默认数据 ChineseHolidayEnum
	 */
	public DayWrapper(LocalDateTime localDateTime, boolean includeLunarDate, boolean includeHoliday,
			Map<String, String> localHolidayMap, Map<String, String> chineseHolidayMap) {
		this(localDateTime, null, includeLunarDate, includeHoliday, localHolidayMap, chineseHolidayMap);
	}

	public DayWrapper(LocalDateTime localDateTime, Object obj, boolean includeLunarDate, boolean includeHoliday,
			Map<String, String> localHolidayMap, Map<String, String> chineseHolidayMap) {
		super();
		this.localDateTime = localDateTime;
		this.date = DateTimeConverterUtil.toDate(localDateTime);
		this.dateStr = DateTimeFormatterUtil.formatToDateStr(localDateTime);
		this.day = localDateTime.getDayOfMonth();
		// week
		this.week = localDateTime.getDayOfWeek().getValue();
		this.weekCnShort = DateTimeCalculatorUtil.getDayOfWeekCnShort(localDateTime);
		this.weekCnLong = DateTimeCalculatorUtil.getDayOfWeekCn(localDateTime);
		this.weekEnShort = DateTimeCalculatorUtil.getDayOfWeekEnShort(localDateTime);
		this.weekEnShortUpper = DateTimeCalculatorUtil.getDayOfWeekEnShortUpper(localDateTime);
		this.weekEnLong = DateTimeCalculatorUtil.getDayOfWeekEnLong(localDateTime);
		this.obj = obj;

		// LunarDate
		if (includeLunarDate) {
			this.lunarDate = LunarDate.from(localDateTime);
			this.lunarDateStr = lunarDate.getlDateCn();
			this.lunarDay = lunarDate.getlDayCn();
			this.solarTerm = lunarDate.getSolarTerm();
		}

		// Holiday
		if (includeHoliday) {
			this.localHoliday = Holiday.getLocalHoliday(localDateTime, localHolidayMap);
			if (includeLunarDate) {
				this.chineseHoliday = Holiday.getChineseHoliday(localDateTime, chineseHolidayMap);
			}
		}
		
		// 工作日
		this.dateType = DateTimeCalculatorUtil.isWorkDay(localDateTime)?1:0;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getWeekCnShort() {
		return weekCnShort;
	}

	public void setWeekCnShort(String weekCnShort) {
		this.weekCnShort = weekCnShort;
	}

	public String getWeekEnShort() {
		return weekEnShort;
	}

	public void setWeekEnShort(String weekEnShort) {
		this.weekEnShort = weekEnShort;
	}

	public String getWeekEnShortUpper() {
		return weekEnShortUpper;
	}

	public void setWeekEnShortUpper(String weekEnShortUpper) {
		this.weekEnShortUpper = weekEnShortUpper;
	}

	public String getWeekCnLong() {
		return weekCnLong;
	}

	public void setWeekCnLong(String weekCnLong) {
		this.weekCnLong = weekCnLong;
	}

	public String getWeekEnLong() {
		return weekEnLong;
	}

	public void setWeekEnLong(String weekEnLong) {
		this.weekEnLong = weekEnLong;
	}

	public LunarDate getLunarDate() {
		return lunarDate;
	}

	public void setLunarDate(LunarDate lunarDate) {
		this.lunarDate = lunarDate;
	}

	public String getLocalHoliday() {
		return localHoliday;
	}

	public void setLocalHoliday(String localHoliday) {
		this.localHoliday = localHoliday;
	}

	public String getChineseHoliday() {
		return chineseHoliday;
	}

	public void setChineseHoliday(String chineseHoliday) {
		this.chineseHoliday = chineseHoliday;
	}

	public String getSolarTerm() {
		return solarTerm;
	}

	public void setSolarTerm(String solarTerm) {
		this.solarTerm = solarTerm;
	}

	public String getLunarDateStr() {
		return lunarDateStr;
	}

	public void setLunarDateStr(String lunarDateStr) {
		this.lunarDateStr = lunarDateStr;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getDateType() {
		return dateType;
	}

	public void setDateType(int dateType) {
		this.dateType = dateType;
	}

	public String getLunarDay() {
		return lunarDay;
	}

	public void setLunarDay(String lunarDay) {
		this.lunarDay = lunarDay;
	}

}
