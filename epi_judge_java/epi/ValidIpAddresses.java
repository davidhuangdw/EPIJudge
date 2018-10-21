package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.EpiTestComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class ValidIpAddresses {
  @EpiTest(testDataFile = "valid_ip_addresses.tsv")

  public static List<String> getValidIpAddress(String s) {
    // TODO - you fill in here.

    char [] pre = new char[s.length()+3];
    List<String> valids = new ArrayList<String>();

    search(4, 0, s, new ArrayList<String>(), valids);
    return valids;
  }

  private static void search(int remain, int ns, String s, List<String> cur, List<String> valids){
    if(remain == 1){
      String sub = s.substring(ns);
      if(validSeg(sub)){
        cur.add(sub);
        valids.add(String.join(".", cur));
        cur.remove(cur.size()-1);
      }
      return;
    }

    int l = s.length()-ns;
    if(l < remain) return;
    // r-1 <= l-i <= (r-1)*3 => l-(r-1)*3 <= i <= l-(r-1)
    // 1<=i<=3
    int max_off = s.charAt(ns) == '0'? 1:3;
    for(int i = Math.max(1, l-(remain-1)*3); i<=Math.min(max_off, l-(remain-1)); i++){
      String sub = s.substring(ns, ns+i);
      if(validSeg(sub)){
        cur.add(sub);
        search(remain-1, ns+i, s, cur, valids);
        cur.remove(cur.size()-1);
      }
      else
        break;
    }
  }

  private static boolean validSeg(String seg){
    if(seg.charAt(0) == '0' && seg.length() > 1)
      return false;

    int sum = 0;
    for(int i=0; i<seg.length() && sum<=255; i++)
      sum = sum * 10 + seg.charAt(i) - '0';
    return sum<=255;
  }

  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ValidIpAddresses.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
