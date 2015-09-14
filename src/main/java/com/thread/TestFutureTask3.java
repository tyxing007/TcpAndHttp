package com.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFutureTask3 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final ExecutorService exec = Executors.newFixedThreadPool(5);
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000 * 2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// ����ָ����ʱ�䣬�˴���ʾ�ò����ȽϺ�ʱ
			}
		};
		Future<?> task = exec.submit(runnable);

		System.out.println("Let's do important things.");
		// ����Ҫ������
		// ����߳��ڲ������黹δ�����꣬����û�з��ؽ�����������
		boolean isDone = task.isDone();
		while (!isDone) {
			// Causes the currently executing thread to sleep (temporarily cease
			// execution) for the specified number of milliseconds,
			Thread.sleep(500);
			isDone = task.isDone();
		}
		String obj = (String) task.get();
		System.out.println(obj);// ���null
		// �ر��̳߳�
		exec.shutdown();
	}
}