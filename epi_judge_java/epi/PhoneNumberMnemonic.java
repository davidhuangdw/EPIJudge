package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.ArrayList;

public class PhoneNumberMnemonic {
  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")

  public static List<String> phoneMnemonic(String phoneNumber) {
    // TODO - you fill in here.
    char [] word = new char[phoneNumber.length()];
    List<String> res = new ArrayList<String>();
    gen(phoneNumber, 0, word, res);
    return res;
  }

  private static String[] MAPPING = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

  private static void gen(String ph, int pos, char [] word, List<String> res){
    if(pos >= ph.length()){
      res.add(new String(word));
      return;
    }
    int number = ph.charAt(pos) - '0';
    for(int i=0; i<MAPPING[number].length(); i++) {
      word[pos] = MAPPING[number].charAt(i);
      gen(ph, pos + 1, word, res);
    }
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
            .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
