import java.util.*;

class Solution {
    
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i=0; i<phone_book.length-1; i++) {
            if (phone_book[i+1].startsWith(phone_book[i])) {
                return false;
            }
        }
        
        return true;
    }
    
    /** 1. Trie
    private static class Node{
        Node[] childNodes = new Node[10]; // 0~9
        boolean isEndOfWord = false;
    }
    
    // 새로운 전화번호를 삽입하고, 접두어 여부를 반환
    private boolean insert(Node root, String phone) {
        Node cur = root;
        
        for (int i=0; i<phone.length(); i++) {
            int num = phone.charAt(i) - '0';
            
            // 자식 노드가 없을 경우 신규 생성
            if (cur.childNodes[num] == null) {
                cur.childNodes[num] = new Node();
            }
            
            cur = cur.childNodes[num];
            
            if (cur.isEndOfWord) {
                return false;
            }
        }
        
        // 번호 전체 삽입 후
        
        for (Node c : cur.childNodes) {
            if (c != null) {
                return false;
            }
        }
        
        cur.isEndOfWord = true; 
        
        return true;
    }
    
    public boolean solution(String[] phone_book) {
        Node root = new Node();
        
        for (String phone : phone_book) {
            if (!insert(root, phone)) {
                return false;
            }
        }
        
        return true;
    }
    **/
}