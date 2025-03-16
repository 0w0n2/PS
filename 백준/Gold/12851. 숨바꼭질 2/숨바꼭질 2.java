import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		
		class Subin{
			int x, time;
			Subin(int x, int time){
				this.x = x;
				this.time = time;
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Deque<Subin> q = new ArrayDeque<>();
		q.offerLast(new Subin(Integer.parseInt(st.nextToken()), 0));
		int end = Integer.parseInt(st.nextToken());
		
		int[] isVisited = new int[100_001];
		isVisited[q.peek().x] = 0;
		
		int t = -1;
		int ct = 0;
		
		int[] dx = {-1, 1};
		
		while(!q.isEmpty()) {
			
			Subin c = q.pollFirst();
			if (c.x==end) {
				t = c.time;
				ct++;
			}
			
			for (int i=0;i<3;i++) {
				int nx = (i<2) ? c.x + dx[i] : c.x * 2;
				
				if (nx<0||nx>=100_001||(t!=-1 && c.time+1 > t)) continue;
				
				if (isVisited[nx]==0 || isVisited[nx]==c.time+1){
					isVisited[nx] = c.time+1;
					q.offerLast(new Subin(nx, c.time+1));
				}
			}
		}
		System.out.printf("%d\n%d", t, ct);
	}
}
