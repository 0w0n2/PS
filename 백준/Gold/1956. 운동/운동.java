import java.util.*;
import java.io.*;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException{
		
		// 10_000 * 400 = 4_000_000
		int INF = 1_000_000_000;
		
		int V = readInt(); // 마을 수
		int E = readInt(); // 도로 수
		
		int[][] dp = new int[V][V];
		for (int i=0;i<V;i++) Arrays.fill(dp[i], INF);
		
		for (int i=0;i<E;i++) {
			int a = readInt()-1;
			int b = readInt()-1;
			dp[a][b] = Math.min(dp[a][b], readInt());
		}
		
		for (int k=0;k<V;k++) for (int i=0;i<V;i++) for (int j=0;j<V;j++) {
			dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k][j]);
		}
		
		int ans = INF;
		for (int i=0;i<V;i++) ans = Math.min(ans, dp[i][i]);
		System.out.print(ans==INF?-1:ans);
	}
}
