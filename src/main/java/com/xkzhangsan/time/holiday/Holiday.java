package com.xkzhangsan.time.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.xkzhangsan.time.LunarDate;
import com.xkzhangsan.time.constants.Constant;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

/**
 * 节日处理
 * 包含
 * 1.公历节假日计算， getLocalHoliday* 比如getLocalHoliday(Date date) 计算date的公历节日，getLocalHoliday(Map<String, String> localHolidayMap, Date date) 可以传入自定义节日数据
 * 2.农历节假日计算， getChineseHoliday* 比如getChineseHoliday(Date date) 计算date的农历节日，getChineseHoliday(Map<String, String> chineseHolidayMap, Date date) 可以传入自定义节日数据
 * 3.二十四节气计算， getSolarTerm* 比如getSolarTerm(Date date) 计算date的二十四节气
 * 
 * 农历相关，仅支持公历1901-1950年的计算
* @ClassName: Holiday
* @Description: Holiday
* @author xkzhangsan
* @date 2019年12月30日
* @version 0.2 试用
 */
public interface Holiday {
	
	/**
	 * 根据日期获取公历节日
	 * @param date
	 * @return
	 */
	static String getLocalHoliday(Date date){
		return getLocalHoliday(null, date);
	}
	
	/**
	 * 根据日期获取公历节日
	 * @param localHolidayMap 自定义节日数据，特殊节日如，"母亲节", "5-W-2-7" 5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天
	 * @param date
	 * @return
	 */
	static String getLocalHoliday(Map<String, String> localHolidayMap, Date date){
		Objects.requireNonNull(date, "date");
		return getLocalHoliday(localHolidayMap, DateTimeConverterUtil.toLocalDateTime(date));
	}
	
	/**
	 * 根据日期获取公历节日
	 * @param temporal
	 * @return
	 */
	static String getLocalHoliday(Temporal temporal){
		return getLocalHoliday(null, temporal);
	}
	
	/**
	 * 根据日期获取公历节日
	 * @param localHolidayMap 自定义节日数据
	 * @param temporal
	 * @return
	 */
	static String getLocalHoliday(Map<String, String> localHolidayMap, Temporal temporal){
		Objects.requireNonNull(temporal, "temporal");
		String localHoliday = "";
		if(localHolidayMap == null || localHolidayMap.isEmpty()){
			System.out.println("localHolidayMap is null, use default data");
			localHolidayMap = LocalHolidayEnum.convertToMap();
		}
		
		MonthDay monthDay = MonthDay.from(temporal);
		String monthDayStr = monthDay.format(DateTimeFormatterUtil.MMDD_FMT);
		for(Entry<String, String> entry : localHolidayMap.entrySet()){
			if (entry.getKey().equals(monthDayStr)) {
				localHoliday = localHoliday + " " +entry.getValue();
			}
			//如果为特殊格式，解析对比
			if (entry.getKey().contains("W")) {
				String[] arr = entry.getKey().split("-");
				int month = Integer.parseInt(arr[0]);
				int weekIndex = Integer.parseInt(arr[2]);
				int weekValue = Integer.parseInt(arr[3]);
				DayOfWeek dow = DayOfWeek.of(weekValue);
				//设置到当前节日的月份
				Temporal tempTem = temporal.with(ChronoField.MONTH_OF_YEAR, month);
				//设置到当前节日的第几星期第几天
				Temporal targetTem = tempTem.with(TemporalAdjusters.dayOfWeekInMonth(weekIndex, dow));
				MonthDay targetMonthDay = MonthDay.from(targetTem);
				String targetMonthDayStr = targetMonthDay.format(DateTimeFormatterUtil.MMDD_FMT);
				if (monthDayStr.equals(targetMonthDayStr)) {
					localHoliday = localHoliday + " " +entry.getValue();
				}
			}
		}
		return localHoliday;
	}
	
	/**
	 * 根据日期获取农历几日
	 * @param date
	 * @return
	 */
	static String getChineseHoliday(Date date){
		return getChineseHoliday(null, date);
	}
	
	/**
	 * 根据日期获取农历几日
	 * @param chineseHolidayMap 自定义节日数据，特殊节日如除夕 用CHUXI表示
	 * @param date
	 * @return
	 */
	static String getChineseHoliday(Map<String, String> chineseHolidayMap, Date date){
		Objects.requireNonNull(date, "date");
		return getChineseHoliday(chineseHolidayMap, DateTimeConverterUtil.toLocalDateTime(date));
	}
	
	/**
	 * 根据日期获取农历几日
	 * @param temporal
	 * @return
	 */
	static String getChineseHoliday(Temporal temporal){
		return getChineseHoliday(null, temporal);
	}
	
	/**
	 * 根据日期获取农历几日
	 * @param chineseHolidayMap 自定义节日数据
	 * @param temporal
	 * @return
	 */
	static String getChineseHoliday(Map<String, String> chineseHolidayMap, Temporal temporal){
		Objects.requireNonNull(temporal, "temporal");
		String chineseHoliday = "";
		if(chineseHolidayMap == null || chineseHolidayMap.isEmpty()){
			System.out.println("chineseHolidayMap is null, use default data");
			chineseHolidayMap = ChineseHolidayEnum.convertToMap();
		}
		
		LunarDate lunarDate = LunarDate.from(temporal);
		String monthDayStr = lunarDate.formatShort();
		//对比枚举日期，返回假日
		for(Entry<String, String> entry : chineseHolidayMap.entrySet()){
			if (entry.getKey().equals(monthDayStr)) {
				chineseHoliday = chineseHoliday + " " +entry.getValue();
			}
			//如果为特殊节日除夕
			if (entry.getKey().equals(Constant.CHUXI)) {
				LocalDate tempLocalDate = lunarDate.getLocalDate();
				LocalDate targetLocalDate = tempLocalDate.plus(1, ChronoUnit.DAYS);
				LunarDate targetLunarDate = LunarDate.from(targetLocalDate);
				String targetMonthDayStr = targetLunarDate.formatShort();
				if(Constant.CHUNJIE.equals(targetMonthDayStr)){
					chineseHoliday = chineseHoliday + " " +entry.getValue();
				}
			}
		}
		return chineseHoliday;
	}
	
	/**
	 * 根据日期获取二十四节气
	 * @param date
	 * @return
	 */
	static String getSolarTerm(Date date){
		Objects.requireNonNull(date, "date");
		LunarDate lunarDate = LunarDate.from(date);
		return lunarDate.getSolarTerm();
	}
	
	/**
	 * 根据日期获取二十四节气
	 * @param chineseHolidayMap
	 * @param temporal
	 * @return
	 */
	static String getSolarTerm(Temporal temporal){
		Objects.requireNonNull(temporal, "temporal");
		LunarDate lunarDate = LunarDate.from(temporal);
		return lunarDate.getSolarTerm();
	}
}
