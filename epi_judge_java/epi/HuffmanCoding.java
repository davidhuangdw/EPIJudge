package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class HuffmanCoding {
  @EpiUserType(ctorParams = {String.class, double.class})

  public static class CharWithFrequency {
    public char c;
    public double freq;
    public String code;

    public CharWithFrequency(String s, double freq) {
      if (s.length() != 1) {
        throw new RuntimeException(
            "CharWithFrequency parser: string length is not 1");
      }
      this.c = s.charAt(0);
      this.freq = freq;
    }
  }

  @EpiTest(testDataFile = "huffman_coding.tsv")

  public static Double huffmanEncoding(List<CharWithFrequency> symbols) {
    // TODO - you fill in here.

    if(symbols.size() <= 2) return 1.0;

    double sum=0;
    PriorityQueue<Double> que = new PriorityQueue<>();
    for(CharWithFrequency s: symbols)
      que.add(s.freq/100);
    while(que.size()>1){
      double pair = que.poll();
      pair += que.poll();
      sum += pair;
      que.add(pair);
    }
    return sum;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "HuffmanCoding.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
