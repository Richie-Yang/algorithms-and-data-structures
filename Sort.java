
// sorts for bubble sort, insertion sort, selection sort

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Sort {
  public static void main(String[] args) {

    boolean isDebug = false;
    int[] arr = createArray(100000);
    // int[] arr = { 100, 30, 90000, 73, 500, 71 };
    // printArray(arr);

    // [1, 3, 9], [2, 5, 7]
    // [1, 2, 3, 5, 7, 9]
    // int[] arr1 = { 1, 3, 9 };
    // int[] arr2 = { 2, 5, 7 };
    // int[] result = mergeArray(arr1, arr2);
    // printArray(result);

    // selectionSort(arr, isDebug);
    // bubbleSort(arr, isDebug);
    insertionSort(arr, isDebug);
    mergeSort(arr, isDebug);
    quickSort(arr, isDebug);
    mapSort(arr, isDebug);
    redixSort(arr, isDebug);

    // int x = getDigit(5123, 3);
    // int y = getCount(5123);
    // System.out.println(x);
    // System.out.println(y);
  }

  public static int[] createArray(int size) {
    int[] arr = new int[size];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * size);
    }
    return arr;
  }

  private static void printArray(int[] arr) {
    System.out.printf("%s\n", java.util.Arrays.toString(arr));
  }

  private static void printArray(String[] arr) {
    System.out.printf("%s\n", java.util.Arrays.toString(arr));
  }

  public static int[] selectionSort(int[] arr, boolean isDebug) {
    int[] cloneArray = arr.clone();
    long startTime = System.currentTimeMillis();
    for (int x = 0; x < cloneArray.length; x++) {
      for (int y = x + 1; y < cloneArray.length; y++) {
        if (cloneArray[x] > cloneArray[y]) {
          int temp = cloneArray[x];
          cloneArray[x] = cloneArray[y];
          cloneArray[y] = temp;
        }
      }
    }
    long endTime = System.currentTimeMillis();
    if (isDebug)
      printArray(cloneArray);
    System.out.printf("Time elapsed: %d\n", endTime - startTime);
    return cloneArray;
  }

  public static int[] bubbleSort(int[] arr, boolean isDebug) {
    int[] cloneArray = arr.clone();
    long startTime = System.currentTimeMillis();
    for (int x = 0; x < cloneArray.length; x++) {
      int currentIndex = 0;
      for (int y = currentIndex + 1; y < cloneArray.length - x; y++) {
        // System.out.printf("currentIndex: %d, y: %d\n", currentIndex, y);
        if (cloneArray[currentIndex] > cloneArray[y]) {
          int temp = cloneArray[currentIndex];
          cloneArray[y - 1] = cloneArray[y];
          cloneArray[y] = temp;
        }
        currentIndex++;
      }
      // System.out.println("one loop finished");
    }
    long endTime = System.currentTimeMillis();
    if (isDebug)
      printArray(cloneArray);
    System.out.printf("Time elapsed: %d\n", endTime - startTime);
    return cloneArray;
  }

  public static int[] insertionSort(int[] arr, boolean isDebug) {
    int[] cloneArray = arr.clone();
    long startTime = System.currentTimeMillis();
    for (int x = 1; x < cloneArray.length; x++) {
      int lowest = cloneArray[x];
      for (int y = x - 1; y >= 0; y--) {
        if (lowest < cloneArray[y]) {
          cloneArray[y + 1] = cloneArray[y];
          cloneArray[y] = lowest;
        } else
          break;
      }
    }
    long endTime = System.currentTimeMillis();
    if (isDebug)
      printArray(cloneArray);
    System.out.printf("Time elapsed: %d\n", endTime - startTime);
    return cloneArray;
  }

  public static int[] mergeSort(int[] array, boolean isDebug) {
    int[] cloneArray = array.clone();
    long startTime = System.currentTimeMillis();
    cloneArray = internalMergeSort(cloneArray);
    long endTime = System.currentTimeMillis();
    if (isDebug)
      printArray(cloneArray);
    System.out.printf("Time elapsed: %d\n", endTime - startTime);
    return cloneArray;
  }

  private static int[] internalMergeSort(int[] array) {
    if (array.length <= 1)
      return array;
    int middleIndex = array.length / 2;
    int[] leftArray = internalMergeSort(Arrays.copyOfRange(array, 0, middleIndex));
    int[] rightArray = internalMergeSort(Arrays.copyOfRange(array, middleIndex, array.length));
    return mergeArray(leftArray, rightArray);
  }

  private static int[] mergeArray(int[] array1, int[] array2) {
    int totalLength = array1.length + array2.length;
    int[] newArray = new int[totalLength];
    int newArrayIndex = 0;
    int index1 = 0;
    int index2 = 0;

    while (index1 < array1.length && index2 < array2.length) {
      if (array1[index1] < array2[index2]) {
        newArray[newArrayIndex] = array1[index1];
        index1++;
      } else {
        newArray[newArrayIndex] = array2[index2];
        index2++;
      }
      newArrayIndex++;
    }
    while (index1 < array1.length) {
      newArray[newArrayIndex] = array1[index1];
      index1++;
      newArrayIndex++;
    }
    while (index2 < array2.length) {
      newArray[newArrayIndex] = array2[index2];
      index2++;
      newArrayIndex++;
    }
    return newArray;
  }

  private static int[] quickSort(int[] array, boolean isDebug) {
    int[] cloneArray = array.clone();
    long startTime = System.currentTimeMillis();
    cloneArray = internalQuickSort(cloneArray, 0, array.length - 1);
    long endTime = System.currentTimeMillis();
    if (isDebug)
      printArray(cloneArray);
    System.out.printf("Time elapsed: %d\n", endTime - startTime);
    return cloneArray;
  }

  private static int[] internalQuickSort(int[] array, int left, int right) {
    if (left < right) {
      int pivotIndex = partition(array, left, right);
      internalQuickSort(array, left, pivotIndex - 1);
      internalQuickSort(array, pivotIndex + 1, right);
    }
    return array;
  }

  private static int partition(int[] array, int left, int right) {
    int swapIndex = left;
    for (int i = left + 1; i < right + 1; i++) {
      if (array[left] > array[i]) {
        swapIndex++;
        swap(array, swapIndex, i);
      }
    }
    swap(array, left, swapIndex);
    return swapIndex;
  }

  private static void swap(int[] array, int index1, int index2) {
    int temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }

  public static int[] redixSort(int[] array, boolean isDebug) {
    long startTime = System.currentTimeMillis();
    int mostDigit = getMostDigits(array);
    for (int x = 0; x < mostDigit; x++) {
      int currentPlace = x;
      ArrayList<ArrayList<Integer>> subCollection = new ArrayList<ArrayList<Integer>>();

      for (int i = 0; i <= 9; i++)
        subCollection.add(i, new ArrayList<Integer>());

      for (int y = 0; y < array.length; y++) {
        int currentItem = array[y];
        int index = getDigit(currentItem, currentPlace);
        subCollection.get(index).add(currentItem);
      }

      ArrayList<Integer> tempArray = new ArrayList<Integer>();
      for (int z = 0; z < subCollection.size(); z++) {
        ArrayList<Integer> indexArray = subCollection.get(z);
        for (Integer item : indexArray)
          tempArray.add(item);
      }
      array = tempArray.stream().mapToInt(i -> i).toArray();
    }
    if (isDebug)
      printArray(array);
    long endTime = System.currentTimeMillis();
    System.out.printf("Time elapsed: %d\n", endTime - startTime);
    return array;
  }

  private static int getDigit(int num, int place) {
    String[] xStrings = String.valueOf(num).split("");
    int index = (xStrings.length - 1) - place;
    if (index < 0)
      return 0;
    return Integer.valueOf(xStrings[index]);
  }

  private static int getCount(int num) {
    return String.valueOf(num).split("").length;
  }

  private static int getMostDigits(int[] numbers) {
    int max = 0;
    for (int num : numbers) {
      int current = getCount(num);
      if (max < current)
        max = current;
    }
    return max;
  }

  public static int[] mapSort(int[] array, boolean isDebug) {
    int[] cloneArray = array.clone();
    int[] resultArray = new int[cloneArray.length];
    long startTime = System.currentTimeMillis();
    Map<Integer, Integer> map = new TreeMap<>();
    for (int i = 0; i < cloneArray.length; i++) {
      map.put(cloneArray[i], i);
    }
    for (int i = 0; i < cloneArray.length; i++) {
      resultArray[i] = map.get(cloneArray[i]);
    }
    long endTime = System.currentTimeMillis();
    if (isDebug)
      printArray(cloneArray);
    System.out.printf("Time elapsed: %d\n", endTime - startTime);
    return resultArray;
  }
}
