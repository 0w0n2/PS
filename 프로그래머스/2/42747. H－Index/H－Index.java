import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations); // 오름차순 정렬
        
        int n = citations.length;
        int left = 0;
        int right = n-1;
        int ans = 0;
        
        while (left <= right) {
            int mid = (left+right)/2;
            int h = n - mid; // mid부터 끝까지 논문 개수
            
            if (citations[mid] >= h) {
                ans = h;
                right = mid-1; // 더 큰 h를 찾으러 왼쪽으로 좁힘
            } else {
                left = mid+1;
            }
        }
        return ans;
    }
}