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
		// 간선 개수 : K*M
		

		ArrayList<ArrayList<Integer>> map = new ArrayList<>(); 
		for (int i=0;i<=N+M;i++) map.add(new ArrayList<>()); // 1~N = 연결된 노드
		
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<K;j++) {
				int s = Integer.parseInt(st.nextToken());
				map.get(N+i+1).add(s);
				map.get(s).add(N+i+1);
			}
		}
		
		Deque<Node> q = new ArrayDeque<>();

		q.offer(new Node(1, 0));
		boolean[] isVisited = new boolean[N+M+1];
		isVisited[1] = true; // 시작 지점 방문 처리
		
		while(!q.isEmpty()) {
			Node current = q.pollFirst();
			
			if (current.v==N) {
				System.out.print(current.ct+1);
				return;
			}
			
			for (Integer nextNode : map.get(current.v)) { // current.v 가 가지고 있는 "하이퍼 튜브" 조회
				if (!isVisited[nextNode]) {
					isVisited[nextNode] = true; // 방문 처리
					q.offerLast(new Node(nextNode, (nextNode>N) ? current.ct : current.ct+1));
				}
			}
		}
		System.out.println(-1);
	}
}
