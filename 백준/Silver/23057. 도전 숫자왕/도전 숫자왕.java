import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 카드의 수
		
		nums = new int[N]; // 카드 별 숫자
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i=0;i<N;i++) {
			M += (nums[i] = Integer.parseInt(st.nextToken()));
			// 모든 카드에 적힌 수의 합
		}
		System.out.print(M-recur(0, 0)); 
	}
	
	static int N, M=0;
	static int[] nums;
	static HashSet<Integer> set = new HashSet<>();
	
	private static int recur(int idx, int nowSum) {
		int result = 0;
		
		if (idx==N) { // 공집합이 아니거나 이전에 만든 중복된 합 결과가 아니라면 체크
			if (nowSum!=0 && !set.contains(nowSum)) {
				set.add(nowSum);
				return 1;
			}
			return 0; // 기저조건
		}
		
		result += recur(idx+1, nowSum); // idx 번째 수를 사용하지 않음
		result += recur(idx+1, nowSum+nums[idx]); // idx 번째 수를 사용함
		return result;
	}
}
