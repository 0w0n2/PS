import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	/**
	 * 1. 주식 하나를 판다
	 * 2. 원하는 만큼 가지고 이는 주식을 판다
	 * 3. 아무것도 안 한다
	 */
	public static void main(String[] args) throws IOException{
		int T = readInt();
		StringBuilder sb = new StringBuilder();
		
		while(T-->0) {
			int N = readInt();	// 날의 수
			int[] cost = new int[N];
			
			for (int i=0;i<N;i++) cost[i] = readInt();
			
			long result = 0;
			int edge = 0;
			
			for (int i=N-1;i>=0;i--) {
				if (cost[i] > edge) {
					edge = cost[i];
				} else {
					result += (edge - cost[i]);
				}
			}
			sb.append(result).append("\n");
		}
		System.out.print(String.valueOf(sb).trim());
	}
}
