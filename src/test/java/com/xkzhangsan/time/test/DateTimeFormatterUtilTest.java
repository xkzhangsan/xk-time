package com.xkzhangsan.time.test;

import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 日期格式化和解析测试类
 * @author xkzhangsan
 * @date 2020年4月29日
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
	public void dateFormatTest(){
		Date date = DateTimeFormatterUtil.parseToDate("2020-04-29 14:46:29.709", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSS_FMT);
		
		Assert.assertEquals("2020", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_FMT));
		Assert.assertEquals("2020-04", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_FMT));
		Assert.assertEquals("2020年04月", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_CN_FMT));
		Assert.assertEquals("2020/04", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_EN_FMT));
		Assert.assertEquals("202004", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMM_FMT));


		Assert.assertEquals("2020-04-29", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_FMT));
		Assert.assertEquals("2020年04月29日", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_CN_FMT));
		Assert.assertEquals("2020/04/29", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_EN_FMT));
		Assert.assertEquals("2020.04.29", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_POINT_FMT));
		Assert.assertEquals("20200429", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDD_FMT));

		Assert.assertEquals("2020-04-29 14:46", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_FMT));
		Assert.assertEquals("202004291446", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMM_FMT));

		Assert.assertEquals("2020-04-29 14:46:29", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT));
		Assert.assertEquals("2020年04月29日 14:46:29", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_FMT));
		Assert.assertEquals("20200429144629", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMMSS_FMT));

		Assert.assertEquals("2020-04-29 14:46:29.709", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSS_FMT));
		Assert.assertEquals("20200429144629709", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMMSSSSS_FMT));

		Assert.assertEquals("04-29 14:46", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_FMT));
		Assert.assertEquals("04月29日 14:46", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_CN_FMT));
		Assert.assertEquals("04-29 14:46:29", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_SS_FMT));
		Assert.assertEquals("04月29日 14:46:29", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_SS_CN_FMT));

		Assert.assertEquals("04-29", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_FMT));
		Assert.assertEquals("04月29日", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_CN_FMT));

		Assert.assertEquals("14:46:29", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_SS_FMT));
		Assert.assertEquals("144629", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HHMMSS_FMT));
		// Excel 中特有格式
		Assert.assertEquals("20/04/29", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YY_MM_DD_EN_FMT));
		Assert.assertEquals("04/29/20", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_YY_EN_FMT));
		Assert.assertEquals("04/29", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_EN_FMT));
		Assert.assertEquals("14时46分29秒", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_SS_CN_FMT));
		Assert.assertEquals("14时46分", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_CN_FMT));
		Assert.assertEquals("14:46", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_FMT));
		Assert.assertEquals("02:46 下午", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_A_FMT));
		Assert.assertEquals("2020年04月29日 02:46:29 下午", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_A_CN_FMT));
		Assert.assertEquals("2020年04月29日 14时46分29秒", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_ALL_FMT));
		Assert.assertEquals("2020年04月29日 14时46分29秒 下午", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_A_CN_ALL_FMT));
		Assert.assertEquals("2020/04/29 14:46:29", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_EN_FMT));
		Assert.assertEquals("2020/04/29 14:46", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_EN_FMT));
		Assert.assertEquals("2020年04月29日 02:46:29 PM", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_A_AM_PM_CN_FMT));
		Assert.assertEquals("2020年04月29日 14时46分29秒 PM", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_A_AM_PM_CN_ALL_FMT));
		Assert.assertEquals("02:46 PM", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_A_AM_PM_FMT));
		Assert.assertEquals("2020/4/29 14:46:29", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_M_D_H_M_S_EN_FMT));
		// Date 默认格式化
		Assert.assertEquals("Wed Apr 29 14:46:29 CST 2020", DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY_FMT));
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
		Assert.assertEquals("2020-4-29T9:18:11.611+02:00", DateTimeFormatterUtil.format(zonedDateTime, DateTimeFormatterUtil.YYYY_M_D_T_H_M_S_SSS_XXX_FMT));
		
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
}
