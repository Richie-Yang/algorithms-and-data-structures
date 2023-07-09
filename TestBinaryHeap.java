import java.util.ArrayList;

public class TestBinaryHeap {
  public static void main(String[] args) {
    BinaryHeap heap = new BinaryHeap();
    heap
        .insert(30)
        .insert(17)
        .insert(10)
        .insert(52)
        .insert(100)
        .insert(27);
    System.out.printf("---\n");
    System.out.printf("start\n");
    heap.printAll();
    heap.extractMax();
    System.out.printf("end\n");
    heap.printAll();

    PriorityHeap pHeap = new PriorityHeap();
    pHeap
        .enqueue("fever", 10)
        .enqueue("cough", 13)
        .enqueue("severe injure", 5)
        .enqueue("broken leg", 7)
        .enqueue("cold", 12)
        .enqueue("mental issue", 6)
        .dequeue();
    System.out.printf("---\n");
    pHeap.printAll();
  }
}

class PriorityNode {
  String value;
  int priority;

  PriorityNode(String value, int priority) {
    this.value = value;
    this.priority = priority;
  }
}

class PriorityHeap {
  ArrayList<PriorityNode> values;

  PriorityHeap() {
    values = new ArrayList<PriorityNode>();
  }

  public void printAll() {
    int length = this.values.size();
    ArrayList<Integer> priorityArray = new ArrayList<>();
    ArrayList<String> valueArray = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      priorityArray.add(this.values.get(i).priority);
      valueArray.add(this.values.get(i).value);
      // System.out.printf("%s\n", this.values.get(i).value);
    }
    System.out.printf("%s\n", priorityArray.toString());
    System.out.printf("%s\n", valueArray.toString());
  }

  public PriorityHeap enqueue(String value, int priority) {
    PriorityNode newNode = new PriorityNode(value, priority);
    values.add(newNode);
    int insertIndex = values.size() - 1;
    bubbleUp(insertIndex, newNode);
    return this;
  }

  public PriorityNode dequeue() {
    int rootIndex = 0;
    int targetIndex = this.values.size() - 1;
    if (rootIndex == this.values.size() - 1)
      return null;

    swap(rootIndex, targetIndex);
    PriorityNode rootNode = this.values.get(rootIndex);
    PriorityNode result = this.values.remove(targetIndex);
    sinkDown(rootIndex, rootNode);
    return result;
  }

  private void sinkDown(int currentIndex, PriorityNode currentNode) {
    int rightChildIndex = (currentIndex * 2) + 2;
    int leftChildIndex = (currentIndex * 2) + 1;
    int length = this.values.size();

    PriorityNode rightChildNode = null, leftChildNode = null;
    if (rightChildIndex < length)
      rightChildNode = this.values.get(rightChildIndex);
    if (leftChildIndex < length)
      leftChildNode = this.values.get(leftChildIndex);
    if (leftChildNode == null && rightChildNode == null)
      return;

    System.out.printf("current %d %d\n", currentIndex, currentNode.priority);
    System.out.printf("right %d %d\n", rightChildIndex, rightChildNode.priority);
    System.out.printf("left %d %d\n", leftChildIndex, leftChildNode.priority);

    int overrideIndex = -1;
    PriorityNode overrideNode = null;
    if (currentNode.priority > leftChildNode.priority && currentNode.priority > rightChildNode.priority) {
      if (rightChildNode.priority > leftChildNode.priority)
        overrideIndex = rightChildIndex;
      else
        overrideIndex = leftChildIndex;

    } else if (currentNode.priority > leftChildNode.priority) {
      overrideIndex = leftChildIndex;
    } else if (currentNode.priority > rightChildNode.priority) {
      overrideIndex = rightChildIndex;
    }

    if (overrideIndex == -1)
      return;

    swap(currentIndex, overrideIndex);
    overrideNode = this.values.get(overrideIndex);
    sinkDown(overrideIndex, overrideNode);
  }

  private void bubbleUp(int currentIndex, PriorityNode currentNode) {
    int parentIndex = (currentIndex - 1) / 2;
    System.out.printf("current %d %d\n", currentIndex, currentNode.priority);
    PriorityNode parentNode = this.values.get(parentIndex);
    System.out.printf("parent %d %d\n", parentIndex, currentNode.priority);
    if (currentNode.priority >= parentNode.priority)
      return;
    swap(currentIndex, parentIndex);
    currentIndex = parentIndex;
    bubbleUp(currentIndex, currentNode);
  }

  private void swap(int sourceIndex, int targetIndex) {
    PriorityNode sourceNode = this.values.get(sourceIndex);
    PriorityNode targetNode = this.values.get(targetIndex);
    System.out.printf("%s\n", this.values.toString());

    this.values.set(targetIndex, sourceNode);
    this.values.set(sourceIndex, targetNode);
    this.printAll();
  }
}

class BinaryHeap {
  ArrayList<Integer> values;

  BinaryHeap() {
    values = new ArrayList<Integer>();
  }

  public void printAll() {
    System.out.printf("%s\n", this.values.toString());
  }

  public BinaryHeap insert(int value) {
    values.add(value);
    int insertIndex = values.size() - 1;
    bubbleUp(insertIndex, value);
    return this;
  }

  public Integer extractMax() {
    int rootIndex = 0;
    int targetIndex = this.values.size() - 1;
    if (rootIndex == this.values.size() - 1)
      return null;

    swap(rootIndex, targetIndex);
    int rootValue = this.values.get(rootIndex);
    int result = this.values.remove(targetIndex);
    sinkDown(rootIndex, rootValue);
    return result;
  }

  private void sinkDown(int currentIndex, int currentValue) {
    int rightChildIndex = (currentIndex * 2) + 2;
    int leftChildIndex = (currentIndex * 2) + 1;
    int length = this.values.size();

    int rightChildValue = -1, leftChildValue = -1;
    if (rightChildIndex < length)
      rightChildValue = this.values.get(rightChildIndex);
    if (leftChildIndex < length)
      leftChildValue = this.values.get(leftChildIndex);
    if (leftChildValue == -1 && rightChildValue == -1)
      return;

    System.out.printf("current %d %d\n", currentIndex, currentValue);
    System.out.printf("right %d %d\n", rightChildIndex, rightChildValue);
    System.out.printf("left %d %d\n", leftChildIndex, leftChildValue);

    int overrideIndex = -1, overrideValue = -1;
    if (currentValue < leftChildValue && currentValue < rightChildValue) {
      if (rightChildValue > leftChildValue)
        overrideIndex = rightChildIndex;
      else
        overrideIndex = leftChildIndex;

    } else if (currentValue < leftChildValue) {
      overrideIndex = leftChildIndex;
    } else if (currentValue < rightChildValue) {
      overrideIndex = rightChildIndex;
    }

    if (overrideIndex == -1)
      return;

    swap(currentIndex, overrideIndex);
    overrideValue = this.values.get(overrideIndex);
    sinkDown(overrideIndex, overrideValue);
  }

  private void bubbleUp(int currentIndex, int currentValue) {
    int parentIndex = (currentIndex - 1) / 2;
    System.out.printf("current %d %d\n", currentIndex, currentValue);
    int parentValue = this.values.get(parentIndex);
    System.out.printf("parent %d %d\n", parentIndex, parentValue);
    if (currentValue <= parentValue)
      return;
    swap(currentIndex, parentIndex);
    currentIndex = parentIndex;
    bubbleUp(currentIndex, currentValue);
  }

  private void swap(int sourceIndex, int targetIndex) {
    int sourceValue = this.values.get(sourceIndex);
    int targetValue = this.values.get(targetIndex);
    System.out.printf("%s\n", this.values.toString());

    this.values.set(targetIndex, sourceValue);
    this.values.set(sourceIndex, targetValue);
    this.printAll();
  }
}