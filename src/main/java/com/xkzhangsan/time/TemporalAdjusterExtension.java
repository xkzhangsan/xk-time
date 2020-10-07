package com.xkzhangsan.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;

/**
 * TemporalAdjuster 扩展
* @author xkzhangsan
*
 */
public final class TemporalAdjusterExtension {

	private TemporalAdjusterExtension() {
	}

	/**
	 * 下一个工作日
	 * next work day
	 * @return TemporalAdjuster
	 */
	public static TemporalAdjuster nextWorkDay(){
		return (temporal) -> {
			DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
			int add = 1;
			if (dayOfWeek == DayOfWeek.FRIDAY){
				add = 3;
			}
			if (dayOfWeek == DayOfWeek.SATURDAY){
				add = 2;
			}
			return temporal.plus(add, ChronoUnit.DAYS);
		};
	}
	
}
