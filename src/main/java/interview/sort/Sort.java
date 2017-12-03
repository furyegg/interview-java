package interview.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort {
	public static void main(String[] args) {
		Integer[] arr = {69, 57, 48, 76, 54, 31, 94, 79, 89, 32, 19, 76, 39, 52, 71, 48, 66, 1, 78, 66, 17, 42, 91, 45, 79, 95, 96, 61, 82, 92, 200, 100};
//		Integer[] arr = {5, 6, 4, 3, 8, 2, 1};
		
		List<Integer> list = new ArrayList<>(Arrays.asList(arr));
		Collections.sort(list);
		System.out.println(list);
		
//		insertSort(arr);
//		shellSort(arr);
//		selectSort(arr);
//		heapSort(arr);
//		bubbleSort(arr);
//		quickSort(arr);
//		mergeSort(arr);
		bucketSort(arr);
		countingSort(arr);
//		radixSort(arr);
		System.out.println(Arrays.asList(arr));
	}
	
	private static void radixSort(Integer[] arr) {
	
	}
	
	private static void countingSort(Integer[] arr) {
	
	}
	
	private static void bucketSort(Integer[] arr) {
	
	}
	
	private static void mergeSort(Integer[] arr) {
		mergeSort(arr, 0, arr.length - 1);
	}
	
	private static void mergeSort(Integer[] arr, int start, int end) {
		if (start >= end) {
			return;
		}
		if (end - start == 1) {
			if (arr[start] > arr[end]) {
				exchange(arr, start, end);
			}
			return;
		}
		int m = start + (end - start) / 2;
		mergeSort(arr, start, m - 1);
		mergeSort(arr, m, end);
		merge(arr, start, m, end);
	}
	
	private static void merge(Integer[] arr, int start, int m, int end) {
		Integer[] merge = new Integer[end - start + 1];
		int left = start;
		int right = m;
		int i = 0;
		while (left <= m - 1 && right <= end) {
			merge[i++] = arr[left] < arr[right] ? arr[left++] : arr[right++];
		}
		while (left <= m - 1) {
			merge[i++] = arr[left++];
		}
		while (right <= end) {
			merge[i++] = arr[right++];
		}
		
		for (int j = 0; j < merge.length; ++j) {
			arr[start + j] = merge[j];
		}
	}
	
	private static void quickSort(Integer[] arr) {
		// Collections.shuffle(Arrays.asList(arr));
		quickSort(arr, 0, arr.length - 1);
	}
	
	private static void quickSort(Integer[] arr, int start, int end) {
		if (start >= end) {
			return;
		}
		int p = partition(arr, start, end);
		quickSort(arr, start, p - 1);
		quickSort(arr, p + 1, end);
	}
	
	private static int partition(Integer[] arr, int start, int end) {
		int p = arr[start];
		int low = start;
		int high = end + 1;
		while (true) {
			while (arr[++low] < p) if (low == end) break;
			while (arr[--high] > p) if (high == start) break;
			if (low >= high) break;
			exchange(arr, low, high);
		}
		exchange(arr, start, high);
		return high;
	}
	
	private static void bubbleSort(Integer[] arr) {
		for (int i = 0; i < arr.length; ++i) {
			for (int j = i + 1; j < arr.length; ++j) {
				if (arr[i] > arr[j]) {
					exchange(arr, i, j);
				}
			}
		}
	}
	
	private static void heapSort(Integer[] arr) {
		List<Integer> list = new ArrayList<>(Arrays.asList(arr));
		list.add(0, -1);
		Integer[] heap = list.toArray(new Integer[list.size()]);
		
		int len = heap.length;
		for (int k = len / 2; k >= 1; k--) {
			sink(heap, k, len);
		}
		while (len > 1) {
			exchange(heap, 1, --len);
			sink(heap, 1, len);
		}
		System.out.println(Arrays.asList(heap));
	}
	
	private static void sink(Integer[] arr, int k, int len) {
		while (k * 2 < len) {
			int j = k * 2;
			if (j + 1 < len && arr[j] < arr[j + 1]) ++j;
			if (arr[k] > arr[j]) break;
			exchange(arr, k , j);
			k = j;
		}
	}

	private static void selectSort(Integer[] arr) {
		for (int i = 0; i < arr.length; ++i) {
			int min = i;
			for (int j = i + 1; j < arr.length; ++j) {
				if (arr[min] > arr[j]) {
					min = j;
				}
			}
			exchange(arr, i, min);
		}
	}

	private static void shellSort(Integer[] arr) {
		int len = arr.length;
		// int h = len / 3 + 1;
		int h = 1;
		while (h < len / 3) {
			h = h * 3 + 1;
		}
		while (h >= 1) {
			for (int i = h; i < len; ++i) {
				for (int j = i; j >= h && arr[j] < arr[j - h]; j -= h) {
					exchange(arr, j, j - h);
				}
			}
			h /= 3;
		}
	}

	private static void insertSort(Integer[] arr) {
		for (int i = 1; i < arr.length; ++i) {
			for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
				exchange(arr, j, j - 1);
			}
		}
	}

	private static void insertSort2(Integer[] arr) {
		for (int i = 1; i < arr.length; ++i) {
			int index = 0;
			int insert = 0;

			for (int m = 0; m < i; ++m) {
				if (arr[i] < arr[m]) {
					index = m;
					insert = arr[i];
				}
			}

			for (int j = index; j < i; ++j) {
				arr[j + 1] = arr[j];
			}

			arr[index] = insert;
		}
	}

	private static void exchange(Integer[] arr, int i, int j) {
		if (i == j) {
			return;
		}
		arr[i] = arr[i] + arr[j];
		arr[j] = arr[i] - arr[j];
		arr[i] = arr[i] - arr[j];
	}
	
}