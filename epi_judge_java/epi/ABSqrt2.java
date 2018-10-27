package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class ABSqrt2 {
  private static class Entry implements Comparable<Entry>{
    int a, b;

    public Entry(int a, int b){
      this.a = a; this.b=b;
    }
    public double val(){
      return a+b*Math.sqrt(2);
    }

    @Override
    public int compareTo(Entry o) {
      return Double.compare(val(),o.val());
    }

    @Override
    public boolean equals(Object obj) {
      Entry o = (Entry) obj;
      return a==o.a && b==o.b;
    }
  }

  @EpiTest(testDataFile = "a_b_sqrt2.tsv")

  public static List<Double> generateFirstKABSqrt2(int k) {
    // TODO - you fill in here.
    List<Double> res = new ArrayList<Double>();
    if(k==0) return res;

    List<Entry> list = new ArrayList<Entry>();
    list.add(new Entry(0,0));

    Entry ni, nj, mi;
    for(int i=0, j=0; list.size()<k;){
      ni = new Entry(list.get(i).a+1, list.get(i).b);
      nj = new Entry(list.get(j).a, list.get(j).b+1);
      if(ni.compareTo(nj) <= 0) {
        mi = ni;
        i++;
      }
      else {
        mi = nj;
        j++;
      }
      if(!list.get(list.size()-1).equals(mi))
        list.add(mi);
    }

    for(int i=0; i<list.size(); i++)
      res.add(list.get(i).val());
    return res;
  }

  public static List<Double> generateFirstKABSqrt2_1(int k) {
    // TODO - you fill in here.
    // O(nlogk)
    List<Double> res = new ArrayList<Double>();
    NavigableSet<Entry> set = new TreeSet<>();
    set.add(new Entry(0,0));
    while(res.size() < k && !set.isEmpty()){
      Entry fir = set.pollFirst();
      res.add(fir.val());
      set.add(new Entry(fir.a+1, fir.b));
      set.add(new Entry(fir.a, fir.b+1));
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ABSqrt2.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
