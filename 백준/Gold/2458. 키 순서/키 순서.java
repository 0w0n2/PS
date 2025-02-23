import java.util.*;
import java.io.*;
public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 학생들 수(번호 1~N)
		int m = Integer.parseInt(st.nextToken()); // 키 비교 횟수
		
		boolean[][] graph = new boolean[n][n];
		for (int i=0;i<n;i++) graph[i][i] = true; // 자기 자신
		
		for (int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			graph[start][end] = true; // 경로 존재
		}
		
		// 플로이드-워셜로 경로 존재하는지 확인
		for (int k=0;k<n;k++) {
			for (int i=0;i<n;i++) {
				for (int j=0;j<n;j++) {
					if (graph[i][k]&&graph[k][j]) graph[i][j] = true;
				}
			}
		}
		
		int ans = 0;
		for (int i=0;i<n;i++) {
			int nowCount = 0;
			for (int j=0;j<n;j++) {
				if (graph[i][j]||graph[j][i]) nowCount++; 
			}
			if (nowCount==n) ans++;
		}

		System.out.print(ans);
	}
}