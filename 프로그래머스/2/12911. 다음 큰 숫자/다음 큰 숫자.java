class Solution {
    public int solution(int n) {

        int ct = Integer.bitCount(n);
        while (Integer.bitCount(++n) != ct);
        
        return n;
    }
}