import java.util.*;
import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static final ArrayList<ArrayList<Node>> map = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int n = readInt(); // 노드 수
        int m = readInt(); // 간선 수

        for (int i=0; i<n; i++) map.add(new ArrayList<>());

        for (int i=0; i<m; i++) {
            int a = readInt()-1;
            int b = readInt()-1;
            int c = readInt();

            map.get(a).add(new Node(b, c));
            map.get(b).add(new Node(a, c));
        }

        int s = readInt()-1;
        int t = readInt()-1;

        System.out.print(dijkstra(n, s, t));
    }

    private static int dijkstra(int n, int start, int end) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.node] < cur.weight) continue;

            for (Node nextNode : map.get(cur.node)) {
                int nextWeight = nextNode.weight + cur.weight;

                if (dist[nextNode.node] > nextWeight) {
                    dist[nextNode.node] = nextWeight;
                    pq.offer(new Node(nextNode.node, nextWeight));
                }
            }
        }

        return dist[end];
    }

    private static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static class Node implements Comparable<Node> {
        int node, weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
