import java.io.*;

public class Main {
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
	
	private static class Edge{
		int T, P;
		Edge(int T, int P){
			this.T = T;
			this.P = P;
		}
	}
	
	public static void main(String[] args) throws IOException{
		// 입력 받기
		int N = readInt();
		Edge[] plan = new Edge[N];
		for (int i=0;i<N;i++) plan[i] = new Edge(readInt(), readInt());
		
		// DP 풀이
		int[] dp = new int[N+1];
		for (int i=0;i<N;i++) {
			dp[i+1] = Math.max(dp[i+1], dp[i]); // 상담 X
			
			// 상담 O
			Edge c = plan[i];
			if (i+c.T<=N) dp[i+c.T] = Math.max(dp[i+c.T], dp[i]+c.P);
		}
		
		// 최대값 구하기
		System.out.print(dp[N]);
	}
}
