import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();

        Queue<Cor> q = new ArrayDeque<>();

        for (int i=0;i<N;i++){
            for (int j=0;j<M;j++) {
                int height = readInt();
                if (height>0){
                    q.offer(new Cor(i, j, height));
                }
            }
        }

        int year = 0;
        while(!q.isEmpty()){
            isIceberg = new boolean[N][M];
            for (Cor c : q) {
                isIceberg[c.x][c.y] = true;
            }

            int ct = 0;
            isVisited = new boolean[N][M];
            for (Cor c : q){
                if (isVisited[c.x][c.y]) continue;
                fillArea(c.x, c.y);
                ct++;
                if (ct>=2) {
                    System.out.print(year);
                    return;
                }
            }

            int size = q.size();

            while(size-->0){
                Cor c = q.poll();
                int zeroCt = 0;
                for (int k=0;k<4;k++){
                    int nx = c.x + dx[k];
                    int ny = c.y + dy[k];
                    if (nx<0||nx>=N||ny<0||ny>=M||isIceberg[nx][ny]) continue;
                    zeroCt++;
                }

                int nh = Math.max(c.height-zeroCt, 0);
                if (nh>0) {
                    q.offer(new Cor(c.x, c.y, nh));
                }
                // else isIceberg[c.x][c.y] = false;
            }

            year++;
        }

        System.out.print(0);
    }

    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};

    private static void fillArea(int i, int j){
        Queue<Cor> q = new ArrayDeque<>();
        q.offer(new Cor(i, j));
        isVisited[i][j] = true;

        while(!q.isEmpty()){
            Cor c = q.poll();
            for (int k=0;k<4;k++){
                int ni = c.x + dx[k];
                int nj = c.y + dy[k];
                if (ni<0||ni>=N||nj<0||nj>=M||isVisited[ni][nj]||!isIceberg[ni][nj]) continue;
                isVisited[ni][nj] = true;
                q.offer(new Cor(ni, nj));
            }
        }
    }

    private static int N, M;
    private static boolean isIceberg[][], isVisited[][];
    private static class Cor{
        int x, y, height;
        Cor(int x, int y){
            this.x = x;
            this.y = y;
        }
        Cor(int x, int y, int height){
            this(x, y);
            this.height = height;
        }
    }


    private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
