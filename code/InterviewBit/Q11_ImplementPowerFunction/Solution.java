package Q11_ImplementPowerFunction;

public class Solution {
  public int pow(int x, int n, int d) {
    if (x == 0) {
      return 0;
    }
    if (n == 0) {
      return 1 % d;
    }
    long result = 1;
    long base = x < 0 ? -x : x;
    int i = n;
    while (i > 0) {
      if (i % 2 == 1) {
        result = (result * base) % d;
        i--;
      } else {
        base = (base * base) % d;
        i = i / 2;
      }
    }
    return (x < 0 && n % 2 == 1) ? d - (int) result : (int) result;
  }

  public int powMath(int x, int n, int d) {
    int result = (int) (Math.pow(x, n) % d);
    return result >= 0 ? result : result + d;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.pow(0, 0, 1)); // 0
    System.out.println(s.pow(2, 0, 1)); // 0

    System.out.println(s.pow(-1, 2, 20)); // 1
    System.out.println(s.pow(-1, 3, 20)); // 19

    System.out.println(s.pow(2, 3, 3)); // 2
    System.out.println(s.pow(-2, 3, 3)); // 1

    System.out.println(s.pow(2, 11, 5)); // 3
    System.out.println(s.pow(-2, 11, 5)); // 2

    System.out.println(s.pow(2, 1001, 5)); // 3
    System.out.println(s.pow(-2, 1001, 5)); // 2

    System.out.println(s.pow(71045970, 41535484, 64735492)); // 2
  }
}
