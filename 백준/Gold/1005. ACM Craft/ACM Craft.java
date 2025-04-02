import java.util.*;
import java.io.*;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	private static long readLong() throws IOException{
		st.nextToken();
		return (long) st.nval;
	}
	
	public static void main(String[] args) throws IOException{
		int T = readInt(); // 테스트 케이스 개수
		
		StringBuilder sb = new StringBuilder();
		for (int t=0;t<T;t++) {
			int N = readInt(); // 건물의 개수(노드)
			int K = readInt(); // 건설 순서(정렬 정보 개수)
			
			int[] time = new int[N]; // 건설 시간
			for (int i=0;i<N;i++) {
				time[i] = readInt();
			}
			
			int[] inDegree = new int[N];
			ArrayList<ArrayList<Integer>> map = new ArrayList<>();
			for (int i=0;i<N;i++) map.add(new ArrayList<>());
 			
			for (int i=0;i<K;i++) {
				int v = readInt()-1;
				int u = readInt()-1;
				
				map.get(v).add(u);
				inDegree[u]++;
			}
			
			int[] dp = new int[N];
			Deque<Integer> q = new ArrayDeque<>();
			for (int i=0;i<N;i++) {
				dp[i] = time[i];
				if (inDegree[i]==0) q.offer(i);
			}
			
			int target = readInt();

			while(!q.isEmpty()) {
				int c = q.pollFirst();

				for (int np : map.get(c)) {
					dp[np] = Math.max(dp[np], dp[c]+time[np]);
					if (--inDegree[np]==0) {
						q.offer(np);
					}
				}
			}
			System.out.println(dp[target-1]);
		}
	}
}
