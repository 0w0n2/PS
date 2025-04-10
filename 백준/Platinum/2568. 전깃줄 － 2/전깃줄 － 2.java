import java.util.*;
import java.io.*;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
	
	private static class Node implements Comparable<Node>{
		int a, b;
		Node(int a, int b){
			this.a = a;
			this.b = b;
		}
		@Override
		public int compareTo(Node o) {
			return this.a - o.a;
		}
	}
	
	public static void main(String[] args) throws IOException{
		int N = readInt(); // 전깃줄의 개수
		Node[] line = new Node[N];
		for (int i=0;i<N;i++) line[i] = new Node(readInt()-1, readInt()-1);
		Arrays.sort(line); // A 전봇대의 전깃줄을 기준으로 정렬
		
		// dp + 이분탐색
		dp = new int[N];
		
		int[] idx = new int[N+1];
		int[] prev = new int[N];
		Arrays.fill(prev, -1);
		int len = 0;
		for (int i=0;i<N;i++) {
			int b = line[i].b;
			int curLen = binarySearch(1, len, b);
			dp[curLen] = b;
			idx[curLen] = i;
			
			if (curLen>1) prev[i] = idx[curLen-1];
			len = Math.max(len, curLen);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(N-len).append("\n");
		
		HashSet<Integer> inverse = new HashSet<>();
		int lastIdx = idx[len];
		while(lastIdx!=-1) {
			inverse.add(line[lastIdx].a);
			lastIdx = prev[lastIdx]; // 역으로 이어진 인덱스 찾아가기
		}
		for (int i=0;i<N;i++) {
			if (!inverse.contains(line[i].a)) {
				sb.append(line[i].a+1).append("\n");
			}
		}
		System.out.print(sb);
	}
	
	static int[] dp;
	
	static int binarySearch(int left, int right, int key) {
		while(left<=right) {
			int mid = (left+right)/2;
			if (dp[mid]<key) left = mid+1;
			else right = mid-1;
		}
		return left;
	}
}
