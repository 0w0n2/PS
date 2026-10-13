import java.util.*;

class Solution {
    public int solution(int[] topping) {
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();
        
        left.put(topping[0], 0);
        
        for (int i=1; i<topping.length; i++) {
            right.put(topping[i], right.getOrDefault(topping[i], 0) + 1);
        }
        
        int ans = 0;
        for (int i=1; i<topping.length-1; i++) {
            // 양 조각의 토핑 가짓수가 같은 경우
            if (left.size() == right.size()) {
                ans++;
            }
            
            // 오른쪽 조각의 앞 토핑을 왼쪽 조각으로 이동
            int t = topping[i];
            left.put(t, left.getOrDefault(t, 0) + 1);
            
            if (right.get(t)==1) {
                right.remove(t);
            } else {
                right.put(t, right.getOrDefault(t, 0) - 1);
            }
        }
        
        return ans;
    }
}