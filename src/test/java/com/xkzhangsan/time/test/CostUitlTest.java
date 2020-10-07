package com.xkzhangsan.time.test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.xkzhangsan.time.cost.Cost;
import com.xkzhangsan.time.cost.CostUtil;

/**
 * 计算耗时测试类
 * @author xkzhangsan
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
		
		Cost cost3 = CostUtil.startSecondCost();
		TimeUnit.MILLISECONDS.sleep(1500);
		Assert.assertTrue(Double.parseDouble(cost3.stopAccurate())-1.0>0);
		
		Cost cost4 = CostUtil.startSecondCost("secondCostTest2");
		TimeUnit.MILLISECONDS.sleep(1500);
		cost4.stopAccurateAndPrint();
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
		
		Cost cost3 = CostUtil.startMillisecondCost();
		TimeUnit.NANOSECONDS.sleep(1000_500_000);
		Assert.assertTrue(Double.parseDouble(cost3.stopAccurate())-1000>0);
		
		Cost cost4 = CostUtil.startMillisecondCost("millisecondCostTest2");
		TimeUnit.NANOSECONDS.sleep(1000_500_000);
		cost4.stopAccurateAndPrint();
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
		
		Cost cost2 = CostUtil.startNanosecondCost("nanosecondTest");
		TimeUnit.NANOSECONDS.sleep(1000_000_000);
		cost2.stopAndPrint();
		
		Cost cost3 = CostUtil.startNanosecondCost();
		TimeUnit.NANOSECONDS.sleep(1000_000_000);
		Assert.assertTrue(cost3.stop()-1000_000_000<=3_000_000);
		
		Cost cost4 = CostUtil.startNanosecondCost("nanosecondTest2");
		TimeUnit.NANOSECONDS.sleep(1000_000_000);
		cost4.stopAccurateAndPrint();
	}
}
