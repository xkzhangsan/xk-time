package com.xkzhangsan.time.enums;


/**
 * 月份名称枚举，包含英文全称，英文简称，中文全称
 * 
 * @author xkzhangsan
 */
public enum MonthNameEnum {
	
	Jan(1, "January", "一月", "一"),
	Feb(2, "February", "二月", "二"),
	Mar(3, "March", "三月", "三"),
	Apr(4, "April", "四月", "四"),
	May(5, "May", "五月", "五"),
	Jun(6, "June", "六月", "六"),
	Jul(7, "July", "七月", "七"), 
	Aug(8, "August", "八月", "八"),
	Sep(9, "September", "九月", "九"),
	Oct(10, "October", "十月", "十"),
	Nov(11, "November", "十一月", "十一"),
	Dec(12, "December", "十二月", "十二"),;
	
	private static final MonthNameEnum[] ENUMS = MonthNameEnum.values();
	
	/**
	 * 序号
	 */
	private int code;
	
	/**
	 * 英文全称
	 */
	private String fullNameEn;
	
	/**
	 * 中文全称
	 */
	private String fullNameCn;
	
	/**
	 * 中文简称
	 */
	private String shortNameCn;

	
	private MonthNameEnum(int code, String fullNameEn, String fullNameCn, String shortNameCn) {
		this.code = code;
		this.fullNameEn = fullNameEn;
		this.fullNameCn = fullNameCn;
		this.shortNameCn = shortNameCn;
	}

	/**
	 * 根据code查询月份名称枚举
	 * @param code code
	 * @return MonthNameEnum
	 */
	public static MonthNameEnum getByCode(int code){
		if (code < 1 || code > 12) {
			return null;
		}
		return ENUMS[code -1];
	}
	
	/**
	 * 根据code查询月份英文简称
	 * @param code code
	 * @return String
	 */
	public static String getShortNameEnByCode(int code){
		MonthNameEnum monthNameEnum = getByCode(code);
		return monthNameEnum != null ? monthNameEnum.name() : null;
	}
	
	/**
	 * 根据code查询月份英文全称
	 * @param code code
	 * @return String
	 */
	public static String getFullNameEnByCode(int code){
		MonthNameEnum monthNameEnum = getByCode(code);
		return monthNameEnum != null ? monthNameEnum.getFullNameEn() : null;
	}
	
	/**
	 * 根据code查询月份中文全称
	 * @param code code
	 * @return String
	 */
	public static String getFullNameCnByCode(int code){
		MonthNameEnum monthNameEnum = getByCode(code);
		return monthNameEnum != null ? monthNameEnum.getFullNameCn() : null;
	}
	
	/**
	 * 根据code查询月份中文
	 * @param code code
	 * @return String
	 */
	public static String getShortNameCnByCode(int code){
		MonthNameEnum monthNameEnum = getByCode(code);
		return monthNameEnum != null ? monthNameEnum.getShortNameCn() : null;
	}

	public int getCode() {
		return code;
	}

	public String getFullNameEn() {
		return fullNameEn;
	}

	public String getFullNameCn() {
		return fullNameCn;
	}

	public String getShortNameCn() {
		return shortNameCn;
	}
}
