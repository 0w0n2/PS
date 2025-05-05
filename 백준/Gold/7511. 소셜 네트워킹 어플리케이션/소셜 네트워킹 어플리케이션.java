import java.io.*;

public class Main {
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	private static int find(int x) {
		if (par[x]<0) return x;
		return par[x] = find(par[x]);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a==b) return;
		
		if (a>b) par[b] = a;
		else par[a] = b;
		
		return;
	}
	
	static int par[] = new int[1_000_001];
	
	public static void main(String[] args) throws IOException{
		int T = readInt(); // 테스트 케이스의 수
		
		StringBuilder sb = new StringBuilder();
		for (int t=1;t<=T;t++) {
			sb.append("Scenario ").append(t).append(":\n");
			
			int N = readInt(); // 유저의 수
			for (int i=0;i<=N;i++) par[i] = -1;
			
			int K = readInt(); // 친구 관계의 수
			for (int i=0;i<K;i++) {
				int a = readInt();
				int b = readInt();
				union(a, b);
			}
			
			int M = readInt(); // 구할 쌍의 개수
			for (int i=0;i<M;i++) {
				int u = readInt();
				int v = readInt();
				sb.append(find(u)==find(v) ? "1\n":"0\n");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}
