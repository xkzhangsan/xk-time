package com.xkzhangsan.time.nlp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;
import com.xkzhangsan.time.utils.CollectionUtil;
import com.xkzhangsan.time.utils.StringUtil;

/**
 * 时间自然语言解析工具类
 * 
 * @author xkzhangsan
 */
public class TimeNLPUtil {
	
	private TimeNLPUtil(){
	}
	
	public static List<TimeNLP> parse(String text){
		return parse(text, null);
	}
	
	public static List<TimeNLP> parse(String text, String timeBase){
		// 文本预处理
		if(StringUtil.isEmpty(text)){
			return null;
		}
		text = TextPreprocess.preprocess(text);
		if(StringUtil.isEmpty(text)){
			return null;
		}
		
		//时间名词匹配分析
		List<String> timeStrs = TextAnalysis.getInstance().analysis(text);
		if(CollectionUtil.isEmpty(timeStrs)){
			return null;
		}
		
		//解析
		List<TimeNLP> timeNLPList = new ArrayList<>(timeStrs.size());
        /**时间上下文： 前一个识别出来的时间会是下一个时间的上下文，用于处理：周六3点到5点这样的多个时间的识别，第二个5点应识别到是周六的。*/
        TimeContext timeContext = new TimeContext();
        if(StringUtil.isEmpty(timeBase)){
        	timeBase = DateTimeFormatterUtil.format(new Date(), "yyyy-MM-dd-HH-mm-ss");
        }
        String oldTimeBase = timeBase;
        timeContext.setTimeBase(timeBase);
        timeContext.setOldTimeBase(oldTimeBase);
        for (int j = 0; j < timeStrs.size(); j++) {
        	if(StringUtil.isEmpty(timeStrs.get(j))){
        		break;
        	}
        	TimeNLP timeNLP = new TimeNLP(timeStrs.get(j), TextAnalysis.getInstance(), timeContext);
        	timeNLPList.add(timeNLP);
            timeContext = timeNLP.getTimeContext();
        }
        
        /**过滤无法识别的字段*/
        List<TimeNLP> timeNLPListResult = TimeNLP.filterTimeUnit(timeNLPList);
		return timeNLPListResult;
	}	
	
}
