package com.xkzhangsan.time.calendar;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.utils.CollectionUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 月
 * 
 * @author xkzhangsan
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
	
	/**
	 * 获取月份中文简称， 比如一
	 */
	private String monthCnShort;
	
	/**
	 * 获取月份中文全称， 比如一月
	 */
	private String monthCnLong;

	/**
	 * 获取月英文简称， 比如 Jan
	 */
	private String monthEnShort;
	
	/**
	 * 获取月英文简称大写， 比如 JAN
	 */
	private String monthEnShortUpper;
	
	/**
	 * 获取月英文全称， 比如 January
	 */
	private String monthEnLong;	
	
	public MonthWrapper(int month, List<DayWrapper> days, int length) {
		super();
		this.month = month;
		this.days = days;
		this.length = length;
		if(CollectionUtil.isNotEmpty(days)){
			DayWrapper day = days.get(0);
			if(day != null){
				this.monthCnShort = DateTimeCalculatorUtil.getMonthCnShort(day.getLocalDateTime());
				this.monthCnLong = DateTimeCalculatorUtil.getMonthCnLong(day.getLocalDateTime());
				this.monthEnShort = DateTimeCalculatorUtil.getMonthEnShort(day.getLocalDateTime());
				this.monthEnShortUpper = DateTimeCalculatorUtil.getMonthEnShortUpper(day.getLocalDateTime());
				this.monthEnLong = DateTimeCalculatorUtil.getMonthEnLong(day.getLocalDateTime());
			}
		}
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

	public String getMonthCnShort() {
		return monthCnShort;
	}

	public void setMonthCnShort(String monthCnShort) {
		this.monthCnShort = monthCnShort;
	}

	public String getMonthCnLong() {
		return monthCnLong;
	}

	public void setMonthCnLong(String monthCnLong) {
		this.monthCnLong = monthCnLong;
	}

	public String getMonthEnShort() {
		return monthEnShort;
	}

	public void setMonthEnShort(String monthEnShort) {
		this.monthEnShort = monthEnShort;
	}

	public String getMonthEnShortUpper() {
		return monthEnShortUpper;
	}

	public void setMonthEnShortUpper(String monthEnShortUpper) {
		this.monthEnShortUpper = monthEnShortUpper;
	}

	public String getMonthEnLong() {
		return monthEnLong;
	}

	public void setMonthEnLong(String monthEnLong) {
		this.monthEnLong = monthEnLong;
	}
}
