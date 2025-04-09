import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException{
		int N = readInt(); // 수열 A의 크기
		int[] num = new int[N];
		
		for (int i=0;i<N;i++) num[i] = readInt(); // 수열 A를 이루고 있는 Ai들..(수퍼노바)
		
		StringBuilder sb = new StringBuilder();
		int len = LIS(N, num);
		sb.append(len).append("\n");
		
		ArrayList<Integer> inverse = new ArrayList<>();
		int idx = position[len];
		while(idx!=-1) {
			inverse.add(num[idx]);
			idx = prev[idx];
		}
		for (int i=len-1;i>=0;i--) sb.append(inverse.get(i)).append(" ");
		System.out.print(sb);
	}
	
	static int[] dp;
	static int[] position; // 각 길이별 마지막 인덱스 저장
	static int[] prev; // 각 길이별 이전 인덱스 저장 
	
	private static int LIS(int N, int[] num) {
		// LIS를 구하는데, DP+이분탐색으로 시간복잡도 줄이기
		dp = new int[N+1]; // 어떤 len (길이)를 갖는 부분 수열 중에 마지막 값이 최소값인 애들의 마지막값을 담음
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		prev = new int[N];
		position = new int[N+1];
		Arrays.fill(prev, -1);
		
		int len = 0;
		for (int i=0;i<N;i++) {
			int idx = binarySearch(1, len, num[i]);
			dp[idx] = num[i];
			
			position[idx] = i; // 현재 길이에서의 인덱스 저장
			if (idx>1) prev[i] = position[idx-1]; // 이전 인덱스 저장
			
			if (idx>len) len = idx;
		}
		
		return len;
	}
	
	// dp[0]~dp[len] 구간에서 num[i] 보다 작은 마지막값을 가지면서 가장 긴 len을 찾음
	private static int binarySearch(int left, int right, int key) {
		while(left<=right) {
			int mid = (left+right)/2;
			if (dp[mid]<key) { // 더 긴 len을 찾아보자
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}
}
