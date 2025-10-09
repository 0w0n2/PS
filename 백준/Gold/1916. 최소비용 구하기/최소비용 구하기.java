import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		map = new ArrayList<ArrayList<int[]>>();
		for (int i=0;i<=N;i++) map.add(new ArrayList<int[]>());
		
		StringTokenizer st;
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			map.get(u).add(new int[] {v, w});
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start, end);
	}
	
	private static ArrayList<ArrayList<int[]>> map;
	private static int N;
	
	private static void dijkstra(int start, int end) {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[1]-b[1]);
		pq.offer(new int[] {start, 0});
		
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if (cur[0] == end) {
				System.out.print(cur[1]);
				return;
			}
			
			if (dist[cur[0]] != cur[1]) continue;
			
			for (int[] next : map.get(cur[0])) {
				int nw = next[1] + cur[1];
				if (dist[next[0]] > nw) {
					dist[next[0]] = nw;
					pq.offer(new int[] {next[0], nw});
				}
			}
		}
	}
}
