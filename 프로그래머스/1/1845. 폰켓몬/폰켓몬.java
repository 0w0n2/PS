class Solution {
    public int solution(int[] nums) {
        boolean[] isSelected = new boolean[200_000+1];
        int ct = 0;
        for (int n : nums) {
            if (!isSelected[n]) {
                isSelected[n] = true;
                ct++;
                if (ct == nums.length/2) return ct; // 조기 종료
            }
        }
        return ct;
    }
}