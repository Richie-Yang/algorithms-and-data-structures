import java.util.ArrayList;
import java.util.HashMap;

public class TestMonarchy {
  static public void main (String[] args) {
    Kingdom k = new Kingdom("Anna");
    k.birth("John", "Anna");
    k.birth("koa", "John");
    k.birth("Luna", "John");
    k.birth("Leo", "Anna");
    k.death("John");
    k.getOrderOfSuccession();
  }
}

interface Monarchy {
  void birth(String child, String parent);
  void death(String name);
  ArrayList<String> getOrderOfSuccession();
}

class Kingdom implements Monarchy {
  String head;
  HashMap<String, ArrayList<String>> records;
  ArrayList<String> deathList;

  Kingdom(String name) {
    records = new HashMap<>();
    deathList = new ArrayList<>();
    head = name;
    initChildList(name);
  }

  private void initChildList(String name) {
    ArrayList<String> childList = new ArrayList<>();
    records.put(name, childList);
  }

  public void birth(String child, String parent) {
    if (!records.containsKey(parent)) {
      System.out.printf("%s does not exist\n", parent);
      return;
    }
    ArrayList<String> list = records.get(parent);
    if (list.contains(child)) {
      System.out.printf("%s is duplicated\n", child);
      return;
    }
    System.out.printf("%s is born\n", child);
    list.add(child);
    initChildList(child);
  }

  public void death(String name) {
    System.out.printf("%s is dead\n", name);
    deathList.add(name);
  }

  public ArrayList<String> getOrderOfSuccession() {
    ArrayList<String> orderList = new ArrayList<>();
    traverse(head, orderList);
    System.out.printf("%s\n", orderList.toString());
    return orderList;
  }

  private void traverse(String parent, ArrayList<String> orderList) {
    if (parent == null) return;
    if (!deathList.contains(parent)) orderList.add(parent);
    ArrayList<String> childList = this.records.get(parent);
    for (String child : childList) traverse(child, orderList);
  }
}