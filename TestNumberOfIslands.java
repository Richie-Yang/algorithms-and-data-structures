import java.util.ArrayList;

public class TestNumberOfIslands {
  static public void main(String[] args) {
    NumberOfIslands noi = new NumberOfIslands();
    Integer[][] map = noi.createMap();
    noi.countIslands(map);

  }
}

class NumberOfIslands {
  NumberOfIslands() {
  }

  public Integer[][] createMap() {
    Integer[][] map = new Integer[][]{
      {1,1,1,1,0},
      {1,1,0,1,0},
      {1,1,0,0,1},
      {0,0,0,1,1}
    };
    printMap(map);
    return map;
  }

  public void printMap(Integer[][] map) {
    System.out.printf("//////\n");
    for (Integer[] row : map) {
      for (Integer column : row) {
        System.out.printf("%s,", column);
      }
      System.out.printf("\n");
    }
  }

  public Integer countIslands(Integer[][] map) {
    Integer counts = 0;
    Integer length = map[0].length;
    for (Integer x = 0; x < length - 1; x++) {
      Integer[] row = map[x];
      for (Integer y = 0; y < row.length; y++) {
        System.out.printf("row: %s column: %s count: %s\n", x, y, counts);
        if (row[y] == 0) continue;
        counts++;
        bfs(map, x, y);
        printMap(map);
      }
    }
    printMap(map);
    System.out.printf("counts: %s\n", counts);
    return counts;
  }

  private void bfs(
    Integer[][] map, 
    Integer startRow, 
    Integer startColumn
    ) {
    ArrayList<Integer[]> queue = new ArrayList<>();
    queue.add(new Integer[]{startRow, startColumn});
    while (queue.size() > 0) {
      Integer[] point = queue.removeFirst();
      Integer row = point[0];
      Integer column = point[1];
      map[row][column] = 0;

      Integer rightVal = 0;
      Integer[] rightColumn = map[row];
      if (rightColumn != null) rightVal = column + 1 < map.length ? rightColumn[column + 1] : 0;

      Integer downVal = 0;
      Integer[] downRow = row + 1 < map.length ? map[row + 1] : null;
      if (downRow != null) downVal = downRow[column];

      Integer leftVal = 0;
      Integer[] leftColumn = map[row];
      if (leftColumn != null) leftVal = column - 1 >= 0 ? leftColumn[column - 1] : 0;

      if (rightVal == 1) 
        queue.add(new Integer[]{row, column + 1});
      if (downVal == 1) 
        queue.add(new Integer[]{row + 1, column}); 
      if (leftVal == 1)
        queue.add(new Integer[]{row, column - 1});
    }
    return;
  }
}