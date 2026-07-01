import java.util.*;

public class Solution {
    private static final int INF = 1_000_000_000;

    ArrayList<ArrayList<Node>> map;
    int N, K;

    class Node implements Comparable<Node>{
        int node;
        int dist;

        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    public int solution(int N, int[][] road, int K) {
        this.N = N;
        this.K = K;

        map = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            map.add(new ArrayList<>());
        }

        for (int[] r : road) {
            int u = r[0];
            int v = r[1];
            int w = r[2];

            map.get(u).add(new Node(v, w));
            map.get(v).add(new Node(u, w));
        }

        int[] dist = dijkstra();
        int ct = 0;
        for (int i=1; i<=N; i++) {
            if (dist[i] <= K) ct++;
        }

        return ct;
    }

    private int[] dijkstra() {
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[1] = 0;
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.dist > dist[cur.node]) {
                continue;
            }

            for (Node nextNode : map.get(cur.node)) {
                int nextDist = cur.dist + nextNode.dist;

                if (nextDist < dist[nextNode.node] && nextDist <= K) {
                    dist[nextNode.node] = nextDist;
                    pq.offer(new Node(nextNode.node, nextDist));
                }
            }
        }

        return dist;
    }
}