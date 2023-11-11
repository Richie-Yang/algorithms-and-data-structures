import java.util.ArrayList;
import java.util.Hashtable;

public class ParenthesesTest {
  public static void main(String[] args) {
    Parentheses pt = new Parentheses();
    String testCase1 = "";
    String testCase2 = "{([])}";
    String testCase3 = "{([]";
    String testCase4 = "{[(])}";
    Boolean tcResult1 = pt.isItValid(testCase1);
    System.out.printf("testCase1: %s\n", tcResult1);
    Boolean tcResult2 = pt.isItValid(testCase2);
    System.out.printf("testCase2: %s\n", tcResult2);
    Boolean tcResult3 = pt.isItValid(testCase3);
    System.out.printf("testCase3: %s\n", tcResult3);
    Boolean tcResult4 = pt.isItValid(testCase4);
    System.out.printf("testCase4: %s\n", tcResult4);
  }
}

class Parentheses {
  ArrayList<String> leftArray;
  ArrayList<String> rightArray;
  Hashtable<String, String> mapTable;

  Parentheses() {
    String initString = "{}()[]";
    this.init(initString);
    System.out.printf("%s\n", this.leftArray);
    System.out.printf("%s\n", this.rightArray);
    System.out.printf("%s\n", this.mapTable.toString());
  }

  private void init(String initString) {
    String[] splitArray = initString.split("");
    this.leftArray = new ArrayList<>();
    this.rightArray = new ArrayList<>();
    this.mapTable = new Hashtable<>();
    for (int index = 0; index < splitArray.length; index++) {
      String str = splitArray[index];
      if ((index - 1) % 2 == 0) {
        rightArray.add(str);
        String lastStr = splitArray[index - 1];
        this.mapTable.put(str, lastStr);
        this.mapTable.put(lastStr, str);
      } else {
        leftArray.add(str);
      }
    }
  }

  public Boolean isItValid(String str) {
    ArrayList<String> stack = new ArrayList<>();
    String[] strArray = str.split("");
    for (String strItem : strArray) {
      Boolean isLeft = this.leftArray.contains(strItem);
      Boolean isRight = this.rightArray.contains(strItem);
      if (isRight) {
        String lastStr = stack.get(stack.size() - 1);
        String rightStr = this.mapTable.get(lastStr);
        int strCode = strItem.hashCode();
        if (rightStr.hashCode() != strCode) return false;
        stack.remove(stack.size() - 1);
      } 
      if (isLeft) stack.add(strItem);
    }
    if (stack.size() > 0) return false;
    return true;
  }
}
