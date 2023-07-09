import java.util.Scanner;

public class Loop2 {
  public static void main(String[] args) {
    int A = 0;
    int B = 0;
    int C = 0;
    int D = 0;
    int F = 0;

    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the grade: ");
    while (sc.hasNext()) {
      int grade = sc.nextInt();
      if (grade == -1) break;
      switch (grade/10) {
        case 9:
        case 10:
          A++;
          break;
        case 8:
          B++;
          break;
        case 7:
          C++;
          break;
        case 6:
          D++;
          break;
        default:
          F++;
          break;
      }
    }
    System.out.printf("A: %d\nB: %d\nC: %d\nD: %d\nF: %d\n", A, B, C, D, F);
    sc.close();
  }
}
