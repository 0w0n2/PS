import java.util.*;
import java.io.*;

/**
 * 메모리: 35,160KB, 시간: 492ms
 */

public class Main {
	
	static class Ball { // 각 공의 입력 받은 인덱스(idx), 색상(color) 저장
		int idx, color;
		Ball(int idx, int color){
			this.idx = idx;
			this.color = color;
		}
	}
	
	// 이건 걍 입력용 
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int inputInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException{
		int N = inputInt();
		
		ArrayList<ArrayList<Ball>> b = new ArrayList<>(); // size 별 공의 정보를 저장
		for (int i=0;i<=2000;i++) b.add(new ArrayList<>());
		
		for (int i=0;i<N;i++) {
			int color = inputInt();
			int size = inputInt();
			b.get(size).add(new Ball(i, color)); // size 에 대해 공의 정보를 저장
		}
		
		int[] result = new int[N]; // 입력 받은 인덱스에 따라 공 별 결과 저장
		int[] prefixSumPerColor = new int[N+1]; // 공의 색상 별 size 누적합 저장
		int total = 0; // 현재까지 탐색한 모든 공의 size 합 저장

		for (int i=1;i<=2000;i++) {
			
			ArrayList<Ball> bi = b.get(i);
			if (bi.isEmpty()) continue; // 해당 사이즈에 대한 공이 없음 (넘어가기)
			
			// size=i 인 모든 공에 대해 결과를 저장함
			for (int j=0;j<bi.size();j++) { // j번째 공의 결과 : 현재까지 저장된 모든 공의 합 - j번째 공과 동일한 색상이며 크기가 작은 공들의 합
				result[bi.get(j).idx] = total - prefixSumPerColor[bi.get(j).color];
			}
			for (int j=0;j<bi.size();j++) { // 변수 갱신
				prefixSumPerColor[bi.get(j).color] += i; // size=i 에 대해 색깔 별 누적합 저장
				total += i; // 현재까지 검색한 모든 공에 대한 누적합 저장
			}
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i:result) sb.append(i).append("\n");
		System.out.print(sb);
	} 
}
