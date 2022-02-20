package Q31_4Sum;

import java.util.*;

public class Solution {
  // DO NOT MODIFY THE LIST. IT IS READ ONLY
  public ArrayList<ArrayList<Integer>> fourSum(final List<Integer> A, int B) {
    TreeSet<ArrayList<Integer>> ans =
        new TreeSet<>(
            (a1, a2) -> {
              int length = Math.min(a1.size(), a2.size());
              for (int i = 0; i < length; i++) {
                if (a1.get(i) < a2.get(i)) {
                  return -1;
                }
                if (a1.get(i) > a2.get(i)) {
                  return +1;
                }
              }
              return 0;
            });

    Hashtable<Integer, ArrayList<Integer>> sum2Hashtable = new Hashtable<>();

    for (int i = 0; i < A.size(); i++) {
      for (int j = i + 1; j < A.size(); j++) {
        int sum2 = A.get(i) + A.get(j);

        ArrayList<Integer> arrayList = sum2Hashtable.get(sum2);
        if (arrayList == null) {
          arrayList = new ArrayList<>();
        }
        boolean found = false;
        for (int k = 0; k < arrayList.size(); k += 2) {
          int v = arrayList.get(k);
          if (v == i || v == j) {
            found = true;
            break;
          }
        }
        if (!found) {
          arrayList.add(i);
          arrayList.add(j);
          sum2Hashtable.put(sum2, arrayList);

          ArrayList<Integer> firstHalf = sum2Hashtable.get(B - sum2);
          if (firstHalf != null) {
            Integer[] solution = {0, 0, A.get(i), A.get(j)};
            for (int k = 0; k < firstHalf.size(); k++) {
              int index = firstHalf.get(k);
              int pos = k % 2;
              if (index == i || index == j) {
                if (pos == 0) {
                  k++;
                }
                continue;
              }
              solution[pos] = A.get(index);
              if (pos == 1) {
                ArrayList<Integer> arrayListSolution = new ArrayList<>(Arrays.asList(solution));
                arrayListSolution.sort(Integer::compareTo);
                ans.add(arrayListSolution);
              }
            }
          }
        }
      }
    }

    return new ArrayList<>(ans);
  }

  public static void main(String[] args) {
    Integer[] s = {1, 0, -1, 0, -2, 2};
    Integer[][] expected = {{-2, -1, 1, 2}, {-2, 0, 0, 2}, {-1, 0, 0, 1}};
    runTestCase(s, 0, expected);

    expected = new Integer[][] {{0, 0, 1, 2}};
    runTestCase(s, 3, expected);
  }

  private static void runTestCase(Integer[] s, int target, Integer[][] expected) {
    Solution solution = new Solution();
    System.out.println("input: " + Arrays.toString(s) + ", target: " + target);
    ArrayList<ArrayList<Integer>> result = solution.fourSum(Arrays.asList(s), target);
    System.out.println(result);
    if (result.size() != expected.length) throw new AssertionError();
    for (int i = 0; i < expected.length; i++) {
      if (!Arrays.equals(result.get(i).toArray(new Integer[0]), expected[i]))
        throw new AssertionError();
    }
  }
}
