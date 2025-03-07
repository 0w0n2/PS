import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 건물 개수
		int a = Integer.parseInt(st.nextToken()); // 가희가 볼 수 있는
		int b = Integer.parseInt(st.nextToken()); // 단비가 볼 수 있는
		
		if (a+b-1>N) System.out.println(-1);
		else {
			ArrayList<Integer> list = check(N, a, b);
			
			StringBuilder sb = new StringBuilder();
			sb.append(list.get(0)).append(" "); // 맨 앞 건물
			for (int i=0;i<N-list.size();i++) sb.append(1).append(" ");	
			// 맨 앞 건물에 가려져서 안 보이는 건물 -> 1로(사전순으로 앞섬) 설정해서 맨 앞 건물에 모두 가려버림  
			
			for (int i=1;i<list.size();i++) sb.append(list.get(i)).append(" "); // (~끝까지)
			
			System.out.println(sb);
		}
	}
	
	static ArrayList<Integer> check(int N, int a, int b) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i=1;i<a;i++) list.add(i); // 가희 쪽 채우기
		list.add(Math.max(a, b));  // 둘 사이 가장 높은 탑(가희와 단비가 공통으로 제일 마지막에 보는 건물)
		for (int i=b-1;i>0;i--) list.add(i); // 가장 높은 탑 이후로 단비 쪽 채우기
		
		return list;
	}
}