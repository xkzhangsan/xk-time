package com.xkzhangsan.time.holiday;

import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 公历常见假日
 * 
* @author xkzhangsan
 */
public enum LocalHolidayEnum implements Holiday{

	NEW_YEAR_DAY("元旦", "0101"),
    VALENTINE_DAY("情人节", "0214"),
    WOMEN_DAY("妇女节", "0308"),
    ARBOR_DAY("植树节", "0312"),
    WORLD_CONSUMER_RIGHTS_DAY("消费者权益日", "0315"),
    APRIL_FOOL_DAY("愚人节", "0401"),
    INTERNATIONAL_WORKERS_DAY("劳动节", "0501"),
    CHINA_YOUTH_DAY("青年节", "0504"),
    NURSES_DAY("护士节", "0512"),
    /**
     * 5-W-2-7
     * 含义：5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天。
     * 5月的第二个星期天
     */
    MOTHER_DAY("母亲节", "5-W-2-7"),
    CHILDREN_DAY("儿童节", "0601"),
    /**
     * 6-W-3-7
     * 含义：6表示6月，W表示星期，3表示第3个星期，7表示星期的第7天。
     * 6月的第3个星期天
     */    
    FATHER_DAY("父亲节", "6-W-3-7"),
    JIANDANGJIE("建党节", "0701"),
    JIANJUNJIE("建军节", "0801"),
    TEACHER_DAY("教师节", "0910"),
    GUOQINGJIE("国庆节", "1001"),
    ALL_SAINTS_DAY("万圣节", "1101"),
    CHRISTMAS("圣诞节", "1225"),
    /**
     * 默认值
     */
    DEFAULT_HOLIDAY("", ""),;
	
	private LocalHolidayEnum(String name, String pattern) {
		this.name = name;
		this.pattern = pattern;
	}

	/**
	 * 中文名称
	 */
	private final String name;
	/**
	 * 匹配模式
	 */
	private final String pattern;


	public String getName() {
		return name;
	}
	public String getPattern() {
		return pattern;
	}
	
	/**
	 * 根据时间获取节日枚举
	 * @param temporal Temporal
	 * @return LocalHolidayEnum
	 */
	@Deprecated
	public static LocalHolidayEnum getHoliday(Temporal temporal) {
		Objects.requireNonNull(temporal, "temporal");
		MonthDay monthDay = MonthDay.from(temporal);
		String monthDayStr = monthDay.format(DateTimeFormatterUtil.MMDD_FMT);
		//对比枚举日期，返回假日
		for (LocalHolidayEnum localHolidayEnum : LocalHolidayEnum.values()) {
			if (localHolidayEnum.getPattern().equals(monthDayStr)) {
				return localHolidayEnum;
			}
			//如果为特殊格式，解析对比
			if (localHolidayEnum.getPattern().contains("W")) {
				String[] arr = localHolidayEnum.getPattern().split("-");
				int month = Integer.parseInt(arr[0]);
				int weekIndex = Integer.parseInt(arr[2]);
				int weekValue = Integer.parseInt(arr[3]);
				DayOfWeek dow = DayOfWeek.of(weekValue);
				//设置到当前节日的月份
				Temporal tempTem = temporal.with(ChronoField.MONTH_OF_YEAR, month);
				//设置到当前节日的第几星期第几天
				Temporal targetTem = tempTem.with(TemporalAdjusters.dayOfWeekInMonth(weekIndex, dow));
				MonthDay targetMonthDay = MonthDay.from(targetTem);
				String targetMonthDayStr = targetMonthDay.format(DateTimeFormatterUtil.MMDD_FMT);
				if (monthDayStr.equals(targetMonthDayStr)) {
					return localHolidayEnum;
				}
			}
		}
		return DEFAULT_HOLIDAY;
	}
	
	/**
	 * 根据时间获取节日名称
	 * @param temporal Temporal
	 * @return String
	 */
	public static String getHolidayName(Temporal temporal) {
		return Holiday.getLocalHoliday(temporal, convertToMap());
	}
	
	/**
	 * 对比月日
	 * @param temporal Temporal
	 * @param monthDay 月日字符串
	 * @return boolean
	 */
	public static boolean compareMonthDay(Temporal temporal, String monthDay){
		Objects.requireNonNull(temporal, "temporal");
		Objects.requireNonNull(monthDay, "monthDay");
		MonthDay monthDay1 = MonthDay.from(temporal);
		String monthDayStr = monthDay1.format(DateTimeFormatterUtil.MMDD_FMT);
		if(monthDayStr.equals(monthDay)){
			return true;
		}
		return false;
	}
	
	public static Map<String, String> convertToMap(){
		Map<String, String> localHolidayMap = new HashMap<String, String>();
		for (LocalHolidayEnum localHolidayEnum : LocalHolidayEnum.values()) {
			localHolidayMap.put(localHolidayEnum.getPattern(), localHolidayEnum.getName());
		}
		return localHolidayMap;
	}
	
}
