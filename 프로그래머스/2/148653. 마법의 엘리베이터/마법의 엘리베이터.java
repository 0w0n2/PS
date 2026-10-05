import java.util.*;

class Solution {
    public int solution(int storey) {
        int ans = 0;
        
        while (storey > 0) {
            int d = storey % 10;
            int next = storey / 10;
            
            boolean isUp = d>5 || (d==5 && next%10 >= 5);
            
            ans += isUp ? 10-d : d;
            storey = next + (isUp ? 1 : 0);
        }
         
        return ans;
    }
}