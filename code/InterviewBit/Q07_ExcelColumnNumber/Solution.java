package Q07_ExcelColumnNumber;

public class Solution {
  public int titleToNumber(String A) {
    int col = 0;
    int factor = 1;
    for (int i = A.length() - 1; i >= 0; i--) {
      col += (A.charAt(i) - 'A' + 1) * factor;
      factor *= 26;
    }
    return col;
  }
}
