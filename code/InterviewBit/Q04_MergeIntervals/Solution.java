package Q04_MergeIntervals;

import java.util.ArrayList;

public class Solution {
  public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
    // binary search to determine index lo at which to insert newInterval
    int lo = 0;
    int hi = intervals.size();
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      if (newInterval.start > intervals.get(mid).start) {
        lo = mid + 1;
      } else {
        hi = mid;
      }
    }

    Interval intervalToInsert = new Interval(newInterval.start, newInterval.end);
    ArrayList<Interval> result = new ArrayList<>();
    Interval lastInterval = null;
    hi = intervals.size();
    if (hi == lo) {
      hi = lo + 1;
    }

    for (int i = 0; i < hi; i++) {
      if (i == lo) {
        if (lastInterval != null && lastInterval.end >= intervalToInsert.start) {
          lastInterval.end = intervalToInsert.end;
          intervalToInsert = lastInterval;
        } else {
          result.add(intervalToInsert);
        }
      }
      if (i < intervals.size()) {
        Interval interval = intervals.get(i);
        if (i < lo) {
          result.add(interval);
          lastInterval = interval;
        } else {
          if (interval.start <= intervalToInsert.end) {
            if (intervalToInsert.end < interval.end) {
              intervalToInsert.end = interval.end;
            }
          } else {
            result.add(interval);
          }
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    ArrayList<Interval> arrayList = new ArrayList<>();
    arrayList.add(new Interval(1, 3));
    arrayList.add(new Interval(6, 9));
    System.out.println("input: " + arrayList.toString());
    ArrayList<Interval> result = solution.insert(arrayList, new Interval(2, 5));
    System.out.println(result.toString());

    arrayList.clear();
    arrayList.add(new Interval(1, 2));
    arrayList.add(new Interval(3, 5));
    arrayList.add(new Interval(6, 7));
    arrayList.add(new Interval(8, 10));
    arrayList.add(new Interval(12, 16));
    System.out.println("input: " + arrayList.toString());
    result = solution.insert(arrayList, new Interval(4, 9));
    System.out.println(result.toString());

    arrayList.clear();
    arrayList.add(new Interval(1, 2));
    arrayList.add(new Interval(3, 6));
    System.out.println("input: " + arrayList.toString());
    result = solution.insert(arrayList, new Interval(10, 8));
    System.out.println(result.toString());

    arrayList.clear();
    arrayList.add(new Interval(3, 6));
    arrayList.add(new Interval(8, 10));
    System.out.println("input: " + arrayList.toString());
    result = solution.insert(arrayList, new Interval(1, 2));
    System.out.println(result.toString());
  }
}
