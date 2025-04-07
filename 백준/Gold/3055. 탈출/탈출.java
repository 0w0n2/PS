import java.util.*;
import java.io.*;

// 레벨 별 BFS 탐색
/**
 * 
 */

public class Main {
	
	private static class Cor{
		int x, y;
		Cor(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// R*C 크기 지도
		
		Cor gosum = new Cor(0, 0), beaver = new Cor(0, 0); // 고슴도치 시작 위치 & 비버네 집
		Queue<Cor> water = new ArrayDeque<>(); // 물 퍼트리기 용 큐
		
		map = new char[R][C];
		for (int i=0;i<R;i++) {
			String line = br.readLine();
			for (int j=0;j<C;j++) {
				map[i][j] = line.charAt(j);
				switch(map[i][j]) {
					case 'S' : 
						gosum = new Cor(i, j);
						break;
					case 'D' : 
						beaver = new Cor(i, j);
						break;
					case '*' : 
						water.add(new Cor(i, j));
						break;
				}
			}
		}
		bfs(gosum, beaver, water);
		
	}
	static char[][] map;
	static int R, C;
	
	private static void bfs(Cor gosum, Cor beaver, Queue<Cor> water) {
		// 방향 델타
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		
		boolean[][] isVisited = new boolean[R][C];
		
		Queue<Cor> gogosum = new ArrayDeque<>(); // 고슴도치 이동 큐
		gogosum.add(gosum);
		isVisited[gosum.x][gosum.y] = true;
		
		// 레벨 별 BFS 탐색
		int time = 0;
		while(!gogosum.isEmpty()) {
			time++;
			
			// 물 퍼짐
			int wsize = water.size();
			while(wsize-- != 0) {
				Cor c = water.poll();
				for (int i=0;i<4;i++) {
					int nx = c.x + dx[i];
					int ny = c.y + dy[i];
					if (nx<0||nx>=R||ny<0||ny>=C||map[nx][ny]!='.') continue;
					map[nx][ny] = '*';
					water.offer(new Cor(nx, ny));
				}
			}
			
			// 고슴도치 이동
			int gsize = gogosum.size();
			while(gsize-- != 0) {
				Cor c = gogosum.poll();
				for (int i=0;i<4;i++) {
					int nx = c.x + dx[i];
					int ny = c.y + dy[i];
					if (nx<0||nx>=R||ny<0||ny>=C||isVisited[nx][ny]) continue;
					if (map[nx][ny]=='D') {
						System.out.print(time);
						return;
					}
					if (map[nx][ny]!='.') continue;
					
					isVisited[nx][ny] = true;
					gogosum.offer(new Cor(nx, ny));
				}
			}
		}
		System.out.print("KAKTUS");
	}
}
