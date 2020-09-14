package com.xkzhangsan.time.cost;

/**
 * 计算耗时，单位毫秒<br>
 * 
* @author xkzhangsan
* @date 2020年8月8日
 */
public final class MillisecondCost implements Cost{
	
	private final String name;
	
	private final long millisecond;
	
	public MillisecondCost() {
		super();
		this.name = "";
		this.millisecond = System.currentTimeMillis();
	}
	
	public MillisecondCost(String name) {
		super();
		this.name = name;
		this.millisecond = System.currentTimeMillis();
	}
	
	@Override
	public long stop() {
		return System.currentTimeMillis() - millisecond;
	}

	@Override
	public void stopAndPrint() {
		System.out.println(this.name + " cost: " + stop() + " ms");
	}

	@Override
	public String stopAndFormat() {
		return (this.name + " cost: " + stop() + " ms");
	}

	@Override
	public String stopAccurate() {
		return String.valueOf(stop());
	}

}
