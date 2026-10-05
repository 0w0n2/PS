import java.util.*;

class Solution {
    // 같은 우선순위를 가진 프로세스가 여러 개 있을 수 있음
    public int solution(int[] priorities, int location) {
        Queue<int[]> q = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1); // 내림차순 정렬
        
        for (int i=0; i<priorities.length; i++) {
            q.offer(new int[]{i, priorities[i]}); // [idx, 우선순위]
            pq.offer(priorities[i]);
        }
        
        int res = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            if (cur[1] == pq.peek()) {
                pq.poll();
                res++;
                
                if (cur[0] == location) {
                    return res;
                }
            } else {
                q.offer(cur);
            }
        }
        
        return res;
    }
}