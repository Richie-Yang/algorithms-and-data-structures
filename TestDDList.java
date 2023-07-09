public class TestDDList {
  public static void main(String[] args) {
    DoubleLinkList list = new DoubleLinkList();
    list.push(10).push(5);
    System.out.printf("%d\n", list.head.next.prev.value);
  }
}

class DDLNode {
  int value;
  DDLNode next;
  DDLNode prev;

  public DDLNode(int value) {
    this.value = value;
    this.next = null;
    this.prev = null;
  }
}

class DoubleLinkList {
  DDLNode head;
  DDLNode tail;
  int length;

  public DoubleLinkList() {
    this.head = null;
    this.tail = null;
    this.length = 0;
  }

  public DoubleLinkList push(int value) {
    DDLNode newNode = new DDLNode(value);
    if (this.length == 0) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      newNode.prev = this.tail;
      this.tail.next = newNode;
      this.tail = newNode;
    }
    this.length++;
    return this;
  }

  public DoubleLinkList pop() {
    if (this.length == 0)
      return this;
    DDLNode popNode = this.tail;
    this.tail = popNode.prev;
    this.tail.next = null;
    popNode.prev = null;
    this.length--;
    return this;
  }

  public DoubleLinkList unshift(int value) {
    DDLNode newNode = new DDLNode(value);
    if (length == 0) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      newNode.next = this.head;
      this.head.prev = newNode;
      this.head = newNode;
    }
    this.length++;
    return this;
  }

  public DoubleLinkList shift() {
    if (this.length == 0)
      return this;
    DDLNode unshiftNode = this.head;
    this.head = unshiftNode.next;
    this.head.prev = null;
    unshiftNode.next = null;
    this.length--;
    return this;
  }

  public DDLNode get(int index) {
    if (index < 0 || index >= this.length)
      return null;
    int mid = this.length / 2;

    DDLNode current = null;
    if (index <= mid) {
      current = this.head;
      for (int i = 0; i < this.length; i++)
        if (i == index)
          break;
    } else {
      current = this.tail;
      for (int i = this.length - 1; i >= 0; i--)
        if (i == index)
          break;
    }
    return current;
  }

  public Boolean set(int index, int value) {
    DDLNode foundNode = this.get(index);
    if (foundNode == null)
      return false;
    foundNode.value = value;
    return true;
  }

  public Boolean insert(int index, int value) {
    if (index < 0 || index >= this.length)
      return false;
    if (index == 0) {
      this.unshift(value);
      return true;
    }
    if (index == this.length - 1) {
      this.push(value);
      return true;
    }
    DDLNode newNode = new DDLNode(value);
    DDLNode foundNode = this.get(index);
    DDLNode prevNode = foundNode.prev;
    foundNode.prev = newNode;
    newNode.next = foundNode;
    newNode.prev = prevNode;
    prevNode.next = newNode;
    this.length++;
    return true;
  }

  public Boolean remove(int index) {
    if (index < 0 || index >= this.length)
      return false;
    if (index == 0) {
      this.shift();
      return true;
    }
    if (index == this.length - 1) {
      this.pop();
      return true;
    }
    DDLNode foundNode = this.get(index);
    DDLNode prevNode = foundNode.prev;
    DDLNode nextNode = foundNode.next;
    prevNode.next = nextNode;
    nextNode.prev = prevNode;
    this.length--;
    return true;
  }
}