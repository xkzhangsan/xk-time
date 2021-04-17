package com.xkzhangsan.time;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.holiday.Holiday;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * 农历日期<br>
 * 1.农历日期年月日计算<br>
 * 2.农历岁次，生肖属相计算<br>
 * 3.二十四节气计算<br>
 * 4.农历转公历<br>
 * 仅支持公历1900-2100年的农历转换<br>
 *
* @author xkzhangsan
 */
public final class LunarDate implements Temporal, Serializable{
	
	private static final long serialVersionUID = 7999322619343295974L;

	/**
	 * 农历信息,1900-2100的润大小信息表
	 * https://github.com/jjonline/calendar.js/blob/master/calendar.js
	 */
	private static final long[] lunarInfo = new long[] {
			0x04bd8,0x04ae0,0x0a570,0x054d5,0x0d260,0x0d950,0x16554,0x056a0,0x09ad0,0x055d2,//1900-1909
            0x04ae0,0x0a5b6,0x0a4d0,0x0d250,0x1d255,0x0b540,0x0d6a0,0x0ada2,0x095b0,0x14977,//1910-1919
            0x04970,0x0a4b0,0x0b4b5,0x06a50,0x06d40,0x1ab54,0x02b60,0x09570,0x052f2,0x04970,//1920-1929
            0x06566,0x0d4a0,0x0ea50,0x16a95,0x05ad0,0x02b60,0x186e3,0x092e0,0x1c8d7,0x0c950,//1930-1939
            0x0d4a0,0x1d8a6,0x0b550,0x056a0,0x1a5b4,0x025d0,0x092d0,0x0d2b2,0x0a950,0x0b557,//1940-1949
            0x06ca0,0x0b550,0x15355,0x04da0,0x0a5b0,0x14573,0x052b0,0x0a9a8,0x0e950,0x06aa0,//1950-1959
            0x0aea6,0x0ab50,0x04b60,0x0aae4,0x0a570,0x05260,0x0f263,0x0d950,0x05b57,0x056a0,//1960-1969
            0x096d0,0x04dd5,0x04ad0,0x0a4d0,0x0d4d4,0x0d250,0x0d558,0x0b540,0x0b6a0,0x195a6,//1970-1979
            0x095b0,0x049b0,0x0a974,0x0a4b0,0x0b27a,0x06a50,0x06d40,0x0af46,0x0ab60,0x09570,//1980-1989
            0x04af5,0x04970,0x064b0,0x074a3,0x0ea50,0x06b58,0x05ac0,0x0ab60,0x096d5,0x092e0,//1990-1999
            0x0c960,0x0d954,0x0d4a0,0x0da50,0x07552,0x056a0,0x0abb7,0x025d0,0x092d0,0x0cab5,//2000-2009
            0x0a950,0x0b4a0,0x0baa4,0x0ad50,0x055d9,0x04ba0,0x0a5b0,0x15176,0x052b0,0x0a930,//2010-2019
            0x07954,0x06aa0,0x0ad50,0x05b52,0x04b60,0x0a6e6,0x0a4e0,0x0d260,0x0ea65,0x0d530,//2020-2029
            0x05aa0,0x076a3,0x096d0,0x04afb,0x04ad0,0x0a4d0,0x1d0b6,0x0d250,0x0d520,0x0dd45,//2030-2039
            0x0b5a0,0x056d0,0x055b2,0x049b0,0x0a577,0x0a4b0,0x0aa50,0x1b255,0x06d20,0x0ada0,//2040-2049
            0x14b63,0x09370,0x049f8,0x04970,0x064b0,0x168a6,0x0ea50, 0x06b20,0x1a6c4,0x0aae0,//2050-2059
            0x092e0,0x0d2e3,0x0c960,0x0d557,0x0d4a0,0x0da50,0x05d55,0x056a0,0x0a6d0,0x055d4,//2060-2069
            0x052d0,0x0a9b8,0x0a950,0x0b4a0,0x0b6a6,0x0ad50,0x055a0,0x0aba4,0x0a5b0,0x052b0,//2070-2079
            0x0b273,0x06930,0x07337,0x06aa0,0x0ad50,0x14b55,0x04b60,0x0a570,0x054e4,0x0d160,//2080-2089
            0x0e968,0x0d520,0x0daa0,0x16aa6,0x056d0,0x04ae0,0x0a9d4,0x0a2d0,0x0d150,0x0f252,//2090-2099
            0x0d520};//2100

	/**
	 * 农历月份列表
	 */
	public static final String[] lunarMonth = new String[] { "", "正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬",
			"腊" };
	
	/**
	 * 天干列表
	 */
	private static final String[] tianGan = new String[] { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };

	/**
	 * 地支列表
	 */
	private static final String[] diZhi = new String[] { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };

	/**
	 * 生肖列表
	 */
	private static final String[] animals = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };

	/**
	 * 中文数字0-9
	 */
	public static final String[] numStr = new String[] { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九"};	

	
	/**
	 * 二十四节气
	 */
	public static final String[] solarTerms = new String[] {"小寒","大寒","立春","雨水","惊蛰","春分","清明","谷雨","立夏","小满","芒种","夏至","小暑","大暑","立秋","处暑","白露","秋分","寒露","霜降","立冬","小雪","大雪","冬至"};
	
	
	/**
	 * 二十四节气数据
	 */
	private static final long[] solarTermInfo = new long[] {0,21208,42467,63836,85337,107014,128867,150921,173149,195551,218072,240693,263343,285989,308563,331033,353350,375494,397447,419210,440795,462224,483532,504758};
	
	/**
	 * 标准日期
	 */
	private final LocalDate localDate;
	
	/**
	 * 标准日期
	 */
	private Date date;

	/**
	 * 农历日期，中文，例如 二〇二〇年正月初一
	 */
	private String lDateCn;
	
	/**
	 * 农历日期，数字，例如：2020-01-01
	 */
	private String lDate;
	
	/**
	 * 公历日期，数字，例如：2020-01-01
	 */
	private String gDate;
	
	/**
	 * 岁次
	 */
	private String suiCi;

	/**
	 * 生肖
	 */
	private String lAnimal;

	/**
	 * 农历年
	 */
	private int lYear;

	/**
	 * 农历月
	 */
	private int lMonth;

	/**
	 * 农历日
	 */
	private int lDay;
	
	/**
	 * 农历年
	 */
	private int gYear;

	/**
	 * 农历月
	 */
	private int gMonth;

	/**
	 * 农历日
	 */
	private int gDay;	

	/**
	 * 农历年，中文
	 */
	private String lYearCn;

	/**
	 * 农历月，中文
	 */
	private String lMonthCn;

	/**
	 * 农历日，中文
	 */
	private String lDayCn;

	/**
	 * 星期，中文
	 */
	private String weekCn;
	
	/**
	 * 二十四节气 
	 */
	private String solarTerm;
	
	/**
	 * 当前日期月份是否为闰月，是 为"闰"
	 */
	private String leapMonthCn;

	private LunarDate(LocalDate localDate) {
		super();
		this.localDate = localDate;
		
		try {
			initialize();
		} catch (Exception e) {
			System.err.println("new LunarDate has error: " + e.getMessage());
		}
	}
	
	/**
	 * 初始化农历日期
	 */
	public void initialize() {
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		this.gYear = year;
		this.gMonth = month;
		this.gDay = day;
		this.date = DateTimeConverterUtil.toDate(localDate);
		
		long[] l = calElement(year, month, day);
		
		this.lYear = (int) l[0];
		this.lMonth = (int) l[1];
		this.lDay = (int) l[2];
		
		this.suiCi = cyclical(this.lYear);
		this.lAnimal = animalsYear(this.lYear);


		this.lYearCn = getChinaYear(this.lYear);
		this.lMonthCn = lunarMonth[this.lMonth];
		this.lDayCn = getChinaDay(this.lDay);

		this.weekCn = getWeekCn(localDate.getDayOfWeek().getValue());
		if(l[7] != -1){
			this.solarTerm = solarTerms[(int)l[7]];
		}else{
			this.solarTerm = "";
		}
		if(l[6] == 1){
			this.leapMonthCn = "闰";
		}else{
			this.leapMonthCn = "";
		}
		this.lDateCn = this.lYearCn + "年" + this.leapMonthCn + this.lMonthCn + "月" + this.lDayCn;
		this.lDate = String.format("%04d", lYear) +"-"+String.format("%02d", lMonth) +"-"+ String.format("%02d", lDay);
		this.gDate = this.localDate.toString();
	}
	
	/**
	 * 通过LocalDateTime创建LunarDate
	 * @param localDateTime LocalDateTime
	 * @return LunarDate
	 */
	public static LunarDate from(LocalDateTime localDateTime) {
		return new LunarDate(DateTimeConverterUtil.toLocalDate(localDateTime));
	}

	/**
	 * 通过LocalDate创建LunarDate
	 * @param localDate LocalDate
	 * @return LunarDate
	 */
	public static LunarDate from(LocalDate localDate) {
		return new LunarDate(localDate);
	}
	
	/**
	 * 通过Instant创建LunarDate
	 * @param instant Instant
	 * @return LunarDate
	 */
	public static LunarDate from(Instant instant) {
		return new LunarDate(DateTimeConverterUtil.toLocalDate(instant));
	}	

	/**
	 * 通过Date创建LunarDate
	 * @param date Date
	 * @return LunarDate
	 */
	public static LunarDate from(Date date) {
		return new LunarDate(DateTimeConverterUtil.toLocalDate(date));
	}
	
	public static LunarDate from(Temporal temporal) {
		return new LunarDate(DateTimeConverterUtil.toLocalDate(temporal));
	}
	
	/**
	 * 根据农历年月日生成农历日期
	 * 
	 * @param lYear
	 *            农历年
	 * @param lMonth
	 *            农历月
	 * @param lDay
	 *            农历日
	 * @return LunarDate
	 */
	public static LunarDate of(int lYear, int lMonth, int lDay){
		return of(lYear, lMonth, lDay, false);
	}
	
	/**
	 * 根据农历年月日生成农历日期
	 * 
	 * @param lYear
	 *            农历年
	 * @param lMonth
	 *            农历月
	 * @param lDay
	 *            农历日
	 * @param isLeapMonth
	 *            是否是农历闰月
	 * @return LunarDate
	 */
	public static LunarDate of(int lYear, int lMonth, int lDay, boolean isLeapMonth){
		return from(lunarToSolar(lYear, lMonth, lDay, isLeapMonth));
	}
	
	/**
	 * 农历转公历日期
	 * 
	 * @param lYear
	 *            农历年
	 * @param lMonth
	 *            农历月
	 * @param lDay
	 *            农历日
	 * @param isLeapMonth
	 *            是否是农历闰月
	 * @return Date
	 */
	public static Date lunarToSolar(int lYear, int lMonth, int lDay, boolean isLeapMonth) {
		int leapMonth = leapMonth(lYear);

		// 传参要求计算该闰月公历 但该年得出的闰月与传参的月份并不同
		if (isLeapMonth && (leapMonth != lMonth)) {
			return null;
		}

		// 超出了最大极限值
		if (lYear == 2100 && lMonth == 12 && lDay > 1 || lYear == 1900 && lMonth == 1 && lDay < 31) {
			return null;
		}

		int day = monthDays(lYear, lMonth);
		int _day = day;
		if (isLeapMonth) {
			_day = leapMonthDays(lYear);
		}

		// 参数合法性效验
		if (lYear < 1900 || lYear > 2100 || lDay > _day) {
			return null;
		}

		// 计算农历的时间差
		int offset = 0;
		for (int i = 1900; i < lYear; i++) {
			offset += lunarYearDays(i);
		}
		int leap = 0;
		boolean isAdd = false;
		for (int i = 1; i < lMonth; i++) {
			leap = leapMonth(lYear);
			if (!isAdd) {// 处理闰月
				if (leap <= i && leap > 0) {
					offset += leapMonthDays(lYear);
					isAdd = true;
				}
			}
			offset += monthDays(lYear, i);
		}

		// 转换闰月农历 需补充该年闰月的前一个月的时差
		if (isLeapMonth) {
			offset += day;
		}

		// 2203804800000L为1900年农历正月一日的公历时间为1900年1月30日0时0分0秒(该时间也是本农历的最开始起始点)的毫秒值
		return new Date((offset + lDay - 31) * 86400000L - 2203804800000L);
	}
	
	/**
	 * 二十节气转公历日期
	 * @param year 公历年
	 * @param index 第一个节气，小寒 为第一个
	 * @return Date
	 */
    public static Date solarTermToDate(int year, int index){
    	int month=index/2 +1;
    	int day = solarTerm(year, index);
		return DateTimeCalculatorUtil.getDate(year, month, day);
    }	

	/**
	 * 传回农历year年的总天数
	 *
	 * @param year
	 * @return int
	 */
	private static final int lunarYearDays(int year) {
		int i, sum = 348;
		for (i = 0x8000; i > 0x8; i >>= 1) {
			if ((lunarInfo[year - 1900] & i) != 0)
				sum += 1;
		}
		return (sum + leapMonthDays(year));
	}

	/**
	 * 传回农历 year年闰月的天数
	 *
	 * @param year
	 * @return int
	 */
	private static final int leapMonthDays(int year) {
		if (leapMonth(year) != 0) {
			if ((lunarInfo[year - 1900] & 0x10000) != 0)
				return 30;
			else
				return 29;
		} else
			return 0;
	}

	/**
	 * 传回农历 year年闰哪个月 1-12 , 没闰传回 0
	 *
	 * @param year
	 * @return int
	 */
	private static final int leapMonth(int year) {
		return (int) (lunarInfo[year - 1900] & 0xf);
	}

	/**
	 * 传回农历 year年month月的总天数
	 *
	 * @param year
	 * @param month
	 * @return int
	 */
	private static final int monthDays(int year, int month) {
		if ((lunarInfo[year - 1900] & (0x10000 >> month)) == 0)
			return 29;
		else
			return 30;
	}

	/**
	 * 传回农历 year年的生肖
	 *
	 * @param year 年
	 * @return String
	 */
	public static final String animalsYear(int year) {
		return animals[(year - 4) % 12];
	}

	/**
	 * 传入 月日的offset 传回干支,0=甲子
	 *
	 * @param num 月日的offset
	 * @return String
	 */
	private static final String cyclicalm(int num) {
		return (tianGan[num % 10] + diZhi[num % 12]);
	}

	/**
	 * 传入 offset 传回干支, 0=甲子
	 *
	 * @param year 年
	 * @return String
	 */
	public static final String cyclical(int year) {
		int num = year - 1900 + 36;
		return (cyclicalm(num));
	}
	
	/**
	 * 计算某年第n个节气的天
	 * @param year 公历年
	 * @param n 第n个节气
	 * @return int
	 */
	public static final int solarTerm(int year, int n){
		LocalDateTime startLocalDateTime = LocalDateTime.of(1900,1,6,2,5);
		long millis = (long) ((31556925974.7*(year-1900) + solarTermInfo[n]*60000));
		LocalDateTime tempLocalDateTime = DateTimeCalculatorUtil.plusMillis(startLocalDateTime, millis);
		return tempLocalDateTime.getDayOfMonth();
	}

	/**
	 * 传出year年month月day日对应的农历.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5
	 * .isLeap6.solarTermIndex7
	 *
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return long[]
	 */
	public static final long[] calElement(int year, int month, int day) {
		long[] nongDate = new long[8];
		int i = 0, temp = 0, leap = 0;
		LocalDateTime baseDate = LocalDate.of(1900, 1, 31).atStartOfDay();
		LocalDateTime objDate = LocalDate.of(year, month, day).atStartOfDay();
		long offset = DateTimeCalculatorUtil.betweenTotalDays(baseDate, objDate);		
		nongDate[5] = offset + 40;
		nongDate[4] = 14;
		for (i = 1900; i <= 2100 && offset > 0; i++) {
			temp = lunarYearDays(i);
			offset -= temp;
			nongDate[4] += 12;
		}
		if (offset < 0) {
			offset += temp;
			i--;
			nongDate[4] -= 12;
		}
		nongDate[0] = i;
		nongDate[3] = i - 1864;
		leap = leapMonth(i); // 闰哪个月
		nongDate[6] = 0;
		for (i = 1; i < 13 && offset > 0; i++) {
			// 闰月
			if (leap > 0 && i == (leap + 1) && nongDate[6] == 0) {
				--i;
				nongDate[6] = 1;
				temp = leapMonthDays((int) nongDate[0]);
			} else {
				temp = monthDays((int) nongDate[0], i);
			}
			// 解除闰月
			if (nongDate[6] == 1 && i == (leap + 1))
				nongDate[6] = 0;
			offset -= temp;
			if (nongDate[6] == 0)
				nongDate[4]++;
		}
		if (offset == 0 && leap > 0 && i == leap + 1) {
			if (nongDate[6] == 1) {
				nongDate[6] = 0;
			} else {
				nongDate[6] = 1;
				--i;
				--nongDate[4];
			}
		}
		if (offset < 0) {
			offset += temp;
			--i;
			--nongDate[4];
		}
		nongDate[1] = i;
		nongDate[2] = offset + 1;
		
		//二十四节气
		int solarTermIndex = -1;
		int tempMonth = month - 1;
		int firstSolarTermOfMonth = solarTerm(year, tempMonth*2);
		int secondSolarTermOfMonth = solarTerm(year, tempMonth*2+1);
		if(day == firstSolarTermOfMonth){
			solarTermIndex = tempMonth*2;
		}else if(day == secondSolarTermOfMonth){
			solarTermIndex = tempMonth*2 + 1;
		}
		nongDate[7] = solarTermIndex;
		return nongDate;
	}

	/**
	 * 获取农历中文年
	 * @param year 年
	 * @return String
	 */
	public final static String getChinaYear(int year) {
		String ge = numStr[year % 10];
		String shi = numStr[year / 10 % 10];
		String bai = numStr[year / 100 % 10];
		String qian = numStr[year / 1000 % 10];
		return qian + bai + shi + ge;
	}
	/**
	 * 获取农历中文日期
	 * @param day 日
	 * @return String
	 */
	public final static String getChinaDay(int day) {
		String a = "";
		if (day == 10)
			return "初十";
		if (day == 20)
			return "二十";
		if (day == 30)
			return "三十";
		int two = (int) ((day) / 10);
		if (two == 0)
			a = "初";
		if (two == 1)
			a = "十";
		if (two == 2)
			a = "廿";
		if (two == 3)
			a = "三";
		int one = (int) (day % 10);
		switch (one) {
		case 1:
			a += "一";
			break;
		case 2:
			a += "二";
			break;
		case 3:
			a += "三";
			break;
		case 4:
			a += "四";
			break;
		case 5:
			a += "五";
			break;
		case 6:
			a += "六";
			break;
		case 7:
			a += "七";
			break;
		case 8:
			a += "八";
			break;
		case 9:
			a += "九";
			break;
		default:
			a += "";
			break;
		}
		return a;
	}
	
	/**
	 * 获取中文星期
	 * @param week 星期
	 * @return String
	 */
	public final static String getWeekCn(int week) {
		String weekCn = "";
		switch (week) {
		case 1:
			weekCn = "星期一";
			break;
		case 2:
			weekCn = "星期二";
			break;
		case 3:
			weekCn = "星期三";
			break;
		case 4:
			weekCn = "星期四";
			break;
		case 5:
			weekCn = "星期五";
			break;
		case 6:
			weekCn = "星期六";
			break;
		case 7:
			weekCn = "星期日";
			break;
		default:
			weekCn = "";
			break;
		}
		return weekCn;
	}

	/**
	 * 以当前时间创建农历日期LunarDate
	 * @return LunarDate
	 */
	public static LunarDate now() {
		LocalDate today = LocalDate.now();
		return new LunarDate(today);
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public String getlDateCn() {
		return lDateCn;
	}

	public String getSuiCi() {
		return suiCi;
	}

	public String getlAnimal() {
		return lAnimal;
	}

	public int getlYear() {
		return lYear;
	}

	public int getlMonth() {
		return lMonth;
	}

	public int getlDay() {
		return lDay;
	}

	public String getlYearCn() {
		return lYearCn;
	}

	public String getlMonthCn() {
		return lMonthCn;
	}

	public String getlDayCn() {
		return lDayCn;
	}

	public String getWeekCn() {
		return weekCn;
	}
	
	public String getSolarTerm() {
		return solarTerm;
	}

	public String getLeapMonthCn() {
		return leapMonthCn;
	}
	
	public String getlDate() {
		return lDate;
	}

	public void setlDate(String lDate) {
		this.lDate = lDate;
	}

	public String getgDate() {
		return gDate;
	}

	public void setgDate(String gDate) {
		this.gDate = gDate;
	}

	public int getgYear() {
		return gYear;
	}

	public void setgYear(int gYear) {
		this.gYear = gYear;
	}

	public int getgMonth() {
		return gMonth;
	}

	public void setgMonth(int gMonth) {
		this.gMonth = gMonth;
	}

	public int getgDay() {
		return gDay;
	}

	public void setgDay(int gDay) {
		this.gDay = gDay;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "LunarDate [localDate=" + localDate + ",lDateCn=" + lDateCn + ", suiCi=" + suiCi + ", lAnimal=" + lAnimal + ", lYear=" + lYear
				+ ", lMonth=" + lMonth + ", lDay=" + lDay + ", lYearCn=" + lYearCn + ", lMonthCn=" + lMonthCn
				+ ", lDayCn=" + lDayCn + ", weekCn=" + weekCn + ", solarTerm=" + solarTerm + ", leapMonthCn=" + leapMonthCn + "]";
	}

	/**
	 * 格式化输出，如：庚子鼠年 二〇二〇年正月初一 星期六 春节
	 * @return String
	 */
	public String formatLongCnWithChineseHoliday(){
		String chineseHoliday = Holiday.getChineseHoliday(localDate);
		if(solarTerm != ""){
			chineseHoliday = chineseHoliday + " " + solarTerm;
		}
		return suiCi + lAnimal + "年 " + lDateCn + " " + weekCn + " " + chineseHoliday;
	}	
	
	/**
	 * 格式化输出，如： 己亥猪年 二〇一九年腊月初六 星期二
	 * @return String
	 */
	public String formatLongCn(){
		return suiCi + lAnimal + "年 " + lDateCn + " " + weekCn;
	}
	
	/**
	 * 格式化输出，如： 0101
	 * @return String
	 */
	public String formatShort(){
		return String.format("%02d", lMonth) + String.format("%02d", lDay);
	}

	@Override
	public boolean isSupported(TemporalField field) {
		return localDate.isSupported(field);
	}

	@Override
	public long getLong(TemporalField field) {
		return localDate.getLong(field);
	}

	@Override
	public boolean isSupported(TemporalUnit unit) {
		return localDate.isSupported(unit);
	}

	@Override
	public Temporal with(TemporalField field, long newValue) {
		return localDate.with(field, newValue);
	}

	@Override
	public Temporal plus(long amountToAdd, TemporalUnit unit) {
		return localDate.plus(amountToAdd, unit);
	}

	@Override
	public long until(Temporal endExclusive, TemporalUnit unit) {
		return localDate.until(endExclusive, unit);
	}
	
}
