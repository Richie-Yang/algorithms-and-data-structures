public class TestBinarySearchTree {
  public static void main(String[] args) {
    BinarySearchTree bst = new BinarySearchTree();
    bst.insert(10).insert(19).insert(5).insert(18);
    System.out.printf("%d\n", bst.root.right.left.value);
    System.out.printf("%b\n", bst.search(5));
  }
}

class BSTNode {
  int value;
  BSTNode left;
  BSTNode right;

  BSTNode(int value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

class BinarySearchTree {
  BSTNode root;

  BinarySearchTree() {
    this.root = null;
  }

  public BinarySearchTree insert(int value) {
    BSTNode newNode = new BSTNode(value);
    if (this.root == null) {
      this.root = newNode;
      return this;
    }

    BSTNode current = this.root;
    boolean isFound = false;
    while (!isFound) {
      if (value > current.value) {
        if (current.right == null) {
          current.right = newNode;
          break;
        }
        current = current.right;
      } else if (value < current.value) {
        if (current.left == null) {
          current.left = newNode;
          break;
        }
        current = current.left;
      } else
        break;
    }
    return this;
  }

  public Boolean search(int value) {
    if (this.root == null)
      return false;

    BSTNode current = this.root;
    boolean isFound = false;
    while (!isFound) {
      if (value > current.value) {
        if (current.right == null)
          return false;
        current = current.right;
      } else if (value < current.value) {
        if (current.left == null)
          return false;
        current = current.left;
      } else
        return true;
    }
    return false;
  }
}
