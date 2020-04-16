package com.xkzhangsan.time.test;

import org.junit.Test;

import com.xkzhangsan.time.cron.CronExpressionUtil;

public class CronExpressionTest {

	/**
	 * Cron表达式工具测试
	 */
	@Test
	public void cronExpressionTest(){
		//验证cron
		System.out.println(CronExpressionUtil.isValidExpression("* * * * * ?"));
		//格式化cron
		System.out.println(CronExpressionUtil.formatExpression("* * * * * ? * "));
		//下一个运行时间
		System.out.println(CronExpressionUtil.getNextTimeStr("10 * * * * ?"));
		//多个下一个运行时间
		System.out.println(CronExpressionUtil.getNextTimeStrList("10 * * * * ?", 10));
	}
}
