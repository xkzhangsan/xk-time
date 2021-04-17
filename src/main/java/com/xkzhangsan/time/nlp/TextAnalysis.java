package com.xkzhangsan.time.nlp;

import com.xkzhangsan.time.utils.RegexResourceUtil;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * 根据正则文件分析文本中的时间字符串
 * 
 * @author xkzhangsan
 */
public class TextAnalysis {
	
	private static volatile TextAnalysis instance;
	private static volatile Pattern pattern;
    private boolean isPreferFuture = true;

	private TextAnalysis(){
		try {
            InputStream resourceAsStream = RegexResourceUtil.class.getClassLoader().getResourceAsStream("TimeRegex.Gzip");
            ObjectInputStream in = new ObjectInputStream(
                    new BufferedInputStream(new GZIPInputStream((resourceAsStream))));
            pattern = RegexResourceUtil.readModel(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static TextAnalysis getInstance(){
		if(instance == null){
			synchronized(TextAnalysis.class){
				if(instance == null){
					instance = new TextAnalysis();
				}
			}
		}
		return instance;
	}

    /**
     * 根据正则集合识别出时间词语
     * @param text 待处理文本
     * @return 时间词语
     */
	public List<String> analysis(String text){
		Matcher match;
        int startline = -1, endline = -1;

        List<String> tempResult = new ArrayList<>();
        tempResult.add("");
        int rpointer = 0;// 计数器，记录当前识别到哪一个字符串了

        match = pattern.matcher(text);
        boolean startmark = true;
        while (match.find()) {
            startline = match.start();
            if (endline == startline) // 假如下一个识别到的时间字段和上一个是相连的 @author kexm
            {
                rpointer--;
                tempResult.set(rpointer, tempResult.get(rpointer) + match.group());// 则把下一个识别到的时间字段加到上一个时间字段去
            } else {
                if (!startmark) {
                    rpointer--;
                    rpointer++;
                }
                startmark = false;
                tempResult.set(rpointer, match.group());// 记录当前识别到的时间字段，并把startmark开关关闭。这个开关貌似没用？
            }
            endline = match.end();
            rpointer++;
            if((tempResult.size()-1)<rpointer){
            	tempResult.add("");
            }
        }
        if (rpointer > 0) {
            rpointer--;
            rpointer++;
        }
        return tempResult;
	}

	public boolean isPreferFuture() {
		return isPreferFuture;
	}

	public void setPreferFuture(boolean isPreferFuture) {
		this.isPreferFuture = isPreferFuture;
	}
	
}
