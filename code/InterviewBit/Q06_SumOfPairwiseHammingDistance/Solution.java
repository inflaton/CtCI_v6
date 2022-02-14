package Q06_SumOfPairwiseHammingDistance;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Description
 *
 * <p>Hamming distance between two non-negative integers is defined as the number of positions at
 * which the corresponding bits are different.
 *
 * <p>Given an array A of N non-negative integers, find the sum of hamming distances of all pairs of
 * integers in the array. Return the answer modulo 1000000007.
 */
public class Solution {
  // DO NOT MODIFY THE LIST. IT IS READ ONLY
  public int hammingDistance(final List<Integer> A) {
    final int n = A.size();
    long sum = 0;
    for (int i = 0; i < 32; i++) {
      // count number of elements with i'th bit set
      long count = 0;
      int bit = 1 << i;
      for (int j = 0; j < n; j++) {
        if ((A.get(j) & bit) != 0) {
          count++;
        }
      }
      sum += count * (n - count) * 2;
    }
    return (int) (sum % 1000000007L);
  }

  public int hammingDistanceBruteForce(final List<Integer> A) {
    int sum = 0;
    for (int i = 0; i < A.size(); i++) {
      for (int j = i + 1; j < A.size(); j++) {
        sum += 2 * Integer.bitCount(A.get(i) ^ A.get(j));
      }
    }
    return sum % 1000000007;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    ArrayList<Integer> arrayList = new ArrayList<>();
    arrayList.add(1);
    System.out.println("input: " + arrayList.toString());
    System.out.println(solution.hammingDistance(arrayList));

    arrayList.clear();
    arrayList.add(2);
    arrayList.add(4);
    arrayList.add(6);
    System.out.println("input: " + arrayList.toString());
    System.out.println(solution.hammingDistance(arrayList));

    arrayList.clear();
    arrayList.add(1);
    arrayList.add(2);
    arrayList.add(5);
    arrayList.add(7);
    arrayList.add(50);
    System.out.println("input: " + arrayList.toString());
    System.out.println(solution.hammingDistance(arrayList));
  }
}
