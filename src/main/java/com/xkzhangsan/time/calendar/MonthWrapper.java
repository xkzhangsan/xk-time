package com.xkzhangsan.time.calendar;

import java.io.Serializable;
import java.util.List;

/**
 * 月
 * 
 * @ClassName: MonthWrapper
 * @Description: MonthWrapper
 * @author xkzhangsan
 * @date 2020年03月18日
 */
public class MonthWrapper implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int month;
	
	private List<DayWrapper> days;
	
	public MonthWrapper(int month, List<DayWrapper> days) {
		super();
		this.month = month;
		this.days = days;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
	
	public List<DayWrapper> getDays() {
		return days;
	}
	
	public void setDays(List<DayWrapper> days) {
		this.days = days;
	}
}
