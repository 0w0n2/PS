import java.util.*;
import java.io.*;

public class Main {
	
	static int n, k;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int[][] dp = new int[k+1][n+1]; // k개를 사용해서 n을 만드는 개수
		for (int i=0;i<=k;i++) dp[i][0] = 1;
		
		for (int i=1;i<=k;i++) {
			for (int j=1;j<=n;j++) {
				dp[i][j] = (dp[i-1][j] + dp[i][j-1])%1_000_000_000;
			}
		}
		System.out.print(dp[k][n]);
	}

}
