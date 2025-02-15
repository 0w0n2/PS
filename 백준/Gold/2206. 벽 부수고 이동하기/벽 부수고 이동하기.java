import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int x, y, move;
		boolean breakWall;
		boolean[] isVisited;
		Node(int x, int y, int move, boolean breakWall){
			this.x = x;
			this.y = y;
			this.move = move;
			this.breakWall = breakWall;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 세로 길이
		int M = Integer.parseInt(st.nextToken()); // 가로 길이
		int[][] field = new int[N][M];
		for (int i=0;i<N;i++) {
			field[i] = br.readLine().chars().map(x -> x-'0').toArray();
		}
		
		boolean[][][] isVisited = new boolean[N][M][2]; // 벽을 부순 여부에 맞춰 방문 여부를 처리할 것
		isVisited[0][0][0] = true;
		
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0, 1, false));
		
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			if(current.x==(N-1)&&current.y==(M-1)) {
				System.out.print(current.move);
				return;
			}
			
			for (int i=0;i<dx.length;i++) {
				int nx = dx[i] + current.x;
				int ny = dy[i] + current.y;
				
				if(nx<0||nx>=N||ny<0||ny>=M||isVisited[nx][ny][current.breakWall ? 1:0]) continue; // 벽은 한번만 부술 수 있음(current.breakWall = true 상태일 때, 이미 벽을 부쉈었음 이전에)

				if(field[nx][ny]==1 &&!current.breakWall) {
					isVisited[nx][ny][1] = true; // 방문 처리
					q.offer(new Node(nx, ny, current.move + 1, true)); // 벽을 부수고 이동
		
				} else if (field[nx][ny]==0) {
					isVisited[nx][ny][current.breakWall ? 1:0] = true; // 방문 처리
					q.offer(new Node(nx, ny, current.move + 1, current.breakWall)); // 벽을 부수지 않고 0으로 이동
				}
			}
		}
		System.out.print(-1);
		
	}
}