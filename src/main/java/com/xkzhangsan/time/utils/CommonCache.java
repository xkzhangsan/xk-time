package com.xkzhangsan.time.utils;

import java.util.function.Supplier;

/**
 * 公共缓存
 * 
 * @author xkzhangsan
 */
public class CommonCache {

	private static volatile BaseCache<String, Object> cache;

	private CommonCache() {
	}

	private static BaseCache<String, Object> getCache() {
		if (cache == null) {
			synchronized (CommonCache.class) {
				if (cache == null) {
					cache = new BaseCache<String, Object>();
				}
			}
		}
		return cache;
	}

	/**
	 * 查询缓存
	 *
	 * @param key
	 *            key
	 * @return Object 结果
	 */
	public static Object get(String key) {
		return getCache().get(key);
	}

	/**
	 * 先从缓存中查找，没有编译后放入缓存
	 *
	 * @param key 键
	 * @param supplier
	 *            提供者
	 * @return Object
	 */
	public static Object get(String key, Supplier<Object> supplier) {
		return getCache().get(key, supplier);
	}

	/**
	 * 删除缓存
	 *
	 * @param  key 键
	 * @return 移除的值
	 */
	public static Object remove(String key) {
		return getCache().remove(key);
	}

	/**
	 * 清空缓存
	 */
	public static void clear() {
		getCache().clear();
	}

}
