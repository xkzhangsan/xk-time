package com.xkzhangsan.time.holiday;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Objects;

import com.xkzhangsan.time.LunarDate;
/**
 * 农历节日
 * 使用LunarDate处理日历，所有仅支持公历1901-1950年的农历节日
 *@version 0.1 ，初版，试用
 */
public enum ChineseHolidayEnum implements Holiday{
	
	CHUNJIE("春节", "0101"),
	YUANXIAOJIE("元宵节", "0115"),
	LONGTAITOU("龙抬头", "0202"),
	DUANWUJIE("端午节", "0505"),
	QIXIJIE("七夕节", "0707"),
	ZHONGQIUJIE("中秋节", "0815"),
	CHONGYANGJIE("重阳节", "0909"),
	LABAJIE(" 腊八节", "1208"),
	XIAONIANNORTH("北方小年", "1223"),
	XIAONIANSOUTH("南方小年", "1224"),
	CHUXI("除夕", "CHUXI"),
    /**
     * 默认值
     */
    DEFAULT_HOLIDAY("", ""),;
	
	private ChineseHolidayEnum(String name, String pattern) {
		this.name = name;
		this.pattern = pattern;
	}
	private final String name;
	private final String pattern;
	public String getName() {
		return name;
	}
	public String getPattern() {
		return pattern;
	}
	
	/**
	 * 根据时间获取农历节日枚举
	 * @param temporal
	 * @return
	 */
	public static ChineseHolidayEnum getHoliday(Temporal temporal) {
		Objects.requireNonNull(temporal, "temporal");
		LunarDate lunarDate = LunarDate.from(temporal);
		String monthDayStr = lunarDate.formatShort();
		//对比枚举日期，返回假日
		for (ChineseHolidayEnum chineseHolidayEnum : ChineseHolidayEnum.values()) {
			if (chineseHolidayEnum.getPattern().equals(monthDayStr)) {
				return chineseHolidayEnum;
			}
			//如果为特殊节日除夕
			if (chineseHolidayEnum.getPattern().equals(CHUXI.getPattern())) {
				LocalDate tempLocalDate = lunarDate.getLocalDate();
				LocalDate targetLocalDate = tempLocalDate.plus(1, ChronoUnit.DAYS);
				LunarDate targetLunarDate = LunarDate.from(targetLocalDate);
				String targetMonthDayStr = targetLunarDate.formatShort();
				if(CHUNJIE.getPattern().equals(targetMonthDayStr)){
					return CHUXI;
				}
			}
		}
		return DEFAULT_HOLIDAY;
	}
	
	/**
	 * 根据时间获取农历节日名称
	 * @param temporal
	 * @return
	 */
	public static String getHolidayName(Temporal temporal) {
		Objects.requireNonNull(temporal, "temporal");
		LunarDate lunarDate = LunarDate.from(temporal);
		String monthDayStr = lunarDate.formatShort();
		//对比枚举日期，返回假日
		for (ChineseHolidayEnum chineseHolidayEnum : ChineseHolidayEnum.values()) {
			if (chineseHolidayEnum.getPattern().equals(monthDayStr)) {
				return chineseHolidayEnum.name;
			}
			//如果为特殊节日除夕
			if (chineseHolidayEnum.getPattern().equals(CHUXI.getPattern())) {
				LocalDate tempLocalDate = lunarDate.getLocalDate();
				LocalDate targetLocalDate = tempLocalDate.plus(1, ChronoUnit.DAYS);
				LunarDate targetLunarDate = LunarDate.from(targetLocalDate);
				String targetMonthDayStr = targetLunarDate.formatShort();
				if(CHUNJIE.getPattern().equals(targetMonthDayStr)){
					return CHUXI.name;
				}
			}
		}
		return "";
	}
	
	/**
	 * 对比农历月日，比如，农历生日等
	 * @param temporal
	 * @param monthDay
	 * @return
	 */
	public static boolean compareLunarMonthDay(Temporal temporal, String monthDay) {
		Objects.requireNonNull(temporal, "temporal");
		Objects.requireNonNull(monthDay, "monthDay");
		LunarDate lunarDate = LunarDate.from(temporal);
		String monthDayStr = lunarDate.formatShort();
		if(monthDayStr.equals(monthDay)){
			return true;
		}
		return false;
	}
}
