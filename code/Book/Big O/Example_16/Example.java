package Example_16;

public class Example {

  public static int powersOf2(int n) {
    if (n < 1) {
      return 0;
    } else if (n == 1) {
      System.out.println(1);
      return 1;
    } else {
      int prev = powersOf2(n / 2);
      int curr = prev * 2;
      System.out.println(curr);
      return curr;
    }
  }

  public static void main(String[] args) {
    int n = 4;
    if (args.length > 0) {
      n = Integer.parseInt(args[0]);
    }
    powersOf2(n);
  }
}
