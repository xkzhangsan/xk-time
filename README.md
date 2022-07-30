# xk-time

<p align="center">
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-8+-green.svg" />
	</a>
	<a target="_blank" href="https://search.maven.org/search?q=g:%22com.github.xkzhangsan%22%20AND%20a:%22xk-time%22">
		<img src="https://img.shields.io/maven-central/v/com.github.xkzhangsan/xk-time.svg?label=Maven%20Central" />
	</a>
	<a target="_blank" href="https://gitee.com/xkzhangsan/xk-time/stargazers">
		<img src="https://gitee.com/xkzhangsan/xk-time/badge/star.svg?theme=dark" alt='gitee star'/>
	</a>
	<a target="_blank" href='https://github.com/xkzhangsan/xk-time'>
		<img src="https://img.shields.io/github/stars/xkzhangsan/xk-time.svg?style=social" alt="github star"/>
	</a>
</p>
<br>
  
  
### xk-time 是时间转换，时间计算，时间格式化，时间解析，日历，时间cron表达式和时间NLP等的工具，使用Java8，线程安全，简单易用，多达70几种常用日期格式化模板，支持Java8时间类和Date，轻量级，无第三方依赖。    
  
  
xk-time is a tool for time conversion, time calculation, time formatting, time parsing, calendar, time cron expression and time NLP, etc. It uses Java8, thread-safe, easy to use, and more than 70 common date formatting templates , Support Java8 time class and Date, lightweight, no third-party dependencies.  
  
  
# 1 安装项目  

### 1.1 Maven  
    <dependency>  
      <groupId>com.github.xkzhangsan</groupId>    
      <artifactId>xk-time</artifactId>       
      <version>3.2.4</version>    
    </dependency>    
      
      Mini版本不包含 time nlp 功能，更简洁。  
    <dependency>  
      <groupId>com.github.xkzhangsan</groupId>    
      <artifactId>xk-time</artifactId>       
      <version>3.2.4.Mini</version>    
    </dependency>    
            
        
### 1.2 Gradle        
    compile group: 'com.github.xkzhangsan', name: 'xk-time', version: '3.2.4'  
      
### 注意：Android谨慎使用，Android端因为需要兼容低版本而不支持Java8，建议继续使用其他工具，如果有需要本项目相关的功能，可以参考源码实现，或留言给我。感谢支持！  
    
# 2 为什么要开发这个工具？ 
 
### 2.1 Java8以前的Date API设计不太好，使用不方便，往往会有线程安全问题。  
 
xk-time工具包，使用java8 api，其中Instant、LocalDate、LocalDateTime、LocalTime、ZonedDateTime等都是线程安全的类，而且增加了更丰富的方法，在此基础上开发相关工具类，线程安全，让使用更方便。  

### 2.2 常见的DateUtil，往往将时间转换，计算，格式化，解析等功能都放在同一个类中，导致类功能复杂，方法太多，查找不方便。  
 
xk-time工具包，将上面功能按照时间转换，时间计算，时间格式化解析分成3个工具类：DateTimeConverterUtil，DateTimeCalculatorUtil，DateTimeFormatterUtil，每个类只做一个种功能，方便使用。  
 
### 2.3 为了将与时间紧密相关的节假日、农历、二十四节气、十二星座、十二生肖、十二时辰和日历等功能集中起来开发成工具，方便使用。  
  
### 2.4 xk-time、joda-time和Java8 time包的关系  

2.4.1 joda-time和Java8 time包实现基本相同，都是Stephen Colebourne主导的，从Java8开始，推荐使用java8 time包替换joda-time。   
  
2.4.2 xk-time是基于Java8日期api的，是一个补充工具，和以前的dateutil类似。  
（1）Java8的类库是层级实现纵向的，xk-time则是按照通用功能横向的，比如转换，计算，格式化解析分为3个类等。  
（2）Java8 Date转换成新类，需要查看api文档，xk-time则在内部转换好，使Date可以直接使用Java8的新功能。  
（3）xk-time把dateutil的常用功能重新按照Java8的Api实现，同时做了一些扩展，使用更方便。  
  
  
  
  

# 3 主要功能说明
### 3.1 日期转换工具类   DateTimeConverterUtil 
包含：  
（1）Date、LocalDate、LocalDateTime、LocalTime、Instant、ZonedDateTime、YearMonth、Timestamp、时间戳和TemporalAccessor等互相转换。    
  
（2）天、小时、分钟、秒和毫秒等时间单位相互转换，支持小单位到大单位的精确转换比如，minuteToHourPrecise(long num) 90分钟转换为小时，为1.5小时。    
  
（3）转换ZonedDateTime的同时支持转换为指定时区，比如toZonedDateTime(Date date, String zoneId)     ,toZonedDateTimeAndTransformZone(LocalDateTime localDateTime, String targetZoneId)。     
   
 注意，ZonedDateTime相关的转换，尤其是其他时间转ZonedDateTime，要注意时间和对应时区一致。  
  
详细使用可以查看相关测试代码： DateTimeConverterUtilTest.  

### 3.2 日期计算工具类  DateTimeCalculatorUtil 
包括：  
（1）获取时间属性方法（支持年月日时分秒毫秒，星期，时间戳等），get* 比如getYear(Date date) 获取年部分，getMonthCnLong(Date date)获取月份中文，getDayOfWeekCn(Date date)，获取星期中文。   
  
（2）获取时间加操作方法，plus* 比如plusYears(Date date, long amountToAdd) 当前时间年增加amountToAdd值。  
  
（3）获取时间减操作方法，minus* 比如minusYears(Date date, long amountToSubtract) 当前时间年减少amountToSubtract值。  
  
（4）获取时间修改属性方法，with* 比如withYear(Date date, long newValue) 修改当前时间年值为newValue。  
  
（5）获取比较2个时间方法，between* 比如betweenTotalDays(Date startInclusive, Date endExclusive) 比较2个时间，返回总天数。  
  
（6）其他常用方法，比如isLeapYear(Date date) 判断是否闰年，isWeekend(Date date) 判断是否周末，isExpiry(String yearMonthStr) 是否过期等  
  
（7）时区转换计算方法，transform*，比如transform(ZonedDateTime zonedDateTime, String zoneId)  
  
（8）比较2个时间大小和相等方法，compare*，比如compare(Date date1, Date date2)  
  
（9）获取准确的起始时间方法，start*,end*，比如startTimeOfMonth() 当月起始时间 当月第一天日期+00:00:00，endTimeOfMonth() 当月最后一天日期+23:59:59  精确到秒；endAccuracyTimeOf*，精确到毫秒（Date），精确到纳秒（LocalDateTime）。    
  
（10）相同月日比较判断方法，isSameMonthDay*，betweenNextSameMonthDay*，nextSameMonthDay*， 比如用于生日，节日等周期性的日期比较判断。  
  
（11）星座计算方法，getConstellation*，比如getConstellationNameCn(String monthDayStr)，根据日期计算星座。  
  
（12）计算指定年月或起始时间区间的时间列表，get*List， 比如getDateList(int year, int month)，计算指定年月的时间列表。  
  
（13）减少时间精度方法，reduceAccuracyTo*， 比如reduceAccuracyToDay(Date date)，减少时间精度到天，其他补0，返回如，2020-04-23 00:00:00。    
  
（14）获取时间戳方法，getEpoch*， 比如getEpochMilli()获取时间戳，getEpochMilliFormat()获取时间戳格式化字符串（yyyy-MM-dd HH:mm:ss）    
  
（15）计算年龄方法，getAge*， 比如getAge(Date birthDay)，通过生日计算年龄。  
  
（16）判断是否到生日方法，isBirthDay*， 比如isBirthDay(Date birthDay)，根据生日判断当前日期是否到生日。  
  
（17）周数计算方法，weekof*， 比如weekOfMonth(Date date)，日期所在月中第几周。  
  
（18）判断星期一，星期五方法，isMonday*,isZhouYi*， 比如isZhouYi(Date date)，是否为周一。  
  
（19）十二时辰计算方法，getTwelveTwo*， 比如getTwelveTwo(Date date)，获取指定时间对应的十二时辰。  
  
（20）季度计算方法，getQuarter*， 比如getQuarter(Date date)，获取指定时间对应的季度。    
  
（21）获取季度准确的起始时间方法（四个季度），startTimeOf*Quarter， 比如startTimeOfFirstQuarter(int year)，获取指定年的第一季度。   
  
（22） 获取年准确的起始时间方法，startTimeOfYear， 比如startTimeOfYear(int year)，获取指定年的开始时间。    
  
（23）常用时间（明天，下周，下月，明年等）计算方法，比如tomorrow()，计算明天，返回Date。  
  
（24）修改星期值方法 withDayOfWeek*，比如withDayOfWeek(Date date, long newValue)，修改星期为指定值newValue，返回Date。  
  
（25）中国工作日计算（将放假信息包含在内），包括判断当前日期是否为工作日和下一个工作日等方法， isChineseWorkDay*，nextChineseWorkDay*，  
比如  isChineseWorkDay(Date, String holidayData)，nextChineseWorkDay(Date date, String holidayData)，  
节假日数据holidayData，如果节假日数据不支持年份，将使用周一到周五为工作日来判断。 下面是我整理的2021年放假信息：  
2021-01-01:0,2021-02-07:1,2021-02-11:0,2021-02-12:0,2021-02-15:0,2021-02-16:0,2021-02-17:0,2021-02-20:1,2021-04-05:0,2021-04-25:1,2021-05-03:0,2021-05-04:0,2021-05-05:0,2021-05-08:1,2021-06-14:0,2021-09-18:1,2021-09-20:0,2021-09-21:0,2021-09-26:1,2021-10-01:0,2021-10-04:0,2021-10-05:0,2021-10-06:0,2021-10-07:0,2021-10-09:1  
  
（26）判断2个或多个时间段是否有重叠（交集）方法， isOverlap*，比如isOverlap(Date startDate1, Date endDate1, Date startDate2, Date endDate2)，重叠返回true。  
    
（27）计算平均时间方法，averageTime*，比如averageTime(List<Date> dateList)，返回平均时间，比如"15:03:03"。  
  
（28）根据毫秒值计算倒计时方法，支持支持传入时间对象和指定格式，countdown*，比如countdown(long millis),返回倒计时，比如"27小时10分钟30秒"。  
  
 （29）获取指定区间的格式化时间列表 方法，比如getDateFormatList(Date start, Date end, String dateFormatPattern) 支持传入格式化模板。    
   
 （30）计算2个时间段的重叠（交集）时间方法，比如overlapTime(Date startDate1, Date endDate1, Date startDate2, Date endDate2)，返回毫秒值。    
    
      
详细使用可以查看相关测试代码： DateTimeCalculatorUtilTest.  

### 3.3 日期格式化和解析工具类  DateTimeFormatterUtil 
包含常用日期格式如：  
 yyyy-MM-dd  
 HH:mm:ss  
 yyyy-MM-dd HH:mm:ss  
 yyyy-MM-dd HH:mm:ss.SSS  
 yyyy-MM-dd HH:mm:ss.SSSSSS  
 yyyy-MM-dd HH:mm:ss.SSSSSSSSS  
 yyyy-MM-dd'T'HH:mm:ssZ等等 ，支持毫秒、微秒和纳秒等精确时间。   
 
（1）格式化方法， format*， 比如formatToDateStr(Date date) 格式化，返回日期部分，如：yyyy-MM-dd；   
format(Date date, DateTimeFormatter formatter) formatter 可以选择已定义好的formatter比如YYYY_MM_DD_HH_MM_SS_FMT（yyyy-MM-dd HH:mm:ss）格式化日期。  

（2）解析方法， parse*， 比如parseDateStrToDate(String text) 解析日期yyyy-MM-dd，返回Date；  
parseToDate(String text, DateTimeFormatter formatter) 根据 formatter解析为 Date。  
  
（3）自动解析方法，根据字符串特点自动识别解析，smartParse*，比如smartParseToDate(String text) 自动解析Date。   

（4）ISO格式（包含T）自动解析方法，根据字符串特点自动识别解析，parseIso*，比如parseIsoToDate(String text) 自动解析Date。  
 
（5）解析时间戳方法, parseEpochMilli*， 比如parseEpochMilliToDate(String text)，解析时间戳为Date，如 1590224790000。  
 
（6）解析Date默认格式，parseDateDefaultStr*，比如parseDateDefaultStrToDate(String text)  
 解析 EEE MMM dd HH:mm:ss zzz yyyy 比如：  Sat May 23 17:06:30 CST 2020 为Date。  
  
（7）自定义时区格式化方法，比如 format(Date date, DateTimeFormatter formatter, String zoneId)，根据zoneId格式化Date。  
  
（8）自定义模板格式化方法，比如 format(Date date, String dateFormatPattern)，根据dateFormatPattern格式化Date。  
  
（9）自定义模板解析方法，比如 parseToDate(String text, String dateFormatPattern)，根据dateFormatPattern格式化Date。  
  
（10）Timestamp默认格式（ yyyy-mm-dd hh:mm:ss.fffffffff 其中 fffffffff 纳秒，省略后面的0）格式化方法。    
比如 formatTimestampStyle(Date date)。  
  
（11）Timestamp默认格式（ yyyy-mm-dd hh:mm:ss.fffffffff 其中 fffffffff 纳秒，省略后面的0）解析方法。    
比如 parseTimestampStyleToDate(String text)。  
  
（12）验证日期格式是否正确方法，isValidDate*， 比如isValidDate(String text)，验证yyyy-MM-dd 格式字符串是否正确。  
  
（13）根据自定义模板数组解析方法， 比如parseToDate(String text, String[] dateFormatPatterns)，dateFormatPatterns 支持多种模板，只要其中一个解析成功就返回对应Date。  
  
（14）解析自然语言时间，今天，明天，下周，下月，明年，昨天，上周，上月，去年等， 比如parseNaturalLanguageToDate(String text),  
 parseNaturalLanguageToDate(String text, Map<String, String> naturalLanguageMap) 支持自定义解析自然语言时间map。  
  
（15）中文日期格式化方法，比如formatToChineseDateStr(Date date, boolean isUpperCase)，isUpperCase false：2021年09月11日 true： 二〇二一年九月十一日。  
  
（16）中文日期解析方法，比如parseChineseDateStrToDate(String text)，支持：2021年09月11日 和 二〇二一年九月十一日格式日期解析。  
  
 注意：格式化和解析与系统时区不同的时间时，使用自定义时区格式化方法，或可以使用withZone方法重新设置时区，比如：  
 YYYY_MM_DD_HH_MM_SS_SSS_FMT.withZone(ZoneId.of("Europe/Paris") 。  
  
详细使用可以查看相关测试代码： DateTimeFormatterUtilTest.  

### 3.4 日历工具类  CalendarUtil 
包括：  
（1）生成指定时间的日历（包含年、月和日层级关系的日历）方法，generateCalendar* 比如generateCalendar(int year, int month) 生成指定年月的日历。   
（2）生成指定时间的日历（包含年、月和日层级关系的日历），包含农历和所有节假日信息方法，generateCalendarWithHoliday*， 比generateCalendarWithHoliday(int year, int month, Map<String, String> localHolidayMap,Map<String, String> chineseHolidayMap, Map<String, Integer> dateTypeMap)生成指定年月的日历，包含农历和所有节假日信息，可以自定义节假日和工作日等。   

详细使用可以查看相关测试代码： CalendarUtilTest.  

### 3.5 农历日期类 LunarDate    
包含：  
（1）农历日期年月日计算。  
（2）农历岁次，生肖属相计算。  
（3）二十四节气计算等  
（4）农历转公历  
 注意： 仅支持公历1900-2100年的农历转换。    
   
详细使用可以查看相关测试代码： LunarDateTest.  
 
### 3.6 节假日计算工具类 HolidayUtil      
包含：  
（1）公历节假日计算， getLocalHoliday* 比如getLocalHoliday(Date date) 计算date的公历节日，getLocalHoliday(Date date, Map<String, String> localHolidayMap) 可以传入自定义公历节日数据。   
（2）农历节假日计算， getChineseHoliday* 比如getChineseHoliday(Date date) 计算date的农历节日，getChineseHoliday(Date date, Map<String, String> chineseHolidayMap) 可以传入自定义农历节日数据。  
（3）二十四节气计算， getSolarTerm* 比如getSolarTerm(Date date) 计算date的二十四节气。  

注意： 农历和二十四节气使用农历日期类 LunarDate，仅支持公历1900-2100年的计算。  

详细使用可以查看相关测试代码。      
    
### 3.7 Cron表达式工具类 CronExpressionUtil    
  
cron表达式从左到右（用空格隔开）：秒（0-59） 分（0-59） 小时（0-23） 日期（1-31） 月份（1-12的整数或者 JAN-DEC） 星期（1-7的整数或者 SUN-SAT （1=SUN）） 年份（可选，1970-2099）  
所有字段均可使用特殊字符：, - * / 分别是枚举，范围，任意，间隔  
日期另外可使用：? L W 分别是任意，最后，有效工作日(周一到周五)  
星期另外可使用：? L # 分别是任意，最后，每个月第几个星期几  
常用cron表达式：  
（1）0 0 2 1 * ? *   表示在每月的1日的凌晨2点触发  
（2）0 15 10 ? * MON-FRI   表示周一到周五每天上午10:15执行作业  
（3）0 15 10 ? * 6L 2002-2006   表示2002-2006年的每个月的最后一个星期五上午10:15执行作  
（4）0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时  
（5）0 15 10 L * ?    每月最后一日的上午10:15触发  
（6）0 15 10 ? * 6#3   每月的第三个星期五上午10:15触发  
   
包含  
（1）验证和格式化Cron表达式方法，isValidExpression和formatExpression。  
（2）生成下一个或多个执行时间方法，getNextTime和getNextTimeList。  
（3）生成下一个或多个执行时间的日期格式化（yyyy-MM-dd HH:mm:ss）方法，getNextTimeStr和getNextTimeStrList。  
（4）对比Cron表达式下一个执行时间是否与指定date相等方法，isSatisfiedBy。  
    
注意： 底层使用 quartz的CronExpression处理。  

详细使用可以查看相关测试代码： CronExpressionUtilTest.              
  
  
### 3.8 计算耗时工具 CostUtil
  
  计算耗时工具，支持秒，毫秒，纳秒
  
  包括：  
（1）计算耗时，返回耗时结果。  
（2）计算耗时，自定义任务名称，输出耗时结果。  
（3）计算耗时，返回精确计时，带3小数的结果，使用ROUND_DOWN 舍弃超过3位的小数部分等。  
  
详细使用可以查看相关测试代码：CostUitlTest.     
  
### 3.9 时间自然语言分析工具类（NLP） TimeNLPUtil  
  
  包括功能：   
（1）以当前时间为基础分析时间自然语言。  
（2）以指定时间为基础分析时间自然语言。  
（3）增加多种调用方式，比如parseConcurrent 并发执行，可设置超时时间和自定义线程池等，提高执行效率。  
  
修改自 https://github.com/shinyke/Time-NLP  
做了一些修改如下：  
（1）封装属性，重命名使符合驼峰命名标准。  
（2）将加载正则资源文件改为单例加载。  
（3）将类按照功能重新划分为单独的多个类。  
（4）使用Java8日期API重写。  
（5）增加注释说明，优化代码。   
（6）修复原项目中的issue：标准时间yyyy-MM-dd、yyyy-MM-dd HH:mm:ss和yyyy-MM-dd HH:mm解析问题。   
（7）修复原项目中的issue：1小时后，1个半小时后，1小时50分钟等解析问题；并且支持到秒，比如50秒后，10分钟30秒后等。     
（8）修复原项目中的issue：修复当前时间是上午10点，那么下午三点 会识别为明天下午三点问题。   
（9）修复原项目中的issue：修复小数解析异常问题。   
（10）性能优化，将使用到的正则预编译后放到缓存中，下次直接使用，提高性能。    
    
注意：NLP会有一定的识别失败率，在不断迭代开发提高成功率。    
    Mini版本不包含此功能。  
  
  详细使用可以查看相关测试代码： TimeNLPUtilTest.     
    
### 3.10 时间单位常量类 XkTimeConstant  
  
时间单位常量 ，方便计算单位换算，比如设置缓存时间 3天：  3\*MILLISECONDS_PER_DAY （每天毫秒数 24\*60\*60\*1000）     
包含：     
（1）基本单位换算数值，比如 MILLISECONDS_PER_SECONDS 每秒毫秒数 1000。  
（2）转换为秒数基本数值，比如 SECONDS_PER_DAY 每天秒数 24\*60\*60。  
（3）转换为毫秒基本数值，比如 MILLISECONDS_PER_DAY 每天毫秒数 24\*60\*60\*1000。  
  
  详细使用可以查看相关测试代码： XkTimeConstantTest.    
    
# 4 更多详细文档
- [JavaDoc 文档](https://apidoc.gitee.com/xkzhangsan/xk-time) 


# 5 参与项目  
### 5.1 提bug和建议  
- [Issues](https://github.com/xkzhangsan/xk-time/issues)    
  
### 5.2 贡献代码  
（1）fork项目。  
（2）在dev分支修改。  
（3）提交pull request。 
  
  
  
# 6 开发计划  
### 6.1 时间自然语言分析工具类（NLP） TimeNLPUtil 支持节假日识别  
（1）常见节假日，比如元旦、春节、清明节、劳动节、端午节、中秋节等节假日支持。  
（2）24节气支持。