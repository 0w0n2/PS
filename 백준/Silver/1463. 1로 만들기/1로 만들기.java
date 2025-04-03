import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // N을 1로 만들기
		int[] dp = new int[N+1]; // 1부터 N까지 만들어지는 수
		
        // dp[k] = min { dp[k/3]+1 (if x%3==0)
        //             { dp[k/2]+1 (if x%2==0)
        //             { dp[k-1]+1
        
		dp[1] = 0;
		for (int i=2;i<=N;i++) {
			dp[i] = dp[i-1]+1;
			if (i%2==0) dp[i] = Math.min(dp[i], dp[i/2]+1);
			if (i%3==0) dp[i] = Math.min(dp[i], dp[i/3]+1);
		}
		System.out.println(dp[N]);
	}
}