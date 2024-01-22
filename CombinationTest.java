import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class CombinationTest {
  static public void main(String[] args) {
    Combination com = new Combination();
    com.testCaseAll();
  } 
}

class Combination {
  Combination () {
    
  }

  public ArrayList<String> exec(Hashtable<String, Integer> input, Integer target) {
    ArrayList<String> items = new ArrayList<>();
    Set<String> keys = input.keySet();
    for (String key : keys) {
      Integer count = input.get(key);
      for (Integer i = 0; i < count; i++) items.add(key);
    }
    System.out.printf("input: %s\n", items.toString());

    ArrayList<String> result = new ArrayList<>();
    for (Integer i = 0; i < items.size(); i++) {
      ArrayList<String> combine = new ArrayList<>();
      Integer loopIndex = i;
      while (combine.size() < target) {
        String item = items.get(loopIndex);
        combine.add(item);
        if (loopIndex < items.size() - 1) loopIndex++;
        else loopIndex = 0;
      }
      String converted = combine.toString();
      if (!result.contains(converted)) result.add(combine.toString());
    }
    System.out.printf("result: %s\n", result.toString());
    return result;
  }

  public void testCaseAll() {
    Hashtable<String, Integer> input1 = new Hashtable<>();
    input1.put("A", 2);
    input1.put("B", 3);
    input1.put("C", 2);
    Integer result1 = this.exec(input1, 2).size();
    System.out.printf("target: %s && result: %s\n", 6, result1);
  }
}
