package com.xkzhangsan.time.test;

import org.junit.Test;

import com.xkzhangsan.time.calendar.CalendarUtil;
import com.xkzhangsan.time.calendar.CalendarWrapper;
import com.xkzhangsan.time.calendar.DayWrapper;

public class CalendarTest {

	@Test
	public void calendarTest(){
		//获取2020年日历，包含农历和所有节假日信息
		CalendarWrapper calendarWrapper = CalendarUtil.generateCalendarWithHoliday(2020, null, null);
		//输出年
		System.out.println(calendarWrapper.getYears().get(0).getYear());
		//输出1月
		System.out.println(calendarWrapper.getYears().get(0).getMonths().get(0).getMonth());
		//输出1日
		System.out.println(calendarWrapper.getYears().get(0).getMonths().get(0).getDays().get(0).getDay());
		//输出1日日期格式化
		System.out.println(calendarWrapper.getYears().get(0).getMonths().get(0).getDays().get(0).getDateStr());
		//输出1日星期
		System.out.println(calendarWrapper.getYears().get(0).getMonths().get(0).getDays().get(0).getWeekStr());
		//输出1日的数据值
		System.out.println(calendarWrapper.getYears().get(0).getMonths().get(0).getDays().get(0).getObj());
		
		//获取指定日期的天，并对当前天设置扩展信息
		DayWrapper dayW = calendarWrapper.getDayMap().get("2020-03-15");
		if(dayW != null){
			dayW.setObj("hello 2020-03-15!");
		}
		
		//获取指定日期的天的扩展信息
		System.out.println(calendarWrapper.getDayMap().get("2020-03-15").getObj());
		System.out.println(calendarWrapper.getYears().get(0).getMonths().get(2).getDays().get(14).getObj());
	}
}
