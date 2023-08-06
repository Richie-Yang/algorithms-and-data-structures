import java.util.ArrayList;

public class TestHashTable {
  public static void main(String[] args) {
    System.out.println(123);
    HashTable ht = new HashTable();
    ht.set("Hello", "ok");
    ht.set("Hello2", "ok2");
    ht.set("Hello3", "ok2");
    String result1 = ht.get("123");
    String result2 = ht.get("Hello");
    System.out.printf("%s %s\n", result1, result2);

    ArrayList<String> keys = ht.keys();
    System.out.printf("keys: %s\n", keys);
    ArrayList<String> values = ht.values();
    System.out.printf("values: %s\n", values);
  }
}

// [[], [], [["pink", 123], ["black", 456]]]
class HashTable {
  private int WEIRD_PRIME = 31;
  private int SIZE = 10;
  ArrayList<ArrayList<String[]>> table;

  enum ReturnOptions {
    KEYS, VALUES
  }

  HashTable() {
    this.table = new ArrayList<>();
    for (int i = 0; i < this.SIZE; i++) {
      ArrayList<String[]> newTableRows = new ArrayList<>();
      this.table.add(newTableRows);
    }
  }

  public Boolean set(String key, String value) {
    int index = this.hash(key);
    ArrayList<String[]> tableRows = this.table.get(index);
    Boolean isChanged = false;
    for (String[] row : tableRows) {
      String rowKey = row[0];
      if (rowKey == key) {
        row[1] = value;
        isChanged = true;
        break;
      }
    }
    if (!isChanged) {
      String[] newRow = { key, value };
      tableRows.add(newRow);
    }
    return true;
  }

  public String get(String key) {
    int index = this.hash(key);
    ArrayList<String[]> tableRows = this.table.get(index);
    for (String[] row : tableRows) {
      String rowKey = row[0];
      if (rowKey == key)
        return row[1];
    }
    return null;
  }

  public ArrayList<String> keys() {
    return this.loopThrough(ReturnOptions.KEYS);
  }

  public ArrayList<String> values() {
    return this.loopThrough(ReturnOptions.VALUES);
  }

  private ArrayList<String> loopThrough(ReturnOptions returnOptions) {
    ReturnOptions options = returnOptions;
    ArrayList<String> result = new ArrayList<>();
    this.table.forEach(rows -> {
      rows.forEach(row -> {
        String target = null;
        switch (options) {
          case KEYS:
            target = row[0];
            break;
          case VALUES:
          default:
            if (!result.contains(row[1]))
              target = row[1];
            break;
        }
        if (target != null)
          result.add(target);
      });
    });
    return result;
  }

  private int hash(String key) {
    int total = 0;
    for (int i = 0; i < Math.min(key.length(), 100); i++) {
      char keyChar = key.charAt(i);
      int ascii = (int) keyChar;
      total = (total * WEIRD_PRIME + ascii) % this.SIZE;
    }
    return total;
  }
}