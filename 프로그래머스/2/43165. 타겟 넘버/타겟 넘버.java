import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        return recursive(numbers, target, 0, 0);
    }
    
    private static int recursive(int[] numbers, int target, int idx, int sum) { 
        if (idx==numbers.length) {
            return (sum==target) ? 1:0;
        }
        
        int result = recursive(numbers, target, idx+1, sum-numbers[idx]);
        result += recursive(numbers, target, idx+1, sum+numbers[idx]);
        
        return result;
    }
}