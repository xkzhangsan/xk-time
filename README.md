# xk-time
xk-time is a datetime converter calculator and formatter tool set, based on java8 date and time API, thread safe, easy to use.

datetime转换，计算，格式化，解析的工具，使用java8，线程安全，简单易用，多达20几种常用日期格式化模板。

1.日期转换
包含Date、LocalDate、LocalDateTime、LocalTime和Instant的互相转换。

2.日期计算工具类
包括：
（1）获取时间属性方法，get* 比如getYear(Date date) 获取年部分
（2）获取时间加操作方法，plus* 比如plusYears(Date date, long amountToAdd) 当前时间年增加amountToAdd值
（3）获取时间减操作方法，minus* 比如minusYears(Date date, long amountToAdd) 当前时间年减少amountToAdd值
（4）获取时间修改属性方法，with* 比如withYear(Date date, long newValue) 修改当前时间年值为newValue
（5）获取比较2个时间方法，between* 比如betweenYears(Date startInclusive, Date endExclusive) 比较2个时间，获取年部分
（6）其他常用方法，比如isLeapYear(Date date) 判断是否闰年

3.日期格式化和解析
包含常用日期格式如：
yyyy-MM-dd
HH:mm:ss
yyyy-MM-dd HH:mm:ss等等
