import java.util.*;


class Solution {
    /**
        주어진 팰린드롬 문자열의 문자들을 재배열해 사전순으로 가장 작은 팰린드롬을 반환한다
     */
    public String smallestPalindrome(String s) {
        int[] ct = new int[26];
        
        for (int i=0; i<s.length(); i++) ct[s.charAt(i)-'a']++;

        StringBuilder left = new StringBuilder(s.length() / 2);
        char middle = 0;

        for (int i=0; i<26; i++) {
            if ((ct[i] & 1) == 1) middle = (char) ('a' + i);

            for (int j=0; j<ct[i]/2; j++) {
                left.append((char) ('a' + i));
            }
        }

        StringBuilder ans = new StringBuilder(s.length());
        ans.append(left);

        if (middle != 0) {
            ans.append(middle);
        }

        ans.append(left.reverse());

        return ans.toString();
    }
}