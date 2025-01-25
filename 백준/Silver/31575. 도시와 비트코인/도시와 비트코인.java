import java.util.*;
import java.io.*;

public class Main {
    static int [] dx = {1, 0}; // 동 / 남
    static int [] dy = {0, 1};
    static int n;
    static int m;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 가로
        m = Integer.parseInt(st.nextToken()); // 세로
        int [][] map = new int[m][n]; // 세로 * 가로
        for (int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        if (bfs(0, 0, map)) System.out.print("Yes");
        else System.out.print("No");
    }

    public static boolean bfs(int xs, int ys, int[][] map){
        boolean [][] isVisited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {xs, ys});
        isVisited[xs][ys] = true;

        while(!q.isEmpty()){
            int [] now = q.poll();
            int x = now[0];
            int y = now[1];
            if (x==(m-1)&&y==(n-1)) return true;

            for (int i=0;i<2;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx<0||nx>=m||ny<0||ny>=n) continue;

                if (!isVisited[nx][ny] && map[nx][ny]==1) {
                    q.offer(new int[]{nx, ny});
                    isVisited[nx][ny] = true;
                }
            }
        }
        return false;
    }
}
