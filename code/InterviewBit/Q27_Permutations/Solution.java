package Q27_Permutations;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

  public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
    boolean[] onStack = new boolean[A.size()];
    Integer[] path = new Integer[A.size()];
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    findPermutations(A, 0, onStack, path, ans);
    return ans;
  }

  private void findPermutations(
      ArrayList<Integer> a,
      int depth,
      boolean[] onStack,
      Integer[] path,
      ArrayList<ArrayList<Integer>> ans) {
    if (depth == a.size()) {
      ArrayList<Integer> permutation = new ArrayList<>(Arrays.asList(path));
      ans.add(permutation);
      return;
    }

    for (int i = 0; i < a.size(); i++) {
      if (!onStack[i]) {
        path[depth] = a.get(i);
        onStack[i] = true;
        findPermutations(a, depth + 1, onStack, path, ans);
        onStack[i] = false;
      }
    }
  }

  public static void main(String[] args) {
    Integer[] array = {1, 2, 3};
    runTestCase(array);
  }

  private static void runTestCase(Integer[] array) {
    Solution solution = new Solution();
    System.out.println("input: " + Arrays.toString(array));
    ArrayList<ArrayList<Integer>> result = solution.permute(new ArrayList<>(Arrays.asList(array)));
    System.out.println("ans: ");
    for (ArrayList<Integer> p : result) {
      System.out.println(Arrays.toString(p.toArray()));
    }
  }
}
