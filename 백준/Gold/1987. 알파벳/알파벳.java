import java.util.*;
import java.io.*;

public class Main {
    static int r, c;
    static char [][] map;
    static int max = 1;

    static boolean [] alpha = new boolean[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()); // 가로
        c = Integer.parseInt(st.nextToken()); // 세로
        map = new char [r][c];
        for (int i=0;i<r;i++){
            String ip = br.readLine();
            for (int j=0;j<ip.length();j++){
                map[i][j] = ip.charAt(j);
            }
        }
        // alpha[map[0][0]-'A'] = true;
        dfs(0, 0, 1);
        System.out.println(max);
    }

    public static void dfs(int x, int y, int count){
        int [] dx = {1, -1, 0, 0};
        int [] dy = {0, 0, 1, -1};
        if (x<0 || x>=r || y<0 || y>=c || alpha[map[x][y]-'A']) return;

        alpha[map[x][y]-'A'] = true;
        max = Math.max(count, max);
        dfs(x+dx[0], y+dy[0], count+1);
        dfs(x+dx[1], y+dy[1], count+1);
        dfs(x+dx[2], y+dy[2], count+1);
        dfs(x+dx[3], y+dy[3], count+1);
        alpha[map[x][y]-'A'] = false;
    }
}
