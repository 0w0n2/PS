import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [] num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i=0;i<n;i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num); // 오름차순 정렬
        int sum = 0;
        for (int i=0;i<n;i++){
            if (num[i]>sum+1){  // 새로 더해질 num[i]가 현재 누적된 측정 가능한 범위(sum) + 1 보다 클 때(연속 X -> 측정 가능한 범위 사이에 갭이 생김)
                System.out.println(sum+1);
                return;
            }
            sum += num[i];  // 누적합 -> 1부터 ~ sum 까지 수 만들 수 있음을 보장
        }
        System.out.println(sum+1);
    }
}
