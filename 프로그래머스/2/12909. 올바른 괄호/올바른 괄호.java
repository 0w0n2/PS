import java.util.*;

class Solution {
    boolean solution(String s) {       
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        for (char c:s.toCharArray()) {
            if (c == '(') stack.addLast(c);
            else {
                if (stack.isEmpty()) return false;
                stack.pollLast(); 
            }   
        }

        return stack.size() == 0; 
    }
}