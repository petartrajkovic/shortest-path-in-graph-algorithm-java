public class QuickSort {
  private int[] list;
  private int size;

  public QuickSort(int[] items) {
    size = items.length;
    list = new int[size];
    for (int i = 0; i < size; i++)
      list[i] = items[i];
  }

  private void exchange(int[] arr, int index1, int index2) {
    int temp = arr[index1];
    arr[index1] = arr[index2];
    arr[index2] = temp;
  }

  private int partition(int[] arr, int low, int high) {
    int temp = arr[high];
    int i = (low - 1);
    for (int j = low; j <= high - 1; j++) {
      if (arr[j] <= temp) {
        i++;
        exchange(arr, i, j);
      }
    }
    exchange(arr, i + 1, high);
    return (i + 1);
  }

  private void sort_algorithm(int[] arr, int low, int high) {
    if (low < high) {
      int temp = partition(arr, low, high);
      sort_algorithm(arr, low, temp - 1);
      sort_algorithm(arr, temp + 1, high);
    }
  }

  public int[] sort_final() {
    sort_algorithm(list, 0, size - 1);
    return list;
  }
}
