import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static class Trie {
		private static class Node {
			HashMap<Character, Node> children = new HashMap<>();
			boolean endOfWord;
		}
		
		// 루트 노드 
		private final Node root = new Node();
		
		/**
		 * 전화번호를 삽입하면서 즉시 접두사 충돌 여부를 판정한다. 
		 * 
		 * - 삽입 과정에서 이미 끝난 번호를 만나면 기존 번호가 접두사이고,
		 * - 삽입 완료 후 자식이 남아 있으면 현재 번호가 기존 번호의 접두사이다. 
		 */
		public boolean insert(String word) {
			Node cur = root;
			
			for (char ch : word.toCharArray()) {
				cur = cur.children.computeIfAbsent(ch, c -> new Node());
				
				// 기존 번호가 현재 번호의 접두사인 경우 
				if (cur.endOfWord) {
					return false;
				}
			}
			
			// 중복 번호 또는 현재 번호가 기존 번호의 접두사인 경우 
			if (cur.endOfWord || !cur.children.isEmpty()) {
				return false;
			}
			
			cur.endOfWord = true;
			return true;
		}
	}
	
	public static void main(String[] args) throws IOException {
		int t = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		while (t-->0) {
			int n = Integer.parseInt(br.readLine().trim());
			Trie trie = new Trie();
			boolean consistent = true;
			
			for (int i=0;i<n;i++) {
				String phone = br.readLine().trim();
				
				if (consistent && !trie.insert(phone)) {
					consistent = false;
				}
			}
			
			sb.append(consistent ? "YES" : "NO").append("\n");
		}
		
		System.out.print(sb.toString().trim());
	}
}