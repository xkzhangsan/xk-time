package com.xkzhangsan.time.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 公共线程池（单例），核心线程为0，最大线程为cpu+1，存活60s，使用同步队列和使用CallerRunsPolicy拒绝策略的线程池。
 * 
 * @author xkzhangsan
 */
public class GlobalThreadPool {
	
	private static volatile GlobalThreadPool globalThreadPool;
	
	private ExecutorService executorService;
	
	private GlobalThreadPool(){
		this.executorService = new ThreadPoolExecutor(0, Runtime.getRuntime().availableProcessors() + 1, 60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.CallerRunsPolicy());
	}

	public static GlobalThreadPool getGlobalThreadPool() {
		if(globalThreadPool == null){
			synchronized(GlobalThreadPool.class){
				if(globalThreadPool == null){
					globalThreadPool = new GlobalThreadPool();
				}
			}
		}
		return globalThreadPool;
	}
	
	public <T> Future<T> submit(Callable<T> task){
		return executorService.submit(task);
	}
	

}
