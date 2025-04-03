import java.util.*;
import java.io.*;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException{

		StringBuilder sb = new StringBuilder(); // 출력용 버퍼
		
		int N, ct=1;
		while((N=readInt())!=0) {
			int[][] map = new int[N][3];
			int[][] dp = new int[N][3];
			
			for (int i=0;i<N;i++) {
				map[i][0] = readInt();
				map[i][1] = readInt();
				map[i][2] = readInt();
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			
			// 0행 값 초기화
			dp[0][1] = map[0][1];

			for (int i=0;i<N;i++) {
				for (int j=0;j<3;j++){
					
					if (i==0&&j==0) continue;
					
					if((j+1)<3) dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+map[i][j+1]); // 오른쪽 이동 (우)
					
					if((i+1)<N) {
						dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+map[i+1][j]); // 아래쪽 이동 (하)
						if((j+1)<3) dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]+map[i+1][j+1]); // 오른쪽 아래 이동 (우하)
						if(0<j) dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]+map[i+1][j-1]); // 왼쪽 아래 이동 (좌하)
					}
				}
			}
			
			sb.append(ct++).append(". ").append(dp[N-1][1]).append("\n");
		}
		
		System.out.print(sb);
	}
}
