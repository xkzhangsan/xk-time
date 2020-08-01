package com.xkzhangsan.time.utils;

/**
 * 数组工具类
 *
 * @author xkzhangsan
 * @date 2020年08月01日
 */
public class ArrayUtil {
	
	/**
     * 判断数组是否为空
     * @param array
     * @return boolean
     */
    public static <T> boolean isEmpty(Object[] array){
        return array == null || array.length == 0;
    }

    /**
     * 判断array是否非空
     * @return boolean
     */
    public static boolean isNotEmpty(Object[] array){
        return ! isEmpty(array);
    }

}
