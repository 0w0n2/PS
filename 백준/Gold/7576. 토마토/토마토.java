import java.util.*;
import java.io.*;

public class Main {
	
	private static Queue<int[]> q = new ArrayDeque<>();
	private static int map[][], N, M, unripeCt = 0;
	
	public static void main(String[] args) throws IOException{
		
		M = readInt();
		N = readInt();
		
		map = new int[N][M];
		
		for (int n=0;n<N;n++) {
			for (int m=0;m<M;m++) {
				map[n][m] = readInt();
				if (map[n][m] == 1) {
					q.offer(new int[] {n, m});
				} else if (map[n][m] == 0) {
					unripeCt++;
				}
			}
		}
		
		System.out.print((unripeCt == 0) ? 0 : simulate());
	}
	
	private static int simulate() {	
		
		final int[] dx = new int[] {-1, 1, 0, 0};
		final int[] dy = new int[] {0, 0, -1, 1};
		int day = -1;
		
		while(!q.isEmpty()) {
			day++;
			int size = q.size();
			while(size-- > 0) {
				int[] c = q.poll();
				
				for (int i=0;i<4;i++) {
					int nx = c[0] + dx[i];
					int ny = c[1] + dy[i];
					
					if (nx<0 || nx>=N || ny<0 || ny>=M) continue;
					if (map[nx][ny]==0) {
						map[nx][ny] = 1;
						q.offer(new int[] {nx, ny});
						unripeCt--;
					}
				}
			}
		}
		return unripeCt == 0 ? day : -1;
	}
	
	private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
}
