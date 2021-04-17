package com.xkzhangsan.time.utils;

import java.io.Serializable;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

/**
 * 缓存基础类
 * 
 * 修改自：{@code cn.hutool.core.lang.SimpleCache<K, V> }
 * 
 * @author xkzhangsan
 */
public class BaseCache<K, V> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 缓存
	 */
	private final Map<K, V> cache;

	/**
	 * 读写锁
	 */
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	/**
	 * 构造，默认使用{@link WeakHashMap}实现缓存自动清理
	 */
	public BaseCache() {
		this(new WeakHashMap<>());
	}

	/**
	 * 构造
	 * <p>
	 * 通过自定义Map初始化，可以自定义缓存实现。<br>
	 * 比如使用{@link WeakHashMap}则会自动清理key，使用HashMap则不会清理<br>
	 * 同时，传入的Map对象也可以自带初始化的键值对，防止在get时创建
	 * </p>
	 *
	 * @param initMap
	 *            初始Map，用于定义Map类型
	 */
	public BaseCache(Map<K, V> initMap) {
		this.cache = initMap;
	}

	/**
	 * 从缓存池中查找值
	 *
	 * @param key
	 *            键
	 * @return 值
	 */
	public V get(K key) {
		lock.readLock().lock();
		try {
			return cache.get(key);
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * 从缓存池中查找值，没有时尝试生成
	 *
	 * @param key
	 *            键
	 * @param supplier 提供者
	 * @return 值
	 */
	public V get(K key, Supplier<V> supplier) {
		V value = get(key);
		if (value == null && supplier != null) {
			lock.writeLock().lock();
			try {
				value = cache.get(key);
				// 双重检查，防止在竞争锁的过程中已经有其它线程写入
				if (null == value) {
					try {
						value = supplier.get();
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					cache.put(key, value);
				}
			} finally {
				lock.writeLock().unlock();
			}
		}
		return value;
	}

	/**
	 * 放入缓存
	 *
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return 值
	 */
	public V put(K key, V value) {
		// 独占写锁
		lock.writeLock().lock();
		try {
			cache.put(key, value);
		} finally {
			lock.writeLock().unlock();
		}
		return value;
	}

	/**
	 * 移除缓存
	 *
	 * @param key
	 *            键
	 * @return 移除的值
	 */
	public V remove(K key) {
		// 独占写锁
		lock.writeLock().lock();
		try {
			return cache.remove(key);
		} finally {
			lock.writeLock().unlock();
		}
	}

	/**
	 * 清空缓存池
	 */
	public void clear() {
		// 独占写锁
		lock.writeLock().lock();
		try {
			this.cache.clear();
		} finally {
			lock.writeLock().unlock();
		}
	}

}