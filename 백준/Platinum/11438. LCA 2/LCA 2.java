import java.io.*;
import java.util.*;

// 미리 2^j 단위 부모 저장해놓고
// depth 다르면 이진수처럼 큰 점프부터 뛰어서 깊이 맞추고
// 같아지면 동시에 위로 점프하면서 공통 조상 찾기

public class Main {
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	static final int MAX = 18; // 보통 18~20 정도 설정한다캄
	
	static int N, M, par[][], depth[]; 
	/**
	 * N : 노드 개수
	 * M : 쿼리 개수
	 * par[i][j] : i의 2^k 번째 부모 정보를 담음
	 * depth[i] : 정점 별 깊이 (루트의 깊이 : 0 -> depth[0]=0)
	 */
	static List<Integer>[] map; // 그래프 입력 정보를 담는 인접 리스트
	
	public static void main(String[] args) throws IOException{
		// (1) 트리 정보 입력
		N = readInt();
		map = new ArrayList[N];
		for (int i=0;i<N;i++) map[i] = new ArrayList<>();
		
		for (int i=0;i<N-1;i++) {
			int u = readInt() - 1;
			int v = readInt() - 1;
			// 무방향 간선 연결
			map[u].add(v);
			map[v].add(u);
		}
		
		// 배열들 초기화
		par = new int[N][MAX];
		for (int i=0;i<N;i++) Arrays.fill(par[i], -1);
		
		depth = new int[N];
		Arrays.fill(depth, -1);
		depth[0] = 0; // 루트 노트(1) 의 깊이는 0
		
		// DFS 로 트리 만들기 (초기 par[i][0] 과 depth[i] 등록)
		makeTreeByDfs(0);
		
		// parent 배열 채우기
		for (int j=0;j<MAX-1;j++) {
			for (int i=1;i<N;i++) {
				// 이전에 계산해둔 2^j 번째 조상을 이용해서 2^(j+1) 번째 조상을 계산 
				// 2^(k+1) = 2^k + 2^k
				// parent[u][k+1] = parent[parent[u][k]][k]
				// 노드 i의 2^(j+1) 번째 위 부모 = 노드 i 의 2^j 번째 위 부모의 2^j 번째 위 부모
				if (par[i][j]!=-1) par[i][j+1] = par[par[i][j]][j];
			}
		}
		
		// 쿼리 입력
		M = readInt();
		StringBuilder sb = new StringBuilder();
		for (int m=0;m<M;m++) {
			int u = readInt() - 1;
			int v = readInt() - 1;
			
			// 항상 u의 높이가 v 보다 같거나 크게 설정 (depth[u]>=depth[v])
			// 그렇지 않을 경우엔 u와 v를 스와핑
			if (depth[u]<depth[v]) {
				int temp = u;
				u = v;
				v = temp;
			}
			
			// 현재 u, v의 깊이 차
			int diff = depth[u] - depth[v];
			
			// 깊이 차(diff)가 없어질 때까지 u 를 한 칸씩 위로 이동시키기
			for (int j=0; diff!=0; j++) {
				if (diff%2!=0) u = par[u][j]; 
				diff /= 2;
			}
			
			// 둘의 깊이 차이가 동일해진 상태 (depth[u]==depth[v])
			// 만약 u와 v가 다르면 동시에 일정 높이만큼 위로 이동시킴
			if (u!=v) {
				// 높이 2^17, 2^16, ... 2^2, 2, 1 순으로 시도
				for (int j=MAX-1; j>=0; j--) {
					if (par[u][j]!=-1 && par[u][j]!=par[v][j]) {
						u = par[u][j];
						v = par[v][j];
					}
				}
				// 위에서 두 정점의 LCA 직전까지 도달함 -> 한 번 더 올림
				u = par[u][0];
			} // else 면 이미 LCA 직전까지 도달한 상태

			// LCA 출력
			sb.append(u+1).append("\n");
		}
		System.out.print(sb);
	}
	
	// 1 - 2 - 3 - 4 - 5 - 6 와 같은 연결 리스트가 있다고 했을 때...
	// parent[4][1] = parent[parent[4][0]][0] = parent[3][0] 
	// 4의 2^1 칸 위 부모 = 3(4의 2^0 칸 위 부모)의 2^0 칸 위 부모
	
	// 왜 2의 거듭제곱을 쓸까? -> 두 노드의 깊이 차를 "이진수"로 표현했을 때
	// 예를 들어 높이 차이가 13이라 하면, 13의 이진수 표현은 1101
	// 즉 8+4+1 칸 점프하면 총 13칸 이동 가능 (13번 1칸씩 가는 것보다 훨씬 빠름(O(N)->O(logN))
	
	
	// DFS 로 트리 제작하며 par[i][0], depth 배열 채움
	// 초기 par[i][0] : 노드 i의 한 칸 위 부모 
	private static void makeTreeByDfs(int curr) {
		for (int next : map[curr]) {
			if (depth[next]==-1) {
				par[next][0] = curr;
				depth[next] = depth[curr]+1;
				makeTreeByDfs(next);
			}
		}
	}
}
