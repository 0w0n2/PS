import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        for (int i=0; i<=n; i++) map.add(new ArrayList<>());
        
        for (int[] e : edge) {
            map.get(e[0]).add(e[1]);
            map.get(e[1]).add(e[0]);
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        
        boolean[] isVisited = new boolean[n+1];
        isVisited[1] = true;
        
        int size = 0;
        while (!q.isEmpty()) {
            size = q.size();
            
            for (int i=0; i<size; i++) {
                int curNode = q.poll();
                
                for (int nextNode : map.get(curNode)) {
                    if (!isVisited[nextNode]) {
                        q.offer(nextNode);
                        isVisited[nextNode] = true;
                    }
                }
            }
        }
        
        return size;
    }
}