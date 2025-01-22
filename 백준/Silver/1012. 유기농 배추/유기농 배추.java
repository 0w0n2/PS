import java.util.*;
import java.io.*;

public class Main {
    static boolean [][] location;
    static boolean [][] isVisited;
    static int [] dx = {1, -1, 0, 0};
    static int [] dy = {0, 0, 1, -1};
    static int width, height;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        for (int step = 0;step<tc;step++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            width = Integer.parseInt(st.nextToken());
            height = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            location = new boolean[width][height]; // 배추 위치 초기화
            isVisited = new boolean[width][height]; // 방문 유무 초기화
            int count = 0; // 컴포넌트(배추흰지렁이 마리 수) 초기화

            for (int i=0;i<k;i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                location[x][y] = true;
            }
            for (int i=0;i<width;i++){
                for (int j=0;j<height;j++){
                    if (!isVisited[i][j]&&location[i][j]){
                        count++;
                        dfs(i, j);
                    }
                }
            }
            bw.write(count+"\n");
        }
        bw.flush();
        bw.close();
    }

    public static void dfs(int x, int y){
        isVisited[x][y] = true;

        for (int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx<0 || nx>=width || ny<0 || ny>=height) continue;
            if (!isVisited[nx][ny] && location[nx][ny]) dfs(nx, ny);
        }
    }
}
