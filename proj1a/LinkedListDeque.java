public class LinkedListDeque<T> {
	private class TNode {
		private T item;
		private TNode prev;
		private TNode next;

		public TNode(T i, TNode n, TNode p) {
			item = i;
			next = n;
			prev = p;
		}

		public T getItem(int index) {
			if (this == null) {
				return null;
			}
			if (index == 0) {
				return item;
			}
			else {
				return this.next.getItem(index - 1);
			}
		}
	}

	private static int size;
	private TNode head;

	public LinkedListDeque() {
		size = 0; 
		head = new TNode(null, null, null);
		head.prev = head;
		head.next = head;
	}

	public void addFirst(T i) {
		size += 1;
		TNode temp = new TNode(i, head.next, head);
		head.next.prev = temp;
		head.next = temp;
	}

	public void addLast(T i) {
		size += 1;
		TNode temp = new TNode(i, head, head.prev);
		head.prev.next = temp;
		head.prev = temp;
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public int size() {
		return size;
	}

	public void printDeque() {
		TNode ptr = head;
		while ((ptr.next != null) & (ptr.next != head)) {
			ptr = ptr.next;
			System.out.print(ptr.item + " ");
		}
		if (ptr != head) {
			System.out.println(ptr.item);
		}
	}

	public T removeFirst() {
		if (isEmpty()) {
			return null;
		}
		size -= 1;
		TNode ptr = head.next;
		ptr.next.prev = head;
		head.next = ptr.next;
		return ptr.item;
	}

	public T removeLast() {
		if (isEmpty()) {
			return null;
		}
		size -= 1;
		TNode ptr = head.prev;
		ptr.prev.next = head;
		head.prev = ptr.prev;
		return ptr.item;
	}

	public T get(int index) {
		if (index >= size) {
			return null;
		}
		TNode ptr = head;
		for (int i = 0; i <= index; ++i) {
			ptr = ptr.next;
		}
		return ptr.item;
	}

	public T getRecursive(int index) {
		if (index >= size) {
			return null;
		}
		return head.next.getItem(index);
	}
}
