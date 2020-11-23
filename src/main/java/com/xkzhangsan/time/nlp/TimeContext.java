package com.xkzhangsan.time.nlp;

/**
 * 时间表达式单元规范化对应的内部类
 * 对应时间表达式规范化的每个字段，
 * 六个字段分别是：年-月-日-时-分-秒，
 * 每个字段初始化为-1
 * 以及对比基本时间
 * 
 * @author xkzhangsan
 */
public class TimeContext{
	private int [] tunit={-1,-1,-1,-1,-1,-1};

	private String timeBase;
    private String oldTimeBase;
    
	public String getTimeBase() {
		return timeBase;
	}
	public void setTimeBase(String timeBase) {
		this.timeBase = timeBase;
	}
	public String getOldTimeBase() {
		return oldTimeBase;
	}
	public void setOldTimeBase(String oldTimeBase) {
		this.oldTimeBase = oldTimeBase;
	}
	public int[] getTunit() {
		return tunit;
	}
	public void setTunit(int[] tunit) {
		this.tunit = tunit;
	}
	
}
