import java.util.ArrayList;

public class ArrayListTest {
  public static void main(String[] args) {
    ArrayList<String> students = new ArrayList<String>();
    students.add("Betty");
    students.add(0, "Alan");
    System.out.printf("fst: %s\n", students);

    students.add("Jane");
    students.add("Betty");
    System.out.printf("snd: %s\n", students);

    students.remove("Betty");
    System.out.printf("trd: %s\n", students);

    students.remove(1);
    System.out.printf("fourth: %s\n", students);

    if (students.contains("Jane")) System.out.println("Jane is here");
    else System.out.println("Jane is not here");
  }
}
