import java.util.*;
import java.io.*;

public class Main {
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
	
	private static class Line implements Comparable<Line>{
		int a, b;
		Line(int a, int b){
			this.a = a;
			this.b = b;
		}
		@Override
		public int compareTo(Line o) {
			return this.a - o.a;
		}
	}
	
	public static void main(String[] args) throws IOException{
		int N = readInt(); // 전깃줄 개수
		Line[] L = new Line[N];
		for (int i=0;i<N;i++) L[i] = new Line(readInt(), readInt());
		Arrays.sort(L);
		
		int[] dp = new int[N]; // 최장증가부분수열 저장
		// dp[n] : 0~n 영역에서 최장 증가 부분 수열의 크기 최댓값을 저장
		Arrays.fill(dp, 1);
		
		int max = 1;
		for (int i=1;i<N;i++) {
			for (int j=0;j<i;j++) {
				if (L[j].b < L[i].b) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			max = Math.max(dp[i], max);
		}
		System.out.println(N-max);
	}
}
