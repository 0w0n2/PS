import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int x, y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static int N, M, map[][], cellCt = 0;
	static Deque<Node> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로 크기
		M = Integer.parseInt(st.nextToken()); // 가로 크기
		map = new int[N][M];
		
		
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==2) q.offerLast(new Node(i, j));
				else if (map[i][j]==0) cellCt++;
			}
		}
		setWall(0, 0, 0);
		System.out.println(maxSafeArea);
	}
	
	static int maxSafeArea = 0;
	
	static void setWall(int ct, int si, int sj) { // 벽 3개를 세우는 조합
		
		if (ct==3) {
			maxSafeArea = Math.max(maxSafeArea, bfsVirus());
			return;
		}
		
		for (int i=si;i<N;i++) {
			for (int j=(i==si) ? sj:0;j<M;j++) {
				if (map[i][j]==0) {
					map[i][j]=1;
					setWall(ct+1, (j==M-1) ? i+1:i, (j+1)%M);
					map[i][j]=0;	// 백트래킹
				}
			}
		}
	}
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	static int bfsVirus() {
		int[][] tempMap = new int[N][M];
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				tempMap[i][j] = map[i][j];
			}
		}
		Deque<Node> tempQ = new ArrayDeque<>(q);
		
		int ct = cellCt - 3;
		
		while(!tempQ.isEmpty()) {
			Node current = tempQ.pollFirst();
			for (int i=0;i<4;i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				if (nx<0||nx>=N||ny<0||ny>=M||tempMap[nx][ny]!=0) continue;
				tempMap[nx][ny] = 2;
				ct--;
				tempQ.offerLast(new Node(nx, ny));
			}
		}
		return ct;
	}
	
}
