import java.util.*;
import java.io.*;

public class Main {
//	static class Planet{
//		int num, x, y, z; // 행성 번호, x-y-z 좌표
//		public Planet(int num, int x, int y, int z) {
//			this.num = num;
//			this.x = x;
//			this.y = y;
//			this.z = z;
//		}
//	}
	
	static class Planet{
		int num, v; // 행성 번호, x-y-z 좌표
		public Planet(int num, int v) {
			this.num = num;
			this.v = v;
		}
	}
	
	static class Node{
		int start, end, dist;
		Node(int start, int end, int dist){
			this.start = start;
			this.end = end;
			this.dist = dist;
		}
	}
	
	static ArrayList<Planet> coordinate = new ArrayList<>();
	static int[] par;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 행성 수
		
		par = new int[n];
		Arrays.fill(par, -1);
		
		ArrayList<Planet> X = new ArrayList<>();
		ArrayList<Planet> Y = new ArrayList<>();
		ArrayList<Planet> Z = new ArrayList<>();
		
		StringTokenizer st;
		for (int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			X.add(new Planet(i, Integer.parseInt(st.nextToken())));
			Y.add(new Planet(i, Integer.parseInt(st.nextToken())));
			Z.add(new Planet(i, Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(X, (o1, o2)-> o1.v - o2.v); // 인접 좌표끼리 이어지게
		Collections.sort(Y, (o1, o2)-> o1.v - o2.v); // 인접 좌표끼리 이어지게
		Collections.sort(Z, (o1, o2)-> o1.v - o2.v); // 인접 좌표끼리 이어지게
		
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)-> o1.dist-o2.dist); // dist(거리 가중치) 짧은 순으로 큐 정렬 
		
		for (int i=1;i<n;i++) {
			pq.add(new Node(X.get(i-1).num, X.get(i).num, Math.abs(X.get(i-1).v-X.get(i).v)));
			pq.add(new Node(Y.get(i-1).num, Y.get(i).num, Math.abs(Y.get(i-1).v-Y.get(i).v)));
			pq.add(new Node(Z.get(i-1).num, Z.get(i).num, Math.abs(Z.get(i-1).v-Z.get(i).v)));
		}
		
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			merge(current.start, current.end, current.dist);
		}
		System.out.println(totalCost);
	}
	
	static int find(int k) {
		if (par[k]==-1) return k;
		return par[k] = find(par[k]);
	}
	
	static int totalCost = 0;
	
	static void merge(int a, int b, int currentCost) {
		a = find(a);
		b = find(b);
		
		if (a==b) return;
		
		par[a] = b;
		totalCost += currentCost;
	}
}