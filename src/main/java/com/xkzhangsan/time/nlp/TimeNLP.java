package com.xkzhangsan.time.nlp;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.enums.MomentEnum;
import com.xkzhangsan.time.enums.RegexEnum;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;
import com.xkzhangsan.time.utils.CollectionUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间自然语言分析
 * <p>
 * 修改自 https://github.com/shinyke/Time-NLP
 *
 * @author xkzhangsan
 */
public class TimeNLP {

    private static Map<Integer, Object> TUNIT_MAP = new HashMap<>();

    static {
        //ChronoField
        TUNIT_MAP.put(0, ChronoField.YEAR);
        TUNIT_MAP.put(1, ChronoField.MONTH_OF_YEAR);
        TUNIT_MAP.put(2, ChronoField.DAY_OF_MONTH);
        TUNIT_MAP.put(3, ChronoField.HOUR_OF_DAY);
        TUNIT_MAP.put(4, ChronoField.MINUTE_OF_HOUR);
        TUNIT_MAP.put(5, ChronoField.SECOND_OF_MINUTE);

        //ChronoUnit
        TUNIT_MAP.put(10, ChronoUnit.YEARS);
        TUNIT_MAP.put(11, ChronoUnit.MONTHS);
        TUNIT_MAP.put(12, ChronoUnit.DAYS);
        TUNIT_MAP.put(13, ChronoUnit.HOURS);
        TUNIT_MAP.put(14, ChronoUnit.MINUTES);
        TUNIT_MAP.put(15, ChronoUnit.SECONDS);
    }

    /**
     * 目标字符串
     */
    private String timeExpression = null;

    /**
     * 解析结果格式化1
     * yyyy年MM月dd日hh时mm分ss秒
     */
    private String timeNorm = "";
    /**
     * 解析结果格式化2
     * yyyy-MM-dd HH:mm:ss
     */
    private String timeNormFormat = "";
    /**
     * 解析结果时间
     */
    private Date time;
    private LocalDateTime localDateTime;

    private Boolean isAllDayTime = true;
    private boolean isFirstTimeSolveContext = true;

    private TextAnalysis textAnalysis = null;
    private TimeContext timeContext = new TimeContext();
    private TimeContext timeContextOrigin = new TimeContext();

    /**
     * 时间表达式单元构造方法
     * 该方法作为时间表达式单元的入口，将时间表达式字符串传入
     *
     * @param timeExpression 时间表达式字符串
     * @param textAnalysis   正则文件分析类
     */
    public TimeNLP(String timeExpression, TextAnalysis textAnalysis) {
        this.timeExpression = timeExpression;
        this.textAnalysis = textAnalysis;
        timeNormalization();
    }

    /**
     * 时间表达式单元构造方法
     * 该方法作为时间表达式单元的入口，将时间表达式字符串传入
     *
     * @param timeExpression 时间表达式字符串
     * @param textAnalysis   正则文件分析类
     * @param timePoint      上下文时间
     */

    public TimeNLP(String timeExpression, TextAnalysis textAnalysis, TimeContext timePoint) {
        this.timeExpression = timeExpression;
        this.textAnalysis = textAnalysis;
        this.timeContextOrigin = timePoint;
        timeNormalization();
    }

    /**
     * 获取 Date
     *
     * @return Date
     */
    public Date getTime() {
        return time;
    }

    /**
     * 标准时间解析
     * <pre>
     *     yyyy-MM-dd HH:mm:ss
     *     yyyy-MM-dd HH:mm
     *     yyyy-MM-dd
     * </pre>
     *
     * @return LocalDateTime
     */
    private LocalDateTime normStandardTime() {
        LocalDateTime localDateTime = null;
        Pattern pattern = RegexEnum.NormStandard.getPattern();
        Matcher match = pattern.matcher(timeExpression);
        Pattern patternCn = RegexEnum.NormStandardCn.getPattern();
        Matcher matchCn = patternCn.matcher(timeExpression);
        if (match.find() || matchCn.find()) {
            try {
                localDateTime = DateTimeFormatterUtil.smartParseToLocalDateTime(timeExpression);
                int[] tunit = timeContext.getTunit();
                tunit[0] = localDateTime.getYear();
                tunit[1] = localDateTime.getMonthValue();
                tunit[2] = localDateTime.getDayOfMonth();
                if (localDateTime.getHour() >= 0) {
                    tunit[3] = localDateTime.getHour();
                }
                if (localDateTime.getMinute() > 0) {
                    tunit[4] = localDateTime.getMinute();
                }
                if (localDateTime.getSecond() > 0) {
                    tunit[5] = localDateTime.getSecond();
                }
            } catch (Exception e) {
                System.out.println("normStandardTime error:" + e.getMessage());
            }
        }
        return localDateTime;
    }

    /**
     * 年-规范化方法
     * <p>
     * 该方法识别时间表达式单元的年字段
     */
    private void normYear() {
        /**假如只有两位数来表示年份*/
        Pattern pattern = RegexEnum.NormYearTwo.getPattern();
        Matcher match = pattern.matcher(timeExpression);
        if (match.find()) {
            timeContext.getTunit()[0] = Integer.parseInt(match.group());
            if (timeContext.getTunit()[0] >= 0 && timeContext.getTunit()[0] < 100) {
                if (timeContext.getTunit()[0] < 30) /**30以下表示2000年以后的年份*/
                    timeContext.getTunit()[0] += 2000;
                else/**否则表示1900年以后的年份*/
                    timeContext.getTunit()[0] += 1900;
            }

        }
        /**不仅局限于支持1XXX年和2XXX年的识别，可识别三位数和四位数表示的年份*/
        pattern = RegexEnum.NormYearFour.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find())/**如果有3位数和4位数的年份，则覆盖原来2位数识别出的年份*/ {
            timeContext.getTunit()[0] = Integer.parseInt(match.group());
        }
    }

    /**
     * 月-规范化方法
     * <p>
     * 该方法识别时间表达式单元的月字段
     */
    private void normMonth() {
        Pattern pattern = RegexEnum.NormMonth.getPattern();
        Matcher match = pattern.matcher(timeExpression);
        if (match.find()) {
            timeContext.getTunit()[1] = Integer.parseInt(match.group());

            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(1);
        }
    }

    /**
     * 月-日 兼容模糊写法
     * <p>
     * 该方法识别时间表达式单元的月、日字段
     * <p>
     * add by kexm
     */
    private void normMonthFuzzyDay() {
        Pattern pattern = RegexEnum.NormMonthFuzzyDay.getPattern();
        Matcher match = pattern.matcher(timeExpression);
        if (match.find()) {
            String matchStr = match.group();
            Pattern p = Pattern.compile("(月|\\.|\\-)");
            Matcher m = p.matcher(matchStr);
            if (m.find()) {
                int splitIndex = m.start();
                String month = matchStr.substring(0, splitIndex);
                String date = matchStr.substring(splitIndex + 1);

                timeContext.getTunit()[1] = Integer.parseInt(month);
                timeContext.getTunit()[2] = Integer.parseInt(date);

                /**处理倾向于未来时间的情况  @author kexm*/
                preferFuture(1);
            }
        }
    }

    /**
     * 日-规范化方法
     * <p>
     * 该方法识别时间表达式单元的日字段
     */
    private void normDay() {
        Pattern pattern = RegexEnum.NormDay.getPattern();
        Matcher match = pattern.matcher(timeExpression);
        if (match.find()) {
            timeContext.getTunit()[2] = Integer.parseInt(match.group());

            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(2);
        }
    }

    /**
     * 时-规范化方法
     * <p>
     * 该方法识别时间表达式单元的时字段
     */
    private void normHour() {
        Pattern pattern = RegexEnum.NormHour.getPattern();
        Matcher match = pattern.matcher(timeExpression);
        if (match.find()) {
            timeContext.getTunit()[3] = Integer.parseInt(match.group());
            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(3);
            isAllDayTime = false;
        }
        /*
         * 对关键字：早（包含早上/早晨/早间），上午，中午,午间,下午,午后,晚上,傍晚,晚间,晚,pm,PM的正确时间计算
         * 规约：
         * 1.中午/午间0-10点视为12-22点
         * 2.下午/午后0-11点视为12-23点
         * 3.晚上/傍晚/晚间/晚1-11点视为13-23点，12点视为0点
         * 4.0-11点pm/PM视为12-23点
         *
         * add by kexm
         */
        pattern = RegexEnum.NormHourDayBreak.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            if (timeContext.getTunit()[3] == -1) /**增加对没有明确时间点，只写了“凌晨”这种情况的处理 @author kexm*/
                timeContext.getTunit()[3] = MomentEnum.day_break.getHourTime();
            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(3);
            isAllDayTime = false;
        }

        pattern = RegexEnum.NormHourEarlyMorning.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            if (timeContext.getTunit()[3] == -1) /**增加对没有明确时间点，只写了“早上/早晨/早间”这种情况的处理 @author kexm*/
                timeContext.getTunit()[3] = MomentEnum.early_morning.getHourTime();
            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(3);
            isAllDayTime = false;
        }

        pattern = RegexEnum.NormHourMorning.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            if (timeContext.getTunit()[3] == -1) /**增加对没有明确时间点，只写了“上午”这种情况的处理 @author kexm*/
                timeContext.getTunit()[3] = MomentEnum.morning.getHourTime();
            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(3);
            isAllDayTime = false;
        }

        pattern = RegexEnum.NormHourNoon.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            if (timeContext.getTunit()[3] >= 0 && timeContext.getTunit()[3] <= 10)
                timeContext.getTunit()[3] += 12;
            if (timeContext.getTunit()[3] == -1) /**增加对没有明确时间点，只写了“中午/午间”这种情况的处理 @author kexm*/
                timeContext.getTunit()[3] = MomentEnum.noon.getHourTime();
            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(3);
            isAllDayTime = false;
        }

        pattern = RegexEnum.NormHourAfternoon.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            if (timeContext.getTunit()[3] >= 0 && timeContext.getTunit()[3] <= 11)
                timeContext.getTunit()[3] += 12;
            if (timeContext.getTunit()[3] == -1) /**增加对没有明确时间点，只写了“下午|午后”这种情况的处理  @author kexm*/
                timeContext.getTunit()[3] = MomentEnum.afternoon.getHourTime();
            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(3);
            isAllDayTime = false;
        }

        pattern = RegexEnum.NormHourNight.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            if (timeContext.getTunit()[3] >= 1 && timeContext.getTunit()[3] <= 11)
                timeContext.getTunit()[3] += 12;
            else if (timeContext.getTunit()[3] == 12)
                timeContext.getTunit()[3] = 0;
            else if (timeContext.getTunit()[3] == -1)
                timeContext.getTunit()[3] = MomentEnum.night.getHourTime();

            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(3);
            isAllDayTime = false;
        }

    }

    /**
     * 分-规范化方法
     * <p>
     * 该方法识别时间表达式单元的分字段
     */
    private void normMinute() {

        //特殊情况排查，比如30分后
        Pattern pattern = RegexEnum.NormMinuteSpec.getPattern();
        Matcher match = pattern.matcher(timeExpression);
        if (match.find()) {
            return;
        }

        pattern = RegexEnum.NormMinute.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            if (!match.group().equals("")) {
                timeContext.getTunit()[4] = Integer.parseInt(match.group());
                /**处理倾向于未来时间的情况  @author kexm*/
                preferFuture(4);
                isAllDayTime = false;
            }
        }
        /** 加对一刻，半，3刻的正确识别（1刻为15分，半为30分，3刻为45分）*/
        pattern = RegexEnum.NormMinuteOneQuarter.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            timeContext.getTunit()[4] = 15;
            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(4);
            isAllDayTime = false;
        }

        pattern = RegexEnum.NormMinuteHalf.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            timeContext.getTunit()[4] = 30;
            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(4);
            isAllDayTime = false;
        }

        pattern = RegexEnum.NormMinuteThreeQuarter.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            timeContext.getTunit()[4] = 45;
            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(4);
            isAllDayTime = false;
        }

    }

    /**
     * 秒-规范化方法
     * <p>
     * 该方法识别时间表达式单元的秒字段
     */
    private void normSecond() {
        //特殊情况排查，比如30秒后
        Pattern pattern = RegexEnum.NormSecondSpec.getPattern();
        Matcher match = pattern.matcher(timeExpression);
        if (match.find()) {
            return;
        }
        /*
         * 添加了省略“分”说法的时间
         * 如17点15分32
         * modified by 曹零
         */
        pattern = RegexEnum.NormSecond.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            timeContext.getTunit()[5] = Integer.parseInt(match.group());
            isAllDayTime = false;
        }
    }

    /**
     * 特殊形式的规范化方法
     * <p>
     * 该方法识别特殊形式的时间表达式单元的各个字段
     */
    private void normTotal() {
        Pattern pattern;
        Matcher match;
        String[] tmpParser;
        String tmpTarget;

        pattern = RegexEnum.NormTotalTime.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            tmpParser = new String[3];
            tmpTarget = match.group();
            tmpParser = tmpTarget.split(":");
            timeContext.getTunit()[3] = Integer.parseInt(tmpParser[0]);
            timeContext.getTunit()[4] = Integer.parseInt(tmpParser[1]);
            timeContext.getTunit()[5] = Integer.parseInt(tmpParser[2]);
            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(3);
            isAllDayTime = false;
        } else {
            pattern = RegexEnum.NormTotalTimeShort.getPattern();
            match = pattern.matcher(timeExpression);
            if (match.find()) {
                tmpParser = new String[2];
                tmpTarget = match.group();
                tmpParser = tmpTarget.split(":");
                timeContext.getTunit()[3] = Integer.parseInt(tmpParser[0]);
                timeContext.getTunit()[4] = Integer.parseInt(tmpParser[1]);
                /**处理倾向于未来时间的情况  @author kexm*/
                preferFuture(3);
                isAllDayTime = false;
            }
        }
        /*
         * 增加了:固定形式时间表达式的
         * 中午,午间,下午,午后,晚上,傍晚,晚间,晚,pm,PM
         * 的正确时间计算，规约同上
         */
        pattern = RegexEnum.NormHourNoon.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            if (timeContext.getTunit()[3] >= 0 && timeContext.getTunit()[3] <= 10)
                timeContext.getTunit()[3] += 12;
            if (timeContext.getTunit()[3] == -1) /**增加对没有明确时间点，只写了“中午/午间”这种情况的处理 @author kexm*/
                timeContext.getTunit()[3] = MomentEnum.noon.getHourTime();
            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(3);
            isAllDayTime = false;

        }

        pattern = RegexEnum.NormHourAfternoon.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            if (timeContext.getTunit()[3] >= 0 && timeContext.getTunit()[3] <= 11)
                timeContext.getTunit()[3] += 12;
            if (timeContext.getTunit()[3] == -1) /**增加对没有明确时间点，只写了“中午/午间”这种情况的处理 @author kexm*/
                timeContext.getTunit()[3] = MomentEnum.afternoon.getHourTime();
            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(3);
            isAllDayTime = false;
        }

        pattern = RegexEnum.NormTotalNight.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            if (timeContext.getTunit()[3] >= 1 && timeContext.getTunit()[3] <= 11)
                timeContext.getTunit()[3] += 12;
            else if (timeContext.getTunit()[3] == 12)
                timeContext.getTunit()[3] = 0;
            if (timeContext.getTunit()[3] == -1) /**增加对没有明确时间点，只写了“中午/午间”这种情况的处理 @author kexm*/
                timeContext.getTunit()[3] = MomentEnum.night.getHourTime();
            /**处理倾向于未来时间的情况  @author kexm*/
            preferFuture(3);
            isAllDayTime = false;
        }

        pattern = RegexEnum.NormTotalDateOne.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            tmpParser = new String[3];
            tmpTarget = match.group();
            tmpParser = tmpTarget.split("-");
            timeContext.getTunit()[0] = Integer.parseInt(tmpParser[0]);
            timeContext.getTunit()[1] = Integer.parseInt(tmpParser[1]);
            timeContext.getTunit()[2] = Integer.parseInt(tmpParser[2]);
        }

        pattern = RegexEnum.NormTotalDateTwo.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            tmpParser = new String[3];
            tmpTarget = match.group();
            tmpParser = tmpTarget.split("/");
            timeContext.getTunit()[1] = Integer.parseInt(tmpParser[0]);
            timeContext.getTunit()[2] = Integer.parseInt(tmpParser[1]);
            timeContext.getTunit()[0] = Integer.parseInt(tmpParser[2]);
        }

        /*
         * 增加了:固定形式时间表达式 年.月.日 的正确识别
         * add by 曹零
         */
        pattern = RegexEnum.NormTotalDateThree.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            tmpParser = new String[3];
            tmpTarget = match.group();
            tmpParser = tmpTarget.split("\\.");
            timeContext.getTunit()[0] = Integer.parseInt(tmpParser[0]);
            timeContext.getTunit()[1] = Integer.parseInt(tmpParser[1]);
            timeContext.getTunit()[2] = Integer.parseInt(tmpParser[2]);
        }
    }

    /**
     * 设置以上文时间为基准的时间偏移计算，日期部分
     */
    private void normBaseRelated() {
        String[] timeGrid = new String[6];
        timeGrid = timeContextOrigin.getTimeBase().split("-");
        int[] ini = new int[6];
        for (int i = 0; i < 6; i++)
            ini[i] = Integer.parseInt(timeGrid[i]);
        //设置结果
        LocalDateTime localDateTime = LocalDateTime.of(ini[0], ini[1], ini[2], ini[3], ini[4], ini[5]);

        boolean flag = false;//观察时间表达式是否因当前相关时间表达式而改变时间

        Pattern pattern = RegexEnum.NormBaseRelatedDayBefore.getPattern();
        Matcher match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag = true;
            int day = Integer.parseInt(match.group());
            localDateTime = localDateTime.minusDays(day);
        }

        pattern = RegexEnum.NormBaseRelatedDayAfter.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag = true;
            int day = Integer.parseInt(match.group());
            localDateTime = localDateTime.plusDays(day);
        }

        pattern = RegexEnum.NormBaseRelatedMonthBefore.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag = true;
            int month = Integer.parseInt(match.group());
            localDateTime = localDateTime.minusMonths(month);
        }

        pattern = RegexEnum.NormBaseRelatedMonthAfter.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag = true;
            int month = Integer.parseInt(match.group());
            localDateTime = localDateTime.plusMonths(month);
        }

        pattern = RegexEnum.NormBaseRelatedYearBefore.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag = true;
            int year = Integer.parseInt(match.group());
            localDateTime = localDateTime.minusYears(year);
        }

        pattern = RegexEnum.NormBaseRelatedYearAfter.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag = true;
            int year = Integer.parseInt(match.group());
            localDateTime = localDateTime.plusYears(year);
        }

        if (flag) {
            setUnitValues(localDateTime);
        }
    }

    /**
     * 设置以上文时间为基准的时间偏移计算，时间部分
     */
    private void normBaseTimeRelated() {
        String[] timeGrid = new String[6];
        timeGrid = timeContextOrigin.getTimeBase().split("-");
        int[] ini = new int[6];
        for (int i = 0; i < 6; i++)
            ini[i] = Integer.parseInt(timeGrid[i]);
        //设置结果
        LocalDateTime localDateTime = LocalDateTime.of(ini[0], ini[1], ini[2], ini[3], ini[4], ini[5]);

        boolean flag = false;//观察时间表达式是否因当前相关时间表达式而改变时间

        Pattern pattern = RegexEnum.NormBaseTimeRelatedHourBefore.getPattern();
        Matcher match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag = true;
            int hour = Integer.parseInt(match.group());
            localDateTime = localDateTime.minusHours(hour);
        }

        pattern = RegexEnum.NormBaseTimeRelatedHourAfter.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag = true;
            int hour = Integer.parseInt(match.group());
            localDateTime = localDateTime.plusHours(hour);
        }

        pattern = RegexEnum.NormBaseTimeRelatedHalfHourBefore.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag = true;
            localDateTime = localDateTime.minusMinutes(30);
        }

        pattern = RegexEnum.NormBaseTimeRelatedHalfHourAfter.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag = true;
            localDateTime = localDateTime.plusMinutes(30);
        }

        boolean matchMinuteBeforeFlag = false;
        pattern = RegexEnum.NormBaseTimeRelatedMinuteBefore.getPattern();
        Matcher matchMinuteBefore = pattern.matcher(timeExpression);
        if (matchMinuteBefore.find()) {
            flag = true;
            matchMinuteBeforeFlag = true;
            int minute = Integer.parseInt(matchMinuteBefore.group());
            localDateTime = localDateTime.minusMinutes(minute);
        }

        boolean matchMinuteAfterFlag = false;
        pattern = RegexEnum.NormBaseTimeRelatedMinuteAfter.getPattern();
        Matcher matchMinuteAfter = pattern.matcher(timeExpression);
        if (matchMinuteAfter.find()) {
            flag = true;
            matchMinuteAfterFlag = true;
            int minute = Integer.parseInt(matchMinuteAfter.group());
            localDateTime = localDateTime.plusMinutes(minute);
        }

        //1个小时10分钟前，组合处理
        if (matchMinuteBeforeFlag) {
            pattern = RegexEnum.NormBaseTimeRelatedHour.getPattern();
            match = pattern.matcher(timeExpression);
            if (match.find()) {
                flag = true;
                int hour = Integer.parseInt(match.group());
                localDateTime = localDateTime.minusHours(hour);
            }
        }

        if (matchMinuteAfterFlag) {
            pattern = RegexEnum.NormBaseTimeRelatedHour.getPattern();
            match = pattern.matcher(timeExpression);
            if (match.find()) {
                flag = true;
                int hour = Integer.parseInt(match.group());
                localDateTime = localDateTime.plusHours(hour);
            }
        }

        boolean matchSecondBeforeFlag = false;
        pattern = RegexEnum.NormBaseTimeRelatedSecondBefore.getPattern();
        Matcher matchSecondBefore = pattern.matcher(timeExpression);
        if (matchSecondBefore.find()) {
            flag = true;
            matchSecondBeforeFlag = true;
            int second = Integer.parseInt(matchSecondBefore.group());
            localDateTime = localDateTime.minusSeconds(second);
        }

        boolean matchSecondAfterFlag = false;
        pattern = RegexEnum.NormBaseTimeRelatedSecondAfter.getPattern();
        Matcher matchSecondAfter = pattern.matcher(timeExpression);
        if (matchSecondAfter.find()) {
            flag = true;
            matchSecondAfterFlag = true;
            int second = Integer.parseInt(matchSecondAfter.group());
            localDateTime = localDateTime.plusSeconds(second);
        }

        if (matchSecondBeforeFlag) {
            pattern = RegexEnum.NormBaseTimeRelatedHour.getPattern();
            match = pattern.matcher(timeExpression);
            if (match.find()) {
                flag = true;
                int hour = Integer.parseInt(match.group());
                localDateTime = localDateTime.minusHours(hour);
            }
            pattern = RegexEnum.NormBaseTimeRelatedMinute.getPattern();
            match = pattern.matcher(timeExpression);
            if (match.find()) {
                flag = true;
                int minute = Integer.parseInt(match.group());
                localDateTime = localDateTime.minusMinutes(minute);
            }
        }

        if (matchSecondAfterFlag) {
            pattern = RegexEnum.NormBaseTimeRelatedHour.getPattern();
            match = pattern.matcher(timeExpression);
            if (match.find()) {
                flag = true;
                int hour = Integer.parseInt(match.group());
                localDateTime = localDateTime.plusHours(hour);
            }
            pattern = RegexEnum.NormBaseTimeRelatedMinute.getPattern();
            match = pattern.matcher(timeExpression);
            if (match.find()) {
                flag = true;
                int minute = Integer.parseInt(match.group());
                localDateTime = localDateTime.plusMinutes(minute);
            }
        }

        if (flag) {
            setUnitValues(localDateTime);
        }
    }

    /**
     * 设置当前时间相关的时间表达式
     */
    private void normCurRelated() {
        String[] timeGrid = new String[6];
        timeGrid = timeContextOrigin.getOldTimeBase().split("-");
        int[] ini = new int[6];
        for (int i = 0; i < 6; i++)
            ini[i] = Integer.parseInt(timeGrid[i]);

        //设置结果
        LocalDateTime localDateTime = LocalDateTime.of(ini[0], ini[1], ini[2], ini[3], ini[4], ini[5]);

        boolean[] flag = {false, false, false};//观察时间表达式是否因当前相关时间表达式而改变时间

        Pattern pattern = RegexEnum.NormCurRelatedYearBeforeLast.getPattern();
        Matcher match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[0] = true;
            localDateTime = localDateTime.minusYears(2);
        }

        pattern = RegexEnum.NormCurRelatedYearBefore.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[0] = true;
            localDateTime = localDateTime.minusYears(1);
        }

        pattern = RegexEnum.NormCurRelatedYear.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[0] = true;
            localDateTime = localDateTime.plusYears(0);
        }

        pattern = RegexEnum.NormCurRelatedYearAfter.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[0] = true;
            localDateTime = localDateTime.plusYears(1);
        }

        pattern = RegexEnum.NormCurRelatedYearAfterNext.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[0] = true;
            localDateTime = localDateTime.plusYears(2);
        }

        pattern = RegexEnum.NormCurRelatedMonthBefore.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[1] = true;
            localDateTime = localDateTime.minusMonths(1);

        }

        pattern = RegexEnum.NormCurRelatedMonth.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[1] = true;
            localDateTime = localDateTime.plusMonths(0);
        }

        pattern = RegexEnum.NormCurRelatedMonthAfter.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[1] = true;
            localDateTime = localDateTime.plusMonths(1);
        }

        pattern = RegexEnum.NormCurRelatedDayBeforeThree.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            localDateTime = localDateTime.minusDays(3);
        }

        pattern = RegexEnum.NormCurRelatedDayBeforeLast.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            localDateTime = localDateTime.minusDays(2);
        }

        pattern = RegexEnum.NormCurRelatedDayYesterday.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            localDateTime = localDateTime.minusDays(1);
        }

        pattern = RegexEnum.NormCurRelatedDayToday.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            localDateTime = localDateTime.plusDays(0);
        }

        pattern = RegexEnum.NormCurRelatedDayTomorrow.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            localDateTime = localDateTime.plusDays(1);
        }

        pattern = RegexEnum.NormCurRelatedDayAfterNext.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            localDateTime = localDateTime.plusDays(2);
        }

        pattern = RegexEnum.NormCurRelatedDayAfterThree.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            localDateTime = localDateTime.plusDays(3);
        }

        pattern = RegexEnum.NormCurRelatedWeekBeforeLast.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            int week;
            try {
                week = Integer.parseInt(match.group());
            } catch (NumberFormatException e) {
                week = 1;
            }
            localDateTime = localDateTime.minusWeeks(2);
            localDateTime = DateTimeCalculatorUtil.withDayOfWeek(localDateTime, week);
        }

        pattern = RegexEnum.NormCurRelatedWeekBefore.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            int week;
            try {
                week = Integer.parseInt(match.group());
            } catch (NumberFormatException e) {
                week = 1;
            }
            localDateTime = localDateTime.minusWeeks(1);
            localDateTime = DateTimeCalculatorUtil.withDayOfWeek(localDateTime, week);
        }

        pattern = RegexEnum.NormCurRelatedWeekAfter.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            int week;
            try {
                week = Integer.parseInt(match.group());
            } catch (NumberFormatException e) {
                week = 1;
            }
            localDateTime = localDateTime.plusWeeks(1);
            localDateTime = DateTimeCalculatorUtil.withDayOfWeek(localDateTime, week);
        }

        pattern = RegexEnum.NormCurRelatedWeekAfterNext.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            int week;
            try {
                week = Integer.parseInt(match.group());
            } catch (NumberFormatException e) {
                week = 1;
            }
            localDateTime = localDateTime.plusWeeks(2);
            localDateTime = DateTimeCalculatorUtil.withDayOfWeek(localDateTime, week);
        }

        pattern = RegexEnum.NormCurRelatedWeek.getPattern();
        match = pattern.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            int week;
            try {
                week = Integer.parseInt(match.group());
            } catch (NumberFormatException e) {
                week = 1;
            }
            localDateTime = localDateTime.plusWeeks(0);
            localDateTime = DateTimeCalculatorUtil.withDayOfWeek(localDateTime, week);
            /**处理未来时间倾向 @author kexm*/
            localDateTime = preferFutureWeek(week, localDateTime);
        }

        String s = DateTimeFormatterUtil.format(localDateTime, "yyyy-MM-dd-HH-mm-ss");
        String[] timeFin = s.split("-");
        if (flag[0] || flag[1] || flag[2]) {
            timeContext.getTunit()[0] = Integer.parseInt(timeFin[0]);
        }
        if (flag[1] || flag[2])
            timeContext.getTunit()[1] = Integer.parseInt(timeFin[1]);
        if (flag[2])
            timeContext.getTunit()[2] = Integer.parseInt(timeFin[2]);

    }

    /**
     * 如果用户选项是倾向于未来时间，检查所指的day_of_week是否是过去的时间，如果是的话，设为下周。
     * <p>
     * 如在周五说：周一开会，识别为下周一开会
     *
     * @param weekday       识别出是周几（范围1-7）
     * @param localDateTime
     * @return
     */
    private LocalDateTime preferFutureWeek(int weekday, LocalDateTime localDateTime) {
        /**1. 确认用户选项*/
        if (!textAnalysis.isPreferFuture()) {
            return localDateTime;
        }
        /**2. 检查被检查的时间级别之前，是否没有更高级的已经确定的时间，如果有，则不进行倾向处理.*/
        int checkTimeIndex = 2;
        for (int i = 0; i < checkTimeIndex; i++) {
            if (timeContext.getTunit()[i] != -1) return localDateTime;
        }
        /**获取当前是在周几，如果识别到的时间小于当前时间，则识别时间为下一周*/
        LocalDateTime curDateTime = LocalDateTime.now();
        if (this.timeContextOrigin.getTimeBase() != null) {
            String[] ini = this.timeContextOrigin.getTimeBase().split("-");
            curDateTime = LocalDateTime.of(Integer.valueOf(ini[0]).intValue(), Integer.valueOf(ini[1]).intValue(),
                    Integer.valueOf(ini[2]).intValue()
                    , Integer.valueOf(ini[3]).intValue(), Integer.valueOf(ini[4]).intValue(),
                    Integer.valueOf(ini[5]).intValue());
        }
        int curWeekday = curDateTime.get(ChronoField.DAY_OF_WEEK);
        if (curWeekday < weekday) {
            return localDateTime;
        }
        //准备增加的时间单位是被检查的时间的上一级，将上一级时间+1
        return localDateTime.plusWeeks(1);
    }

    /**
     * 该方法用于更新timeBase使之具有上下文关联性
     */
    private void modifyTimeBase() {
        String[] timeGrid = new String[6];
        timeGrid = timeContextOrigin.getTimeBase().split("-");

        String s = "";
        if (timeContext.getTunit()[0] != -1)
            s += Integer.toString(timeContext.getTunit()[0]);
        else
            s += timeGrid[0];
        for (int i = 1; i < 6; i++) {
            s += "-";
            if (timeContext.getTunit()[i] != -1)
                s += Integer.toString(timeContext.getTunit()[i]);
            else
                s += timeGrid[i];
        }
        timeContextOrigin.setTimeBase(s);
    }

    /**
     * 时间表达式规范化的入口
     * <p>
     * 时间表达式识别后，通过此入口进入规范化阶段，
     * 具体识别每个字段的值
     */
    private void timeNormalization() {
        //标准时间解析
        LocalDateTime localDateTime = normStandardTime();
        if (localDateTime == null) {
            normYear();
            normMonth();
            normDay();
            normMonthFuzzyDay();/**add by kexm*/
            normBaseRelated();
            normBaseTimeRelated();
            normCurRelated();
            normHour();
            normMinute();
            normSecond();
            normTotal();
            modifyTimeBase();
            localDateTime = LocalDateTime.of(1970, 1, 1, 0, 0);
        }
        String[] timeGrid = new String[6];
        timeGrid = timeContextOrigin.getTimeBase().split("-");

        int tunitpointer = 5;
        while (tunitpointer >= 0 && timeContext.getTunit()[tunitpointer] < 0) {
            tunitpointer--;
        }
        for (int i = 0; i < tunitpointer; i++) {
            if (timeContext.getTunit()[i] < 0)
                timeContext.getTunit()[i] = Integer.parseInt(timeGrid[i]);
        }
        String[] resultTmp = new String[6];
        resultTmp[0] = String.valueOf(timeContext.getTunit()[0]);
        if (timeContext.getTunit()[0] >= 10 && timeContext.getTunit()[0] < 100) {
            resultTmp[0] = "19" + String.valueOf(timeContext.getTunit()[0]);
        }
        if (timeContext.getTunit()[0] > 0 && timeContext.getTunit()[0] < 10) {
            resultTmp[0] = "200" + String.valueOf(timeContext.getTunit()[0]);
        }

        for (int i = 1; i < 6; i++) {
            resultTmp[i] = String.valueOf(timeContext.getTunit()[i]);
        }
        if (Integer.parseInt(resultTmp[0]) != -1) {
            timeNorm += resultTmp[0] + "年";
            localDateTime = localDateTime.withYear(Integer.valueOf(resultTmp[0]));
            if (Integer.parseInt(resultTmp[1]) != -1) {
                timeNorm += resultTmp[1] + "月";
                localDateTime = localDateTime.withMonth(Integer.valueOf(resultTmp[1]));
                if (Integer.parseInt(resultTmp[2]) != -1) {
                    timeNorm += resultTmp[2] + "日";
                    localDateTime = localDateTime.withDayOfMonth(Integer.valueOf(resultTmp[2]));
                    if (Integer.parseInt(resultTmp[3]) != -1) {
                        timeNorm += resultTmp[3] + "时";
                        localDateTime = localDateTime.withHour(Integer.valueOf(resultTmp[3]));
                        if (Integer.parseInt(resultTmp[4]) != -1) {
                            timeNorm += resultTmp[4] + "分";
                            localDateTime = localDateTime.withMinute(Integer.valueOf(resultTmp[4]));
                            if (Integer.parseInt(resultTmp[5]) != -1) {
                                timeNorm += resultTmp[5] + "秒";
                                localDateTime = localDateTime.withSecond(Integer.valueOf(resultTmp[5]));
                            }
                        }
                    }
                }
            }
        }
        timeContextOrigin.setTunit(timeContext.getTunit().clone());
        timeContext.setTimeBase(timeContextOrigin.getTimeBase());
        timeContext.setOldTimeBase(timeContextOrigin.getOldTimeBase());
        time = DateTimeConverterUtil.toDate(localDateTime);
        this.localDateTime = localDateTime;
        timeNormFormat = DateTimeFormatterUtil.format(localDateTime, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT);
    }

    public Boolean getIsAllDayTime() {
        return isAllDayTime;
    }

    public void setIsAllDayTime(Boolean isAllDayTime) {
        this.isAllDayTime = isAllDayTime;
    }

    @Override
    public String toString() {
        return timeExpression + " ---> " + timeNormFormat;
    }

    /**
     * 如果用户选项是倾向于未来时间，检查checkTimeIndex所指的时间是否是过去的时间，如果是的话，将大一级的时间设为当前时间的+1。
     * <p>
     * 如在晚上说“早上8点看书”，则识别为明天早上;
     * 12月31日说“3号买菜”，则识别为明年1月的3号。
     *
     * @param checkTimeIndex _tp.getTunit()时间数组的下标
     */
    private void preferFuture(int checkTimeIndex) {
        /**1. 检查被检查的时间级别之前，是否没有更高级的已经确定的时间，如果有，则不进行处理.*/
        for (int i = 0; i < checkTimeIndex; i++) {
            if (timeContext.getTunit()[i] != -1) return;
        }
        /**2. 根据上下文补充时间*/
        checkContextTime(checkTimeIndex);
        /**3. 根据上下文补充时间后再次检查被检查的时间级别之前，是否没有更高级的已经确定的时间，如果有，则不进行倾向处理.*/
        for (int i = 0; i < checkTimeIndex; i++) {
            if (timeContext.getTunit()[i] != -1) return;
        }
        /**4. 确认用户选项*/
        if (!textAnalysis.isPreferFuture()) {
            return;
        }
        /**5. 获取当前时间，如果识别到的时间小于当前时间，则将其上的所有级别时间设置为当前时间，并且其上一级的时间步长+1*/
        LocalDateTime localDateTime = LocalDateTime.now();
        if (this.timeContextOrigin.getTimeBase() != null) {
            String[] ini = this.timeContextOrigin.getTimeBase().split("-");
            localDateTime = LocalDateTime.of(Integer.valueOf(ini[0]).intValue(), Integer.valueOf(ini[1]).intValue(),
                    Integer.valueOf(ini[2]).intValue()
                    , Integer.valueOf(ini[3]).intValue(), Integer.valueOf(ini[4]).intValue(),
                    Integer.valueOf(ini[5]).intValue());
        }

        int curTime = localDateTime.get((TemporalField) TUNIT_MAP.get(checkTimeIndex));
        
        //月，月份小于等于当前月，年不用加
        if (checkTimeIndex == 1 && timeContext.getTunit()[1] <= curTime) {
        	return;
        }
        
        //小时，下午时间特殊处理，修复当前时间是上午10点，那么下午三点 会识别为明天下午三点问题
        if (checkTimeIndex == 3 && timeContext.getTunit()[3] >= 0 && timeContext.getTunit()[3] <= 11) {
            Pattern afternoonPattern = RegexEnum.NormHourAfternoon.getPattern();
            Matcher afternoonMatch = afternoonPattern.matcher(timeExpression);
            Pattern nightPattern = RegexEnum.NormHourNight.getPattern();
            Matcher nightMatch = nightPattern.matcher(timeExpression);
            if (afternoonMatch.find() || nightMatch.find()) {
                if (curTime < (timeContext.getTunit()[3] + 12)) {
                    return;
                }
            }
        } else {
            if (curTime < timeContext.getTunit()[checkTimeIndex]) {
                return;
            }
        }
        //准备增加的时间单位是被检查的时间的上一级，将上一级时间+1
        localDateTime = localDateTime.plus(1, (TemporalUnit) TUNIT_MAP.get(checkTimeIndex - 1 + 10));

        for (int i = 0; i < checkTimeIndex; i++) {
            timeContext.getTunit()[i] = localDateTime.get((TemporalField) TUNIT_MAP.get(i));
        }
    }

    /**
     * 根据上下文时间补充时间信息
     *
     * @param checkTimeIndex 序号
     */
    private void checkContextTime(int checkTimeIndex) {
        for (int i = 0; i < checkTimeIndex; i++) {
            if (timeContext.getTunit()[i] == -1 && timeContextOrigin.getTunit()[i] != -1) {
                timeContext.getTunit()[i] = timeContextOrigin.getTunit()[i];
            }
        }
        /**在处理小时这个级别时，如果上文时间是下午的且下文没有主动声明小时级别以上的时间，则也把下文时间设为下午*/
        if (isFirstTimeSolveContext == true && checkTimeIndex == 3 && timeContextOrigin.getTunit()[checkTimeIndex] >= 12 && timeContext.getTunit()[checkTimeIndex] < 12) {
            timeContext.getTunit()[checkTimeIndex] += 12;
        }
        isFirstTimeSolveContext = false;
    }

    /**
     * 过滤timeNLPList中无用的识别词。无用识别词识别出的时间是1970.01.01 00:00:00(fastTime=-28800000)
     *
     * @param timeNLPList 待处理列表
     * @return 返回结果
     */
    public static List<TimeNLP> filterTimeUnit(List<TimeNLP> timeNLPList) {
        if (CollectionUtil.isEmpty(timeNLPList)) {
            return timeNLPList;
        }
        List<TimeNLP> list = new ArrayList<>();
        for (TimeNLP t : timeNLPList) {
            if (t != null && t.getTime().getTime() != -28800000) {
                list.add(t);
            }
        }
        return list;
    }

    /**
     * 根据修改时间设置unit值
     *
     * @param localDateTime 时间
     */
    private void setUnitValues(LocalDateTime localDateTime) {
        String s = DateTimeFormatterUtil.format(localDateTime, "yyyy-MM-dd-HH-mm-ss");
        String[] timeFin = s.split("-");
        timeContext.getTunit()[0] = Integer.parseInt(timeFin[0]);
        timeContext.getTunit()[1] = Integer.parseInt(timeFin[1]);
        timeContext.getTunit()[2] = Integer.parseInt(timeFin[2]);
        timeContext.getTunit()[3] = Integer.parseInt(timeFin[3]);
        timeContext.getTunit()[4] = Integer.parseInt(timeFin[4]);
        timeContext.getTunit()[5] = Integer.parseInt(timeFin[5]);
    }

    // get set

    public String getTimeExpression() {
        return timeExpression;
    }

    public void setTimeExpression(String timeExpression) {
        this.timeExpression = timeExpression;
    }

    public String getTimeNorm() {
        return timeNorm;
    }

    public void setTimeNorm(String timeNorm) {
        this.timeNorm = timeNorm;
    }

    public String getTimeNormFormat() {
        return timeNormFormat;
    }

    public void setTimeNormFormat(String timeNormFormat) {
        this.timeNormFormat = timeNormFormat;
    }

    public TimeContext getTimeContext() {
        return timeContext;
    }

    public void setTimeContext(TimeContext timeContext) {
        this.timeContext = timeContext;
    }

    public void setTime(Date time) {
        this.time = time;
    }

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

}
