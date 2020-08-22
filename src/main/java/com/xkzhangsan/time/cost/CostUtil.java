package com.xkzhangsan.time.cost;

/**
 * 计算耗时工具，支持秒，毫秒，纳秒
 * <p>包括:
 * <p>1.计算耗时，返回耗时结果
 * <pre>
 *		Cost cost = CostUtil.startMillisecondCost();
 *		TimeUnit.MILLISECONDS.sleep(1000);
 *		long millisecond = cost.stop();
 * <pre>
 * <p>2.计算耗时，自定义任务名称，输出耗时结果
 * <pre>
 *		Cost cost = CostUtil.startMillisecondCost("millisecondCostTest");
 *		TimeUnit.MILLISECONDS.sleep(1000);
 *		cost.stopAndPrint();
 *		// millisecondCostTest cost: 1000 ms
 * <pre>
 * 
 * @author xkzhangsan
 * @date 2020年8月8日
 */
public class CostUtil {
	
	private CostUtil(){
	}
	
	/**
	 * 开始计时，单位纳秒，返回cost对象
	 * @return 返回cost对象
	 */
	public static Cost startNanosecondCost(){
		return new NanosecondCost();
	}
	
	/**
	 * 开始计时，单位纳秒，返回cost对象
	 * @param name
	 * @return
	 */
	public static Cost startNanosecondCost(String name){
		return new NanosecondCost(name);
	}
	
	/**
	 * 开始计时，单位毫秒，返回cost对象
	 * @return 返回cost对象
	 */
	public static Cost startMillisecondCost(){
		return new MillisecondCost();
	}
	
	/**
	 * 开始计时，单位毫秒，返回cost对象
	 * @param name 任务名称
	 * @return 返回cost对象
	 */
	public static Cost startMillisecondCost(String name){
		return new MillisecondCost(name);
	}
	
	/**
	 * 开始计时，单位秒，返回cost对象
	 * @return 返回cost对象
	 */
	public static Cost startSecondCost(){
		return new SecondCost();
	}
	
	/**
	 * 开始计时，单位秒，返回cost对象
	 * @param name 任务名称
	 * @return 返回cost对象
	 */
	public static Cost startSecondCost(String name){
		return new SecondCost(name);
	}
}
