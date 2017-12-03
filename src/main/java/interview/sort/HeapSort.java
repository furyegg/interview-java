//package interview.sort;
//
//import java.util.Arrays;
//
//public class HeapSort {
//	public static void main(String[] args) {
//		char[] arr = {(char)20, 'S','O','R','T','E','X','A','M','P','L','E'};
//
//		char[] sortArr = Arrays.copyOf(arr, arr.length);
//		Arrays.sort(sortArr);
//		printArray(sortArr);
//
//		heapSort(arr);
//		printArray(arr);
//	}
//
//	private static void heapSort(char[] heap) {
//		int len = heap.length;
//		for (int k = len / 2; k >= 1; k--) {
//			sink(heap, k, len);
//			System.out.print("sink: ");
//			printArray(heap);
//		}
//		while (len > 1) {
//			exchange(heap, 1, --len);
//			sink(heap, 1, len);
//		}
//	}
//
//	private static void sink(char[] arr, int k, int len) {
//		while (k * 2 < len) {
//			int j = k * 2;
//			if (j + 1 < len && arr[j] < arr[j + 1]) ++j;
//			if (arr[k] > arr[j]) break;
//			exchange(arr, k, j);
//			k = j;
//		}
//	}
//
//	private static void exchange(char[] arr, int i, int j) {
//		if (i == j) {
//			return;
//		}
//		char c = arr[i];
//		arr[i] = arr[j];
//		arr[j] = c;
//	}
//
//	private static void printArray(char[] arr) {
//		for (int i = 0; i < arr.length; ++i) {
//			System.out.print(arr[i]);
//			System.out.print(", ");
//		}
//		System.out.println();
//	}
//}