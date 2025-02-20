import java.io.*;
import java.util.*;

/*
 * 모든 정점 쌍(u, v)에 대해 어떤 정점 a를 중간 정점으로 해서 최단 경로를 갱신할 수 있는지? -> dp를 활용
 * -> u->v 까지의 거리보다 u->a->v가 더 짧으면 최단 경로를 후자로 갱신
 */
public class Main {
	
	public static void main(String[] args) throws IOException{
		int INF = 1000000000;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 도시 개수(노드)
		int m = Integer.parseInt(br.readLine()); // 버스의 개수(간선)
		// 모든 (i, j)쌍에 대하여 최소 비용 경로 계산
		int[][] dist = new int [n+1][n+1];
		for (int i=1;i<=n;i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0; // 같은 정점끼리의 이동 거리는 0
		}

		StringTokenizer st;
		for (int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			dist[u][v] = Math.min(dist[u][v], w);
		}
		
		// 플로이드-워셜
		for (int a=1; a<=n; a++) {
			for (int i=1;i<=n;i++) for (int j=1;j<=n;j++) {
					if (dist[a][j]!=INF && dist[i][a]!=INF) {
						dist[i][j] = Math.min(dist[i][j], dist[a][j] + dist[i][a]); // 갱신
					}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1;i<=n;i++) {
			for (int j=1;j<=n;j++) {
				sb.append(dist[i][j]==INF ? 0:dist[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}