package com.xkzhangsan.time.constants;

/**
 * 时间单位常量 ，方便计算，比如设置缓存时间 3天：  3*MILLISECONDS_PER_DAY （每天毫秒数 24*60*60*1000）<br>
 *  包含：<br>
 *	1.基本单位换算数值，比如 MILLISECONDS_PER_SECONDS 每秒毫秒数 1000。<br>
 *	2.转换为秒数基本数值，比如 SECONDS_PER_DAY 每天秒数 24*60*60。<br>
 *	3.转换为毫秒基本数值，比如 MILLISECONDS_PER_DAY 每天毫秒数 24*60*60*1000。<br>
 *
 * @author xkzhangsan
 */
public class XkTimeConstant {
	
	//===========================基本单位换算数值============================
	/**
	 * 每周天数 7
	 */
	public static final int DAYS_PER_WEEK = 7;
	
	/**
	 * 每天小时数 24
	 */
	public static final int HOURS_PER_DAY = 24;
	
	/**
	 * 每小时分钟数 60
	 */
	public static final int MINUTES_PER_HOUR = 60;
	
	/**
	 * 每分钟秒数 60
	 */
	public static final int SECONDS_PER_MINUTE = 60;
	
	/**
	 * 每秒毫秒数 1000
	 */
	public static final int MILLISECONDS_PER_SECONDS = 1000;
	
	//===========================转换为秒数基本数值============================
	
	/**
	 * 每小时秒数 60*60
	 */
	public static final int SECONDS_PER_HOUR = MINUTES_PER_HOUR * SECONDS_PER_MINUTE;
	
	/**
	 * 每天秒数 24*60*60
	 */
	public static final int SECONDS_PER_DAY = HOURS_PER_DAY * SECONDS_PER_HOUR;
	
	/**
	 * 每周秒数 7*24*60*60
	 */
	public static final int SECONDS_PER_WEEK = DAYS_PER_WEEK * SECONDS_PER_DAY;
	
	//===========================转换为毫秒基本数值============================
	/**
	 * 每分钟毫秒数 60*1000
	 */
	public static final int MILLISECONDS_PER_MINUTE = SECONDS_PER_MINUTE * MILLISECONDS_PER_SECONDS;
	
	/**
	 * 每小时毫秒数 60*60*1000
	 */
	public static final int MILLISECONDS_PER_HOUR = MINUTES_PER_HOUR * MILLISECONDS_PER_MINUTE;
	
	/**
	 * 每天毫秒数 24*60*60*1000
	 */
	public static final int MILLISECONDS_PER_DAY = HOURS_PER_DAY * MILLISECONDS_PER_HOUR;
	
	/**
	 * 每星期毫秒数 7*24*60*60*1000
	 */
	public static final int MILLISECONDS_PER_WEEK = DAYS_PER_WEEK * MILLISECONDS_PER_DAY;
	
}
