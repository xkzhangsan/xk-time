package com.xkzhangsan.time.test;

import java.time.LocalDateTime;

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
}
