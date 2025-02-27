import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static boolean[] col; 	// i번째 col가 선택됐는지 여부
	static boolean[] leftDown; 
	// ↙ 좌하 방향 대각선
	// 좌하 방향 : (row + col) 값이 같으면 같은 대각선에 속함 (우하 대각선 이동할 때 r++/c-- 이므로)
	
	static boolean[] rightDown; 
	// ↘ 우하 방향  대각선 
	// 우하 방향 : (row - col) 같이 같으면 같은 대각선에 속함 (좌하 대각선 이동할 때 r++/c++ 이므로(r/c의 차이가 일정하게 증가)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // N*N 크기의 체스판, N개의 퀸
		
		col = new boolean[N]; // i번째 col가 선택됐는지 여부 (true:선택)
		
		leftDown = new boolean[N*2]; 	// "0"부터 "N+N" [(N,N)일때] 까지 나올 수 있음 (총 2N개의 variation)
		rightDown = new boolean[N*2];	// "-N" [(0,N)] 부터 "N" [(N,0)] 까지 나올 수 있음 (총 2N개의 variation)
		
		queenMaker(0); // N개의 퀸을 놓을 수 있는 방법의 수?
		System.out.print(result);
	}
	
	// 퀸의 움직임 : 8방으로 어디든지 갈 수 있음
	static int result = 0;
	
	static void queenMaker(int r) {
		
		if (r==N) { // 모든 행(row)에 퀸을 1개씩 다 뒀음
			result++;
			return;
		}
		
		for (int c=0;c<N;c++) { // 어느 행에 퀸을 놓을까
			
			if (!isValid(r, c)) {
				col[c] = leftDown[r+c] = rightDown[r-c +(N-1)] = true; // i번째 col을 선택 처리
				// (N-1)은 음수 방지용(예를 들어 (0,N)일 때 -N..)으로 처리해준 것(보정)
				queenMaker(r+1);
				col[c] = leftDown[r+c] = rightDown[r-c +(N-1)] = false; // 백트래킹
			}
		}
	}
	
	static boolean isValid(int r, int candidateCol) {
		return col[candidateCol] || leftDown[r+candidateCol] || rightDown[r-candidateCol+(N-1)]; 
		// 선택 가능한 col이면 true가 리턴될 것(이전에 선택하지 않은 col)
		// 하나라도 true면 선택 불가능한 위치임을 리턴
	}

}