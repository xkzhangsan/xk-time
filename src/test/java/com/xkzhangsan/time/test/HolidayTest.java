package com.xkzhangsan.time.test;

import java.time.LocalDate;

import org.junit.Test;

import com.xkzhangsan.time.holiday.ChineseHolidayEnum;
import com.xkzhangsan.time.holiday.LocalHolidayEnum;

public class HolidayTest {

	/**
	 * 公历节日，母亲节
	 */
	@Test
	public void localHolidayEnumTest(){
		LocalDate localDate = LocalDate.of(2020, 5, 10);
		LocalHolidayEnum holiday = LocalHolidayEnum.getHoliday(localDate);
		System.out.println(holiday.getName());
	}
	
	/**
	 * 农历节日，除夕
	 */
	@Test
	public void chineseHolidayEnumTest(){
		LocalDate localDate = LocalDate.of(2020, 1, 24);
		ChineseHolidayEnum holiday = ChineseHolidayEnum.getHoliday(localDate);
		System.out.println(holiday.getName());
	}
}
