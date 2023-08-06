import java.util.HashMap;

public class TestFibSequence {
  public static void main(String[] args) {
    FibSequence f = new FibSequence();
    Long result = f.exec1(1000);
    System.out.printf("%s\n", result.toString());
  }
}

class FibSequence {
  HashMap<Integer, Long> temp;

  FibSequence() {
    this.init();
  }

  private void init() {
    temp = new HashMap<>();
  }

  public Long exec1(Integer n) {
    if (n <= 2)
      return n.longValue();

    if (this.temp.containsKey(n))
      return this.temp.get(n);

    Long result = exec1(n - 2) + exec1(n - 1);
    this.temp.put(n, result);
    return result;
  }

  public Integer exec2(int n) {
    if (n <= 2)
      return 1;
    return exec2(n - 2) + exec2(n - 1);
  }
}
