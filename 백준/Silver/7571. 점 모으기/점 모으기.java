import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 격자공간의 크기
        int m = Integer.parseInt(st.nextToken()); // 점 개수
        int [] x = new int[m]; // x 축 각 점 좌표 저장
        int [] y = new int[m]; // y 축 각 점 좌표 저장
        for (int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(x); // 중간지점 : m/2
        Arrays.sort(y); // x, y 축 각각 중간지점에서 만나는 게 항상 최단거리
        int sum = 0;
        for (int i=0;i<m;i++){
            sum += Math.abs(x[i]-x[m/2]) + Math.abs(y[i]-y[m/2]);
        }
        System.out.print(sum);
    }
}
