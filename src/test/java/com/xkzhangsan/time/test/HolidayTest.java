package com.xkzhangsan.time.test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.xkzhangsan.time.holiday.Holiday;

public class HolidayTest {

	/**
	 * 公历节日，母亲节
	 */
	@Test
	public void localHolidayEnumTest(){
		LocalDate localDate = LocalDate.of(2020, 5, 10);
		System.out.println(Holiday.getLocalHoliday(localDate));
		
		//自定义节日数据
		Map<String, String> localHolidayMap = new HashMap<String, String>();
		localHolidayMap.put("0422", "世界地球日");
		LocalDate localDate2 = LocalDate.of(2020, 4, 22);
		System.out.println(Holiday.getLocalHoliday(localDate2, localHolidayMap));
	}
	
	/**
	 * 农历节日，除夕
	 */
	@Test
	public void chineseHolidayEnumTest(){
		LocalDate localDate = LocalDate.of(2020, 1, 24);
		System.out.println(Holiday.getChineseHoliday(localDate));
	}
	
	/**
	 * 二十四节气，2020-08-07 立秋
	 */
	@Test
	public void solarTermEnumTest(){
		LocalDate localDate = LocalDate.of(2020, 8, 7);
		System.out.println(Holiday.getSolarTerm(localDate));
	}
}
