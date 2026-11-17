package Exercise_23._01Extra;

import java.util.Arrays;

public class Exercise_23_01Extra {
	private static final int DEFAULT_SIZE = 10_000;
	private static final int DEFAULT_REPETITIONS = 3;
	private static final int QUADRATIC_LIMIT = 100_000;

	public static void main(String[] args) {
		int size = args.length > 0 ? Integer.parseInt(args[0])
			: DEFAULT_SIZE;
		int repetitions = args.length > 1 ? Integer.parseInt(args[1])
			: DEFAULT_REPETITIONS;

		System.out.println("Average time over " + repetitions
			+ " run(s) for " + size + " items:");
		runBenchmarks(size, repetitions);
	}

	private static void runBenchmarks(int size, int repetitions) {
		long bubble = 0;
		long selection = 0;
		long insertion = 0;
		long merge = 0;
		long quick = 0;
		long heap = 0;
		long radixDecimal = 0;
		long radixBinary = 0;

		boolean runQuadratic = size <= QUADRATIC_LIMIT;

		for (int run = 0; run < repetitions; run++) {
			int[] original = new int[size];
			for (int i = 0; i < size; i++) {
				original[i] = (int)(Math.random() * Integer.MAX_VALUE);
			}

			if (runQuadratic) {
				int[] data = Arrays.copyOf(original, size);
				long start = System.nanoTime();
				bubbleSort(data);
				bubble += System.nanoTime() - start;
				checkSorted(data);

				data = Arrays.copyOf(original, size);
				start = System.nanoTime();
				selectionSort(data);
				selection += System.nanoTime() - start;
				checkSorted(data);

				data = Arrays.copyOf(original, size);
				start = System.nanoTime();
				insertionSort(data);
				insertion += System.nanoTime() - start;
				checkSorted(data);
			}

			int[] data = Arrays.copyOf(original, size);
			long start = System.nanoTime();
			mergeSort(data);
			merge += System.nanoTime() - start;
			checkSorted(data);

			data = Arrays.copyOf(original, size);
			start = System.nanoTime();
			quickSort(data, 0, data.length - 1);
			quick += System.nanoTime() - start;
			checkSorted(data);

			data = Arrays.copyOf(original, size);
			start = System.nanoTime();
			heapSort(data);
			heap += System.nanoTime() - start;
			checkSorted(data);

			data = Arrays.copyOf(original, size);
			start = System.nanoTime();
			radixSortDecimal(data);
			radixDecimal += System.nanoTime() - start;
			checkSorted(data);

			data = Arrays.copyOf(original, size);
			start = System.nanoTime();
			radixSortBinary(data);
			radixBinary += System.nanoTime() - start;
			checkSorted(data);
		}

		if (runQuadratic) {
			print("Bubble Sort", bubble, repetitions);
			print("Selection Sort", selection, repetitions);
			print("Insertion Sort", insertion, repetitions);
		}
		else {
			System.out.println("Bubble Sort: skipped");
			System.out.println("Selection Sort: skipped");
			System.out.println("Insertion Sort: skipped");
		}
		print("Merge Sort", merge, repetitions);
		print("Quick Sort", quick, repetitions);
		print("Heap Sort", heap, repetitions);
		print("Decimal Radix Sort", radixDecimal, repetitions);
		print("Binary Radix Sort", radixBinary, repetitions);
	}

	private static void print(String name, long nanos, int repetitions) {
		System.out.printf("%s: %.3f ms%n", name,
			nanos / 1_000_000.0 / repetitions);
	}

	private static void checkSorted(int[] list) {
		for (int i = 1; i < list.length; i++) {
			if (list[i - 1] > list[i]) {
				throw new IllegalStateException("Array not sorted");
			}
		}
	}

	public static void bubbleSort(int[] list) {
		boolean needNextPass = true;
		for (int k = 1; k < list.length && needNextPass; k++) {
			needNextPass = false;
			for (int i = 0; i < list.length - k; i++) {
				if (list[i] > list[i + 1]) {
					int temp = list[i];
					list[i] = list[i + 1];
					list[i + 1] = temp;
					needNextPass = true;
				}
			}
		}
	}

	public static void selectionSort(int[] list) {
		for (int i = 0; i < list.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < list.length; j++) {
				if (list[j] < list[minIndex]) {
					minIndex = j;
				}
			}
			if (minIndex != i) {
				int temp = list[i];
				list[i] = list[minIndex];
				list[minIndex] = temp;
			}
		}
	}

	public static void insertionSort(int[] list) {
		for (int i = 1; i < list.length; i++) {
			int current = list[i];
			int j = i - 1;
			while (j >= 0 && list[j] > current) {
				list[j + 1] = list[j];
				j--;
			}
			list[j + 1] = current;
		}
	}

	public static void mergeSort(int[] list) {
		if (list.length < 2) {
			return;
		}
		int middle = list.length / 2;
		int[] left = Arrays.copyOfRange(list, 0, middle);
		int[] right = Arrays.copyOfRange(list, middle, list.length);
		mergeSort(left);
		mergeSort(right);
		merge(left, right, list);
	}

	private static void merge(int[] left, int[] right, int[] result) {
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				result[k++] = left[i++];
			}
			else {
				result[k++] = right[j++];
			}
		}
		while (i < left.length) {
			result[k++] = left[i++];
		}
		while (j < right.length) {
			result[k++] = right[j++];
		}
	}

	public static void quickSort(int[] list, int first, int last) {
		if (first >= last) {
			return;
		}
		int pivotIndex = partition(list, first, last);
		quickSort(list, first, pivotIndex - 1);
		quickSort(list, pivotIndex + 1, last);
	}

	private static int partition(int[] list, int first, int last) {
		int middle = (first + last) >>> 1;
		int pivot = list[middle];
		swap(list, middle, last);
		int store = first;
		for (int i = first; i < last; i++) {
			if (list[i] < pivot) {
				swap(list, i, store++);
			}
		}
		swap(list, store, last);
		return store;
	}

	private static void swap(int[] list, int i, int j) {
		int temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}

	public static void heapSort(int[] list) {
		for (int i = list.length / 2 - 1; i >= 0; i--) {
			siftDown(list, i, list.length);
		}
		for (int end = list.length - 1; end > 0; end--) {
			swap(list, 0, end);
			siftDown(list, 0, end);
		}
	}

	private static void siftDown(int[] list, int root, int size) {
		while (true) {
			int left = root * 2 + 1;
			if (left >= size) {
				return;
			}
			int largest = left;
			int right = left + 1;
			if (right < size && list[right] > list[left]) {
				largest = right;
			}
			if (list[root] >= list[largest]) {
				return;
			}
			swap(list, root, largest);
			root = largest;
		}
	}

	public static void radixSortDecimal(int[] list) {
		int[] temp = new int[list.length];
		long dev = 1;
		for (int digit = 0; digit < 10; digit++) {
			int[] count = new int[10];
			for (int value : list) {
				count[(int)((value / dev) % 10)]++;
			}
			int[] start = new int[10];
			for (int i = 1; i < 10; i++) {
				start[i] = start[i - 1] + count[i - 1];
			}
			for (int value : list) {
				int bucket = (int)((value / dev) % 10);
				temp[start[bucket]++] = value;
			}
			System.arraycopy(temp, 0, list, 0, list.length);
			dev *= 10;
		}
	}

	public static void radixSortBinary(int[] list) {
		int[] temp = new int[list.length];
		for (int bit = 0; bit < 32; bit++) {
			int[] count = new int[2];
			for (int value : list) {
				count[(value >>> bit) & 1]++;
			}
			int[] start = {0, count[0]};
			for (int value : list) {
				int bucket = (value >>> bit) & 1;
				temp[start[bucket]++] = value;
			}
			System.arraycopy(temp, 0, list, 0, list.length);
		}
	}
}
