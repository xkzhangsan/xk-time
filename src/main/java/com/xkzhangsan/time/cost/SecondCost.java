package com.xkzhangsan.time.cost;

import java.util.concurrent.TimeUnit;

/**
 * 计算秒耗时，单位 秒<br>
 * 
* @author xkzhangsan
* @date 2020年8月8日
 */
public final class SecondCost implements Cost{
	
	private final String name;
	
	private final long millisecond;
	
	public SecondCost() {
		super();
		this.name = "";
		this.millisecond = System.currentTimeMillis();
	}
	
	public SecondCost(String name) {
		super();
		this.name = name;
		this.millisecond = System.currentTimeMillis();
	}
	
	@Override
	public long stop() {
		return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - millisecond);
	}

	@Override
	public void stopAndPrint() {
		System.out.println(this.name + " cost: " + stop() + " s");
	}


}
