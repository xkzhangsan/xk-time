package com.xkzhangsan.time.utils;

import java.util.function.Supplier;
import java.util.regex.Pattern;

import com.xkzhangsan.time.utils.BaseCache;

/**
 * 正则缓存
 * 
 * @author xkzhangsan
 */
public class RegexCache {

	private static volatile BaseCache<Regex, Pattern> cache;

	private RegexCache() {
	}

	private static BaseCache<Regex, Pattern> getCache() {
		if (cache == null) {
			synchronized (RegexCache.class) {
				if (cache == null) {
					cache = new BaseCache<Regex, Pattern>();
				}
			}
		}
		return cache;
	}

	/**
	 * 查询正则缓存
	 *
	 * @param rule
	 *            正则表达式
	 * @return Pattern
	 */
	public static Pattern get(String rule) {
		return get(rule, 0);
	}

	/**
	 * 先从缓存中查找正则，没有编译后放入缓存
	 *
	 * @param rule
	 *            正则表达式
	 * @param flags
	 *            正则标识位集合 {@link Pattern}
	 * @return Pattern
	 */
	public static Pattern get(String rule, int flags) {
		final Regex regex = new Regex(rule, flags);
		Supplier<Pattern> supplier = new Supplier<Pattern>() {
			@Override
			public Pattern get() {
				return Pattern.compile(rule, flags);
			}
		};
		return getCache().get(regex, supplier);
	}

	/**
	 * 删除正则缓存
	 *
	 * @param rule 正则
	 *            键
	 * @return 移除的值
	 */
	public static Pattern remove(String rule) {
		return remove(rule, 0);
	}

	/**
	 * 删除正则缓存
	 *
	 * @param rule 正则
	 *            键
	 * @param flags
	 *            正则标识位集合 {@link Pattern}
	 * @return 移除的值
	 */
	public static Pattern remove(String rule, int flags) {
		final Regex regex = new Regex(rule, flags);
		return getCache().remove(regex);
	}

	/**
	 * 清空正则缓存
	 */
	public static void clear() {
		getCache().clear();
	}

	/**
	 * 正则对象
	 * 
	 * @author xkzhangsan
	 */
	private static class Regex {

		public Regex(String rule, int flags) {
			this(null, rule, flags);
		}

		public Regex(String name, String rule, int flags) {
			this(name, rule, flags, null);
		}

		public Regex(String name, String rule, int flags, String desc) {
			super();
			this.name = name;
			this.rule = rule;
			this.flags = flags;
			this.desc = desc;
		}

		private String name;

		private String rule;

		private int flags;

		private String desc;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + flags;
			result = prime * result + ((rule == null) ? 0 : rule.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Regex other = (Regex) obj;
			if (flags != other.flags)
				return false;
			if (rule == null) {
				if (other.rule != null)
					return false;
			} else if (!rule.equals(other.rule))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Regex [name=" + name + ", rule=" + rule + ", flags=" + flags + ", desc=" + desc + "]";
		}

	}

}
