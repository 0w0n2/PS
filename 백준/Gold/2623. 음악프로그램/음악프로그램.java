import java.util.*;
import java.io.*;

public class Main {
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException{
		int N = readInt(); // 가수의 수
		int M = readInt(); // 보조 PD의 수
		
		// 각 M개의 줄 마다, 각 보조 PD 들이 정한 순서들이 한 줄에 하나씩 나옴
		// 한 줄에 있는 원소들 각각을 이어줘야 함
		List<Integer>[] arr = new ArrayList[N+1];
		for (int i=0;i<=N;i++) arr[i] = new ArrayList<>();
		
		int[] inDegree = new int[N+1];
		
		// 앞쪽이 더 출연 순서 빠름
		// 3->1, 1->4, 4->3
		for (int i=0;i<M;i++) {
			int n = readInt(); // 이 줄에 등장하는 사람 수
			int left = readInt();
			for (int j=1;j<n;j++) {
				int right = readInt();
				arr[left].add(right);
				inDegree[right]++;
				left = right;
			}
		}
		
		// 위상 정렬 시작
		boolean impossible = false;
		Queue<Integer> q = new ArrayDeque<>();
		for (int i=1;i<=N;i++) if (inDegree[i]==0) q.offer(i); // 시작 입구
		
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<N;i++) {
			if (q.isEmpty()) { // 정렬 불가능
				impossible = true;
				break;
			}
			int idx = q.poll();
			sb.append(idx).append("\n"); // 정렬 순서
			for (int np : arr[idx]) {
				if (--inDegree[np]==0) { // 다음 입구 추가
					q.offer(np);
				}
			}
		}
		System.out.print(impossible ? 0:sb);
	}
}
