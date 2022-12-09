import java.util.Random;

public class SortingTest {
	public static void main(String[] args) {
		long startTime, endTime;
		Random rand = new Random();
		int[] keys = new int[10000];
		int[] output1 = new int[10000];
		int[] output2 = new int[10000];
		for (int i = 0; i < 10000; i++) {
			keys[i] = rand.nextInt();
			output1[i] = keys[i];
			output2[i] = keys[i];
		}

		startTime = System.currentTimeMillis();
		heapSort(output1);
		endTime = System.currentTimeMillis();
		System.out.println("Heapsort takes: " + (endTime - startTime) + " ms");

		startTime = System.currentTimeMillis();
		quickSort(output2);
		endTime = System.currentTimeMillis();
		System.out.println("Quicksort takes: " + (endTime - startTime) + " ms");

	}

	private static void heapSort(int[] items) {
		HeapPQueue heapPQueue = new HeapPQueue(items);
		int[] sorted_list = new int[heapPQueue.size()];
		int sort_index = 0;
		while (!heapPQueue.isEmpty()) {
			sorted_list[sort_index] = heapPQueue.removeMin();
			sort_index++;
		}
	}

	private static void quickSort(int[] items) {
		QuickSort quickSort = new QuickSort(items);
		int[] sorted_list = quickSort.sort_final();
	}
}
