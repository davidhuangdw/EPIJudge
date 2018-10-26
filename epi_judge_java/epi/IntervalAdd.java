package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.List;
public class IntervalAdd {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Interval interval = (Interval)o;

      if (left != interval.left) {
        return false;
      }
      return right == interval.right;
    }

    @Override
    public String toString() {
      return "[" + left + ", " + right + "]";
    }
  }

  @EpiTest(testDataFile = "interval_add.tsv")

  public static List<Interval> addInterval(List<Interval> disjointIntervals,
                                           Interval newInterval) {
    // TODO - you fill in here.

//    List<Interval> res= new ArrayList<Interval>();
//    Interval first=null;
//    Interval last=null;
//    for(Interval x: disjointIntervals)
//      if(intersect(x,newInterval)){
//        if(first == null) first = x;
//        last = x;
//      } else res.add(x);
//    if(first != null)
//      newInterval = new Interval(Math.min(first.left, newInterval.left), Math.max(last.right, newInterval.right));
//    int i;
//    for(i=0; i<res.size() && res.get(i).left < newInterval.left; i++);
//    res.add(i, newInterval);

    int l,r;
    for(l=0; l<disjointIntervals.size() && disjointIntervals.get(l).right < newInterval.left; l++);
    for(r=disjointIntervals.size()-1; r>=0 && disjointIntervals.get(r).left > newInterval.right; r--);
    if(l<=r)
      newInterval = new Interval(Math.min(disjointIntervals.get(l).left, newInterval.left),
          Math.max(disjointIntervals.get(r).right, newInterval.right));

    List<Interval> res= new ArrayList<Interval>(disjointIntervals.subList(0, l));
    res.add(newInterval);
    res.addAll(disjointIntervals.subList(r+1, disjointIntervals.size()));

    return res;
  }

  static private boolean intersect(Interval a, Interval b){
    return a.right >= b.left && a.left <= b.right;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntervalAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
