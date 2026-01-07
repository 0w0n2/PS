import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> arr = new ArrayList<>();
        
        Queue<Integer> q = new ArrayDeque<>();
        for (int i=0;i<progresses.length;i++) {
            q.add((100-progresses[i]+speeds[i]-1)/speeds[i]);
        }
        
        while(!q.isEmpty()) {
            int ct = 1;
            int cur = q.poll(); // 이것보다 작은 것들 모두 꺼낼 수 있음
            while (!q.isEmpty() && (cur >= q.peek())) {
                q.poll();
                ct++;
            }
            arr.add(ct);
        }
        
        int[] result = new int[arr.size()];
        for (int i=0;i<arr.size();i++) result[i] = arr.get(i);
        return result;
    }
}