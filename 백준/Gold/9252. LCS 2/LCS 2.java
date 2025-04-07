import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] A = br.readLine().toCharArray();
		char[] B = br.readLine().toCharArray();
		
		int lenA = A.length;
		int lenB = B.length;
		int[][] dp = new int[lenA+1][lenB+1];
		
		// LCS 구하기
		for (int i=1;i<=lenA;i++) {
			for (int j=1;j<=lenB;j++) {
				if (A[i-1]==B[j-1]) dp[i][j] = dp[i-1][j-1]+1;
				else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		
		// 문자열 복구하기
		StringBuilder sb = new StringBuilder();
		int i = lenA, j = lenB;
		while(i>0 && j>0) {
			if (A[i-1]!=B[j-1]) {
				if (dp[i-1][j]>dp[i][j-1]) i--;
				else j--;
			} else {
				sb.append(A[i-1]);
				i--;
				j--;
			}
		}
		System.out.println(dp[lenA][lenB]);
		System.out.println(sb.reverse());
 	}
}
