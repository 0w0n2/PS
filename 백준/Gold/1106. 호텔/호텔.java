import java.util.*;
import java.io.*;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
	
	private static class Edge{
		int V, P; // 홍보 비용(V), 사람 수(P)
		Edge(int V, int P){
			this.V = V;
			this.P = P;
		}
	}
	
	public static void main(String[] args) throws IOException{
		int C = readInt(); // 목표 인원
		int N = readInt(); // 도시의 개수
		
		Edge[] info = new Edge[N];
		for (int i=0;i<N;i++) {
			info[i] = new Edge(readInt(), readInt()); // "홍보 비용" + "얻을 수 있는 고객의 수" 정보 입력
		}
		
		int[] dp = new int[C+1_000];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i=0;i<N;i++) {
			int people = info[i].P;
			int cost = info[i].V;
			// i번째 도시 선택
			
			for (int j=people;j<dp.length;j++) {
				if (dp[j-people]!=Integer.MAX_VALUE) { // [j-people] -> 정수배로 증가하느 거 보장하며 여러 조합을 가능하게 함
					dp[j] = Math.min(dp[j], dp[j-people]+cost); 
					// dp[j] = j명을 얻기 위한 최소 비용
					// dp[j-people] = j명보다 people 명 적은 수의 최소 비용
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i=C;i<dp.length;i++) min = Math.min(min, dp[i]);
		System.out.print(min);
	}
}
