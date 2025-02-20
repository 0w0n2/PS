import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dist = new int[N][N];
		
		StringTokenizer st;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				int x = Integer.parseInt(st.nextToken());
				dist[i][j] = x==1 ? 1 : 0;
			}
		}
		for (int a=0;a<N;a++) {
			for (int i=0;i<N;i++) for (int j=0;j<N;j++) {
				if (dist[i][a]==1&&dist[a][j]==1) {
					dist[i][j] = 1;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<N;i++) { 
			for (int j=0;j<N;j++) {
				sb.append(dist[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}