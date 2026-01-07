import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        int ct = 0;
        for (int[] cmd:commands) {
            int i = cmd[0];
            int j = cmd[1];
            int k = cmd[2];
            int[] temp = new int[j-i+1];
            
            for (int idx=i-1;idx<j;idx++) {
                temp[idx-(i-1)] = array[idx];
            }
            Arrays.sort(temp);
            answer[ct++] = temp[k-1];
        }
        
        return answer;
    }
}