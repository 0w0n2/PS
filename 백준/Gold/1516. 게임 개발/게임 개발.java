import java.util.*;
import java.io.*;

public class Main {
	
	static class Node{
		int v, time;
		Node(int v, int time){
			this.v = v;
			this.time = time;
		}
	}
	
	static ArrayList<ArrayList<Integer>> field = new ArrayList<>();
	static int[] inDegree;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 건물 종류 수(노드)
		
		for (int i=0;i<=N;i++) field.add(new ArrayList<Integer>());
		inDegree = new int[N+1];
		int[] time = new int[N+1];
		int [] result = new int[N+1];
		
		Deque<Integer> q = new ArrayDeque<>();
		
		StringTokenizer st;
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken()); // i번째 건물을 짓는데 드는 비용
			
			int u = 0; // u가 i보다 먼저 지어져야 함
			while((u=Integer.parseInt(st.nextToken()))!=-1) {
				field.get(u).add(i);
				inDegree[i]++; // i로 들어가는 입구 +1
			}
			
			if(inDegree[i]==0) {
				q.offerLast(i); // inDegree = 0인 노드를 큐에 넣음
				result[i] = time[i];
			}
		}

		// 위상 정렬
		for (int i=0;i<N;i++) {
			
			int current = q.pollFirst();
			
			for (int nextPoint:field.get(current)) {
				// 최소 건설 완료 시간 갱신
				// 선행 건물이 여러개일 수 있고, 그중 가장 오래 걸리는 선행 건물의 건설 시간이 nextPoint의 건설 시간을 결정
				result[nextPoint] = Math.max(result[nextPoint], result[current]+time[nextPoint]);
				// 간선을 삭제하고, 그 후 nextPoint의 inDegree가 0이 되면 nextPoint도 큐에 넣음
				if (--inDegree[nextPoint]==0) q.offerLast(nextPoint);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1;i<=N;i++) {
			sb.append(result[i]).append("\n");
		}
		System.out.print(sb);
		
	}
	
}