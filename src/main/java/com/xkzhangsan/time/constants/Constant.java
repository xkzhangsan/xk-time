package com.xkzhangsan.time.constants;


import java.time.LocalTime;
import java.time.MonthDay;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 常量
 *
 * @author xkzhangsan
 */
public class Constant {
	
	//===========================数值定义============================
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
	
	//===========================异常定义============================
	/**
	 * 解析日期时异常
	 */
	public static final String PARSE_LOCAL_DATE_EXCEPTION = "Unable to obtain";
	
	//===========================正则定义============================
	
	/**
	 * 纯数字
	 */
	public static final Pattern NUMERIC_REGEX = Pattern.compile("[0-9]+");
	
	/**
	 * 字母开头
	 */
	public static final Pattern START_WITH_WORD_REGEX = Pattern.compile("^[A-Za-z].*");
	
	/**
	 * 中文
	 */
	public static final Pattern CHINESE_REGEX = Pattern.compile("[\u4E00-\u9FFF]");
	
	//===========================时间定义============================
	
	/**
	 * 1点
	 */
	public static final LocalTime ONECLOCK = LocalTime.of(1, 0, 0);
	
	/**
	 * 23点
	 */
	public static final LocalTime TWENTYTHREECLOCK = LocalTime.of(23, 0, 0);
	
	/**
	 * MonthDay 默认解析前缀
	 * 解析字符串需要加前缀，如"--12-03"
	 * @see java.time.MonthDay#parse(CharSequence)
	 */
	public static final String MONTHDAY_FORMAT_PRE = "--";

	/**
	 * 月日的起始值 01-01
	 */
	public static final MonthDay MONTH_DAY_START = MonthDay.parse(MONTHDAY_FORMAT_PRE +"01-01");
	
	/**
	 * 月日的终止值 12-31
	 */
	public static final MonthDay MONTH_DAY_END = MonthDay.parse(MONTHDAY_FORMAT_PRE +"12-31");
	
	/**
	 * 摩羯座的起始日期 12-22
	 */
	public static final MonthDay CAPRICORN_START = MonthDay.parse(MONTHDAY_FORMAT_PRE +"12-22");
	
	/**
	 * 摩羯座的终止日期 01-19
	 */
	public static final MonthDay CAPRICORN_END = MonthDay.parse(MONTHDAY_FORMAT_PRE +"01-19");
	
	
	
	/**
	 * TimeNLP 默认解析超时时间 3s
	 * 
	 */
	public static final long TIME_NLP_TIMEOUT = 3000;	
	//===========================其他定义============================
	
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
	
	//===========================常用方法============================
	
	/**
	 * 是否为中文语言环境
	 * @return boolean
	 */
	public static boolean isChineseLanguage(){
		return Locale.getDefault().getLanguage().equals(ZH);
	}
	


}
