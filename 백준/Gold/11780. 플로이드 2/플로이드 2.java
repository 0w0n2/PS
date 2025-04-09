import java.util.*;
import java.io.*;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
    
    public static void main(String[] args) throws IOException{
    	int N = readInt(); // 도시 개수
    	int M = readInt(); // 버스 개수
    	
    	int[][] dist = new int[N][N];
    	prev = new int[N][N]; // 이동 도시
    	int INF = 1_000_000_000;
    	for (int i=0;i<N;i++) {
    		Arrays.fill(dist[i], INF);
    		dist[i][i] = 0;
    		prev[i][i] = i;
    	}
    	
    	for (int i=0;i<M;i++) {
    		int a = readInt()-1;
    		int b = readInt()-1;
    		int len = readInt();
    		if (dist[a][b]>len) {
    			dist[a][b] = len;
    			prev[a][b] = b;
    		}
    	} 
    	
    	for (int k=0;k<N;k++) {
    		for (int i=0;i<N;i++) {
    			for (int j=0;j<N;j++) {
    				if (dist[i][j]>dist[i][k]+dist[k][j]) {
    					dist[i][j] = dist[i][k]+dist[k][j];
    					prev[i][j] = prev[i][k];
    				}
    			}
    		}
    	}
    	
    	for (int i=0;i<N;i++) {
    		for (int j=0;j<N;j++) {
    			sb.append((dist[i][j]>=INF?0:dist[i][j])+" ");
    		}
    		sb.append("\n");
    	}
    	
    	for (int i=0;i<N;i++) {
    		for (int j=0;j<N;j++) {
    			if (i==j || dist[i][j]>=INF) sb.append("0\n");
    			else {
    				sb.append(recursive(i, j));
    			}
    		}
    	}
    	System.out.print(sb);
    }
    static int[][] prev;
    static StringBuilder sb = new StringBuilder();
    
    static StringBuilder recursive(int start, int end) {
    	int ct = 1;
    	StringBuilder nsb = new StringBuilder();
    	int cur = start;
    	while(cur!=end) {
    		nsb.append(cur+1).append(" ");
    		ct++;
    		cur = prev[cur][end];
    	}
    	nsb.append(end+1).append("\n");
    	sb.append(ct).append(" ");
    	return nsb;
    }
}
