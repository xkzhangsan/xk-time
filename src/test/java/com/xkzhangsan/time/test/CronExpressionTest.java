package com.xkzhangsan.time.test;

import java.util.Date;

import org.junit.Test;

import com.xkzhangsan.time.cron.CronExpressionUtil;

public class CronExpressionTest {

	/**
	 * Cron表达式工具测试
	 */
	@Test
	public void cronExpressionTest(){
		//验证cron,0 0 2 1 * ? *   表示在每月的1日的凌晨2点触发
		System.out.println(CronExpressionUtil.isValidExpression("0 0 2 1 * ? *"));
		//格式化cron
		System.out.println(CronExpressionUtil.formatExpression(" * * * * * ? *"));
		//下一个运行时间
		System.out.println(CronExpressionUtil.getNextTimeStr("10 * * * * ?"));
		//多个下一个运行时间
		System.out.println(CronExpressionUtil.getNextTimeStrList("10 * * * * ?", 10));
		//对比Cron表达式下一个执行时间是否与指定date相等
		Date date = new Date();
		String cronExpression = "0 10 * * * ?";
		Date nextDate = CronExpressionUtil.getNextTime(cronExpression, date);
		System.out.println(CronExpressionUtil.isSatisfiedBy(cronExpression, nextDate));
	}
}
