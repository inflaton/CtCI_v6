package Q02_AddOneToNumber;

import java.util.Arrays;
import java.util.Deque;

/**
 * Problem Description
 *
 * <p>Given a non-negative number represented as an array of digits, add 1 to the number ( increment
 * the number represented by the digits ).
 *
 * <p>The digits are stored such that the most significant digit is at the head of the list.
 *
 * <p>NOTE: Certain things are intentionally left unclear in this question which you should practice
 * asking the interviewer. For example: for this problem, following are some good questions to ask :
 *
 * <p>Q : Can the input have 0's before the most significant digit. Or in other words, is 0 1 2 3 a
 * valid input? A : For the purpose of this question, YES Q : Can the output have 0's before the
 * most significant digit? Or in other words, is 0 1 2 4 a valid output? A : For the purpose of this
 * question, NO. Even if the input has zeroes before the most significant digit.
 *
 * <p>Input Format First argument is an array of digits.
 *
 * <p>Output Format Return the array of digits after adding one.
 *
 * <p>Example Input Input 1:
 *
 * <p>[1, 2, 3]
 *
 * <p>Example Output Output 1:
 *
 * <p>[1, 2, 4]
 *
 * <p>Example Explanation Explanation 1:
 *
 * <p>Given vector is [1, 2, 3]. The returned vector should be [1, 2, 4] as 123 + 1 = 124.
 */
public class Solution {
  public int[] plusOne(int[] A) {
    if (A.length == 0) {
      throw new IllegalArgumentException();
    }
    int length = A.length;
    boolean findLeadingZeros = true;
    int numOfNines = 0;
    for (int j : A) {
      if (j > 9 || j < 0) {
        throw new IllegalArgumentException();
      }
      if (findLeadingZeros) {
        if (j == 0) {
          length--;
        } else {
          findLeadingZeros = false;
        }
      }
      if (j == 9) {
        numOfNines++;
      }
    }

    int[] result = new int[numOfNines == length ? length + 1 : length];
    if (numOfNines == length) {
      result[0] = 1;
    } else {
      int increment = 1;
      for (int i = A.length - 1; length-- > 0; i--) {
        result[length] = (A[i] + increment) % 10;
        increment = (A[i] + increment) / 10;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    int[] a = {0};
    System.out.println(Arrays.toString(s.plusOne(a)));

    int[] b = {0, 3, 7, 6, 4, 0, 5, 5, 5};
    System.out.println(Arrays.toString(s.plusOne(b)));
  }
}
