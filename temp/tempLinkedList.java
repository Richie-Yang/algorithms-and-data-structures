package temp;

public class tempLinkedList {
  static public void main(String[] args) {
    LinkedList list = new LinkedList();
    list.push("1").push("2").push("9").push("5").push("3");

    list.printAll();
    list.mnReverse("2", "3");
    list.printAll();
  }
}

class LinkedList {
  Node head;
  Node tail;
  Integer size;

  public LinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public void printAll() {
    int max = 1;
    Node currentNode = this.head;
    for (int current = 0; current < max; current++) {
     System.out.printf("%s->", currentNode.value);
     if (currentNode.next != null) {
      currentNode = currentNode.next;
      max++;
     } 
    }
    System.out.printf("null\n");
  }

  public LinkedList push(String value) {
    Node newNode = new Node(value);
    Node currentNode = this.head;
    if (currentNode == null) {
      this.head = newNode;
      this.tail = newNode;
      this.size++;
      return this;
    }
    while (currentNode.next != null) {
      currentNode = currentNode.next;
    }
    currentNode.next = newNode;
    this.tail = newNode;
    this.size++;
    return this; 
  }

  public Node pop() {
    Node currentNode = this.head;
    Node prevNode = this.head;
    if (currentNode == null) return null;
    while (currentNode.next != null) {
      prevNode = currentNode;
      currentNode = currentNode.next;
      this.size--;
    }
    Node popNode = currentNode;
    prevNode.next = null;
    this.tail = prevNode;
    this.size--;
    return popNode; 
  }

  public LinkedList reverse() {
    Node currentNode = this.head;
    this.tail = this.head;
    Node prevNode = null;
    Node nextNode = null;
    for (Integer i = 0; i < this.size; i++) {
      nextNode = currentNode.next;
      currentNode.next = prevNode;

      prevNode = currentNode;
      currentNode = nextNode;
    }
    this.head = prevNode;
    return this;
  }

  public LinkedList mnReverse(String mValue, String nValue) {
    Node currentNode = this.head;
    Node prevNode = null, nextNode = null;
    Node startNode = null, endNode = null;
    Node subHead = null, subTail = null;
    Boolean isReverse = false;
    for (Integer i = 0; i < this.size; i++) {
      nextNode = currentNode.next;
      if (isReverse) currentNode.next = prevNode;
      if (currentNode.value == mValue) {
        isReverse = true;
        startNode = prevNode;
        subTail = currentNode;
      }
      if (currentNode.value == nValue) {
        isReverse = false;
        endNode = nextNode;
        subHead = currentNode;
      }
      prevNode = currentNode;
      currentNode = nextNode;
    }
    startNode.next = subHead;
    subTail.next = endNode;
    return this;
  }
}

class Node {
  String value;
  Node next;
  public Node(String value) {
    this.value = value;
    this.next = null;
  }
}
