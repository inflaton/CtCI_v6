package Q13_RotatedSortedArraySearch;

import java.util.Arrays;
import java.util.List;

public class Solution { // DO NOT MODIFY THE LIST. IT IS READ ONLY
  public int search(final List<Integer> A, int B) { // now, do binary search
    int n = A.size();
    int l = 0;
    int r = n; // r is n, NOT n-1, this is important!!
    while (l < r) {
      int leftValue = A.get(l);
      if (leftValue == B) {
        return l;
      }
      int rightValue = A.get(r - 1);
      if (rightValue == B) {
        return r - 1;
      }
      int mid = (l + r) / 2;
      int midValue = A.get(mid);
      if (midValue == B) {
        return mid;
      }

      boolean goLeft = true;
      if (midValue > leftValue && midValue < rightValue) { // no kink
        if (B > midValue) {
          goLeft = false;
        }
      } else if (midValue > leftValue) { // kink at right
        if (B > midValue || B < leftValue) {
          goLeft = false;
        }
      } else { // kink at left
        if (B < rightValue && B > midValue) {
          goLeft = false;
        }
      }

      if (goLeft) {
        r = mid;
      } else {
        l = mid + 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    List<Integer> a = Arrays.asList(4, 5, 6, 7, 0, 1, 2, 3);
    System.out.println("a: " + Arrays.toString(a.toArray()));
    int b = 60;
    System.out.println("b: " + b + ", index: " + s.search(a, b));
    for (int i : a) {
      System.out.println("b: " + i + ", index: " + s.search(a, i));
    }

    a = Arrays.asList(5, 17, 100, 3);
    System.out.println("a: " + Arrays.toString(a.toArray()));
    b = 6;
    System.out.println("b: " + b + ", index: " + s.search(a, b));
    for (int i : a) {
      System.out.println("b: " + i + ", index: " + s.search(a, i));
    }

    a =
        Arrays.asList(
            122, 180, 181, 182, 183, 184, 187, 188, 189, 191, 192, 193, 194, 195, 196, 201, 202,
            203, 204, 3, 4, 5, 6, 7, 8, 9, 10, 14, 16, 17, 18, 19, 23, 26, 27, 28, 29, 32, 33, 36,
            37, 38, 39, 41, 42, 43, 45, 48, 51, 52, 53, 54, 56, 62, 63, 64, 67, 69, 72, 73, 75, 77,
            78, 79, 83, 85, 87, 90, 91, 92, 93, 96, 98, 99, 101, 102, 104, 105, 106, 107, 108, 109,
            111, 113, 115, 116, 118, 119, 120, 122, 123, 124, 126, 127, 129, 130, 135, 137, 138,
            139, 143, 144, 145, 147, 149, 152, 155, 156, 160, 162, 163, 164, 166, 168, 169, 170,
            171, 172, 173, 174, 175, 176, 177);
    System.out.println("a: " + Arrays.toString(a.toArray()));
    b = 42;
    System.out.println("b: " + b + ", index: " + s.search(a, b));
    for (int i : a) {
      System.out.println("b: " + i + ", index: " + s.search(a, i));
    }
  }
}
