import java.util.*;
import java.io.*;

public class Main {
	
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int readInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
	static long readLong() throws IOException{
		st.nextToken();
		return (long) st.nval;
	}
	
	static class Node implements Comparable<Node>{
		int v;
		long weight;
		Node(int v, long weight){
			this.v = v;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws IOException{
		int N = readInt(); // 도시의 수 (노드)
		int M = readInt(); // 도로의 수 (간선)
		int K = readInt(); // 면접장이 있는 도시의 수
		
		ArrayList<ArrayList<Node>> map = new ArrayList<>();
		for (int i=0;i<=N;i++) map.add(new ArrayList<>());
		
		for (int i=0;i<M;i++) {
			int v = readInt();
			int u = readInt();
			long weight = readLong();
			
//			map.get(v).add(new Node(u, weight));
			map.get(u).add(new Node(v, weight));
		}
		
//		int[] meetingCity = new int[K];
		for (int k=0;k<K;k++) {
//			meetingCity[k] = readInt();
//			map.get(0).add(new Node(meetingCity[k],0));
			map.get(0).add(new Node(readInt(),0));
		}
		
		// 0번 정점(실제론 없는 정점)을 슈퍼노드로 잡고, 슈퍼노드에서 모든 간선을 역방향으로 이동
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0));
		long[] dist = new long[N+1];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[0] = 0;
		
		while(!pq.isEmpty()) {
			Node c = pq.poll();
			
			if (dist[c.v]!=c.weight) continue;
			
			for (Node np:map.get(c.v)) {
				long nw = np.weight + c.weight;
				if (dist[np.v]>nw) {
					dist[np.v] = nw;
					pq.offer(new Node(np.v, nw));
				}
			}
		}
		
		int maxCity=0;
		long maxWeight=0;
		for (int i=1;i<=N;i++) {
			if (dist[i]>maxWeight) {
				maxWeight = dist[i];
				maxCity = i;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(maxCity).append("\n").append(maxWeight);
		System.out.print(sb);
		
	}
}
