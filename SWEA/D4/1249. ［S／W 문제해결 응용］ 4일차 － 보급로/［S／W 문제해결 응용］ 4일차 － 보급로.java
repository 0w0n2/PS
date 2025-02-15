import java.util.*;
import java.io.*;

public class Solution {

	static class Node{
		int x, y, time;
		Node(int x, int y, int time){
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	static StringBuilder sb = new StringBuilder();
	
	static int field[][] = new int [101][101];
	static int dist[][] = new int[101][101];
	static int N;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		
		for (int t=1; t<=tc; t++) {
			N = Integer.parseInt(br.readLine()); // 지도의 크기

			for (int i=0;i<N;i++) {
				char[] temp = br.readLine().toCharArray();
				for (int j=0;j<N;j++) {
					field[i][j] = temp[j] - '0';
				}
			}
			
			for (int i=0;i<N;i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
			dist[0][0] = 0; // 시작 지점
			dijkstra(t);
		}
		
		System.out.print(sb);
	}
	
	public static void dijkstra(int t) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.time - o2.time; // 최단 시간 순(오름차순)
			}
		});
		
		pq.offer(new Node(0, 0, 0)); // 시작 지점 넣기
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if(dist[current.x][current.y]!=current.time) continue; // 무효값은 무시
			
			if(current.x==(N-1) && current.y==(N-1)) { // 도착
				sb.append("#").append(t).append(" ").append(dist[N-1][N-1]).append("\n");
				return; // 탐색 종료
			}
			
			for (int i=0;i<4;i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if (nx<0 || nx>=N || ny<0 || ny>=N) continue; // 배열 범위 out 무시
				
				int nt = current.time + field[nx][ny];
				if(nt<dist[nx][ny]) {
					dist[nx][ny] = nt; // 더 최소 시간으로 교체
					pq.offer(new Node(nx, ny, nt));
				}
				
			}
		}
		
	}
}