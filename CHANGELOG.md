#3.2.0

## 1 新功能
（1）TimeNLPUtil增加多种调用方式，比如parseConcurrent 并发执行，可设置超时时间和自定义线程池等，提高执行效率。（Mini版无TimeNLPUtil功能）  
  
（2）DateTimeCalculatorUtil判断2个或多个时间段是否有重叠（交集）方法
  
（3）DateTimeCalculatorUtil增加计算平均时间方法
  
（4）DateTimeCalculatorUtil增加根据毫秒值计算倒计时方法
  
（5）DateTimeCalculatorUtil中getDateList获取日期列表方法，增加获取间隔指定单位的相同时间，比如每月的15号日期列表。
  
（6）DateTimeCalculatorUtil中isChineseWorkDay工作日计算，使用本地缓存优化，提高性能。
  
## 2 修复bug
  无
---

#3.2.1

## 1 新功能
（1）DateTimeConverterUtil增加TemporalAccessor相关转换方法，比如: TemporalAccessor转Date    
  
（2）DateTimeCalculatorUtil获取指定区间的格式化时间列表方法  
  
（3）DateTimeCalculatorUtil计算2个时间段的重叠（交集）时间方法  
  
（4）DateTimeCalculatorUtil计算倒计时支持传入时间对象和指定格式方法  
  
（5）DateTimeFormatterUtil增加对带Z格式的解析方法，如：2020-05-23T17:06:30.272Z。  
  
## 2 修复bug
  无
---
