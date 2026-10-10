import java.util.*;

class Solution {
    private boolean[] isVisited;
    private String[] ans;
    
    public String[] solution(String[][] tickets) {
        // 티켓 종류 정렬(1순위: 출발 공항 사전순, 2순위: 도착 공항 사전순)
        Arrays.sort(tickets, (a, b) -> {
           int cmp = a[0].compareTo(b[0]);
            return cmp != 0 ? cmp : a[1].compareTo(b[1]);
        });
        
        isVisited = new boolean[tickets.length];
        ans = new String[tickets.length + 1];
        ans[0] = "ICN";
        
        dfs(tickets, "ICN", 0);
        
        return ans;
    }
    
    private boolean dfs(String[][] tickets, String curCity, int visitedCt) {
        // 모든 티켓 사용 완료
        if (visitedCt == tickets.length) { 
            return true;
        }
    
        for (int i=0; i < tickets.length; i++) {
            if (isVisited[i] || !tickets[i][0].equals(curCity)) {
                continue;
            }
            
            
            isVisited[i] = true;
            ans[visitedCt + 1] = tickets[i][1];
            
            // 정답을 찾은 최초 1회 종료
            if (dfs(tickets, tickets[i][1], visitedCt + 1)) {
                return true;
            }
            
            isVisited[i] = false;
        }
        
        return false;
    }
}