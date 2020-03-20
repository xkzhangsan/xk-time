package com.xkzhangsan.time.calendar;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 日历
 * 
 * @ClassName: CalendarWrapper
 * @Description: CalendarWrapper
 * @author xkzhangsan
 * @date 2020年03月18日
 */
public class CalendarWrapper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<YearWrapper> years;
	
	private Map<String, DayWrapper> dayMap = new ConcurrentHashMap<String, DayWrapper>();
	
	public CalendarWrapper() {
		super();
	}
	
	public CalendarWrapper(List<YearWrapper> years, Map<String, DayWrapper> dayMap) {
		super();
		this.years = years;
		this.dayMap = dayMap;
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
}
