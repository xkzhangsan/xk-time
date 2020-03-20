package com.xkzhangsan.time.calendar;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

/**
 * 日历工具类
 * 包括：
 * 1.生成指定时间的日历方法，generateCalendar* 比如generateCalendar(int year, int month) 生成指定年月的日历
 * 
 * @ClassName: CalendarUtil
 * @Description: CalendarUtil
 * @author xkzhangsan
 * @date 2020年03月18日
 */
public class CalendarUtil {

	private CalendarUtil() {
	}

	/**
	 * 生成指定年月的日历
	 * @param year
	 * @param month
	 * @return
	 */
	public static CalendarWrapper generateCalendar(int year, int month){
		CalendarWrapper calendarWrapper = new CalendarWrapper();
		Map<String, DayWrapper> dayMap = new ConcurrentHashMap<String, DayWrapper>();
		List<LocalDateTime> localDateTimeList = DateTimeCalculatorUtil.getLocalDateTimeList(YearMonth.of(year, month));
		if(localDateTimeList == null || localDateTimeList.isEmpty()){
			return calendarWrapper;
		}
		List<DayWrapper> dayWrapperList = new ArrayList<>();
		localDateTimeList.stream().forEach(localDateTime->{
			DayWrapper dayWrapper = new DayWrapper(localDateTime);
			dayWrapperList.add(dayWrapper);
			dayMap.put(DateTimeFormatterUtil.formatToDateStr(localDateTime), dayWrapper);
		});
		
		MonthWrapper monthWrapper = new MonthWrapper(month, dayWrapperList);
		List<MonthWrapper> monthWrapperList = new ArrayList<>();
		monthWrapperList.add(monthWrapper);
		YearWrapper yearWrapper = new YearWrapper(year, monthWrapperList);
		
		List<YearWrapper> yearWrapperList = new ArrayList<>();
		yearWrapperList.add(yearWrapper);
		
		calendarWrapper = new CalendarWrapper(yearWrapperList, dayMap);
		return calendarWrapper;
	}
	
	/**
	 * 生成指定年的日历
	 * @param year
	 * @return
	 */
	public static CalendarWrapper generateCalendar(int year){
		CalendarWrapper calendarWrapper = new CalendarWrapper();
		Map<String, DayWrapper> dayMap = new ConcurrentHashMap<String, DayWrapper>();
		List<MonthWrapper> monthWrapperList = new ArrayList<>();
		for(int i=1; i<=12; i++){
			List<LocalDateTime> localDateTimeList = DateTimeCalculatorUtil.getLocalDateTimeList(YearMonth.of(year, i));
			if(localDateTimeList == null || localDateTimeList.isEmpty()){
				continue;
			}
			List<DayWrapper> dayWrapperList = new ArrayList<>();
			localDateTimeList.stream().forEach(localDateTime->{
				DayWrapper dayWrapper = new DayWrapper(localDateTime);
				dayWrapperList.add(dayWrapper);
				dayMap.put(DateTimeFormatterUtil.formatToDateStr(localDateTime), dayWrapper);
			});
			
			MonthWrapper monthWrapper = new MonthWrapper(i, dayWrapperList);
			monthWrapperList.add(monthWrapper);
		}
		
		List<YearWrapper> yearWrapperList = new ArrayList<>();
		YearWrapper yearWrapper = new YearWrapper(year, monthWrapperList);
		yearWrapperList.add(yearWrapper);
		calendarWrapper = new CalendarWrapper(yearWrapperList, dayMap);
		return calendarWrapper;
	}
}
