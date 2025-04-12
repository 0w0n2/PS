import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t=1;t<=10;t++) {
			br.readLine();
			int sx = -1, sy = -1; // 시작 지점
			for (int i=0;i<16;i++) {
				String line = br.readLine();
				for (int j=0;j<16;j++) {
					map[i][j] = line.charAt(j)-'0';
					if(sx==-1 && map[i][j]==2) {
						sx = i;
						sy = j;
					}
				}
			}
			sb.append("#").append(t).append(" ").append((bfs(sx, sy) ? "1\n":"0\n"));
		}
		System.out.print(sb);
	}
	
	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {-1, 1, 0, 0};
	
	static int map[][] = new int[16][16];
 	
	static class Node{
		int x, y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static boolean bfs(int sx, int sy) {
		boolean[][] isVisited = new boolean[16][16];
		isVisited[sx][sy] = true;
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(sx, sy));
		
		while(!q.isEmpty()) {
			Node c = q.poll();
			if (map[c.x][c.y]==3) return true;
			
			for (int i=0;i<4;i++) {
				int nx = c.x + dx[i];
				int ny = c.y + dy[i];
				if (nx<0||nx>=16||ny<0||ny>=16||isVisited[nx][ny]||map[nx][ny]==1) continue;
				isVisited[nx][ny] = true;
				q.offer(new Node(nx, ny));
			}
		}
		
		return false;
	}
}
