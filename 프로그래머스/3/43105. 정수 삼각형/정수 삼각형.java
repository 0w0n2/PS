import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        
        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];
        
        for (int r=1; r<n; r++) {
            for (int c=0; c<=r; c++) {
                dp[r][c] = triangle[r][c];
                if (c==0) {
                    dp[r][c] += dp[r-1][c];
                } else if (c==r) {
                    dp[r][c] += dp[r-1][c-1];
                } else {
                    dp[r][c] += Math.max(dp[r-1][c-1], dp[r-1][c]); // 왼쪽 이동, 오른쪽 이동
                }
            }
        }
        
        int ans = 0;
        for (int d : dp[n-1]) {
            ans = Math.max(ans, d);
        }
        
        return ans;
    }
}