package com.xkzhangsan.time.enums;

import java.time.MonthDay;
import java.util.Objects;

import com.xkzhangsan.time.constants.Constant;

import static com.xkzhangsan.time.constants.Constant.MONTHDAY_FORMAT_PRE;

/**
 * 星座名称枚举，包含英文全称，中文全称，时间范围
 * 
 * @author xkzhangsan
 */
public enum ConstellationNameEnum {
	
	Aries(1, "白羊座", "03-21", "04-19"),
	Taurus(2, "金牛座", "04-20", "05-20"),
	Gemini(3, "双子座", "05-21", "06-21"),
	Cancer(4, "巨蟹座", "06-22", "07-22"),
	Leo(5, "狮子座", "07-23", "08-22"),
	Virgo(6, "处女座", "08-23", "09-22"),
	Libra(7, "天秤座", "09-23", "10-23"), 
	Scorpio(8, "天蝎座", "10-24", "11-22"),
	Sagittarius(9, "射手座", "11-23", "12-21"),
	Capricorn(10, "摩羯座", "12-22", "01-19"),
	Aquarius(11, "水瓶座", "01-20", "02-18"),
	Pisces(12, "双鱼座", "02-19", "03-20"),;
	
	/**
	 * 序号
	 */
	private int code;
	
	/**
	 * 中文名称
	 */
	private String nameCn;
	
	/**
	 * 开始时间
	 */
	private String startDate;
	
	/**
	 * 结束时间
	 */
	private String endDate;

	

	private ConstellationNameEnum(int code, String nameCn, String startDate, String endDate) {
		this.code = code;
		this.nameCn = nameCn;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/**
	 * 根据日期查询星座名称枚举
	 * @param monthDayStr MM-dd格式
	 * @return ConstellationNameEnum
	 */
	public static ConstellationNameEnum getConstellationNameEnumByMonthDay(String monthDayStr){
		Objects.requireNonNull(monthDayStr, "monthDayStr");
		MonthDay monthDay = MonthDay.parse(MONTHDAY_FORMAT_PRE + monthDayStr);
		for(ConstellationNameEnum constellationNameEnum : ConstellationNameEnum.values()){
			if(constellationNameEnum.getStartDate().equals(monthDayStr)||constellationNameEnum.getEndDate().equals(monthDayStr)){
				return constellationNameEnum;
			}
			MonthDay monthDayStart = MonthDay.parse(MONTHDAY_FORMAT_PRE + constellationNameEnum.getStartDate());
			MonthDay monthDayEnd = MonthDay.parse(MONTHDAY_FORMAT_PRE + constellationNameEnum.getEndDate());
			if(isCapricorn(monthDay, monthDayStart, monthDayEnd)){
				return ConstellationNameEnum.Capricorn;
			}
			if (monthDay.isAfter(monthDayStart) && monthDay.isBefore(monthDayEnd)) {
				return constellationNameEnum;
			}
		}
		return null;
	}

	/**
	 * 是否是摩羯座
	 * @param monthDay
	 * @param monthDayStart
	 * @param monthDayEnd
	 * @return
	 */
	private static boolean isCapricorn(MonthDay monthDay, MonthDay monthDayStart, MonthDay monthDayEnd) {
		if(Constant.MONTH_DAY_START.equals(monthDay)||Constant.MONTH_DAY_END.equals(monthDay)){
			return true;
		}
		if(monthDay.isAfter(Constant.CAPRICORN_START) && monthDay.isBefore(Constant.MONTH_DAY_END)){
			return true;
		}
		if(monthDay.isAfter(Constant.MONTH_DAY_START) && monthDay.isBefore(Constant.CAPRICORN_END)){
			return true;
		}
		return false;
	}

	/**
	 * 根据日期查询星座中文名称
	 * @param monthDayStr MM-dd格式
	 * @return String
	 */
	public static String getNameCnByMonthDay(String monthDayStr){
		ConstellationNameEnum constellationNameEnum = getConstellationNameEnumByMonthDay(monthDayStr);
		return constellationNameEnum != null ? constellationNameEnum.getNameCn() : null;
	}
	
	/**
	 * 根据日期查询星座英文名称
	 * @param monthDayStr MM-dd格式
	 * @return String
	 */
	public static String getNameEnByMonthDay(String monthDayStr){
		ConstellationNameEnum constellationNameEnum = getConstellationNameEnumByMonthDay(monthDayStr);
		return constellationNameEnum != null ? constellationNameEnum.name() : null;
	}
	

	public int getCode() {
		return code;
	}

	public String getNameCn() {
		return nameCn;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

}
