import java.util.ArrayList;

//      10
//   4       15
// 2      13    17 
// [10, 4, 15, 2, 13, 17]
public class TestBreadthFirstSearch {
  public static void main(String[] args) {
    BinarySearchTree bst = new BinarySearchTree();
    bst.insert(10).insert(15).insert(13).insert(4).insert(2).insert(17).insert(9);
    breadthFirstSearch(bst);
  }

  public static ArrayList<Integer> breadthFirstSearch(BinarySearchTree tree) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    recursiveBFS(tree.root, result);
    System.out.printf("%s\n", result.toString());
    return result;
  }

  private static void recursiveBFS(BSTNode current, ArrayList<Integer> result) {
    if (result.size() == 0)
      result.add(current.value);
    Boolean isLeftNull = current.left == null;
    Boolean isRightNull = current.right == null;

    if (!isLeftNull)
      result.add(current.left.value);
    if (!isRightNull)
      result.add(current.right.value);
    if (!isLeftNull)
      recursiveBFS(current.left, result);
    if (!isRightNull)
      recursiveBFS(current.right, result);
  }
}
