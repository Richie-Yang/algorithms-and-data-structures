import java.util.ArrayList;
import java.util.Hashtable;

public class TestShortPathAlgorithm {
  static public void main(String[] args) {
    SPFGraph gh = new SPFGraph();
    gh
        .addVertex("A")
        .addVertex("B")
        .addVertex("C")
        .addVertex("D")
        .addVertex("E");

    gh
        .addEdge("A", "B", 7)
        .addEdge("B", "C", 99)
        .addEdge("D", "C", 3)
        .addEdge("B", "D", 1)
        .addEdge("D", "E", 2)
        .addEdge("E", "C", 1);

    gh.printAll();

    gh.getSPFResult("A", "C");
  }
}

class SPFGraph {

  Hashtable<String, ArrayList<Hashtable<String, String>>> adjacencyList;

  SPFGraph() {
    this.adjacencyList = new Hashtable<>();
  }

  public void getSPFResult(String fromVertex, String toVertex) {
    Hashtable<String, Hashtable<String, String>> spfObj = initSPF();
    Hashtable<String, String> weightMap = spfObj.get("weightMap");
    Hashtable<String, String> previousMap = spfObj.get("previousMap");
    ArrayList<String> visitedList = new ArrayList<>();

    loop(fromVertex, weightMap, previousMap, visitedList);

    System.out.printf("weight: %s\n", weightMap.toString());
    System.out.printf("previous: %s\n", previousMap.toString());
    ArrayList<String> path = getShortestPath(toVertex, previousMap);
    System.out.printf("final: %s\n", path.toString());
  }

  private Hashtable<String, Hashtable<String, String>> initSPF() {
    Hashtable<String, String> weightMap = new Hashtable<>();
    Hashtable<String, String> previousMap = new Hashtable<>();

    this.adjacencyList.keySet().forEach(key -> {
      weightMap.put(key, "999999");
      previousMap.put(key, "null");
    });

    Hashtable<String, Hashtable<String, String>> result = new Hashtable<>();
    result.put("weightMap", weightMap);
    result.put("previousMap", previousMap);
    return result;
  }

  private void loop(
      String vertex,
      Hashtable<String, String> weightMap,
      Hashtable<String, String> previousMap,
      ArrayList<String> visitedList) {
    ArrayList<Hashtable<String, String>> neighbors = this.adjacencyList.get(vertex);

    PriorityHeap queue = new PriorityHeap();
    for (Hashtable<String, String> neighbor : neighbors) {
      String nodeName = neighbor.get("node");
      String nodeWeight = neighbor.get("weight");
      int parsedWeight = Integer.parseInt(nodeWeight);
      if (visitedList.contains(nodeName))
        continue;
      queue.enqueue(nodeName, parsedWeight);
    }
    PriorityNode node = queue.dequeue();

    if (node == null)
      return;

    visitedList.add(node.value);
    previousMap.put(node.value, vertex);
    int totalWeight = getTotalWeight(
        node.value, node.priority, weightMap, previousMap);
    weightMap.put(node.value, Integer.toString(totalWeight));
    loop(node.value, weightMap, previousMap, visitedList);
  }

  private int getTotalWeight(
      String vertex,
      int baseWeight,
      Hashtable<String, String> weightMap,
      Hashtable<String, String> previousMap) {
    String preVertex = previousMap.get(vertex);
    String preWeight = weightMap.get(preVertex);
    if (preWeight == null || preWeight == "999999")
      return baseWeight;
    int convertedWeight = Integer.parseInt(preWeight);
    return convertedWeight + baseWeight;
  }

  private ArrayList<String> getShortestPath(String toVertex, Hashtable<String, String> previousMap) {
    ArrayList<String> path = new ArrayList<>();
    String vertex = toVertex;
    int max = 1;
    for (int i = 0; i < max; i++) {
      if (vertex == "null")
        break;
      max++;
      path.add(0, vertex);
      vertex = previousMap.get(vertex);
    }
    return path;
  }

  public void printAll() {
    this.adjacencyList.entrySet().forEach(entry -> {
      System.out.printf("%s\n", entry.toString());
    });
  }

  public SPFGraph addVertex(String value) {
    Boolean isKeyPresent = this.adjacencyList.containsKey(value);
    if (!isKeyPresent) {
      ArrayList<Hashtable<String, String>> subList = new ArrayList<>();
      this.adjacencyList.put(value, subList);
    }
    return this;
  }

  public SPFGraph addEdge(String sourceVertex, String targetVertex, int weight) {
    Boolean isSourcePresent = this.adjacencyList.containsKey(sourceVertex);
    Boolean isTargetPresent = this.adjacencyList.containsKey(targetVertex);

    if (isSourcePresent && isTargetPresent) {
      ArrayList<Hashtable<String, String>> sourceSubList = this.adjacencyList.get(sourceVertex);
      ArrayList<Hashtable<String, String>> targetSubList = this.adjacencyList.get(targetVertex);
      this.addVertexAndWeight(sourceSubList, targetVertex, weight);
      this.addVertexAndWeight(targetSubList, sourceVertex, weight);
    }
    return this;
  }

  private void addVertexAndWeight(ArrayList<Hashtable<String, String>> subList, String addVertex, int weight) {
    boolean isChanged = false;
    String convertedWeight = Integer.toString(weight);
    for (Hashtable<String, String> table : subList) {
      String node = table.get("node");
      if (node != addVertex)
        continue;
      table.put("node", addVertex);
      table.put("weight", convertedWeight);
      isChanged = true;
      break;
    }
    if (!isChanged) {
      Hashtable<String, String> newTable = new Hashtable<>();
      newTable.put("node", addVertex);
      newTable.put("weight", convertedWeight);
      subList.add(newTable);
    }
  }

}
