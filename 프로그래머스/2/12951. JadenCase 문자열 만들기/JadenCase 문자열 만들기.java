import java.util.*;
class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append(c);
                flag = true;
            } else {
                if (flag) {
                    sb.append(Character.toUpperCase(c));
                    flag = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }
        return String.valueOf(sb);
    }
}