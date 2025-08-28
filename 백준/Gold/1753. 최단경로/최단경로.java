import java.util.*;
import java.io.*;

public class Main {

	private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException {
		V = readInt(); // 정점의 개수
		int E = readInt(); // 간선의 개수
		int S = readInt()-1;
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0;i<V;i++) map.add(new ArrayList<int[]>());
		
		for (int i=0;i<E;i++) {
			int s = readInt()-1;
			int e = readInt()-1;
			int w = readInt();
			map.get(s).add(new int[] {e, w});
		}
		
		dijkstra(S);
		for (int i : dist) sb.append(i==INF ? "INF" : i).append("\n");
		System.out.print(sb);
	}
	
	private static int V;
	private static ArrayList<ArrayList<int[]>> map = new ArrayList<ArrayList<int[]>>();
	private static int[] dist;
	private final static int INF = 1_000_000_000;;
	
	private static void dijkstra(int s) {
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		
		dist = new int[V];
		Arrays.fill(dist, INF);
		dist[s] = 0;
		pq.offer(((long) dist[s] << 32) | s);
		
		while (!pq.isEmpty()) {
			long cur = pq.poll();
			int u = (int) (cur & 0xFFFFFFFL);
			int d = (int) (cur >>> 32);
			if (dist[u] != d) continue;

			for (int[] next : map.get(u)) {
				int nw = d + next[1];
				if (dist[next[0]] > nw) {
					dist[next[0]] = nw;
					pq.offer(((long) nw << 32) | next[0]);
				}
			} 
		}
	}
}
