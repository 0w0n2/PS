import java.util.*;
import java.io.*;

class Main
{	
	public static boolean isMove;
	public static int n;
	public static ArrayList<ArrayList<Integer>> jump = new ArrayList<ArrayList<Integer>>();
	public static int [] isVisited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<n;i++) jump.add(new ArrayList<>()); // 리스트 초기화(0~n 칸 만들어줌)
		isVisited = new int [n];
		
		for (int i=0;i<n;i++) {
			int available = Integer.parseInt(st.nextToken());
			for (int j=1; j<=available; j++) {
				int ni = i+j;
				if (ni>=n) continue;
				jump.get(i).add(ni);
			}
		}
		bfs(0);
		if(isMove) System.out.print(isVisited[n-1]);
		else System.out.print(-1);
		
		// 쩜푸쩜푸
	}
	
	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		isVisited[start] = 0;
		q.offer(start);
		
		while(!q.isEmpty()) {
			int i = q.poll();
			if (i==n-1) {
				isMove = true;
				return;
			}
			for (int j=0; j<jump.get(i).size(); j++) {
				int ni = jump.get(i).get(j);
				if (isVisited[ni]==0) {
					q.offer(ni);
					isVisited[ni] = isVisited[i] + 1;
				}
			}
		}
	}
}

