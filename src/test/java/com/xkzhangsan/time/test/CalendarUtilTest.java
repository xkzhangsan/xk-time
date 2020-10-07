package com.xkzhangsan.time.test;

import com.xkzhangsan.time.calendar.CalendarUtil;
import com.xkzhangsan.time.calendar.CalendarWrapper;
import com.xkzhangsan.time.calendar.DayWrapper;
import com.xkzhangsan.time.calendar.MonthWrapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日历测试类
 *
 * @author xkzhangsan
 */
public class CalendarUtilTest {

	/**
	 * 日历整年基本测试 generateCalendarWithHoliday
	 */
	@Test
	public void calendarTest() {
		// 公历节假日自定义
		Map<String, String> localHolidayMap = new HashMap<>();
		localHolidayMap.put("0801", "建军节");

		// 农历节假日自定义
		Map<String, String> chineseHolidayMap = new HashMap<>();
		chineseHolidayMap.put("0707", "七夕情人节");

		// 工作日自定义 2020-08-07周五本为工作日，这里特别设置为非工作日，2020-08-08周六本为非工作日，这里特别设置为工作日
		Map<String, Integer> dateTypeMap = new HashMap<>();
		dateTypeMap.put("2020-08-07", 0);
		dateTypeMap.put("2020-08-08", 1);

		// 获取2020年8月日历，包含农历和所有节假日信息，包含自定义数据
		CalendarWrapper calendarWrapper = CalendarUtil.generateCalendarWithHoliday(2020, localHolidayMap,
				chineseHolidayMap, dateTypeMap);
		Assert.assertEquals(366, calendarWrapper.getDayList().size());
	}

	/**
	 * 日历基本测试 generateCalendarWithHoliday
	 */
	@Test
	public void calendarMonthTest() {
		// 获取2020年日历，包含农历和所有节假日信息
		CalendarWrapper calendarWrapper = CalendarUtil.generateCalendarWithHoliday(2020, 3);
		// 输出年
		System.out.println(calendarWrapper.getYears().get(0).getYear());
		// 输出1月
		System.out.println(calendarWrapper.getYears().get(0).getMonths().get(0).getMonth());
		// 输出1日
		System.out.println(calendarWrapper.getYears().get(0).getMonths().get(0).getDays().get(0).getDay());
		// 输出1日日期格式化
		System.out.println(calendarWrapper.getYears().get(0).getMonths().get(0).getDays().get(0).getDateStr());
		// 输出1日星期
		System.out.println(calendarWrapper.getYears().get(0).getMonths().get(0).getDays().get(0).getWeekCnLong());
		// 输出1日的数据值
		System.out.println(calendarWrapper.getYears().get(0).getMonths().get(0).getDays().get(0).getObj());

		// 获取指定日期的天，并对当前天设置扩展信息
		DayWrapper dayW = calendarWrapper.getDayMap().get("2020-03-15");
		if (dayW != null) {
			dayW.setObj("hello 2020-03-15!");
		}

		// 获取指定日期的天的扩展信息
		System.out.println(calendarWrapper.getDayMap().get("2020-03-15").getObj());
		System.out.println(calendarWrapper.getYears().get(0).getMonths().get(0).getDays().get(14).getObj());

		MonthWrapper month = calendarWrapper.getYears().get(0).getMonths().get(0);
		int length = month.getLength();
		List<DayWrapper> days = month.getDays();
		int blankNum = days.get(0).getWeek();
		if (blankNum == 7) {
			blankNum = 0;
		}

		// 打印日历
		// 1.表头
		System.out.println("===================================================" + month.getMonthCnLong()+ "=================================================");
		System.out.print("日\t\t一\t\t二\t\t三\t\t四\t\t五\t\t六\n");

		// 循环打印日历内容
		// 记录当前日期值
		int count = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				// 打印空白
				if (i == 0 && j < blankNum) {
					System.out.print("\t\t");
					continue;
				}

				if (count >= length) {
					break;
				}

				// 打印日期信息
				DayWrapper dayWrapper = days.get(count);
				System.out.print(dayWrapper.getDay() + "\t\t");//天
				count++;
			}
			System.out.println();
		}
	}

	/*
	 * 日历自定义测试 generateCalendarWithHoliday
	 */
	@Test
	public void calendarMonthCustomTest() {
		// 公历节假日自定义
		Map<String, String> localHolidayMap = new HashMap<>();
		localHolidayMap.put("0801", "建军节");

		// 农历节假日自定义
		Map<String, String> chineseHolidayMap = new HashMap<>();
		chineseHolidayMap.put("0707", "七夕情人节");

		// 工作日自定义 2020-08-07周五本为工作日，这里特别设置为非工作日，2020-08-08周六本为非工作日，这里特别设置为工作日
		Map<String, Integer> dateTypeMap = new HashMap<>();
		dateTypeMap.put("2020-08-07", 0);
		dateTypeMap.put("2020-08-08", 1);

		// 获取2020年8月日历，包含农历和所有节假日信息，包含自定义数据
		CalendarWrapper calendarWrapper = CalendarUtil.generateCalendarWithHoliday(2020, 8, localHolidayMap,
				chineseHolidayMap, dateTypeMap);
		MonthWrapper month = calendarWrapper.getYears().get(0).getMonths().get(0);
		int length = month.getLength();
		List<DayWrapper> days = month.getDays();
		int blankNum = days.get(0).getWeek();
		if (blankNum == 7) {
			blankNum = 0;
		}

		// 打印日历
		// 1.表头
		System.out.println("===================================================" + month.getMonthCnLong()+ "=================================================");
		System.out.print("日\t\t一\t\t二\t\t三\t\t四\t\t五\t\t六\n");

		// 循环打印日历内容
		// 记录当前日期值
		int count = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				// 打印空白
				if (i == 0 && j < blankNum) {
					System.out.print("\t\t");
					continue;
				}

				if (count >= length) {
					break;
				}

				// 打印日期信息
				DayWrapper dayWrapper = days.get(count);
				System.out.print(dayWrapper.getDay() + "(");//天
				System.out.print(dayWrapper.getLocalHoliday());//公历节假日，会打印上面定义的 建军节
				System.out.print(dayWrapper.getLunarDay());//农历天
				System.out.print(dayWrapper.getChineseHoliday());//农历节假日，会打印上面定义的 七夕情人节
				System.out.print(dayWrapper.getSolarTerm());//二十四节气
				//是否工作日，1是，0否，默认周一到周五为工作日，周六周日为非工作日，
				//2020-08-07周五本为工作日，这里特别设置为非工作日，2020-08-08周六本为非工作日，这里特别设置为工作日，会重新设置
				System.out.print(dayWrapper.getDateType());
				System.out.print(")\t\t");
				count++;
			}
			System.out.println();
		}
	}
}
