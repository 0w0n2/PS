import java.util.*;

// 유저: id (unique), nickname (중복 가능)
class Solution {
    private static final String LEAVE = "Leave";
    private static final String ENTER = "Enter";
    private static final String CHANGE = "Change";
    
    public String[] solution(String[] record) {
        HashMap<String, String> idToNickname = new HashMap<>();

        // 최종 닉네임 상태 갱신
        StringTokenizer st; 
        for (String r : record) {
            st = new StringTokenizer(r);
            String command = st.nextToken();
            
            if (!command.equals(LEAVE)) {
                String id = st.nextToken();
                String nickname = st.nextToken();
                idToNickname.put(id, nickname);
            } 
        }
        
        ArrayList<String> answer = new ArrayList<>();
        
        StringBuilder sb;
        for (int i=0; i<record.length; i++) {
            st = new StringTokenizer(record[i]);
            
            String command = st.nextToken();
          
            if (command.equals(CHANGE)) {
                continue;
            }
            
            String id = st.nextToken();

            sb = new StringBuilder();
            
            sb.append(idToNickname.get(id))
                .append(command.equals(ENTER) ? "님이 들어왔습니다." : "님이 나갔습니다.");
            
            answer.add(sb.toString());
        }
        
        return answer.toArray(new String[0]);
    }
}