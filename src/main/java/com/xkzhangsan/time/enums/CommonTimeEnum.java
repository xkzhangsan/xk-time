package com.xkzhangsan.time.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * 常用时间枚举
 *
 * @author xkzhangsan
 */
public enum CommonTimeEnum {

    TODAY("today", "今天"),
    ;

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    CommonTimeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Map<String, String> convertToMap(){
        Map<String, String> commonTimeMap = new HashMap<String, String>();
        for (CommonTimeEnum commonTimeEnum : CommonTimeEnum.values()) {
            commonTimeMap.put(commonTimeEnum.getCode(), commonTimeEnum.getCode());
            commonTimeMap.put(commonTimeEnum.getName(), commonTimeEnum.getCode());
        }
        return commonTimeMap;
    }

    public static CommonTimeEnum getCommonTimeEnumByCode(String code){
        for (CommonTimeEnum commonTimeEnum : CommonTimeEnum.values()) {
            if(commonTimeEnum.getCode().equals(code)){
                return commonTimeEnum;
            }
        }
        return null;
    }
}
