package com.xkzhangsan.time.calendar;

import java.io.Serializable;
import java.util.List;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;

/**
 * 年
 * 
 * @ClassName: YearWrapper
 * @Description: YearWrapper
 * @author xkzhangsan
 * @date 2020年03月18日
 */
public class YearWrapper implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int year;
	
	private List<MonthWrapper> months;
	
	private boolean isLeapYear;

	public YearWrapper(int year, List<MonthWrapper> months) {
		super();
		this.year = year; 
		this.months = months;
		this.isLeapYear = DateTimeCalculatorUtil.isLeapYear(year);
	}


	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<MonthWrapper> getMonths() {
		return months;
	}
	
	public void setMonths(List<MonthWrapper> months) {
		this.months = months;
	}
	
	public boolean isLeapYear() {
		return isLeapYear;
	}

	public void setLeapYear(boolean isLeapYear) {
		this.isLeapYear = isLeapYear;
	}
}
