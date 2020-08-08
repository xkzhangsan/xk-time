package com.xkzhangsan.time.test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.xkzhangsan.time.cost.Cost;
import com.xkzhangsan.time.cost.CostUtil;

/**
 * 计算耗时测试类
 * @author xkzhangsan
 * @date 2020年8月8日
 */
public class CostUitlTest {
	
	/**
	 * 计算耗时，秒
	 * @throws InterruptedException 
	 */
	@Test
	public void secondCostTest() throws InterruptedException{
		Cost cost = CostUtil.startSecondCost();
		TimeUnit.SECONDS.sleep(1);
		Assert.assertEquals(1, cost.stop());
		
		Cost cost2 = CostUtil.startSecondCost("secondCostTest");
		TimeUnit.SECONDS.sleep(1);
		cost2.stopAndPrint();
	}
	
	/**
	 * 计算耗时，毫秒
	 * @throws InterruptedException 
	 */
	@Test
	public void millisecondCostTest() throws InterruptedException{
		Cost cost = CostUtil.startMillisecondCost();
		TimeUnit.MILLISECONDS.sleep(1000);
		Assert.assertTrue(cost.stop()-1000<=3);
		
		Cost cost2 = CostUtil.startMillisecondCost("millisecondCostTest");
		TimeUnit.MILLISECONDS.sleep(1000);
		cost2.stopAndPrint();
	}
	
	/**
	 * 计算耗时，纳秒
	 * @throws InterruptedException 
	 */
	@Test
	public void nanosecondTest() throws InterruptedException{
		Cost cost = CostUtil.startNanosecondCost();
		TimeUnit.NANOSECONDS.sleep(1000_000_000);
		Assert.assertTrue(cost.stop()-1000_000_000<=3_000_000);
		
		Cost cost2 = CostUtil.startNanosecondCost();
		TimeUnit.NANOSECONDS.sleep(1000_000_000);
		cost2.stopAndPrint();
	}
}
