import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] state = new int[n];
        
        Arrays.fill(state, 1); 
        // 0: 없음, 1: 있음, 2: 여분 있음
        for (int l : lost) state[l-1] = 0;
        for (int r : reserve) state[r-1]++;
        
        for (int i=0;i<n;i++) {
            if (state[i]==2) {
                if (i>0 && state[i-1] == 0) {
                    state[i-1]++;
                    state[i]--;
                } else if (i<n-1 && state[i+1] == 0) {
                    state[i+1]++;
                    state[i]--;
                }
            }
        }
        
        int answer = 0;
        for (int s:state) if (s>=1) answer++;
        
        
        return answer;
    }
}