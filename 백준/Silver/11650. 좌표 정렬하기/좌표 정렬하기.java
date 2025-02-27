import java.util.*;
import java.io.*;

public class Main {
	static class Node implements Comparable<Node>{
		int x, y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.x!=o.x ? this.x-o.x:this.y-o.y;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] coordinate = new Node[N];
		StringTokenizer st;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			coordinate[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(coordinate);
		StringBuilder sb = new StringBuilder();
		for (Node c:coordinate) {
			sb.append(c.x).append(" ").append(c.y).append("\n");
		}
		System.out.println(sb);
	}
}