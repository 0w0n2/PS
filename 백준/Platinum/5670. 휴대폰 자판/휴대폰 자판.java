import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Main {
	private static class Trie {
		private static class Node {
			private final Map<Character, Node> children = new HashMap<>();
			
			private boolean endOfWord; 	// 현재 노드가 단어 끝인지 여부 
			
			private int branch;	// 가지 수 (자식 개수 + 단어 종료를 1개 가지로 간주)
			
			private int words;	// 현재 노드 서브트리의 단어 수 
		}
		
		private final Node root = new Node();
		
		/**
		 * 삽입 재귀 함수 
		 * @param node	현재 노드 
		 * @param word	삽입할 단
		 * @param idx	현재 인덱스 
		 */
		private void insert(Node node, String word, int idx) {
			// 기저 조건
			if (idx == word.length()) {
				node.branch++;
				node.endOfWord = true;
				return; 
			}
			
			char ch = word.charAt(idx);
			Node next = node.children.get(ch);
			
			if (next == null) {
				next = new Node();
				node.children.put(ch, next);
				node.branch++;
			}
			
			node.words++;
			
			insert(next, word, idx+1);
		}
		
		public void insert(String word) {
			insert(root, word, 0);
		}
		
		public long cntKeyStrokes() {
			return cntKeyStrokes(root, true);
		}
		
		/**
		 * 타이핑 횟수 계산 재귀 
		 * @param node	현재 노드 
		 * @param isRoot 루트 여부 
		 * @return 현재 노드 기준 총 타이핑 횟수 
		 */
		private long cntKeyStrokes(Node node, boolean isRoot) {
			long result = 0L;
			
			if (isRoot || node.branch > 1) {
				result = node.words;
			}
			
			for (Node child : node.children.values()) {
				result += cntKeyStrokes(child, false);
			}
			
			return result; 
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		
		while ((line = br.readLine()) != null) {
			int n = Integer.parseInt(line);
			Trie trie = new Trie();
			
			for (int i=0;i<n;i++) {
				trie.insert(br.readLine().trim());
			}
			
			double avg = (double) trie.cntKeyStrokes() / n;
			sb.append(String.format("%.2f%n", avg));
		}
		
		System.out.print(sb);
	}
}
