import java.util.*;
import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException{
        int M = readInt(); // 가로 길이 (열)
        int N = readInt(); // 세로 길이 (행)

        int[][] tomato = new int[N][M];
        int yet = 0;
        Queue<int[]> q = new ArrayDeque<>();

        for (int i=0;i<N;i++) for (int j=0;j<M;j++) {
            if ((tomato[i][j]=readInt())==0) {
                yet++;
            } else if (tomato[i][j]==1) {
                q.offer(new int[] {i, j});
            }
        }

        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{1, -1, 0, 0};

        if (yet == 0) { // 조기 종료: 모든 토마토가 익어 있음
            System.out.print(0);
            return;
        }


        int day = 1;

        while(!q.isEmpty()) {
            int size = q.size();
            while(size-->0) {
                int[] cur = q.poll();
                for (int k=0;k<4;k++) {
                    int nx = cur[0] + dx[k];
                    int ny = cur[1] + dy[k];
                    if (nx<0||nx>=N||ny<0||ny>=M||tomato[nx][ny]!=0) continue;

                    tomato[nx][ny] = 1;
                    q.offer(new int[] {nx, ny});
                    yet--;
                }
            }
            if (yet==0) break;
            day++;
        }

        System.out.print((yet>0) ? -1 : day);
    }
}
