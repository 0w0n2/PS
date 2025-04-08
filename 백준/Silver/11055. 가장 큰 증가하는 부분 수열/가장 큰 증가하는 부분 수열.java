import java.util.*;
import java.io.*;

public class Main {
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
	
	// 단순 DP로 풀어보기 (O(N^2))
	public static void main(String[] args) throws IOException{
		int N = readInt(); // 수열 A의 크기
		int[] arr = new int[N];
		int[] dp = new int[N];
		for (int i=0;i<N;i++) {
			arr[i] = dp[i] = readInt();
		}
		
		// DP로 풀이
		int max = dp[0];
		for (int i=1;i<N;i++) {
			for (int j=0;j<i;j++) {
				if (arr[j]<arr[i]) {
					dp[i] = Math.max(dp[i], dp[j]+arr[i]);
				}
			}
			max = Math.max(max, dp[i]);
		}
		System.out.print(max);
  	}
}
