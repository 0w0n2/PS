import java.util.*;
import java.io.*;

public class Main {
	static int[] par;
	static int[] expense;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 학생 수
		int M = Integer.parseInt(st.nextToken()); // 인간관계 수
		int k = Integer.parseInt(st.nextToken()); // 준석이가 갖고 있는 돈 ㅠㅠ
		
		expense = new int[N+1]; // 친구비
		st = new StringTokenizer(br.readLine());
		for (int i=1;i<=N;i++) expense[i] = Integer.parseInt(st.nextToken());
		
		par = new int[N+1];
		Arrays.fill(par, -1);
		
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// a와 b를 친구로 만들자
			merge(a, b);
		}
		
		int totalCost = 0;
		for (int i=1;i<=N;i++) {
			if(par[i]==-1) {
				totalCost += expense[i]; // 모든 친구 그룹 대장(최상위 노드)들이 요구하는 친구비의 합 
			}
		}
		System.out.print(totalCost<=k ? totalCost:"Oh no");
	}
	
	public static int find(int n) {
		if(par[n]==-1) return n;
		return par[n] = find(par[n]);
	}
	
	public static void merge(int a, int b) {
		a = find(a);
		b = find(b);
		
		// 가장 적은 친구비를 요구하는 노드를 최상위 노드로
		if(a!=b) {
			int aCost = expense[a];
			int bCost = expense[b];
			if(aCost < bCost) { // a가 더 싸니까 a쪽에 b를 붙이자
				par[b] = a;
			} else {
				par[a] = b;
			}
		}
	}
}