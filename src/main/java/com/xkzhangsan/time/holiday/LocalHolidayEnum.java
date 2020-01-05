package com.xkzhangsan.time.holiday;

import java.time.temporal.Temporal;

public enum LocalHolidayEnum implements Holiday{

	NEW_YEAR_DAY("元旦", "0101"),
    VALENTINE_DAY("情人节", "0214"),
    WOMEN_DAY("妇女节", "0308"),
    ARBOR_DAY("植树节", "0312"),
    WORLD_CONSUMER_RIGHTS_DAY("消费者权益日", "0315"),
    APRIL_FOOL_DAY("愚人节", "0401"),
    INTERNATIONAL_WORKERS_DAY("劳动节", "0501"),
    CHINA_YOUTH_DAY("青年节", "0504"),
    NURSES_DAY("护士节", "0512"),
    MOTHER_DAY("母亲节", "5-W-2-7"),
    CHILDREN_DAY("儿童节", "0601"),
    FATHER_DAY("父亲节", "6-W-3-7"),
    JIANDANGJIE("建党节", "0701"),
    JIANJUNJIE("建军节", "0801"),
    TEACHER_DAY("教师节", "0910"),
    GUOQINGJIE("国庆节", "1001"),
    ALL_SAINTS_DAY("万圣节", "1101"),
    CHRISTMAS("圣诞节", "1225"),;
	
	private LocalHolidayEnum(String name, String pattern) {
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
