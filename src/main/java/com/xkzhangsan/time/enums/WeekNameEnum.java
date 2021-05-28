package com.xkzhangsan.time.enums;


/**
 * 星期名称枚举，包含英文全称，英文简称，中文
 * Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday
 * @author xkzhangsan
 */
public enum WeekNameEnum {
	
	Mon(1, "Monday", "星期一"),
	Tue(2, "Tuesday", "星期二"),
	Wed(3, "Wednesday", "星期三"),
	Thu(4, "Thursday", "星期四"),
	Fri(5, "Friday", "星期五"),
	Sat(6, "Saturday", "星期六"),
	Sun(7, "Sunday", "星期日"),;
	
	private static final WeekNameEnum[] ENUMS = WeekNameEnum.values();
	/**
	 * 序号
	 */
	private int code;
	
	/**
	 * 英文全称
	 */
	private String fullNameEn;
	
	/**
	 * 中文
	 */
	private String nameCn;
	

	private WeekNameEnum(int code, String fullNameEn, String nameCn) {
		this.code = code;
		this.fullNameEn = fullNameEn;
		this.nameCn = nameCn;
	}

	/**
	 * 根据code查询星期名称枚举
	 * @param code code
	 * @return WeekNameEnum
	 */
	public static WeekNameEnum getByCode(int code){
		if (code < 1 || code > 7) {
			return null;
		}
		return ENUMS[code -1];
	}
	
	/**
	 * 根据code查询星期英文简称
	 * @param code code
	 * @return String
	 */
	public static String getShortNameEnByCode(int code){
		WeekNameEnum monthNameEnum = getByCode(code);
		return monthNameEnum != null ? monthNameEnum.name() : null;
	}
	
	/**
	 * 根据code查询星期英文全称
	 * @param code code
	 * @return String
	 */
	public static String getFullNameEnByCode(int code){
		WeekNameEnum monthNameEnum = getByCode(code);
		return monthNameEnum != null ? monthNameEnum.getFullNameEn() : null;
	}
	
	/**
	 * 根据code查询星期中文名称
	 * @param code code
	 * @return String
	 */
	public static String getNameCnByCode(int code){
		WeekNameEnum monthNameEnum = getByCode(code);
		return monthNameEnum != null ? monthNameEnum.getNameCn() : null;
	}

	public int getCode() {
		return code;
	}

	public String getFullNameEn() {
		return fullNameEn;
	}

	public String getNameCn() {
		return nameCn;
	}
}
