package Q17_MinimumParantheses;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

  public int solve(String A) {
    int ans = 0;
    Deque<Character> stack = new ArrayDeque<>();
    for (int i = 0; i < A.length(); i++) {
      char ch = A.charAt(i);
      if (ch == '(') {
        stack.push(ch);
      } else {
        if (stack.size() > 0) {
          stack.pop();
        } else {
          ans++;
        }
      }
    }
    return ans + stack.size();
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    String a = "())";
    System.out.println("a: " + a);
    int r = s.solve(a);
    System.out.println("r: " + r);
    if (r != 1) {
      throw new AssertionError();
    }

    a = "(((";
    System.out.println("a: " + a);
    r = s.solve(a);
    System.out.println("r: " + r);
    if (r != 3) {
      throw new AssertionError();
    }
  }
}
