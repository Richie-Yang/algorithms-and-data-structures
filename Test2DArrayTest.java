import java.util.ArrayList;
import java.util.Random;

public class Test2DArrayTest {
  public static void main(String[] args) {
    TwoDimensionArray TdArray = new TwoDimensionArray(5, 5);
    TdArray.flatPrint();
    ArrayList<Integer> resultArray = TdArray.dfs(0, 0);
    System.out.printf("%s\n", resultArray.toString());
  }
}

class TwoDimensionArray {
  ArrayList<ArrayList<Integer>> targetArray;
  ArrayList<Integer> resultArray;
  ArrayList<ArrayList<Integer>> seenArray;
  Integer arrayHeight;
  Integer arrayWidth;
  ArrayList<Direction> dfsDirectionArray;
  Integer isSeen = 1;
  Integer notSeen = 0;

  enum Direction {
    TOP, RIGHT, BOTTOM, LEFT
  }

  TwoDimensionArray(Integer height, Integer width) {
    this.dfsDirectionArray = new ArrayList<>();
    this.dfsDirectionArray.add(Direction.TOP);
    this.dfsDirectionArray.add(Direction.RIGHT);
    this.dfsDirectionArray.add(Direction.BOTTOM);
    this.dfsDirectionArray.add(Direction.LEFT);
    this.arrayHeight = height;
    this.arrayWidth = width;
    this.targetArray = this.build(this.arrayHeight, this.arrayWidth, 10);
  }

  void flatPrint() {
    this.targetArray.forEach(innerArray -> {
      System.out.printf("%s\n", innerArray.toString());
    });
  }

  ArrayList<ArrayList<Integer>> build(Integer height, Integer width, Integer range) {
    ArrayList<ArrayList<Integer>> outerArray = new ArrayList<>();
    for (int h = 0; h < height; h++) {
      ArrayList<Integer> innerArray = new ArrayList<>();
      for (int w = 0; w < width; w++) {
        innerArray.add(getRandomNumber(range));
      }
      outerArray.add(innerArray);
    }
    return outerArray;
  }

  ArrayList<Integer> dfs(Integer startHeight, Integer startWidth) {
    this.seenArray = this.build(this.arrayHeight, this.arrayWidth, this.notSeen);
    this.resultArray = new ArrayList<>();
    dfsTraverse(startHeight, startWidth);
    return this.resultArray;
  }

  void dfsTraverse(Integer heightIndex, Integer widthIndex) {
    Integer currentValue = this.getValue(this.targetArray, heightIndex, widthIndex);
    if (currentValue == null) return;
    this.resultArray.add(currentValue);

    Integer[] finalPoint = new Integer[]{null, null};
    for (Direction dir : dfsDirectionArray) {
      Integer[] point = this.getDirectionIndex(heightIndex, widthIndex, dir);
      Integer pointValue = this.getValue(seenArray, point[0], point[1]);
      Boolean isPointExist = pointValue != null;
      if (!isPointExist) continue;
      Boolean isPointSeen = pointValue == 1;
      if (!isPointSeen) {
        this.setValue(this.seenArray, heightIndex, widthIndex, this.isSeen);
        finalPoint = point;
        break;
      }
    }
    if (finalPoint[0] != null && finalPoint[0] != null) dfsTraverse(finalPoint[0], finalPoint[1]);
    else return;
  }

  private Integer[] getDirectionIndex(Integer heightIndex, Integer widthIndex, Direction dir) {
    Direction direction = dir;
    switch (direction) {
      case TOP:
        heightIndex = heightIndex - 1;
        break;
      case RIGHT:
        widthIndex = widthIndex + 1;
        break;
      case BOTTOM:
        heightIndex = heightIndex + 1;
        break;
      case LEFT:
        widthIndex = widthIndex - 1;
        break;
      default:
        break;
    }
    return new Integer[]{ heightIndex, widthIndex };
  }

  private Integer getValue(ArrayList<ArrayList<Integer>> nestArray, Integer heightIndex, Integer widthIndex) {
    if (heightIndex < 0 || heightIndex > nestArray.size() - 1) return null;
    ArrayList<Integer> innerArray = nestArray.get(heightIndex);
    if (widthIndex < 0 || widthIndex > innerArray.size() - 1) return null;
    return innerArray.get(widthIndex);
  }

  private void setValue(ArrayList<ArrayList<Integer>> nestArray, Integer heightIndex, Integer widthIndex, Integer value) {
    ArrayList<Integer> innerArray = nestArray.get(heightIndex);
    innerArray.set(widthIndex, value);
    return;
  }

  private Integer getRandomNumber(Integer range) {
        Random random = new Random();
        int randomInRange;
        if (range <= 0) randomInRange = 0;
        else randomInRange = random.nextInt(range);
        return randomInRange;
  }
}