import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 색상환에 포함된 색의 개수
		int K = Integer.parseInt(br.readLine()); // 선택할 개수
		
		int x = 1_000_000_003;
		
		// 1. dp 배열 초기화 
		int[][] dp = new int[N+1][K+1];
		for (int i=0;i<=N;i++) {
			dp[i][1] = i; // i개의 색 중 1개의 색을 고르는 경우 (i개 중에서 1개 = i가지 경우)
			dp[i][0] = 1; // i개의 색 중 0개의 색을 고르는 경우 : 항상 1 (아무것도 선택 x)
		}
		
		// 2. 바텀업 방식으로 i(2~N)개의 색상에서 j(2~K)개를 고르는 경우의 수를 구함
		for (int j=2;j<=K;j++) {
			for (int i=2;i<=N;i++) {
				dp[i][j] = (dp[i-2][j-1] + dp[i-1][j])%x; 
				// i개의 숫자에서 j개를 고르는 경우의 수 : i번째 숫자를 고르는 경우의 수 + i번째 숫자를 고르지 않는 경우의 수
			}
		}
		
		System.out.println((dp[N-1][K] + dp[N-3][K-1])%x);
		//				 1번 칸을 칠하지 않은 경우 + 1번 칸을 칠한 경우
		// dp[N-1][K] : 1번 칸을 칠하지 않은 경우 -> 2번부터 ~ N번까지(총 N-1개 중) 총 K개를 고르는 경우의 수
		// dp[N-3][K-1] : 1번 칸을 칠한 경우 -> 3번부터 ~ N-1번까지(총 N-3개 중) 총 K-1개를 고르는 경우의 수
	}
}
