import java.util.*;

class Solution {
    class People {
        int y, x, health;

        People(int y, int x, int health) {
            this.y = y;
            this.x = x;
            this.health = health;
        }
    }

    final int[] dy = {-1, 1, 0, 0};
    final int[] dx = {0, 0, -1, 1};

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int startH = health - grid.get(0).get(0);
        if (startH <= 0) {
            return false;
        }

        int[][] dp = new int[m][n];
        Queue<People> q = new ArrayDeque<>();

        q.offer(new People(0, 0, startH));
        dp[0][0] = startH;

        while(!q.isEmpty()) {
            People cur = q.poll();

            if (cur.y == m-1 && cur.x == n-1) {
                return true;
            }

            for (int dir=0; dir<4; dir++) {
                int ny = cur.y + dy[dir];
                int nx = cur.x + dx[dir];

                if (ny<0 || ny>=m || nx<0 || nx>=n) continue;

                int nextH = cur.health - grid.get(ny).get(nx);
                if (nextH <= 0 || dp[ny][nx] >= nextH) continue;

                dp[ny][nx] = nextH;
                q.offer(new People(ny, nx, nextH));
            }
        }

        return false;
    }
}