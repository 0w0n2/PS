import java.util.*;
import java.io.*;

public class Main {
	static class Family{
		int v, ct;
		Family(int v, int ct) {
			this.v = v;
			this.ct = ct;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 사람의 수
		ArrayList<ArrayList<Integer>> map = new ArrayList<>();
		for (int i=0;i<N;i++) map.add(new ArrayList<>()); // 초기화
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		Deque<Family> q = new ArrayDeque<>();
		q.offerLast(new Family(Integer.parseInt(st.nextToken())-1, 0));
		boolean[] isVisited = new boolean[N];
		isVisited[q.peek().v] = true;
		
		int end = Integer.parseInt(st.nextToken())-1;
		
		int M = Integer.parseInt(br.readLine()); // 가족관계의 수
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			// 양방향 간선
			int v = Integer.parseInt(st.nextToken()) - 1;
			int u = Integer.parseInt(st.nextToken()) - 1;
			map.get(v).add(u);
			map.get(u).add(v);
		}
		
		while(!q.isEmpty()) {
			Family c = q.pollFirst();
			if (c.v==end) {
				System.out.print(c.ct);
				return;
			}
			
			for (int np : map.get(c.v)) {
				if (!isVisited[np]) {
					isVisited[np] = true;
					q.offerLast(new Family(np, c.ct+1));
				}
			}
		}
		System.out.print(-1);
	}
}
