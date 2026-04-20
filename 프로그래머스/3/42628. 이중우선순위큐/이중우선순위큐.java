import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> num2ct = new TreeMap<>();
        
        for (String op : operations) {
            StringTokenizer st = new StringTokenizer(op);
            String cmd = st.nextToken();
            int val = Integer.parseInt(st.nextToken());
            
            switch (cmd) {
                case "I":
                    num2ct.put(val, num2ct.getOrDefault(val, 0) + 1);
                    break;
                case "D":
                    if (num2ct.isEmpty())  continue;
                    
                    if (val==1) remove(num2ct, num2ct.lastKey());
                    else remove(num2ct, num2ct.firstKey());
                    break;
            }
        }
        
        return (num2ct.isEmpty()) ? new int[]{0, 0} : new int[]{num2ct.lastKey(), num2ct.firstKey()};
    }
    
    private void remove(TreeMap<Integer, Integer> map, int key) {
        int ct = map.get(key);
        if (ct == 1) {
            map.remove(key);
        } else {
            map.put(key, ct - 1);
        }
    }
}