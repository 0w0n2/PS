import java.util.*;

class Solution {
    public int solution(int n) {
        int[] f = new int[n+1];
        f[0] = 0;
        f[1] = 1;
        
        final int MOD = 1_234_567;
        
        for (int i=2; i<=n; i++) {
            f[i] = (f[i-1] + f[i-2]) % MOD;    
        }
        
        return f[n];
    }
}