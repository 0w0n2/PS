import java.io.*;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
    
	public static void main(String[] args) throws IOException{
		N = readInt(); // 행성 개수
		int K = readInt(); // 발사 위치
		dist = new int[N][N];

		for (int i=0;i<N;i++) for (int j=0;j<N;j++) dist[i][j] = readInt();
		
		for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++) {
			dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
		}

		isVisited = new boolean[N];
		isVisited[K] = true;
		recursive(1, K, 0);
		System.out.print(result);
		
	}
	
	static int N;
	static int result = Integer.MAX_VALUE;
	static boolean[] isVisited;
	static int[][] dist;
	
	static void recursive(int ct, int row, int sum) {
		if (sum>result) return;
		
		if (ct==N) {
			result = Math.min(result, sum);
			return;
		}
		
		for (int i=0;i<N;i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				recursive(ct+1, i, sum+dist[row][i]);
				isVisited[i] = false;
			}
		}
	}
}
