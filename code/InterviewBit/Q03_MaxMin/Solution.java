package Q03_MaxMin;

public class Solution {
  public int solve(int[] A) {
    if (A.length == 0) {
      return 0;
    }
    int max = A[0];
    int min = A[0];
    for (int i = 1; i < A.length; i++) {
      if (A[i] > max) {
        max = A[i];
      } else if (A[i] < min) {
        min = A[i];
      }
    }
    return max + min;
  }
}
