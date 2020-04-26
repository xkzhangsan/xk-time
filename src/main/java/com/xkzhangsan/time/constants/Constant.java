package com.xkzhangsan.time.constants;

import java.util.Locale;

public class Constant {
	
	/**
	 * 每天小时数
	 */
	public static final int HOURS_PER_DAY = 24;
	
	/**
	 * 每小时分钟数
	 */
	public static final int MINUTES_PER_HOUR = 60;
	
	/**
	 * 每分钟秒数
	 */
	public static final int SECONDS_PER_MINUTE = 60;
	
	/**
	 * 每秒毫秒数
	 */
	public static final int MILLISECOND_PER_SECONDS = 1000;
	
	/**
	 * 每天秒数
	 */
	public static final int SECONDS_PER_DAY = HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE;
	
	/**
	 * 每天毫秒数
	 */
	public static final int MILLISECOND_PER_DAY = SECONDS_PER_DAY * MILLISECOND_PER_SECONDS;
	
	/**
	 * MonthDay 默认解析前缀
	 * 解析字符串需要加前缀，如"--12-03"
	 * @see java.time.MonthDay.parse(CharSequence)
	 */
	public static final String MONTHDAY_FORMAT_PRE = "--";
	
	/**
	 * 中文
	 */
	public static final String ZH = "zh";
	
	/**
	 * 除夕，节日处理使用
	 */
	public static final String CHUXI = "CHUXI";
	
	/**
	 * 春节，节日处理使用
	 */
	public static final String CHUNJIE = "0101";
	
	/**
	 * 解析日期时异常
	 */
	public static final String PARSE_LOCAL_DATE_EXCEPTION = "Unable to obtain";
	
	private static volatile Constant constant;
	
	private Constant(){
	}

	public static Constant getInstance(){
		if(constant == null){
			synchronized(Constant.class){
				if(constant == null){
					constant = new Constant();
				}
			}
		}
		return constant;
	}
	
	/**
	 * 是否为中文
	 * @return
	 */
	public boolean isChinese(){
		return Locale.getDefault().getLanguage().equals(ZH);
	}

}
