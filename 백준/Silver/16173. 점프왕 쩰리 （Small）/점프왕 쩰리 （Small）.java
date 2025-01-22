import java.util.*;
import java.io.*;

class Main
{	
	static int n;
	static int [][] gameMap;
	static int [] dx = {+1, 0}; // 오른쪽 이동, 아래쪽 이동
	static int [] dy = {0, +1};
	static boolean [][] isVisited;
	static boolean result = false;
	 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 게임 구역의 크기 (n*n)
		gameMap = new int [n][n];
		isVisited = new boolean [n][n];
		for (int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0;j<n;j++) {
				gameMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);
		if (result) System.out.println("HaruHaru");
		else System.out.println("Hing");
	}
	
	public static void dfs(int x, int y) {
		isVisited[x][y] = true;
		if (gameMap[x][y]==-1) {
			result = true;
			return;
		}
		
		for (int i=0;i<2;i++) {
			int nx = x + dx[i]*gameMap[x][y];
			int ny = y + dy[i]*gameMap[x][y];
			if (nx<0 || nx>=n || ny<0 || ny>=n) continue;
			if (!isVisited[nx][ny]) {
				dfs(nx, ny);
			}
		}
	
		return;
	}
}

