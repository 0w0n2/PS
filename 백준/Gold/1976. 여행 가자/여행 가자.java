import java.util.*;
import java.io.*;

public class Main {
	
	static int[] par; // 최상위 부모 노드 저장
	//static int[] height; // 각 트리의 높이 저장
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 도시의 수
		int M = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시의 수
		
		par = new int[N+1]; // 노드를 0이 아닌 1부터 시작해서, dat 배열로 쓰려고 N+1
		Arrays.fill(par, -1);
		
//		height = new int[N+1];
//		Arrays.fill(height, 1);
		
		StringTokenizer st;
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1;j<=N;j++) {				
				if(Integer.parseInt(st.nextToken())==1) merge(i, j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int root = find(Integer.parseInt(st.nextToken()));
		
		for(int i=1; i<M;i++) {
			if(root!=find(Integer.parseInt(st.nextToken()))) {
				System.out.print("NO");
				return;
			}
		}

		System.out.print("YES");

	}
	
	public static int find(int k) {
		if (par[k]==-1) return k; // 최상위 노드 리턴
		return par[k] = find(par[k]); // 부모 노드를 최상위 노드로 갱신
	}
	
	public static void merge(int i, int j) { // i번 노드와 j번 노드를 연결하자
		i = find(i);
		j = find(j);
		
		if(i==j) return; // 이미 연결댐
		
		par[i] = j;
		
//		if(height[i]>height[j]) par[j] = i; // i의 트리가 더 높으므로 i에 j를 붙이기
//		else {
//			par[i] = j;
//			if(height[i]==height[j]) height[j]++;
//		}
	}
}