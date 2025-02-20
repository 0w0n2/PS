import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int v, weight;
		Node(int v, int weight){
			this.v = v;
			this.weight = weight;
		}
	}
	static int N;
	
	static ArrayList<ArrayList<Node>> map = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 노드 개수
		int E = Integer.parseInt(st.nextToken()); // 간선 개수
		
		// 양방향 그래프
		for (int i=0;i<=N;i++) map.add(new ArrayList<Node>());
		for (int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken()); 
			int u = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			map.get(v).add(new Node(u, w));
			map.get(u).add(new Node(v, w));
		}
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		long ab = bfs(a, b);
		// 1->a->b->N
		long way1 = bfs(1, a) + bfs(b, N) + ab;
			
		// 1->b->a->N
		long way2 = bfs(1, b) + bfs(a, N) + ab;
		
		long result = Math.min(way1, way2);
			
		if (result>=Integer.MAX_VALUE) {
			System.out.print(-1);
		} else {
			System.out.print(result);
		}
	
	}
	
	public static long bfs(int startPoint, int endPoint) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.weight - o2.weight;
			}
		});
		
		long [] dist = new long[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[startPoint] = 0;
		pq.offer(new Node(startPoint, 0)); // 자기자신 스타트
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			int cv = current.v;
			int cw = current.weight;
			if (dist[cv]!=cw) continue; // 무효값
			
			for (Node nextPoint : map.get(cv)) {
				int nv = nextPoint.v;
				int nw = nextPoint.weight + cw;
				if(dist[nv]>nw) {
					dist[nv] = nw; // 교체
					pq.offer(new Node(nv, nw));
				}
			}
			
		}
		return dist[endPoint];
	}
	
}