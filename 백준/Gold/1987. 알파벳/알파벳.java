import java.util.*;
import java.io.*;

public class Main {
    static int r, c;
    static String [][] map;
    static int max = 1;

    static boolean [] alpha = new boolean[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()); // 가로
        c = Integer.parseInt(st.nextToken()); // 세로
        map = new String[r][c];
        for (int i=0;i<r;i++){
            String ip = br.readLine();
            for (int j=0;j<ip.length();j++){
                map[i][j] = String.valueOf(ip.charAt(j));
            }
        }
        alpha[map[0][0].charAt(0)-'A'] = true;
        dfs(0, 0, 1);
        System.out.println(max);
    }
    
    public static void dfs(int x, int y, int count){
        int [] dx = {1, -1, 0, 0};
        int [] dy = {0, 0, 1, -1};
        max = Math.max(count, max);

        for (int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx<0||nx>=r||ny<0||ny>=c) continue;
            if (!alpha[map[nx][ny].charAt(0)-'A']) {
                alpha[map[nx][ny].charAt(0)-'A'] = true;
                dfs(nx, ny, count+1);
                alpha[map[nx][ny].charAt(0)-'A'] = false;
            }
        }
    }
}
