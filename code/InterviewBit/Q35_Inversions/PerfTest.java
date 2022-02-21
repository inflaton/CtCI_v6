package Q35_Inversions;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.function.Function;

public class PerfTest {
  public static void main(String[] args) {
    final String[] names = {"Merge sort", "Using Trie", "Heap sort + Bisection", "Brute Force"};
    final Function[] functions =
        new Function[] {
          x -> new Solution().countInversions((int[]) x),
          x -> new Solution().countInversionsUsingTrie((int[]) x),
          x -> new Solution().countInversionsHeapsortBisection((int[]) x),
          x -> new Solution().countInversionsBruteForce((int[]) x)
        };

    final int MAX = 1000000000;
    final Random random = new Random();
    for (int size = 100; size <= MAX; size *= 10) {
      int[] array = new int[size];
      for (int j = 0; j < size; j++) {
        array[j] = random.nextInt(MAX);
      }
      runTestCase(array, functions, names);
    }
  }

  private static void runTestCase(int[] array, Function[] functions, String[] names) {
    DecimalFormat ft = new DecimalFormat("###,###");
    System.out.println("input length = " + ft.format(array.length));

    int length = functions.length;
    if (array.length > 100000) {
      length--; // skip brute force
      if (array.length > 1000000) {
        length--; // skip Heap sort + Bisection - too slow
        if (array.length > 10000000) {
          length--; // skip Using Trie - too much heap space used
        }
      }
    }

    long expected = -1;
    for (int i = 0; i < length; i++) {
      long start = System.nanoTime();
      long result = (long) functions[i].apply(array);
      long end = System.nanoTime();

      if (expected < 0) {
        System.out.println("result: " + result);
        expected = result;
      } else if (result != expected) {
        throw new AssertionError("result: " + result + " does not equal to expected: " + expected);
      }

      System.out.println(
          names[i] + " elapsed time (s): " + (double) (end - start) / 1000.0 / 1000.0 / 1000.0);
      System.gc();
    }
  }
}

/*
/Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home/bin/java -Xms512m -Xmx128g -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=52136:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/donghao/Documents/code/exam/CtCI_v6/out/production/CtCI_v6 Q35_Inversions.PerfTest
input length = 100
result: 2429
Merge sort elapsed time (s): 5.389539999999999E-4
Using Trie elapsed time (s): 0.001063223
Heap sort + Bisection elapsed time (s): 0.005673529
Brute Force elapsed time (s): 1.2775000000000002E-4
input length = 1,000
result: 249367
Merge sort elapsed time (s): 5.03567E-4
Using Trie elapsed time (s): 0.001412538
Heap sort + Bisection elapsed time (s): 0.001903508
Brute Force elapsed time (s): 0.003342173
input length = 10,000
result: 25005119
Merge sort elapsed time (s): 0.001587329
Using Trie elapsed time (s): 0.013548003000000001
Heap sort + Bisection elapsed time (s): 0.011157690000000001
Brute Force elapsed time (s): 0.050490424
input length = 100,000
result: 2501114892
Merge sort elapsed time (s): 0.012748993
Using Trie elapsed time (s): 0.179536518
Heap sort + Bisection elapsed time (s): 0.25098308799999997
Brute Force elapsed time (s): 6.2697063360000005
input length = 1,000,000
result: 249745603289
Merge sort elapsed time (s): 0.132379627
Using Trie elapsed time (s): 1.637584268
Heap sort + Bisection elapsed time (s): 27.159691729
input length = 10,000,000
result: 24994825212697
Merge sort elapsed time (s): 1.510088514
Using Trie elapsed time (s): 28.284922000999998
input length = 100,000,000
result: 2500088533416732
Merge sort elapsed time (s): 17.449066473000002
input length = 1,000,000,000
result: 250008596138714743
Merge sort elapsed time (s): 196.904193403
 */
