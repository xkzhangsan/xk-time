package com.xkzhangsan.time.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.xkzhangsan.time.utils.CollectionUtil;
import com.xkzhangsan.time.utils.CommonCache;

/**
 * 中文日期数字枚举
 *
 * @author xkzhangsan
 */
public enum ChineseDateDigitEnum {
	
	ZERO("〇"),
	ONE("一"),
	TWO("二"),
	THREE("三"),
	FOUR("四"),
	FIVE("五"),
	SIX("六"),
	SEVEN("七"),
	EIGHT("八"),
	NINE("九"),
	TEN("十"),
	ELEVEN("十一"),
	TWELVE("十二"),
	THIRTEEN("十三"),
	FOURTEEN("十四"),
	FIFTEEN("十五"),
	SIXTEEN("十六"),
	SEVENTEEN("十七"),
	EIGHTEEN("十八"),
	NINETEEN("十九"),
	TWENTY("二十"),
	TWENTYONE("二十一"),
	TWENTYTWO("二十二"),
	TWENTYTHREE("二十三"),
	TWENTYFOUR("二十四"),
	TWENTYFIVE("二十五"),
	TWENTYSIX("二十六"),
	TWENTYSEVEN("二十七"),
	TWENTYEIGHT("二十八"),
	TWENTYNINE("二十九"),
	THIRTY("三十"),
	THIRTYONE("三十一");
	
	public static final ChineseDateDigitEnum[] ENUMS = ChineseDateDigitEnum.values();
	
	public static final String CHINESE_DATE_DIGIT_MAP = "CHINESE_DATE_DIGIT_MAP";
	
	private String chineseDigit;

	private ChineseDateDigitEnum(String chineseDigit) {
		this.chineseDigit = chineseDigit;
	}

	public String getChineseDigit() {
		return chineseDigit;
	}
	
	@SuppressWarnings("unchecked")
	public static Integer getIndexUseCache(String chineseDigit){
		Map<String, Integer> chineseDateDigitMap = new HashMap<>(32);
		
		//查询缓存
		chineseDateDigitMap = (Map<String, Integer>)CommonCache.get(CHINESE_DATE_DIGIT_MAP);
		
		//缓存存在，返回缓存
		if(CollectionUtil.isNotEmpty(chineseDateDigitMap)){
			return chineseDateDigitMap.get(chineseDigit);
		}
		
		//缓存不存在，先设置缓存然后返回
		Supplier<Object> supplier = new Supplier<Object>() {
			@Override
			public Object get() {
				Map<String, Integer> dateDigitMap = new HashMap<>();
				
				for(ChineseDateDigitEnum chineseDateDigitEnum : ENUMS){
					dateDigitMap.put(chineseDateDigitEnum.getChineseDigit(), chineseDateDigitEnum.ordinal());
				}
				return dateDigitMap;
			}
		};
		return ((Map<String, Integer>)CommonCache.get(CHINESE_DATE_DIGIT_MAP, supplier)).get(chineseDigit);
	}
	

}
