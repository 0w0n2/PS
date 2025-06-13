import java.util.*;
import java.io.*;

public class Main {

    private static final int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
    private static final int MAX = 125;
    private static int[][] map;

    private static class Node implements Comparable<Node>{
        int x, y, cost;
        Node(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {

        map = new int[MAX][MAX];
        int N, t = 1;
        StringBuilder sb = new StringBuilder();
        while ((N=readInt())!=0){
            for (int i=0;i<N;i++) for (int j=0;j<N;j++) map[i][j] = readInt();
            int result = dijkstra(N);
            sb.append("Problem ").append(t++).append(": ").append(result).append("\n");
        }
        System.out.print(sb);
    }

    private static int dijkstra(int N){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[][] dist = new int[N][N];
        for (int[] d : dist) Arrays.fill(d, Integer.MAX_VALUE);
        dist[0][0] = map[0][0];
        pq.offer(new Node(0, 0, dist[0][0]));

        while(!pq.isEmpty()){
            Node c = pq.poll();
            if (dist[c.x][c.y] < c.cost) continue;
            if (c.x==N-1&&c.y==N-1) break;

            for (int i=0;i<4;i++){
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];
                if (nx<0||nx>=N||ny<0||ny>=N) continue;

                int nextCost = c.cost + map[nx][ny];
                if (dist[nx][ny] > nextCost){
                    dist[nx][ny] = nextCost;
                    pq.offer(new Node(nx, ny, nextCost));
                }
            }
        }

        return dist[N-1][N-1];
    }

    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
