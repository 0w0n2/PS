import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스
		StringBuilder sb = new StringBuilder(); // 출력용
		
		Loop1:
		for (int t=0;t<tc;t++) {
			
			int n = Integer.parseInt(br.readLine()); // 팀의 수(노드 수)
			boolean[][] map = new boolean[n+1][n+1]; // map[i][j] = true : i->j 연결됨, false : 연결 X
			
			int[] lastYear = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0;i<n;i++) {
				lastYear[i] = Integer.parseInt(st.nextToken()); 
			}
			
			int[] inDegree = new int[n+1];
			for (int i=0;i<n;i++) {
				for (int j=i+1;j<n;j++) {
					map[lastYear[i]][lastYear[j]] = true; // i->j 관계 성립
					inDegree[lastYear[j]]++;
				}
			}
			
			int m = Integer.parseInt(br.readLine());
			for (int i=0;i<m;i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				if (map[start][end]) { // 원래 순서 start->end 였을 경우, end->start로 반전
			        map[start][end] = false;
			        map[end][start] = true; // 반전
			        inDegree[end]--;
			        inDegree[start]++;
			    } else { // 원래 순서 end->start 였을 경우, start->end로 반전
			        map[end][start] = false;
			        map[start][end] = true;
			        inDegree[start]--;
			        inDegree[end]++;
			    }
			}
			
			Deque<Integer> q = new ArrayDeque<>(); // 큐
			int[] result = new int[n];
			
			for (int i=1;i<=n;i++) {
				if(inDegree[i]==0) q.offerLast(i);
			}
			
			// 위상 정렬 시작
			for (int i=0;i<n;i++) {
				// 도중에 큐가 비면 정렬 불가능 (순환 고리 생김)
				if(q.isEmpty()) {
					sb.append("IMPOSSIBLE\n");
					continue Loop1; // 정렬 종료
				}
				
				int current = q.pollFirst();
				result[i] = current;
				
				for (int nextPoint=1;nextPoint<=n;nextPoint++) {
					if (map[current][nextPoint]) {
						if (--inDegree[nextPoint]==0) q.addLast(nextPoint);
					}
				}
			}
			
			for (int i=0;i<n;i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}