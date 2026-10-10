import java.util.*;

class Solution {
    private int totalCt; // 총 티켓 개수
    private boolean[] isUsed;
    private String[] ans;
    
    public String[] solution(String[][] tickets) {
        // 티켓 종류 정렬(1순위: 출발 공항 사전순, 2순위: 도착 공항 사전순)
        Arrays.sort(tickets, (a, b) -> {
           int cmp = a[0].compareTo(b[0]);
            return cmp != 0 ? cmp : a[1].compareTo(b[1]);
        });
        
        totalCt = tickets.length;
        isUsed = new boolean[totalCt];
        ans = new String[totalCt+1];
        
        ans[0] = "ICN";
        dfs(tickets, 1);
        
        return ans;
    }
    
    private boolean dfs(String[][] tickets, int curCt) {
        // 모든 티켓 사용 완료
        if (curCt == totalCt + 1) { 
            return true;
        }
    
        for (int i=0; i<totalCt; i++) {
            if (isUsed[i]) continue;
            if (!tickets[i][0].equals(ans[curCt-1])) continue;
            
            // 티켓 사용
            ans[curCt] = tickets[i][1];
            isUsed[i] = true;
            
            // 정답을 찾은 최초 1회 종료
            if (dfs(tickets, curCt + 1)) {
                return true;
            }
            
            isUsed[i] = false;
        }
        
        return false;
    }
}