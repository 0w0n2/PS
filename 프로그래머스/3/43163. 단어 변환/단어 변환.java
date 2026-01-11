import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        Queue<String> q = new ArrayDeque<>();
        q.offer(begin);
        
        boolean[] isVisited = new boolean[words.length];
        
        int ct = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            
            while(size-->0) {
                String cur = q.poll();
                if (cur.equals(target)) {
                    return ct;
                }
                for (int i=0;i<words.length;i++) {
                    if (!isVisited[i] && isAvaliable(cur, words[i])) {
                        isVisited[i] = true;
                        q.offer(words[i]);
                    }
                }
            }
            ct++;
        }
        
        return 0;
    }
    
    private static boolean isAvaliable(String cur, String word) {
        int diff = 0;
        
        for (int j=0;j<cur.length();j++) {
            if (cur.charAt(j)!=word.charAt(j)) diff++;
            if (diff>1) return false;
        }
        return diff==1;
    }
}