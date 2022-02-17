package Q24_EvaluateExpression;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.function.BiFunction;

public class Solution {
  public int evalRPN(ArrayList<String> A) {
    Deque<String> stack = new ArrayDeque<>();

    for (String token : A) {
      BiFunction<Integer, Integer, Integer> calcOp = null;

      switch (token) {
        case "+":
          calcOp = Integer::sum;
          break;

        case "-":
          calcOp = (x, y) -> y - x;
          break;

        case "*":
          calcOp = (x, y) -> x * y;
          break;

        case "/":
          calcOp = (x, y) -> y / x;
          break;
      }

      if (calcOp == null) {
        stack.push(token);
      } else {
        int x = Integer.parseInt(stack.pop());
        int y = Integer.parseInt(stack.pop());
        int value = calcOp.apply(x, y);
        stack.push(String.valueOf(value));
      }
    }

    return Integer.parseInt(stack.pop());
  }

  public static void main(String[] args) {
    // String Input
    String[] s = {"2", "1", "+", "3", "*"};

    Solution solution = new Solution();
    int result = solution.evalRPN(new ArrayList<>(Arrays.asList(s)));
    System.out.println(result);

    String[] s2 = {"4", "13", "5", "/", "+"};
    result = solution.evalRPN(new ArrayList<>(Arrays.asList(s2)));
    System.out.println(result);

    String[] s3 = {"2", "2", "/"};
    result = solution.evalRPN(new ArrayList<>(Arrays.asList(s3)));
    System.out.println(result);
  }
}
