package com.xkzhangsan.time.enums;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

import com.xkzhangsan.time.constants.Constant;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;

/**
 * 十二时辰枚举，包含英文全称，中文全称，时间范围
 * 
 * @author xkzhangsan
 */
public enum TwelveTwoEnum {
	
	ZISHI(1, "子时", "23:00:00", "01:00:00"),
	CHOUSHI(2, "丑时", "01:00:00", "03:00:00"),
	YINSHI(3, "寅时", "03:00:00", "05:00:00"),
	MAOSHI(4, "卯辰", "05:00:00", "07:00:00"),
	CHENSHI(5, "辰时", "07:00:00", "09:00:00"),
	SISHI(6, "巳时", "09:00:00", "11:00:00"),
	WUSHI(7, "午时", "11:00:00", "13:00:00"), 
	WEISHI(8, "未时", "13:00:00", "15:00:00"),
	SHENSHI(9, "申时", "15:00:00", "17:00:00"),
	YOUSHI(10, "酉时", "17:00:00", "19:00:00"),
	XUSHI(11, "戌时", "19:00:00", "21:00:00"),
	HAISHI(12, "亥时", "21:00:00", "23:00:00"),;
	
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
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;

	
	private TwelveTwoEnum(int code, String nameCn, String startTime, String endTime) {
		this.code = code;
		this.nameCn = nameCn;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * 根据时间查询时辰名称枚举
	 * @param localTime LocalTime
	 * @return TwelveHoursEnum
	 */
	public static TwelveTwoEnum getTwelveHoursEnum(LocalTime localTime){
		Objects.requireNonNull(localTime, "localTime");
		for(TwelveTwoEnum twelveHours : TwelveTwoEnum.values()){
			LocalTime timeStart = LocalTime.parse(twelveHours.getStartTime());
			LocalTime timeEnd = LocalTime.parse(twelveHours.getEndTime());
			//子时，特殊计算
			if(isZiShi(localTime, timeStart, timeEnd)){
				return TwelveTwoEnum.ZISHI;
			}
			if (isBetween(localTime, timeStart, timeEnd)) {
				return twelveHours;
			}
		}
		return null;
	}

	/**
	 * 时间区间判断
	 * @param localTime LocalTime
	 * @param timeStart 开始时间
	 * @param timeEnd 结束时间
	 * @return 在区间 true，不在 false
	 */
	private static boolean isBetween(LocalTime localTime, LocalTime timeStart, LocalTime timeEnd) {
		return localTime.equals(timeStart) || (localTime.isAfter(timeStart) && localTime.isBefore(timeEnd));
	}
	
	/**
	 * 是否子时
	 * @param localTime LocalTime
	 * @param timeStart 开始时间
	 * @param timeEnd 结束时间
	 * @return 是 true， 否 false
	 */
	private static boolean isZiShi(LocalTime localTime, LocalTime timeStart, LocalTime timeEnd) {
		//23点，0点
		if(Constant.TWENTYTHREECLOCK.equals(localTime) || LocalTime.MIDNIGHT.equals(localTime)){
			return true;
		}
		//23-0点
		if(localTime.isAfter(Constant.TWENTYTHREECLOCK)){
			return true;
		}
		//0-1点
		if(localTime.isAfter(LocalTime.MIDNIGHT) && localTime.isBefore(Constant.ONECLOCK)){
			return true;
		}
		return false;
	}

	/**
	 * 根据时间查询时辰名称
	 * @param localTime LocalTime
	 * @return String
	 */
	public static String getNameCn(LocalTime localTime){
		TwelveTwoEnum twelveHours = getTwelveHoursEnum(localTime);
		return twelveHours != null ? twelveHours.getNameCn() : null;
	}
	
	/**
	 * 根据时间查询时辰名称
	 * @param date Date
	 * @return String
	 */
	public static String getNameCn(Date date){
		return getNameCn(DateTimeConverterUtil.toLocalTime(date));
	}
	
	public int getCode() {
		return code;
	}

	public String getNameCn() {
		return nameCn;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

}
