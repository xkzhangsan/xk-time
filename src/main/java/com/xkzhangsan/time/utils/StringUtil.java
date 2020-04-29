package com.xkzhangsan.time.utils;

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
}
