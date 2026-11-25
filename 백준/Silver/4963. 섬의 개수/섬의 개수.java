import java.util.*;
import java.io.*;

public class Main {
    // === 입력기 ===
    private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
    // ============

    private final static int[] dx = new int[]{-1, 1, 0, 0, -1, -1, 1, 1};
    private final static int[] dy = new int[]{0, 0, -1, 1, -1, 1, -1, 1};
    private static boolean[][] map;
    private static int width, height;

    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        while (true) {
            width = readInt();
            height = readInt();

            if (width == 0 && height == 0) break;

            map = new boolean[height][width];

            for (int i=0; i<height;i ++) {
                for (int j=0; j<width; j++) {
                    map[i][j] = readInt() == 1; // 섬O=true
                }
            }

            // bfs
            int ct = 0;
            for (int i=0; i<height; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j]) {
                        ct++;
                        bfs(i, j);
                    }
                }
            }

            sb.append(ct).append("\n");
        }
        System.out.print(sb);
    }

    static class Cor{
        int x, y;
        public Cor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void bfs(int i, int j) {
        Queue<Cor> q = new ArrayDeque<>();
        map[i][j] = false;
        q.offer(new Cor(i, j));

        while (!q.isEmpty()) {
            Cor cur = q.poll();

            for (int k=0; k<8; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if (nx<0 || nx>=height || ny<0 || ny>=width) continue;
                if (!map[nx][ny]) continue;

                map[nx][ny] = false;
                q.offer(new Cor(nx, ny));
            }
        }
    }
}
