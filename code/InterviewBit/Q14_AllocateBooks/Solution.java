package Q14_AllocateBooks;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
  public int books(ArrayList<Integer> A, int B) {
    int n = A.size();
    if (B > n || B < 1) {
      return -1;
    }

    int r = 0;
    for (int pages : A) {
      r += pages;
    }

    int l = 0;
    int ans = -1;
    while (l <= r) {
      int mid = (l + r) / 2;
      if (canAllocateBooks(A, B, mid)) {
        ans = mid;
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }

    return ans;
  }

  private boolean canAllocateBooks(ArrayList<Integer> a, int b, int threshold) {
    int n = a.size();
    int k = 1, pages = 0;

    for (int i = 0; i < n; i++) {
      pages += a.get(i);
      if (pages > threshold) {
        k += 1;
        if (k > b) {
          return false;
        }
        pages = 0;
        i -= 1;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    ArrayList<Integer> a = new ArrayList<>(Arrays.asList(5, 17, 100, 11));
    System.out.println("a: " + Arrays.toString(a.toArray()));
    int b = 4;
    System.out.println("b: " + b + ", output: " + s.books(a, b));

    a = new ArrayList<>(Arrays.asList(12, 34, 67, 90));
    System.out.println("a: " + Arrays.toString(a.toArray()));
    b = 2;
    System.out.println("b: " + b + ", output: " + s.books(a, b));
  }
}
