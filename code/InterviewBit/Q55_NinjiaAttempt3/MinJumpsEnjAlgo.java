package Q55_NinjiaAttempt3;

import java.util.ArrayList;
import java.util.Arrays;

// https://www.enjoyalgorithms.com/blog/minimum-number-of-jumps-to-reach-end
public class MinJumpsEnjAlgo {
  public int minJumps(ArrayList<Integer> A) {
    //    return minJumpsRecursive(A);
    //    return minJumpsDP(A);
    return minJumpsSingleLoop(A);
  }

  private int minJumpsSingleLoop(ArrayList<Integer> A) {
    int n = A.size();
    if (n <= 1) {
      return 0;
    }

    int jump = 0;
    int currMaxReach = A.get(0);
    int stepsCount = currMaxReach;

    for (int i = 1; i < n - 1; i++) {
      currMaxReach = Math.max(currMaxReach, i + A.get(i));
      stepsCount -= 1;
      if (stepsCount == 0) {
        jump = jump + 1;
        stepsCount = currMaxReach - i;
      }
    }
    return jump + 1;
  }

  private int minJumpsDP(ArrayList<Integer> A) {
    int n = A.size();
    int[] jump = new int[n];
    Arrays.fill(jump, Integer.MAX_VALUE);
    jump[0] = 0;

    for (int i = 1; i < n; i = i + 1) {
      for (int j = 0; j < i; j = j + 1) {
        int steps = A.get(j);
        if (i <= j + steps && jump[j] != Integer.MAX_VALUE) {
          jump[i] = Math.min(jump[i], jump[j] + 1);
        }
      }
    }
    return jump[n - 1];
  }

  public int minJumpsRecursive(ArrayList<Integer> A) {
    return minJumps(A, 0, A.size() - 1);
  }

  private int minJumps(ArrayList<Integer> A, int start, int end) {
    if (start >= end) {
      return 0;
    }
    int minJumpCount = Integer.MAX_VALUE;
    int steps = A.get(start);
    for (int i = 1; i <= steps && i <= end; i++) {
      int jumpCount = 1 + minJumps(A, start + i, end);
      if (jumpCount < minJumpCount) {
        minJumpCount = jumpCount;
      }
    }
    return minJumpCount;
  }

  public static void main(String[] args) {
    Integer[] a = {1, 3, 5, 8, 10, 2, 6, 7, 6, 8, 9};
    runTestCase(a, 3);
  }

  private static void runTestCase(Integer[] a, int expected) {
    MinJumpsEnjAlgo solution = new MinJumpsEnjAlgo();
    ArrayList<Integer> A = new ArrayList<>(Arrays.asList(a));
    System.out.println("A:" + A);

    int result = solution.minJumps(A);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
