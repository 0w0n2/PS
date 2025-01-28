import java.util.*;
import java.io.*;
public class Main {
    static int n, m;
    static int[][] map;
    static Queue<int[]> virus = new LinkedList<>();
    static int maxArea = 0;

    public static void main(String[] args) throws IOException {
        
        // 1. 어디에 벽 3개를 세울지 정하자 -> 브루트포스 [wall() 메서드]
        // 2. (1)에서 세운 벽을 사용했을 때, 안전영역은 얼마가 될까? -> bfs (바이러스가 여러마리라) [bfs() 메서드]
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virus.offer(new int[]{i, j});
            }
        }
        wall(0);
        System.out.println(maxArea);

    }
    public static int bfs(){ // 브루트포스로부터 얻은 맵에서, 바이러스들은 가능한 모든 0을 오염시킴(bfs)
        int [] dx = {0, 0, 1, -1};
        int [] dy = {1, -1, 0, 0};
        Queue<int[]> q = new LinkedList<>(virus);   // 전역변수 virus -> 로컬변수(q)
        int[][] wallMap = new int[n][m];            // 전역변수 map -> 로컬변수(wallMap) -> 이 두 작업 안 하면 전역변수들이 오염됨(wall() 처럼 복구 기능이 없기 때문에)
        for (int i=0;i<n;i++) wallMap[i] = Arrays.copyOf(map[i], map[i].length);

        while(!q.isEmpty()){
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];

            for (int i=0;i<4;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if (nx<0 || nx>=n || ny<0 || ny>=m || wallMap[nx][ny]!=0) continue; // 벽(1)이거나 오염된 곳(2)는 넘어감
                q.offer(new int[] {nx, ny});
                wallMap[nx][ny] = 2; // 바이러스에 의해 오염
            }
        }
        int safeArea = 0;   // 바이러스로 최대한 오염시킨 후(while 문 끝) 남은 안전영역(0) 구하기
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (wallMap[i][j]==0) safeArea++;
            }
        }
        return safeArea;
    }

    public static void wall(int cnt){ // 브루트포스로 벽 3개 세우는 모든 경우를 탐색함
        if (cnt==3) {   // 벽 3개 세웠으면 그 map으로 bfs 탐색해서 안전 영역 찾기
            maxArea = Math.max(maxArea, bfs()); // 모든 벽 세우기 경우에서 안전영역의 최댓값을 maxArea에 저장
            return;
        }

        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (map[i][j]==0) {
                    // (i,j) 위치에 벽을 세움
                    map[i][j] = 1;
                    wall(cnt+1);
                    map[i][j] = 0;  // 복구(백트래킹)
                }
            }
        }
    }
}
