import java.util.*;
import java.io.*;

public class Main {
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException{
		int n = readInt(); // n가지 종류의 동전
		int k = readInt(); // 목표 금액
		
		int[] cost = new int[n];
		for (int i=0;i<n;i++) cost[i] = readInt();
		
		int[] dp = new int[k+1];
		dp[0] = 1;
		for (int i=0;i<n;i++) {
			int c = cost[i];
			
			for (int nowCost=c; nowCost<=k; nowCost++) {
				dp[nowCost] += dp[nowCost-c];
			}
		}
		System.out.println(dp[k]);
	}
}
