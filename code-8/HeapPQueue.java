public class HeapPQueue implements PQueue {
	private int[] list;
	private int size;

	public HeapPQueue(int[] items) {
		size = items.length;
		list = new int[size];
		for (int i = 0; i < size; i++) {
			list[i] = items[i];
		}
	}

	public int size() {
		int temp = 0;
		for (int i : list) {
			if (i == 1)
				temp++;
		}
		return temp;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public void insert(int key) {
		list[key] = 1;
	}

	public int removeMin(int key) {
		list[key] = -1;
		return key;
	}

	public int min() {
		return 0;
	}

	public boolean isVisited(int key) {
		return list[key] == 1;
	}
}
