import java.util.*;
import java.io.*;

public class Main {
	
	static class Node{
		int x, y, wall;
		Node(int x, int y, int wall){
			this.x = x;
			this.y = y;
			this.wall = wall;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // 가로 크기(열)
		int N = Integer.parseInt(st.nextToken()); // 세로 크기(행)
		// (0, 0) -> (N-1, M-1) 로 가는 최단 경로
		
		int[][] map = new int[N][M];
		for (int i=0;i<N;i++) map[i] = br.readLine().chars().map(c -> c-'0').toArray();
		
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		boolean[][] isVisited = new boolean[N][M];
		
		Deque<Node> dq = new LinkedList<>();
		dq.addFirst(new Node(0, 0, 0));
		isVisited[0][0] = true;
		
		while(!dq.isEmpty()) {
			Node current = dq.poll();
			
			if(current.x==(N-1) && current.y==(M-1)) {
				System.out.print(current.wall);
				return;
			}
			
			for (int i=0;i<4;i++) {
				int nx = dx[i] + current.x;
				int ny = dy[i] + current.y;
				
				if(nx<0 || nx>=N || ny<0 || ny>=M || isVisited[nx][ny]) continue;
				
				isVisited[nx][ny] = true; // 방문 처리
				if(map[nx][ny]==0) dq.addFirst(new Node(nx, ny, current.wall)); // 우선으로 넣기(벽 안 부숨)
				else dq.addLast(new Node(nx, ny, current.wall + map[nx][ny]));
			}
		}
	}
}