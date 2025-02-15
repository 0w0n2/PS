import java.util.*;
import java.io.*;

public class Main {
	
	static class Node{
		int v, people;
		Node(int v, int people){
			this.v = v;
			this.people = people;
		}
	}
	
	static ArrayList<ArrayList<Node>> map = new ArrayList<>();
	static int N;
	static int[] people;
	static int minPeople = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		people = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1;i<=N;i++) people[i] = Integer.parseInt(st.nextToken());
		
		for (int i=0;i<=N;i++) map.add(new ArrayList<Node>());
		
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			for (int j=0;j<x;j++) {
				map.get(i).add(new Node(Integer.parseInt(st.nextToken()), people[i])); // 이어진 간선, 인구수
			}
		}
		
		selectTeam(1);
		
		if(minPeople!=Integer.MAX_VALUE) System.out.print(minPeople);
		else System.out.print(-1);
	}
	
	static ArrayList<Integer> a = new ArrayList<>();
	static ArrayList<Integer> b = new ArrayList<>();
	
	public static void selectTeam(int i) {
		if (i>N) { // 모든 팀을 골랐으면(N번까지 선택)
			
			if (!a.isEmpty() && !b.isEmpty()) {
				// bfs로는 이어져있는지만 확인하고, 모든 인구수는 for문으로 직접 계산
				if (bfs(a.get(0), a) && bfs(b.get(0), b)) {
					int aPeople = 0;
					int bPeople = 0;
					for (int k=0;k<a.size();k++) aPeople += people[a.get(k)];
					for (int k=0;k<b.size();k++) bPeople += people[b.get(k)];
					
					minPeople = Math.min(minPeople, Math.abs(aPeople-bPeople));
				}
			}
			return;
		}
		
		// 이 노드 번호를 a 팀에 넣자
		a.add(i);
		selectTeam(i+1);
		a.remove(a.size()-1); // 백트래킹
		
		// 이 노드 번호를 b 팀에 넣자
		b.add(i);
		selectTeam(i+1);
		b.remove(b.size()-1); // 백트래킹
	}
	
	public static boolean bfs(int start, ArrayList<Integer> team) {
		
		boolean [] isVisited = new boolean[N+1]; 
		isVisited[start] = true; // 방문 처리
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			int cv = q.poll();
			
			for (Node nextPoint : map.get(cv)) {
				int nv = nextPoint.v;
				if(!team.contains(nv) || isVisited[nv]) continue;
				isVisited[nv] = true;
				q.offer(nv);
			}
		}
		
		for (int v : team) if(!isVisited[v]) return false; // 방문 확인
		
		return true;
	}
	
}