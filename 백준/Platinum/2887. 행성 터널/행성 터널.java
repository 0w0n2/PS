import java.util.*;
import java.io.*;

public class Main {
	
	static class Node{
		int start, end, dist;
		Node(int start, int end, int dist){
			this.start = start;
			this.end = end;
			this.dist = dist;
		}
	}
	
	static int[] par;
	static int[] height;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 행성 수
		
		par = new int[n];
		Arrays.fill(par, -1);
		height = new int[n];
		Arrays.fill(height, 1);

		int[][] coordinate = new int[n][4]; // 0:행성 번호, 1:x, 2:y, 3:z
		
		StringTokenizer st;
		for (int i=0;i<n;i++) {
			coordinate[i][0] = i; // 행성 번호
			st = new StringTokenizer(br.readLine());
			for (int j=1;j<4;j++) coordinate[i][j] = Integer.parseInt(st.nextToken()); // 각 x,y,z 좌표
		}
		
		List<Node> q = new ArrayList<>(); 
		for (int i=1;i<4;i++) {
			final int idx = i;
			Arrays.sort(coordinate, (o1, o2) -> Integer.compare(o1[idx], o2[idx])); // 가능한 인접 좌표끼리 이어지게(두 행성의 좌표간의 차이가 적게) 정렬
			for (int j=1; j<n; j++) {
				q.add(new Node(coordinate[j-1][0], coordinate[j][0], Math.abs(coordinate[j-1][i]-coordinate[j][i])));
			}
		}
		
		Collections.sort(q, (o1, o2) -> o1.dist - o2.dist); // dist(거리 가중치) 짧은 순으로 큐 정렬 
		
		int step = 0;
		int result = 0;
		for (Node current : q) {
			if (merge(current.start, current.end)) {
				result += current.dist;
				step ++;
			}
			if (step==n-1) break;
		}
		System.out.print(result);
	}
	
	static int find(int k) {
		if (par[k]==-1) return k;
		return par[k] = find(par[k]);
	}
	
	static boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a==b) return false;
		
		if (height[a]>height[b]) par[b] = a;
		else {
			par[a] = b;
			if (height[a]==height[b]) height[b]++;
		}
		return true;
	}
}