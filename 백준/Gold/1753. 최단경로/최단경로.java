import java.util.*;
import java.io.*;

public class Main {
	
	private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	static int V, E, start;
	static int[] head, to, weight, next;
	static int edgePtr = 0;
	
	static ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
	
	static void addEdge(int u, int v, int w) {
		to[edgePtr] = v;
		weight[edgePtr] = w;
		next[edgePtr] = head[u];
		head[u] = edgePtr++;
	}
	
	public static void main(String[] args) throws IOException{
		V = readInt(); // 정점의 개수
		E = readInt(); // 간선의 개수
		start = readInt(); // 시작 지점
		
		head = new int[V+1];
		Arrays.fill(head, -1);
		to = new int[E];
		weight = new int[E];
		next = new int[E];
		
		for (int i=0;i<E;i++) {
			int u = readInt();
			int v = readInt();
			int w = readInt();
			addEdge(u, v, w);
		}
		
		final int INF = 1_000_000_000;
		int[] dist = new int[V+1];
		Arrays.fill(dist, INF);
		
		// long 하나에 두 개의 int 정보 담기
		// long : 64비트 / int : 32비트
		// 64비트짜리 long 변수 공간을 반으로 나누어 앞 32비트에는 "거리", 뒤 32비트에는 "정점 번호"를 저장
		PriorityQueue<Long> pq = new PriorityQueue<>();
		dist[start] = 0;
		pq.offer(((long) dist[start]) << 32 | start);
		// `dist[start]` 의 비트를 왼쪽으로 32칸 밀어냄 
		// `| start` : OR 연산자 (왼쪽 시프트 연산 결과로 하위 32비트가 모두 0이 되었으므로 start 정점 번호를 OR 연산하면 하위 32비트에 start의 값이 그대로 채워짐)
		
		while (!pq.isEmpty()) {
			long cur = pq.poll();
			// 정보 분리하기
			int u = (int) (cur & 0xFFFFFFFFL);
			// 0xFFFFFFFFL : 하위 32비트가 모두 1인 값 + Long 타입임을 명시
			// & 연산하면 하위 32비트에 해당하는 값만 분리됨
			int d = (int) (cur >>> 32);
			// >> 연산자 : 그냥 0 채우면서 shift
			// >>> 연산자 : 부호를 고려하면서 shift
			
			if (d!=dist[u]) continue;
			
			for (int e = head[u]; e!=-1; e=next[e]) {
				int v = to[e];
				int nd = d + weight[e];
				if (nd < dist[v]) {
					dist[v] = nd;
					pq.offer(((long) nd) << 32 | v);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1;i<=V;i++) {
			sb.append(dist[i] == INF ? "INF" : dist[i]).append("\n");
		}
		System.out.print(sb);
	}
}
