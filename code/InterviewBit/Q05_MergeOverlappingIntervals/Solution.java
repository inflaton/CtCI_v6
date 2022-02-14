package Q05_MergeOverlappingIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Solution {
  public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
    Interval[] intervalArray = new Interval[intervals.size()];
    intervals.toArray(intervalArray);
    Arrays.sort(
        intervalArray,
        new Comparator<Interval>() {
          @Override
          public int compare(Interval o1, Interval o2) {
            return Integer.compare(o1.start, o2.start);
          }
        });

    Interval lastInterval = null;
    ArrayList<Interval> result = new ArrayList<>();
    for (int i = 0; i < intervalArray.length; i++) {
      Interval interval = intervalArray[i];
      if (lastInterval == null || interval.start > lastInterval.end) {
        result.add(interval);
        lastInterval = interval;
      } else if (lastInterval != null && lastInterval.end < interval.end) {
        lastInterval.end = interval.end;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    ArrayList<Interval> arrayList = new ArrayList<>();
    arrayList.add(new Interval(1, 3));
    arrayList.add(new Interval(15, 18));
    arrayList.add(new Interval(2, 6));
    arrayList.add(new Interval(8, 10));
    System.out.println("input: " + arrayList.toString());
    ArrayList<Interval> result = solution.merge(arrayList);
    System.out.println(result.toString());
  }
}
