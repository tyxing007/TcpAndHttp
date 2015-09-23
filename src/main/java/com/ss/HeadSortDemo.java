package com.ss;

public class HeadSortDemo {
	/**
	 * �������㷨�� ��ͣ����a[0]�����
	 * 
	 * @param arr
	 *            ����������
	 */
	private static void heapSort(int[] arr) {
		// ���������ѻ�
		buildMaxHeap(arr);
		for (int i = arr.length - 1; i > 0; i--) {
			arr[0] = arr[0] ^ arr[i];
			arr[i] = arr[i] ^ arr[0];
			arr[0] = arr[0] ^ arr[i];
			// ����һ��(��)�����������ѻ�,
			// ����������Ľ�������, �������ų��ų�������i-1Ԫ��
			maxHeapify(arr, 0, i - 1);
		}
	}

	/**
	 * ����������������ѻ�: �ɶ�ÿ����Ҷ�ӽڵ�����������ѻ��� ע��Ҷ�ӽڵ�����Ϊ: [(arr.length)/2��+1,
	 * +2...n]������û��Ҫ�����ǽ������ѻ� �����鹹��Ϊ���ѵ����Ը��Ӷ�Ϊ: O(n)
	 * 
	 * @param arr
	 *            ��Ӧ����
	 */
	private static void buildMaxHeap(int arr[]) {
		for (int i = (arr.length - 1 - 1) / 2; i >= 0; i--) { // ����Ӵ�С
			maxHeapify(arr, i, arr.length - 1);
		}
	}

	/**
	 * ���ѻ�����arr[index]Ϊ��������
	 * 
	 * @param arr
	 *            ��Ӧ����
	 * @param index
	 *            �������� 0 �� arr.length��1
	 * @param lastIndex
	 *            �Ƚϲ������������
	 */
	private static void maxHeapify(int arr[], int index, int lastIndex) {
		int leftChildIndex = left(index);
		int rightChildIndex = right(index);

		int biggest = index;
		// ������ߵĺ��Ӹ���
		if ((leftChildIndex <= lastIndex) && arr[index] < arr[leftChildIndex]) {
			biggest = leftChildIndex;
		}
		// ���ұߵĺ��ӻ�����
		if ((rightChildIndex <= lastIndex) && arr[biggest] < arr[rightChildIndex]) {
			biggest = rightChildIndex;
		}
		// ��Ҫ���е���
		if (biggest != index) {
			int temp = arr[biggest];
			arr[biggest] = arr[index];
			arr[index] = temp;
			// �ݹ����ѻ�����
			maxHeapify(arr, biggest, lastIndex);
		}
	}

	// ��ȡ��������
	private static int left(int index) {
		return 2 * index + 1;
	}

	// ��ȡ�Һ�������
	private static int right(int index) {
		return 2 * index + 2;
	}

	public static void main(String[] args) {
		int arr[] = new int[] { 4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };
		heapSort(arr);

		print(arr, 0, arr.length - 1);
	}

	public static void print(int[] arr, int start, int end) {
		for (int i = start; i <= end; i++) {
			System.out.print(arr[i] + ",");
		}
		System.out.println();
	}

}
