import java.util.*;
import java.io.*;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
	
	private static class Edge{
		int weight, value;
		Edge(int weight, int value){ // 물건 별 (무게, 가치)
			this.weight = weight; 
			this.value = value;
		}
	}
	
	// N개의 물건이 있는데 물건마다 (W(무게), V(가치)) 값을 가짐
	// 물건 중복 선택 불가능
	// 최대 K 무게를 넘기지 않는 선에서 V(가치)의 총합이 최대가 되는 경우 찾기
	
	public static void main(String[] args) throws IOException{
		int N = readInt(); // 물품 수
		int K = readInt(); // 버틸 수 있는 무게, K
		
		Edge[] product = new Edge[N];
		for (int i=0;i<N;i++) product[i] = new Edge(readInt(), readInt()); // 물건별 무게, 가치 입력
		
		int[] dp = new int[K+1]; // 어떤 물건들을 조합해서 만들 수 있는 무게 별 가치 최대 값 저장
		for (int i=0;i<N;i++) {
			int v = product[i].value;
			int w = product[i].weight;
			
			for (int weight=K; weight-w>=0;weight--) {
				dp[weight] = Math.max(dp[weight], dp[weight-w]+v);
			}
		}
		System.out.println(dp[K]);
	}
}
