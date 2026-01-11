import java.util.*;

class Solution {
    
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int p:priorities) pq.offer(p);
        
        int i, ct;
        i = ct = 0;
        while (!pq.isEmpty()) {
            if ((priorities[i]!=-1) && (pq.peek()==priorities[i])) {
                pq.poll();
                priorities[i] = -1;
                ct++;
                if (i==location) return ct;
            }
            i = (i+1)%priorities.length;
        }
        return 0;
    }
}