import java.io.*;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException{
		
		int N = readInt();
		int M = readInt();
		
		int[][] b = new int[N][M];
		
		// 윗면 입력
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				b[i][j] = readInt();
			}
		}
		
		int[] topFront = new int[M];
		int[] front = new int[M]; // 앞면 사용 여부
		for (int i=0;i<M;i++) front[i] = readInt();
		
		int[] topRight = new int[N];
		int[] right = new int[N]; // 옆면 사용 여부
		for (int i=N-1;i>=0;i--) right[i] = readInt();
		
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				
				if (b[i][j]==1) {
					if (front[j]==0||right[i]==0) { // 쌓아야 하는데 앞면/옆면 블럭이 없음
						System.out.print(-1);
						return;
					}
					b[i][j] = Math.min(front[j], right[i]);
				}

				b[i][j] = b[i][j]==0 ? 0 : Math.min(front[j], right[i]);
				topFront[j] = Math.max(b[i][j], topFront[j]); // 각 열 별 최대 높이
				topRight[i] = Math.max(b[i][j], topRight[i]); // 각 행 별 최대 높이
				
				sb.append(b[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		if(!isEnoughHeight(front, topFront)||!isEnoughHeight(right, topRight)) return;

		System.out.print(sb);
	}
	
	// 각 열/행 의 최대높이가 입력으로 들어온 앞면/옆면의 필요 높이와 일치한지 체크
	private static boolean isEnoughHeight(int[] inputH, int[] finalH) {
		for (int i=0;i<finalH.length;i++) {
			if (inputH[i]!=finalH[i]) {
				System.out.print(-1);
				return false;
			}
		}
		return true;
	}
	
	// 윗-앞-뒷 3면 할당된 숫자 합치되, 윗면 1일 때만 두개 합치는데, 두 개 중에 작은 수를 할당하자
	// 각 열/행의 최댓값이 필요한 앞면-옆면 값과 일치한지 체크해야 함 
	/*	
	 	윗면        최종
		0 1 0      0 4 0  
		1 1 1      1 3 3
		앞면 +
		1 4 3
		1 4 3
		옆면 +
		4 4 4
		3 3 3
	 */
}
