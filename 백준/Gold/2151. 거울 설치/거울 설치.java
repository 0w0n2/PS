import java.util.*;
import java.io.*;

public class Main {
    private static final int DIR = 4;
    private static final char DOOR = '#', MIRROR = '!', WALL = '*';
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int[][][] dist;
    private static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];
        pq = new PriorityQueue<>();
        dist = new int[N][N][DIR];

        int endX = -1, endY = -1;

        for (int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j=0; j<N; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);

                if (map[i][j] == DOOR) {
                    if (pq.isEmpty()) {
                        for (int d=0; d<DIR; d++) {
                            dist[i][j][d] = 0;
                            pq.offer(new Node(i, j, d, 0));
                        }
                    } else {
                        endX = i;
                        endY = j;
                    }
                }
            }
        }

        // dijkstra
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.x==endX && cur.y==endY) {
                System.out.print(cur.mirrorCt);
                return;
            }

            if (cur.mirrorCt > dist[cur.x][cur.y][cur.dir]) {
                continue;
            }

            // 거울 설치 없이 직진
            int nx = cur.x + dx[cur.dir];
            int ny = cur.y + dy[cur.dir];

            if (0<=nx && nx<N && 0<=ny && ny<N && map[nx][ny] != WALL) {
                update(nx, ny, cur.dir, cur.mirrorCt);
            }

            // 거울 설치: 수직 두 방향으로 꺾기
            if (map[cur.x][cur.y] == MIRROR) {
                update(cur.x, cur.y, (cur.dir+1) % DIR, cur.mirrorCt+1);
                update(cur.x, cur.y, (cur.dir+3) % DIR, cur.mirrorCt+1);
            }
        }
    }

    private static void update(int x, int y, int dir, int nextCt) {
        if (nextCt < dist[x][y][dir]) {
            dist[x][y][dir] = nextCt;
            pq.offer(new Node(x, y, dir, nextCt));
        }
    }

    private static class Node implements Comparable<Node> {
        int x, y, dir, mirrorCt;
        public Node(int x, int y, int dir, int mirrorCt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirrorCt = mirrorCt;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.mirrorCt, o.mirrorCt);
        }
    }
}
