import java.io.*;

/**
 * Java | 메모리: 27,648kB, 시간: 96ms
 * @since 25-04-09 (수)
 * @author 이혜원
 */

public class Main {
	
    private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
     
    static int[][] map = new int[100][100]; // 입력으로 들어온 각 셀별 지형 높이
    static int[] colLine = new int[100];
    static boolean[] canMakeRow;
    static int N, X, result;
     
    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        N = readInt();
        X = readInt();
         
        canMakeRow = new boolean[N];
        result = 0;
         
        // 각 셀별 지형 높이 입력 받기
        for (int i=0;i<N;i++) {
            boolean cm = true; // 전처리 - 행 기준으로 동일한 높이를 가진 행인지 체크
             
            for (int j=0;j<N;j++) {
                map[i][j] = readInt();
                if (cm && j>0 && map[i][j]!=map[i][j-1]) cm = false;
            }
            if (cm) {
                canMakeRow[i] = true;
                result++;
            }
        }
        for (int i=0;i<N;i++) {
            if (!canMakeRow[i] && inspection(map[i])) result++; 
            if (inspection(getColLine(i))) result++;
        }
        System.out.println(result);
    }
     
    public static int[] getColLine(int col) {
        for (int i=0;i<N;i++) colLine[i] = map[i][col];
        return colLine;
    }
 
    public static boolean inspection(int[] line) {
        boolean[] isInstalled = new boolean[N];
         
        for (int i=1;i<N;i++) {
            int left = line[i-1];
            int right = line[i];
             
            if (left==right) continue;
            if (Math.abs(left-right)>=2) return false; 
             
            if (left<right) { // 왼쪽이 더 낮을 때 
                for (int x=1;x<=X;x++) {
                    int idx = i-x;
                    if (idx<0 || line[idx]!=left || isInstalled[idx]) return false;
                    isInstalled[idx] = true;
                }
            } else { // 오른쪽이 더 낮음
                for (int x=0;x<X;x++) {
                    int idx = i+x;
                    if (idx>=N || line[idx]!=right || isInstalled[idx]) return false;
                    isInstalled[idx] = true;
                }
            }
        }
        return true;
    }
}
