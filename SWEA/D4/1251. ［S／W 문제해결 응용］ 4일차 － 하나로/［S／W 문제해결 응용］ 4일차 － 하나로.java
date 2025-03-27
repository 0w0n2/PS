import java.io.*;
import java.util.*;


public class Solution {
	static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	static class Node implements Comparable<Node>{
		int v, u;
		double length;
		Node(int v, int u, double length){
			this.v = v;
			this.u = u;
			this.length = length;
		}
		
		@Override
		public int compareTo(Node o) {
			return Double.compare(this.length, o.length);
		}
		
	}
	
	private static int find(int x) {
		if (par[x]<0) return x;
		return par[x] = find(par[x]); // 경로압축하면서 최상위 루트 노드까지 접근
	}
	
	private static boolean merge(int a, int b) { // 유니온 연산
		a = find(a);
		b = find(b);
		
		if (a==b) return false; // 이미 더 최소 비용으로 병합된 간선
		
		par[a] = b; // 병합
		return true;
	}
	
	static int[] par;
	
	public static void main(String[] args) throws IOException{
		
		int T = nextInt(); // 테스트케이스의 수

		StringBuilder sb = new StringBuilder();
		for (int t=1;t<=T;t++) {
			int N = nextInt(); // 섬의 개수
			int[] xCor = new int[N];
			int[] yCor = new int[N];

			for (int i=0;i<N;i++) xCor[i] = nextInt();
			for (int i=0;i<N;i++) yCor[i] = nextInt();
			
			double tax = nextDouble();
			
			ArrayList<Node> way = new ArrayList<>();
			par = new int[N];
			Arrays.fill(par, -1);
			
			for (int i=0;i<N;i++) {
				for (int j=i+1;j<N;j++) {
					int xDiff = xCor[i]-xCor[j];
					int yDiff = yCor[i]-yCor[j];
					
					double cL = (double)xDiff*xDiff + (double)yDiff*yDiff;
					way.add(new Node(i, j, cL));
				}
			}
			
			Collections.sort(way);
			
			int ct = 0;
			double totalCost = 0.0;
			for (int i=0;i<way.size();i++) {
				if (ct==N) break;
				
				if (merge(way.get(i).v, way.get(i).u)) {
					ct++;
					totalCost += way.get(i).length;
				}
			}
			sb.append("#").append(t).append(" ").append((long)Math.round(totalCost*tax)).append("\n");
			
		}
		System.out.print(sb);
	}
	
	static private int nextInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	static private double nextDouble() throws IOException{
		st.nextToken();
		return st.nval;
	}
}