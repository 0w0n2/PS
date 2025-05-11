import java.util.*;
import java.io.*;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException{
		int N = readInt(); // 전체 용액의 수
		int[] arr = new int[N];
		
		for (int i=0;i<N;i++) arr[i] = readInt(); // 용액 별 pH
		Arrays.sort(arr); // 오름차순 정렬
		
		// 투 포인터 - 용액 별 
		// 출력 : 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값을 출력
		//		 - 두 용액은 오름차순으로 / 같은 최적값을 만들어내는 경우가 여러개면 하나만 출력하면 됨
		
		int left = 0;
		int right = N-1;
		int minSum = Integer.MAX_VALUE;
		int minIdxLeft = 0, minIdxRight = 0;
		
		while(left<right) {
			int sum = arr[left] + arr[right];
			
			if (Math.abs(sum) < minSum) {
				minSum = Math.abs(sum);
				minIdxLeft = left;
				minIdxRight = right;
			}
			
			if (sum < 0) left++;
			else right--;
		}
		
		System.out.print(arr[minIdxLeft]+arr[minIdxRight]);
	}
}
