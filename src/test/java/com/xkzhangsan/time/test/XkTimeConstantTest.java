package com.xkzhangsan.time.test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.xkzhangsan.time.constants.XkTimeConstant;

public class XkTimeConstantTest {

	@Test
	public void xkTimeConstantTest(){
		//===========================基本单位换算数值============================
		Assert.assertEquals(TimeUnit.DAYS.toHours(1), XkTimeConstant.HOURS_PER_DAY);
		Assert.assertEquals(TimeUnit.HOURS.toMinutes(1), XkTimeConstant.MINUTES_PER_HOUR);
		Assert.assertEquals(TimeUnit.MINUTES.toSeconds(1), XkTimeConstant.SECONDS_PER_MINUTE);
		Assert.assertEquals(TimeUnit.SECONDS.toMillis(1), XkTimeConstant.MILLISECONDS_PER_SECONDS);
		
		//===========================转换为秒数基本数值============================
		Assert.assertEquals(TimeUnit.HOURS.toSeconds(1), XkTimeConstant.SECONDS_PER_HOUR);
		Assert.assertEquals(TimeUnit.DAYS.toSeconds(1), XkTimeConstant.SECONDS_PER_DAY);
		Assert.assertEquals(7*TimeUnit.DAYS.toSeconds(1), XkTimeConstant.SECONDS_PER_WEEK);
		
		//===========================转换为毫秒基本数值============================
		Assert.assertEquals(TimeUnit.MINUTES.toMillis(1), XkTimeConstant.MILLISECONDS_PER_MINUTE);
		Assert.assertEquals(TimeUnit.HOURS.toMillis(1), XkTimeConstant.MILLISECONDS_PER_HOUR);
		Assert.assertEquals(TimeUnit.DAYS.toMillis(1), XkTimeConstant.MILLISECONDS_PER_DAY);
		Assert.assertEquals(7*TimeUnit.DAYS.toMillis(1), XkTimeConstant.MILLISECONDS_PER_WEEK);
	}
	
}
