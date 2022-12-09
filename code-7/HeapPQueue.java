import java.util.*;
import java.util.stream.*;

public class HeapPQueue implements PQueue {
	private int[] list;
	private int size;

	public HeapPQueue(int[] items) {
		this.bottom_up(items);
	}

	public int size() {
		return list.length;
	}

	public boolean isEmpty() {
		return list.length == 0;
	}

	public void insert(int key) {
		this.heapup(key);
	}

	public int removeMin() {
		int min = this.min();
		this.heapdown();
		return min;
	}

	public int min() {
		int temp = list[0];
		for (int i = 0; i < list.length; i++) {
			if (temp > list[i])
				temp = list[i];
		}
		return temp;
	}

	private void bottom_up(int[] items) {
		size = items.length;
		list = new int[size];
		for (int i = 0; i < size; i++)
			list[i] = items[i];
	}

	private void heapdown() {
		int temp = list[0];
		int temp_index = 0;
		for (int i = 0; i < list.length; i++) {
			if (temp > list[i]) {
				temp = list[i];
				temp_index = i;
			}
		}
		List<Integer> arrayList = IntStream.of(list).boxed().collect(Collectors.toList());
		arrayList.remove(temp_index);
		list = arrayList.stream().mapToInt(Integer::intValue).toArray();
	}

	private void heapup(int i) {
		int[] temp_list = new int[list.length + 1];
		temp_list[list.length] = i;
		System.arraycopy(list, 0, temp_list, 0, list.length);
	}
}
