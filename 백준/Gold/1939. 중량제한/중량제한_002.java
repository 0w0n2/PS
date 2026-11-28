import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static final ArrayList<ArrayList<Path>> map = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        for (int i=0; i<N; i++) map.add(new ArrayList<>());

        for (int i=0; i<M; i++) {
            int a = readInt()-1;
            int b = readInt()-1;
            int c = readInt();

            map.get(a).add(new Path(b, c));
            map.get(b).add(new Path(a, c));
        }

        System.out.print(dijkstra(readInt()-1, readInt()-1, N));
    }

    private static int dijkstra(int start, int end, int N) {
        PriorityQueue<Path> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.weight, o1.weight));
        int[] dist = new int[N];
        
        dist[start] = Integer.MAX_VALUE;
        pq.offer(new Path(start, Integer.MAX_VALUE));

        while (!pq.isEmpty()) {
            Path cur = pq.poll();

            if (cur.node == end) { // 목적지 
                return cur.weight;
            }

            if (cur.weight < dist[cur.node]) {
                continue;
            }

            for (Path next : map.get(cur.node)) {
                int minWeight = Math.min(cur.weight, next.weight); // 지나온 다리 중 최솟값

                if (minWeight > dist[next.node]) {
                    dist[next.node] = minWeight;
                    pq.offer(new Path(next.node, minWeight));
                }
            }
        }

        return 0;
    }

    private static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval; // 1<=C<=10^9
    }

    private static class Path {
        int node, weight;

        public Path(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
