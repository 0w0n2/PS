class Solution {
    private static class Node{
        Node[] childNodes = new Node[10]; // 0~9
        boolean isEndOfWord = false;
    }
    
    private boolean insert(Node root, String phone) {
        Node cur = root;
        for (int i=0;i<phone.length();i++) {
            int num = phone.charAt(i) - '0';
            if (cur.childNodes[num]==null) {
                cur.childNodes[num] = new Node();   // 자식 노드가 없다면 새로 생성
            }
            
            cur = cur.childNodes[num];  // 포인터 노드 갱신
            if (cur.isEndOfWord) return false; // 다른 phone의 끝 -> 현재 번호가 더 긴데 중간에 짧은 번호가 이미 있씀
        }
        
        // 번호 삽입을 마쳤는데 이 노드에 이미 자식드리 있음(누군가의 접두사)
        for (int i=0;i<10;i++) if (cur.childNodes[i] != null) return false; 
        
        cur.isEndOfWord = true; // 여기가 이 번호의 끝
        return true;
    }
    
    
    public boolean solution(String[] phone_book) {
        Node root = new Node();
        for (String number:phone_book) {
            if (!insert(root, number)) {
                return false;
            }
        }
        return true;
    }
}