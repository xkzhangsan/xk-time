package com.xkzhangsan.time.test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

/**
 * 日期计算，性能测试类
* @ClassName: CalculatorPerformanceTest 
* @Description: CalculatorPerformanceTest
* @author xkzhangsan
* @date 2020年4月22日
 */
public class CalculatorPerformanceTest {

	/**
	 * 使用Format方法减少精度到天
	 */
	@Test
	public void reduceAccuracyFormat(){
		LocalDateTime ldt = LocalDateTime.now();
		LocalDateTime ldt2 = null;
		long s = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			ldt2 = DateTimeFormatterUtil.parseDateStrToLocalDateTime(DateTimeFormatterUtil.formatToDateStr(ldt));
		}
		System.out.println("reduceAccuracyFormat cost:"+(System.currentTimeMillis()-s));
		System.out.println("reduceAccuracyFormat result:"+ldt2);
	}
	
	/**
	 * 使用Of方法减少精度到天
	 */
	@Test
	public void reduceAccuracyOf(){
		LocalDateTime ldt = LocalDateTime.now();
		LocalDateTime ldt2 = null;
		long s = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			ldt2 = LocalDateTime.of(ldt.getYear(), ldt.getMonth().getValue(), ldt.getDayOfMonth(), 0, 0);
		}
		System.out.println("reduceAccuracyOf cost:"+(System.currentTimeMillis()-s));
		System.out.println("reduceAccuracyOf result:"+ldt2);
	}
	
	/**
	 * 使用With方法减少精度到天
	 */
	@Test
	public void reduceAccuracyWith(){
		LocalDateTime ldt = LocalDateTime.now();
		LocalDateTime ldt2 = null;
		long s = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			ldt2 = ldt.withHour(0).withMinute(0).withSecond(0).withNano(0);
		}
		System.out.println("reduceAccuracyWith cost:"+(System.currentTimeMillis()-s));
		System.out.println("reduceAccuracyWith result:"+ldt2);
	}
	
	/**
	 * 使用System获取时间戳
	 */
	@Test
	public void getEpochMilliWithSystem(){
		long s = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			System.currentTimeMillis();
		}
		System.out.println("getEpochMilliWithSystem cost:"+(System.currentTimeMillis()-s));
	}
	
	/**
	 * 使用Date获取时间戳
	 */
	@Test
	public void getEpochMilliWithDate(){
		long s = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			new Date().getTime();
		}
		System.out.println("getEpochMilliWithDate cost:"+(System.currentTimeMillis()-s));
	}
	
	/**
	 * 使用Calendar获取时间戳
	 */
	@Test
	public void getEpochMilliWithCalendar(){
		long s = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			Calendar.getInstance().getTimeInMillis();
		}
		System.out.println("getEpochMilliWithDate cost:"+(System.currentTimeMillis()-s));
	}
	
	/**
	 * 使用Instant获取时间戳
	 */
	@Test
	public void getEpochMilliWithInstant(){
		long s = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			Instant.now().toEpochMilli();
		}
		System.out.println("getEpochMilliWithInstant cost:"+(System.currentTimeMillis()-s));
	}
	
	/**
	 * 使用Clock获取时间戳
	 */
	@Test
	public void getEpochMilliWithClock(){
		long s = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			Clock.systemUTC().millis();
		}
		System.out.println("getEpochMilliWithClock cost:"+(System.currentTimeMillis()-s));
	}
	
}
