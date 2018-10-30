package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class RefuelingSchedule {
  private static final int MPG = 20;

  // gallons[i] is the amount of gas in city i, and distances[i] is the distance
  // city i to the next city.
  public static int findAmpleCity(List<Integer> gallons,
                                  List<Integer> distances) {
    // TODO - you fill in here.
    int min= Integer.MAX_VALUE, mini=-1, n=gallons.size(), sum=0;
    for(int i=0; i<n; i++){
      sum += gallons.get(i)*MPG - distances.get(i);
      if(sum < min){
        min = sum;
        mini = i;
      }
    }
    return (mini+1)%n;
  }

  public static int findAmpleCity1(List<Integer> gallons,
                                  List<Integer> distances) {
    // TODO - you fill in here.

    int n =gallons.size();
    int sum[] = new int[n*2];

    for(int i=0; i<n; i++)
      sum[i] = sum[n+i] = gallons.get(i)*MPG - distances.get(i);
    for(int i=1; i<n*2; i++)
      sum[i] += sum[i-1];

    Deque<Integer> minQue = new LinkedList<>();
    for(int i=0; i<n; i++) {
      while (!minQue.isEmpty() && minQue.peek()>sum[i])
        minQue.pop();
      minQue.push(sum[i]);
    }

    int prev = 0;
    for(int i=0; i<n; i++) {
      if (minQue.getLast()-prev >=0)
        return i;
      prev =  sum[i];
      while (!minQue.isEmpty() && minQue.peek()>sum[i+n])
        minQue.pop();
      minQue.push(sum[i+n]);
    }

    return -1;
  }
  @EpiTest(testDataFile = "refueling_schedule.tsv")
  public static void findAmpleCityWrapper(TimedExecutor executor,
                                          List<Integer> gallons,
                                          List<Integer> distances)
      throws Exception {
    int result = executor.run(() -> findAmpleCity(gallons, distances));
    final int numCities = gallons.size();
    int tank = 0;
    for (int i = 0; i < numCities; ++i) {
      int city = (result + i) % numCities;
      tank += gallons.get(city) * MPG - distances.get(city);
      if (tank < 0) {
        throw new TestFailure(String.format("Out of gas on city %d", city));
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RefuelingSchedule.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
