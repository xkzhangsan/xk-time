package com.xkzhangsan.time.utils;

/**
 * 数组工具类
 *
 * @author xkzhangsan
 */
public class ArrayUtil {

    /**
     * 判断数组是否为空
     * @param array 数组
     * @return boolean
     */
    public static boolean isEmpty(Object[] array){
        return array == null || array.length == 0;
    }

    /**
     * 判断array是否非空
     * @param array 数组
     * @return boolean
     */
    public static boolean isNotEmpty(Object[] array){
        return ! isEmpty(array);
    }

}
