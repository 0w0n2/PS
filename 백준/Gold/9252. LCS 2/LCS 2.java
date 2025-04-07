import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		
		int lenA = A.length();
		int lenB = B.length();
		int[][] dp = new int[lenA+1][lenB+1];
		
		// LCS 구하기
		for (int i=1;i<=lenA;i++) {
			for (int j=1;j<=lenB;j++) {
				char a = A.charAt(i-1);
				char b = B.charAt(j-1);
				if (a==b) dp[i][j] = dp[i-1][j-1]+1;
				else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		
		// 문자열 복구하기
		StringBuilder sb = new StringBuilder();
		int i = lenA;
		int j = lenB;
		while(i>0 && j>0) {
			char a = A.charAt(i-1);
			char b = B.charAt(j-1);
			if (a!=b) {
				if (dp[i-1][j]>dp[i][j-1]) i--;
				else j--;
			} else {
				sb.append(a);
				i--;
				j--;
			}
		}
		System.out.printf("%s\n%s", dp[lenA][lenB], sb.reverse());
 	}
}
