import java.io.*;
import java.util.*;

public class Main {
	
	static int [] par;
	static int [] height;
	static HashMap<String, Integer> people;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		
		StringBuilder sb = new StringBuilder();
		for (int t=0;t<tc;t++) {
			int F = Integer.parseInt(br.readLine()); // 친구 관계의 수
			
			par = new int[F*2];
			Arrays.fill(par, -1);
			
			height = new int[F*2];
			Arrays.fill(height, 1);
			
			people = new HashMap<String, Integer>();
			
			StringTokenizer st;
			int id = 0;
			
			for (int i=0;i<F;i++) {
				st = new StringTokenizer(br.readLine());
				String p1 = st.nextToken();
				String p2 = st.nextToken();
				if (!people.containsKey(p1)) people.put(p1, id++);
				if (!people.containsKey(p2)) people.put(p2, id++);
				
				sb.append(merge(people.get(p1), people.get(p2))).append("\n");
			}
		}
		System.out.print(sb);
	}
	
	public static int find(int n) {
		if (par[n]==-1) return n;
		return par[n] = find(par[n]);
	}
	
	public static int merge(int p1, int p2) {
		p1 = find(p1);
		p2 = find(p2);
		
		if (p1!=p2) {
			if (height[p1]>height[p2]) {
				par[p2] = p1;
				height[p1] += height[p2];
				return height[p1];
			}
			else {
				par[p1] = p2;
				height[p2] += height[p1];
				return height[p2];
			}
		}

		return height[p1];
	}
}