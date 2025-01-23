import java.util.*;
import java.io.*;

class Main
{	
	// 너비 우선 탐색으로 노드를 방문할 경우 노드의 방문 순서?
	public static int[] visited;
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	public static void main (String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 노드의 수
		int m = Integer.parseInt(st.nextToken()); // 라인의 수
		int start = Integer.parseInt(st.nextToken()); // 시작 위치
		
		for (int i = 0; i <= n; i++) graph.add(new ArrayList<>()); // 리스트 초기화
		
		for (int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		} // 라인 입력
		
		for (int i=0;i<=n;i++) {
			Collections.sort(graph.get(i));
		} // 노드 별 연결된 노드 오름차순 정렬
	
		visited = new int[n+1]; // 방문 순서 저장
		
		bfs(start);
		
		for (int i=1;i<=n;i++) bw.write(String.valueOf(visited[i])+"\n");
		bw.flush();
		bw.close();
	}
	
	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		int count = 1;
		
		q.offer(start); // 현재 노드 q에 bfs를 시작할 노드 번호를 넣어줌
		visited[start] = count; // 현재 노드를 방문 처리
		
		while(!q.isEmpty()) { // 큐가 빌 때까지 반복
			int x = q.poll(); // 큐에서 하나의 원소를 뽑음
			
			// 해당 원소와 연결된, 아직 방문하지 않은 원소들을 큐에 삽입
			for (int i=0;i<graph.get(x).size();i++){
				int y = graph.get(x).get(i);
				if (visited[y]==0) {
					q.offer(y);
					count++;
					visited[y] = count;
				}
			}
			
		}
	}
}

