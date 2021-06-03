package com.xkzhangsan.time.calculator;

import java.time.DateTimeException;

/**
 * 时间段
 *
 *@author xkzhangsan
 */
public class TimePair {

	public TimePair(long start, long end) {
		if(end<start){
			throw new DateTimeException("end不能小于start");
		}
		this.start = start;
		this.end = end;
	}

	private long start;
	
	private long end;

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}
	
}
