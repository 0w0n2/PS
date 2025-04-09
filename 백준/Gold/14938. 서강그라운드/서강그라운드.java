import java.util.*;
import java.io.*;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException{
		int n = readInt(); // 지역 개수
		int m = readInt(); // 수색 범위
		int r = readInt(); // 길의 개수
		
		int[] item = new int[n];
		for (int i=0;i<n;i++) item[i] = readInt();
		
		int INF = 1_000_000_000;
		int[][] dist = new int[n][n];
		for (int i=0;i<n;i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0; // 자기자신
		}
		
		for (int i=0;i<r;i++) {
			int a = readInt()-1;
			int b = readInt()-1;
			int len = readInt();
			// 양방향 간선
			dist[a][b] = Math.min(dist[a][b], len);
			dist[b][a] = Math.min(dist[b][a], len);
		}
		
		// 플로이드 워셜 처리로 모든 쌍의 최단거리 구하기
		for (int k=0;k<n;k++) for (int i=0;i<n;i++) for (int j=0;j<n;j++) {
			dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
		}
		
		// 각 i마다 j까지 가는 거리가 m 이하라면, 갈 수 있는 경로이고, j지역의 아이템 카운팅 해줌
		int maxItem = 0;
		for (int i=0;i<n;i++) {
			int curItem = 0; // item[i] 안 해줘도 되는 이유가 밑에 for(j) 에서 어짜피 더해짐 (0<=m)=true 라서
			for (int j=0;j<n;j++) { 
				if (dist[i][j]<=m) curItem += item[j];
			}
			maxItem = Math.max(maxItem, curItem);
		}
		System.out.print(maxItem);
	}
}
