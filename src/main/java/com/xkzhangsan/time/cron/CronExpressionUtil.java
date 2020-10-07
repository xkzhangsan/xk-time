package com.xkzhangsan.time.cron;

import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;
import com.xkzhangsan.time.utils.CollectionUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Cron表达式工具
 * <p>
 * Cron表达式从左到右（用空格隔开）：秒（0~59） 分（0~59） 小时（0~23） 日期（1~31） 月份（1~12的整数或者 JAN-DEC） 星期（1~7的整数或者 SUN-SAT （1=SUN）） 年份（可选，1970~2099）<br>
 * 所有字段均可使用特殊字符：, - * / 分别是枚举，范围，任意，间隔<br>
 * 日期另外可使用：? L W 分别是任意，最后，有效工作日(周一到周五)<br>
 * 星期另外可使用：? L # 分别是任意，最后，每个月第几个星期几<br>
 * 常用cron表达式：<br>
 * （1）0 0 2 1 * ? *   表示在每月的1日的凌晨2点触发<br>
 * （2）0 15 10 ? * MON-FRI   表示周一到周五每天上午10:15执行作业<br>
 * （3）0 15 10 ? * 6L 2002-2006   表示2002-2006年的每个月的最后一个星期五上午10:15执行作<br>
 * （4）0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时<br>
 * （5）0 15 10 L * ?    每月最后一日的上午10:15触发<br>
 * （6）0 15 10 ? * 6#3   每月的第三个星期五上午10:15触发<br>
 * </p>
 * <p>
 * Cron表达式工具包含<br>
 * 1.验证和格式化Cron表达式方法，isValidExpression和formatExpression<br>
 * 2.生成下一个或多个执行时间方法，getNextTime和getNextTimeList<br>
 * 3.生成下一个或多个执行时间的日期格式化（yyyy-MM-dd HH:mm:ss）方法，getNextTimeStr和getNextTimeStrList<br>
 * 4.对比Cron表达式下一个执行时间是否与指定date相等方法，isSatisfiedBy<br>
 * </p>
* @author xkzhangsan
*
*使用 quartz CronExpression
 */
public class CronExpressionUtil {

	private CronExpressionUtil(){
	}
	
	/**
	 * 验证Cron表达式
	 * @param cronExpression Cron表达式
	 * @return boolean
	 */
	public static boolean isValidExpression(String cronExpression){
		return CronExpression.isValidExpression(cronExpression);
	}
	
	/**
	 * 格式化Cron表达式
	 * @param cronExpression Cron表达式
	 * @return String
	 */
	public static String formatExpression(String cronExpression){
		try {
			return new CronExpression(cronExpression).toString();
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 生成下一个执行时间
	 * @param cronExpression Cron表达式
	 * @param date 日期
	 * @return Date
	 */
	public static Date getNextTime(String cronExpression, Date date){
		try {
			if(date != null){
				return new CronExpression(cronExpression).getNextValidTimeAfter(date);
			}else{
				return new CronExpression(cronExpression).getNextValidTimeAfter(new Date());
			}
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 生成下一个执行时间
	 * @param cronExpression Cron表达式
	 * @return Date
	 */
	public static Date getNextTime(String cronExpression){
		return getNextTime(cronExpression, null);
	}
	
	/**
	 * 生成下一个执行时间的日期格式化
	 * @param cronExpression Cron表达式
	 * @param date 日期
	 * @return String 返回格式化时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String getNextTimeStr(String cronExpression, Date date){
		return DateTimeFormatterUtil.formatToDateTimeStr(getNextTime(cronExpression, date));
	}
	
	/**
	 * 生成下一个执行时间的日期格式化
	 * @param cronExpression Cron表达式
	 * @return String 返回格式化时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String getNextTimeStr(String cronExpression){
		return getNextTimeStr(cronExpression, null);
	}
	
	/**
	 * 生成多个执行时间
	 * @param cronExpression Cron表达式
	 * @param date 日期
	 * @param num 数量
	 * @return 时间列表
	 */
	public static List<Date> getNextTimeList(String cronExpression, Date date, int num){
		List<Date> dateList = new ArrayList<>();
		if(num < 1){
			throw new RuntimeException("num must be greater than 0");
		}
		Date startDate = date != null ? date : new Date();
		for(int i=0; i<num; i++){
			startDate = getNextTime(cronExpression, startDate);
			if(startDate != null){
				dateList.add(startDate);
			}
		}
		return dateList;
	}
	
	/**
	 * 生成多个执行时间
	 * @param cronExpression Cron表达式
	 * @param num 数量
	 * @return  时间列表
	 */
	public static List<Date> getNextTimeList(String cronExpression, int num){
		return getNextTimeList(cronExpression, null, num);
	}
	
	/**
	 * 生成多个执行时间的日期格式化
	 * @param cronExpression Cron表达式
	 * @param date 日期
	 * @param num 数量
	 * @return  返回格式化时间列表 yyyy-MM-dd HH:mm:ss
	 */
	public static List<String> getNextTimeStrList(String cronExpression, Date date, int num){
		List<String> dateStrList = new ArrayList<>();
		List<Date> dateList = getNextTimeList(cronExpression, date, num);
		if(CollectionUtil.isNotEmpty(dateList)){
			dateList.stream().forEach(d->{
				String dateStr = DateTimeFormatterUtil.formatToDateTimeStr(d);
				dateStrList.add(dateStr);
			});
		}
		return dateStrList;
	}
	
	/**
	 * 生成多个执行时间的日期格式化
	 * @param cronExpression Cron表达式
	 * @param num 数量
	 * @return 返回格式化时间列表 yyyy-MM-dd HH:mm:ss
	 */
	public static List<String> getNextTimeStrList(String cronExpression, int num){
		return getNextTimeStrList(cronExpression, null, num);
	}
	
	/**
	 * 对比Cron表达式下一个执行时间是否与指定date相等
	 * @param cronExpression Cron表达式
	 * @param date 日期
	 * @return boolean
	 */
	public static boolean isSatisfiedBy(String cronExpression, Date date){
		try {
			return new CronExpression(cronExpression).isSatisfiedBy(date);
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
