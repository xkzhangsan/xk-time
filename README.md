# xk-time

xk-time is a datetime converter calculator formatter calendar and cron expression tool set, based on java8 date and time API, thread safe, easy to use.

时间转换，计算，格式化，解析，日历和cron表达式等的工具，使用java8，线程安全，简单易用，多达20几种常用日期格式化模板。  
 
 
## 0.为什么要开发这个工具？ 
 
### （1）java8以前的Date API设计不太好，使用不方便，往往会有线程安全问题。  
 
xk-time工具包，使用java8 api，其中Instant、LocalDate、LocalDateTime、LocalTime、ZonedDateTime等都是线程安全的类，而且增加了更丰富的方法，在此基础上开发相关工具类，线程安全，让使用更方便。  

### （2）常见的DateUtil，往往将时间转换，计算，格式化，解析等功能都放在同一个类中，导致类功能复杂，方法太多，查找不方便。  
 
xk-time工具包，将上面功能按照时间转换，时间计算，时间格式化解析分成3个工具类：DateTimeConverterUtil，DateTimeCalculatorUtil，DateTimeFormatterUtil，每个类只做一个种功能，方便使用。  
 
### （3）为了将与时间紧密相关的节假日、农历、二十四节气、十二星座和日历等功能集中起来开发成工具，方便使用。  
  
  
  
  
  
## 1.Maven 坐标  

    <dependency>  
      <groupId>com.github.xkzhangsan</groupId>    
      <artifactId>xk-time</artifactId>       
      <version>1.0.0</version>    
    </dependency>    


## 2.日期转换工具类   DateTimeConverterUtil 
包含Date、LocalDate、LocalDateTime、LocalTime、Instant、ZonedDateTime、YearMonth和Timestamp的互相转换    
 注意，ZonedDateTime相关的转换，尤其是其他时间转ZonedDateTime，要注意时间和对应时区一致。  

详细使用可以查看相关测试代码。  

## 3.日期计算工具类  DateTimeCalculatorUtil 
包括：  
（1）获取时间属性方法，get* 比如getYear(Date date) 获取年部分，getMonthCnLong(Date date)获取月份中文，getDayOfWeekCn(Date date)，获取星期中文。   
（2）获取时间加操作方法，plus* 比如plusYears(Date date, long amountToAdd) 当前时间年增加amountToAdd值。  
（3）获取时间减操作方法，minus* 比如minusYears(Date date, long amountToSubtract) 当前时间年减少amountToSubtract值。  
（4）获取时间修改属性方法，with* 比如withYear(Date date, long newValue) 修改当前时间年值为newValue。  
（5）获取比较2个时间方法，between* 比如betweenYears(Date startInclusive, Date endExclusive) 比较2个时间，获取年部分。  
（6）其他常用方法，比如isLeapYear(Date date) 判断是否闰年，isWeekend(Date date) 判断是否周末，isExpiry(String yearMonthStr) 是否过期等  
（7）时区转换计算方法，transform*，比如transform(ZonedDateTime zonedDateTime, String zoneId)  
（8）比较2个时间大小和相等方法，compare*，比如compare(Date date1, Date date2)  
（9）获取准确的起始时间方法，start*,end*，比如startTimeOfMonth() 当月起始时间 当月第一天日期+00:00:00，endTimeOfMonth() 当月最后一天日期+23:59:59。  
（10）相同月日比较判断方法，isSameMonthDay*，betweenNextSameMonthDay*，nextSameMonthDay*， 比如用于生日，节日等周期性的日期比较判断。  
（11）星座计算方法，getConstellation*，比如getConstellationNameCn(String monthDayStr)，根据日期计算星座。  
（12）计算指定年月或起始时间区间的时间列表，get*List， 比如getDateList(int year, int month)，计算指定年月的时间列表。  
（13）减少时间精度方法，reduceAccuracyTo*， 比如reduceAccuracyToDay(Date date)，减少时间精度到天，其他补0，返回如，2020-04-23 00:00:00。    
（14）获取时间戳方法，getEpoch*， 比如getEpochMilli()获取时间戳，getEpochMilliFormat()获取时间戳格式化字符串（yyyy-MM-dd HH:mm:ss）    
  
详细使用可以查看相关测试代码。  

## 4.日期格式化和解析工具类  DateTimeFormatterUtil 
包含常用日期格式如：  
 yyyy-MM-dd  
 HH:mm:ss  
 yyyy-MM-dd HH:mm:ss  
 yyyy-MM-dd'T'HH:mm:ssZ等等  
 
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
 
     
 注意：格式化和解析与系统时区不同的时间时，使用自定义时区格式化方法，或可以使用withZone方法重新设置时区，比如：  
 YYYY_MM_DD_HH_MM_SS_SSS_FMT.withZone(ZoneId.of("Europe/Paris") 。  
  
详细使用可以查看相关测试代码。  

## 5.日历工具类  CalendarUtil 
包括：  
（1）生成指定时间的日历（包含年、月和日层级关系的日历）方法，generateCalendar* 比如generateCalendar(int year, int month) 生成指定年月的日历。   
（2）生成指定时间的日历（包含年、月和日层级关系的日历），包含农历和所有节假日信息方法，generateCalendarWithHoliday*， 比generateCalendarWithHoliday(int year, int month, Map<String, String> localHolidayMap,Map<String, String> chineseHolidayMap, Map<String, Integer> dateTypeMap)生成指定年月的日历，包含农历和所有节假日信息，可以自定义节假日和工作日等。   

详细使用可以查看相关测试代码。  

## 6.农历日期类 LunarDate    
包含：  
（1）农历日期年月日计算。  
（2）农历岁次，生肖属相计算。  
（3）二十四节气计算等  
 注意： 仅支持公历1901-1950年的农历转换。    
 农历和二十四节气计算的准确依赖于lunarInfo和solarTermInfo基础数据的准确性和精确度。  
 根据测试结果，发现本程序和常用万年历基本一致，常用万年历软件中也有很少量计算误差，欢迎提出问题，会不断进行改进和修正。  
   
详细使用可以查看相关测试代码。  
 
## 7.节假日计算类 Holiday      
包含：  
（1）公历节假日计算， getLocalHoliday* 比如getLocalHoliday(Date date) 计算date的公历节日，getLocalHoliday(Date date, Map<String, String> localHolidayMap) 可以传入自定义公历节日数据。   
（2）农历节假日计算， getChineseHoliday* 比如getChineseHoliday(Date date) 计算date的农历节日，getChineseHoliday(Date date, Map<String, String> chineseHolidayMap) 可以传入自定义农历节日数据。  
（3）二十四节气计算， getSolarTerm* 比如getSolarTerm(Date date) 计算date的二十四节气。  

注意： 农历和二十四节气使用农历日期类 LunarDate，仅支持公历1901-1950年的计算。  

详细使用可以查看相关测试代码。      
    
## 8.Cron表达式工具类 CronExpressionUtil    
  
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

详细使用可以查看相关测试代码。              
  
  
# 参与项目  
## 1.提bug和建议  
- [Issues](https://github.com/xkzhangsan/xk-time/issues)    
  
## 2.贡献代码  
（1）fork项目。  
（2）在dev分支修改。  
（3）提交pull request。  
