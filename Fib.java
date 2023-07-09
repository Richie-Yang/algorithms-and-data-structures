public class Fib {
  public static void main(String[] args) {
    int n = 40;
    int x = fib(n);
    System.out.println(x);
  }

  public static int fib(int n) {
    if (n <= 2)
      return n;
    return fib(n - 1) + fib(n - 2);
  }

  // 有能力設計演算法並實作程式碼解決以下問題：給定不重複正整數 m 種類型 m1 m2 m3
  // 每種類型對應正整數數量為 n1, n2, n3，請列出所有取法。
  // 幫我處理以上問題的演算法並實作程式碼。

  // help me to create a function which takes input int[] m, and int n for the
  // number to pick from int[] m, and it will get all the possible combination in
  // int[][]

  // int[] m = { 3, 2, 4 };
  // int n = 3; use n to gen all possible combs
  // then we check if any num for our array is greater than number from m
  // { 1, 1, 1}
  // { 1, 2, 0}
  // { 1, 0, 2}
  // { 0, 1, 2}
  // { 0, 2, 1}
  // { 0, 3, 0}
  // { 0, 0, 3}
  public static void getCombinations(int[] m, int n) {
  }
}
