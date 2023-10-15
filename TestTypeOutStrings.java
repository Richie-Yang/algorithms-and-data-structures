import java.util.ArrayList;
import java.util.Arrays;

// ab#z -->  #z --> ab
// agc##z --> #z --> agc#
public class TestTypeOutStrings {
  public static void main(String[] args) {
    String stringA = "gab#z";
    String stringB = "#gacg#z";
    TypeOutStrings tos = new TypeOutStrings();
    Boolean result1 = tos.betterExec(stringA, stringB);
    System.out.printf("1: %s and %s are equal: %b\n", stringA, stringB, result1);
    Boolean result2 = tos.optimalExec(stringA, stringB);
    System.out.printf("2: %s and %s are equal: %b\n", stringA, stringB, result2);
  }
}

class TypeOutStrings {
  TypeOutStrings() {}

  public Boolean regularExec(String stringA, String stringB) {
    ArrayList<String> arrayA = new ArrayList<String>(Arrays.asList(stringA.split("")));
    ArrayList<String> arrayB = new ArrayList<String>(Arrays.asList(stringB.split("")));
    ArrayList<String> resultA = getFinalStrings(arrayA);
    ArrayList<String> resultB = getFinalStrings(arrayB);
    System.out.printf("result A: %s\n", resultA.toString());
    System.out.printf("result B: %s\n", resultB.toString());
    return resultA.hashCode() == resultB.hashCode();
  }

  public Boolean betterExec(String stringA, String stringB) {
    int hashCountA = 0, hashCountB = 0;
    String resultA = "", resultB = "";

    int maxLength = stringA.length() > stringB.length() ? stringA.length() : stringB.length();
    if (stringA.length() < maxLength) stringA = "_".repeat(maxLength - stringA.length()) + stringA;
    if (stringB.length() < maxLength) stringB = "_".repeat(maxLength - stringB.length()) + stringB;
    System.out.printf("stringA: %s\n", stringA);
    System.out.printf("stringB: %s\n", stringB);

    for (int index = maxLength - 1; index >= 0; index--) {
      String tempA = getNewString(stringA, index, hashCountA, resultA);
      System.out.printf("temp A: %s\n", tempA);
      resultA = tempA.split(":")[0];
      hashCountA = Integer.parseInt(tempA.split(":")[1]);

      String tempB = getNewString(stringB, index, hashCountB, resultB);
      System.out.printf("temp B: %s\n", tempB);
      resultB = tempB.split(":")[0];
      hashCountB = Integer.parseInt(tempB.split(":")[1]);
    }
    System.out.printf("A: %s\n", resultA);
    System.out.printf("B: %s\n", resultB);
    return resultA.hashCode() == resultB.hashCode();
  }

  public Boolean optimalExec(String stringA, String stringB) {
    int HASH_SYMBOL = "#".hashCode();
    int pointA = stringA.length() - 1, pointB = stringB.length() - 1;
    while (pointA >= 0 || pointB >= 0) {
      int charA = pointA >= 0 ? String.valueOf(stringA.charAt(pointA)).hashCode() : "PA".hashCode();
      int charB = pointB >= 0 ? String.valueOf(stringB.charAt(pointB)).hashCode(): "PB".hashCode();
      if (charA == HASH_SYMBOL) pointA = backCount(stringA, pointA);
      if (charB == HASH_SYMBOL) pointB = backCount(stringB, pointB);
      if (charA != HASH_SYMBOL && charB != HASH_SYMBOL) {
        if (charA != charB) return false;
        pointA--;
        pointB--; 
      }
    }
    return true;
  }

  private ArrayList<String> getFinalStrings(ArrayList<String> array) {
    String HASH_SYMBOL = "#";
    int hashCount = 0;
    ArrayList<String> result = new ArrayList<>();
    for (int index = array.size() - 1; index >= 0; index--) {
      String pickedChar = array.get(index);
      if (pickedChar.contains(HASH_SYMBOL)) {
        hashCount++;
        continue;
      } else if (hashCount > 0 && !pickedChar.contains(HASH_SYMBOL)) {
        hashCount--;
        continue;
      }
      result.add(pickedChar);
    }
    return result;
  }

  private String getNewString(String input, int index, int hashCount, String output) {
    String HASH_SYMBOL = "#", UNDERSCORE_SYMBOL = "_";
    Boolean isAppended = true;
    String newChar = input.substring(index, index + 1);

    if (newChar.contains(HASH_SYMBOL)) {
      hashCount++;
      isAppended = false;
    } else if (hashCount > 0 && !newChar.contains(HASH_SYMBOL)) {
      hashCount--;
      isAppended = false;
    } else if (newChar.contains(UNDERSCORE_SYMBOL)) {
      isAppended = false;
    }

    if (isAppended) output = output + newChar;
    return output = output + ":" + hashCount;
  }

  private int backCount(String stringTarget, int targetPoint) {
    int HASH_SYMBOL = "#".hashCode();
    int backCount = 2;
    while (backCount > 0) {
      backCount--;
      targetPoint--;
      int charTarget = targetPoint >= 0 ? String.valueOf(stringTarget.charAt(targetPoint)).hashCode() : (int)Math.random();
      if (charTarget == HASH_SYMBOL) backCount += 2;      
    }
    return targetPoint;
  }
}