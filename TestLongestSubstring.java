import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TestLongestSubstring {
  static public void main(String[] args) {
    String stringA = "abccgaabbaaddxxffac";
    LongestSubstring ls = new LongestSubstring();
    int longest1 = ls.regularExec(stringA);
    System.out.printf("logest1: %s\n", longest1);
    int longest2 = ls.optimalExec(stringA);
    System.out.printf("logest2: %s\n", longest2);
  }
}

class LongestSubstring {
  LongestSubstring() {}

  public int regularExec(String input) {
    System.out.printf("input: %s\n", input);
    int longest = 0;
    ArrayList<String> tempArray = new ArrayList<>();
    String[] strSplit = input.split(""); 
    ArrayList<String> inputArray = new ArrayList<>(Arrays.asList(strSplit));
    for (int index = 0; index < inputArray.size(); index++) {
      String currentChar = inputArray.get(index);
      if (tempArray.contains(currentChar)) tempArray = new ArrayList<>();
      tempArray.add(currentChar);
      if (tempArray.size() > longest) longest = tempArray.size();
    }
    return longest;
  }

  public int optimalExec(String input) {
    System.out.printf("input: %s\n", input);
    int longest = 0;
    int leftPointer = 0, rightPointer = 0;
    HashMap<String, Integer> tempHashMap = new HashMap<>();
    String[] strSplit = input.split(""); 
    ArrayList<String> inputArray = new ArrayList<>(Arrays.asList(strSplit));

    int currentLongest = 0;
    for (; rightPointer < inputArray.size(); rightPointer++) {
      String currentChar = inputArray.get(rightPointer);
      int previousIndex = tempHashMap.get(currentChar) == null ? -1 : tempHashMap.get(currentChar);
      tempHashMap.put(currentChar, rightPointer);
      if (previousIndex < leftPointer) {
        currentLongest++;
        continue;
      }
      leftPointer = rightPointer;
      longest = Math.max(longest, currentLongest);
      currentLongest = 1;
    }
    return longest;
  }
}