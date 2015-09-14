package com.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFutureTask {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final ExecutorService exec = Executors.newFixedThreadPool(5);
		Callable<String> call = new Callable<String>() {
			public String call() throws Exception {
				Thread.sleep(1000 * 3);// ����ָ����ʱ�䣬�˴���ʾ�ò����ȽϺ�ʱ
				return "Other less important but longtime things.";
			}
		};
		Future<String> task = exec.submit(call);
		// Thread.sleep(1000 * 3);
		System.out.println("Let's do important things.");
		// ����߳��ڲ������黹δ�����꣬����û�з��ؽ�����������
		boolean isDone = task.isDone();
		while (!isDone) {
			isDone = task.isDone();
		}
		String obj = task.get();
		System.out.println(obj);
		// �ر��̳߳�
		exec.shutdown();
	}
}