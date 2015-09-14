package com.thread;

import java.util.concurrent.*;

public class line2 {
	final CyclicBarrier barrier;

	// �߳���
	int count;

	class Worker implements Runnable {
		int index;

		Worker(int index) {
			this.index = index;
		}

		public void run() {
			System.out.println("��" + index + "���߳�����" + (2 * index) + "�룡");
			try {
				Thread.sleep(2000 * index);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("��" + index + "���߳̽������ߣ�");

			try {
				// �ȴ������̶߳�������Ϻ��ټ������´����ִ��
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}

			System.out.println("��" + index + "���̼߳���ʣ�µ�����");
		}
	}

	public line2(int count) {
		this.count = count;

		// �������ϵ� �ȴ���5���̺߳�ִ����Ӧ��barrierAction
		barrier = new CyclicBarrier(count, new Runnable() {
			public void run() {
				System.out.println("ȫ���߳���ִ����ϣ�");
				System.out.println("----------------------");
				for (int i = 1; i <= 5; i++) { // ��ȫ���߳�ִ���꣬��������̣߳�����CyclicBarrier����
					new Thread(new Worker(i)).start();// �˳�������ѭ�����ֶ�����
				}
				try {
					TimeUnit.MILLISECONDS.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		for (int i = 1; i <= 5; i++) {
			new Thread(new Worker(i)).start();
		}
	}

	public static void main(String[] args) {
		new line2(5);
	}
}
