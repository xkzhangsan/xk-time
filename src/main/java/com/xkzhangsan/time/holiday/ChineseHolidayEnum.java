package com.xkzhangsan.time.holiday;

import com.xkzhangsan.time.LunarDate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/**
 * 农历节日处理
 * 
 * 农历相关，仅支持公历1900-2100年的农历，使用{@link LunarDate}
* @author xkzhangsan
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

	/**
	 * 中文名称
	 */
	private final String name;
	/**
	 * 匹配模式
	 */
	private final String pattern;
	public String getName() {
		return name;
	}
	public String getPattern() {
		return pattern;
	}
	
	/**
	 * 根据时间获取农历节日枚举
	 * @param temporal Temporal
	 * @return ChineseHolidayEnum
	 */
	@Deprecated
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
	 * @param temporal Temporal
	 * @return String
	 */
	public static String getHolidayName(Temporal temporal) {
		return Holiday.getChineseHoliday(temporal, convertToMap());
	}
	
	/**
	 * 对比农历月日，比如，农历生日等
	 * @param temporal Temporal
	 * @param monthDay 月日字符串
	 * @return boolean
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
	
	public static Map<String, String> convertToMap(){
		Map<String, String> chineseHolidayMap = new HashMap<String, String>();
		for (ChineseHolidayEnum chineseHolidayEnum : ChineseHolidayEnum.values()) {
			chineseHolidayMap.put(chineseHolidayEnum.getPattern(), chineseHolidayEnum.getName());
		}
		return chineseHolidayMap;
	}
}
