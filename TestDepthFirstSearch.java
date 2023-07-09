
//      10
//   7      12
// 5   8  11
//1
// PreOrder [10, 7, 5, 1, 8, 12, 11] mid > left > right; good for export the tree structure
// PostOrder [1, 5, 8, 7, 11, 12, 10] left > right > mid; 
// InOrder [1, 5, 7, 8, 10, 11, 12] left > mid > right; good for visualize the tree with array

import java.util.ArrayList;

public class TestDepthFirstSearch {
  enum OrderType {
    PRE_ORDER, POST_ORDER, IN_ORDER
  }

  public static void main(String[] args) {
    BinarySearchTree bst = new BinarySearchTree();
    bst.insert(10).insert(7).insert(12).insert(5).insert(8).insert(11).insert(1);

    ArrayList<Integer> result1 = DepthFirstSearch(bst, OrderType.PRE_ORDER);
    System.out.printf("Pre Order: %s\n", result1.toString());

    ArrayList<Integer> result2 = DepthFirstSearch(bst, OrderType.POST_ORDER);
    System.out.printf("Post Order: %s\n", result2.toString());

    ArrayList<Integer> result3 = DepthFirstSearch(bst, OrderType.IN_ORDER);
    System.out.printf("In Order: %s\n", result3.toString());
  }

  static ArrayList<Integer> DepthFirstSearch(BinarySearchTree tree, OrderType orderType) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    OrderType order = orderType;
    switch (order) {
      case PRE_ORDER:
        recursivePreOrderDFS(tree.root, result);
        break;
      case POST_ORDER:
        recursivePostOrderDFS(tree.root, result);
        break;
      default:
      case IN_ORDER:
        recursiveInOrderDFS(tree.root, result);
        break;
    }
    return result;
  }

  static void recursivePreOrderDFS(BSTNode current, ArrayList<Integer> result) {
    result.add(current.value);
    if (current.left != null)
      recursivePreOrderDFS(current.left, result);
    if (current.right != null)
      recursivePreOrderDFS(current.right, result);
  }

  static void recursivePostOrderDFS(BSTNode current, ArrayList<Integer> result) {
    if (current.left != null)
      recursivePostOrderDFS(current.left, result);
    if (current.right != null)
      recursivePostOrderDFS(current.right, result);
    result.add(current.value);
  }

  static void recursiveInOrderDFS(BSTNode current, ArrayList<Integer> result) {
    if (current.left != null)
      recursiveInOrderDFS(current.left, result);
    result.add(current.value);
    if (current.right != null)
      recursiveInOrderDFS(current.right, result);
  }
}
