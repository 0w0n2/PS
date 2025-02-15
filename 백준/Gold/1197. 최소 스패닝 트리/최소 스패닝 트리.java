import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int a, b, weight;
		Node(int a, int b, int weight){
			this.a = a;
			this.b = b;
			this.weight = weight;
		}
	}
	
	static int[] par;
	
	public static void main (String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수(노드)
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		par = new int[V+1];
		Arrays.fill(par, -1);

		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.weight - o2.weight;
			}
		});
		
		for (int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.offer(new Node(a, b, c));
		}
		
		int totalWeight = 0;
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			if (merge(current.a, current.b)) totalWeight += current.weight;
		}
		System.out.print(totalWeight);
	}
	
	public static int find(int k) {
		if (par[k]==-1) return k;
		return par[k] = find(par[k]);
	}
	
	
	public static boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		
		par[a] = b;
		return true;
	}
}