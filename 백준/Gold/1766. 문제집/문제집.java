import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 문제의 수 (노드)
		int M = Integer.parseInt(st.nextToken()); // 먼저 푸는 것이 좋은 문제 정보 개수 (간선)
		
		int[] inDegree = new int[N+1];
		ArrayList<ArrayList<Integer>> map = new ArrayList<>();
		for(int i=0;i<=N;i++) map.add(new ArrayList<Integer>()); // 초기화
		
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map.get(a).add(b); // b번 문제 전에 a번 문제 풀기
			inDegree[b]++; // b번 문제 전에 꼭 풀어야 하는 문제 개수 +1     
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // 작은 문제번호가 더 쉽다(우선순위)
		for (int i=1;i<=N;i++) {
			if (inDegree[i]==0) pq.offer(i); // 시작점
		}
		
		StringBuilder sb = new StringBuilder();
		int[] result = new int[N]; // 위상정렬 결과 
		for (int i=0;i<N;i++) {
			int current = pq.poll();
			result[i] = current;
			for (int nextPoint:map.get(current)) {
				if(--inDegree[nextPoint]==0) {
					pq.offer(nextPoint);
				}
			}
			sb.append(result[i]).append(" ");
		}
		System.out.print(sb);
	
	}
}