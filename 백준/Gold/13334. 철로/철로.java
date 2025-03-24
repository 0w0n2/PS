import java.util.*;
import java.io.*;

public class Main {
	
	static class Node implements Comparable<Node>{
		int start, end, len;
		Node(int start, int end){
			this.start = start;
			this.end = end;
			this.len = Math.abs(this.start-this.end);
		}
		
		@Override
		public int compareTo(Node o) {
			return this.end!=o.end ? this.end-o.end : this.start-o.start;
		} 
		// 1순위 : o_i 오름차순(끝 점이 더 왼쪽에 있는 순), 2순위 : h_i 오름차순(시작 점이 더 왼족에 있는 순) 
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Node[] H = new Node[n]; // 집 위치 저장
		
		int minLen = Integer.MAX_VALUE; // 
		for (int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			H[i] = new Node(Math.min(a, b), Math.max(a, b)); // 집과 사무실 위치 입력 받음
			minLen = Math.min(minLen, H[i].len); // 모든 집에 대해 집과 사무실의 최소 거리를 계산
		}
		
		int d = Integer.parseInt(br.readLine()); // 철로의 길이
		if (d<minLen) { // 만약 집과 사무실 위치를 입력 받는 단계에서 구한 집의 최소 길이가 철로의 길이보다 크다면 0 출력 후 조기 종료 
			System.out.print(0);
			return;
		}
		
		Arrays.sort(H); // 집 좌표 정렬
		
		int maxElement = 0; 
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->o1.start-o2.start);
		
		for (int i=0;i<n;i++) {
			if (H[i].len<=d) {
				while(!pq.isEmpty() && H[i].end-pq.peek().start>d) {
					// 현재 pq의 peek 좌표의 start (= pq에 들어있는 좌표 정보 중 가장 왼쪽에 치우친 집의 시작점 점) 값과 현재 i번째 집의 끝 점 간의 길이가 d 안에 포함될 때까지 pq의 원소를 제거함
					pq.poll();
				}
				pq.add(H[i]); // i번째 집을 pq에 삽입
				maxElement = Math.max(pq.size(), maxElement); 
				// 현재 pq에 있는 원소 개수 = 집과 사무실 위치가 모두 길이 d인 선분에 포함되는 사람들의 수
			}
		}
		System.out.print(maxElement);
	}
}
