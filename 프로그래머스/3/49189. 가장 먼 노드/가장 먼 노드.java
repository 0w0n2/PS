import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        List<Integer>[] map = new ArrayList[n+1];
        for (int i=1;i<=n;i++) map[i] = new ArrayList<>();
        
        for (int[] e : edge) {
            map[e[0]].add(e[1]);
            map[e[1]].add(e[0]);
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] isVisited = new boolean[n+1];
        
        q.offer(1);
        isVisited[1] = true;
        
        int size = 0;
        
        while (!q.isEmpty()) {
            size = q.size();
            
            for (int i=0;i<size;i++) {
                int cur = q.poll();
                
                for (int next : map[cur]) {
                    if (!isVisited[next]) {
                        isVisited[next] = true;
                        q.offer(next);
                    }
                }
            }
        }
        
        return size;
    }
}