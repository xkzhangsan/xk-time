package com.xkzhangsan.time.test;

import com.xkzhangsan.time.holiday.Holiday;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * 节假日测试类
 * @author xkzhangsan
 */
public class HolidayTest {

	/**
	 * 公历节日，母亲节
	 */
	@Test
	public void localHolidayEnumTest(){
		LocalDate localDate = LocalDate.of(2020, 5, 10);
		Assert.assertEquals("母亲节", Holiday.getLocalHoliday(localDate));
		
		//自定义节日数据
		Map<String, String> localHolidayMap = new HashMap<String, String>();
		localHolidayMap.put("0422", "世界地球日");
		LocalDate localDate2 = LocalDate.of(2020, 4, 22);
		Assert.assertEquals("世界地球日",Holiday.getLocalHoliday(localDate2, localHolidayMap));
	}
	
	/**
	 * 农历节日，除夕
	 */
	@Test
	public void chineseHolidayEnumTest(){
		LocalDate localDate = LocalDate.of(2020, 1, 24);
		Assert.assertEquals("除夕",Holiday.getChineseHoliday(localDate));
		
		//正常农历九月初九
		LocalDate localDate2 = LocalDate.of(2014, 10, 2);
		Assert.assertEquals("重阳节",Holiday.getChineseHoliday(localDate2));
		
		//正常农历闰九月初九  闰月不算节假日
		LocalDate localDate3 = LocalDate.of(2014, 11, 1);
		Assert.assertEquals("",Holiday.getChineseHoliday(localDate3));
	}
	
	/**
	 * 二十四节气，2020-08-07 立秋
	 */
	@Test
	public void solarTermEnumTest(){
		LocalDate localDate = LocalDate.of(2020, 8, 7);
		Assert.assertEquals("立秋",Holiday.getSolarTerm(localDate));
	}
}
