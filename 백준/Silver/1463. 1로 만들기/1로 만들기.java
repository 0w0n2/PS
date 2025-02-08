import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [] dp = new int[1_000_001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0; // baseCase
        for (int i=2;i<=n;i++){ // 바텀업
            dp[i] = dp[i-1]+1;
            if(i%2==0) dp[i] = Math.min(dp[i/2]+1, dp[i]);
            if(i%3==0) dp[i] = Math.min(dp[i/3]+1, dp[i]);
        }
        System.out.print(dp[n]);
    }
}
