import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String ,Integer> kind2Ct = new HashMap<>();

        // 의상 목록 정리(같은 이름을 가진 의상은 없음. unique)
        for (String[] c : clothes) {
            kind2Ct.put(c[1], kind2Ct.getOrDefault(c[1], 0) + 1);
        }

        // 조합 산출
        int ans = 1;
        for (Integer ct : kind2Ct.values()) {
            ans *= ct + 1;
        }

        return ans - 1;
    }
}