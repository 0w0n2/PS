import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        List<Integer>[] mapForward = new ArrayList[n+1];
        List<Integer>[] mapReverse = new ArrayList[n+1];
        
        for (int i=1;i<=n;i++) {
            mapForward[i] = new ArrayList<>();
            mapReverse[i] = new ArrayList<>();
        }
        
        for (int[] r : results) {
            mapForward[r[0]].add(r[1]); // 순방향
            mapReverse[r[1]].add(r[0]); // 역방향
        }
        
        int answer = 0;
        for (int i=1;i<=n;i++) {
            answer += (bfs(n, i, mapForward) + bfs(n, i, mapReverse))/(n-1);
        }
        return answer;
    }
    
    private static int bfs(int n, int idx, List<Integer>[] map) {
        int ct = 0;
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] isVisited = new boolean[n+1];
        
        isVisited[idx] = true;
        q.offer(idx);
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            for (int next : map[cur]) {
                if (isVisited[next]) continue;
                isVisited[next] = true;
                q.offer(next);
                ct++;
            }
        }
        
        return ct;
    }
}