public class TestStack {
  public void main(String[] args) {

  }
}

class StackNode {
  StackNode next;
  int value;

  StackNode(int value) {
    this.next = null;
    this.value = value;
  }
}

class Stack {
  StackNode first;
  StackNode last;
  int size;

  Stack() {
    this.first = null;
    this.last = null;
    this.size = 0;
  }

  public Boolean push(int value) {
    StackNode newNode = new StackNode(value);
    if (this.size == 0) {
      this.first = newNode;
      this.last = newNode;
    } else {
      newNode.next = this.first;
      this.first = newNode;
    }
    this.size++;
    return true;
  }

  public StackNode pop() {
    if (this.size == 0)
      return null;
    StackNode popNode = this.first;
    if (this.size == 1) {
      this.first = null;
      this.last = null;
    } else {
      this.first = popNode.next;
    }
    popNode.next = null;
    this.size--;
    return popNode;
  }
}