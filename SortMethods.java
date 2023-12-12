import java.util.List;
/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Michael Yeung
 *	@since	30 November, 2023
 */
public class SortMethods {
	private List<City> temp; //array for merge sort
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(List<City> arr) {
		for (int outer = arr.size() - 1; outer > 0; outer--) {
			for (int inner = 0; inner < outer; inner++) {
				if (arr.get(inner).compareTo(arr.get(inner + 1)) > 0) {
					swap(arr, inner, inner + 1);
				}
			}
		}	
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> arr, int x, int y) {
		List<City> temp = arr.get(x);
		arr.get(y) = arr.get(x);
		arr.get(x) = temp;
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(List<City> arr) {
		for (int outer = arr.size() - 1; outer > 0; outer--) {
			int maxIndex = 0;
			for (int inner = 0; inner <= outer; inner++) {
				if (arr.get(inner).compareTo(arr.get(maxIndex)) > 0) {
					maxIndex = inner;
				}
			}
			swap(arr, maxIndex, outer);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(List<City> arr) {
		for (int outer = 1; outer < arr.size(); outer++) {
			int inner = outer;
			while (inner > 0 && arr.get(inner).compareTo(arr.get(inner - 1)) < 0) {
				swap(arr, inner, inner - 1);
				inner--;
			}
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(List<City> arr) {
		temp = new List<City>();
		mergeSort(arr, 0, arr.size() - 1);
	}
	
	/** Helper recursive method */
	private void mergeSort(List<City> arr, int start, int end) {
		if (start != end) { //if small array not 1 in length
			mergeSort(arr, start, (start + end + 1) / 2 - 1);
			mergeSort(arr, (start + end + 1) / 2, end);
			
			int counter = start; //number of elements stored
			int pr = (start + end + 1) / 2; //right pointer
			for (int pl = start; pl < (start + end + 1) / 2; pl++) { //left pointer
				while (pr <= end && arr.get(pr) < arr.get(pl)) {
					temp.get(counter) = arr.get(pr);
					counter++;
					pr++;
				}
				temp.get(counter) = arr.get(pl);
				counter++;
			}
			while (pr <= end) {
				temp.get(counter) = arr.get(pr);
				pr++;
				counter++;
			}
			for (int i = start; i <= end; i++) {
				arr.get(i) = temp.get(i);
			}
		}		 
	}
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(List<City> arr) {
		if (arr.size() == 0) System.out.print("(");
		else System.out.printf("( %4d", arr.get(0));
		for (int a = 1; a < arr.size(); a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr.get(a));
			else System.out.printf(", %4d", arr.get(a));
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		/*
		List<City> arr = new List<City>();
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr.get(a) = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		for (int a = 0; a < 10; a++)
			arr.get(a) = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr.get(a) = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr.get(a) = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		*/
	}
}
