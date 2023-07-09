public class Division {
  public static void main(String[] args) {
    int num1 = 10;
    int num2 = 5;
    if (num2 == 0) {
      System.out.println("Cannot divide by zero!");
      System.exit(0);
    } else {
      float num3 = (float) num1 / num2;
      System.out.printf("The division is: %.2f%n", num3);
    }
  }
}
