package com.xkzhangsan.time.cost;

/**
 * 计算耗时工具，支持秒，毫秒，纳秒<br>
 * 
 * <pre>
 * 		Cost cost = CostUtil.startMillisecondCost();
		TimeUnit.MILLISECONDS.sleep(1000);
		long millisecond = cost.stop();
		
		Cost cost2 = CostUtil.startMillisecondCost("millisecondCostTest");
		TimeUnit.MILLISECONDS.sleep(1000);
		cost2.stopAndPrint();
		// millisecondCostTest cost: 1000 ms
 * <pre>
 * 
* @author xkzhangsan
* @date 2020年8月8日
 */
public class CostUtil {
	
	private CostUtil(){
	}
	
	public static Cost startNanosecondCost(){
		return new NanosecondCost();
	}
	
	public static Cost startNanosecondCost(String name){
		return new NanosecondCost(name);
	}
	
	public static Cost startMillisecondCost(){
		return new MillisecondCost();
	}
	
	public static Cost startMillisecondCost(String name){
		return new MillisecondCost(name);
	}
	
	public static Cost startSecondCost(){
		return new SecondCost();
	}
	
	public static Cost startSecondCost(String name){
		return new SecondCost(name);
	}
}
