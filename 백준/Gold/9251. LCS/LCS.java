import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		// 문자열 s1의 길이 : m, 문자열 s2의 길이 : n 이라 했을 때 dp[m+1][n+1] 생성
		// dp[i][j] 는 문자열 s1의 0~i번째 문자 영역과 문자열 s2의 0~j번째 문자 영역에서 최대 LCS의 길이를 저장함
		// 초기값 : dp[0][~] = 0, dp[~][0] = 0  (빈문자열과의 비교이므로)
		
		// 만약 s1의 i번째 문자와 s2의 j번째 문자가 같다면 : dp[i][j] = dp[i-1][j-1] + 1 -> LCS 길이가 +1 됐음을 저장
		// 			다르다면 : dp[i][j] = max(dp[i-1][j], dp[i][j-1]) -> 문자 하나 안 쓴 거에서 최댓값으로 저장해줌
		
		int m = s1.length();
		int n = s2.length();
		
		int[][] dp = new int[m+1][n+1];
		
		for (int i=1;i<=m;i++) {
			for (int j=1;j<=n;j++) {
				if (s1.charAt(i-1)==s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		System.out.print(dp[m][n]);
	}
}
