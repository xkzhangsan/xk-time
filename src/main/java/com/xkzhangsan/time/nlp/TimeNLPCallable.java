package com.xkzhangsan.time.nlp;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * TimeNLP Callable
 * @author xkzhangsan
 */
public class TimeNLPCallable implements Callable<List<TimeNLP>>{
	
	private String text;
	
	private String timeBase;
	
	public TimeNLPCallable(String text, String timeBase) {
		super();
		this.text = text;
		this.timeBase = timeBase;
	}

	@Override
	public List<TimeNLP> call() throws Exception {
		List<TimeNLP> timeNLPList = TimeNLPUtil.parse(text, timeBase);
		return timeNLPList;
	}
	
}
