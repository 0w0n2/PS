import java.util.*;

class Solution {
    private class Task implements Comparable<Task>{
        int id, reqTime, durTime;
        Task(int id, int reqTime, int durTime) {
            this.id = id;
            this.reqTime = reqTime;
            this.durTime = durTime;
        }
        
        @Override
        public int compareTo(Task o) {
            if (this.durTime != o.durTime) {
                return Integer.compare(this.durTime, o.durTime);
            } else if (this.reqTime != o.reqTime) {
                return Integer.compare(this.reqTime, o.reqTime);
            }
            return Integer.compare(this.id, o.id);
        }
    }
    
    public int solution(int[][] jobs) {
        int n = jobs.length;
        Task[] tasks = new Task[n];
        for (int i=0; i<n; i++) {
            tasks[i] = new Task(i, jobs[i][0], jobs[i][1]);
        }
        
        // 요청 시간 순 정렬
        Arrays.sort(tasks, (o1, o2) -> {
            return Integer.compare(o1.reqTime, o2.reqTime);
        });
        
        // 현재 시간에 실행 가능한 Task 목록
        PriorityQueue<Task> pq = new PriorityQueue<>();
        
        int curTime = 0;
        int totalDiff = 0;
        int idx = 0;
        int completed = 0;
        
        while (completed < n) {
            while (idx < n && tasks[idx].reqTime <= curTime) {
                pq.offer(tasks[idx++]);
            }
            
            if (pq.isEmpty()) {
                curTime = tasks[idx].reqTime;
                continue;
            }
            
            Task curTask = pq.poll();
            curTime += curTask.durTime;
            totalDiff += curTime - curTask.reqTime;
            completed++;
        }
        
        return totalDiff / n;
    }
} 