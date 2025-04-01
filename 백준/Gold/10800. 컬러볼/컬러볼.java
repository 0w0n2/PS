import java.util.*;
import java.io.*;

public class Main {
	static class Ball {
		int idx, color;
		Ball(int idx, int color){
			this.idx = idx;
			this.color = color;
		}
	}
	
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int inputInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException{
		int N = inputInt();
		
		ArrayList<ArrayList<Ball>> b = new ArrayList<>();
		for (int i=0;i<=2000;i++) b.add(new ArrayList<>());
		
		for (int i=0;i<N;i++) {
			int color = inputInt();
			int size = inputInt();
			b.get(size).add(new Ball(i, color));
		}
		
		int[] result = new int[N];
		int[] prefixSumPerColor = new int[N+1];
		int total = 0;

		for (int i=1;i<=2000;i++) {
			ArrayList<Ball> bi = b.get(i);
			if (bi.isEmpty()) continue;
			
			for (int j=0;j<bi.size();j++) {
				result[bi.get(j).idx] = total - prefixSumPerColor[bi.get(j).color];
			}
			for (int j=0;j<bi.size();j++) {
				prefixSumPerColor[bi.get(j).color] += i;
				total += i;
			}
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i:result) sb.append(i).append("\n");
		System.out.print(sb);
	} 
}
