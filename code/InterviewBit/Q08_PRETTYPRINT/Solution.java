package Q08_PRETTYPRINT;

import java.util.ArrayList;

public class Solution {
  public ArrayList<ArrayList<Integer>> prettyPrint(int A) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    final int n = 2 * A - 1;
    for (int i = 0; i < n; i++) {
      ArrayList<Integer> arrayList = new ArrayList<>();
      result.add(arrayList);
      for (int j = 0; j < n; j++) {
        int distRow = Math.min(i, n - i - 1);
        int distCol = Math.min(j, n - j - 1);
        int value = A - Math.min(distCol, distRow);
        arrayList.add(value);
      }
    }
    return result;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    ArrayList<ArrayList<Integer>> result = s.prettyPrint(4);
    for (ArrayList<Integer> arrayList : result) {
      for (int i : arrayList) {
        System.out.print(i);
        System.out.print("\t");
      }
      System.out.println();
    }

    result = s.prettyPrint(3);
    for (ArrayList<Integer> arrayList : result) {
      for (int i : arrayList) {
        System.out.print(i);
        System.out.print("\t");
      }
      System.out.println();
    }
  }
}
