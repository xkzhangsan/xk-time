package com.xkzhangsan.time.enums;

/**
 * 范围时间的默认时间点
 * 
 * @author xkzhangsan
 */
public enum MomentEnum {
	
	day_break(3),
	early_morning(8), //早
	morning(10), //上午
	noon(12), //中午、午间
	afternoon(15), //下午、午后
	night(18), //晚上、傍晚
	lateNight(20), //晚、晚间
	midNight(23);  //深夜
	
	private int hourTime = 0;

	/**
	 * @param hourTime
	 */
	private MomentEnum(int hourTime) {
		this.hourTime = hourTime;
	}

	/**
	 * @return the hourTime
	 */
	public int getHourTime() {
		return hourTime;
	}
	
}
