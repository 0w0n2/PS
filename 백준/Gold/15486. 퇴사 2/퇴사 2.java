import java.util.*;
import java.io.*;

public class Main {

	public static void main (String [] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int [][] plan = new int[n+1][2];
		
		int [] dp = new int[n+1];
		
		StringTokenizer st;
		for (int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			plan[i][0] = Integer.parseInt(st.nextToken()); // T_i (소요 시간)
			plan[i][1] = Integer.parseInt(st.nextToken()); // P-i (이익)
		}
		
		// 바텀업
		for (int i=1;i<=n;i++) {
			dp[i] = Math.max(dp[i], dp[i-1]);
			if (i+plan[i][0]-1<=n) dp[i+plan[i][0]-1] = Math.max(dp[i+plan[i][0]-1], dp[i-1] + plan[i][1]);
		}
		int max = 0;
		for (int i=1;i<=n;i++) max = Math.max(max,  dp[i]);
		System.out.print(max);
	}
}
