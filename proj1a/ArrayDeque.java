public class ArrayDeque<T> {
	private int size;
	private T[] items;
	private int head;

	public ArrayDeque() {
		int initCapacity = 8;
		size = 0;
		items = (T[]) new Object[initCapacity];
		head = 0;
	}

	private void resizeArray(int cap) {
		T[] temp = (T[]) new Object[cap];
		if (head + size < items.length) {
			System.arraycopy(items, head, temp, 0, size);
		}
		else {
			System.arraycopy(items, head, temp, 0, items.length - head);
			System.arraycopy(items, 0, temp, items.length - head, head + size - items.length);
		}
		items = temp;
		head = 0;
	}

	public void addFirst(T x) {
		if (size == items.length) {
			resizeArray(2 * size);
		}
		int index = (head - 1 + items.length) % items.length;
		items[index] = x;
		size += 1;
		head = index;
	}

	public void addLast(T x) {
		if (size == items.length) {
			resizeArray(2 * size);
		}
		int index = (head + size) % items.length;
		items[index] = x;
		size += 1;
	}

	public T removeFirst() {
		if (isEmpty()) {
			return null;
		}
		int index = head;
		T returnItem = get(index);
		double capLowBound = 0.25;
		items[index] = null;
		size -= 1;
		double currentCapRatio = (double) size / items.length;
		if ((items.length >= 16) & (currentCapRatio < capLowBound)) {
			resizeArray(items.length / 2);
		}
		head = (head + 1) % items.length;
		return returnItem;
	}
	public T removeLast() {
		if (isEmpty()) {
			return null;
		}
		int index = (head + size - 1) % items.length;
		T returnItem = get(index);
		double capLowBound = 0.25;
		items[index] = null;
		size -= 1;
		double currentCapRatio = (double) size / items.length;
		if ((items.length >= 16) & (currentCapRatio < capLowBound)) {
			resizeArray(items.length / 2);
		}
		return returnItem;
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public T get(int index) {
		if (index >= size) {
			return null;
		}
		int rrIndex = (head + index) % items.length;
		return items[rrIndex];
	}

	public int size() {
		return size;
	}

	public void printDeque() {
		for (int i = 0; i < size; i++) {
			int index = (head + i) % items.length;
			if (i < size - 1) {
				System.out.print(items[index] + " ");
			}
			else {
				System.out.println(items[index]);
			}
		}
	}

	public int capacity() {
		return items.length;
	}

	public double ratio() {
		return (double) size / items.length;
	}

	public void printArray() {
		for (int i = 0; i < items.length; i++) {
			if (i < items.length - 1) {
				System.out.print(items[i] + " ");
			}
			else {
				System.out.println(items[i]);
			}
		}
	}
}
