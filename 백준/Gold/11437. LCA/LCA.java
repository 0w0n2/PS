import java.util.*;
import java.io.*;

public class Main {
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	static final int MAX = 18;
	
	static int parent[][]; // parent[i[[k] : i의 2^k 번째 부모
	static int depth[]; // 정점의 깊이 (루트의 깊이:0)
	static List<Integer>[] map; // 인접 리스트
	
	public static void main(String[] args) throws IOException{
		
		// 트리 정보 입력
		int N = readInt(); 
		
		map = new ArrayList[N];
		for (int i=0;i<N;i++) map[i] = new ArrayList<>();
		
		for (int i=0;i<N-1;i++) {
			int u = readInt()-1;
			int v = readInt()-1;
			map[u].add(v); // 양방향(무방향) 간선
			map[v].add(u);
		}
		
		// 배열 초기화
		parent = new int[N][MAX];
		for (int i=0;i<N;i++) Arrays.fill(parent[i], -1);
		depth = new int[N];
		Arrays.fill(depth, -1);
		depth[0] = 0;
		
		// 트리 만들기
		makeTreeByDfs(0); // 루트 노드는 항상 1
		
		// parent 배열 채우기
		for (int j=0;j<MAX-1;j++) {
			for (int i=1;i<N;i++) {
				if (parent[i][j]!=-1) {
					parent[i][j+1] = parent[parent[i][j]][j]; 
				}
			}
		}
		
		// 쿼리 입력 받기
		StringBuilder sb = new StringBuilder();
		int M = readInt();
		for (int i=0;i<M;i++) {
			int u = readInt()-1;
			int v = readInt()-1;
			
			// 항상 depth[u] >= depth[v] 가 되도록 필요에 따라 u, v를 스왑
			if (depth[u] < depth[v]) {
				int temp = u;
				u = v;
				v = temp;
			}
			int diff = depth[u] - depth[v];
			
			// 깊이 차(diff) 를 없애며 u 를 이동시킴
			for (int j=0; diff!=0; j++) {
				if (diff%2!=0) u = parent[u][j];
				diff /= 2;
			}
			
			// u와 v가 다르면 동시에 일정 높이만큼 위로 이동시킴
			if (u!=v) {
				// 높이 2^17, 2^16, ... 2^2, 2, 1 순으로 시도
				for (int j=MAX-1; j>=0; j--) {
					if (parent[u][j]!=-1 && parent[u][j]!=parent[v][j]) {
						u = parent[u][j];
						v = parent[v][j];
					}
				}
				// 마지막엔 두 정점 u, v 의 부모가 같으므로 한 번 더 올림
				u = parent[u][0];
			}
			
			// LCA 출력
			sb.append(u+1).append("\n");
		}
		System.out.print(sb);
	}
	
	// dfs 로 트리 제작하며 parent[i][0], depth 배열 채움
	private static void makeTreeByDfs(int curr) {
		for (int next:map[curr]) {
			if (depth[next]==-1) {
				parent[next][0] = curr;
				depth[next] = depth[curr]+1;
				makeTreeByDfs(next);
			}
		}
	}
}
