public class Method {
  public static void main(String[] args) {
    int num1 = 10;
    int num2 = 5;
    int num3 = 7;
    myPrint();
    System.out.println(maxNum(num1, num2, num3));
  }

  public static void myPrint() {
    System.out.print("The maximum number is: ");
  }

  public static int maxNum(int a, int b, int c) {
    if(a > b && a > c) return a;
    if(a > b && a < c) return c;
    if(b > c) return b;
    return c;
  }
}
