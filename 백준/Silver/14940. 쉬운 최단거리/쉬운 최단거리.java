import java.util.*;
import java.io.*;

public class Main {
    static int [][] map;
    static int [][] res;
    static boolean [][] isVisited;
    static int n, m;
    static int [] dx = {1, -1, 0, 0}; // 오직 가로와 세로로만 움직일 수 있음
    static int [] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 세로 길이 n
        m = Integer.parseInt(st.nextToken()); // 가로 길이 m
        map = res = new int [n][m];
        isVisited = new boolean[n][m];
        int sx = 0;
        int sy = 0;
        for (int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==2) {
                    sx = i;
                    sy = j;
                }
            }
        }
        bfs(sx, sy);
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++) sb.append(res[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);

    }
    public static void bfs(int sx, int sy){
        Queue<int []> q = new LinkedList<>();
        q.offer(new int[] {sx, sy});
        isVisited[sx][sy] = true;
        res[sx][sy] = 0;

        while(!q.isEmpty()){
            int [] current = q.poll();
            int x = current[0];
            int y = current[1];
            for (int i=0;i<4;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if (!isVisited[nx][ny] && map[nx][ny]==1){
                    isVisited[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                    res[nx][ny] = res[x][y]+1;
                }
            }
        }

        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (map[i][j]!=0 && !isVisited[i][j]) res[i][j] = -1;
            }
        }
    }
}
