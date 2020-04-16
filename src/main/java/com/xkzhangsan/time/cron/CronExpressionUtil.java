package com.xkzhangsan.time.cron;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;
import com.xkzhangsan.time.utils.CollectionUtil;

/**
 * Cron表达式工具
 * 包含
 * 1.验证和格式化Cron表达式方法，isValidExpression和formatExpression
 * 2.生成下一个或多个执行时间方法，getNextTime和getNextTimeList
 * 3.生成下一个或多个执行时间的日期格式化（yyyy-MM-dd HH:mm:ss）方法，getNextTimeStr和getNextTimeStrList
 * 
* @ClassName: CronExpressionUtil 
* @Description: CronExpressionUtil
* @author xkzhangsan
* @date 2020年4月16日
*
*使用 quartz CronExpression
 */
public class CronExpressionUtil {

	private CronExpressionUtil(){
	}
	
	/**
	 * 验证Cron表达式
	 * @param cronExpression
	 * @return
	 */
	public static boolean isValidExpression(String cronExpression){
		return CronExpression.isValidExpression(cronExpression);
	}
	
	/**
	 * 格式化Cron表达式
	 * @param cronExpression
	 * @return
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
	 * @param cronExpression
	 * @param date
	 * @return
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
	 * @param cronExpression
	 * @return
	 */
	public static Date getNextTime(String cronExpression){
		return getNextTime(cronExpression, null);
	}
	
	/**
	 * 生成下一个执行时间的日期格式化
	 * @param cronExpression
	 * @param date
	 * @return 返回格式化时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String getNextTimeStr(String cronExpression, Date date){
		return DateTimeFormatterUtil.formatToDateTimeStr(getNextTime(cronExpression, date));
	}
	
	/**
	 * 生成下一个执行时间的日期格式化
	 * @param cronExpression
	 * @return 返回格式化时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String getNextTimeStr(String cronExpression){
		return getNextTimeStr(cronExpression, null);
	}
	
	/**
	 * 生成多个执行时间
	 * @param cronExpression
	 * @param date
	 * @param num
	 * @return
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
	 * @param cronExpression
	 * @param num
	 * @return
	 */
	public static List<Date> getNextTimeList(String cronExpression, int num){
		return getNextTimeList(cronExpression, null, num);
	}
	
	/**
	 * 生成多个执行时间的日期格式化
	 * @param cronExpression
	 * @param date
	 * @param num
	 * @return 返回格式化时间 yyyy-MM-dd HH:mm:ss
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
	 * @param cronExpression
	 * @param num
	 * @return 返回格式化时间 yyyy-MM-dd HH:mm:ss
	 */
	public static List<String> getNextTimeStrList(String cronExpression, int num){
		return getNextTimeStrList(cronExpression, null, num);
	}

}
