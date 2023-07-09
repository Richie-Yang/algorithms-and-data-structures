import java.util.Scanner;

public class Triangle {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the side which takes two parts of triangle: ");
    int side = sc.nextInt();
    int slopeSide = (side * side) * 2;
    System.out.printf("%d, %d, %d\n", side, side, slopeSide);
    sc.close();
  }
}
