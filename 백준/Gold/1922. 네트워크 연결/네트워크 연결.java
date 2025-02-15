import java.util.*;
import java.io.*;

public class Main {
	static int[] par;
	static int[] costs;
	static int totalCosts = 0;
	
	static class Node{
		int v, u, costs;
		Node(int v, int u, int costs){
			this.v = v;
			this.u = u;
			this.costs = costs;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수(노드)
		int M = Integer.parseInt(br.readLine()); // 선의 수(간선 종류)
		par = new int[N+1];
		Arrays.fill(par, -1);
		
		StringTokenizer st;
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.costs - o2.costs;
			}
		});
		
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.offer(new Node(a, b, c));
		}
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			if(merge(current.u, current.v)) totalCosts += current.costs;
		}
		System.out.print(totalCosts);
		
	}
	
	public static int find(int n) {
		if(par[n]==-1) return n;
		return par[n] = find(par[n]);
	}
	
	public static boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		
		par[a] = b;
		return true;
	}
}