import java.util.*;
import java.io.*;

public class Main {
	private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException{
		N = readInt();
		costs = new int[N][3];
		
		int[][] dp = new int[N][3];
		for (int i=0;i<N;i++) {
			for (int j=0;j<3;j++) {
				costs[i][j] = readInt();
			}
		}
		
		for (int i=0;i<3;i++) {
			dp[0][i] = costs[0][i];
		}
		
		for (int i=1;i<N;i++) {
			for (int j=0;j<3;j++) {
				dp[i][j] = Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]) + costs[i][j];
			}
		}
		
		int result = Integer.MAX_VALUE;
		for (int i=0;i<3;i++) {
			result = Math.min(dp[N-1][i], result);
		}
		System.out.print(result);
	}
	
	private static int N, costs[][];

}
