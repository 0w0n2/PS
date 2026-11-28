import java.util.*;
import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static final ArrayList<ArrayList<Node>> map = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int m = readInt();

        for (int i=0; i<n; i++) map.add(new ArrayList<>());

        for (int i=0; i<m; i++) {
            int a = readInt()-1;
            int b = readInt()-1;
            int w = readInt();

            map.get(a).add(new Node(b, w));
            map.get(b).add(new Node(a, w));
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            int[] first = dijkstra(n, i);

            for (int j=0; j<n; j++) {
                sb.append(j == i ? "-" : first[j]+1).append(" ");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static int[] dijkstra(int N, int start) {
        int[] first = new int[N];
        int[] dist = new int[N];

        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.weight > dist[cur.v]) continue;

            for (Node next : map.get(cur.v)) {
                int nextWeight = next.weight + cur.weight;

                if (nextWeight < dist[next.v]) {
                    dist[next.v] = nextWeight;
                    first[next.v] = (cur.v == start) ? next.v : first[cur.v];
                    pq.offer(new Node(next.v, nextWeight));
                }
            }
        }

        return first;
    }

    private static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static class Node implements Comparable<Node>{
        int v, weight;
        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

}
