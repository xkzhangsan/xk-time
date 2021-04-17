package com.xkzhangsan.time.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 正则资源文件工具类
 *
 * @author xkzhangsan
 */
public class RegexResourceUtil {

    /**
     * 获取Pattern
     * @param file 文件路径
     * @return Pattern 正则对象
     * @throws Exception 异常
     */
	public static Pattern readModel(String file) throws Exception {
        ObjectInputStream in;
        if (file.startsWith("jar:file") || file.startsWith("file:")) {
            in = new ObjectInputStream(new BufferedInputStream(new GZIPInputStream(new URL(file).openStream())));
        } else {
            in = new ObjectInputStream(
                    new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
        }
        return readModel(in);
    }

    public static Pattern readModel(ObjectInputStream in) throws Exception {
        Pattern p = (Pattern) in.readObject();
        return Pattern.compile(p.pattern());
    }

    /**
     * 修改 Model
     * @param p 正则对象
     * @param path 文件保存路径
     * @throws Exception 异常
     */
    public static void writeModel(Object p, String path) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(path))));
        out.writeObject(p);
        out.close();
    }
    
    public static String[] matcher(Pattern pattern, String text) throws Exception {
    	String[] result = new String[99];
    	int index = 0;
    	Matcher match = pattern.matcher(text);
    	while (match.find()) {
    		result[index] = match.group();
    		index++;
    	}
    	return result;
    }
    
    public static String matcher(String rule, String text) throws Exception {
    	String result = "";
    	Pattern pattern = Pattern.compile(rule); 
    	Matcher match = pattern.matcher(text);
    	while (match.find()) {
    		result = match.group();
    	}
    	return result;
    }    
    
    public static void main(String[] args) throws Exception {
    	String filePath = RegexResourceUtil.class.getClassLoader().getResource("TimeRegex.Gzip").getFile();
    	Pattern pattern = readModel(filePath);
    	System.out.println(pattern.pattern());
    	String[] result = matcher(pattern, "明天");
    	System.out.println(result);
    	
        System.out.println(matcher("\\d+(分钟|分|min)[以之]?[前后]","10分后"));
	}
}
