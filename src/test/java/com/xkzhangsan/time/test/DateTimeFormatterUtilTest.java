package com.xkzhangsan.time.test;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.formatter.DateFormatPattern;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期格式化和解析测试类
 * @author xkzhangsan
 */
public class DateTimeFormatterUtilTest {
	
	@Test
	public void simpleFormatTest(){
		Date d = DateTimeFormatterUtil.parseDateTimeStrToDate("2019-12-01 17:03:03");

		String dateTimeStr = DateTimeFormatterUtil.formatToDateTimeStr(d);
		Assert.assertEquals("2019-12-01 17:03:03", dateTimeStr);

		String dateStr = DateTimeFormatterUtil.formatToDateStr(d);
		Assert.assertEquals("2019-12-01", dateStr);

		String timeStr = DateTimeFormatterUtil.formatToTimeStr(d);
		Assert.assertEquals("17:03:03", timeStr);
	}
	
	
	@Test
	public void parseToDateTest(){
		Date date1 = DateTimeConverterUtil.toDate(LocalDateTime.of(2020, 4, 29,14,46,29));
		Date date2 = DateTimeFormatterUtil.parseToDate("2020-04-29 14:46:29", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT);
		Assert.assertEquals(date1, date2);

		Date date3 = DateTimeConverterUtil.toDate(LocalDate.of(2020, 4, 29));
		Date date4 = DateTimeFormatterUtil.parseToDate("2020-04-29", DateTimeFormatterUtil.YYYY_MM_DD_FMT);
		Assert.assertEquals(date3, date4);
		
		Date date5 = DateTimeConverterUtil.toDate(LocalDate.of(2020, 5, 1));
		Date date6 = DateTimeFormatterUtil.parseToDate("2020-5-1", DateTimeFormatterUtil.YYYY_M_D_FMT);
		Assert.assertEquals(date5, date6);
		
		
		// Date 默认格式解析
		Date date7 = DateTimeConverterUtil.toDate(LocalDateTime.of(2020, 5, 21,22,58,5));
		Date date8 = DateTimeFormatterUtil.parseToDate("Thu May 21 22:58:05 CST 2020", DateTimeFormatterUtil.EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY_FMT);
		Assert.assertEquals(date7, date8);
		
		// Date 自动解析
		Date date9 = DateTimeFormatterUtil.smartParseToDate("2020-05-01");
		Date date10 = DateTimeFormatterUtil.smartParseToDate("2020/5/1");
		Assert.assertEquals(date9, date10);
		Date date11 = DateTimeFormatterUtil.smartParseToDate("2020/5/1 0:0:0");
		Assert.assertEquals(date9, date11);
		Date date12 = DateTimeFormatterUtil.smartParseToDate("2020/05/01 00:00:00");
		Assert.assertEquals(date9, date12);
		Date date13 = DateTimeFormatterUtil.smartParseToDate("2020-05-01T00:00:00.000+08:00");
		Assert.assertEquals(date9, date13);
		Date date14 = DateTimeFormatterUtil.smartParseToDate("2020/05/01T00:00:00.000+08:00");
		Assert.assertEquals(date9, date14);
	}

	@Test
	public void parseToLocalDateTimeTest(){
		// LocalDateTime 自动解析
		LocalDateTime ldt9 = DateTimeFormatterUtil.smartParseToLocalDateTime("2020/05/01 00:00:00");
		LocalDateTime ldt10 = DateTimeFormatterUtil.smartParseToLocalDateTime("2020/5/1");
		Assert.assertEquals(ldt9, ldt10);
		LocalDateTime ldt11 = DateTimeFormatterUtil.smartParseToLocalDateTime("2020/5/1 0:0:0");
		Assert.assertEquals(ldt9, ldt11);
		LocalDateTime ldt13 = DateTimeFormatterUtil.smartParseToLocalDateTime("2020-05-01T00:00:00.000+08:00");
		Assert.assertEquals(ldt9, ldt13);
		LocalDateTime ldt14 = DateTimeFormatterUtil.smartParseToLocalDateTime("2020/05/01T00:00:00.000+08:00");
		Assert.assertEquals(ldt9, ldt14);
	}
	
	/**
	 * 时区时间格式化和ISO常用格式化
	 * YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ssZ"
	 */
	@Test
	public void zonedDateTimeFormatTest(){
		//默认为巴黎时区
		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2020-04-29T09:18:11.611+02:00[Europe/Paris]");
		Assert.assertEquals("2020-04-29T09:18:11+0200", DateTimeFormatterUtil.format(zonedDateTime, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_Z_FMT));
		Assert.assertEquals("2020-04-29T09:18:11.611+0200", DateTimeFormatterUtil.format(zonedDateTime, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_SSS_Z_FMT));
		Assert.assertEquals("2020-04-29T09:18:11+02:00", DateTimeFormatterUtil.format(zonedDateTime, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_XXX_FMT));
		Assert.assertEquals("2020-04-29T09:18:11.611+02:00", DateTimeFormatterUtil.format(zonedDateTime, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_SSS_XXX_FMT));
		Assert.assertEquals("2020-04-29T09:18:11+02:00", DateTimeFormatterUtil.format(zonedDateTime, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_XXX_Z_FMT));
		Assert.assertEquals("2020-04-29T09:18:11.611+02:00", DateTimeFormatterUtil.format(zonedDateTime, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_SSS_XXX_Z_FMT));
		
		Assert.assertEquals("2020-04-29+02:00",zonedDateTime.format(DateTimeFormatterUtil.ISO_DATE_FMT));
		Assert.assertEquals("2020-04-29T09:18:11.611+02:00[Europe/Paris]",zonedDateTime.format(DateTimeFormatterUtil.ISO_DATE_TIME_FMT));
		Assert.assertEquals("2020-04-29T07:18:11.611Z",zonedDateTime.format(DateTimeFormatterUtil.ISO_INSTANT_FMT));
		Assert.assertEquals("2020-04-29",zonedDateTime.format(DateTimeFormatterUtil.ISO_LOCAL_DATE_FMT));
		Assert.assertEquals("2020-04-29T09:18:11.611",zonedDateTime.format(DateTimeFormatterUtil.ISO_LOCAL_DATE_TIME_FMT));
		Assert.assertEquals("09:18:11.611",zonedDateTime.format(DateTimeFormatterUtil.ISO_LOCAL_TIME_FMT));

		Assert.assertEquals("09:18:11.611+02:00",zonedDateTime.format(DateTimeFormatterUtil.ISO_TIME_FMT));
		Assert.assertEquals("2020-W18-3+02:00",zonedDateTime.format(DateTimeFormatterUtil.ISO_WEEK_DATE_FMT));
		Assert.assertEquals("2020-04-29T09:18:11.611+02:00[Europe/Paris]",zonedDateTime.format(DateTimeFormatterUtil.ISO_ZONED_DATE_TIME_FMT));
		Assert.assertEquals("20200429+0200",zonedDateTime.format(DateTimeFormatterUtil.BASIC_ISO_DATE_FMT));
		
		//其他格式化重新设置时区，用于非系统默认时区时间格式化
		Assert.assertEquals("2020-04-29 09:18:11.611",zonedDateTime.format(DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSS_FMT.withZone(ZoneId.of("Europe/Paris"))));
		
		//转换其他时区格式，比如上海时区
		Assert.assertEquals("2020-04-29T15:18:11.611+08:00[Asia/Shanghai]", DateTimeFormatterUtil.format(zonedDateTime,
				DateTimeFormatterUtil.ISO_ZONED_DATE_TIME_FMT, DateTimeFormatterUtil.SHANGHAI_ZONE_ID));
		
		//Date 默认格式解析
		Assert.assertEquals("2020-04-29T15:18:11+08:00",DateTimeFormatterUtil.format(zonedDateTime, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_XXX_FMT, "Asia/Shanghai"));
	}
	
	/**
	 * 时区时间解析
	 * YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ssZ"
	 */
	@Test
	public void parseToZonedDateTimeTest(){
		String text = "2020-02-18T22:37:55+0800";
		ZonedDateTime zonedDateTime = DateTimeFormatterUtil.parseToZonedDateTime(text, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_Z_FMT);
		Assert.assertNotNull(zonedDateTime);

		String text2 = "2020-02-19T12:30:25.121+08:00[Asia/Shanghai]";
		ZonedDateTime zonedDateTime2 = DateTimeFormatterUtil.parseToZonedDateTime(text2, DateTimeFormatterUtil.ISO_ZONED_DATE_TIME_FMT);
		ZonedDateTime zonedDateTime3 = ZonedDateTime.parse(text2);
		Assert.assertEquals(zonedDateTime2, zonedDateTime3);
	}
	
	/**
	 * 格式化 yyyy-MM-dd相关格式
	 */
	@Test
	public void formatDateTest(){
		Date date = DateTimeFormatterUtil.smartParseToDate("2020-05-23T17:06:30.272+08:00");
		
		Assert.assertEquals("2020-05-23", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_FMT));
		
		Assert.assertEquals("2020-5-23", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_D_FMT));
		
		Assert.assertEquals("20200523", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDD_FMT));
		
		Assert.assertEquals("2020/05/23", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_EN_FMT));
		
		Assert.assertEquals("2020/5/23", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_D_EN_FMT));
		
		Assert.assertEquals("2020年05月23日", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_CN_FMT));
		
		Assert.assertEquals("2020年5月23日", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_D_CN_FMT));
		
		Assert.assertEquals("2020.05.23", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_POINT_FMT));
	
		Assert.assertEquals("2020.5.23", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_D_POINT_FMT));
		
		Assert.assertEquals("20/05/23", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YY_MM_DD_EN_FMT));
		
		Assert.assertEquals("20/5/23", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YY_M_D_EN_FMT));
		
		Assert.assertEquals("05/23/20", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_YY_EN_FMT));
		
		Assert.assertEquals("5/23/20", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.M_D_YY_EN_FMT));
		
		Assert.assertEquals("2020-05-23 星期六", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_E_FMT));
	
		Assert.assertEquals("20", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YY_FMT));
	
		Assert.assertEquals("2020", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_FMT));
		
		Assert.assertEquals("2020-05", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_FMT));
		
		Assert.assertEquals("202005", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMM_FMT));

		Assert.assertEquals("2020/05", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_EN_FMT));
		
		Assert.assertEquals("2020年05月", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_CN_FMT));
		
		Assert.assertEquals("2020年5月", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_CN_FMT));
		
		Assert.assertEquals("05-23", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_FMT));
		
		Assert.assertEquals("0523", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MMDD_FMT));
		
		Assert.assertEquals("05/23", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_EN_FMT));
		
		Assert.assertEquals("5/23", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.M_D_EN_FMT));
		
		Assert.assertEquals("05月23日", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_CN_FMT));
	
		Assert.assertEquals("5月23日", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.M_D_CN_FMT));
	}
	
	/**
	 * 解析 yyyy-MM-dd相关格式
	 */
	@Test
	public void smartParseToDateTest1(){
		
		Date date = DateTimeFormatterUtil.smartParseToDate("2020-05-23");
		
		Assert.assertEquals(date, DateTimeFormatterUtil.smartParseToDate("2020-5-23"));
		
		Assert.assertEquals(date, DateTimeFormatterUtil.smartParseToDate("20200523"));
		
		Assert.assertEquals(date, DateTimeFormatterUtil.smartParseToDate("2020/05/23"));
		
		Assert.assertEquals(date, DateTimeFormatterUtil.smartParseToDate("2020/5/23"));
		
		Assert.assertEquals(date, DateTimeFormatterUtil.smartParseToDate("2020年05月23日"));
		
		Assert.assertEquals(date, DateTimeFormatterUtil.smartParseToDate("2020年5月23日"));
		
		Assert.assertEquals(date, DateTimeFormatterUtil.smartParseToDate("2020.05.23"));
	
		Assert.assertEquals(date, DateTimeFormatterUtil.smartParseToDate("2020.5.23"));
	}
	
	/**
	 * 格式化 HH:mm:ss相关格式
	 */
	@Test
	public void formatTimeTest(){
		Date date = DateTimeFormatterUtil.smartParseToDate("2020-05-23T17:06:30.272+08:00");
		
		Assert.assertEquals("17:06:30", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_SS_FMT));
		
		Assert.assertEquals("17:6:30", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.H_M_S_FMT));
		
		Assert.assertEquals("170630", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HHMMSS_FMT));
		
		Assert.assertEquals("17时06分30秒", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_SS_CN_FMT));
		
		Assert.assertEquals("17:06", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_FMT));
		
		Assert.assertEquals("17:6", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.H_M_FMT));
		
		Assert.assertEquals("17时06分", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_CN_FMT));
		
		Assert.assertEquals("05:06 下午", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_A_FMT));
		
		Assert.assertEquals("05:06 PM", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_A_AM_PM_FMT));
	}
	
	
	/**
	 * 格式化 yyyy-MM-dd HH:mm:ss相关格式
	 */
	@Test
	public void formatDateTimeTest(){
		Date date = DateTimeFormatterUtil.smartParseToDate("2020-05-23T17:06:30.272+08:00");
		
		Assert.assertEquals("2020-05-23 17:06:30", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT));
		
		Assert.assertEquals("2020-5-23 17:6:30", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_D_H_M_S_FMT));
		
		Assert.assertEquals("20200523170630", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMMSS_FMT));
		
		Assert.assertEquals("2020/05/23 17:06:30", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_EN_FMT));
		
		Assert.assertEquals("2020/5/23 17:6:30", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_D_H_M_S_EN_FMT));
		
		Assert.assertEquals("2020年05月23日 17:06:30", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_FMT));
		
		Assert.assertEquals("2020年05月23日 17时06分30秒", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_ALL_FMT));
		
		Assert.assertEquals("2020-05-23 17:06", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_FMT));
		
		Assert.assertEquals("2020-5-23 17:6", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_D_H_M_FMT));
		
		Assert.assertEquals("202005231706", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMM_FMT));
		
		Assert.assertEquals("2020/05/23 17:06", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_EN_FMT));
		
		Assert.assertEquals("2020/5/23 17:6", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_D_H_M_EN_FMT));
		
		Assert.assertEquals("2020/5/23 5:6 下午", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_D_H_M_A_EN_FMT));
		
		Assert.assertEquals("2020/5/23 5:6 PM", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_D_H_M_A_AM_PM_EN_FMT));
		
		Assert.assertEquals("05-23 17:06", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_FMT));
		
		Assert.assertEquals("05月23日 17:06", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_CN_FMT));
		
		Assert.assertEquals("05-23 17:06:30", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_SS_FMT));
		
		Assert.assertEquals("05月23日 17:06:30", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_SS_CN_FMT));
		
		Assert.assertEquals("2020年05月23日 05:06:30 下午", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_A_CN_FMT));
		
		Assert.assertEquals("2020年05月23日 05:06:30 PM", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_A_AM_PM_CN_FMT));
		
		Assert.assertEquals("2020年05月23日 05时06分30秒 下午", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_A_CN_ALL_FMT));
	}
	
	/**
	 * 解析 yyyy-MM-dd HH:mm:ss相关格式
	 */
	@Test
	public void smartParseToDateTest2(){
		
		Date date1 = DateTimeFormatterUtil.smartParseToDate("2020-05-23 17:06:30");
		
		Assert.assertEquals(date1, DateTimeFormatterUtil.smartParseToDate("2020-5-23 17:6:30"));
		
		Assert.assertEquals(date1, DateTimeFormatterUtil.smartParseToDate("20200523170630"));
		
		Assert.assertEquals(date1, DateTimeFormatterUtil.smartParseToDate("2020/05/23 17:06:30"));
		
		Assert.assertEquals(date1, DateTimeFormatterUtil.smartParseToDate("2020/5/23 17:6:30"));
		
		Assert.assertEquals(date1, DateTimeFormatterUtil.smartParseToDate("2020年05月23日 17:06:30"));
		
		Assert.assertEquals(date1, DateTimeFormatterUtil.smartParseToDate("2020年05月23日 17时06分30秒"));
		
		Date date2 = DateTimeFormatterUtil.smartParseToDate("2020-05-23 17:06");
		
		Assert.assertEquals(date2, DateTimeFormatterUtil.smartParseToDate("2020-5-23 17:6"));
		
		Assert.assertEquals(date2, DateTimeFormatterUtil.smartParseToDate("202005231706"));
		
		Assert.assertEquals(date2, DateTimeFormatterUtil.smartParseToDate("2020/05/23 17:06"));
		
		Assert.assertEquals(date2, DateTimeFormatterUtil.smartParseToDate("2020/5/23 17:6"));
		
	}
	
	/**
	 * 格式化yyyy-MM-dd HH:mm:ss.SSS相关格式
	 */
	@Test
	public void formatDateTimeWithMillisecondTest(){
		Date date = DateTimeFormatterUtil.smartParseToDate("2020-05-23T17:06:30.272+08:00");
		
		Assert.assertEquals("2020-05-23 17:06:30.272", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSS_FMT));
		
		Assert.assertEquals("2020-05-23 17:06:30,272", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSS_COMMA_FMT));
		
		Assert.assertEquals("20200523170630272", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMMSSSSS_FMT));
		
		Assert.assertEquals("2020-5-23 17:6:30.272", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_D_H_M_S_SSS_FMT));
		
		Assert.assertEquals("2020/5/23 17:6:30.272", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_D_H_M_S_SSS_EN_FMT));
		
		Assert.assertEquals("2020-5-23 17:6:30,272", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_D_H_M_S_SSS_COMMA_FMT));
	}
	
	/**
	 * 解析yyyy-MM-dd HH:mm:ss.SSS相关格式
	 */
	@Test
	public void smartParseToDateTest3(){
		
		Date date = DateTimeFormatterUtil.smartParseToDate("2020-05-23 17:06:30.272");
		
		Assert.assertEquals(date, DateTimeFormatterUtil.smartParseToDate("2020-05-23 17:06:30,272"));
		
		Assert.assertEquals(date, DateTimeFormatterUtil.smartParseToDate("20200523170630272"));
		
		Assert.assertEquals(date, DateTimeFormatterUtil.smartParseToDate("2020-5-23 17:6:30.272"));
		
		Assert.assertEquals(date, DateTimeFormatterUtil.smartParseToDate("2020/5/23 17:6:30.272"));
		
		Assert.assertEquals(date, DateTimeFormatterUtil.smartParseToDate("2020-5-23 17:6:30,272"));
	}
	
	
	/**
	 * 格式化Iso相关格式
	 */
	@Test
	public void formatDateTimeWithZoneTest(){
		Date date = DateTimeFormatterUtil.smartParseToDate("2020-05-23T17:06:30.272+08:00");
		
		Assert.assertEquals("2020-05-23T17:06:30+0800", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_Z_FMT));
		
		Assert.assertEquals("2020-05-23T17:06:30+08:00", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_XXX_FMT));
		
		Assert.assertEquals("2020-05-23T17:06:30+08:00", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_XXX_Z_FMT));
		
		Assert.assertEquals("2020-05-23T17:06:30.272+0800", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_SSS_Z_FMT));
		
		Assert.assertEquals("2020-05-23T17:06:30.272+08:00", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_SSS_XXX_FMT));
		
		Assert.assertEquals("2020-05-23T17:06:30.272+08:00", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_SSS_XXX_Z_FMT));
	}
	
	/**
	 * 解析Iso相关格式
	 */
	@Test
	public void smartParseToDateTest4(){
		
		Date date = DateTimeFormatterUtil.smartParseToDate("2020-05-23T17:06:30.272+08:00[Asia/Shanghai]");
		
		Assert.assertEquals(date.toString(), DateTimeFormatterUtil.smartParseToDate("2020-05-23T17:06:30+08:00").toString());
		
		Assert.assertEquals(date.toString(), DateTimeFormatterUtil.smartParseToDate("2020-05-23T17:06:30+08:00").toString());
		
		Assert.assertEquals(date.toString(), DateTimeFormatterUtil.smartParseToDate("2020-05-23T17:06:30Z").toString());
		
		Assert.assertEquals(date.toString(), DateTimeFormatterUtil.smartParseToDate("2020-05-23T17:06:30.272+0800").toString());
		
		Assert.assertEquals(date.toString(), DateTimeFormatterUtil.smartParseToDate("2020-05-23T17:06:30.272+08:00").toString());
		
		Assert.assertEquals(date.toString(), DateTimeFormatterUtil.smartParseToDate("2020-05-23T17:06:30.272+08:00").toString());
		
		// parseIsoToLocalDateTime 方法
		
		Assert.assertEquals(date.toString(), DateTimeFormatterUtil.parseIsoToDate("2020-05-23T17:06:30.272+08:00[Asia/Shanghai]").toString());
		
		Assert.assertEquals(date.toString(), DateTimeFormatterUtil.parseIsoToDate("2020-05-23T17:06:30+08:00").toString());
		
		Assert.assertEquals(date.toString(), DateTimeFormatterUtil.parseIsoToDate("2020-05-23T17:06:30+08:00").toString());
		
		Assert.assertEquals(date.toString(), DateTimeFormatterUtil.parseIsoToDate("2020-05-23T17:06:30.272+0800").toString());
		
		Assert.assertEquals(date.toString(), DateTimeFormatterUtil.parseIsoToDate("2020-05-23T17:06:30.272+08:00").toString());
		
		Assert.assertEquals(date.toString(), DateTimeFormatterUtil.parseIsoToDate("2020-05-23T17:06:30.272+08:00").toString());
		
		Assert.assertEquals(date.toString(), DateTimeFormatterUtil.parseIsoToDate("2020-05-23T17:06:30.272Z").toString());
	}
	
	/**
	 * 其他格式解析
	 */
	@Test
	public void parseToDateTest5(){
		//时间戳字符串解析
		Date date1 = DateTimeFormatterUtil.parseEpochMilliToDate("1590224790000");
		
		//Date默认格式解析
		Date date2 = DateTimeFormatterUtil.parseDateDefaultStrToDate("Sat May 23 17:06:30 CST 2020");
		
		Assert.assertEquals(date1, date2);
	}
	
	/**
	 * 含纳秒时间格式化
	 */
	@Test
	public void formatTimeWithNanoTest(){
		LocalDateTime localDateTime = DateTimeFormatterUtil.smartParseToLocalDateTime("2020-05-23T17:06:30.272150620+08:00");
		//时间格式化
		Assert.assertEquals("17:06:30.272", DateTimeFormatterUtil.format(localDateTime, DateTimeFormatterUtil.HH_MM_SS_SSS_FMT));
		Assert.assertEquals("17:06:30.272150", DateTimeFormatterUtil.format(localDateTime, DateTimeFormatterUtil.HH_MM_SS_SSSSSS_FMT));
		Assert.assertEquals("17:06:30.272150620", DateTimeFormatterUtil.format(localDateTime, DateTimeFormatterUtil.HH_MM_SS_SSSSSSSSS_FMT));
		
		//日期时间格式化
		Assert.assertEquals("2020-05-23 17:06:30.272150", DateTimeFormatterUtil.format(localDateTime, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSSSSS_FMT));
		Assert.assertEquals("2020-05-23 17:06:30.272150620", DateTimeFormatterUtil.format(localDateTime, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSSSSSSSS_FMT));

		//ISO日期时间格式化
		Assert.assertEquals("2020-05-23T17:06:30.272150620+0800", DateTimeFormatterUtil.format(localDateTime, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_SSSSSSSSS_Z_FMT));
		Assert.assertEquals("2020-05-23T17:06:30.272150620+08:00", DateTimeFormatterUtil.format(localDateTime, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_SSSSSSSSS_XXX_Z_FMT));
	}
	
	/**
	 * 含纳秒时间格式解析
	 */
	@Test
	public void parseTimeWithNanoTest(){
		LocalDateTime localDateTime1 = DateTimeFormatterUtil.smartParseToLocalDateTime("2020-05-23T17:06:30.272150620+08:00");
		
		LocalDateTime localDateTime2 = DateTimeFormatterUtil.smartParseToLocalDateTime("2020-05-23T17:06:30.272150+08:00");
		
		LocalDateTime localDateTime3 = DateTimeFormatterUtil.smartParseToLocalDateTime("2020-05-23T17:06:30.272+08:00");
		
		Assert.assertTrue(localDateTime3.isBefore(localDateTime2));
		
		Assert.assertTrue(localDateTime2.isBefore(localDateTime1));
	}
	
	/**
	 * 含纳秒Timestamp时间格式化
	 */
	@Test
	public void formatTimestampStyleTest(){
		Date date = DateTimeFormatterUtil.smartParseToDate("2020-05-23 17:06:30");
		//时间格式化
		Assert.assertEquals("2020-05-23 17:06:30.0", DateTimeFormatterUtil.formatTimestampStyle(date));
		
		LocalDateTime localDateTime1 = DateTimeFormatterUtil.smartParseToLocalDateTime("2020-05-23 17:06:30.272150");
		Assert.assertEquals("2020-05-23 17:06:30.27215",DateTimeFormatterUtil.formatTimestampStyle(localDateTime1));
		
		LocalDateTime localDateTime2 = DateTimeFormatterUtil.smartParseToLocalDateTime("2020-05-23 17:06:30.272150620");
		Assert.assertEquals("2020-05-23 17:06:30.27215062",DateTimeFormatterUtil.formatTimestampStyle(localDateTime2));
	}
	
	/**
	 * 含纳秒Timestamp时间格式解析
	 */
	@Test
	public void parseTimestampStyleTest(){
		
		Date date = DateTimeFormatterUtil.smartParseToDate("2020-05-23 17:06:30");
		
		Date date2 = DateTimeFormatterUtil.parseTimestampStyleToDate("2020-05-23 17:06:30.0");
		
		Date date3 = DateTimeFormatterUtil.parseTimestampStyleToDate("2020-05-23 17:06:30.0");
		
		Assert.assertEquals(date, date2);
		Assert.assertEquals(date2, date3);
		
		LocalDateTime localDateTime1 = DateTimeFormatterUtil.smartParseToLocalDateTime("2020-05-23 17:06:30.272150");
		
		LocalDateTime localDateTime2 = DateTimeFormatterUtil.smartParseToLocalDateTime("2020-05-23 17:06:30.27215");
		Assert.assertEquals(localDateTime1, localDateTime2);
	}
	
	/**
	 * 根据dateFormatPattern格式化
	 */
	@Test
	public void dateFormatPatternFormatTest(){
		Date date = DateTimeFormatterUtil.smartParseToDate("2020-05-23 17:06:30");
		//时间格式化
		Assert.assertEquals("2020-05-23", DateTimeFormatterUtil.format(date, DateFormatPattern.YYYY_MM_DD));
		
		LocalDateTime localDateTime = DateTimeFormatterUtil.smartParseToLocalDateTime("2020-05-23 17:06:30");
		//时间格式化
		Assert.assertEquals("2020-05-23", DateTimeFormatterUtil.format(localDateTime, DateFormatPattern.YYYY_MM_DD));
		
		//自定义格式
		Assert.assertEquals("2020-05-23 17:06", DateTimeFormatterUtil.format(date, "yyyy-MM-dd HH:mm"));
	}
	
	/**
	 * 根据dateFormatPattern解析
	 */
	@Test
	public void dateFormatPatternParseTest(){
		
		Date date = DateTimeFormatterUtil.parseToDate("2020-05-23", DateFormatPattern.YYYY_MM_DD);
		
		Date date2 = DateTimeFormatterUtil.parseToDate("2020-05-23", DateTimeFormatterUtil.YYYY_MM_DD_FMT);
		Assert.assertEquals(date, date2);
		
		LocalDateTime localDateTime1 = DateTimeFormatterUtil.parseToLocalDateTime("2020-05-23", DateFormatPattern.YYYY_MM_DD);
		
		LocalDateTime localDateTime2 = DateTimeFormatterUtil.parseToLocalDateTime("2020-05-23", DateTimeFormatterUtil.YYYY_MM_DD_FMT);
		Assert.assertEquals(localDateTime1, localDateTime2);
		
		//自定义格式
		Date date3 = DateTimeFormatterUtil.smartParseToDate("2020-05-23 17:06");
		Assert.assertEquals(date3, DateTimeFormatterUtil.parseToDate("2020-05-23 17:06", "yyyy-MM-dd HH:mm"));
	}
	
	/**
	 * 验证日期格式是否正确测试
	 */
	@Test
	public void isValidDateTimeTest(){
		Assert.assertTrue(DateTimeFormatterUtil.isValidDate("2020-12-31"));
		Assert.assertFalse(DateTimeFormatterUtil.isValidDate("2020-13-61"));
		
		Assert.assertTrue(DateTimeFormatterUtil.isValidDateTime("2020-12-31 23:00:00"));
		Assert.assertFalse(DateTimeFormatterUtil.isValidDateTime("2020-12-31 25:00:00"));
	}
	
	/**
	 * 验证根据自定义模板数组解析时间
	 */
	@Test
	public void isValidDateTimeTest2(){
		//解析为Date
		Assert.assertEquals(DateTimeCalculatorUtil.getDate(2020, 12, 31),DateTimeFormatterUtil.parseToDate("2020-12-31", new String[]{"MM", "yyyy-MM-dd"}));
		//解析为LocalDateTime
		Assert.assertEquals(LocalDateTime.of(2020, 12, 31, 0, 0),DateTimeFormatterUtil.parseToLocalDateTime("2020-12-31", new String[]{"MM", "yyyy-MM-dd"}));
	}

	/**
	 * 解析自然语言时间
	 */
	@Test
	public void parseNaturalLanguageTest(){
		Assert.assertEquals(LocalDateTime.now().getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeFormatterUtil.parseNaturalLanguageToDate("今天")));

		//自定义自然语言时间map
		Map<String, String> map = new HashMap<>();
		map.put("当天", "today");
		Assert.assertEquals(LocalDateTime.now().getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeFormatterUtil.parseNaturalLanguageToDate("当天", map)));

		Assert.assertEquals(LocalDateTime.now().plusDays(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeFormatterUtil.parseNaturalLanguageToDate("明天")));
		Assert.assertEquals(LocalDateTime.now().plusWeeks(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeFormatterUtil.parseNaturalLanguageToDate("下周")));
		Assert.assertEquals(LocalDateTime.now().plusMonths(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeFormatterUtil.parseNaturalLanguageToDate("下月")));
		Assert.assertEquals(LocalDateTime.now().plusYears(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeFormatterUtil.parseNaturalLanguageToDate("明年")));

		Assert.assertEquals(LocalDateTime.now().minusDays(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeFormatterUtil.parseNaturalLanguageToDate("昨天")));
		Assert.assertEquals(LocalDateTime.now().minusWeeks(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeFormatterUtil.parseNaturalLanguageToDate("上周")));
		Assert.assertEquals(LocalDateTime.now().minusMonths(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeFormatterUtil.parseNaturalLanguageToDate("上月")));
		Assert.assertEquals(LocalDateTime.now().minusYears(1).getDayOfMonth(), DateTimeCalculatorUtil.getDayOfMonth(DateTimeFormatterUtil.parseNaturalLanguageToDate("去年")));

	}
	
	/**
	 * 中文日期格式化测试
	 */
	@Test
	public void formatToChineseDateStrTest(){
		Date date = DateTimeCalculatorUtil.getDate(2021, 9, 11);
		Assert.assertEquals("2021年09月11日",DateTimeFormatterUtil.formatToChineseDateStr(date, false));
		Assert.assertEquals("二〇二一年九月十一日",DateTimeFormatterUtil.formatToChineseDateStr(date, true));
		
		LocalDateTime localDateTime = LocalDateTime.of(2021, 9, 11, 0, 0);
		Assert.assertEquals("2021年09月11日",DateTimeFormatterUtil.formatToChineseDateStr(localDateTime, false));
		Assert.assertEquals("二〇二一年九月十一日",DateTimeFormatterUtil.formatToChineseDateStr(localDateTime, true));
	}
	
	/**
	 * 中文日期解析测试
	 */
	@Test
	public void parseChineseDateStrToDateTest(){
		Date date = DateTimeCalculatorUtil.getDate(2021, 8, 31);
		Assert.assertEquals(date, DateTimeFormatterUtil.parseChineseDateStrToDate("2021年08月31日"));
		Assert.assertEquals(date, DateTimeFormatterUtil.parseChineseDateStrToDate("二〇二一年八月三十一日"));
		
		LocalDateTime localDateTime = LocalDateTime.of(2021, 8, 31, 0, 0);
		Assert.assertEquals(localDateTime, DateTimeFormatterUtil.parseChineseDateStrToLocalDateTime("2021年08月31日"));
		Assert.assertEquals(localDateTime, DateTimeFormatterUtil.parseChineseDateStrToLocalDateTime("二〇二一年八月三十一日"));
	}
	
}
