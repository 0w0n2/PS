import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int [][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());   // 가로
        n = Integer.parseInt(st.nextToken());   // 세로
        map = new int [n][m];
        boolean tomato = false;
        Queue<int[]> q = new LinkedList<>();

        for (int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==0) tomato = true;
                if (map[i][j]==1) q.offer(new int[] {i, j});
            }
        }
        if (!tomato){
            System.out.println(0);
            return;
        }

        System.out.println(bfs(q));
    }

    public static int bfs(Queue<int[]> q) {
        
        int [] dx = {0, 0, 1, -1};
        int [] dy = {1, -1, 0, 0};
        
        int days = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] xy = q.poll();
                int x = xy[0];
                int y = xy[1];

                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    
                    if (map[nx][ny] == 0) {
                        map[nx][ny] = 1; 
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
            days++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    return -1;
                }
            }
        }
        return days - 1;
    }
}
