import java.util.ArrayList;

public class TestTrappedRainWater {
  static public void main(String[] args) {
    int[] heights = { 0, 1, 0, 2, 1, 0, 3, 1, 0, 1, 2 };
    // 1 + 3 + 4 = 8
    int expected = 8;
    TrappedRainWater trw = new TrappedRainWater();
    int result1 = trw.regularExec(heights);
    System.out.printf("expected: %s; result: %s; is Equal?: %b\n", expected, result1, expected == result1);

    int result2 = trw.optimalExec(heights);
    System.out.printf("expected: %s; result: %s; is Equal?: %b\n", expected, result2, expected == result2);
  }
}

class TrappedRainWater {
  TrappedRainWater() {}

  public int optimalExec(int[] heights) {
    int leftMax = 0, rightMax = 0, total = 0;
    int leftPointer = 0, rightPointer = heights.length - 1;
    
    for (int i = 0; i < heights.length; i++) {
      int leftHeight = heights[leftPointer];
      int rightHeight = heights[rightPointer];
      if (leftHeight < rightHeight) {
        if (leftHeight > leftMax) leftMax = leftHeight;
        else total = total + (leftMax - leftHeight);
        leftPointer = leftPointer + 1;
      } else {
        if (rightHeight > rightMax) rightMax = rightHeight;
        else total = total + (rightMax - rightHeight);
        rightPointer = rightPointer - 1;
      }
      if (leftPointer == rightPointer) break;
    }
    return total;
  }

  public int regularExec(int[] heights) {
    int total = 0;
    int p = 0;
    int leftPoint = p;
    ArrayList<Integer> tempArray = new ArrayList<>();
    for (; p < heights.length; p++) {
      int currentHight = heights[p];
      if (leftPoint == 0 && currentHight <= 0) 
        continue;
      if (leftPoint == 0) 
        leftPoint = p;
      if (currentHight >= heights[leftPoint]) {
        int leftHeight = heights[leftPoint];
        for (int itemHeight : tempArray) {
          int diff = leftHeight - itemHeight;
          total = total + diff;
        }
        tempArray = new ArrayList<>();
        leftPoint = p;
        continue;
      }
      if (p == heights.length - 1) {
        int minHeight = Math.min(heights[leftPoint], currentHight);
        for (int itemHeight : tempArray) {
          int diff = minHeight - itemHeight;
          total = total + diff;
        }
        break;
      }
      tempArray.add(currentHight);
      System.out.printf("left:%s; right:%s; total:%s\n", leftPoint, p, total);
    }
    return total;
  }
}