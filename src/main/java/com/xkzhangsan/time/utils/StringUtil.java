package com.xkzhangsan.time.utils;

import com.xkzhangsan.time.constants.Constant;

/**
 * 字符串工具类
 *
 * @author xkzhangsan
 * @date 2020年03月23日
 */
public class StringUtil {

    /**
     * 判断是否为空字符串
     * @param str
     * @return boolean 如果为空，则返回true
     */
    public static boolean isEmpty(String str){
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符串是否非空
     * @param str 如果不为空，则返回true
     * @return boolean
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
    
	/**
	 * 是否纯数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		if(str == null){
			return false;
		}
		return Constant.NUMERIC_REGEX.matcher(str).matches();
	}
    
    /**
     * 判断字符串是否以字母开头
     * @param str 如果不为空，则返回false
     * @return boolean
     */
    public static boolean isStartWithWord(String str){
    	if(str == null){
    		return false;
    	}
        return Constant.START_WITH_WORD_REGEX.matcher(str).matches();
    }
    
    /**
     * 计算字符出现次数
     * @param str 如果不为空，则返回0
     * @return boolean
     */
    public static int countWord(String str, String target){
    	if(str == null){
    		return 0;
    	}
    	int len1 = str.length();
    	int len2 = str.replace(target, "").length();
        return (len1-len2);
    }
    
    /**
     * 判断字符串是包含中文
     * @param str 如果不为空，则返回false
     * @return boolean
     */
    public static boolean hasChinese(String str){
    	if(str == null){
    		return false;
    	}
        return Constant.CHINESE_REGEX.matcher(str).find();
    }
}
