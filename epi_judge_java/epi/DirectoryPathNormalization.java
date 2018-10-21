package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.serialization_traits.LinkedListTraits;

import java.util.*;

public class DirectoryPathNormalization {
  @EpiTest(testDataFile = "directory_path_normalization.tsv")

  public static String shortestEquivalentPath(String path) {
    // TODO - you fill in here.
    if(path.length() == 0) return path;

    Deque<String> cur = new LinkedList<String>();
    boolean relative = path.charAt(0) != '/';
    String [] words = path.split("/");
    for(String word: words){
      if(word.equals("") || word.equals("."))
        continue;
      if(word.equals("..")){
        if(relative){
          if(cur.isEmpty() || cur.peek().equals(".."))
            cur.push(word);
          else
            cur.pop();
        }else{
          if(cur.isEmpty()) return null;
          cur.pop();
        }
      }else
        cur.push(word);
    }

    List<String> paths = new ArrayList<String>();
    for(Iterator it=cur.descendingIterator(); it.hasNext();)
      paths.add((String)it.next());

    return (relative ? "" : "/") + String.join("/", paths);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
