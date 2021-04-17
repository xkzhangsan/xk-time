package com.xkzhangsan.time.nlp;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xkzhangsan.time.enums.RegexEnum;
import com.xkzhangsan.time.utils.RegexCache;

/**
 * 文本预处理
 * 
 * @author xkzhangsan
 */
public class TextPreprocess {
	
	private TextPreprocess(){
	}

	/**
	 * 文本预处理
	 * @param text 待处理文本
	 * @return 处理后的文本
	 */
	public static String preprocess(String text){
//		text = delKeyword(text, "\\s+"); // 清理空白符 直接删除空格会影响部分格式识别错误，比如2016-07-19 00:00:00
		text = text.trim();
		text = delKeyword(text, RegexEnum.TextPreprocessSeparator.getRule()); // 清理语气助词
		text = delDecimalStr(text); // 清理非日期小数
		text = numberTranslator(text);// 大写数字转化
		return text;
	}

	/**
	 * 该方法删除一字符串中所有匹配某一规则字串
	 * 可用于清理一个字符串中的空白符和语气助词
	 * 
	 * @param target 待处理字符串
	 * @param rules 删除规则
	 * @return 清理工作完成后的字符串
	 */
	public static String delKeyword(String target, String rules){
		Pattern p = RegexCache.get(rules); 
		Matcher m = p.matcher(target); 
		StringBuffer sb = new StringBuffer(); 
		boolean result = m.find(); 
		while(result) {
			m.appendReplacement(sb, ""); 
			result = m.find(); 
		}
		m.appendTail(sb);
		String s = sb.toString();
		//System.out.println("字符串："+target+" 的处理后字符串为：" +sb);
		return s;
	}
	
	/**
	 * 该方法可以将字符串中所有的用汉字表示的数字转化为用阿拉伯数字表示的数字
	 * 如"这里有一千两百个人，六百零五个来自中国"可以转化为
	 * "这里有1200个人，605个来自中国"
	 * 此外添加支持了部分不规则表达方法
	 * 如两万零六百五可转化为20650
	 * 两百一十四和两百十四都可以转化为214
	 * 一六零加一五八可以转化为160+158
	 * 该方法目前支持的正确转化范围是0-99999999
	 * 该功能模块具有良好的复用性
	 * 
	 * @param target 待转化的字符串
	 * @return 转化完毕后的字符串
	 */
	public static String numberTranslator(String target){
		Pattern p = RegexEnum.TextPreprocessNumberTranslatorOne.getPattern(); 
		Matcher m = p.matcher(target); 
		StringBuffer sb = new StringBuffer(); 
		boolean result = m.find(); 
		while(result) { 
			String group = m.group();
			String[] s = group.split("万");
			int num = 0;
			if(s.length == 2){
				num += wordToNumber(s[0])*10000 + wordToNumber(s[1])*1000;
			}
			m.appendReplacement(sb, Integer.toString(num)); 
			result = m.find(); 
		}
		m.appendTail(sb);
		target = sb.toString();
		
		p = RegexEnum.TextPreprocessNumberTranslatorTwo.getPattern(); 
		m = p.matcher(target); 
		sb = new StringBuffer(); 
		result = m.find(); 
		while(result) { 
			String group = m.group();
			String[] s = group.split("千");
			int num = 0;
			if(s.length == 2){
				num += wordToNumber(s[0])*1000 + wordToNumber(s[1])*100;
			}
			m.appendReplacement(sb, Integer.toString(num)); 
			result = m.find(); 
		}
		m.appendTail(sb);
		target = sb.toString();
		
		p = RegexEnum.TextPreprocessNumberTranslatorThree.getPattern(); 
		m = p.matcher(target); 
		sb = new StringBuffer(); 
		result = m.find(); 
		while(result) { 
			String group = m.group();
			String[] s = group.split("百");
			int num = 0;
			if(s.length == 2){
				num += wordToNumber(s[0])*100 + wordToNumber(s[1])*10;
			}
			m.appendReplacement(sb, Integer.toString(num)); 
			result = m.find(); 
		}
		m.appendTail(sb);
		target = sb.toString();
		
		p = RegexEnum.TextPreprocessNumberTranslatorFour.getPattern();
		m = p.matcher(target); 
		sb = new StringBuffer(); 
		result = m.find(); 
		while(result) { 
			m.appendReplacement(sb, Integer.toString(wordToNumber(m.group()))); 
			result = m.find(); 
		}
		m.appendTail(sb);
		target = sb.toString();
		
		p = RegexEnum.TextPreprocessNumberTranslatorFive.getPattern(); 
		m = p.matcher(target);
		sb = new StringBuffer();
		result = m.find(); 
		while(result) { 
			m.appendReplacement(sb, Integer.toString(wordToNumber(m.group()))); 
			result = m.find(); 
		}
		m.appendTail(sb);
		target = sb.toString();
		
		p = RegexEnum.TextPreprocessNumberTranslatorSix.getPattern();
		m = p.matcher(target);
		sb = new StringBuffer();
		result = m.find();
		while(result) { 
			String group = m.group();
			String[] s = group.split("十");
			int num = 0;
			if(s.length == 0){
				num += 10;
			}
			else if(s.length == 1){
				int ten = Integer.parseInt(s[0]);
				if(ten == 0)
					num += 10;
				else num += ten*10;
			}
			else if(s.length == 2){
				if(s[0].equals(""))
					num += 10;
				else{
					int ten = Integer.parseInt(s[0]);
					if(ten == 0)
						num += 10;
					else num += ten*10;
				}
				num += Integer.parseInt(s[1]);
			}
			m.appendReplacement(sb, Integer.toString(num)); 
			result = m.find(); 
		}
		m.appendTail(sb);
		target = sb.toString();
		
		p = RegexEnum.TextPreprocessNumberTranslatorSeven.getPattern(); 
		m = p.matcher(target);
		sb = new StringBuffer();
		result = m.find();
		while(result) { 
			String group = m.group();
			String[] s = group.split("百");
			int num = 0;
			if(s.length == 1){
				int hundred = Integer.parseInt(s[0]);
				num += hundred*100;
			}
			else if(s.length == 2){
				int hundred = Integer.parseInt(s[0]);
				num += hundred*100;
				num += Integer.parseInt(s[1]);
			}
			m.appendReplacement(sb, Integer.toString(num)); 
			result = m.find(); 
		}
		m.appendTail(sb);
		target = sb.toString();
		
		p = RegexEnum.TextPreprocessNumberTranslatorEight.getPattern();
		m = p.matcher(target);
		sb = new StringBuffer();
		result = m.find();
		while(result) { 
			String group = m.group();
			String[] s = group.split("千");
			int num = 0;
			if(s.length == 1){
				int thousand = Integer.parseInt(s[0]);
				num += thousand*1000;
			}
			else if(s.length == 2){
				int thousand = Integer.parseInt(s[0]);
				num += thousand*1000;
				num += Integer.parseInt(s[1]);
			}
			m.appendReplacement(sb, Integer.toString(num)); 
			result = m.find(); 
		}
		m.appendTail(sb);
		target = sb.toString();
		
		p = RegexEnum.TextPreprocessNumberTranslatorNine.getPattern(); 
		m = p.matcher(target);
		sb = new StringBuffer();
		result = m.find();
		while(result) { 
			String group = m.group();
			String[] s = group.split("万");
			int num = 0;
			if(s.length == 1){
				int tenthousand = Integer.parseInt(s[0]);
				num += tenthousand*10000;
			}
			else if(s.length == 2){
				int tenthousand = Integer.parseInt(s[0]);
				num += tenthousand*10000;
				num += Integer.parseInt(s[1]);
			}
			m.appendReplacement(sb, Integer.toString(num)); 
			result = m.find(); 
		}
		m.appendTail(sb);
		target = sb.toString();
		
		return target;
	}
	
	/**
	 * 方法numberTranslator的辅助方法，可将[零-九]正确翻译为[0-9]
	 * 
	 * @param s 大写数字
	 * @return 对应的整形数，如果不是大写数字返回-1
	 */
	private static int wordToNumber(String s){
		if(s.equals("零")||s.equals("0"))
			return 0;
		else if(s.equals("一")||s.equals("1"))
			return 1;
		else if(s.equals("二")||s.equals("两")||s.equals("2"))
			return 2;
		else if(s.equals("三")||s.equals("3"))
			return 3;
		else if(s.equals("四")||s.equals("4"))
			return 4;
		else if(s.equals("五")||s.equals("5"))
			return 5;
		else if(s.equals("六")||s.equals("6"))
			return 6;
		else if(s.equals("七")||s.equals("天")||s.equals("日") || s.equals("末") ||s.equals("7"))
			return 7;
		else if(s.equals("八")||s.equals("8"))
			return 8;
		else if(s.equals("九")||s.equals("9"))
			return 9;
		else return -1;
	}
	
	/**
	 * 清理非日期小数 比如4.5等
	 * @param target
	 * @return 处理过的字符串
	 */
	private static String delDecimalStr(String target){
		Pattern dateFlagPattern = RegexEnum.TextPreprocessDelDecimalStrSeparator.getPattern();
		Matcher dateFlagMatcher = dateFlagPattern.matcher(target);
		Set<Integer> dateFlagSet = new HashSet<>();
		while(dateFlagMatcher.find()){
			dateFlagSet.add(dateFlagMatcher.start());
		}
		
		Pattern p = RegexEnum.TextPreprocessDelDecimalStr.getPattern();
		Matcher m = p.matcher(target);
		StringBuffer sb = new StringBuffer(); 
		boolean result = m.find();
		while(result) {
			if(!dateFlagSet.contains(m.end())){
				m.appendReplacement(sb, "");
			}
			result = m.find(); 
		}
		m.appendTail(sb);
		return sb.toString();
	}	
}
