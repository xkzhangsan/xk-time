package com.xkzhangsan.time.calculator.adjuster;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * 
* @ClassName: NextWorkDayAdjuster 
* @Description: 计算下一个工作日
* @author xkzhangsan
* @date 2019年12月1日
*
 */
public final class NextWorkDayAdjuster implements TemporalAdjuster{

	@Override
	public Temporal adjustInto(Temporal temporal) {
		DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
		int add = 1;
		if (dayOfWeek == DayOfWeek.FRIDAY){
			add = 3;
		}
		if (dayOfWeek == DayOfWeek.SATURDAY){
			add = 2;
		}
		return temporal.plus(add, ChronoUnit.DAYS);
	}

}
