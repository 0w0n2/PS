import java.util.*;

class Solution {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        while (st.hasMoreTokens()) {
            int k = Integer.parseInt(st.nextToken());
            max = Math.max(max, k);
            min = Math.min(min, k);
        }
        return min + " " + max;
    }
}