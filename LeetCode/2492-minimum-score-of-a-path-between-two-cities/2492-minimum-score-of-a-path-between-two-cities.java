import java.util.*;

class Solution {
    int[] parent;
    public int minScore(int n, int[][] roads) {
        parent = new int[n+1];
        Arrays.fill(parent, -1);

        for (int[] r : roads) {
            merge(r[0], r[1]);
        }

        int root = find(1);
        int ans = Integer.MAX_VALUE;

        for (int[] r : roads) {
            if (find(r[0])==root || find(r[1])==root) {
                ans = Math.min(ans, r[2]);
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