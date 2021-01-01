package com.xkzhangsan.time.holiday;

import java.util.HashMap;
import java.util.Map;

/**
 * 中国法定节假日数据
 * 
 * 目前仅支持2021年放假信息
 * 
* @author xkzhangsan
 */
public enum ChineseHolidayData{
	
	IN_2021(2021, "2021-01-01:0," //元旦
					+"2021-02-07:1,                2021-02-11:0,2021-02-12:0,2021-02-15:0,2021-02-16:0,2021-02-17:0,2021-02-20:1,"//春节
					+"2021-04-05:0," //清明节
					+"2021-04-25:1,2021-05-03:0,2021-05-04:0,2021-05-05:0,2021-05-08:1,"//劳动节
					+"2021-06-14:0," //端午节
					+"2021-09-18:1,2021-09-20:0,2021-09-21:0,"//中秋节
					+"2021-09-26:1,2021-10-01:0,2021-10-04:0,2021-10-05:0,2021-10-06:0,2021-10-07:0,2021-10-09:1"//春节
					),;
	
	private ChineseHolidayData(Integer year, String data) {
		this.year = year;
		this.data = data;
	}

	/**
	 * 年
	 */
	private final Integer year;
	/**
	 * 数据
	 */
	private final String data;
	
	public Integer getYear() {
		return year;
	}

	public String getData() {
		return data;
	}
	
	public static boolean isSupported(int year){
		for (ChineseHolidayData chineseHolidayData : ChineseHolidayData.values()) {
			if(chineseHolidayData.getYear().equals(year)){
				return true;
			}
		}
		return false;
	}

	public static Map<String, Integer> convertToMap(int year){
		Map<String, Integer> chineseHolidayDataMap = new HashMap<String, Integer>();
		for (ChineseHolidayData chineseHolidayData : ChineseHolidayData.values()) {
			if(chineseHolidayData.getYear().equals(year)){
				String[] dateTypeArr = chineseHolidayData.getData().replace(" ", "").split(",");
				for(String dateType : dateTypeArr){
					String[] arr = dateType.split(":");
					chineseHolidayDataMap.put(arr[0], Integer.valueOf(arr[1]));
				}
			}
		}
		return chineseHolidayDataMap;
	}

}
