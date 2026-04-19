import java.util.*;

class Solution {
    public int solution(int []A, int []B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int sum = 0;
        for (int a=0; a<A.length; a++) {
            sum += (A[a] * B[A.length-a-1]);
        }

        return sum;
    }
    
}