import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        int goalDate = getDate(progresses[0], speeds[0]);
        int ct = 1;
        
        for (int i=1; i<progresses.length; i++) {
            int curDate = getDate(progresses[i], speeds[i]);
            
            if (curDate <= goalDate) { // 같이 배포
                ct++;
            } else { // 다음 배포일로 넘김
                ans.add(ct);
                ct = 1;
                goalDate = curDate;
            }
        }
        
        ans.add(ct);
        
        int[] res = new int[ans.size()];
        for (int i=0; i<ans.size(); i++) res[i] = ans.get(i);
        
        return res;
    }
    
    private int getDate(int p, int s) {
        int diff = 100 - p;
        return (diff + s - 1) / s; 
    }
}