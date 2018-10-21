package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class CircularQueue {

  public static class Queue {
    private int capacity, size, head, tail;
    Integer[] val;
    public Queue(int cap) {
      capacity = cap;
      val = new Integer[capacity];
      head = tail = size = 0;
    }

    public void enqueue(Integer x) {
      // TODO - you fill in here.
      if(size == capacity)
        resize(capacity<<1);
      val[head] = x;
      head = (head+1)%capacity;
      size ++;
      return;
    }
    public Integer dequeue() {
      // TODO - you fill in here.
      int r = val[tail];
      tail = (tail+1)%capacity;
      size--;
      return r;
    }
    public int size() {
      // TODO - you fill in here.
      return size;
    }
    @Override
    public String toString() {
      // TODO - you fill in here.
      return super.toString();
    }

    private void resize(int cap){
      Integer[] next = new Integer[cap];
      for(int i=0; i<size; i++)
        next[i] = val[(tail+i)%capacity];
      capacity = cap;
      head = size;
      tail = 0;
      val = next;
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTest(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
