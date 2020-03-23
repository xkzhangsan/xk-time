# xk-time

xk-time is a datetime converter calculator and formatter tool set, based on java8 date and time API, thread safe, easy to use.

时间转换，计算，格式化，解析的工具，使用java8，线程安全，简单易用，多达20几种常用日期格式化模板。  
 
 
## 0.为什么要开发这个工具？

### 原因1：常见的DateUtil，往往将时间转换，计算，格式化，解析等功能都放在同一个类中，导致类功能复杂，方法太多，查找不方便。  
xk-time工具包，将上面功能按照时间转换，时间计算，时间格式化解析分成3个工具类：DateTimeConverterUtil，DateTimeCalculatorUtil，DateTimeFormatterUtil，每个类只做一个种功能，方便使用。  

### 原因2：java8以前的Date API设计不太好，往往会有线程安全问题  
使用java8api，Instant、LocalDate、LocalDateTime、LocalTime、ZonedDateTime等都是线程安全的类，而且增加了更丰富的方法，在此基础上开发相关工具类，线程安全，让使用更方便。


## 1.依赖  

    <dependency>  
      <groupId>com.github.xkzhangsan</groupId>    
      <artifactId>xk-time</artifactId>       
      <version>0.0.5</version>    
    </dependency>    


## 2.日期转换工具类   DateTimeConverterUtil 
包含Date、LocalDate、LocalDateTime、LocalTime、Instant、ZonedDateTime和YearMonth的互相转换  
 注意，ZonedDateTime相关的转换，尤其是其他时间转ZonedDateTime，要注意时间和对应时区一致。  


## 3.日期计算工具类  DateTimeCalculatorUtil 
包括：  
（1）获取时间属性方法，get* 比如getYear(Date date) 获取年部分，getMonthCnLong(Date date)获取月份中文，getDayOfWeekCn(Date date)，获取星期中文。   
（2）获取时间加操作方法，plus* 比如plusYears(Date date, long amountToAdd) 当前时间年增加amountToAdd值。  
（3）获取时间减操作方法，minus* 比如minusYears(Date date, long amountToAdd) 当前时间年减少amountToAdd值。  
（4）获取时间修改属性方法，with* 比如withYear(Date date, long newValue) 修改当前时间年值为newValue。  
（5）获取比较2个时间方法，between* 比如betweenYears(Date startInclusive, Date endExclusive) 比较2个时间，获取年部分。  
（6）其他常用方法，比如isLeapYear(Date date) 判断是否闰年，isWeekend(Date date) 判断是否周末，isExpiry(String yearMonthStr) 是否过期等  
（7）时区转换计算方法，transform*，比如transform(ZonedDateTime zonedDateTime, String zoneId)  
（8）比较2个时间大小和相等方法，compare*，比如compare(Date date1, Date date2)  
（9）获取准确的起始时间方法，start*,end*，比如startTimeOfMonth() 当月起始时间 当月第一天日期+00:00:00，endTimeOfMonth() 当月最后一天日期+23:59:59。  
（10）相同月日比较判断方法，isSameMonthDay*，betweenNextSameMonthDay*，nextSameMonthDay*， 比如用于生日，节日等周期性的日期比较判断。  
（11）星座计算方法，getConstellation*，比如getConstellationNameCn(String monthDayStr)，根据日期计算星座。  
（12）计算指定年月或起始时间区间的时间列表，get*List， 比如getDateList(String yearMonthStr)，计算指定年月（yyyy-MM）的时间列表。

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

 注意：格式化和解析ZonedDateTime 时区时间时，只能使用ISO开头  的Formatter，如ISO_DATE_FMT和YYYY_MM_DD_T_HH_MM_SS_Z_FMT  
 因为，其他Formatter都绑定的是系统默认时区：
 private static final ZoneId ZONE = ZoneId.systemDefault();  
 
 如果需要使用其他Formatter，可以使用withZone方法重新设置时区，比如：  
YYYY_MM_DD_HH_MM_SS_SSS_FMT.withZone(ZoneId.of("Europe/Paris")  

## 5.日历工具类  CalendarUtil 
包括：  
（1）生成指定时间的日历（包含年、月和日层级关系的日历）方法，generateCalendar* 比如generateCalendar(int year, int month) 生成指定年月的日历。   
（2）生成指定时间的日历（包含年、月和日层级关系的日历），包含农历和所有节假日信息方法，generateCalendarWithHoliday*， 比如generateCalendarWithHoliday(int year, int month, Map<String, String> localHolidayMap, Map<String, String> chineseHolidayMap) 生成指定年月的日历，包含农历和所有节假日信息。  

## 6.农历日期类 LunarDate （试用）  
包含：  
（1）农历日期年月日计算。  
（2）农历岁次，生肖属相计算。  
（3）二十四节气计算等  
 注意： 仅支持公历1901-1950年的农历转换。  
 
## 7.节假日计算类 Holiday （试用）  
包含：  
（1）公历节假日计算， getLocalHoliday* 比如getLocalHoliday(Date date) 计算date的公历节日，getLocalHoliday(Date date, Map<String, String> localHolidayMap) 可以传入自定义公历节日数据。   
（2）农历节假日计算， getChineseHoliday* 比如getChineseHoliday(Date date) 计算date的农历节日，getChineseHoliday(Date date, Map<String, String> chineseHolidayMap) 可以传入自定义农历节日数据。  
（3）二十四节气计算， getSolarTerm* 比如getSolarTerm(Date date) 计算date的二十四节气。  

注意： 农历和二十四节气使用农历日期类 LunarDate，仅支持公历1901-1950年的计算。  

### 欢迎提问题和建议！  
