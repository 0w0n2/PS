import java.io.*;
import java.util.*;

public class Main {
	private static int[] par;
	
	private static class Node implements Comparable<Node>{
		int v, u, weight;
		
		Node(int v, int u, int weight){
			this.v = v;
			this.u = u;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.weight-o.weight;
		}
	}
	
	private static int find(int x) {
		if (par[x]<0) return x;
		return par[x] = find(par[x]); // 경로압축하면서 최상위 루트 노드까지 접근
	}
	
	private static boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a==b) return false; // 이미 더 최소 비용으로 병합된 간선
		
		par[a] = b;
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수 (노드)
		int E = Integer.parseInt(st.nextToken()); // 입력으로 들어오는 간선의 개수
		
		par = new int[V+1];
		Arrays.fill(par, -1);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for (int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.offer(new Node(v, u, w));
		}
		
		int totalCost = 0;
		int ct = 0;
		while(!pq.isEmpty()) {
			if (ct==V) break;
			
			Node current = pq.poll();
			
			if (merge(current.v, current.u)) {
				totalCost += current.weight;
				ct++;
			}
		}
		System.out.println(totalCost);
	}
}
