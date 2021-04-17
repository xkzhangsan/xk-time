package com.xkzhangsan.time.test;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.xkzhangsan.time.LunarDate;
import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

public class LunarDateTest {

	@Test
	public void lunarDateTest(){
		//今天农历
		Assert.assertNotNull(LunarDate.now());
		
		//of(int lYear, int lMonth, int lDay)
		Assert.assertEquals("二〇二一年三月初四", LunarDate.of(2021,3,4).getlDateCn());
		//of(int lYear, int lMonth, int lDay, boolean isLeapMonth) 闰月
		Assert.assertEquals("二〇二〇年闰四月十五", LunarDate.of(2020, 4, 15, true).getlDateCn());
		
		//公历转农历
		//from(Date date)
		Assert.assertEquals("二〇二一年三月初四", LunarDate.from(DateTimeCalculatorUtil.getDate(2021, 4, 15)).getlDateCn());
		//from(LocalDate localDate)
		Assert.assertEquals("二〇二一年三月初四", LunarDate.from(LocalDate.of(2021, 4, 15)).getlDateCn());
		
		//农历转公历
		Assert.assertEquals("2020-06-06", DateTimeFormatterUtil.formatToDateStr(LunarDate.of(2020, 4, 15, true).getDate()));
		
		//二十四节气
		Assert.assertEquals("谷雨", LunarDate.from(LocalDate.of(2021, 4, 20)).getSolarTerm());
		Assert.assertEquals("2021-04-20", DateTimeFormatterUtil.formatToDateStr(LunarDate.solarTermToDate(2021, 7)));
	}
}
