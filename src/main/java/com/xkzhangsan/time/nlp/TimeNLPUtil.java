package com.xkzhangsan.time.nlp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.xkzhangsan.time.constants.Constant;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;
import com.xkzhangsan.time.utils.CollectionUtil;
import com.xkzhangsan.time.utils.GlobalThreadPool;
import com.xkzhangsan.time.utils.StringUtil;

/**
 * 时间自然语言分析工具类<br>
 * 作为xk-time的一个扩展应用功能，主要原理是根据TimeRegex.Gzip正则集合识别时间词语，然后计算得到结果。<br>
 * 
 *  包括功能：  <br>
 *（1）以当前时间为基础分析时间自然语言。  <br>
 *（2）以指定时间为基础分析时间自然语言。 <br>
 *（3）增加多种调用方式，比如不抛出异常，限制时间内完成和使用线程池等方式。<br>
 *
 * 修改自 https://github.com/shinyke/Time-NLP<br>
 * 做了一些修改如下：<br>
 *（1）封装属性，重命名使符合驼峰命名标准。<br>
 *（2）将加载正则资源文件改为单例加载。<br>
 *（3）将类按照功能重新划分为单独的多个类。<br>
 *（4）使用Java8日期API重写。<br>
 *（5）增加注释说明，优化代码。<br>
 *（6）修复原项目中的issue：标准时间yyyy-MM-dd、yyyy-MM-dd HH:mm:ss和yyyy-MM-dd HH:mm解析问题。<br>
 *（7）修复原项目中的issue：1小时后，1个半小时后，1小时50分钟等解析问题；并且支持到秒，比如50秒后，10分钟30秒后等。<br>
 *（8）修复原项目中的issue：修复当前时间是上午10点，那么下午三点 会识别为明天下午三点问题。<br>
 *（9）修复原项目中的issue：修复小数解析异常问题。<br>
 *  <br>
 *
 *注意：NLP会有一定的识别失败率，在不断迭代开发提高成功率
 *
 * @author xkzhangsan
 */
public class TimeNLPUtil {
	
	private TimeNLPUtil(){
	}
	
	/**
	 * 时间自然语言分析
	 * @param text 待分析文本
	 * @return 结果列表
	 */
	public static List<TimeNLP> parse(String text){
		return parse(text, null);
	}
	
	/**
	 * 时间自然语言分析，不抛出异常
	 * @param text 待分析文本
	 * @return 结果列表
	 */
	public static List<TimeNLP> parseSafe(String text){
		return parse(text, null, true, 0, false);
	}

	/**
	 * 时间自然语言分析，不抛出异常，并且限制时间在指定时间内完成，注意：会创建一个线程异步完成
	 * @param text 待分析文本
	 * @param timeout 超时时间 毫秒
	 * @return 结果列表
	 */
	public static List<TimeNLP> parseSafe(String text, long timeout){
		return parse(text, null, true, timeout, false);
	}

	/**
	 * 时间自然语言分析  不抛出异常，并且限制时间在3s中内完成，注意：会创建一个线程异步完成
	 * @param text 待分析文本
	 * @return 结果列表
	 */
	public static List<TimeNLP> parseSafeAndTimeLimit(String text){
		return parse(text, null, true, Constant.TIME_NLP_TIMEOUT, false);
	}
	
	/**
	 * 时间自然语言分析，注意：会创建一个线程异步完成，使用线程池
	 * @param text 待分析文本
	 * @return 结果列表
	 */
	public static List<TimeNLP> parseUseThreadPool(String text){
		return parse(text, null, false, 0, true);
	}
	
	/**
	 * 时间自然语言分析，不抛出异常，注意：会创建一个线程异步完成，使用线程池
	 * @param text 待分析文本
	 * @return 结果列表
	 */
	public static List<TimeNLP> parseSafeUseThreadPool(String text){
		return parse(text, null, true, 0, true);
	}
	
	/**
	 * 时间自然语言分析，不抛出异常，并且限制时间在指定时间内完成，注意：会创建一个线程异步完成，使用线程池
	 * @param text 待分析文本
	 * @param timeout 超时时间 毫秒
	 * @return 结果列表
	 */
	public static List<TimeNLP> parseSafeUseThreadPool(String text, long timeout){
		return parse(text, null, true, timeout, true);
	}
	
	/**
	 * 时间自然语言分析，不抛出异常，并且限制时间在3s内完成，注意：会创建一个线程异步完成，使用线程池
	 * @param text 待分析文本
	 * @return 结果列表
	 */
	public static List<TimeNLP> parseSafeAndTimeLimitUseThreadPool(String text){
		return parse(text, null, true, Constant.TIME_NLP_TIMEOUT, true);
	}
	
	
	/**
	 * 时间自然语言分析
	 * @param text 待分析文本 
	 * @param timeBase 指定时间
	 * @return 结果列表
	 */
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
	
	/**
	 * 时间自然语言分析
	 * @param text 待分析文本 
	 * @param timeBase 指定时间
	 * @param isSafe true 不抛出异常，false 抛出异常 
	 * @param timeout 超时时间
	 * @param useThreadPool 是否使用线程池
	 * @return 结果列表
	 */
	public static List<TimeNLP> parse(String text, String timeBase, boolean isSafe, long timeout,
			boolean useThreadPool) {
		List<TimeNLP> result = null;
		if (StringUtil.isEmpty(text)) {
			return result;
		}

		if (!isSafe && timeout <= 0 && !useThreadPool) {
			result = parse(text, timeBase);
		}

		if (isSafe && timeout > 0 && useThreadPool) {
			try {
				result = GlobalThreadPool.getGlobalThreadPool().submit(new TimeNLPCallable(text, null)).get(timeout,
						TimeUnit.MILLISECONDS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (isSafe && timeout > 0) {
			try {
				TimeNLPCallable timeNLPCallable = new TimeNLPCallable(text, timeBase);
				FutureTask<List<TimeNLP>> futureTask = new FutureTask<>(timeNLPCallable);
				new Thread(futureTask).start();
				result = futureTask.get(timeout, TimeUnit.MILLISECONDS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (isSafe && useThreadPool) {
			try {
				result = GlobalThreadPool.getGlobalThreadPool().submit(new TimeNLPCallable(text, null)).get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (timeout > 0 && useThreadPool) {
			try {
				result = GlobalThreadPool.getGlobalThreadPool().submit(new TimeNLPCallable(text, null)).get(timeout,
						TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
		} else if (isSafe) {
			try {
				result = parse(text, timeBase);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (timeout > 0) {
			TimeNLPCallable timeNLPCallable = new TimeNLPCallable(text, timeBase);
			FutureTask<List<TimeNLP>> futureTask = new FutureTask<>(timeNLPCallable);
			new Thread(futureTask).start();
			try {
				result = futureTask.get(timeout, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
		} else if (useThreadPool) {
			try {
				result = GlobalThreadPool.getGlobalThreadPool().submit(new TimeNLPCallable(text, null)).get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}	
	
}
