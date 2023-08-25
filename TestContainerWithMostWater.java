import java.util.ArrayList;

public class TestContainerWithMostWater {
  public static void main(String[] args) {
    int[] testArray1 = { 7, 1, 2, 3, 9 };
    int[] testArray2 = { 1, 7, 9, 3, 2 };

    ContainerWithMostWater cwm = new ContainerWithMostWater();

    ArrayList<Integer> result1 = cwm.getArray(testArray1);
    System.out.println(result1.toString());
    int result2 = cwm.getNumber(testArray1);
    System.out.println(result2);

    ArrayList<Integer> result3 = cwm.getArray(testArray2);
    System.out.println(result3.toString());
    int result4 = cwm.getNumber(testArray2);
    System.out.println(result4);
  }
}

// {1, 7, 9, 3, 2}
// {1 1}
// 1 * 4 = 4 <-- biggestNum
// { 7 2}
// 2 * 3 = 6
// { 7 3 }
// 3 * 2 = 6
// { 7 9 }
// 7 * 1 = 7

class ContainerWithMostWater {
  ContainerWithMostWater() {
  }

  public ArrayList<Integer> getArray(int[] array) {
    ArrayList<Integer> result = this.exec(array);
    result.remove(2);
    return result;
  }

  public int getNumber(int[] array) {
    ArrayList<Integer> result = this.exec(array);
    return result.get(2);
  }

  private ArrayList<Integer> exec(int[] array) {
    ArrayList<Integer> resultArray = new ArrayList<>();
    int maxNum = 0;
    int maxFrontP = 0;
    int maxBackP = array.length - 1;

    int backPointer = array.length - 1;
    for (int frontPointer = 0; frontPointer < array.length;) {
      int frontNum = array[frontPointer];
      int backNum = array[backPointer];
      int finalNum = frontNum > backNum ? backNum : frontNum;
      int size = finalNum * (backPointer - frontPointer);

      if (size > maxNum) {
        maxFrontP = frontPointer;
        maxBackP = backPointer;
        maxNum = size;
      }

      if (frontNum > backNum)
        backPointer--;
      else
        frontPointer++;

      if (frontPointer == backPointer)
        break;
    }

    resultArray.add(maxFrontP);
    resultArray.add(maxBackP);
    resultArray.add(maxNum);
    return resultArray;
  }
}