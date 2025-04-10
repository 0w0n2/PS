// 음악프로그램(위상정렬)
import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] inDegree = new int[N+1];
		List<Integer>[] map = new List[N+1];
		for (int i=0;i<=N;i++) map[i] = new ArrayList<>();
		
		int[] result = new int[N];
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 가수의 수
			int last = Integer.parseInt(st.nextToken());
			for (int j=0;j<x-1;j++) {
				int now = Integer.parseInt(st.nextToken());
				map[last].add(now);
				inDegree[now]++;
				last = now;
			}
		}
		
		Deque<Integer> q = new ArrayDeque<>();
		for (int i=1;i<=N;i++) if(inDegree[i]==0) q.offerLast(i);
		
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<N;i++) {
			if(q.isEmpty()) {
				System.out.print(0);
				return;
			}
			int current = q.pollFirst();
			result[i] = current;
			
			for (int next:map[current]) {
				if (--inDegree[next]==0) {
					q.offerLast(next);
				}
			}
			sb.append(result[i]).append("\n");
		}
		System.out.print(sb);
	}
}