import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int [] x = new int [n]; // x(0) y(1)
        int [] y = new int [n]; // x(0) y(1)
        int [] min = new int [n];
        Arrays.fill(min, -1);

        for (int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken()); // x 좌표 입력
            y[i] = Integer.parseInt(st.nextToken()); // y 좌표 입력
        }

        for (int i=0;i<n;i++){ // x[i] 선택
            for (int j=0;j<n;j++){ // y[j] 선택  // 범위 중에 있는 x, y 좌표 일단 선택
                ArrayList<Integer> distance = new ArrayList<>();
                for (int k=0;k<n;k++) { // 체커 위치와 선택한 x, y 좌표의 거리 계산
                    distance.add(Math.abs(x[i] - x[k]) + Math.abs(y[j] - y[k]));
                }
                Collections.sort(distance); // 오름차순 정렬
                int sum = 0;
                for (int k=0;k<n;k++){
                    sum += distance.get(k);
                    if (min[k]==-1) min[k] = sum; // 처음 등록
                    else min[k] = Math.min(sum, min[k]); // 최소 거리를 갖는 x, y를 찾을 때까지 Math.min()으로 재등록...
                }
            }
        }
        for (int i=0;i<n;i++) System.out.print((min[i])+" ");
    }
}
