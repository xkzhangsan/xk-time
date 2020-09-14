package com.xkzhangsan.time.cost;

/**
 * 计算耗时<br>
 * 
* @author xkzhangsan
* @date 2020年8月8日
 */
public interface Cost {

	/**
	 * 停止计时，返回计时结果
	 * @return 返回计时结果
	 */
	long stop();
	
	/**
	 * 停止计时，并打印计时结果
	 * <p>例如:  name cost: 1000 ms, 使用System.out.println
	 */
	void stopAndPrint();
	
	/**
	 * 停止计时，并返回组装结果
	 * <p>例如:  name cost: 1000 ms
	 */
	String stopAndFormat();
	
	/**
	 * 停止计时，返回计时结果，带小数的结果
	 * @return 返回带小数的结果
	 */
	String stopAccurate();
}
