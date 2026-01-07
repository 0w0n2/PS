import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int right = citations.length;
        int left = 0;
        int answer = 0;
        while (left<=right) {
            int mid = (right+left)/2;
            // mid번 이상 인용된 논문 수가 mid개 이상이어야 함
            if (mid==0) { // 런타임 에러 방지
                left = mid+1;
                continue;
            }
            if (citations[citations.length-mid]>=mid) {
                left = mid+1;
                answer = mid;
            } else {
                right = mid-1;
            }
        }
        
        return answer;
    }
}