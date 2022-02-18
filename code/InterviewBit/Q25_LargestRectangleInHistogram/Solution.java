package Q25_LargestRectangleInHistogram;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
  public int largestRectangleArea(ArrayList<Integer> A) {
    return largestRectangleArea(A, 0, A.size() - 1);
  }

  int largestRectangleArea(ArrayList<Integer> A, int start, int end) {
    if (end < start) {
      return 0;
    }

    int min = A.get(start);
    int minIndex = start;

    for (int i = start + 1; i <= end; i++) {
      int item = A.get(i);
      if (item < min) {
        min = item;
        minIndex = i;
      }
    }

    int area = min * (end - start + 1);

    int leftArea = largestRectangleArea(A, start, minIndex - 1);
    area = Math.max(area, leftArea);

    int rightArea = largestRectangleArea(A, minIndex + 1, end);
    area = Math.max(area, rightArea);

    return area;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    Integer[] s = {2};
    int result = solution.largestRectangleArea(new ArrayList<>(Arrays.asList(s)));
    System.out.println(result);
    if (result != 2) throw new AssertionError();

    Integer[] s2 = {2, 1, 5, 6, 2, 3};
    result = solution.largestRectangleArea(new ArrayList<>(Arrays.asList(s2)));
    System.out.println(result);
    if (result != 10) throw new AssertionError();
  }
}
