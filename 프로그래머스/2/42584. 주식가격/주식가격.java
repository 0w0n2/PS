import java.util.*;

class Solution {
    // 같은 가격으로 내려가는 것도 안 떨어진 것으로 간주함
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        for (int i=0; i<n; i++) {
            while (!stack.isEmpty() && prices[stack.peekFirst()] > prices[i]) {
                int idx = stack.pollFirst();
                ans[idx] = i-idx;
            }
            
            stack.offerFirst(i);
        }
        
        while (!stack.isEmpty()) {
            int idx = stack.pollFirst();
            ans[idx] = n-1-idx;
        }
        
        return ans;
    }
}