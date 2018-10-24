package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(A, 1) returns 3, findKthLargest(A, 2) returns 2,
  // findKthLargest(A, 3) returns 1, and findKthLargest(A, 4) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {
    // TODO - you fill in here.
    if(k > A.size()) return -1;

    int l=0, r=A.size()-1;
    while(r+1-l >= k){
      int v = A.get(l);
      int i=l+1, j=r;
      while(i<=j){
        while(i<=j && A.get(i)<v)
          i++;
        while(i<=j && A.get(j)>=v)
          j--;
        if(i<=j)
          swap(A, i++, j--);
      }
      swap(A,l,j);

      int rlen = r+1-j;
      if(rlen == k) return A.get(j);
      if(rlen > k) l=j+1;
      else{
        k -= rlen;
        r = j-1;
      }
    }
    return -1;
  }

  private static void swap(List<Integer> A, int i, int j){
    int tmp = A.get(i);
    A.set(i, A.get(j));
    A.set(j, tmp);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
