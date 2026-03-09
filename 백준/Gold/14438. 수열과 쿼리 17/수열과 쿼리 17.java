import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	private static int N;
	// 버켓 사이즈를 정하는 기준은 보통 루트N
	private static final int BUCKET_SIZE = 320;	// 버켓 하나당 크기
	private static int[] arr;
	private static int[] bucketMin;

	private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	static void update(int i, int v) {
		arr[i] = v;
		int bIdx = i/BUCKET_SIZE;
		
		int start = Math.max(0, bIdx * BUCKET_SIZE);
		int end = Math.min(start + BUCKET_SIZE -1, N-1);
		
		int min = Integer.MAX_VALUE;
		for (int k=start; k<=end; k++) {
			if (arr[k] < min) {
				min = arr[k];
			}
		}
		bucketMin[bIdx] = min;
	}
	
	static int get(int i, int j) {
		int res = Integer.MAX_VALUE;
		int sB = i / BUCKET_SIZE;	// 시작 버켓 인덱스
		int eB = j / BUCKET_SIZE; 	// 끝 버켓 인덱스
		
		if (sB == eB) {	// 같은 버켓 내에 있을 경우
			for (int k=i; k<=j; k++) {
				res = Math.min(res, arr[k]);
			}
		} else {
			int sEnd = (sB + 1) * BUCKET_SIZE - 1;
			// i부터 해당 버켓 끝까지
			for (int k=i; k<=sEnd; k++) {
				res = Math.min(res, arr[k]);
			}
			// 중간 버켓
			for (int k=sB+1; k<eB; k++) {
				res = Math.min(res, bucketMin[k]);
			}
			// 마지막 버켓 시작부터 j까지
			int eStart = eB * BUCKET_SIZE;
			for (int k=eStart; k<=j; k++) {
				res = Math.min(res, arr[k]);
			}
		}
		return res;
	}
	
	public static void main(String[] args) throws IOException{
		N = readInt();
		arr = new int[N];
		int bucketCount = N/BUCKET_SIZE + 1;
		bucketMin = new int[bucketCount];
		Arrays.fill(bucketMin, Integer.MAX_VALUE);
		
		for (int i=0;i<N;i++) {
			arr[i] = readInt();
			int bIdx = i/BUCKET_SIZE;
			bucketMin[bIdx] = Math.min(bucketMin[bIdx], arr[i]);
		}
		
		// 명령 시작
		StringBuilder sb = new StringBuilder();
		int M = readInt();
		while (M-->0) {
			switch (readInt()) {
			case 1:
				update(readInt()-1, readInt());
				break;
			case 2:
				sb.append(get(readInt()-1, readInt()-1)).append("\n");
				break;
			}	
		}
		
		System.out.print(sb);
	}
}
