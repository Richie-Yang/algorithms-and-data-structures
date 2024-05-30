public class HanotaTest {
  public static void main(String[] args) {
    System.out.printf("hello\n");

    Hanota ha = new Hanota();
    ha.exec(3, "A", "B", "C", 9);
  }
}

class Hanota {
  Hanota() {}

  public void exec(int disks, String charA, String charB, String charC, int call) {
    if(disks == 1) {
        System.out.printf("%s, Move disk from %s to %s\n", call, charA, charC);
        return;
    }
    else {
        this.exec(disks - 1, charA, charC, charB, 0);
        this.exec(1, charA, charB, charC, 1);
        this.exec(disks - 1, charB, charA, charC, 2);
    }
  }
}