import java.util.ArrayList;
import java.util.Arrays;

public class TestMinBracketsToRemove {
  static public void main(String[] args) {
    MinBracketsToRemove mb = new MinBracketsToRemove();
    String testCase1 = "a)(bc(d)";
    String testCase2 = "(ab(c)d";
    String testCase3 = "))((";

    String result1 = mb.exec(testCase1);
    System.out.printf("result1: %s\n", result1);
    String result2 = mb.exec(testCase2);
    System.out.printf("result2: %s\n", result2);
    String result3 = mb.exec(testCase3);
    System.out.printf("result3: %s\n", result3);
  }
}

class MinBracketsToRemove {
  Integer leftBracketCode = "(".hashCode();
  Integer rightBracketCode = ")".hashCode();
  
  MinBracketsToRemove() {}

  public String exec(String str) {
    ArrayList<Integer> indexArray = new ArrayList<>();
    ArrayList<String> strArray = new ArrayList<>(Arrays.asList(str.split("")));

    for (Integer index = 0; index < strArray.size(); index++) {
      String strItem = strArray.get(index);
      Integer convertCode = strItem.hashCode();

      if (convertCode == leftBracketCode) indexArray.add(index);
      Boolean isRightBracket = convertCode == rightBracketCode;
      if (isRightBracket && indexArray.size() > 0)
          indexArray.removeLast();
      if (isRightBracket && indexArray.size() == 0)
          strArray.set(index, "");
    }

    ArrayList<String> resultList = new ArrayList<>();
    for (Integer index = strArray.size() - 1; index >= 0; index--) {
      if (indexArray.contains(index)) continue;
      resultList.add(0, strArray.get(index));
    }
    return String.join("", resultList);
  }
}