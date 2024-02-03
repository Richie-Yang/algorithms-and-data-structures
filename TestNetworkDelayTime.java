import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class TestNetworkDelayTime {
  public static void main(String[] args) {
    NetworkDelayTime ndt = new NetworkDelayTime();
    ndt.testCase();
  }
}

class NetworkDelayTime {
  Integer infinity = 999999;
  NetworkDelayTime() {}

  public Integer exec(Integer[][] timeInput, Integer numOfNodes, Integer start) {

    // step1: prepare record table
    ArrayList<Integer> queue = new ArrayList<>();
    ArrayList<Integer> seenRecord = new ArrayList<>();
    Hashtable<Integer, Integer> weightedTable = new Hashtable<>();
    Hashtable<Integer, Integer> previousTable = new Hashtable<>();
    for (Integer i = 1; i <= numOfNodes; i++) {
      if (i == start) queue.add(start);
      Integer defaultWeight = i == start ? 0 : this.infinity;
      weightedTable.put(i, defaultWeight);
      previousTable.put(i, this.infinity);
    }

    // step2: loop through each node
    while (queue.size() > 0) {
      // System.out.printf("queue: %s\n", queue);
      Integer node = queue.removeFirst();

      // add into seen in order to avoid future visit
      seenRecord.add(node); 
      // System.out.printf("node: %s\n", node);

      for (Integer[] timeSet: timeInput) {
        Integer startPoint = timeSet[0];
        Integer endPoint = timeSet[1];
        Integer delayTime = timeSet[2];
        // System.out.printf("startPoint: %s\n", startPoint);
        // System.out.printf("endPoint: %s\n", endPoint);

        // it's kind of like BFS, which is just used in Graph here
        if (!seenRecord.contains(endPoint)) queue.add(endPoint);
        
        // if record matched, we calculate the weight
        // and compare to exist weight
        if (startPoint == node) {
          Integer previousWeight = weightedTable.get(startPoint);
          Integer totalWeight = previousWeight + delayTime;
          Integer existWeight = weightedTable.get(endPoint);
          // System.out.printf("totalWeight: %s\n", totalWeight);
          // System.out.printf("existWeight: %s\n", existWeight);
          if (existWeight > totalWeight) {
            weightedTable.put(endPoint, totalWeight);
            previousTable.put(endPoint, startPoint);
          }
        }
      }
    }
    System.out.printf("weight: %s\n", weightedTable.toString());
    System.out.printf("previous: %s\n", previousTable.toString());
    
    // step3: get max delay time from weightedTable
    Integer maxWeight = 0;
    Set<Map.Entry<Integer,Integer>> setOfKeys = weightedTable.entrySet();
    for (Map.Entry<Integer,Integer> entry : setOfKeys) {
      Integer weight = entry.getValue();
      maxWeight = weight > maxWeight ? weight : maxWeight;
    }
    return maxWeight;
  }

  public void testCase() {
    Integer[][] timeInput = new Integer[][]{
      {1, 2, 9}, {1, 4, 2}, {2, 5, 1}, {4, 2, 4},
      {4, 5, 6}, {3, 2, 3}, {5, 3, 7}, {3, 1, 5}
    };
    Integer numOfNodes = 5;
    Integer start = 1;
    Integer total = this.exec(timeInput, numOfNodes, start);
    System.out.printf("%s\n", total);
  }
}