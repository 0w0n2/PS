package d250327;
import java.io.*;

/**
 * 기존 코드에서 S.contains(str) 이 결국 indexOf 로 S 문자열을 선형 탐색하는 메소드라 해서
 * ㄴ 최악의 경우 S.contains() 조회 할 때마다 O(length(S)) 만큼의 시간복잡도가 발생할 수 있음...
 * 
 * 따라서 S 문자열의 모든 부분 문자열을 초기에 Trie 에 저장해두고,
 * P의 어떤 위치에서 최대 몇 글자까지 일치하는지 Trie 에서 검색함
 * 
 * Trie 구조 만드는 건 챗지피티 도움 받았음 -> 나중에 Trie 공부하고 수정
 * @author 0w0n
 * @since 25-03-28 (목)
 * 
 * 근데 !!!!!! 가장 큰 문제점 -> 문자열의 길이가 너무 커서 Trie 객체의 메모리 초과가 발생함 ...
 * 때문에 Trie 사용 불가능 (Trie 는 보통 Trie 로 풀 수 있는 문제가 따로 있음(들어오는 문자열의 최대 길이를 확인하고, 메모리적으로 쓸 수 있겠다! 싶으면 사용하는 게 좋음) 
 * S.length() 가 1000 미만인 경우에...
 */

public class Main_2195_문자열복사_트라이인데사용불가능 {
	static class TrieNode{
		TrieNode[] next = new TrieNode[128];
		boolean isEnd;
	}
	
	static class Trie{
		TrieNode root = new TrieNode();
		void insert (String word) {
			TrieNode node = root;
			for (char ch : word.toCharArray()) {
				int idx = ch;
				if (node.next[idx]==null) node.next[idx] = new TrieNode();
				node = node.next[idx];
			}
			node.isEnd = true;
		}
		
		int match(String P, int start) { // P의 현재 위치에서 최대 몇 글자까지 일치하는가
			TrieNode node = root;
			int i = start;
			
			while (i<P.length()) {
				int idx = P.charAt(i);
				if (node.next[idx] == null) break;
				node = node.next[idx];
				i++;
			}
			return i-start; // 접두사 포함되면 존재
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine(); // 문자열 S의 모든 접두사부터 시작하는 연속 부분 문자열들을 전부 트라이에 저장
		String P = br.readLine();
		
		Trie trie = new Trie();
		// S의 모든 연속 부분 문자열들을 트라이에 저장
		for (int i=0;i<S.length();i++) {
			trie.insert(S.substring(i));
		}
		
		int idx = 0;
		int ct = 0;
		
		while(idx<P.length()) {
			int len = trie.match(P, idx); // 현재 위치에서 최대 몇 글자까지 일치하는가
			// if (len==0) 은 고려 안 해줘도 됨(문제 조건상 나올 수 없음)
			idx += len;
			ct++;
		}
		System.out.print(ct);
	}
}
