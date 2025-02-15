import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int a, b, costs;
		Node(int a, int b, int costs){
			this.a = a;
			this.b = b;
			this.costs = costs;
		}
	}
	static int[] par;
	static int[] height;
	static int maxLineLength = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 집의 개수(노드)
		int M = Integer.parseInt(st.nextToken()); // 간선 개수(간선)
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.costs - o2.costs;
			}
		});
		
		par = new int[N+1];
		Arrays.fill(par, -1);
		
		height = new int[N+1];
		Arrays.fill(height, 1);
		
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			pq.offer(new Node(a, b, c));
		}
		
		int totalCosts = 0;
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			totalCosts += merge(current.a, current.b, current.costs);
		}
		System.out.print(totalCosts-maxLineLength);
		
	}
	
	public static int find(int k) {
		if(par[k]==-1) return k;
		return par[k] = find(par[k]);
	}
	
	public static int merge(int a, int b, int costs) {
		a = find(a);
		b = find(b);
		
		if (a==b) return 0;
		
		if (height[a]>height[b]) {
			par[b] = a;
			height[a] += height[b];
		} else {
			par[a] = b;
			height[b] += height[a];
		}
		
		maxLineLength = Math.max(maxLineLength, costs); // 모두 최소경로로 연결하고, 가장 큰 간선을 잘라버릴 것
		return costs;
	}
}