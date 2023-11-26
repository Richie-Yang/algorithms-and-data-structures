import java.util.ArrayList;

public class TestQueueWithStacks {
  public static void main(String[] args) {
    QueueWithStacks qs = new QueueWithStacks();
    qs.push(1).push(2).push(3);
    System.out.printf("%s\n", qs.pop());
    qs.push(4).push(5);
    System.out.printf("%s\n", qs.pop());
    System.out.printf("%s\n", qs.pop());
    System.out.printf("%s\n", qs.pop());
    System.out.printf("%s\n", qs.pop());
    System.out.printf("%s\n", qs.pop());
    qs.printInfo();
  }
}

class QueueWithStacks {
  ArrayList<Integer> pushStack;
  ArrayList<Integer> popStack;
  QueueWithStacks() {
    this.pushStack = new ArrayList<>();
    this.popStack = new ArrayList<>();
  }

  public QueueWithStacks push(int value) {
    this.pushStack.add(value);
    return this;
  }

  public void printInfo() {
    System.out.printf("Push Stack: %s\n", this.pushStack.toString());
    System.out.printf("Pop Stack: %s\n", this.popStack.toString());
  }

  public int pop() {
    int popValue = -1;
    if (popStack.size() == 0) this.reverseStack();
    if (popStack.size() > 0) popValue = this.popStack.removeLast();
    return popValue;
  }

  private void reverseStack() {
    int startIndex = this.pushStack.size() - 1;
    for (int index = startIndex; index >= 0; index--) {
      int pushValue = this.pushStack.removeLast();
      this.popStack.add(pushValue);
    }
  }
}