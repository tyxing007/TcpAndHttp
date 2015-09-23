package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量用来控制同一时间资源访问者的数量
 * 
 * @author zhengcandong
 *
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		// 线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		// 只能5个线程同时访问
		final Semaphore semp = new Semaphore(3);
		// 模拟20个客户端访问
		for (int index = 0; index < 20; index++) {
			final int NO = index;
			Runnable run = new Runnable() {
				public void run() {
					try {
						// 获取许可
						semp.acquire();
						System.out.println("Accessing: " + NO);
						Thread.sleep((long) (Math.random() * 10000));
						// 访问完后，释放 ，如果屏蔽下面的语句，则在控制台只能打印5条记录，之后线程一直阻塞

					} catch (InterruptedException e) {

					} finally {
						semp.release();
					}
				}
			};
			exec.execute(run);
		}
		// 退出线程池
		exec.shutdown();
	}
}