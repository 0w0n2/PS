import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int sum = 0;
        for (int s:scoville) {
            pq.offer(s);
        }
        
        // 시뮬레이션 시작
        int ct = 0;
        while (pq.size()>=2 && pq.peek()<K) {
            int temp = pq.poll() + pq.poll()*2;
            pq.offer(temp);
            ct++;
        }
        
        return (pq.peek()<K) ? -1:ct;
    }
}