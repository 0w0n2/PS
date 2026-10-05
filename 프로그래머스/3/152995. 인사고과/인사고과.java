import java.util.Arrays;

class Solution {
    public int solution(int[][] scores) {
        int[] w = scores[0]; // 완호 점수 정보

        Arrays.sort(scores, (o1, o2) -> {
            return (o1[0] != o2[0]) ? Integer.compare(o2[0], o1[0]) : Integer.compare(o1[1], o2[1]);
        }); // 1순위: [0] 내림차순, 2순위: [1] 오름차순

        int rank = 1;
        int maxB = 0;
        
        for (int[] s : scores) {
            if (s[1] < maxB) {
                if (s[0] == w[0] && s[1] == w[1]) return -1;
                continue;
            }

            maxB = Math.max(maxB, s[1]);
            
            if (s[0] + s[1] > w[0] + w[1]) rank++;
        }
        
        return rank;
    }
}