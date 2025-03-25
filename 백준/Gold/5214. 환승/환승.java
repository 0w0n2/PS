import java.util.*;
import java.io.*;

public class Main {
	
	static class Node implements Comparable<Node>{
		int v, ct;
		Node(int v, int ct){
			this.v = v;
			this.ct = ct;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.ct - o.ct;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 역의 수(노드 개수) N
		int K = Integer.parseInt(st.nextToken()); // 한 하이퍼튜브가 서로 연결하는 역의 개수
		int M = Integer.parseInt(st.nextToken()); // 하이퍼 튜브의 개수

		ArrayList<ArrayList<Integer>> map = new ArrayList<>(); 
		for (int i=0;i<=N+M;i++) map.add(new ArrayList<>()); // 1~N = 노드, N+1~N+M = 하이퍼 링크
		
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<K;j++) {
				int s = Integer.parseInt(st.nextToken());
				map.get(N+i+1).add(s); // i번째 하이퍼링크가 노드 s와 연결되어 있음을 추가
				map.get(s).add(N+i+1); // s번째 노드가 i번째 하이퍼링크와 연결되어 있음을 추가
			}
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N+M+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		pq.offer(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if (dist[current.v]!=current.ct) continue;
			
			for (Integer nextPoint : map.get(current.v)) { // current.v 가 가지고 있는 하이퍼 튜브 조회
				int nct = nextPoint>N ? current.ct : current.ct+1;
				if (dist[nextPoint]>nct) {
					dist[nextPoint] = nct;
					pq.offer(new Node(nextPoint, nct));
				}
			}
		}
		System.out.println(dist[N]==Integer.MAX_VALUE ? -1 : dist[N]+1);
	}
}
