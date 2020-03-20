package com.xkzhangsan.time.calendar;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.constants.Constant;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

/**
 * 日
 * 
 * @ClassName: DayWrapper
 * @Description: DayWrapper
 * @author xkzhangsan
 * @date 2020年03月18日
 */
public class DayWrapper implements Serializable{

	private static final long serialVersionUID = 1L;

	private Date date;
	
	private LocalDateTime localDateTime;
	
	private String dateStr;
	
	private int day;
	
	private String weekStr;
	
	private Object obj;

	public DayWrapper(LocalDateTime localDateTime) {
		this(localDateTime, null);
	}

	public DayWrapper(LocalDateTime localDateTime, Object obj) {
		super();
		this.localDateTime = localDateTime;
		this.date = DateTimeConverterUtil.toDate(localDateTime);
		this.dateStr = DateTimeFormatterUtil.formatToDateStr(localDateTime);
		this.day = localDateTime.getDayOfMonth();
		if(Constant.getInstance().isChinese()){
			this.weekStr = DateTimeCalculatorUtil.getDayOfWeekCn(localDateTime);
		}else{
			this.weekStr = DateTimeCalculatorUtil.getDayOfWeekEnLong(localDateTime);
		}
		this.obj = obj;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getWeekStr() {
		return weekStr;
	}

	public void setWeekStr(String weekStr) {
		this.weekStr = weekStr;
	}

}
