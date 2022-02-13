package Q01_PickFromBothSides;

/**
 * Pick From Both Sides
 *
 * <p>https://www.interviewbit.com/problems/pick-from-both-sides/ Problem Description
 *
 * <p>Given an integer array A of size N.
 *
 * <p>You can pick B elements from either left or right end of the array A to get maximum sum.
 *
 * <p>Find and return this maximum possible sum.
 *
 * <p>NOTE: Suppose B = 4 and array A contains 10 elements then
 *
 * <p>You can pick first four elements or can pick last four elements or can pick 1 from front and 3
 * from back etc . you need to return the maximum possible sum of elements you can pick.
 *
 * <p>Problem Constraints 1 <= N <= 105
 *
 * <p>1 <= B <= N
 *
 * <p>-103 <= A[i] <= 103
 *
 * <p>Input Format First argument is an integer array A.
 *
 * <p>Second argument is an integer B.
 *
 * <p>Output Format Return an integer denoting the maximum possible sum of elements you picked.
 *
 * <p>Example Input Input 1:
 *
 * <p>A = [5, -2, 3 , 1, 2] B = 3 Input 2:
 *
 * <p>A = [1, 2] B = 1
 *
 * <p>Example Output Output 1:
 *
 * <p>8 Output 2:
 *
 * <p>2
 *
 * <p>Example Explanation Explanation 1:
 *
 * <p>Pick element 5 from front and element (1, 2) from back so we get 5 + 1 + 2 = 8 Explanation 2:
 *
 * <p>Pick element 2 from end as this is the maximum we can get
 */
public class Solution {
  public static int solve(int[] A, int B) {
    int length = A.length;
    if (B <= 0 || B > length) {
      throw new IllegalArgumentException();
    }
    int sum = 0;
    int head = length - B;
    for (int i = head; i < length; i++) {
      sum += A[i];
    }
    int max = sum;
    while (head < length) {
      sum += A[(head + B) % length] - A[head];
      if (max < sum) {
        max = sum;
      }
      head++;
    }
    return max;
  }

  public static void main(String[] args) {
    int[] A = {5, -2, 3, 1, 2};
    int B = 3;
    System.out.println(solve(A, B));

    A = new int[] {1, 2};
    B = 1;
    System.out.println(solve(A, B));
  }
}
