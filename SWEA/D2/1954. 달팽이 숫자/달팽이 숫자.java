import java.util.*;
import java.io.*;

public class Solution {
    private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int T = readInt(); // 테케
        StringBuilder sb = new StringBuilder();

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0}; // 우, 하, 좌, 상

        for (int t=1;t<=T;t++){
            sb.append("#").append(t).append("\n");
            int N = readInt();
            int[][] nums = new int[N][N];
            int dir=0, x=0, y=0, ct=1;

            while (ct<=N*N){
                nums[x][y] = ct++;
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (nx<0 || ny<0 || nx>=N || ny>=N || nums[nx][ny]!=0) {
                    dir = (dir + 1) % 4;
                    nx = x + dx[dir]; // 재계산
                    ny = y + dy[dir];
                }
                x = nx;
                y = ny;
            }

            for (int i=0;i<N;i++) {
                for (int j=0;j<N;j++) sb.append(nums[i][j]).append(" ");
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
