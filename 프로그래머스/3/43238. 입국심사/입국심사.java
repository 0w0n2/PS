import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        long left = 1;
        long right = (long) times[times.length-1]*n;
        // 범위: 1~MAX(젤 오래 걸리는 심사관*검사인원)
        
        while (left<=right) {
            long mid = (left + right)/2;
            long temp = n;
            int idx = 0;
            while (temp>=0 && idx<times.length) {
                temp -= (mid/(long)times[idx++]);
            }
            if (temp<=0) {
                answer = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        
        return answer;
    }
}