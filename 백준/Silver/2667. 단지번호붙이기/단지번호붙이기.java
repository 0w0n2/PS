import java.util.*;
import java.io.*;

class Main
{	
	static int [][] map;
	static boolean [][] isVisited;
	static int n;
	static int []dx = {0, 0, -1, 1};
	static int []dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		isVisited = new boolean[n][n];
		for (int i=0;i<n;i++) {
			String ip = br.readLine();
			for (int j=0;j<n;j++) {
				map[i][j] = (int)(ip.charAt(j)-'0');
			}
		} // 입력 완료
		
		PriorityQueue<Integer> area = new PriorityQueue<>();
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				if (map[i][j]==1 && !isVisited[i][j]) {
					area.offer(dfs(i, j));
				}
			}
		}
		
		System.out.println(area.size());
		while(!area.isEmpty()) System.out.println(area.poll());
		
	}
	public static int dfs(int x, int y) {
		isVisited[x][y] = true;
		int area = 1;
		
		for (int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx<0 || nx>=n || ny<0 || ny>=n || map[nx][ny]==0 || isVisited[nx][ny]) continue;
			area += dfs(nx, ny);
		}
		return area;
	}
	
}

