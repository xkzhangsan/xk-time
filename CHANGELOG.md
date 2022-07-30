# 3.2.0

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

# 3.2.1

## 1 新功能
（1）DateTimeConverterUtil增加TemporalAccessor相关转换方法，比如: TemporalAccessor转Date    
  
（2）DateTimeCalculatorUtil获取指定区间的格式化时间列表方法  
  
（3）DateTimeCalculatorUtil计算2个时间段的重叠（交集）时间方法  
  
（4）DateTimeCalculatorUtil计算倒计时支持传入时间对象和指定格式方法  
  
（5）DateTimeFormatterUtil增加对带Z格式的解析方法，如：2020-05-23T17:06:30.272Z。  
  
## 2 修复bug
  无
---

# 3.2.2

## 1 新功能
（1）DateTimeFormatterUtil中增加文日期格式化和解析方法，如：2021年09月11日 和 二〇二一年九月十一日  
  
（2）DateTimeConverterUtil中betweenTotalDays等方法，增加对LocalDate的支持  
  

## 2 修复bug
（1）修复TimeNLPUtil识别昨天、上月、去年等解析问题  
  
（2）DateTimeConverterUtil中between方法中和Period相关的方法名称调整，比如betweenPeriodDays仅返回相差年月日中天数；计算相差总天数需使用betweenTotalDays方法   

---

# 3.2.3

## 1 新功能
（1）DateTimeConverterUtil中新增单位转换直接返回字符串方法，同时去掉末尾的0    
  

## 2 修复bug
（1）修复TimeNLPUtil解析中文时间问题，比如：yyyy年MM月dd日 HH:mm:ss  
  
（2）修复TimeNLPUtil解析同时包含时分秒问题，比如：1小时50分钟10秒后  

---

# 3.2.4

## 1 修复bug
（1）修改DateTimeCalculatorUtil十二时辰子时计算问题  
  
（2）修复TimeNLPUtil本月解析为明年问题   
  
（3）修复TimeNLPUtil晚上9点解析为明天晚上9点问题  
  
（4）修复TimeNLPUtil标准格式时间解析0点问题  
  
（5）修复TimeNLPUtil1小时后解析问题   
  

---
