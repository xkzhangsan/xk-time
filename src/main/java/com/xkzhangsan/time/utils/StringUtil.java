package com.xkzhangsan.time.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.xkzhangsan.time.constants.Constant;

/**
 * 字符串工具类
 *
 * @author xkzhangsan
 */
public class StringUtil {

    /**
     * 判断是否为空字符串
     * @param str 字符串
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
	 * @param str 字符串
	 * @return boolean
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
	 * @param target 需要计算的字符串
	 * @return 出现次数
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
    
    /**
     * 转换节日数据为map
     * @param holidayData 节日map
     * @return 返回节日map
     */
	public static Map<String, Integer> convertHolidayDataToMap(String holidayData){
		Map<String, Integer> dateTypeMap = new HashMap<>();
		if(isEmpty(holidayData)){
			return dateTypeMap;
		}
		
		String[] dateTypeArr = holidayData.replace(" ", "").split(",");
		for(String dateType : dateTypeArr){
			String[] arr = dateType.split(":");
			dateTypeMap.put(arr[0], Integer.valueOf(arr[1]));
		}
		return dateTypeMap;
	}

    /**
     * 转换节日数据为map，使用缓存提高性能
     * @param holidayData 节日map
     * @return 返回节日map
     */
	@SuppressWarnings("unchecked")
	public static Map<String, Integer> convertHolidayDataToMapUseCache(String holidayData){
		Map<String, Integer> dateTypeMap = new HashMap<>();
		//参数为空，直接返回
		if(isEmpty(holidayData)){
			return dateTypeMap;
		}
		
		//查询缓存
		dateTypeMap = (Map<String, Integer>)CommonCache.get(holidayData);
		
		//缓存存在，返回缓存
		if(CollectionUtil.isNotEmpty(dateTypeMap)){
			return dateTypeMap;
		}
		
		//缓存不存在，先设置缓存然后返回
		Supplier<Object> supplier = new Supplier<Object>() {
			@Override
			public Object get() {
				Map<String, Integer> dateTypeMap = new HashMap<>();
				String[] dateTypeArr = holidayData.replace(" ", "").split(",");
				for(String dateType : dateTypeArr){
					String[] arr = dateType.split(":");
					dateTypeMap.put(arr[0], Integer.valueOf(arr[1]));
				}
				return dateTypeMap;
			}
		};
		return (Map<String, Integer>)CommonCache.get(holidayData, supplier);
	}
}
