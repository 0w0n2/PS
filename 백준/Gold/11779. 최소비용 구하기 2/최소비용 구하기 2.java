import java.util.*;
import java.io.*;

public class Main {
	
	private static class Node implements Comparable<Node>{
		int v, weight;
		Node(int v, int weight){
			this.v = v;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 도시의 개수(노드)
		int m = Integer.parseInt(br.readLine()); // 버스의 개수(간선)
		
		List<Node>[] map = new ArrayList[n+1]; // 간선 정보 저장
		for (int i=0;i<=n;i++) map[i] = new ArrayList<>(); // 초기화
		
		StringTokenizer st;
		for (int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken()); // 출발 도시
			int u = Integer.parseInt(st.nextToken()); // 도착 도시
			int w = Integer.parseInt(st.nextToken()); // 비용
			
			map[v].add(new Node(u, w));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()); // 출발점
		int end = Integer.parseInt(st.nextToken()); // 목표 도착점
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0)); // (출발점, 비용0) 에서 시작
		
		int[] dist = new int[n+1];	// 노드별 출발점에서의 최소 거리 저장
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
	
		int[] prev = new int[n+1];
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if (dist[current.v]!=current.weight) continue; // 이미 갱신된 최단 경로 정보와 다르면 무시
			
			if (current.v==end) break; // 목표 도달점 도착 시 종료
			
			for (Node nextPoint:map[current.v]) {
				int nv = nextPoint.v;
				int nw = nextPoint.weight + current.weight;
				
				if (dist[nv]>nw) {
					dist[nv] = nw;
					prev[nv] = current.v;
					pq.offer(new Node(nv, nw));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder(); // 출력 저장
		
		sb.append(dist[end]).append("\n"); // 도착점까지의 최소 경로
		
		ArrayList<Integer> path = new ArrayList<>();
		for (int i=end;i!=0;i=prev[i]) path.add(i);
		Collections.reverse(path); 
		
		sb.append(path.size()).append("\n"); // 방문하는 도시 개수
		
		for (int i:path) sb.append(i).append(" ");
		
		System.out.print(sb);
	}

}
