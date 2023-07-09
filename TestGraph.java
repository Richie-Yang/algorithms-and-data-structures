import java.util.ArrayList;
import java.util.Hashtable;

public class TestGraph {
  public static void main(String[] args) {
    Graph graph = new Graph();
    graph
        .addVertex("Tokyo")
        .addVertex("Taipei")
        .addVertex("London")
        .addVertex("Los Angeles")
        .addEdge("Tokyo", "Taipei")
        .addEdge("London", "Tokyo")
        .addEdge("Tokyo", "Los Angeles")
        .removeEdge("Tokyo", "London")
        .removeVertex("Taipei");

    graph.printAll();
  }
}

class Graph {
  Hashtable<String, ArrayList<String>> adjacencyList;

  Graph() {
    this.adjacencyList = new Hashtable<>();
  }

  public void printAll() {
    this.adjacencyList.forEach((String value, ArrayList<String> subList) -> {
      System.out.printf("Vertex %s: %s\n", value, subList.toString());
    });
  }

  public Graph addVertex(String value) {
    if (!adjacencyList.containsKey(value)) {
      ArrayList<String> subList = new ArrayList<>();
      adjacencyList.put(value, subList);
    }
    return this;
  }

  public Graph removeVertex(String value) {
    ArrayList<String> subList = this.adjacencyList.get(value);
    for (int i = this.adjacencyList.get(value).size() - 1; i >= 0; i--) {
      String popValue = subList.remove(i);
      removeEdge(value, popValue);
    }
    this.adjacencyList.remove(value);
    return this;
  }

  public Graph addEdge(String vertex1, String vertex2) {
    ArrayList<String> list1 = adjacencyList.get(vertex1);
    ArrayList<String> list2 = adjacencyList.get(vertex2);
    if (list1 != null && list2 != null) {
      list1.add(vertex2);
      list2.add(vertex1);
    }
    return this;
  }

  public Graph removeEdge(String vertex1, String vertex2) {
    ArrayList<String> list1 = adjacencyList.get(vertex1);
    ArrayList<String> list2 = adjacencyList.get(vertex2);
    if (list1 != null && list2 != null) {
      list1.remove(vertex2);
      list2.remove(vertex1);
    }
    return this;
  }
}