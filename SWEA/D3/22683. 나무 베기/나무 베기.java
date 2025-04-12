// 입력 : 테케, N: 필드 크기, K: 나무 벨 수 있는 횟수
// \ N<=10, K<=5
// G: 이동 가능 영역, T: 이동 불가 나무, X: 시작 위치, Y: 목표 위치
// <최소 리모컨 조작 횟수 출력>
// 이동 불가 시 -1 출력

import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t=1;t<=T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			map = new char[N][N];
			
			int sx = -1, sy = -1;
			
			for (int i=0;i<N;i++) {
				map[i] = br.readLine().toCharArray();
				if (sx==-1) { // 시작 지점 갱신
					for (int j=0;j<N;j++) {
						if (map[i][j]=='X') {
							sx = i;
							sy = j;
							break;
						}
					}
				}
			}
			sb.append("#").append(t).append(" ").append(bfs(sx, sy, K, N)).append("\n");
		}
		System.out.print(sb);
	}
	
	static char[][] map;
	
	static class Node{
		int x, y, dir, k, ct;
		Node(int x, int y, int dir, int k, int ct){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.k = k;
			this.ct = ct;
		}
	}
	
	static final int[] dx = {-1, 0, 1, 0}; // 상우하좌
	static final int[] dy = {0, 1, 0, -1};
	
	static int bfs(int sx, int sy, int K, int N) {

		boolean[][][][] isVisited = new boolean[N][N][4][K+1]; // x,y,방향,사용횟수
		isVisited[sx][sy][0][K] = true;
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(sx, sy, 0, K, 0));
		
		while(!q.isEmpty()) {
			Node c = q.poll();
			
			if (map[c.x][c.y]=='Y') {
				return c.ct;
			}
			
			int nc = c.ct + 1;
			
			int nx = c.x + dx[c.dir];
			int ny = c.y + dy[c.dir];
			
			if (nx>=0 && nx<N && ny>=0 && ny<N) {
				// 단순 전진 (A)
				if (map[nx][ny]!='T' && !isVisited[nx][ny][c.dir][c.k]) {
					isVisited[nx][ny][c.dir][c.k] = true;
					q.offer(new Node(nx, ny, c.dir, c.k, nc));
				}
				// 나무 자른 후 전진
				else if (map[nx][ny]=='T' && c.k>0 && !isVisited[nx][ny][c.dir][c.k-1]) {
					isVisited[nx][ny][c.dir][c.k-1] = true;
					q.offer(new Node(nx, ny, c.dir, c.k-1, nc));
				}
			}
			
			// 좌회전 (L)
			int nd = (c.dir+3)%4;
			if (!isVisited[c.x][c.y][nd][c.k]) {
				isVisited[c.x][c.y][nd][c.k] = true;
				q.offer(new Node(c.x, c.y, nd, c.k, nc));
			}
			// 우회전 (R)
			nd = (c.dir+1)%4;
			if (!isVisited[c.x][c.y][nd][c.k]) {
				isVisited[c.x][c.y][nd][c.k] = true;
				q.offer(new Node(c.x, c.y, nd, c.k, nc));
			}
			
		}
		return -1;
	}
}
