import java.util.*;
import java.io.*;

public class Main {
    
	static HashMap<Integer, Integer> area = new HashMap<>();
	static int n = 1, N, M, map[][];
	
	public static void main(String[] args) throws IOException{
		
		// 1. 입력 단계
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i=0;i<N;i++) {
			String line = br.readLine();
			for (int j=0;j<M;j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		// 2. map[i][j] == 0 인 곳에 대해서, 연결되어 있는 0이 몇 개인지 영역을 구한다
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				if (map[i][j]==0) {
					n++;
					
					map[i][j] = n;	// map에 해당 그룹의 번호(n)를 기록한다 (n은 2부터 시작 (입력이 숫자 1까지 들어오므로))
					area.put(n, dfs(i, j));	// 해당 그룹이 인접한 0이 몇개인지 해시맵에 저장
				}
			}
		}
		
		// 3. map[i][j] == 1 인 곳에 대해서, 상하좌우의 bfs 값을 더해 출력한다
		StringBuilder sb = new StringBuilder();
		
		HashSet<Integer> use;	
		// 상하좌우에 존재하는 0 중에 어느 두 곳 이상이 같은 그룹(영역)에 속하는 0일 때 잘 못 카운팅되지 않도록 사용한 영역을 저장하는 세트
		// 그룹이란, 상하좌우를 인접한 0들을 기준으로 묶은 영역을 뜻함 
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				if (map[i][j]!=1) sb.append(0);
				else { 
					int sum = 1; // 자기 자신 포함(1)
					use = new HashSet<>();
					
					for (int k=0;k<4;k++) { // 상하좌우에 0이 있는지 검사
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx<0||nx>=N||ny<0||ny>=M||map[nx][ny]==1) continue;
						
						if (!use.isEmpty() && use.contains(map[nx][ny])) continue;	// 만약 상하좌우에 존재하는 0 중에 어느 두 곳 이상이 같은 그룹에 속할 경우 무시
						
						sum += area.get(map[nx][ny]);	// 해당 0이 속하는 그룹에 대한 인접한 0의 넓이를 해시맵에서 반환하여 더함 
						use.add(map[nx][ny]);	// 이번 그룹을 사용했음을 세트에 저장
					
					}
					sb.append(sum % 10); // 10의 나머지를 출력에서 요구함...
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static int dfs(int x, int y) {
		
		int result = 1;
		
		for (int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx<0||nx>=N||ny<0||ny>=M||map[nx][ny]!=0) continue;	// map[nx][ny]!=0 를 방문처리 검사하듯이 사용했음
			map[nx][ny] = n;	// 인접한 0에 해당 그룹의 번호(n)를 기록한다
			result += dfs(nx, ny);
		}
		
		return result;
	}
	
}
