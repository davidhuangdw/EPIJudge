package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class AddingCredits {

  public static class ClientsCreditsInfo {
    private int offset=0;
    private Map<String, Integer> credits = new HashMap<>();
    private NavigableMap<Integer, Set<String>> creditToClient = new TreeMap<>();

    public void insert(String clientID, int c) {
      // TODO - you fill in here.
      c -= offset;
      Integer cr = credits.get(clientID);
      if(cr!=null && cr.equals(c)) return;

      remove(clientID);

      credits.put(clientID, c);
      if(creditToClient.get(c) == null)
        creditToClient.put(c, new HashSet<>());
      creditToClient.get(c).add(clientID);
    }
    public boolean remove(String clientID) {
      // TODO - you fill in here.
      Integer credit = credits.get(clientID);
      if(credit == null) return false;

      credits.remove(clientID);
      Set<String> set = creditToClient.get(credit);
      set.remove(clientID);
      if(set.isEmpty())
        creditToClient.remove(credit);
      return true;
    }
    public int lookup(String clientID) {
      // TODO - you fill in here.

      Integer c = credits.get(clientID);
      return c==null ? -1 : c+offset;
    }
    public void addAll(int C) {
      // TODO - you fill in here.
      offset += C;
      return;
    }
    public String max() {
      // TODO - you fill in here.
      return creditToClient.isEmpty() ? "" : creditToClient.lastEntry().getValue().iterator().next();
    }
    @Override
    public String toString() {
      // TODO - you fill in here.
      return super.toString();
    }
  }
  @EpiUserType(ctorParams = {String.class, String.class, int.class})
  public static class Operation {
    public String op;
    public String sArg;
    public int iArg;

    public Operation(String op, String sArg, int iArg) {
      this.op = op;
      this.sArg = sArg;
      this.iArg = iArg;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Operation operation = (Operation)o;

      if (iArg != operation.iArg) {
        return false;
      }
      if (!op.equals(operation.op)) {
        return false;
      }
      return sArg.equals(operation.sArg);
    }

    @Override
    public int hashCode() {
      int result = op.hashCode();
      result = 31 * result + sArg.hashCode();
      result = 31 * result + iArg;
      return result;
    }

    @Override
    public String toString() {
      return String.format("%s(%s, %d)", op, sArg, iArg);
    }
  }

  @EpiTest(testDataFile = "adding_credits.tsv")
  public static void ClientsCreditsInfoTester(List<Operation> ops)
      throws TestFailure {
    ClientsCreditsInfo cr = new ClientsCreditsInfo();
    int opIdx = 0;
    for (Operation x : ops) {
      String sArg = x.sArg;
      int iArg = x.iArg;
      int result;
      switch (x.op) {
      case "ClientsCreditsInfo":
        break;
      case "remove":
        result = cr.remove(sArg) ? 1 : 0;
        if (result != iArg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, cr)
              .withProperty(TestFailure.PropertyName.COMMAND, x)
              .withMismatchInfo(opIdx, iArg, result);
        }
        break;
      case "insert":
        cr.insert(sArg, iArg);
        break;
      case "add_all":
        cr.addAll(iArg);
        break;
      case "lookup":
        result = cr.lookup(sArg);
        if (result != iArg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, cr)
              .withProperty(TestFailure.PropertyName.COMMAND, x)
              .withMismatchInfo(opIdx, iArg, result);
        }
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AddingCredits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
