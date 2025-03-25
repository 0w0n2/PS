import java.util.*;
import java.io.*;

public class Main {
	
	/**
	 * 백준 5214 환승 / 다익스트라로 풀이
	 * @since 25-03-25 (화)
	 * Java11 - 실행 시간 : 145,888 KB, 788 ms
	 */
	
	static class Node {
		int v, ct;
		Node(int v, int ct){
			this.v = v;
			this.ct = ct;
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
		
		Deque<Node> q = new ArrayDeque<>(); // bfs용 큐

		q.offer(new Node(1, 0));	// 초기 시작 지점
		boolean[] isVisited = new boolean[N+M+1]; 
		isVisited[1] = true; // 시작 지점 방문 처리
		
		while(!q.isEmpty()) {
			Node current = q.pollFirst();
			
			if (current.v==N) { // N에 도달
				System.out.print(current.ct+1);
				return;
			}
			
			for (Integer nextNode : map.get(current.v)) { 
				if (!isVisited[nextNode]) {
					isVisited[nextNode] = true; // 방문 처리
					q.offerLast(new Node(nextNode, (nextNode>N) ? current.ct : current.ct+1));
					// 하이퍼링크 방문 시 카운트 +1 하지 않고, 역에 방문한 경우에만 카운트 +1 처리
				}
			}
		}
		System.out.println(-1); // 방문 불가
	}
}
