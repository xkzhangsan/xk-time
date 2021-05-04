package com.xkzhangsan.time.formatter;

/**
 * 日期格式化常用模板
 * 
 * @author xkzhangsan
 */
public class DateFormatPattern {
	
	private DateFormatPattern(){
	}

	// ==================================yyyy-MM-dd相关Pattern==================================
	
	/**
	 * yyyy-MM-dd 比如：  2020-05-23
	 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	
	/**
	 * yyyy-M-d 不补0 比如：  2020-5-23
	 */
	public static final String YYYY_M_D = "yyyy-M-d";
	
	/**
	 * yyyyMMdd  比如：  20200523
	 */
	public static final String YYYYMMDD = "yyyyMMdd";
	
	/**
	 * yyyy/MM/dd  比如：  2020/05/23
	 */
	public static final String YYYY_MM_DD_EN = "yyyy/MM/dd";
	
	/**
	 * yyyy/M/d 不补0  比如：  2020/5/23
	 */
	public static final String YYYY_M_D_EN = "yyyy/M/d";
	
	/**
	 * yyyy年MM月dd日  比如： 2020年05月23日
	 */
	public static final String YYYY_MM_DD_CN = "yyyy年MM月dd日";
	
	/**
	 * yyyy年M月d日 不补0  比如： 2020年5月23日
	 */
	public static final String YYYY_M_D_CN = "yyyy年M月d日";
	
	/**
	 * yyyy.MM.dd  比如：2020.05.23
	 */
	public static final String YYYY_MM_DD_POINT = "yyyy.MM.dd";
	
	/**
	 * yyyy.M.d 不补0  比如：2020.5.23
	 */
	public static final String YYYY_M_D_POINT = "yyyy.M.d";
	
	/**
	 * yy/MM/dd  比如：20/05/23
	 */
	public static final String YY_MM_DD_EN = "yy/MM/dd";
	
	/**
	 * yy/M/d  比如：20/5/23
	 */
	public static final String YY_M_D_EN = "yy/M/d";

	/**
	 * MM/dd/yy  比如：05/23/20
	 */
	public static final String MM_DD_YY_EN = "MM/dd/yy";
	
	/**
	 * M/d/yy  比如：5/23/20
	 */
	public static final String M_D_YY_EN = "M/d/yy";

	/**
	 * yyyy-MM-dd E  比如：2020-05-23 星期六
	 */
	public static final String YYYY_MM_DD_E = "yyyy-MM-dd E";
	
	/**
	 * yy 年的后2位  比如： 20
	 */
	public static final String YY = "yy";
	
	/**
	 * yyyy  比如：2020
	 */
	public static final String YYYY = "yyyy";

	/**
	 * yyyy-MM  比如：2020-05
	 */
	public static final String YYYY_MM = "yyyy-MM";

	/**
	 * yyyyMM  比如：202005
	 */
	public static final String YYYYMM = "yyyyMM";

	/**
	 * yyyy/MM  比如：2020/05
	 */
	public static final String YYYY_MM_EN = "yyyy/MM";

	/**
	 * yyyy年MM月  比如：2020年05月
	 */
	public static final String YYYY_MM_CN = "yyyy年MM月";
	
	/**
	 * yyyy年M月  比如：2020年5月
	 */
	public static final String YYYY_M_CN = "yyyy年M月";

	/**
	 * MM-dd  比如：05-23
	 */
	public static final String MM_DD = "MM-dd";

	/**
	 * MMdd  比如：0523
	 */
	public static final String MMDD = "MMdd";

	/**
	 * MM/dd  比如：05/23
	 */
	public static final String MM_DD_EN = "MM/dd";

	/**
	 * M/d 不补0  比如：5/23
	 */
	public static final String M_D_EN = "M/d";
	
	/**
	 * MM月dd日  比如：05月23日
	 */
	public static final String MM_DD_CN = "MM月dd日";

	/**
	 * M月d日 不补0  比如：5月23日
	 */
	public static final String M_D_CN = "M月d日";
	
	
	
	// ==================================HH:mm:ss 相关Pattern==================================
	
	/**
	 * HH:mm:ss  比如：17:26:30
	 */
	public static String HH_MM_SS = "HH:mm:ss";
	
	/**
	 * H:m:s  比如：17:6:30
	 */
	public static String H_M_S = "H:m:s";

	/**
	 * HHmmss  比如：170630
	 */
	public static String HHMMSS = "HHmmss";

	/**
	 * HH时mm分ss秒  比如：17时06分30秒
	 */
	public static String HH_MM_SS_CN = "HH时mm分ss秒";

	/**
	 * HH:mm  比如：17:06
	 */
	public static String HH_MM = "HH:mm";
	
	/**
	 * H:m  比如：17:6
	 */
	public static String H_M = "H:m";

	/**
	 * HH时mm分 比如：17时06分
	 */
	public static String HH_MM_CN = "HH时mm分";

	/**
	 * hh:mm a 比如：05:06 下午 如果需要 显示PM 需要设置 Locale.ENGLISH
	 */
	public static String HH_MM_A = "hh:mm a";
	
	// ==================================HH:mm:ss.SSS 相关Pattern==================================
	
	/**
	 * HH:mm:ss.SSS  比如：17:26:30.272
	 */
	public static String HH_MM_SS_SSS = "HH:mm:ss.SSS";
	
	
	// ==================================HH:mm:ss.SSSSSS 相关Pattern==================================
	
	/**
	 * HH:mm:ss.SSSSSS  比如：17:26:30.272150
	 */
	public static String HH_MM_SS_SSSSSS = "HH:mm:ss.SSSSSS";
	
	
	// ==================================HH:mm:ss.SSSSSSSSS 相关Pattern==================================
	
	/**
	 * HH:mm:ss.SSSSSSSSS  比如：17:26:30.272150620
	 */
	public static String HH_MM_SS_SSSSSSSSS = "HH:mm:ss.SSSSSSSSS";
	
	
	
	
	// ==================================yyyy-MM-dd HH:mm:ss 相关Pattern==================================
	
	/**
	 * yyyy-MM-dd HH:mm:ss 比如：2020-05-23 17:06:30
	 */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * yyyy-M-d H:m:s 比如：2020-5-23 17:6:30
	 */
	public static final String YYYY_M_D_H_M_S = "yyyy-M-d H:m:s";

	/**
	 * yyyyMMddHHmmss 比如：20200523170630
	 */
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	
	/**
	 * yyyy/MM/dd HH:mm:ss 比如：2020/05/23 17:06:30
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_EN = "yyyy/MM/dd HH:mm:ss";
	
	/**
	 * yyyy/M/d H:m:s 比如：2020/5/23 17:6:30
	 */
	public static final String YYYY_M_D_H_M_S_EN = "yyyy/M/d H:m:s";

	/**
	 * yyyy年MM月dd日 HH:mm:ss 比如：2020年05月23日 17:06:30
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_CN = "yyyy年MM月dd日 HH:mm:ss";

	/**
	 * yyyy年MM月dd日 HH时mm分ss秒 比如：2020年05月23日 17时06分30秒
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_CN_ALL = "yyyy年MM月dd日 HH时mm分ss秒";
	
	/**
	 * yyyy-MM-dd HH:mm 比如：2020-05-23 17:06
	 */
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	
	/**
	 * yyyy-M-d H:m 比如：2020-5-23 17:6
	 */
	public static final String YYYY_M_D_H_M = "yyyy-M-d H:m";
	
	/**
	 * yyyyMMddHHmm 比如：202005231706
	 */
	public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";

	/**
	 * yyyy/MM/dd HH:mm 比如：2020/05/23 17:06
	 */
	public static final String YYYY_MM_DD_HH_MM_EN = "yyyy/MM/dd HH:mm";
	
	/**
	 * yyyy/M/d H:m 比如：2020/5/23 17:6
	 */
	public static final String YYYY_M_D_H_M_EN = "yyyy/M/d H:m";
	
	/**
	 * yyyy/M/d h:m a 比如：2020/5/23 5:6 下午
	 */
	public static final String YYYY_M_D_H_M_A_EN = "yyyy/M/d h:m a";
	
	/**
	 * MM-dd HH:mm 比如：05-23 17:06
	 */
	public static final String MM_DD_HH_MM = "MM-dd HH:mm";

	/**
	 * MM月dd日 HH:mm 比如：05月23日 17:06
	 */
	public static final String MM_DD_HH_MM_CN = "MM月dd日 HH:mm";

	/**
	 * MM-dd HH:mm:ss 比如：05-23 17:06:30
	 */
	public static final String MM_DD_HH_MM_SS = "MM-dd HH:mm:ss";

	/**
	 * MM月dd日 HH:mm:ss 比如：05月23日 17:06:30
	 */
	public static final String MM_DD_HH_MM_SS_CN = "MM月dd日 HH:mm:ss";

	/**
	 * yyyy年MM月dd日 hh:mm:ss a 比如：2020年05月23日 05:06:30 下午  如果需要 显示PM 需要设置 Locale.ENGLISH
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_A_CN = "yyyy年MM月dd日 hh:mm:ss a";
	
	/**
	 * yyyy年MM月dd日 hh时mm分ss秒 a 比如：2020年05月23日 17时06分30秒 下午  如果需要 显示PM 需要设置 Locale.ENGLISH
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_A_CN_ALL = "yyyy年MM月dd日 hh时mm分ss秒 a";
	
	
	
	// ==================================yyyy-MM-dd HH:mm:ss.SSS 相关Pattern==================================

	/**
	 * yyyy-MM-dd HH:mm:ss.SSS 比如：2020-05-23 17:06:30.272
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	
	/**
	 * yyyy-MM-dd HH:mm:ss,SSS 比如：2020-05-23 17:06:30,272
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_SSS_COMMA = "yyyy-MM-dd HH:mm:ss,SSS";
	
	/**
	 * yyyyMMddHHmmssSSS 比如：20200523170630272
	 */
	public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	
	/**
	 * yyyy-M-d H:m:s.SSS 比如：2020-5-23 17:6:30.272
	 */
	public static final String YYYY_M_D_H_M_S_SSS = "yyyy-M-d H:m:s.SSS";

	/**
	 * yyyy/M/d H:m:s.SSS 比如：2020/5/23 17:6:30.272
	 */
	public static final String YYYY_M_D_H_M_S_SSS_EN = "yyyy/M/d H:m:s.SSS";
	
	/**
	 * yyyy-M-d H:m:s,SSS 比如：2020-5-23 17:6:30,272
	 */
	public static final String YYYY_M_D_H_M_S_SSS_COMMA = "yyyy-M-d H:m:s,SSS";
	
	
	// ==================================yyyy-MM-dd HH:mm:ss.SSSSSS 相关Pattern==================================	
	
	/**
	 * yyyy-MM-dd HH:mm:ss.SSSSSS 比如：2020-05-23 17:06:30.272150
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_SSSSSS = "yyyy-MM-dd HH:mm:ss.SSSSSS";
	
	
	
	// ==================================yyyy-MM-dd HH:mm:ss.SSSSSSSSS 相关Pattern==================================	
	
	/**
	 * yyyy-MM-dd HH:mm:ss.SSSSSSSSS 比如：2020-05-23 17:06:30.272150620
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_SSSSSSSSS = "yyyy-MM-dd HH:mm:ss.SSSSSSSSS";	

	
	
	// ==================================Iso相关Pattern 包含 T==================================
	
	/**
	 * yyyy-MM-dd'T'HH:mm:ssZ 比如：2020-05-23T17:06:30+0800 2020-05-23T09:06:30+0000
	 */
	public static final String YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ssZ";

	/**
	 * yyyy-MM-dd'T'HH:mm:ssxxx 比如：2020-05-23T17:06:30+08:00 2020-05-23T09:06:30+00:00 0时区时末尾 为+00:00
	 */
	public static final String YYYY_MM_DD_T_HH_MM_SS_XXX = "yyyy-MM-dd'T'HH:mm:ssxxx";
	
	/**
	 * yyyy-MM-dd'T'HH:mm:ssXXX 比如：2020-05-23T17:06:30+08:00 2020-05-23T09:06:30Z 0时区时末尾 为Z
	 */
	public static final String YYYY_MM_DD_T_HH_MM_SS_XXX_Z = "yyyy-MM-dd'T'HH:mm:ssXXX";

	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSZ 比如：2020-05-23T17:06:30.272+0800 2020-05-23T09:06:30.272+0000
	 */
	public static final String YYYY_MM_DD_T_HH_MM_SS_SSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSxxx 比如：2020-05-23T17:06:30.272+08:00 2020-05-23T09:06:30.272+00:00
	 */
	public static final String YYYY_MM_DD_T_HH_MM_SS_SSS_XXX = "yyyy-MM-dd'T'HH:mm:ss.SSSxxx";

	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSXXX 比如：2020-05-23T17:06:30.272+08:00 2020-05-23T09:06:30.272Z 0时区时末尾 为Z
	 */
	public static final String YYYY_MM_DD_T_HH_MM_SS_SSS_XXX_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	
	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ 比如：2020-05-23T17:06:30.272150+0800 2020-05-23T09:06:30.272150+0000
	 */
	public static final String YYYY_MM_DD_T_HH_MM_SS_SSSSSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ";

	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSSSSxxx 比如：2020-05-23T17:06:30.272150+08:00 2020-05-23T09:06:30.272150+00:00
	 */
	public static final String YYYY_MM_DD_T_HH_MM_SS_SSSSSS_XXX = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSxxx";

	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX 比如：2020-05-23T17:06:30.272150+08:00 2020-05-23T09:06:30.272150Z 0时区时末尾 为Z
	 */
	public static final String YYYY_MM_DD_T_HH_MM_SS_SSSSSS_XXX_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX";
	
	
	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSZ 比如：2020-05-23T17:06:30.272150620+0800 2020-05-23T09:06:30.272150620+0000
	 */
	public static final String YYYY_MM_DD_T_HH_MM_SS_SSSSSSSSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSZ";

	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSxxx 比如：2020-05-23T17:06:30.272150620+08:00 2020-05-23T09:06:30.272150620+00:00
	 */
	public static final String YYYY_MM_DD_T_HH_MM_SS_SSSSSSSSS_XXX = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSxxx";

	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSXXX 比如：2020-05-23T17:06:30.272150620+08:00 2020-05-23T09:06:30.272150620Z 0时区时末尾 为Z
	 */
	public static final String YYYY_MM_DD_T_HH_MM_SS_SSSSSSSSS_XXX_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSXXX";	
	
	
	// ==================================其他格式 Pattern==================================

	/**
	 * Date 默认格式 EEE MMM dd HH:mm:ss zzz yyyy 比如：  Sat May 23 17:06:30 CST 2020
	 */
	public static final String EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY = "EEE MMM dd HH:mm:ss zzz yyyy";

}
