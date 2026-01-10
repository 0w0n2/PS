import java.util.*;

public class Solution {
    
    public int[] solution(int []arr) {
        ArrayList<Integer> temp = new ArrayList<>();
        int prev = -1;
        
        for (int a:arr) {
            if (a!=prev) temp.add(a);
            prev = a;
        }
        
        int[] answer = new int[temp.size()];
        for (int i=0;i<answer.length;i++) {
            answer[i] = temp.get(i);
        }

        return answer;
    }
}