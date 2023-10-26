import java.util.ArrayList;
import java.util.Arrays;

public class TestPalindrome {
  public static void main(String[] args) {
    String input1 = "racegecar";
    Palindrome pd = new Palindrome();
    Boolean result1 = pd.isPalindrome(ExecType.FROM_OUTSIDE, input1);
    System.out.printf("result1: %s\n", result1);
    Boolean result2 = pd.isPalindrome(ExecType.FROM_CENTER, input1);
    System.out.printf("result2: %s\n", result2);
    Boolean result3 = pd.isPalindrome(ExecType.FROM_REVERSE, input1);
    System.out.printf("result3: %s\n", result3);

    String input2 = "racegdcar";
    Boolean result4 = pd.isAlmostPalindrome(input2);
    System.out.printf("result4: %s\n", result4);
  }
}

enum ExecType {
  FROM_OUTSIDE, FROM_CENTER, FROM_REVERSE
} 

class Palindrome {
  public Palindrome() {}

  public Boolean isPalindrome(ExecType execType,String inputString) {
    ExecType inputType = execType;
    switch (inputType) {
      case FROM_OUTSIDE:
        return compareFromOutside(inputString);
      case FROM_CENTER:
        return compareFromCenter(inputString);
      case FROM_REVERSE:
        return compareFromReverse(inputString);
      default:
        return false;
    }
  }

  private Boolean compareFromOutside(String inputString) {
    String[] strSplit = inputString.split(""); 
    ArrayList<String> inputArray = new ArrayList<>(Arrays.asList(strSplit));
    
    int leftP = 0, rightP = inputArray.size() - 1;
    for (; leftP < inputArray.size() / 2;) {
      String leftChar = inputArray.get(leftP);
      String rightChar = inputArray.get(rightP);
      // System.out.printf("%s %s\n", leftChar, rightChar);
      if (leftChar.hashCode() != rightChar.hashCode()) return false;
      leftP++;
      rightP--;
    }
    return true;
  }

  private Boolean compareFromCenter(String inputString) {
    String[] strSplit = inputString.split(""); 
    ArrayList<String> inputArray = new ArrayList<>(Arrays.asList(strSplit));

    int totalLength = inputArray.size();
    int leftP = (totalLength / 2) - 1, rightP = (totalLength / 2);
    if (totalLength % 2 != 0) rightP++;
    while (leftP >= 0 && rightP < inputArray.size()) {
      String leftChar = inputArray.get(leftP);
      String rightChar = inputArray.get(rightP);
      // System.out.printf("%s %s\n", leftChar, rightChar);
      if (leftChar.hashCode() != rightChar.hashCode()) return false;
      leftP--;
      rightP++;
    }
    return true;
  }

  private Boolean compareFromReverse(String inputString) {
    String[] strSplit = inputString.split(""); 
    ArrayList<String> inputArray = new ArrayList<>(Arrays.asList(strSplit));
    ArrayList<String> reverseArray = new ArrayList<>();
    for (int index = inputArray.size() - 1; index >= 0; index--) {
      String currentChar = inputArray.get(index);
      reverseArray.add(currentChar);
    }
    String reverseString = String.join("", reverseArray);
    return inputString.hashCode() == reverseString.hashCode();
  }

  public Boolean isAlmostPalindrome(String inputString) {
    String[] strSplit = inputString.split(""); 
    ArrayList<String> inputArray = new ArrayList<>(Arrays.asList(strSplit));
    
    int leftP = 0, rightP = inputArray.size() - 1;
    for (; leftP < inputArray.size() / 2;) {
      String leftChar = inputArray.get(leftP);
      String rightChar = inputArray.get(rightP);
      // System.out.printf("%s %s\n", leftChar, rightChar);
      if (leftChar.hashCode() != rightChar.hashCode()) {
        String leftTempChar = inputArray.get(leftP + 1);
        String rightTempChar = inputArray.get(rightP - 1);
        if (leftTempChar.hashCode() == rightChar.hashCode()) {
          leftP = leftP + 2;
          rightP--;
          continue;
        }
        if (rightTempChar.hashCode() == leftChar.hashCode()) {
          rightP = rightP - 2;
          leftP++;
          continue;
        }
        return false;
      };
      leftP++;
      rightP--;
    };
    return true;
  }
}
