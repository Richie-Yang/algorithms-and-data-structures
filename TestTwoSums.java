import java.util.ArrayList;
import java.util.Hashtable;

public class TestTwoSums {
  public static void main(String[] args) {
    int[] testArray = { 1, 4, 5, 100, 20, 8, 9, 2 };
    // ArrayList<Integer> result1 = TwoSums.exec1(testArray, 11);
    ArrayList<Integer> result2 = TwoSums.exec2(testArray, 11);
    // System.out.printf("result1: %s\n", result1.toString());
    System.out.printf("result2: %s\n", result2.toString());
  }
}

// [1, 4, 5] -> [1, 2] -> 9

class TwoSums {
  static ArrayList<Integer> exec1(int[] array, int target) {
    long startTime = System.currentTimeMillis();
    ArrayList<Integer> result = new ArrayList<>();
    Boolean isSolved = false;

    for (int x = 0; x < array.length - 1; x++) {
      int diff = target - array[x];
      int startNum = x + 1;
      // System.out.printf("x:%s diff:%s start:%s\n", x, diff, startNum);
      for (int y = startNum; y < array.length; y++) {
        int currentNum = array[y];
        // System.out.printf("y:%s current:%s\n", y, currentNum);
        if (diff != currentNum)
          continue;
        result.add(x);
        result.add(y);
        isSolved = true;
        break;
      }
      if (isSolved == true)
        break;
    }
    long endTime = System.currentTimeMillis();
    System.out.printf("startTime: %d\n", startTime);
    System.out.printf("endTime: %d\n", endTime);
    System.out.printf("Time elapsed: %d\n", endTime - startTime);
    return result;
  }

  // [1, 3, 5, 7, 9, 2] t = 11
  static ArrayList<Integer> exec2(int[] array, int target) {
    long startTime = System.currentTimeMillis();
    Hashtable<Integer, Integer> temp = new Hashtable<>();
    ArrayList<Integer> result = new ArrayList<>();

    for (int i = 0; i < array.length; i++) {
      int currentNum = array[i];
      if (!temp.containsKey(currentNum)) {
        int numToFind = target - currentNum;
        temp.put(numToFind, i);
        continue;
      }
      int index = temp.get(currentNum);
      result.add(index);
      result.add(i);
      break;
    }
    long endTime = System.currentTimeMillis();
    System.out.printf("Time elapsed: %d\n", endTime - startTime);
    return result;
  }
}