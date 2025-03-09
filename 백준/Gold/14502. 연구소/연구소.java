import java.util.*;
import java.io.*;

/*
 * 백준 14502 연구소
 * JAVA11 : 메모리 90692 KB, 시간 372ms
 * [문제 풀이 과정]
 * 1) 입력 받은 지도에 벽 3개를 세우는 경우를 조합으로 구함
 * 2) (1)에서 세운 벽 지도로부터 지도에 바이러스가 퍼진 후 안전 영역이 얼마나 남는지 체크
 * - 바이러스 퍼지는 건 초기 입력 때 바이러스 위치를 담고 있는 초기 큐를 만들고, 바이러스가 상하좌우로 움직이도록 bfs로 구현
 */

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
		isVisited = new boolean[N][M];
		
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
		
		if (ct==3) {	// 벽 3개를 세운 이후엔 bfs로 최대 안전영역을 체크함
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
	
	static boolean[][] isVisited; // bfs용 방문 배열
	static Deque<Node> tempQ; // 초기 큐(입력으로 들어온 바이러스의 좌표를 담고 있는) 복사
	
	static int bfsVirus() {
		
		for (int i=0;i<N;i++) Arrays.fill(isVisited[i], false);		
		tempQ = new ArrayDeque<>(q); // 초기 큐(입력으로 들어온 바이러스의 좌표를 담고 있는) 복사
		
		int ct = cellCt - 3; // 빈 방 개수 - 3 (조합에서 세운 벽 3개) : 현재 빈 방 개수
		
		while(!tempQ.isEmpty()) {
			
			Node current = tempQ.pollFirst();
			
			for (int i=0;i<4;i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				if (nx<0||nx>=N||ny<0||ny>=M||isVisited[nx][ny]||map[nx][ny]!=0) continue;
				isVisited[nx][ny] = true;
				ct--; // 바이러스에 의해서 빈방-> 바이러스 될 때마다 빈방 개수 -1
				tempQ.offerLast(new Node(nx, ny));
			}
		}
		
		return ct; // 남은 빈방(안전 영역)을 리턴
	}
	
}
