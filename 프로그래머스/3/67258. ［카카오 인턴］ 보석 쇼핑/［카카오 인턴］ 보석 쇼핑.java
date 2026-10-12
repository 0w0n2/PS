import java.util.*;

class Solution {
    
    public int[] solution(String[] gems) {
        HashMap<String, Integer> gem2nums = new HashMap<>();
        int totalKinds = new HashSet<>(Arrays.asList(gems)).size();
        
        int left = 0;
        int bestLeft = 0;
        int bestRight = gems.length - 1;
        
        for (int right=0; right<gems.length; right++) {
            // 오른쪽 확장
            gem2nums.put(gems[right], gem2nums.getOrDefault(gems[right], 0) + 1);
            
            // 모든 종류의 보석이 하나 이상 포함되는 선에서 왼쪽 범위 축소
            while (gem2nums.size() == totalKinds) {
                if (right-left < bestRight-bestLeft) {
                    bestRight = right;
                    bestLeft = left;
                }
                
                if (gem2nums.get(gems[left]) == 1) {
                    gem2nums.remove(gems[left]);
                } else {
                    gem2nums.put(gems[left], gem2nums.get(gems[left]) - 1);
                }
                
                left++;
            }
        }
        
        return new int[]{bestLeft+1, bestRight+1};
    }
}