package Q19_JustifiedText;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
  public ArrayList<String> fullJustify(ArrayList<String> A, int B) {
    ArrayList<String> ans = new ArrayList<>();
    int numOfWords = 0;
    int lineLength = 0;

    for (int i = 0; i < A.size(); i++) {
      String word = A.get(i);
      int newLineLength = lineLength + word.length() + (numOfWords > 0 ? 1 : 0);

      if (newLineLength > B) {
        i--;
      } else {
        numOfWords++;
        lineLength = newLineLength;
      }

      boolean lastLine = i == A.size() - 1;
      if (newLineLength > B || lastLine) {
        StringBuilder sb = new StringBuilder();
        int extraSpaces = B - lineLength + numOfWords - 1;
        int mod = 0;
        int averageSpaces = extraSpaces;

        // For the last line of text, it should be left justified and no extra space is inserted
        // between words.
        if (lastLine) {
          averageSpaces = 1;
        } else if (numOfWords > 1) {
          averageSpaces = extraSpaces / (numOfWords - 1);
          mod = extraSpaces % (numOfWords - 1);
        }

        for (int j = 0; j < numOfWords; j++) {
          String s = A.get(i + 1 - numOfWords + j);
          sb.append(s);
          if (numOfWords == 1 || j < numOfWords - 1 || lastLine) {
            int numOfSpaces = lastLine && j == numOfWords - 1 ? extraSpaces : averageSpaces;
            if (mod > 0) {
              mod--;
              numOfSpaces++;
            }
            for (int k = 0; k < numOfSpaces; k++) {
              sb.append(" ");
            }
            extraSpaces -= numOfSpaces;
          }
        }
        ans.add(sb.toString());
        lineLength = 0;
        numOfWords = 0;
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    ArrayList<String> a =
        new ArrayList<>(
            Arrays.asList("This", "is", "an", "example", "of", "text", "justification."));
    System.out.println("a: " + Arrays.toString(a.toArray()));
    int b = 16;
    ArrayList<String> ans = s.fullJustify(a, b);
    System.out.println("output: " + ans);
    for (String str : ans) {
      System.out.println(str.length() + ": '" + str + "'");
    }

    a = new ArrayList<>(Arrays.asList("What", "must", "be", "shall", "be."));
    System.out.println("a: " + Arrays.toString(a.toArray()));
    b = 12;
    ans = s.fullJustify(a, b);
    System.out.println("output: " + ans);
    for (String str : ans) {
      System.out.println(str.length() + ": '" + str + "'");
    }
  }
}
