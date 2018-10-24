package variants.search;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

// 12.1 Variant: Let A be an unsorted array of n integers, with A[0] > A[l] and A[n - 2] < A[n - 1].
// Call an index i a local minimum if A[i\ is less than or equal to its neighbors.
// How would you efficiently find a local minimum, if one exists?
public class SearchLocalMinimum {
  public int searchLocalMinimul(List<Integer> A){
    if(A.size() < 3) throw new java.lang.Error("size should more than 3");
    int l=1, r=A.size()-2;

    while(l<r){
      int m = l+(r-l)/2;
      //l<=m<m+1<=r
      //keep: A[l-1]>A[l], A[r]<A[r+1]
      if(A.get(m)<A.get(m+1))
        r = m;
      else
        l = m+1;
    }
    return l;
  }

  @Test
  public void test1(){
    List<Integer> A= Arrays.asList(4,3,2,1,0,1,9,10);
    Assert.assertEquals(searchLocalMinimul(A), 4);

    A= Arrays.asList(2,1,0,1,2,3,4,5,6,7);
    Assert.assertEquals(searchLocalMinimul(A), 0);

    A= Arrays.asList(1,0,2,3);
    Assert.assertEquals(searchLocalMinimul(A), 1);
  }
}
