package com.xkzhangsan.time.calendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 日历
 * 
 * @author xkzhangsan
 */
public class CalendarWrapper implements Serializable{
	
	private static final long serialVersionUID = -4287759184630652424L;

	/**
	 * 日历中所有的年 
	 */
	private List<YearWrapper> years;
	
	/**
	 * 日历中所有的天map，方便快速访问，key 格式：yyyy-MM-dd
	 */
	private Map<String, DayWrapper> dayMap = new ConcurrentHashMap<String, DayWrapper>();
	
	/**
	 * 日历中所有的天list，方便顺序遍历访问
	 */
	private List<DayWrapper> dayList = new ArrayList<DayWrapper>();
	
	public CalendarWrapper() {
		super();
	}
	
	public CalendarWrapper(List<YearWrapper> years, Map<String, DayWrapper> dayMap, List<DayWrapper> dayList) {
		super();
		this.years = years;
		this.dayMap = dayMap;
		this.dayList = dayList;
	}

	public CalendarWrapper(List<YearWrapper> years) {
		super();
		this.years = years;
	}

	public List<YearWrapper> getYears() {
		return years;
	}

	public void setYears(List<YearWrapper> years) {
		this.years = years;
	}

	public Map<String, DayWrapper> getDayMap() {
		return dayMap;
	}

	public void setDayMap(Map<String, DayWrapper> dayMap) {
		this.dayMap = dayMap;
	}

	public List<DayWrapper> getDayList() {
		return dayList;
	}

	public void setDayList(List<DayWrapper> dayList) {
		this.dayList = dayList;
	}
}
