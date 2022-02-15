package Q10_SearchForRange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
  // DO NOT MODIFY THE LIST. IT IS READ ONLY
  public ArrayList<Integer> searchRange(final List<Integer> A, int B) {
    // binary search to determine index at which we first find B
    int lo = 0;
    int hi = A.size();
    int min = -1;
    int max = -1;
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      int value = A.get(mid);
      if (B == value) {
        min = mid;
        max = mid;
        break;
      } else if (B > A.get(mid)) {
        lo = mid + 1;
      } else {
        hi = mid;
      }
    }

    ArrayList<Integer> result = new ArrayList<>();
    if (min >= 0) {
      while (min > 0) {
        if (A.get(min - 1) != B) {
          break;
        }
        min--;
      }

      while (max < A.size() - 1) {
        if (A.get(max + 1) != B) {
          break;
        }
        max++;
      }
    }
    result.add(min);
    result.add(max);
    return result;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    List<Integer> inputs = Arrays.asList(5, 7, 7, 8, 8, 10);
    System.out.println("inputs: " + inputs);
    System.out.println("result: " + s.searchRange(inputs, 8));

    inputs = Arrays.asList(5, 17, 100, 111);
    System.out.println("inputs: " + inputs);
    System.out.println("result: " + s.searchRange(inputs, 3));

    inputs = Arrays.asList(3, 3, 5, 7, 7, 10, 10, 10, 10);
    System.out.println("inputs: " + inputs);
    System.out.println("result: " + s.searchRange(inputs, 3));
    System.out.println("result: " + s.searchRange(inputs, 10));
  }
}
