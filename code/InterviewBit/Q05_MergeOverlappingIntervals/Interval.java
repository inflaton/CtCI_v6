package Q05_MergeOverlappingIntervals;

public class Interval {
  int start;
  int end;

  Interval() {
    this(0, 0);
  }

  Interval(int s, int e) {
    start = s < e ? s : e;
    end = s > e ? s : e;
  }

  @Override
  public String toString() {
    return "[" + start + ", " + end + ']';
  }
}
