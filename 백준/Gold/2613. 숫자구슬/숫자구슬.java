import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N];
		int sum = 0;
		int maxNum = 0;
		st = new StringTokenizer(br.readLine());
		
		for (int i=0;i<N;i++) {
			map[i] = Integer.parseInt(st.nextToken());
			sum += map[i];
			maxNum = Math.max(maxNum, map[i]);
		}
		
		int target = binarySearch(maxNum, sum);	// 이분 탐색으로 적절한 합 value 찾기
		
		StringBuilder sb = new StringBuilder();
		sb.append(target).append("\n");
		divideMember(target, sb);
		System.out.println(sb);
		
	}
	
	static int binarySearch(int start, int end) {	// 이분탐색으로
		while(start<=end) {
			int mid = (start+end)/2;
			if (validationCheck(mid)) end = mid-1; // 현재 고른 합(mid)가 M개의 그룹으로 나눠지면 더 최솟값을 찾아봄
			else start = mid+1;						// ㄴ 반대
		}
		return end+1;
	}
	
	static boolean validationCheck(int x) {	
		int ct = 1, nowSum = 0;	// 현재 묶음 개수, 현재 묶음의 합
		
		for (int i=0;i<N;i++) {
			nowSum += map[i];
			
			if (nowSum > x) {
				ct++;
				nowSum = map[i];
			}
		}
		return ct<=M;
	}
	
	static void divideMember(int target, StringBuilder sb) {
		int mem = 0;	// 현재 묶음에 포함된 공 개수
		int nowSum = 0;	// 현재 묶음의 합
		for (int i=0;i<N;i++) {
			nowSum += map[i];
			if (nowSum > target || N-i<M) {	
				// N-i<M : 현재 묶음 뒤로 아직 처리 안 한 그룹이 최소 1개씩 공을 가질 수 있도록 보장
				sb.append(mem).append(" ");
				nowSum = map[i];
				mem = 0;
				M--;
			}
			mem++;
		}
		sb.append(mem);
		
	}
}
