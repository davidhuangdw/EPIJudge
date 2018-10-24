package variants.search;
import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

// 12.3 Variant: A sequence is strictly ascending if each element is greater than its predecessor.
// Suppose it is known that an array A consists of a strictly ascending sequence followed by a strictly
// a strictly descending sequence. Design an algorithm for finding the maximum element in A.
public class SearchAscendDescendArray {
  public int searchAscendDescendArray(List<Integer> A){
    if(A.size() == 0) return -1;
    int l=0, r=A.size()-1;

    while(l<r){
      int m = l + (r-l)/2;
      if(A.get(m) < A.get(m+1))
        l = m+1;
      else
        r = m;
    }
    return l;
  }

  @Test
  public void test1(){
    List<Integer> A;
    A = Arrays.asList(0,1,2,3,4,2,-1,-5);
    Assert.assertEquals(searchAscendDescendArray(A), 4);

    A = Arrays.asList(0,1,0);
    Assert.assertEquals(searchAscendDescendArray(A), 1);

    A = Arrays.asList(0,1,2,3,4);
    Assert.assertEquals(searchAscendDescendArray(A), 4);

    A = Arrays.asList(5,3,1);
    Assert.assertEquals(searchAscendDescendArray(A), 0);

    A = Arrays.asList(0,1);
    Assert.assertEquals(searchAscendDescendArray(A), 1);

    A = Arrays.asList(1,0);
    Assert.assertEquals(searchAscendDescendArray(A), 0);
  }

}
