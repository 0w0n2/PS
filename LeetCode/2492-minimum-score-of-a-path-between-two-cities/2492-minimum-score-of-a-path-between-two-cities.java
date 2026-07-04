import java.util.*;

class Solution {
    class Node implements Comparable<Node>{
        int u, v, dist;

        Node(int[] r) {
            this.u = r[0];
            this.v = r[1];
            this.dist = r[2];
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    int[] parent;
    public int minScore(int n, int[][] roads) {
        parent = new int[n+1];
        Arrays.fill(parent, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int[] r : roads) {
            merge(r[0], r[1]);
            pq.offer(new Node(r));
        }

        int root = find(1);
        int ans = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (find(cur.u) == root || find(cur.v) == root) {
                ans = cur.dist;
                break;
            }
        }

        return ans;
    }

    private int find(int x) {
        if (parent[x] == -1) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private void merge(int a, int b) {
        a = find(a);
        b = find(b);

        if (a==b) {
            return;
        }

        parent[b] = a;
    }
}