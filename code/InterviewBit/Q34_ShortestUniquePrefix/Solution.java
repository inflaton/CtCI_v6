package Q34_ShortestUniquePrefix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
  private static final int R = 256;

  private static class TrieNode {
    HashMap<Character, TrieNode> next;
    String word;

    TrieNode() {
      this(null);
    }

    TrieNode(String word) {
      this.word = word;
      this.next = new HashMap<>();
    }

    public boolean isWord() {
      return this.word != null;
    }

    @Override
    public String toString() {
      return "TrieNode{"
          + "next["
          + next.size()
          + "]="
          + next
          + (word == null ? "" : ", word='" + word + '\'' + '}');
    }
  }

  public ArrayList<String> prefix(ArrayList<String> A) {
    ArrayList<String> ans = new ArrayList<>();
    TrieNode root = new TrieNode();
    for (String s : A) {
      add(root, s);
    }

    for (String s : A) {
      String prefix = findPrefix(root, s);
      ans.add(prefix);
    }
    return ans;
  }

  private void add(TrieNode root, String s) {
    add(root, s, 0);
  }

  private TrieNode add(TrieNode node, String s, int i) {
    if (node == null) {
      node = new TrieNode();
    }
    if (i == s.length()) {
      node.word = s;
    } else {
      char ch = s.charAt(i);
      TrieNode nextNode = node.next.get(ch);
      node.next.put(ch, add(nextNode, s, i + 1));
    }
    return node;
  }

  private String findPrefix(TrieNode root, String s) {
    int len = findPrefixLength(root, s, 0, 0);
    return s.substring(0, len);
  }

  private int findPrefixLength(TrieNode node, String s, int i, int lastBranchingIndex) {
    if (i == s.length()) {
      return Math.min(lastBranchingIndex + 1, i);
    }
    char ch = s.charAt(i);
    TrieNode nextNode = node.next.get(ch);

    if (node.isWord() || node.next.size() >= 2) {
      lastBranchingIndex = i;
    }

    return findPrefixLength(nextNode, s, i + 1, lastBranchingIndex);
  }

  public static void main(String[] args) {
    String[] array = {"zebra", "dog", "duck", "dove"};
    String[] expected = {"z", "dog", "du", "dov"};
    runTestCase(array, expected);

    String[] array1 = {"zebra"};
    String[] expected1 = {"z"};
    runTestCase(array1, expected1);

    String[] array2 = {"bearcat", "bert"};
    String[] expected2 = {"bea", "ber"};
    runTestCase(array2, expected2);

    String[] array3 = {"lrbbmqb", "cd", "r", "owkk"};
    String[] expected3 = {"l", "c", "r", "o"};
    runTestCase(array3, expected3);

    String[] array4 = {"zebra", "doggy", "duck", "dove", "dog"};
    String[] expected4 = {"z", "dogg", "du", "dov", "dog"};
    runTestCase(array4, expected4);

    String[] array5 = {
      "qzgdixrpddzplcrwnqwqecyjyibfjykmjfqwltvzkqtpvolp",
      "ckcyufdqmlglimklfzktgygdttnh",
      "vpf",
      "fbrpzlkvshwywshtdg",
      "babc",
      "bkkxcvgumonmwvytbytnuqhmfjaqtgn",
      "cwkuzyamnerphfmwevhwlezohye",
      "hbrcewjxvceziftiqtntfsrpt",
      "gtiznorvonzjfeacgamayapwlmbzitzszhzkosvnknberbltl",
      "ggdgpljfisyltmmfvhybljvkypcflsaqevcijcyrgmq",
      "rzniaxakholawoydvchveigttxwpukzjfhx",
      "rtspfttotafsngqvoijxuvqbztvaalsehzxbshnrvbyk",
      "qlrzzfmlvyoshiktodnsjjpqplciklzqrxloqxru",
      "ygjtyz",
      "eizmeainxslwhhjwslqendjvxjyg",
      "rveuvphknqtsdtwxcktmwwwsdthzmlmbhjkmouhpbqurqfxgql",
      "jmwsomowsjvpv",
      "zn",
      "silhhdkb",
      "xqgrgedpzchrgefeukmcow",
      "eabc",
      "nwhpiiduxdnnlbnmyjyssbsococdzcuunkrfduvoua",
      "hhcyvmlkzaajpfpyljtyjjpyntsef",
      "iswjutenuycpbcnmhfuqmmidmvknyxmywegm",
      "unodvuzygvguxtrdsdfz",
      "ssmeluodjgdgzfmrazvndtaurdkugsbdpawxitivdubbqeonyc",
      "egxfjkklrfkraoheucsvpiteqrswgkaaaohxxzhqjtkqa",
      "hkwberbpmglbjipnujywogwczlkyrdejaqufowbig",
      "snjniegvdvotugocedktcbbu",
      "nxorix",
      "bbdfrzuqsyrfqghoyqevcuanuujszitaoaowsxygl",
      "fbwzddoznrvjqeyqignpitruijvyllsibobjltusryp",
      "nvybsfrxtlfmpdidtyozoolzslgdgowijat",
      "lvjzscizrkupmsoxftumyxifyunxucubvkfctkqlroqgz",
      "vjwzizppvsomflvioemy",
      "nphfjtbnwedtubynsbirepgcxfgsf",
      "mhvpmymkdohetty",
      "csibbeaxniwjkfvabnrllkmaglythkglauzgkeu",
      "yrpaeurdvexqlwgakdtbihmfrjijanxkhrqdllecyhbsuxnl",
      "tmjcnyybwsjmajbwtuhkki",
      "vytgaufpjlxiwbnzhybsxfmumbhkjqm",
      "abmyulbrglwstjkoxbczkjhvhsgzvwiixxaobhfsopqneb",
      "flcooetjizolqrmsxphqdgz",
      "mqhoggvrvjqrpmxb",
      "kkfgzzxjegsyovdrmwcjavpmshojzxaxnbiztkfomzdhujdmcy",
      "dqteqjalgqgsomon",
      "breqqzzpwqlihdnvudvlzn",
      "hbaokxvc",
      "lykfhxbldylqqewdnjzrlbskqgfvnqlfvobeyolyy",
      "vviwhxfpbuiujlolnjldgvwxljboayp",
      "otdzjxxrschmw",
      "veliumzpnoieipogwilaswntywuegd",
      "yethsrznlzrffmwdgxaigmxpyvyaqga",
      "ltodtlgzcyvfiykmkllfb",
      "xqyhvizqmamjzlvvgoiflt"
    };
    String[] expected5 = {
      "qz", "ck", "vp", "fbr", "ba", "bk", "cw", "hbr", "gt", "gg", "rz", "rt", "ql", "yg", "ei",
      "rv", "j", "z", "si", "xqg", "ea", "nw", "hh", "i", "u", "ss", "eg", "hk", "sn", "nx", "bb",
      "fbw", "nv", "lv", "vj", "np", "mh", "cs", "yr", "t", "vy", "a", "fl", "mq", "k", "d", "br",
      "hba", "ly", "vv", "o", "ve", "ye", "lt", "xqy"
    };
    runTestCase(array5, expected5);
  }

  private static void runTestCase(String[] array, String[] expectedArray) {
    ArrayList<String> input = new ArrayList<>(Arrays.asList(array));
    System.out.println("input: " + input);
    ArrayList<String> result = new Solution().prefix(input);
    System.out.println("result: " + result);
    ArrayList<String> expected = new ArrayList<>(Arrays.asList(expectedArray));
    if (!result.equals(expected)) {
      for (int i = 0; i < expectedArray.length; i++) {
        if (!expectedArray[i].equals(result.get(i))) {
          throw new AssertionError(
              "result["
                  + i
                  + "]: "
                  + result.get(i)
                  + " does not equal to expected: "
                  + expectedArray[i]);
        }
      }
    }
  }
}
