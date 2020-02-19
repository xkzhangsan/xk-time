# xk-time
xk-time is a datetime converter calculator and formatter tool set, based on java8 date and time API, thread safe, easy to use.

datetime转换，计算，格式化，解析的工具，使用java8，线程安全，简单易用，多达20几种常用日期格式化模板。  

0.依赖  

    <dependency>  
      <groupId>com.github.xkzhangsan</groupId>    
      <artifactId>xk-time</artifactId>       
      <version>0.0.1</version>    
    </dependency>    

1.日期转换    
包含Date、LocalDate、LocalDateTime、LocalTime、Instant和ZonedDateTime的互相转换  
 注意，ZonedDateTime相关的转换，尤其是其他时间转ZonedDateTime，要注意时间和对应时区一致。  

2.日期计算工具类   
包括：  
（1）获取时间属性方法，get* 比如getYear(Date date) 获取年部分。  
（2）获取时间加操作方法，plus* 比如plusYears(Date date, long amountToAdd) 当前时间年增加amountToAdd值。  
（3）获取时间减操作方法，minus* 比如minusYears(Date date, long amountToAdd) 当前时间年减少amountToAdd值。  
（4）获取时间修改属性方法，with* 比如withYear(Date date, long newValue) 修改当前时间年值为newValue。  
（5）获取比较2个时间方法，between* 比如betweenYears(Date startInclusive, Date endExclusive) 比较2个时间，获取年部分。  
（6）其他常用方法，比如isLeapYear(Date date) 判断是否闰年。  
（7）时区转换计算方法，transform*，比如transform(ZonedDateTime zonedDateTime, String zoneId)  
（8）比较2个时间大小和相等方法，compare*，比如compare(Date date1, Date date2)  

3.  包含常用日期格式如：  
 yyyy-MM-dd  
 HH:mm:ss  
 yyyy-MM-dd HH:mm:ss  
 yyyy-MM-dd'T'HH:mm:ssZ等等  
  
 注意：格式化和解析ZonedDateTime 时区时间时，只能使用ISO开头  的Formatter，如ISO_DATE_FMT和YYYY_MM_DD_T_HH_MM_SS_Z_FMT  
 因为，其他Formatter都绑定的是系统默认时区：
 private static final ZoneId ZONE = ZoneId.systemDefault();  
 
 如果需要使用其他Formatter，可以使用withZone方法重新设置时区，比如：  
YYYY_MM_DD_HH_MM_SS_SSS_FMT.withZone(ZoneId.of("Europe/Paris")  
