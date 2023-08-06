import java.util.ArrayList;
import java.util.Hashtable;

public class TestGraph {
  public static void main(String[] args) {
    Graph graph = new Graph();
    // graph
    // .addVertex("Tokyo")
    // .addVertex("Taipei")
    // .addVertex("London")
    // .addVertex("Los Angeles")
    // .addEdge("Tokyo", "Taipei")
    // .addEdge("London", "Tokyo")
    // .addEdge("Tokyo", "Los Angeles")
    // .removeEdge("Tokyo", "London")
    // .removeVertex("Taipei");

    graph
        .addVertex("A")
        .addVertex("B")
        .addVertex("C")
        .addVertex("D")
        .addVertex("E")
        .addVertex("F");
    graph
        .addEdge("A", "B")
        .addEdge("A", "C")
        .addEdge("B", "D")
        .addEdge("C", "E")
        .addEdge("D", "E")
        .addEdge("D", "F")
        .addEdge("E", "F");

    graph.printAll();

    ArrayList<String> dfs1 = graph.depthFirstSearchRecursive("A");
    System.out.printf("final recursive %s\n", dfs1.toString());

    ArrayList<String> dfs2 = graph.depthFirstSearchIterative("A");
    System.out.printf("final iterative %s\n", dfs2.toString());

    ArrayList<String> bfs = graph.breathFirstSearch("A");
    System.out.printf("final bfs %s\n", bfs.toString());
  }
}

class Graph {
  enum GraphTraversalMode {
    DFSRecursive,
    DFSIterative,
    BFS
  }

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

  public ArrayList<String> depthFirstSearchRecursive(String startPoint) {
    ArrayList<String> result = new ArrayList<>();
    helperForTraversal(result, startPoint, GraphTraversalMode.DFSRecursive);
    return result;
  }

  public ArrayList<String> depthFirstSearchIterative(String startPoint) {
    ArrayList<String> result = new ArrayList<>();
    helperForTraversal(result, startPoint, GraphTraversalMode.DFSIterative);
    return result;
  }

  public ArrayList<String> breathFirstSearch(String startPoint) {
    ArrayList<String> result = new ArrayList<>();
    helperForTraversal(result, startPoint, GraphTraversalMode.BFS);
    return result;
  }

  private void helperForTraversal(
      ArrayList<String> result,
      String currentVertex,
      GraphTraversalMode mode) {

    result.add(currentVertex);
    ArrayList<String> subList = this.adjacencyList.get(currentVertex);
    if (subList == null || subList.size() == 0)
      return;

    GraphTraversalMode selectedMode = mode;
    switch (selectedMode) {
      case DFSRecursive:
        for (String childVertex : subList) {
          if (result.contains(childVertex))
            continue;
          helperForTraversal(result, childVertex, selectedMode);
        }
        break;
      case DFSIterative:
        for (int i = subList.size() - 1; i >= 0; i--) {
          String current = subList.get(i);
          if (result.contains(current))
            continue;
          helperForTraversal(result, current, selectedMode);
        }
        break;
      case BFS:
        ArrayList<String> queue = new ArrayList<>();
        queue.add(currentVertex);
        for (int i = 0; i < queue.size();) {
          String vertex = queue.remove(0);
          ArrayList<String> list = this.adjacencyList.get(vertex);
          list.forEach((String value) -> {
            if (result.contains(value))
              return;
            queue.add(value);
            result.add(value);
          });
        }
        break;
      default:
        break;
    }
  }
}