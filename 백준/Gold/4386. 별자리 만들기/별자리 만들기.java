import java.util.*;
import java.io.*;

public class Main {
	static class Edge{
		double x, y;
		Edge(double x, double y){
			this.x = x;
			this.y = y;
		}
	}
	
	static class Node implements Comparable<Node>{
		int v, u;
		double length;
		
		Node(int v, int u){
			this.v = v;
			this.u = u;
			this.length = Math.sqrt(Math.pow(cor[v].x-cor[u].x, 2)+Math.pow(cor[v].y-cor[u].y, 2));
		}
		
		@Override
		public int compareTo(Node o) {
			return Double.compare(this.length, o.length);
		}
	}
	
	static Edge[] cor;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 별의 개수 (노드)
		cor = new Edge[n];
		par = new int[n];
		Arrays.fill(par, -1);
		
		StringTokenizer st;
		for (int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			cor[i] = new Edge(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i=0;i<n;i++) {
			for (int j=i+1;j<n;j++) {
				pq.offer(new Node(i, j));
			}
		}
		
		int ct = 0;
		double totalCost = 0.0;
		while(!pq.isEmpty()) {
			if (ct==n) break;
			
			Node c = pq.poll();
			if (merge(c.v, c.u)) {
				ct++;
				totalCost += c.length;
			}
		}
		System.out.print(totalCost);
	}
	
	static int[] par;
	
	public static int find(int x) {
		if (par[x]<0) return x;
		return par[x] = find(par[x]);
	}
	
	public static boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		if (a==b) return false;
		
		if(a>b) par[b] = a;
		else par[a] = b;
		return true;
	}
}
