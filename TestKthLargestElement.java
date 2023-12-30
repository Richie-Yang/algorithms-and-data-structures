import java.util.ArrayList;

public class TestKthLargestElement {
  public static void main(String[] args) {
   ArrayList<Integer> input = new ArrayList<>();
   input.add(9);
   input.add(5);
   input.add(1);
   input.add(4);

   KthLargestElement kle = new KthLargestElement(input);
   int output = kle.exec(2);
   System.out.printf("%s\n", output);
  }
}

// 9    5    1    4
// 4    5    1    9
// 1    4    5    9
// 1    4    5    9
// 1    4    5    9

class KthLargestElement {
  ArrayList<Integer> array;
  KthLargestElement(ArrayList<Integer> inputs) {
    this.init(inputs);
  }

  public Integer exec(int kth) {
    System.out.printf("input: %s\n",  this.array.toString());
    quickSort(0, this.array.size() - 1);
    System.out.printf("output: %s\n",  this.array.toString());
    return this.array.get(this.array.size() - kth);
  }

  public void init(ArrayList<Integer> inputs) {
    array = inputs;
  }  

  private ArrayList<Integer> quickSort(int startIndex, int endIndex) {
    if (startIndex < endIndex) {
      int pivot = sortPivot(startIndex, endIndex);
      quickSort(startIndex, pivot - 1);
      quickSort(pivot + 1, endIndex);
    }
    return this.array;
  }

  private int sortPivot(int startIndex, int endIndex) {
   int swapIndex = startIndex;
   int currentIndex = startIndex + 1;
   Integer startValue = this.array.get(startIndex);
    for (; currentIndex <= endIndex; currentIndex++) {
      Integer currentValue = this.array.get(currentIndex);
      if (startValue <= currentValue) continue;
      this.swap(currentIndex, swapIndex);
      swapIndex++;
    }
    this.swap(startIndex, swapIndex - 1);
    return swapIndex;
  }

  private void swap(int indexA, int indexB) {
    Integer valueA = this.array.get(indexA);
    Integer valueB = this.array.get(indexB);
    this.array.set(indexA, valueB);
    this.array.set(indexB, valueA);
  }

  private Integer getElementByIndex(int index) {
    return 1;
  }
  
}