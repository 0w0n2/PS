import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[] par;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // n+1 개의 집합 ({0}, {1}, {2}, ... {n})
		int m = Integer.parseInt(st.nextToken()); // 연산 개수 m
		
		par = new int[n+1]; // 0부터 n까지 각각 원소 1개씩 갖는 n+1개의 트리를 dat 배열로 구현 
		Arrays.fill(par, -1); // 현재 각 트리의 최상위 부모는 자기 자신이다(-1)
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int sign = Integer.parseInt(st.nextToken()); // 0: merge, 1: 동일 트리인지 확인
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(sign==1) { // 동일 트리에 포함되는지 확인
				a = find(a);
				b = find(b);
				if(a==b) sb.append("YES").append("\n");
				else sb.append("NO").append("\n");
			} else { // 합치기
				merge(a, b);
			}
		}
		System.out.print(sb);
		
	}
	
	public static int find(int k) {
		
		if(par[k]==-1) return k; // 최상위 노드 리턴
		
		par[k] = find(par[k]); // 경로 압축하며 최상위 노드를 찾아감
		return par[k];
	}
	
	public static void merge(int a, int b) {
		a = find(a); // a의 최상위 노드?
		b = find(b); // b의 최상위 노드?
		
		if(a==b) return; // 이미 동일 트리임, 합치기 필요 X
		
		par[a] = b; // 합치기
	}
}