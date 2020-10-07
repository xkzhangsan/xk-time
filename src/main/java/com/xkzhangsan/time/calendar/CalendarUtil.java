package com.xkzhangsan.time.calendar;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;
import com.xkzhangsan.time.utils.CollectionUtil;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 日历工具类 <br>
 * 包括：<br>
 * <pre>
 *  1.生成指定时间的日历方法，generateCalendar* 比如generateCalendar(int year, int
 * month) 生成指定年月的日历<br>
 *  2.生成指定时间的日历，包含农历和所有节假日信息方法，generateCalendarWithHoliday*， 比如
 *  {@code generateCalendarWithHoliday(int year, int month, Map<String, String> localHolidayMap,
			Map<String, String> chineseHolidayMap, Map<String, Integer> dateTypeMap) } 生成指定年月的日历，包含农历和所有节假日信息，可以自定义节假日和工作日等。<br>
   </pre>
 * @author xkzhangsan
 */
public class CalendarUtil {

	private CalendarUtil() {
	}

	/**
	 * 生成指定年月的日历
	 * 
	 * @param year 年
	 * @param month 月
	 * @return CalendarWrapper
	 */
	public static CalendarWrapper generateCalendar(int year, int month) {
		return generateCalendar(year, month, false, false, null, null, null);
	}

	/**
	 * 生成指定年月的日历， 包含农历信息
	 *
	 * @param year 年
	 * @param month 月
	 * @return CalendarWrapper
	 */
	public static CalendarWrapper generateCalendarWithLunar(int year, int month) {
		return generateCalendar(year, month, true, false, null, null, null);
	}

	/**
	 * 生成指定年月的日历，包含公历节假日信息
	 *
	 * @param year 年
	 * @param month 月
	 * @param localHolidayMap
	 *            自定义公历节日数据，特殊节日如，"母亲节", "5-W-2-7"
	 *            5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天，为null时，使用默认数据 LocalHolidayEnum
	 *            比如localHolidayMap.put("0801", "建军节");
	 * @param dateTypeMap 日期类型，0休息日，1等其他为工作日，比如dateTypeMap.put("2020-08-07", 0);
	 * @return CalendarWrapper
	 */
	public static CalendarWrapper generateCalendarWithLocalHoliday(int year, int month,
			Map<String, String> localHolidayMap, Map<String, Integer> dateTypeMap) {
		return generateCalendar(year, month, false, true, localHolidayMap, null, dateTypeMap);
	}
	
	/**
	 * 生成指定年月的日历，包含农历和所有节假日信息
	 * @param year 年
	 * @param month 月
	 * @return CalendarWrapper
	 */
	public static CalendarWrapper generateCalendarWithHoliday(int year, int month) {
		return generateCalendar(year, month, true, true, null, null, null);
	}

	/**
	 * 生成指定年月的日历，包含农历和所有节假日信息，使用自定义数据
	 *
	 * @param year 年
	 * @param month 月
	 * @param localHolidayMap
	 *            自定义公历节日数据，特殊节日如，"母亲节", "5-W-2-7"
	 *            5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天，为null时，使用默认数据 LocalHolidayEnum
	 *            比如localHolidayMap.put("0801", "建军节");
	 * @param chineseHolidayMap
	 *            自定义农历节日信息，特殊节日如除夕 用CHUXI表示，为null时，使用默认数据 ChineseHolidayEnum
	 *            比如chineseHolidayMap.put("0707", "七夕情人节");
	 * @param dateTypeMap 日期类型，0休息日，1等其他为工作日，比如dateTypeMap.put("2020-08-07", 0);
	 * @return CalendarWrapper
	 */
	public static CalendarWrapper generateCalendarWithHoliday(int year, int month, Map<String, String> localHolidayMap,
			Map<String, String> chineseHolidayMap, Map<String, Integer> dateTypeMap) {
		return generateCalendar(year, month, true, true, localHolidayMap, chineseHolidayMap, dateTypeMap);
	}

	/**
	 * 生成指定年月的日历，包含农历和所有节假日信息
	 *
	 * @param year 年
	 * @param month 月
	 * @param includeLunarDate 包含农历
	 * @param includeHoliday 包含节日
	 * @param localHolidayMap
	 *            自定义公历节日数据，特殊节日如，"母亲节", "5-W-2-7"
	 *            5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天，为null时，使用默认数据 LocalHolidayEnum
	 *            比如localHolidayMap.put("0801", "建军节");
	 * @param chineseHolidayMap
	 *            自定义农历节日信息，特殊节日如除夕 用CHUXI表示，为null时，使用默认数据 ChineseHolidayEnum
	 *            比如chineseHolidayMap.put("0707", "七夕情人节");
	 * @param dateTypeMap 日期类型，0休息日，1等其他为工作日，比如dateTypeMap.put("2020-08-07", 0);
	 * @return CalendarWrapper
	 */
	private static CalendarWrapper generateCalendar(int year, int month, boolean includeLunarDate,
			boolean includeHoliday, Map<String, String> localHolidayMap, Map<String, String> chineseHolidayMap, Map<String, Integer> dateTypeMap) {
		YearMonth yearMonth = YearMonth.of(year, month);
		CalendarWrapper calendarWrapper = new CalendarWrapper();
		Map<String, DayWrapper> dayMap = new ConcurrentHashMap<String, DayWrapper>(64);
		List<DayWrapper> dayList = new ArrayList<DayWrapper>();
		
		List<LocalDateTime> localDateTimeList = DateTimeCalculatorUtil.getLocalDateTimeList(YearMonth.of(year, month));
		if (CollectionUtil.isEmpty(localDateTimeList)) {
			return calendarWrapper;
		}
		List<DayWrapper> dayWrapperList = new ArrayList<>();
		localDateTimeList.stream().forEach(localDateTime -> {
			DayWrapper dayWrapper = new DayWrapper(localDateTime, includeLunarDate, includeHoliday, localHolidayMap,
					chineseHolidayMap);
			dayWrapperList.add(dayWrapper);
			dayMap.put(DateTimeFormatterUtil.formatToDateStr(localDateTime), dayWrapper);
			dayList.add(dayWrapper);
		});

		// DateType
		if(CollectionUtil.isNotEmpty(dateTypeMap) && CollectionUtil.isNotEmpty(dayMap)){
			dateTypeMap.forEach((k,v)->{
				if(dayMap.containsKey(k)){
					dayMap.get(k).setDateType(v);
				}
			});
		}
		
		MonthWrapper monthWrapper = new MonthWrapper(month, dayWrapperList, yearMonth.lengthOfMonth());
		List<MonthWrapper> monthWrapperList = new ArrayList<>();
		monthWrapperList.add(monthWrapper);
		YearWrapper yearWrapper = new YearWrapper(year, monthWrapperList);

		List<YearWrapper> yearWrapperList = new ArrayList<>();
		yearWrapperList.add(yearWrapper);
		
		calendarWrapper = new CalendarWrapper(yearWrapperList, dayMap, dayList);
		return calendarWrapper;
	}

	/**
	 * 生成指定年月的日历
	 * 
	 * @param year 年
	 * @return CalendarWrapper
	 */
	public static CalendarWrapper generateCalendar(int year) {
		return generateCalendar(year, false, false, null, null, null);
	}

	/**
	 * 生成指定年月的日历， 包含农历信息
	 * 
	 * @param year 年
	 * @return CalendarWrapper
	 */
	public static CalendarWrapper generateCalendarWithLunar(int year) {
		return generateCalendar(year, true, false, null, null, null);
	}

	/**
	 * 生成指定年月的日历，包含公历节假日信息
	 * 
	 * @param year 年
	 * @param localHolidayMap
	 *            自定义公历节日数据，特殊节日如，"母亲节", "5-W-2-7"
	 *            5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天，为null时，使用默认数据 LocalHolidayEnum
	 *            比如localHolidayMap.put("0801", "建军节");
	 * @param dateTypeMap 日期类型，0休息日，1等其他为工作日，比如dateTypeMap.put("2020-08-07", 0);
	 * @return CalendarWrapper
	 */
	public static CalendarWrapper generateCalendarWithLocalHoliday(int year, Map<String, String> localHolidayMap, Map<String, Integer> dateTypeMap) {
		return generateCalendar(year, false, true, localHolidayMap, null, dateTypeMap);
	}
	
	/**
	 * 生成指定年月的日历，包含农历和所有节假日信息
	 * @param year 年
	 * @return CalendarWrapper
	 */
	public static CalendarWrapper generateCalendarWithHoliday(int year) {
		return generateCalendar(year, true, true, null, null, null);
	}

	/**
	 * 生成指定年月的日历，包含农历和所有节假日信息，使用自定义数据
	 * 
	 * @param year 年
	 * @param localHolidayMap
	 *            自定义公历节日数据，特殊节日如，"母亲节", "5-W-2-7"
	 *            5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天，为null时，使用默认数据 LocalHolidayEnum
	 *            比如localHolidayMap.put("0801", "建军节");
	 * @param chineseHolidayMap
	 *            自定义农历节日信息，特殊节日如除夕 用CHUXI表示，为null时，使用默认数据 ChineseHolidayEnum
	 *            比如chineseHolidayMap.put("0707", "七夕情人节");
	 * @param dateTypeMap 日期类型，0休息日，1等其他为工作日，比如dateTypeMap.put("2020-08-07", 0);
	 * @return CalendarWrapper
	 */
	public static CalendarWrapper generateCalendarWithHoliday(int year, Map<String, String> localHolidayMap,
			Map<String, String> chineseHolidayMap, Map<String, Integer> dateTypeMap) {
		return generateCalendar(year, true, true, localHolidayMap, chineseHolidayMap, dateTypeMap);
	}

	/**
	 * 生成指定年月的日历，包含农历和所有节假日信息
	 * 
	 * @param year 年
	 * @param includeLunarDate 包含农历
	 * @param includeHoliday 包含节日
	 * @param localHolidayMap
	 *            自定义公历节日数据，特殊节日如，"母亲节", "5-W-2-7"
	 *            5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天，为null时，使用默认数据 LocalHolidayEnum
	 *            比如localHolidayMap.put("0801", "建军节");
	 * @param chineseHolidayMap
	 *            自定义农历节日信息，特殊节日如除夕 用CHUXI表示，为null时，使用默认数据 ChineseHolidayEnum
	 *            比如chineseHolidayMap.put("0707", "七夕情人节");
	 * @param dateTypeMap 日期类型，0休息日，1等其他为工作日，比如dateTypeMap.put("2020-08-07", 0);
	 * @return CalendarWrapper
	 */
	private static CalendarWrapper generateCalendar(int year, boolean includeLunarDate, boolean includeHoliday,
			Map<String, String> localHolidayMap, Map<String, String> chineseHolidayMap, Map<String, Integer> dateTypeMap) {
		CalendarWrapper calendarWrapper = new CalendarWrapper();
		Map<String, DayWrapper> dayMap = new ConcurrentHashMap<String, DayWrapper>(512);
		List<DayWrapper> dayList = new ArrayList<DayWrapper>();
		List<MonthWrapper> monthWrapperList = new ArrayList<>();
		
		for (int i = 1; i <= 12; i++) {
			YearMonth yearMonth = YearMonth.of(year, i);
			List<LocalDateTime> localDateTimeList = DateTimeCalculatorUtil.getLocalDateTimeList(YearMonth.of(year, i));
			if (CollectionUtil.isEmpty(localDateTimeList)) {
				continue;
			}
			List<DayWrapper> dayWrapperList = new ArrayList<>();
			localDateTimeList.stream().forEach(localDateTime -> {
				DayWrapper dayWrapper = new DayWrapper(localDateTime, includeLunarDate, includeHoliday, localHolidayMap,
						chineseHolidayMap);
				dayWrapperList.add(dayWrapper);
				dayMap.put(DateTimeFormatterUtil.formatToDateStr(localDateTime), dayWrapper);
				dayList.add(dayWrapper);
			});

			MonthWrapper monthWrapper = new MonthWrapper(i, dayWrapperList, yearMonth.lengthOfMonth());
			monthWrapperList.add(monthWrapper);
		}
		
		// DateType
		if(CollectionUtil.isNotEmpty(dateTypeMap) && CollectionUtil.isNotEmpty(dayMap)){
			dateTypeMap.forEach((k,v)->{
				if(dayMap.containsKey(k)){
					dayMap.get(k).setDateType(v);
				}
			});
		}

		List<YearWrapper> yearWrapperList = new ArrayList<>();
		YearWrapper yearWrapper = new YearWrapper(year, monthWrapperList);
		yearWrapperList.add(yearWrapper);
		calendarWrapper = new CalendarWrapper(yearWrapperList, dayMap, dayList);
		return calendarWrapper;
	}
}
