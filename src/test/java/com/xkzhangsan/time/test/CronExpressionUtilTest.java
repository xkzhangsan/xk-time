package com.xkzhangsan.time.test;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.cron.CronExpressionUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * cron表达式测试类
* @author xkzhangsan
*/
public class CronExpressionUtilTest {

	/**
	 * Cron表达式工具测试
	 */
	@Test
	public void cronExpressionTest(){
		//验证cron,0 0 2 1 * ? *   表示在每月的1日的凌晨2点触发
		Assert.assertEquals(true, CronExpressionUtil.isValidExpression("0 0 2 1 * ? *"));
		//格式化cron
		Assert.assertEquals(" * * * * * ? *",CronExpressionUtil.formatExpression(" * * * * * ? *"));
		//下一个运行时间
		System.out.println(CronExpressionUtil.getNextTimeStr("10 * * * * ?"));
		//多个下一个运行时间
		System.out.println(CronExpressionUtil.getNextTimeStrList("10 * * * * ?", 10));
		//对比Cron表达式下一个执行时间是否与指定date相等
		Date date = new Date();
		String cronExpression = "0 10 * * * ?";
		Date nextDate = CronExpressionUtil.getNextTime(cronExpression, date);
		Assert.assertEquals(true,CronExpressionUtil.isSatisfiedBy(cronExpression, nextDate));
	}
	
	/**
	 * 特殊周期cron，不能被字段范围整除的周期值，比如：每隔40s执行，需要多个cron组合使用
	 */
	@Test
	public void cronExpressionSpecialPeriodTest(){
		List<Date> dateList = new ArrayList<Date>();
		dateList.addAll(CronExpressionUtil.getNextTimeList("0 0/2 * * * ? ", 3));
		dateList.addAll(CronExpressionUtil.getNextTimeList("40 0/2 * * * ? ", 3));
		dateList.addAll(CronExpressionUtil.getNextTimeList("20 1/2 * * * ? ", 3));
		Collections.sort(dateList);
		for(int i=0; i<dateList.size()-1; i++){
			Assert.assertEquals(40, DateTimeCalculatorUtil.betweenTotalSeconds(dateList.get(i),dateList.get(i+1)));
		}
	}
}
