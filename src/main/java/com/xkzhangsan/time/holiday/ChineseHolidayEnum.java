package com.xkzhangsan.time.holiday;

import java.time.temporal.Temporal;

public enum ChineseHolidayEnum implements Holiday{
	
	CHUNJIE("春节", "0101"),
	YUANXIAOJIE("元宵节", "0115"),
	LONGTAITOU("龙抬头", "0202"),
	DUANWUJIE("端午节", "0505"),
	QIXIJIE("七夕节", "0707"),
	ZHONGQIUJIE("中秋节", "0815"),
	CHONGYANGJIE("重阳节", "0909"),
	LABAJIE(" 腊八节", "1208"),
	XIAONIANNORTH("北方小年", "1223"),
	XIAONIANSOUTH("南方小年", "1224"),
	CHUXI("除夕", "12-M-L"),;
	
	private ChineseHolidayEnum(String name, String pattern) {
		this.name = name;
		this.pattern = pattern;
	}
	private final String name;
	private final String pattern;
	public String getName() {
		return name;
	}
	public String getPattern() {
		return pattern;
	}
	@Override
	public Holiday getHoliday(Temporal temporal) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
