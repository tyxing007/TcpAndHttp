package com.tree;

public class BinarySearchTree<T extends Comparable<T>> {
	private Node<T> root; // tree root
	private int count; // tree element counts

	public boolean insert(T t) {
		if (root == null) { // ��Ϊ����
			root = new Node<T>(t, null, null, null);
			return true;
		}
		Node<T> newNode = new Node<T>(t, null, null, null);
		Node<T> pointer = root;
		while (true) {
			if (newNode.value.compareTo(pointer.value) > 0) {
				if (pointer.right == null) { // �����ұ�
					newNode.parent = pointer;
					pointer.right = newNode;
					count++;
					return true;
				} else {
					pointer = pointer.right;
				}
			} else if (newNode.value.compareTo(pointer.value) < 0) {
				if (pointer.left == null) { // �������
					newNode.parent = pointer;
					pointer.left = newNode;
					count++;
					return true;
				} else {
					pointer = pointer.left;
				}
			} else { // �����
				return false;
			}
		}
	}

	/**
	 * ����Ԫ��
	 * 
	 * @param t
	 *            ������Ԫ��
	 * @return ��ӦԪ�ػ�null
	 */
	public T get(T t) {
		Node<T> n = getN(t);
		return n == null ? null : n.value;
	}

	/**
	 * ���ҽڵ�
	 * 
	 * @param t
	 *            ������Ԫ��
	 * @return Ԫ�ض�Ӧ�ڵ��null
	 */
	private Node<T> getN(T t) {
		Node<T> cur = root;
		while (cur != null) {
			if (cur.value.compareTo(t) < 0) { // �ұ�������
				cur = cur.right;
			} else if (cur.value.compareTo(t) > 0) { // ���������
				cur = cur.left;
			} else { // �ҵ��ýڵ�
				break;
			}
		}
		return cur;
	}

	/**
	 * ��ȡĳ�ڵ�Ϊ����������СԪ��
	 */
	public T min(Node<T> n) {
		Node<T> min = minN(n);
		return min == null ? null : min.value;
	}

	/**
	 * ��ȡĳ�ڵ�Ϊ����������С�ڵ�
	 * 
	 * @param n
	 *            �����ڵ�
	 * @return ��������С�ڵ�
	 */
	private Node<T> minN(Node<T> n) {
		Node<T> min = n;
		while (min != null && min.left != null) {
			min = min.left;
		}
		return min;
	}

	/**
	 * �������
	 */
	public void leftRootRight() {
		printLRR(root);
	}

	/**
	 * ���������ӡԪ��
	 */
	private void printLRR(Node<T> node) {
		if (node != null) {
			printLRR(node.left);
			System.out.println(node.value);
			printLRR(node.right);
		}
	}

	/**
	 * ��ȡĳ�ڵ�Ϊ�����������Ԫ��
	 * 
	 * @return ���Ԫ��, û�з���null
	 */
	public T max(Node<T> n) {
		Node<T> max = maxN(n);
		return max == null ? null : max.value;
	}

	/**
	 * ��ȡĳ�ڵ�Ϊ�����������ڵ�
	 */
	private Node<T> maxN(Node<T> n) {
		Node<T> max = n;
		while (max != null && max.right != null) {
			max = max.right;
		}
		return max;
	}

	/**
	 * ��ȡԪ��ǰ��(�������)
	 * 
	 * @param t
	 *            ָ��Ԫ��
	 * @return Ԫ��ǰ����û�з���null
	 */
	public T prev(T t) {
		// ���ҵ���Ԫ��
		Node<T> cur = getN(t);
		if (cur != null) {
			return locatePrev(cur);
		}
		return null;
	}

	/**
	 * ��λ��ǰ���ڵ�
	 * 
	 * @param cur
	 *            ��ǰ�ڵ�
	 * @return ǰ���ڵ㣬û�з���null
	 */
	private T locatePrev(Node<T> cur) {
		Node<T> prev = locatePrevN(cur);
		return prev == null ? null : prev.value;
	}

	/**
	 * ��λ��ǰ���ڵ�
	 * 
	 * @param cur
	 *            ��ǰ�ڵ�
	 * @return ��ǰ�ڵ��ǰ���ڵ�
	 */
	private Node<T> locatePrevN(Node<T> cur) {
		if (cur != null) {
			// 1.����ýڵ�����������գ�����ǰ��Ϊ�������������Ԫ��
			if (cur.left != null)
				return maxN(cur.left);
			// 2.�ýڵ�������Ϊ��, ����ǰ��Ϊ�������Ƚڵ�(�ݹ�), �Ҹ����Ƚڵ���Һ���Ҳ�������Ƚڵ�
			// ͨ�׵�˵��һֱ�������ҵ���պ��Ǹ��ڵ�;
			Node<T> p = cur.parent;
			while (p != null && cur == p.left) {
				cur = p;
				p = p.parent;
			}
			return p == null ? null : p;
		}
		return null;
	}

	/**
	 * ��ȡԪ�غ��Ԫ��(�������)
	 * 
	 * @param t
	 *            ָ��Ԫ��
	 * @return ���Ԫ�أ�û�з���null
	 */
	public T next(T t) {
		// ���ҵ���Ԫ��
		Node<T> cur = getN(t);
		if (cur != null) {
			return locateNext(cur);
		}
		return null;
	}

	/**
	 * ��λ��ǰ�ڵ�ĺ��Ԫ��
	 * 
	 * @param cur
	 *            ��ǰ�ڵ�
	 * @return ����Ԫ��
	 */
	private T locateNext(Node<T> cur) {
		Node<T> next = locateNextN(cur);
		return next == null ? null : next.value;
	}

	/**
	 * ��λ����ǰ�ڵ�ĺ�̽ڵ�
	 * 
	 * @param cur
	 *            ��ǰ�ڵ�
	 * @return ��ǰ�ڵ�ĺ�̽ڵ�
	 */
	private Node<T> locateNextN(Node<T> cur) {
		if (cur == null)
			return null;
		// 1.������������Ϊ�գ���ô���̽ڵ����������������СԪ��
		if (cur.right != null)
			return minN(cur.right);
		// 2.��Ϊ�գ�Ӧ��Ϊ�����Ƚڵ�(�ݹ�)���Ҹ����Ƚڵ������Ҳ�������Ƚڵ�
		// ͨ�׵�˵��һֱ�����ң��ҵ��ҹպ��Ǹ��ڵ�;
		Node<T> p = cur.parent;
		while (p != null && cur == p.right) {
			cur = p;
			p = p.parent;
		}
		return p;
	}

	/**
	 * �Ƴ�ĳԪ��
	 * 
	 * @param t
	 *            ��ɾ��Ԫ��
	 * @return ɾ���ɹ�����true, ��֮false
	 */
	public boolean remove(T t) {
		// �ҵ��ýڵ�
		Node<T> cur = getN(t);
		if (cur != null) {
			if (doRemove(cur)) {
				cur = null;
				count--;
				return true;
			}
		}
		return false;
	}

	/**
	 * ִ��ɾ������
	 */
	private boolean doRemove(Node<T> cur) {
		// �ýڵ��Ƿ�Ϊ��
		boolean isRoot = cur == root;
		// 1.�ýڵ�ΪҶ�ӽڵ�, ֱ�ӽ��丸�ڵ��Ӧ(�����)�����ÿ�
		if (cur.left == null && cur.right == null) {
			if (isRoot)
				return true; // ����ֻ��һ�����ڵ�
			if (cur == cur.parent.right) // �ýڵ�Ϊ���ڵ���Һ���
				cur.parent.right = null;
			else
				// �ýڵ�Ϊ���ڵ������
				cur.parent.left = null;
			return true;
		} else if (cur.left != null && cur.right != null) {
			// 2.�ýڵ���2������, �������ҳ�һ���滻�ڵ�(�ýڵ�ĺ�̽ڵ㣬��̽ڵ�û����ǰ���ڵ�)
			// �ҵ����̽ڵ�
			Node<T> replaceNode = locateNextN(cur);
			if (replaceNode == null) // ��û�к�̽ڵ�����ǰ���ڵ�
				replaceNode = locatePrevN(cur);
			doRemove(replaceNode);
			cur.value = replaceNode.value;
			return true;
		} else { // 3.�ýڵ���1������, ֱ�ӽ��丸�ڵ��Ӧ(�����)���ӽӵ���ǿպ���
			Node<T> needLinkedNode = null;
			if (cur.left == null && cur.right != null) { // �ýڵ����Һ���
				needLinkedNode = cur.right;
			} else if (cur.left != null && cur.right == null) { // �ýڵ�������
				needLinkedNode = cur.left;
			}
			if (isRoot) { // ���ýڵ�Ϊ��
				root = needLinkedNode;
				return true;
			}
			if (cur == cur.parent.right) // �ýڵ�Ϊ���ڵ��Һ���
				cur.parent.right = needLinkedNode;
			else
				cur.parent.left = needLinkedNode;
			return true;
		}
	}

	/**
	 * �ڲ��ڵ���
	 */
	private static class Node<E> {
		E value; // Ԫ�ض���
		Node<E> parent; // ���ڵ�
		Node<E> left; // ���ӽڵ�
		Node<E> right; // �Һ��ӽڵ�

		public Node(E value, Node<E> parent, Node<E> left, Node<E> right) {
			this.value = value;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
	}
}