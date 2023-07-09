public class TestQueue {
  public void main(String[] args) {

  }
}

class QueueNode {
  QueueNode next;
  int value;

  QueueNode(int value) {
    this.next = null;
    this.value = value;
  }
}

class Queue {
  QueueNode first;
  QueueNode last;
  int size;

  Queue() {
    this.first = null;
    this.last = null;
    this.size = 0;
  }

  public Boolean enqueue(int value) {
    QueueNode newNode = new QueueNode(value);
    if (this.size == 0) {
      this.first = newNode;
      this.last = newNode;
    } else {
      this.last.next = newNode;
      this.last = newNode;
    }
    this.size++;
    return true;
  }

  public QueueNode dequeue() {
    if (this.size == 0)
      return null;
    QueueNode deNode = this.first;
    if (this.size == 1) {
      this.first = null;
      this.last = null;
    } else {
      this.first = deNode.next;
    }
    deNode.next = null;
    this.size--;
    return deNode;
  }
}