
public class TestLinkedList {
  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    // list.Push(0).Push(1).Push(9).printAll();
    System.out.println(list.length);
    System.out.println("---");
    list.unshift(10).unshift(1).unshift(5).Push(20);
    // [5, 1, 10, 20]

    list.insert(1, 99);
    // [5, 99, 1, 10, 20]

    // list.Remove(0);
    list.reverse();

    System.out.printf("%s %s\n", list.head.value, list.tail.value);
    System.out.println(list.length);
    list.printAsArray();
  }
}

class Node {
  int value;
  Node next;

  public Node(int value) {
    this.value = value;
    this.next = null;
  }
}

class LinkedList {
  Node head;
  Node tail;
  int length;

  public LinkedList() {
    this.head = null;
    this.tail = null;
    this.length = 0;
  }

  public LinkedList printAll() {
    Node currentNode = this.head;
    int arrayLen = this.length;
    for (int current = 0; current < arrayLen; current++) {
      System.out.println(currentNode.value);
      currentNode = currentNode.next;
    }
    return this;
  }

  public void printAsArray() {
    int[] arr = new int[this.length];
    Node currentNode = this.head;
    for (int i = 0; i < this.length; i++) {
      arr[i] = currentNode.value;
      if (currentNode.next == null)
        break;
      currentNode = currentNode.next;
    }
    System.out.println(java.util.Arrays.toString((arr)));
  }

  public LinkedList Push(int value) {
    Node newNode = new Node(value);
    if (this.length == 0)
      this.head = newNode;
    else
      this.tail.next = newNode;

    this.tail = newNode;
    length++;
    return this;
  }

  public LinkedList pop() {
    Node currentNode = this.head;
    for (int current = 0; current < this.length; current++) {
      if (current == this.length - 2) {
        this.tail = currentNode;
        currentNode.next = null;
        this.length--;
        break;
      }
      if (this.head == this.tail) {
        this.head = null;
        this.tail = null;
        this.length--;
        break;
      }
      currentNode = currentNode.next;
    }
    return this;
  }

  public LinkedList shift() {
    if (this.head == this.tail) {
      this.head = null;
      this.tail = null;
    } else {
      this.head = this.head.next;
    }
    this.length--;
    return this;
  }

  public LinkedList unshift(int value) {
    Node currentNode = new Node(value);
    if (this.length == 0) {
      this.head = currentNode;
      this.tail = currentNode;
    } else {
      currentNode.next = this.head;
      this.head = currentNode;
    }
    this.length++;
    return this;
  }

  public Node get(int index) {
    int count = 0;
    if (this.isIndexInValid(index))
      return null;
    return this.loopThroughIndex(head, index, count);
  }

  private Node loopThroughIndex(Node current, int index, int count) {
    if (index == count)
      return current;
    current = current.next;
    count++;
    return this.loopThroughIndex(current, index, count);
  }

  public Boolean set(int index, int value) {
    if (this.isIndexInValid(index))
      return false;
    Node pointNode = this.get(index);
    pointNode.value = value;
    return true;
  }

  public Boolean insert(int index, int value) {
    if (this.isIndexInValid(index))
      return false;
    if (index == 0)
      this.unshift(value);
    else if (index == this.length - 1)
      this.Push(value);
    else {
      Node newNode = new Node(value);
      Node preNode = this.get(index - 1);
      newNode.next = preNode.next;
      preNode.next = newNode;
      this.length++;
    }
    return true;
  }

  public Boolean remove(int index) {
    if (this.isIndexInValid(index))
      return false;
    if (index == 0)
      this.shift();
    else if (index == this.length - 1)
      this.pop();
    else {
      Node preNode = this.get(index - 1);
      preNode.next = preNode.next.next;
      this.length--;
    }
    return true;
  }

  // 5 -> 7 -> 8 -> 11
  // 5 <- 7 <- 8 <- 11
  //  p  ne
  // [5, 99, 1, 10, 20]
  public LinkedList reverse() {
    Node node = this.head;
    this.head = this.tail;
    this.tail = node;
    Node prev = null;
    Node next;

    for (int i = 0; i < this.length; i++) {
      next = node.next;
      node.next = prev;

      prev = node;
      node = next;
    }
    return this;
  }

  public LinkedList mnReverse(Integer mValue, Integer nValue) {
    Node currentNode = this.head;
    Node prevNode = null, nextNode = null;
    Node startNode = null, endNode = null;
    Node subHead = null, subTail = null;
    Boolean isReverse = false;
    for (Integer i = 0; i < this.length; i++) {
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

  private Boolean isIndexInValid(int index) {
    return index < 0 || index > this.length - 1;
  }
}