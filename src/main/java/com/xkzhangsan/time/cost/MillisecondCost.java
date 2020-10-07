package com.xkzhangsan.time.cost;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * 计算耗时，单位毫秒<br>
 * 
* @author xkzhangsan
 */
public final class MillisecondCost implements Cost{
	
	private final String name;
	
	private final long nanosecond;
	
	public MillisecondCost() {
		super();
		this.name = "";
		this.nanosecond = System.nanoTime();
	}
	
	public MillisecondCost(String name) {
		super();
		this.name = name;
		this.nanosecond = System.nanoTime();
	}
	
	@Override
	public long stop() {
		return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanosecond);
	}

	@Override
	public void stopAndPrint() {
		System.out.println(stopAndFormat());
	}

	@Override
	public String stopAndFormat() {
		return (this.name + " cost: " + stop() + " ms");
	}

	@Override
	public String stopAccurate() {
		double result= (System.nanoTime() - nanosecond)/1000_000.0;
		return new BigDecimal(result).setScale(3, BigDecimal.ROUND_DOWN).toString();
	}

	@Override
	public void stopAccurateAndPrint() {
		System.out.println(stopAccurateAndFormat());
	}

	@Override
	public String stopAccurateAndFormat() {
		return (this.name + " cost: " + stopAccurate() + " ms");
	}

}
