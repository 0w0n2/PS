import java.util.*;
import java.io.*;

/**
 * 백준 1932 - 정수 삼각형
 * - 최상단 층을 1층이라 했을 때, 층에 포함되는 숫자 개수는 층 숫자와 같음
 * - 아래 층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 오른쪽만 선택 가능하다 
 * 		- 즉, (2,1) (2층의 1번 숫자) 를 골랐으면 3층에서는 (3,1) 또는 (3,2)를 선택 가능하다 <자신과 동일한 열이거나, 열+1>
 * 		- 합이 최대가 되는 경로를 찾기 위해 dp 점화식을 작성하면
 * 			> dp[0][0] = map[0][0],
 * 			> dp[i][j] = 	dp[i-1][0] + map[i][0] (j==0 일 때)
 * 							dp[i-1][i-1] + map[i][i] (j==i 일 때)
 * 							max(dp[i-1][j], dp[i-1][j-1]) + map[i][j] (otherwise) 
 * 
 * @author 0w0n
 */

public class Main {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 삼각형의 크기(층의 높이)
		
		int[][] map = new int[N][N];
		
		StringTokenizer st;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<=i;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N][N];
		dp[0][0] = map[0][0]; // 제일 꼭대기 층
		
		for (int i=0;i<N-1;i++) {	// 바텀업
			for (int j=0;j<=i;j++) {
				dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + map[i+1][j]);
				dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + map[i+1][j+1]);
			}
		}
		
		int maxSum = 0;
		for (int i=0;i<N;i++) maxSum = Math.max(maxSum, dp[N-1][i]);
		System.out.print(maxSum);
	}
}
