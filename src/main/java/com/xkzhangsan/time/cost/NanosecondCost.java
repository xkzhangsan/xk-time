package com.xkzhangsan.time.cost;

/**
 * 计算耗时，单位纳秒<br>
 * 
* @author xkzhangsan
* @date 2020年8月8日
 */
public final class NanosecondCost implements Cost{
	
	private final String name;
	
	private final long nanosecond;
	
	public NanosecondCost() {
		super();
		this.name = "";
		this.nanosecond = System.nanoTime();
	}
	
	public NanosecondCost(String name) {
		super();
		this.name = name;
		this.nanosecond = System.nanoTime();
	}

	@Override
	public long stop() {
		return System.nanoTime() - nanosecond;
	}

	@Override
	public void stopAndPrint() {
		System.out.println(stopAndFormat());
	}

	@Override
	public String stopAndFormat() {
		return (this.name + " cost: " + stop() + " ns");
	}

	@Override
	public String stopAccurate() {
		return String.valueOf(stop());
	}

	@Override
	public void stopAccurateAndPrint() {
		System.out.println(stopAccurateAndFormat());
	}

	@Override
	public String stopAccurateAndFormat() {
		return stopAndFormat();
	}
}
