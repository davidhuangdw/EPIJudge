package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class RoadNetwork {
  @EpiUserType(ctorParams = {int.class, int.class, int.class})

  public static class HighwaySection {
    public int x, y, distance;

    public HighwaySection(int x, int y, int distance) {
      this.x = x;
      this.y = y;
      this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      HighwaySection that = (HighwaySection)o;
      return x == that.x && y == that.y && distance == that.distance;
    }

    @Override
    public String toString() {
      return "[" + x + ", " + y + ", " + distance + "]";
    }
  }

  @EpiTest(testDataFile = "road_network.tsv")

  public static HighwaySection
  findBestProposals(List<HighwaySection> H, List<HighwaySection> P, int n) {
    // TODO - you fill in here.

    Integer shortest[][] = new Integer[n][n];
    for(int i=0; i<n; i++)
      shortest[i][i] = 0;

    for(HighwaySection sec: H)
      if(shortest[sec.x][sec.y] == null || sec.distance < shortest[sec.x][sec.y])
        shortest[sec.x][sec.y] = shortest[sec.y][sec.x] = sec.distance;

    for(int i=0; i<n; i++)
      for(int j=0; j<n; j++)
        for(int k=j+1; k<n; k++)
          if(shortest[j][i] != null && shortest[i][k] != null
            && (shortest[j][k] == null || shortest[j][k] > shortest[j][i] + shortest[i][k]))
            shortest[j][k] = shortest[k][j] = shortest[j][i] + shortest[i][k];

    int max = -1;
    HighwaySection res=null;
    for(HighwaySection s:P){
      int sum=0;

      for(int i=0; i<n; i++)
        for(int j=i+1; j<n; j++)
          sum += Math.max(0, shortest[i][j] -
              Math.min(shortest[i][s.x]+shortest[s.y][j], shortest[i][s.y]+shortest[s.x][j])-s.distance);
      if(sum > max){
        max = sum;
        res = s;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RoadNetwork.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
