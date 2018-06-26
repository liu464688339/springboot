package com.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		final AtomicInteger totalCount = new AtomicInteger();
		for (int i = 0; i < 100; i++) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("name:" + Thread.currentThread().getName() + "+1");
					totalCount.getAndAdd(1);
				}
			});
		}
		threadPool.shutdown();

		// 2.4、关闭启动线程
		threadPool.shutdown();
		// 2.5、等待子线程结束，再继续执行下面的代码
		try {
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			 e.printStackTrace();
		} finally {
			if (null != threadPool) {
				threadPool.shutdown();
			}
		}
		System.out.println("totalCount:" + totalCount);
	}

}
