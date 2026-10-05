class Solution {
    public int solution(int n) {

        int ct = bitCount(n);
        while (bitCount(++n) != ct);

        return n;
    }

    private int bitCount(int n) {
        int ct = 0;

        while (n > 0) {
            n &= n-1;
            ct++;
        }

        return ct;
    }
}