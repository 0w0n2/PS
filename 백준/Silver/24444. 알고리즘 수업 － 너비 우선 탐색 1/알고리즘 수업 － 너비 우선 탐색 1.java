import java.util.*;
import java.io.*;

class Main
{	
	// 너비 우선 탐색으로 노드를 방문할 경우 노드의 방문 순서?
	public static boolean[] visited;
	public static int [] series;
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	public static void main (String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 노드의 수
		int m = Integer.parseInt(st.nextToken()); // 라인의 수
		int start = Integer.parseInt(st.nextToken()); // 시작 위치
		series = new int [n+1];
		
		visited = new boolean[n+1];
		for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
		
		for (int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		for (int i=0;i<=n;i++) {
			Collections.sort(graph.get(i));
		}
		
		bfs(start);
		for (int i=1;i<=n;i++) System.out.println(series[i]);
	}
	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(start); // 현재 노드 q에 bfs를 시작할 노드 번호를 넣어줌
		visited[start] = true; // 현재 노드를 방문 처리
		
		int k = 1;
		while(!q.isEmpty()) { // 큐가 빌 때까지 반복
			int x = q.poll(); // 큐에서 하나의 원소를 뽑음
			series[x] = k;
			k++;
			
			// 해당 원소와 연결된, 아직 방문하지 않은 원소들을 큐에 삽입
			for (int i=0;i<graph.get(x).size();i++){
				int y = graph.get(x).get(i);
				if (!visited[y]) {
					q.offer(y);
					visited[y] = true;
				}
			}
			
		}
	}
}

