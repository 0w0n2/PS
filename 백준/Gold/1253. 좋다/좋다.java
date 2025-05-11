import java.util.*;
import java.io.*;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException{
		N = readInt(); // 수의 개수 (1<=N<=2_000)
		arr = new ArrayList<>();
		for (int i=0;i<N;i++) arr.add(readInt()); // i개 수 (|Ai|<=1_000_000_000) *2 해도 2억 안 넘음
		
		Collections.sort(arr); // 오름차순 정렬
		
		// i개 숫자마다 이분 탐색 시작
		int result = 0;
		for (int i=0;i<N;i++) {
			if(twoPointerSearch(i)) result++;
		}
		
		System.out.print(result);
	}
	
	static ArrayList<Integer> arr;
	static int N;
	
	private static boolean twoPointerSearch(int keyIdx) {
		
		int start = 0;
		int end = N-1;
		int key = arr.get(keyIdx);
		
		while(start<end) {
			
			if (start==keyIdx) {
				start++;
				continue;
			}
			if (end==keyIdx) {
				end--;
				continue;
			}

			int nowSum = arr.get(start) + arr.get(end); // 현재 합
			
			/*
			 * sum > key : end 를 줄인다 (너무 큼)
			 * sum >= key : return true 
			 * sum < key : start 를 늘린다 (부족해)
			 * 
			 * start<=end 조건 벗어나면 ㅂㅂ
			 */
			if (nowSum == key) return true;
			else if (nowSum < key) start++;
			else end--;
		}
		
		return false;
		
	}
}
