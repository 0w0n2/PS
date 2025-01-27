import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int [n][m];
        isVisited = new boolean[n][m];
        boolean pic = false;
        for (int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==1) pic = true;
            }
        }
        if (!pic){
            System.out.println(0+"\n"+0);
            return;
        }

        int maxArea = Integer.MIN_VALUE;
        int count = 0;

        for (int i=0;i<n;i++) for (int j=0;j<m;j++){
            if (map[i][j]==1&&!isVisited[i][j]){
                maxArea = Math.max(bfs(i, j), maxArea);
                count++;
            }
        }
        System.out.println(count+"\n"+maxArea);
    }

    public static int bfs(int startX, int startY){
        Queue<int []> q = new LinkedList<>();
        q.offer(new int[] {startX, startY});
        isVisited[startX][startY] = true;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int areas = 0;

        while (!q.isEmpty()){
            int [] current = q.poll();
            int x = current[0];
            int y = current[1];
            for (int k=0;k<dx.length;k++){
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if (!isVisited[nx][ny] && map[nx][ny]==1){
                    isVisited[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                }
            }
            areas++;
        }
        return areas;
    }
}
