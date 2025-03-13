import java.io.*;
import java.util.*;

public class Main {
	static int[][] map = new int[9][9];
	static boolean[][] row = new boolean[9][9];	// 행 별 - 특정 숫자 사용했는지 체크
	static boolean[][] col = new boolean[9][9];	// 열 별 - 특정 숫자 사용했는지 체크 
	static boolean[][] group = new boolean[9][9];	// 그룹(3X3 칸으로 나눠지는) 별 - 특정 숫자 사용했는지 체크
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i=0;i<9;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<9;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]!=0) {
					row[i][map[i][j]-1] = true;
					col[j][map[i][j]-1] = true;
					int g = i/3 + (j/3)*3;	
					// g : 그룹 번호 
					// 0 1 2
					// 3 4 5
					// 6 7 8 
					group[g][map[i][j]-1] = true;
				} 
			}
		}
		sudoku(0, 0);
	}
	
	static boolean alreadyPrinted = false;
	static void sudoku(int x, int y) {
		
		if (alreadyPrinted) return;
		
		if (x==9) {	// 모든 ([0,0]~[8,8]) 칸 다 채웠음
			if (!alreadyPrinted) {
				printMap();
				alreadyPrinted = true;
			}
			return;
		}
		
		if (map[x][y]==0) {	// 0인 칸은 숫자 넣어줌
			for (int k=0;k<9;k++) {
				int g = x/3 + (y/3)*3;
				if (row[x][k]||col[y][k]||group[g][k]) continue;
				row[x][k] = col[y][k] = group[g][k] = true;
				map[x][y] = k+1;
				
				sudoku(y==8 ? x+1:x, (y+1)%9);
				map[x][y] = 0;
				row[x][k] = col[y][k] = group[g][k] = false;
			}
		} 
		else sudoku(y==8 ? x+1:x, (y+1)%9);	// 이미 채워진 칸은 넘어감
	}
	
	static void printMap() {	// 출력 용
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) sb.append(map[i][j]).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
