package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;

import epi.Gcd;

public class LineThroughMostPoints {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Point {
    public int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
      Point o=(Point) obj;
      return x==o.x && y==o.y;
    }

    @Override
    public int hashCode() {
      return x<<10+y;
    }
  }

  @EpiTest(testDataFile = "line_through_most_points.tsv")

  public static int findLineWithMostPoints(List<Point> points) {
    // TODO - you fill in here.

    Point zero = new Point(0, 0);
    int n=points.size(), res=0;
    for(int i=0; i<n; i++) {
      HashMap<Point, Integer> cnt=new HashMap<>();
      for (int j = 0; j<n; j++)
        if(j!=i){
          Point r = buildRational(points.get(i).x-points.get(j).x, points.get(i).y-points.get(j).y);
          cnt.put(r, cnt.getOrDefault(r,0)+1);
          if(!r.equals(zero)) //bug: forgot avoid zero
            res = Math.max(res, 1+cnt.get(r)+cnt.getOrDefault(zero,0));
        }

      res = Math.max(res, 1+cnt.getOrDefault(zero,0));
    }
    return res;
  }

  private static Point buildRational(int x, int y){
    int sign = x*y>=0 ? 1:-1;
    x = Math.abs(x);
    y = Math.abs(y);
    int g = gcd(x,y);
    if(g == 0) return new Point(0, 0);
    x /= g; y/=g;
    return new Point(x, sign*y);
  }

  private static int gcd(int x, int y){
    while(x != 0 && y!=0){
      if(x>=y) x %= y;
      else y %= x;
    }
    return x+y;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LineThroughMostPoints.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
