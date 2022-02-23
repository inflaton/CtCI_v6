package Q40_DisjointIntervals;

import java.util.*;

public class Solution {

  private static class Interval implements Comparable<Interval> {
    int start;
    int end;

    Interval(ArrayList<Integer> o1) {
      start = Math.min(o1.get(1), o1.get(0));
      end = Math.max(o1.get(1), o1.get(0));
    }

    @Override
    public int compareTo(Interval o2) {
      int r = Integer.compare(end, o2.end);
      if (r == 0) {
        r = Integer.compare(start, o2.start);
      }
      return r;
    }
  }

  // Runtime: 2575 ms
  public int solve(ArrayList<ArrayList<Integer>> A) {
    PriorityQueue<Interval> pq = new PriorityQueue<>();
    for (ArrayList<Integer> i : A) {
      pq.add(new Interval(i));
    }

    int endTime = Integer.MIN_VALUE;
    int count = 0;

    while (!pq.isEmpty()) {
      Interval interval = pq.remove();
      if (endTime < interval.start) {
        count++;
        endTime = interval.end;
      }
    }

    return count;
  }

  // Runtime: 2871 ms
  public int solve2(ArrayList<ArrayList<Integer>> A) {
    Collections.sort(
        A,
        new Comparator<ArrayList<Integer>>() {
          @Override
          public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
            int r = Integer.compare(o1.get(1), o2.get(1));
            if (r == 0) {
              r = Integer.compare(o1.get(0), o2.get(0));
            }
            return r;
          }
        });

    int endTime = Integer.MIN_VALUE;
    int count = 0;
    for (ArrayList<Integer> interval : A) {
      if (endTime < interval.get(0)) {
        count++;
        endTime = interval.get(1);
      }
    }
    return count;
  }

  // Runtime: 2871 ms
  public int solve3(ArrayList<ArrayList<Integer>> A) {
    A.sort(
        (o1, o2) -> {
          int r = Integer.compare(o1.get(1), o2.get(1));
          if (r == 0) {
            r = Integer.compare(o1.get(0), o2.get(0));
          }
          return r;
        });

    int endTime = Integer.MIN_VALUE;
    int count = 0;
    for (ArrayList<Integer> interval : A) {
      if (endTime < interval.get(0)) {
        count++;
        endTime = interval.get(1);
      }
    }
    return count;
  }

  // Runtime: 3135 ms
  public int solve4(ArrayList<ArrayList<Integer>> A) {
    int n = A.size();
    Interval[] intervals = new Interval[n];
    for (int i = 0; i < n; i++) {
      intervals[i] = new Interval(A.get(i));
    }
    Arrays.sort(intervals);

    int endTime = Integer.MIN_VALUE;
    int count = 0;
    for (int i = 0; i < n; i++) {
      if (endTime < intervals[i].start) {
        count++;
        endTime = intervals[i].end;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    Integer[][] A = {
      {1, 4},
      {2, 3},
      {4, 6},
      {8, 9}
    };
    int expected = 3;
    runTestCase(A, expected);

    A =
        new Integer[][] {
          {1, 9},
          {2, 3},
          {5, 7}
        };
    expected = 2;
    runTestCase(A, expected);
  }

  private static void runTestCase(Integer[][] a, int expected) {
    Solution solution = new Solution();
    ArrayList<ArrayList<Integer>> A = new ArrayList<>();
    for (Integer[] interval : a) {
      A.add(new ArrayList<Integer>(Arrays.asList(interval)));
    }
    System.out.println("A: " + A);
    int result = solution.solve(A);
    System.out.println("result: " + result);
    if (result != expected) {
      throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
    }
  }
}
