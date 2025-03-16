import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Integer>> map = new ArrayList<>();
		for (int i=0;i<N;i++) map.add(new ArrayList<>());
		
		int[] inDegree = new int[N];
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken()) - 1;
			int back = Integer.parseInt(st.nextToken()) - 1;
			
			map.get(front).add(back);
			inDegree[back]++;
		}
		
		Deque<Integer> q = new ArrayDeque<>();
		for (int i=0;i<N;i++) {
			if (inDegree[i]==0) q.offerLast(i);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<N;i++) {
			int c = q.pollFirst();
			sb.append(c+1).append(" ");
			
			for (int np : map.get(c)) {
				if (--inDegree[np]==0) {
					q.offerLast(np);
				}
			}
		}
		System.out.print(sb);
	}
}
