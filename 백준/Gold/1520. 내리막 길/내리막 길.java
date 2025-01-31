import java.io.*;
import java.util.*;
public class Main {
    static int m, n;
    static int [][] map;
    static int way = 0;
    static boolean [][] isVisited;
    static int [][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int [m][n];
        isVisited = new boolean[m][n];
        dp = new int[m][n];

        for (int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(0, 0));
    }
    public static int dfs(int x, int y){
        
        if (x == (m - 1) && y == (n - 1)) return 1;
        
        if (dp[x][y]!=-1) return dp[x][y];

        int [] dx = {1, -1, 0, 0};
        int [] dy = {0, 0, 1, -1};

        dp[x][y] = 0;

        for (int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx<0 || nx>=m || ny<0 || ny>=n || map[nx][ny]>=map[x][y]) continue;
            dp[x][y] += dfs(nx, ny);
        }
        return dp[x][y];
    }
}
