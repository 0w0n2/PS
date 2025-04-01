import java.util.*;
import java.io.*;

public class Main {
	static class Ball implements Comparable<Ball>{
		int idx, size, color;
		Ball(int idx, int size, int color){
			this.idx = idx;
			this.size = size;
			this.color = color;
		}
		@Override
		public int compareTo(Ball o) {
			return this.size-o.size;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Ball[] b = new Ball[N];
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			b[i] = new Ball(i, size, color);
		}
		Arrays.sort(b);
		
		int[] result = new int[N];
		int[] prefixSumPerColor = new int[N+1];
		int total = 0;
		int si = 0;
		
		for (int i=0;i<N;i++) {
			while(b[si].size<b[i].size) {
				total += b[si].size;
				prefixSumPerColor[b[si].color] += b[si].size;
				si++;
			}
			result[b[i].idx] = total - prefixSumPerColor[b[i].color];
		}
		StringBuilder sb = new StringBuilder();
		for (int i:result) sb.append(i).append("\n");
		System.out.print(sb);
	} 
}
