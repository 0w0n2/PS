import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int [] dx = {1, -1, 0, 0};
    static int [] dy = {0, 0, 1, -1};
    static int [][] move;
    static int [][] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i=0;i<n;i++){
            String ip = br.readLine();
            for (int j=0;j<m;j++) map[i][j] = ip.charAt(j) - '0';
        }
        move = new int[n][m]; // visited 대신에 move 씀... visited 역할을 하면서 얼마나 이동했는지도 저장함
        dfs(0, 0);

    }

    public static void dfs(int startX, int startY){
        Queue<int []> q = new LinkedList<>();
        q.offer(new int[] {startX, startY});
        move[startX][startY] = 1;   // 시작 지점이니까 지난 칸 : 1

        while(!q.isEmpty()){
            int [] now = q.poll();  // 현재 위치한 노드
            int X = now[0];
            int Y = now[1];
            if (X==(n-1) && Y==(m-1)){
                System.out.print(move[X][Y]);   // 가장 빨리 목표 지점에 도달한 BFS 경로가 곧 최단 거리
                return;
            }

            for (int i=0;i<4;i++){  // 현재 위치와 연결된 모든 노드들을(상하좌우) 델타로 찾고, 이동 가능하면 움직임
                int nx = X + dx[i];
                int ny = Y + dy[i];
                if (nx<0 || nx>=n || ny<0 || ny>=m) continue;

                if (map[nx][ny]==1 && move[nx][ny]==0){ // move=0 (한 번도 방문 X), map=1 (1 적혀있는 곳만 이동 가능)
                    q.offer(new int [] {nx, ny});
                    move[nx][ny] = move[X][Y] + 1;      // 다음에 이동할 좌표의 역대 지난 칸 값은 (현재 move 값 + 1)
                }
            }
        }

    }


}
