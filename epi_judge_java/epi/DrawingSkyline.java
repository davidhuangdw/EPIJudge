package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.*;

public class DrawingSkyline {
  @EpiUserType(ctorParams = {int.class, int.class, int.class})

  public static class Rectangle {
    public int left, right, height;

    public Rectangle(int left, int right, int height) {
      this.left = left;
      this.right = right;
      this.height = height;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Rectangle rectangle = (Rectangle)o;

      if (left != rectangle.left) {
        return false;
      }
      if (right != rectangle.right) {
        return false;
      }
      return height == rectangle.height;
    }

    @Override
    public String toString() {
      return "[" + left + ", " + right + ", " + height + ']';
    }
  }

  private static class Endpoint{
    int x,h;
    boolean start;
    public Endpoint(int ix, int ih, boolean istart){
      x=ix; h=ih; start=istart;
    }
  }

  @EpiTest(testDataFile = "drawing_skyline.tsv")

  public static List<Rectangle> drawingSkylines(List<Rectangle> buildings) {
    // TODO - you fill in here.
    // todo: debug

    List<Rectangle> res = new ArrayList<>();
    if(buildings.size() == 0) return res;

    initEndpoints(buildings);
    TreeSet<Integer> set = new TreeSet<>();
    Endpoint cur=null;
    int lastx = -1;
    for(Endpoint p: endpoints){
      if(p.start) {
        //create when higher
        if(!set.isEmpty() && p.h > set.last()){
          res.add(new Rectangle(lastx, p.x, set.last()));
          lastx = p.x;
        }
        if(set.isEmpty()) lastx=p.x;
        set.add(p.h);
      }
      else{
        set.remove(p.h);
        if(set.isEmpty() || set.last() < p.h){
          res.add(new Rectangle(lastx, p.x, p.h));
          lastx = p.x;
        }
      }
    }

    return res;
  }

  private static void initEndpoints(List<Rectangle> buildings){
    endpoints = new ArrayList<>();
    for(Rectangle r: buildings){
      endpoints.add(new Endpoint(r.left, r.height, true));
      endpoints.add(new Endpoint(r.right, r.height, false));
    }
    Collections.sort(endpoints, (a,b)->(a.x<b.x||(a.x==b.x && a.start) ? -1: 1));
  }

  private static List<Endpoint> endpoints;

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DrawingSkyline.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
