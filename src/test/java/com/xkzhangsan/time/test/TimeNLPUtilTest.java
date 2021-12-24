package com.xkzhangsan.time.test;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;
import com.xkzhangsan.time.nlp.TimeNLP;
import com.xkzhangsan.time.nlp.TimeNLPUtil;
import com.xkzhangsan.time.utils.CollectionUtil;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;

/**
 * 时间自然语言解析工具类测试
 *
 * @author xkzhangsan
 */
public class TimeNLPUtilTest {

    /**
     * 常见实例
     */
    @Test
    public void timeNLPTest() {

        List<TimeNLP> timeNLPList = TimeNLPUtil.parse("去年5月");
        System.out.println("去年5月");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());
        
        timeNLPList = TimeNLPUtil.parse("上月21日");
        System.out.println("上月21日");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("前年3月21日");
        System.out.println("前年3月21日");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("昨天10点去公司开会");
        System.out.println("昨天10点去公司开会");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("前天下午3点");
        System.out.println("前天下午3点");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("大前天上午5点");
        System.out.println("大前天上午5点");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("3个月之前");
        System.out.println("3个月之前");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("早上六点起床");
        System.out.println("早上六点起床");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("周一开会");
        System.out.println("周一开会");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("下下周一开会");
        System.out.println("下下周一开会");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("6:30 起床");
        System.out.println("6:30 起床");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("6-3 春游");
        System.out.println("6-3 春游");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("6月3春游");
        System.out.println("6月3春游");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("明天早上跑步");
        System.out.println("明天早上跑步");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("本周日到下周日出差");
        System.out.println("本周日到下周日出差");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(1).getTime()) + "-"
                + timeNLPList.get(1).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("周四下午三点到五点开会");
        System.out.println("周四下午三点到五点开会");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(1).getTime()) + "-"
                + timeNLPList.get(1).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("《辽宁日报》今日报道，7月18日辽宁召开省委常委扩大会，会议从下午两点半开到六点半，主要议题为：落实中央巡视整改要求。",
                "2016-07-19-00-00-00");
        System.out.println("《辽宁日报》今日报道，7月18日辽宁召开省委常委扩大会，会议从下午两点半开到六点半，主要议题为：落实中央巡视整改要求。");
        for (int i = 0; i < timeNLPList.size(); i++) {
            System.out.println("时间文本:" + timeNLPList.get(i).getTimeExpression() + ",对应时间:"
                    + timeNLPList.get(i).getTimeNormFormat());
        }
    }

    /**
     * 标准时间
     */
    @Test
    public void normStandardTimeTest() {
        List<TimeNLP> timeNLPList = TimeNLPUtil.parse("2016-07-19对应时间");
        System.out.println("2016-07-19对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("2016-07-19 15:30:10对应时间");
        System.out.println("2016-07-19 15:30:10对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("2016-07-19 15:30对应时间");
        System.out.println("2016-07-19 15:30对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());
        
        timeNLPList = TimeNLPUtil.parse("2016年07月19日对应时间");
        System.out.println("2016年07月19日对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("2016年07月19日 15:30:10对应时间");
        System.out.println("2016年07月19日 15:30:10对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("2016年07月19日 15:30对应时间");
        System.out.println("2016年07月19日 15:30对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

    }

    /**
     * 时分秒，前后解析
     */
    @Test
    public void normHourTest() {
        System.out.println("当前时间:" + DateTimeCalculatorUtil.getEpochMilliFormat());
        List<TimeNLP> timeNLPList = TimeNLPUtil.parse("1小时后对应时间");
        System.out.println("1小时后对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("1钟头后对应时间");
        System.out.println("1钟头后对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("1个半小时后对应时间");
        System.out.println("1个半小时后对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("1小时50分钟后对应时间");
        System.out.println("1小时50分钟后对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("1小时50分后对应时间");
        System.out.println("1小时50分后对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("10分钟后对应时间");
        System.out.println("10分钟后对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("10分钟30秒后对应时间");
        System.out.println("10分钟30秒后对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("50秒后对应时间");
        System.out.println("50秒后对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("180秒后对应时间");
        System.out.println("180秒后对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        timeNLPList = TimeNLPUtil.parse("1个半小时前对应时间");
        System.out.println("1个半小时前对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());
        
        timeNLPList = TimeNLPUtil.parse("1小时50分钟10秒后对应时间");
        System.out.println("1小时50分钟10秒后对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());        
    }

    /**
     * 当前时间是上午10点，那么下午三点 会识别为明天下午三点问题
     */
    @Test
    public void normHourTest2() {
        System.out.println("当前时间:" + DateTimeCalculatorUtil.getEpochMilliFormat());
        List<TimeNLP> timeNLPList = TimeNLPUtil.parse("下午3点对应时间");
        System.out.println("下午3点对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());
    }

    /**
     * 数字解析异常问题验证
     */
    @Test
    public void normDecimalStrTest() {
        System.out.println("当前时间:" + DateTimeCalculatorUtil.getEpochMilliFormat());
        List<TimeNLP> timeNLPList = TimeNLPUtil.parse("4.5对应时间");
        System.out.println("4.5对应时间");
        if (CollectionUtil.isNotEmpty(timeNLPList)) {
            System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                    + timeNLPList.get(0).getIsAllDayTime());
        } else {
            System.out.println("无匹配结果");
        }

        timeNLPList = TimeNLPUtil.parse("4.5元日对应时间");
        System.out.println("4.5元日对应时间");
        if (CollectionUtil.isNotEmpty(timeNLPList)) {
            System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                    + timeNLPList.get(0).getIsAllDayTime());
        } else {
            System.out.println("无匹配结果");
        }

        timeNLPList = TimeNLPUtil.parse("4.5日对应时间");
        System.out.println("4.5日对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());
    }

    /**
     * 并发执行，可限制时间内完成功能
     *
     * @throws TimeoutException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void parseConcurrentTest() throws InterruptedException, ExecutionException, TimeoutException {
        //1.并发执行
        System.out.println("当前时间:" + DateTimeCalculatorUtil.getEpochMilliFormat());
        List<TimeNLP> timeNLPList = TimeNLPUtil.parseConcurrent("下午3点对应时间");
        System.out.println("下午3点对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList.get(0).getTime()) + "-"
                + timeNLPList.get(0).getIsAllDayTime());

        //2.限时30毫秒完成
        List<TimeNLP> timeNLPList2 = TimeNLPUtil.parseConcurrent("下午3点对应时间", 30, TimeUnit.MILLISECONDS);
        System.out.println("下午3点对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList2.get(0).getTime()) + "-"
                + timeNLPList2.get(0).getIsAllDayTime());

        //3.使用自定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<TimeNLP> timeNLPList3 = TimeNLPUtil.parseConcurrent("下午3点对应时间", 30, TimeUnit.MILLISECONDS,
                executorService);
        System.out.println("下午3点对应时间");
        System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(timeNLPList3.get(0).getTime()) + "-"
                + timeNLPList3.get(0).getIsAllDayTime());
    }
}
