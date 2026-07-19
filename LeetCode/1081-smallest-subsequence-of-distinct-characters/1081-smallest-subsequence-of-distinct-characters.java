import java.util.*;

class Solution {
    
    public String smallestSubsequence(String s) {
        int[] lastIdx = new int[26];
        for (int i=0; i<s.length(); i++) lastIdx[s.charAt(i)-'a'] = i; // 각 문자의 최대 idx 기록 

        boolean[] isVisited = new boolean[26];
        Deque<Character> stack = new ArrayDeque<>();

        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (isVisited[c - 'a']) continue; // 제일 작은 idx만 기록

            while (!stack.isEmpty() && (stack.peek() > c) && (lastIdx[stack.peek()-'a'] > i)) {
                isVisited[stack.pop() - 'a'] = false;
            }

            stack.push(c);
            isVisited[c-'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}