package Q54_NinjiaAttempt2;

import java.util.ArrayList;
import java.util.Arrays;

public class MaximumLongestCommonSubsequence {
  public ArrayList<Integer> maxLCS(String A) {
    int maxLCS = Integer.MIN_VALUE;
    int index = -1;
    for (int i = 0; i < A.length(); i++) {
      int lcs = findLCS(A, i);
      if (lcs > maxLCS) {
        maxLCS = lcs;
        index = i + 1;
      }
    }
    ArrayList<Integer> arrayList = new ArrayList<>();
    arrayList.add(index);
    arrayList.add(maxLCS);
    return arrayList;
  }

  static class MyString {
    String a;
    int i;
    boolean secondHalf;

    MyString(String a, int i, boolean secondHalf) {
      this.a = a;
      this.i = i;
      this.secondHalf = secondHalf;
    }

    int length() {
      return secondHalf ? a.length() - i - 1 : i + 1;
    }

    char charAt(int index) {
      return secondHalf ? a.charAt(a.length() - 1 - index) : a.charAt(index);
    }
  }

  private int findLCS(String a, int i) {
    int ans = 0;
    if (i < a.length() - 1) {
      MyString s = new MyString(a, i, false);
      MyString t = new MyString(a, i, true);
      Integer[][] dp = new Integer[s.length() + 1][t.length() + 1];
      ans = solve(s, s.length(), t, t.length(), dp);
    }
    return ans;
  }

  private int solve(MyString a, int aLen, MyString b, int bLen, Integer[][] dp) {
    if (aLen <= 0 || bLen <= 0) {
      return 0;
    }

    if (dp[aLen][bLen] == null) {
      if (a.charAt(aLen - 1) == b.charAt(bLen - 1)) {
        dp[aLen][bLen] = 1 + solve(a, aLen - 1, b, bLen - 1, dp);
      } else {
        dp[aLen][bLen] = Math.max(solve(a, aLen, b, bLen - 1, dp), solve(a, aLen - 1, b, bLen, dp));
      }
    }
    return dp[aLen][bLen];
  }

  public static void main(String[] args) {
    String a = "abba";
    Integer[] expected = {2, 2};
    runTestCase(a, expected);

    a =
        "dfutojsrnouppbgarjbmchiolyngzrapyxoiwteurrixwxexrdeopjapynzrppwrqriueppyodubwzxmxbpvaxpmcmouotxqrufrflwzxazjuysareqscszgxgphqibbmkqogoabbcrghiuynvwjlhpfqsbxiglwkyhwafdmlpntjwvttbulemteelekfmusjfrnheaelpgmtruewomzkopszudapcascdrikelairyowzufxplxzuxnmjhpdqelgqecmgwatgwgzdvjczucgiclyjjlegnsyrkkxnjcwqjdgwotcgehydhckbivqpufrkowuikvagemmpwowexjealjdhetiakjbxptvpreezytadovzrcbvovhxymfqkfbthniwljqpolfegrcrzefxojkpxxwiaepgitmdxpqdfmxodrahceqhyohmpvtysqqwaeroyvtlvcvqnjkmpnjukxpnbvbadhrsvnqtccxuftiywscxcnewlqbodybgoytzbhyiwpzxiyfwzptksjqctfviokvtyilmoyxtvpgbukzbbiusfcifbqywleegzudofxfjriiyisdjpufgaarqbhruyntbybqlznqwzjodvbdalcngzqsuryeoqsxhuzzicfndcvrrfjwtouiwmpttisiseudfbvtqpdzzpdycurtslmurrafkvdumjvfuxoovbquuforpwcjlnmxxlchaffjmaeimksbyznxrlqprkqkllvlgjtzdzladrinqdatwhwrdlarthyjxrmclkzkaildbvsgfrfygyznopligfxxjqtwpdnhcovzrrdgmoexffmlbshtglioqjtrxqhhcxthkqvqattljdlexsnccgkajixkiikvljnekwlxrrjnpnwdfrgmhzvfbaotwqmfzvatwogoknhoybstufyexewzseadewzgjfsuljebuvawwwpsaeekraocfvibntgzgemvrbasfyfuyfmzuxud";
    expected = new Integer[] {399, 160};
    runTestCase(a, expected);
  }

  private static void runTestCase(String A, Integer[] expected) {
    MaximumLongestCommonSubsequence solution = new MaximumLongestCommonSubsequence();
    System.out.println("A:" + A);

    ArrayList<Integer> result = solution.maxLCS(A);
    System.out.println("result: " + result);
    ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(expected));
    if (!result.equals(expectedResult)) {
      throw new AssertionError(
          "result: " + result + " does not equal to expected: " + expectedResult);
    }
  }
}
