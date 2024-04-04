import java.util.ArrayList;

class TestWallsAndGates {
  public static void main(String[] args) {
    WallsAndGates wag = new WallsAndGates();
    wag.exec();
  }
}

class WallsAndGates {
  Integer W = -1;
  Integer G = 0;
  Integer E = 999;
  Integer[][] directions = new Integer[][]{
    {1,0},
    {0,1},
    {-1,0},
    {0,-1}
  };

  WallsAndGates() {}

  public Integer[][] exec() {
    System.out.printf("%s\n", 123);
    Integer[][] map = createMap();
    this.sqs(map);
    return map;
  }

  private void sqs(Integer[][] map) {
    for (Integer row = 0; row < map.length; row++) {
      for (Integer col = 0; col < map[row].length; col++) {
        if (map[row][col] == this.G) {
          System.out.printf("row: %s, col: %s\n", row, col);
          bfs(map, row, col);
        }
      }
    }
    printMap(map);
  }

  private void bfs(Integer[][] map, Integer rowIndex, Integer colIndex) {
    System.out.printf("%s\n", "hit");
    ArrayList<Integer[]> queue = new ArrayList<>();
    ArrayList<String> isSeen = new ArrayList<>();
    queue.add(new Integer[]{rowIndex, colIndex});
    Integer currentVal = 1;
    Boolean isFirst = false;

    while (queue.size() > 0) {
      System.out.printf("currentVal: %s\n", currentVal);
      printMap(map);
      // System.out.printf("queue.size:", queue.size());
      Integer[] current = queue.removeFirst();
      String currentMark = current[0].toString() + current[1].toString();
      isSeen.add(currentMark);
      if (isFirst) currentVal = map[current[0]][current[1]] + 1;
      isFirst = true;

      for (Integer[] dir : directions) {
        Integer newRowIndex = current[0] + dir[0];
        Integer newColIndex = current[1] + dir[1];
        if (newRowIndex < 0 || newRowIndex > map.length-1) continue;
        if (newColIndex < 0 || newColIndex > map[0].length-1) continue;
        Integer nextVal = map[newRowIndex][newColIndex];
        if (nextVal == this.W || nextVal == this.G) continue;
        String nextMark = newRowIndex.toString() + newColIndex.toString();
        if (isSeen.contains(nextMark)) continue;

        queue.add(new Integer[]{newRowIndex, newColIndex});
        if (currentVal < nextVal) map[newRowIndex][newColIndex] = currentVal;
      }
    }
  }

  private Integer[][] createMap() {
    Integer[][] map = new Integer[][]{
      {E,W,G,E},
      {E,E,E,W},
      {E,W,E,W},
      {G,W,E,E}
    };
    printMap(map);
    return map;
  }

  private void printMap(Integer[][] map) {
    System.out.printf("\n");
    for (Integer[] row : map) {
      for (Integer col : row) {
        System.out.printf("%s,", col);
      }
      System.out.printf("\n");
    }
  }
}