import java.util.HashMap;
import java.util.Map;

public class TestTries {
  static public void main (String[] args) {
    MyTries m = new MyTries();
    m.insert("apple");
    m.search("dog");
    m.insert("dog");
    m.search("dog");
    m.startsWith("app");
    m.search("app");
    m.insert("app");
    m.search("app");
  }
}

interface Tries {
  void insert(String word);
  Boolean search(String word);
  Boolean startsWith(String prefix);
}

class MyTries implements Tries {
  HashMap<String, String> records;

  MyTries() {
    records = new HashMap<>();
  }

  public void insert(String name) {
    System.out.printf("insert: %s\n", name);
    records.put(name, "");
  }

  public Boolean search(String name) {
    Boolean isKeyContained = records.containsKey(name);
    System.out.printf("search: %s => %s\n", name, isKeyContained);
    return isKeyContained;
  }

  public Boolean startsWith(String name) {
    Boolean isStartsWith = false;
    for (
      Map.Entry<String, String> 
        entry : records.entrySet()
      ) {
      String key = entry.getKey();
      if (name.length() > key.length())
        isStartsWith = name.startsWith(key);
      else isStartsWith = key.startsWith(name);
      if (isStartsWith == true) {
        System.out.printf("startsWith: %s => %s\n", name, isStartsWith);
        return true;
      }
    }
    System.out.printf("startsWith: %s => %s", name, isStartsWith);
    return false;
  }

}