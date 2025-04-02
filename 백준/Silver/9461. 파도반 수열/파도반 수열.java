import java.util.*;
import java.io.*;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException{
		int T = readInt(); // 테스트 케이스 개수
		
		StringBuilder sb = new StringBuilder(); // 출력 용
		for (int t=0;t<T;t++) {
			int N = readInt(); // P(N) 구하기
			// P(N) = 나선에 있는 정삼각형의 변의 길이
			// P(1) = 1
			// P(2) = 1
			// P(3) = 1
			// P(4) = 2
			// P(5) = 3
			// ... 규칙 : P(i) = P(i-2)+P(i-3)
			// 다이나믹 프로그래밍으로 풀이
			
			long[] dp = new long[N];
			
			dp[0] = 1;
			if (N>1) dp[1] = 1;
			if (N>2) dp[2] = 1;

			for (int i=3;i<N;i++) {
				dp[i] = dp[i-2]+dp[i-3];
			}
			sb.append(dp[N-1]).append("\n");
		}
		System.out.print(sb);
	}
}
