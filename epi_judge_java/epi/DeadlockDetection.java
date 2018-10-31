package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeadlockDetection {

  public static class GraphVertex {
    public List<GraphVertex> edges;

    public GraphVertex() { edges = new ArrayList<>(); }
  }

  public static boolean isDeadlocked(List<GraphVertex> graph) {
    // TODO - you fill in here.

    Map<GraphVertex, Integer> count = new HashMap<>();
    Map<GraphVertex, Integer> visited = new HashMap<>();

    for(GraphVertex fr:graph)
      for(GraphVertex to:fr.edges)
        count.put(to, count.getOrDefault(to, 0)+1);

    List<GraphVertex> st = graph.stream().filter(e->!count.containsKey(e)).collect(Collectors.toList());
    if(st.size() == 0) return true;

    int id=0;
    for(GraphVertex v:st)
      if(search(id++, v, visited)) return true;
    return false;
  }

  private static boolean search(int id, GraphVertex v, Map<GraphVertex, Integer> visited){
    if(!visited.containsKey(v)){
      visited.put(v, id);
      for(GraphVertex to:v.edges)
        if(search(id, to, visited)) return true;
    } else if(visited.get(v) == id)
      return true;
    return false;
  }

  @EpiUserType(ctorParams = {int.class, int.class})
  public static class Edge {
    public int from;
    public int to;

    public Edge(int from, int to) {
      this.from = from;
      this.to = to;
    }
  }

  @EpiTest(testDataFile = "deadlock_detection.tsv")
  public static boolean isDeadlockedWrapper(TimedExecutor executor,
                                            int numNodes, List<Edge> edges)
      throws Exception {
    if (numNodes <= 0) {
      throw new RuntimeException("Invalid numNodes value");
    }
    List<GraphVertex> graph = new ArrayList<>();
    for (int i = 0; i < numNodes; i++) {
      graph.add(new GraphVertex());
    }
    for (Edge e : edges) {
      if (e.from < 0 || e.from >= numNodes || e.to < 0 || e.to >= numNodes) {
        throw new RuntimeException("Invalid vertex index");
      }
      graph.get(e.from).edges.add(graph.get(e.to));
    }

    return executor.run(() -> isDeadlocked(graph));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeadlockDetection.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
