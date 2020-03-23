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

	private static final long serialVersionUID = 6688876063027489849L;

	/**
	 * 月
	 */
	private int month;
	
	/**
	 * 当月包含的所有天
	 */
	private List<DayWrapper> days;
	
	/**
	 * 当前月包含天数
	 */
	private int length;
	
	public MonthWrapper(int month, List<DayWrapper> days, int length) {
		super();
		this.month = month;
		this.days = days;
		this.length = length;
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
