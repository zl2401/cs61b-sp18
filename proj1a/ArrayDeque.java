public class ArrayDeque<T> {
	private int size;
	private T[] items;

	public ArrayDeque() {
		int init_capacity = 8;
		size = 0;
		items = (T[]) new Object[init_capacity];
	}

	private void resizeArray(int cap) {
		T[] temp = (T[]) new Object[cap];
		System.arraycopy(items, 0, temp, 0, size);
		items = temp;
	}

	public void add(T x) {
		if (size == items.length) {
			resizeArray(2 * size);
		}
		items[size] = x;
		size += 1;
	}

	public T remove() {
		T returnItem = get(size - 1);
		double cap_low_bound = 0.25;
		items[size - 1] = null;
		size -= 1;
		double current_cap_ratio = (double) size / items.length;
		if ((items.length >= 16) & (current_cap_ratio < cap_low_bound)) {
			resizeArray(items.length / 2);
		}
		return returnItem;
	}

	public T get(int index) {
		if (index >= size) {
			return null;
		}
		return items[index];
	}

	public int size() {
		return size;
	}


}