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
		System.arraycopy(items, head, temp, 0, size - head);
		System.arraycopy(items, 0, temp, size - head, head);
		items = temp;
		head = 0;
	}

	public void addFirst(T x) {
		if (size == items.length) {
			resizeArray(2 * size);
		}
		int index = (head - 1) % items.length;
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
}
